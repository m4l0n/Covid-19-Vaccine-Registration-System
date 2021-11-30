/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package registration.system;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import javax.swing.JOptionPane;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author nigel
 */
public class Appointment implements Serializable{
    private String appointmentID;
    private LocalDate date;
    private LocalTime time;
    private Centre centre;
    private String peopleID;
    private Vaccine vaccine;
    private int doseNum;
    private final String dataAppointment = "dataAppointment.txt";
    static final long serialVersionUID = 1L;
    
    public void setAppointmentID(String appointmentID)
    {
        this.appointmentID = appointmentID;
    }
    
    public String getAppointmentID()
    {
        return appointmentID;
    }
    
    
    public void setDate(LocalDate date)
    {
        this.date = date;
    }
    
    public LocalDate getDate()
    {
        return date;
    }
    
    public void setTime(LocalTime time)
    {
        this.time = time;
    }
    
    public LocalTime getTime()
    {
        return time;
    }  
    
    public void setPeopleID(String peopleID)
    {
        this.peopleID = peopleID;
    }
    
    public String getPeopleID()
    {
        return peopleID;
    }
    
    public void setVaccine(Vaccine vaccine)
    {
        this.vaccine = vaccine;
    }
    
    public Vaccine getVaccine()
    {
        return vaccine;
    }

    public void setDoseNum(int doseNum)
    {
        this.doseNum = doseNum;
    }
    
    public int getDoseNum()
    {
        return doseNum;
    }
    
    public Centre getCentre()
    {
        return centre;
    }
    
    public void setCentre(Centre centre)
    {
        this.centre = centre;
    }
    
    public String getDataAppointment()
    {
        return dataAppointment;
    }
    
    public void regAppointment(Appointment newAppointment){
        ArrayList<Appointment> tempAppointment = new ArrayList<Appointment>();
        ObjectInputStream ois = null;
        Appointment appointment = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(new Appointment().getDataAppointment()));
            Object obj = null;
            while ((obj = ois.readObject()) != null) {
                tempAppointment.add((Appointment)obj);

            }
        } catch (EOFException ex) {}
        catch (ClassNotFoundException ex) { ex.printStackTrace(); }
        catch (FileNotFoundException ex) { ex.printStackTrace(); }
        catch (IOException ex) { ex.printStackTrace(); }
        finally {
            try {
                if (ois != null) {
                    ois.close();
                }
            } catch (IOException ex) { ex.printStackTrace(); }
        }
        newAppointment.setAppointmentID("AID" + Integer.toString(generateNum(100000, 999999)));
        ObjectOutputStream oos = null;
        try {
            boolean idExist = false;
            for (Appointment existingAppointment:tempAppointment){
                if (existingAppointment.getAppointmentID().equals(newAppointment.getAppointmentID()))
                {
                    idExist = true;
                    break;
                }
            }
            if (idExist = true) { regAppointment(newAppointment); }
            else { tempAppointment.add(newAppointment); }
            for(Appointment existingAppointment:tempAppointment){
                oos.writeObject(existingAppointment);
            }
            newAppointment = null;
            tempAppointment.clear();
            oos.flush();
            oos.close();
            
        } catch (EOFException ex) { ex.printStackTrace(); }
        catch (FileNotFoundException ex) { ex.printStackTrace(); }
        catch (IOException ex) { JOptionPane.showMessageDialog(null, ex.toString(), "Error", JOptionPane.ERROR_MESSAGE); }
        finally {
            try {
                if (ois != null) {
                    ois.close();
                }
            } catch (IOException ex) { ex.printStackTrace(); }
        }
    }
    
    private static int generateNum(int min, int max){
        Random rand = new Random();
        return min + rand.nextInt((max - min) + 1);
    }
}
