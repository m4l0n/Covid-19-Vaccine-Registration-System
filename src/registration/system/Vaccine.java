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

/**
 *
 * @author nigel
 */
public class Vaccine implements Serializable{
    private String vaccineID;
    private String vaccineName;
    private int effectivePeriod;
    private int vaccineQuantity;
    private final String dataVaccine = "dataVaccine.txt";
    static final long serialVersionUID = 1L;

    public void setVacID(String vaccineID)
    {
        this.vaccineID = vaccineID;
    }
    
    public String getVacID()
    {
        return vaccineID;
    }

    public void setVaccineName(String vaccineName)
    {
        this.vaccineName = vaccineName;
    }
    
    public String getVaccineName()
    {
        return vaccineName;
    }
    
    public String getDataVaccine()
    {
        return dataVaccine;
    }
    
    public void setEffectivePeriod(int effectivePeriod)
    {
        this.effectivePeriod = effectivePeriod;
    }
    
    public int getEffectivePeriod()
    {
        return effectivePeriod;
    }
    
    public void setVaccineQuantity(int vaccineQuantity)
    {
        this.vaccineQuantity = vaccineQuantity;
    }
    
    public int getVaccineQuantity()
    {
        return vaccineQuantity;
    }
    
    
    public Vaccine searchVaccine(String vaccineName)
    {
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(dataVaccine));
            Object obj = null;
            while ((obj = ois.readObject()) != null) {
                if (((Vaccine)obj).getVaccineName().equals(vaccineName))
                {
                    return ((Vaccine)obj);
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
}
