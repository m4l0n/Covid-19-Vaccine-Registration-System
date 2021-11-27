/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package registration.system;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author nigel
 */
public class Appointment {
    public int appointmentID;
    public LocalDate date;
    public LocalTime time;
    public Centre centre;
    public int peopleID;
    public int effectivePeriod;
    public int vacBatchID;
    public int doseNum;
    
}
