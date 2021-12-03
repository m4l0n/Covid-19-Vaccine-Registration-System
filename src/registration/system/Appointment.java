/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package registration.system;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import javax.swing.JOptionPane;
import java.io.ObjectOutputStream;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author nigel
 */
public class Appointment implements Serializable{
    private String appointmentID;
    private String date;
    private LocalTime time;
    private Centre centre;
    private People people;
    private Vaccine vaccine;
    private String expDate;
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
    
    public void setDate(String date)
    {
        this.date = date;
    }
    
    public String getDate()
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
    
    public void setPeople(People people)
    {
        this.people = people;
    }
    
    public People getPeople()
    {
        return people;
    }
    
    public void setVaccine(Vaccine vaccine)
    {
        this.vaccine = vaccine;
    }
    
    public Vaccine getVaccine()
    {
        return vaccine;
    }
    
    public void setExpDate(String date)
    {
        Vaccine vaccine = new Vaccine();
        int ep = vaccine.getEffectivePeriod();
        this.expDate = LocalDate.parse(date).plusDays(ep).toString();
    }
    
    public String getExpDate() 
    {
        return expDate;
    }
    
    public boolean checkDoseNum(String userID)
    {
        Appointment app = new Appointment();
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(new Appointment().getDataAppointment()));
            Object obj = null;
            while ((obj = ois.readObject()) != null) {
                if(((User)obj).getUsername().equals(userID)){
                    return true;
                }
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
        return false;
    }

    public void setDoseNum(boolean result)
    {
        //If there has been previous appointments, meaning dose number is 2, else 1
        if (result = false)
        {
            doseNum = 1; 
        }
        else 
        {
            doseNum = 2;
        }
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
            oos = new ObjectOutputStream(new FileOutputStream(new User().getDataUser()));
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
    
    public ArrayList<Appointment> getAppointments(String vacName, String centreName)
    {
        ArrayList<Appointment> tempAppointment = new ArrayList<Appointment>();
        ObjectInputStream ois = null;
        Appointment appointment = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(new Appointment().getDataAppointment()));
            Object obj = null;
            while ((obj = ois.readObject()) != null) {
                if (((Appointment)obj).getCentre().getCentreName().equals(centreName) 
                        && ((Appointment)obj).getVaccine().getVaccineName().equals(vacName))
                {
                    tempAppointment.add((Appointment)obj);
                }
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
        return tempAppointment;
    }
    
    private static int generateNum(int min, int max){
        Random rand = new Random();
        return min + rand.nextInt((max - min) + 1);
    }
    
    public Appointment getAppointmentDetails(String appointmentID)
    {
        Appointment app = new Appointment();
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(new Appointment().getDataAppointment()));
            Object obj = null;
            while ((obj = ois.readObject()) != null) {
                if(((Appointment)obj).getAppointmentID().equals(appointmentID)){
                    return ((Appointment)obj);
                }
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
        return null;
    }
    
    public ArrayList<Appointment> getFutureAppointments(String userID, LocalDate currentDate)
    {
        ArrayList<Appointment> tempAppointment = new ArrayList<Appointment>();
        ObjectInputStream ois = null;
        Appointment appointment = null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        try {
            ois = new ObjectInputStream(new FileInputStream(new Appointment().getDataAppointment()));
            Object obj = null;
            while ((obj = ois.readObject()) != null) {
                if (((Appointment)obj).getPeople().getUsername().equals(userID) && 
                        (LocalDate.parse(((Appointment)obj).getDate(), formatter)
                                .compareTo(currentDate) > 0))
                {
                    tempAppointment.add((Appointment)obj);
                }
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
        return tempAppointment;
    }
    
    public void modifyAppointment(Appointment savedAppointment)
    {
        ArrayList<Appointment> tempAppointment = new ArrayList <>();
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(new Appointment().getDataAppointment()));
            Object obj = null;
            while ((obj = ois.readObject()) != null) 
            {
                if (!((Appointment)obj).getAppointmentID().equals(savedAppointment.getAppointmentID()))
                {
                    tempAppointment.add((Appointment)obj);
                }
                else
                {
                    tempAppointment.add(savedAppointment);
                    JOptionPane.showMessageDialog(null, "Appointment Updated Successfully!");
                }
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
        
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream(new Appointment().getDataAppointment()));
            oos.writeObject(tempAppointment);
            tempAppointment.clear();
            oos.flush();
            oos.close();
        } catch (IOException e) { e.printStackTrace(); }
    }
    
    public boolean deleteAppointment(String appointmentID)
    {
        boolean idFound = false;
        ArrayList<Appointment> tempAppointment = new ArrayList <>();
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(new Appointment().getDataAppointment()));
            Object obj = null;
            while ((obj = ois.readObject()) != null) {
                if (!((Appointment)obj).getAppointmentID().equals(appointmentID))
                {
                    tempAppointment.add((Appointment)obj);
                }
                else
                {
                    idFound = true;
                }
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
        
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream(new Appointment().getDataAppointment()));
            oos.writeObject(tempAppointment);
            tempAppointment.clear();
            oos.flush();
            oos.close();
        } catch (Exception e) { e.printStackTrace(); }
        finally { return idFound; }
    }
}
