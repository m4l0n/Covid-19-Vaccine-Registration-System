/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package registration.system;

import java.time.LocalDate;

/**
 *
 * @author nigel
 */
public class Vaccine {
    private int vaccineBatchID;
    private String vaccineName;
    private int vaccineQuantity;
    private LocalDate vaccineExpiry;

    public void setVacBatchID()
    {
        this.vaccineBatchID = vaccineBatchID;
    }
    
    public int getVacBatchID()
    {
        return vaccineBatchID;
    }

    public void setVaccineName()
    {
        this.vaccineName = vaccineName;
    }
    
    public String sgtVaccineName()
    {
        return vaccineName;
    }
    
    public void setVaccineQuantity()
    {
        this.vaccineQuantity = vaccineQuantity;
    }
    
    public int getVaccineQuantity()
    {
        return vaccineQuantity;
    }
    
    public void setVaccineExpiry()
    {
        this.vaccineExpiry = vaccineExpiry;
    }
    
    public LocalDate getVaccineExpiry()
    {
        return vaccineExpiry;
    }

}
