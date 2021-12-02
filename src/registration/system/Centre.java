/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package registration.system;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nigel
 */
public class Centre implements Serializable{
    private String centreID;
    private String centreName;
    private String centreLocation;
    private ArrayList<Vaccine> vaccineList;
    private final String dataCentre = "dataCenter.txt";
    static final long serialVersionUID = 1L;
    
    public void setCentreID(String centreID)
    {
        this.centreID = centreID;
    }
    
    public String getCentreID()
    {
        return centreID;
    }
    
    public void setCentreName(String centreName)
    {
        this.centreName = centreName;
    }
    
    public String getCentreName()
    {
        return centreName;
    }
    
    public void setCentreLocation(String centreLocation)
    {
        this.centreLocation = centreLocation;
    }
    
    public String getCentreLocation()
    {
        return centreLocation;
    }
    
    public void setVaccine(ArrayList<Vaccine> vaccineList)
    {
        this.vaccineList = vaccineList;
    }
    
    public ArrayList<Vaccine> getVaccine()
    {
        return vaccineList;
    }
    
    public String getDataCentre()
    {
        return dataCentre;
    }
    
    public Centre searchCentre(String centreName)
    {
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(dataCentre));
            Object obj = null;
            while ((obj = ois.readObject()) != null) {
                if (((Centre)obj).getCentreName().equals(centreName))
                {
                    return ((Centre)obj);
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
    
    public ArrayList<String> getCentreVaccines(String centreName)
    {
        ArrayList<String> vacNames = new ArrayList<String>();
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(dataCentre));
            Object obj = null;
            while ((obj = ois.readObject()) != null) {
                if (((Centre)obj).getCentreName().equals(centreName))
                {
                    for(Vaccine eachVaccine:((Centre)obj).getVaccine())
                    {
                        vacNames.add(eachVaccine.getVaccineName());
                    }
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
        return vacNames;
    }
    
    public HashMap<String, String> getAllCentre()
    {
        HashMap<String, String> centreList = new HashMap<String, String>();
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(dataCentre));
            Object obj = null;
            while ((obj = ois.readObject()) != null) {
                centreList.put(((Centre)obj).getCentreName(), 
                        ((Centre)obj).getCentreLocation());
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
        return centreList; 
    }
    
    public int getRemainingVaccine(String vacName, String centreName, String apDate)
    {
        int count=0;
        ArrayList<Appointment> apList = new Appointment().getAppointments(vacName, centreName);
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
            for (Vaccine eachVac:new Centre().searchCentre(centreName).getVaccine())
            {
                if (eachVac.getVaccineName().equals(vacName))
                {
                    vacQuantity = eachVac.getVaccineQuantity();
                    int remainingVac = vacQuantity - count + getAdditionalVaccineQuantity(vacName, apDate, centreName);
                    return remainingVac;
                }
            }
        }
        return getAdditionalVaccineQuantity(vacName, apDate, centreName);
    }
    
    public void setAdditionalVaccineQuantity(String vacName, String date, String centreName, int quantity)
    {
        try (PrintWriter out = new PrintWriter("additionalVaccine.txt")) {
            out.println(vacName + "," + date + "," + centreName + "," + quantity);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Centre.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public int getAdditionalVaccineQuantity(String vacName, String date, String centreName)
    {
        try
        {
            BufferedReader in = new BufferedReader(new FileReader("additionalVaccine.txt"));
            Scanner read = new Scanner(in);
            read.useDelimiter(",");
            String tempVac, tempDate, tempCentre;
            int additionalSupply;
            while(read.hasNext())
            {
                tempCentre = read.next();
                tempDate = read.next();
                tempVac = read.next();
                additionalSupply = Integer.parseInt(read.next());
                if (tempVac.equals(vacName) && tempDate.equals(date) && tempCentre.equals(centreName))
                {
                    return (additionalSupply);
                    
                }
            }
        } catch (FileNotFoundException ex) {
            return 0;
        } 
        return 0;
    }
    
    public void modifyCentre(Centre newCentre)
    {
        ArrayList<Centre> tempCentre = new ArrayList<Centre>();
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(dataCentre));
            Object obj = null;
            while ((obj = ois.readObject()) != null) {
                if (!((Centre)obj).getCentreID().equals(newCentre.getCentreID()))
                {
                    tempCentre.add((Centre)obj);
                }
                else
                {
                    tempCentre.add(newCentre);
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
            oos = new ObjectOutputStream(new FileOutputStream(dataCentre));
            for(Centre eachCentre:tempCentre)
            {
                oos.writeObject(eachCentre);
            }
            tempCentre.clear();
            oos.flush();
            oos.close();
        } catch (Exception e) { e.printStackTrace(); }
    }
}
