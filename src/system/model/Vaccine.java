/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package system.model;

import java.io.Serializable;

/**
 *
 * @author nigel
 */
public class Vaccine implements Serializable{
    private String vaccineID;
    private String vaccineName;
    private int effectivePeriod;
    private int vaccineQuantity;
    private static final String dataVaccine = "dataVaccine.txt";
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
    
    public static String getDataVaccine()
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

}
