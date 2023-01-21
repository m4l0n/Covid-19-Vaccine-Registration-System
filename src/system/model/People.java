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
public class People extends User implements Serializable {
    private String peopleID;
    private Appointment dose1;
    private Appointment dose2;
    static final long serialVersionUID = 1L;
    
    public void setPeopleID(String peopleID)
    {
        this.peopleID = peopleID;
    }
    
    public String getPeopleID()
    {
        return peopleID;
    }
    
    public void setDose1(Appointment dose1)
    {
        this.dose1 = dose1;
    }
    
    public Appointment getDose1()
    {
        return dose1;
    }
    
    public void setDose2(Appointment dose2)
    {
        this.dose2 = dose2;
    }
    
    public Appointment getDose2()
    {
        return dose2;
    }

}
