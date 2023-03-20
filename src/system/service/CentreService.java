package system.service;

import system.common.exception.ObjectNotFoundException;
import system.common.fileIO.ReadNWrite;
import system.model.Appointment;
import system.model.Centre;
import system.model.Vaccine;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static system.common.fileIO.ThrowingConsumer.throwingConsumerWrapper;

public class CentreService implements ReadNWrite<Centre> {
    public Optional<Centre> searchCentre(String centreName)
    {
        return readFile().stream()
                .filter(centre -> centre.getCentreName().equals(centreName))
                .findFirst();
    }

    public List<String> getCentreVaccines(String centreName)
    {
        try {
            Optional<Centre> optionalCentre = searchCentre(centreName);
            Centre centre = optionalCentre.orElseThrow(() -> new ObjectNotFoundException("Centre not found"));

            return centre.getVaccine().stream()
                    .map(Vaccine::getVaccineName)
                    .collect(Collectors.toList());
        } catch (ObjectNotFoundException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public Map<String, String> getAllCentre()
    {
        return readFile().stream()
                .collect(Collectors.toMap(Centre::getCentreName, Centre::getCentreLocation));
    }

    public int getRemainingVaccine(String vacName, String centreName, String apDate)
    {
        List<Appointment> apList = new AppointmentService().getAppointments(vacName, centreName);
        if (!apList.isEmpty())
        {
            int apCount = Math.toIntExact(apList.stream()
                    .filter(appointment -> appointment.getDate().equals(apDate)).count());
            int vacQuantity = 0;
            Centre centre = searchCentre(centreName).get();
            Vaccine vaccine = centre.getVaccine().stream()
                    .filter(eachVac -> eachVac.getVaccineName().equals(vacName))
                    .findFirst()
                    .orElse(new Vaccine());
            vacQuantity = vaccine.getVaccineQuantity();
            return vacQuantity - apCount + getAdditionalVaccineQuantity(vacName, apDate, centreName);
        }
        return 500 + getAdditionalVaccineQuantity(vacName, apDate, centreName);
    }

    public void setAdditionalVaccineQuantity(String vacName, String date, String centreName, int quantity)
    {
        try(FileWriter fw = new FileWriter(Centre.getDataAdditionalVaccine(), true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw))
        {
            out.println(vacName + "," + date + "," + centreName + "," + Integer.toString(quantity));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public int getAdditionalVaccineQuantity(String vacName, String date, String centreName)
    {
        try
        {
            BufferedReader in = new BufferedReader(new FileReader(Centre.getDataAdditionalVaccine()));
            return in.lines().map(line -> line.split(","))
                    .filter(parts -> parts[0].equals(vacName) && parts[1].equals(centreName) && parts[2].equals(date))
                    .mapToInt(parts -> Integer.parseInt(parts[3]))
                    .sum();
        } catch (FileNotFoundException ex) {
            return 0;
        }
    }

    public void modifyCentre(Centre newCentre)
    {
        List<Centre> updatedList = readFile().stream().map(centre -> {
            if (centre.getCentreID().equals(newCentre.getCentreID())) {
                return newCentre;
            }
            return centre;
        }).collect(Collectors.toList());

        update(updatedList);
    }

    @Override
    public void update(List<Centre> arrayList) {
        try(ObjectOutputStream oos =
                    new ObjectOutputStream(Files.newOutputStream(Paths.get(Centre.getDataCentre())));) {
            arrayList.forEach(throwingConsumerWrapper(oos::writeObject));
            oos.flush();
        } catch (Exception e) { e.printStackTrace(); }
    }

    @Override
    public List<Centre> readFile() {
        try (ObjectInputStream ois =
                     new ObjectInputStream(Files.newInputStream(Paths.get(Centre.getDataCentre())))) {
            List<Centre> centreList = new ArrayList<>();
            readFileHelper(ois, centreList);
            return centreList;
        } catch (IOException ex) {
            ex.printStackTrace();
            return new ArrayList<>();
        }
    }
}
