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
import java.util.ArrayList;
import java.util.HashMap;

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
    
    public ArrayList<Centre> searchCentre(String centreName)
    {
        ArrayList<Centre> centreList = new ArrayList<Centre>();
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(dataCentre));
            Object obj = null;
            while ((obj = ois.readObject()) != null) {
                if (!((Centre)obj).getCentreName().equals(centreName))
                {
                    centreList.add(((Centre)obj));
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
        return centreList; 
    }
    
    public ArrayList<String> getCentreVaccines(String centreName)
    {
        ArrayList<String> vacNames = new ArrayList<String>();
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(dataCentre));
            Object obj = null;
            while ((obj = ois.readObject()) != null) {
                if (!((Centre)obj).getCentreName().equals(centreName))
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
}
