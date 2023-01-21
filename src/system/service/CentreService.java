package system.service;

import system.model.Appointment;
import system.model.Centre;
import system.model.Vaccine;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class CentreService implements ReadNWrite<Centre> {
    public Centre searchCentre(String centreName)
    {
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(Files.newInputStream(Paths.get(Centre.getDataCentre())));
            Object obj = null;
            while ((obj = ois.readObject()) != null) {
                if (((Centre)obj).getCentreName().equals(centreName))
                {
                    return ((Centre)obj);
                }
            }
        } catch (EOFException ex) {} catch (ClassNotFoundException | IOException ex) { ex.printStackTrace(); } finally {
            try {
                if (ois != null) {
                    ois.close();
                }
            } catch (IOException ex) { ex.printStackTrace(); }
        }
        return null;
    }

    public ArrayList<String> getCentreVaccines(String centreName)
    {
        ArrayList<Centre> centres = new ArrayList<>();
        ArrayList<String> vacNames = new ArrayList<>();
        try {
            centres = readFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (Centre centre: centres) {
            if (centre.getCentreName().equals(centreName))
            {
                for(Vaccine eachVaccine: centre.getVaccine())
                {
                    vacNames.add(eachVaccine.getVaccineName());
                }
            }
        }
        return vacNames;
    }

    public HashMap<String, String> getAllCentre()
    {
        HashMap<String, String> centreMap = new HashMap<String, String>();
        ArrayList<Centre> centreList = new ArrayList<>();
        try {
            centreList = readFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (Centre centre:centreList) {
            centreMap.put(centre.getCentreName(), centre.getCentreLocation());
        }
        return centreMap;
    }

    public int getRemainingVaccine(String vacName, String centreName, String apDate)
    {
        int count=0;
        ArrayList<Appointment> apList = new AppointmentService().getAppointments(vacName, centreName);
        if (!apList.isEmpty())
        {
            for (Appointment eachAP:apList)
            {
                if (eachAP.getDate().equals(apDate))
                {
                    count++;
                }
            }
            int vacQuantity = 0;
            for (Vaccine eachVac:searchCentre(centreName).getVaccine())
            {
                if (eachVac.getVaccineName().equals(vacName))
                {
                    vacQuantity = eachVac.getVaccineQuantity();
                    int remainingVac = vacQuantity - count + getAdditionalVaccineQuantity(vacName, apDate, centreName);
                    return remainingVac;
                }
            }
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
            Scanner read = new Scanner(in);
            read.useDelimiter(",");
            String tempVac, tempDate, tempCentre;
            int totalAdditionalSupply = 0;
            while(read.hasNext())
            {
                tempVac = read.next();
                tempCentre = read.next();
                tempDate = read.next();
                int additionalSupply = Integer.valueOf(read.nextLine().replace(",",""));
                if (tempVac.equals(vacName) && tempDate.equals(date) && tempCentre.equals(centreName))
                {
                    totalAdditionalSupply += additionalSupply;
                }
            }
            return totalAdditionalSupply;
        } catch (FileNotFoundException ex) {
            return 0;
        }
    }

    public void modifyCentre(Centre newCentre)
    {
        ArrayList<Centre> tempCentre = new ArrayList<Centre>();
        ArrayList<Centre> newCentreList = new ArrayList<Centre>();
        try {
            tempCentre = readFile();
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (Centre centre:tempCentre) {
            if (!centre.getCentreID().equals(newCentre.getCentreID()))
            {
                newCentreList.add(centre);
            }
            else
            {
                newCentreList.add(newCentre);
            }
        }

        try {
            update(newCentreList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(ArrayList<Centre> arrayList) throws Exception {
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(Files.newOutputStream(Paths.get(Centre.getDataCentre())));
            for(Centre eachCentre:arrayList)
            {
                oos.writeObject(eachCentre);
            }
            arrayList.clear();
            oos.flush();
            oos.close();
        } catch (Exception e) { e.printStackTrace(); }
    }

    @Override
    public ArrayList<Centre> readFile() throws Exception {
        ArrayList<Centre> tempCentre = new ArrayList <>();
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(Files.newInputStream(Paths.get(Centre.getDataCentre())));
            Object obj = null;
            while ((obj = ois.readObject()) != null) {
                tempCentre.add((Centre) obj);
            }
        } catch (EOFException ex) {} catch (ClassNotFoundException | IOException ex) { ex.printStackTrace(); } finally {
            try {
                if (ois != null) {
                    ois.close();
                }
            } catch (IOException ex) { ex.printStackTrace(); }
        }
        return tempCentre;
    }
}
