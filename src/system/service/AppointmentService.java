package system.service;

import system.model.Appointment;

import javax.swing.*;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Random;

public class AppointmentService implements ReadNWrite<Appointment> {

    public boolean checkDoseNum(String userID)
    {
        ArrayList<Appointment> tempAppointment = new ArrayList<Appointment>();
        try {
            tempAppointment = readFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (Appointment appointment:tempAppointment) {
            if (appointment.getPeople().getUsername().equals(userID))
            {
                return true;
            }
        }
        return false;
    }

    public void regAppointment(Appointment newAppointment){
        ArrayList<Appointment> tempAppointment = new ArrayList<Appointment>();
        ArrayList<Appointment> newAppList = new ArrayList<>();
        try {
            tempAppointment = readFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
        newAppointment.setAppointmentID("AID" + Integer.toString(generateNum(100000, 999999)));
        boolean idExist = false;
        for (Appointment existingAppointment:tempAppointment){
            if (existingAppointment.getAppointmentID().equals(newAppointment.getAppointmentID()))
            {
                idExist = true;
                break;
            }
        }
        //to regenerate ID
        if (idExist) { regAppointment(newAppointment); }
        else { newAppList.add(newAppointment); }
        try {
            update(newAppList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Appointment> getAppointments(String vacName, String centreName)
    {
        ArrayList<Appointment> tempAppointment = new ArrayList<Appointment>();
        ArrayList<Appointment> newAppList = new ArrayList<>();
        try {
            tempAppointment = readFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (Appointment existingAppointment:tempAppointment){
            if (existingAppointment.getCentre().getCentreName().equals(centreName)
                    && (existingAppointment.getVaccine().getVaccineName().equals(vacName)))
            {
                newAppList.add(existingAppointment);
            }
        }
        return newAppList;
    }

    private static int generateNum(int min, int max){
        Random rand = new Random();
        return min + rand.nextInt((max - min) + 1);
    }

    public Appointment getAppointmentDetails(String appointmentID)
    {
        ArrayList<Appointment> tempAppointment = new ArrayList<Appointment>();
        try {
            tempAppointment = readFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (Appointment existingAppointment:tempAppointment){
            if(existingAppointment.getAppointmentID().equals(appointmentID)){
                return (existingAppointment);
            }
        }
        return null;
    }

    public ArrayList<Appointment> getFutureAppointments(String userID, LocalDate currentDate)
    {
        ArrayList<Appointment> tempAppointment = new ArrayList<Appointment>();
        ArrayList<Appointment> futureAppointments = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        try {
            tempAppointment = readFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (Appointment appointment:tempAppointment) {
            if (appointment.getPeople().getUsername().equals(userID) &&
                    (LocalDate.parse(appointment.getDate(), formatter).isAfter(currentDate)))
            {
                futureAppointments.add(appointment);
            }
        }
        return futureAppointments;
    }

    public void modifyAppointment(Appointment savedAppointment)
    {
        ArrayList<Appointment> tempAppointment = new ArrayList <>();
        ArrayList<Appointment> newAppList = new ArrayList <>();
        try {
            tempAppointment = readFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (Appointment appointment:tempAppointment) {
            if (!(appointment.getAppointmentID().equals(savedAppointment.getAppointmentID())))
            {
                newAppList.add(appointment);
            }
            else
            {
                newAppList.add(savedAppointment);
                JOptionPane.showMessageDialog(null, "Appointment Updated Successfully!");
            }
        }
        try {
            update(newAppList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean deleteAppointment(String appointmentID)
    {
        boolean idFound = false;
        ArrayList<Appointment> tempAppointment = new ArrayList <>();
        ArrayList<Appointment> newAppList = new ArrayList <>();
        try {
            tempAppointment = readFile();
            for (Appointment appointment:tempAppointment) {
                if (appointment.getAppointmentID().equals(appointmentID)) {
                    idFound = true;
                }
                else newAppList.add(appointment);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            update(newAppList);
        } catch (Exception e) { e.printStackTrace(); }

        return idFound;
    }

    public int getAppointmentCount(LocalDate currentDate){
        ArrayList<Appointment> appointmentList = new ArrayList<>();
        int count = 0;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        try {
            appointmentList = readFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (Appointment appointment:appointmentList) {
            if(LocalDate.parse(appointment.getDate(), formatter).isAfter(currentDate)) {
                count++;
            }
        }
        return count;
    }

    public Appointment getDose1(String userID)
    {
        Appointment app = new Appointment();
        ArrayList<Appointment> appointmentList = new ArrayList<>();
        try {
            appointmentList = readFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (Appointment appointment:appointmentList) {
            if(appointment.getPeople().getUsername().equals(userID) && (appointment.getDoseNum() == 1)) {
                app = appointment;
            }
        }
        return app;
    }

    public Appointment getDose2(String userID)
    {
        Appointment app = new Appointment();
        ArrayList<Appointment> appointmentList = new ArrayList<>();
        try {
            appointmentList = readFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (Appointment appointment:appointmentList) {
            if(appointment.getPeople().getUsername().equals(userID) && (appointment.getDoseNum() == 2)) {
                app = appointment;
            }
        }
        return app;
    }

    public void deletePeopleAppointment(String peopleID)
    {
        ArrayList<Appointment> appointmentList = new ArrayList <>();
        ArrayList<Appointment> filteredAppointmentList = new ArrayList<>();
        try {
            appointmentList = readFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (Appointment appointment:appointmentList) {
            if (!appointment.getPeople().getPeopleID().equals(peopleID))
            {
                filteredAppointmentList.add(appointment);
            }
        }
        try {
            update(filteredAppointmentList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(ArrayList<Appointment> arrayList) throws Exception {
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(Files.newOutputStream(Paths.get(Appointment.getDataAppointment())));
            for (Appointment eachAP:arrayList) {
                oos.writeObject(eachAP);
            }
            arrayList.clear();
            oos.flush();
            oos.close();
        } catch (Exception e) { e.printStackTrace(); }
    }

    @Override
    public ArrayList<Appointment> readFile() throws Exception {
        ArrayList<Appointment> tempAppointment = new ArrayList <>();
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(Files.newInputStream(Paths.get(Appointment.getDataAppointment())));
            Object obj = null;
            while ((obj = ois.readObject()) != null) {
                tempAppointment.add((Appointment)obj);
            }
        } catch (EOFException ex) {} catch (ClassNotFoundException | IOException ex) { ex.printStackTrace(); } finally {
            try {
                if (ois != null) {
                    ois.close();
                }
            } catch (IOException ex) { ex.printStackTrace(); }
        }
        return tempAppointment;
    }
}
