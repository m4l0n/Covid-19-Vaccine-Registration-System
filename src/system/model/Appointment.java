/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package system.model;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nigel
 */
public class Appointment implements Serializable {
    private String appointmentID;
    private String date;
    private LocalTime time;
    private Centre centre;
    private People people;
    private Vaccine vaccine;
    private String expDate;
    private int doseNum;

    static final long serialVersionUID = 1L;

    private static final String dataAppointment = "dataAppointment.txt";

    public static String getDataAppointment()
    {
        return dataAppointment;
    }

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

    public String getExpDate()
    {
        return expDate;
    }

    public void setExpDate(String date)
    {
        try {
            SimpleDateFormat dcn = new SimpleDateFormat("dd-MM-yyyy");
            int ep = this.vaccine.getEffectivePeriod();
            Calendar c = Calendar.getInstance();
            c.setTime(dcn.parse(date));
            c.add(Calendar.MONTH, ep);
            this.expDate = dcn.format(c.getTime());
        } catch (ParseException ex) {
            Logger.getLogger(Appointment.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setDoseNum(boolean result)
    {
        //If there has been previous appointments, meaning dose number is 2, else 1
        if (!result)
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

}
