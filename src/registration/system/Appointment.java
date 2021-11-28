/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package registration.system;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author nigel
 */
public class Appointment implements Serializable{
    private int appointmentID;
    private LocalDate date;
    private LocalTime time;
    private Centre centre;
    private String peopleID;
    private int effectivePeriod;
    private String vacBatchID;
    private int doseNum;
    
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
    
    public void setEffectivePeriod(int effectivePeriod)
    {
        this.effectivePeriod = effectivePeriod;
    }
    
    public int getEffectivePeriod()
    {
        return effectivePeriod;
    }
    
    public void setVacBatchID(String vacBatchID)
    {
        this.vacBatchID = vacBatchID;
    }
    
    public String getVacBatchID()
    {
        return vacBatchID;
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
    
}
