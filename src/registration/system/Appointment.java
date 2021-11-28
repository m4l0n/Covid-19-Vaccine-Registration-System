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
    private int userID;
    private int effectivePeriod;
    private int vacBatchID;
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
    
    public void setUserID(int userID)
    {
        this.userID = userID;
    }
    
    public int getUserID()
    {
        return userID;
    }
    
    public void setEffectivePeriod(int effectivePeriod)
    {
        this.effectivePeriod = effectivePeriod;
    }
    
    public int getEffectivePeriod()
    {
        return effectivePeriod;
    }
    
    public void setVacBatchID(int vacBatchID)
    {
        this.vacBatchID = vacBatchID;
    }
    
    public int getVacBatchID()
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
    
}
