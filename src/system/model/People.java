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

    public People() {

    }

    //When modifying existing People
    public People(String peopleID,
                  String name,
                  String phoneNum,
                  String userName,
                  String userPass,
                  String dob,
                  String state,
                  String citizenship,
                  String gender,
                  String status,
                  Appointment dose1,
                  Appointment dose2) {
        super(name, phoneNum, gender, state, citizenship, status, dob, userName, userPass, "People");
        Appointment emptyAppointment = new Appointment("none");
        this.peopleID = peopleID;
        this.dose1 = dose1 != null ? dose1 : emptyAppointment;
        this.dose2 = dose2 != null ? dose1 : emptyAppointment;
    }

    //When registering new People
    public People(String name,
                  String phoneNum,
                  String userName,
                  String userPass,
                  String dob,
                  String state,
                  String citizenship,
                  String gender,
                  String status) {
        super(name, phoneNum, gender, state, citizenship, status, dob, userName, userPass, "People");
        Appointment emptyAppointment = new Appointment("none");
        this.dose1 = emptyAppointment;
        this.dose2 = emptyAppointment;
    }

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
