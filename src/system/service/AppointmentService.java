package system.service;

import system.common.fileIO.ReadNWrite;
import system.model.Appointment;

import javax.swing.JOptionPane;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import static system.common.fileIO.ThrowingConsumer.throwingConsumerWrapper;

public class AppointmentService implements ReadNWrite<Appointment> {

    public boolean checkDoseNum(String userID)
    {
        return readFile().stream()
                .anyMatch(appointment -> appointment.getPeople().getUsername().equals(userID));
    }

    public void regAppointment(Appointment newAppointment){
        List<Appointment> appointmentList = readFile();
        //generate new appointment ID
        newAppointment.setAppointmentID("AID" + generateNum(100000, 999999));
        appointmentList.stream()
                .filter(existingAppointment -> existingAppointment.getAppointmentID().equals(newAppointment.getAppointmentID()))
                .findAny().ifPresentOrElse(
                        appointment -> regAppointment(newAppointment), // if exists, try again
                        () -> {
                            JOptionPane.showMessageDialog(null, "Appointment Added Successfully.");
                            appointmentList.add(newAppointment);
                            update(appointmentList);
                        }
                );
    }

    public List<Appointment> getAppointments(String vacName, String centreName)
    {
        return readFile().stream()
                .filter(existingAppointment -> existingAppointment.getCentre().getCentreName().equals(centreName))
                .filter(existingAppointment -> (existingAppointment.getVaccine().getVaccineName().equals(vacName)))
                .collect(Collectors.toList());
    }

    private static int generateNum(int min, int max){
        Random rand = new Random();
        return min + rand.nextInt((max - min) + 1);
    }


    public Optional<Appointment> getAppointmentDetails(String appointmentID)
    {
        return readFile().stream()
                .filter(existingAppointment -> existingAppointment.getAppointmentID().equals(appointmentID))
                .findFirst();
    }

    public List<Appointment> getFutureAppointments(String userID, LocalDate currentDate)
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return readFile().stream()
                .filter(appointment -> appointment.getPeople().getUsername().equals(userID))
                .filter(appointment -> (LocalDate.parse(appointment.getDate(), formatter).isAfter(currentDate)))
                .collect(Collectors.toList());
    }

    public void modifyAppointment(Appointment savedAppointment) {
        List<Appointment> updatedList = readFile().stream().map(appointment -> {
            if (!(appointment.getAppointmentID().equals(savedAppointment.getAppointmentID()))) {
                return appointment;
            }
            return savedAppointment;
        }).collect(Collectors.toList());

        update(updatedList);
    }

    public boolean deleteAppointment(String appointmentID)
    {
        List<Appointment> appointmentList = readFile();
        List<Appointment> updatedList = appointmentList.stream()
                .filter(appointment -> !appointment.getAppointmentID().equals(appointmentID))
                .collect(Collectors.toList());

        update(updatedList);

        return appointmentList.size() - updatedList.size() == 1;
    }

    public int getAppointmentCount(LocalDate currentDate){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return Math.toIntExact(readFile().stream()
                .filter(appointment -> LocalDate.parse(appointment.getDate(), formatter).isAfter(currentDate))
                .count());
    }

    public Optional<Appointment> getDose1(String userID)
    {
        return readFile().stream()
                .filter(appointment -> appointment.getPeople().getUsername().equals(userID) && (appointment.getDoseNum() == 1))
                .findFirst();
    }

    public Optional<Appointment> getDose2(String userID)
    {
        return readFile().stream()
                .filter(appointment -> appointment.getPeople().getUsername().equals(userID) && (appointment.getDoseNum() == 2))
                .findFirst();
    }

    public void deletePeopleAppointment(String peopleID)
    {
        List<Appointment> updatedList = readFile().stream()
                .filter(appointment -> !appointment.getPeople().getPeopleID().equals(peopleID))
                .collect(Collectors.toList());

        update(updatedList);
    }

    @Override
    public void update(List<Appointment> arrayList) {
        try (ObjectOutputStream oos =
                     new ObjectOutputStream(Files.newOutputStream(Paths.get(Appointment.getDataAppointment())))) {
            arrayList.forEach(throwingConsumerWrapper(oos::writeObject));
            oos.flush();
        } catch (Exception e) { e.printStackTrace(); }
    }

    @Override
    public List<Appointment> readFile() {
        try (ObjectInputStream ois
                     = new ObjectInputStream(Files.newInputStream(Paths.get(Appointment.getDataAppointment())))) {
            List<Appointment> appointmentList = new ArrayList<>();
            readFileHelper(ois, appointmentList);
            return appointmentList;
        } catch (IOException ex) {
            ex.printStackTrace();
            return new ArrayList<>();
        }
    }
}
