/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package registration.system;

import java.awt.List;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import static java.util.Collections.list;
import java.util.Random;

/**
 *
 * @author nigel
 */
public class Personnel extends User implements Serializable{
    private String personnelID;
    static final long serialVersionUID = 1L;
    
    public void setPersonnelID(String personnelID)
    {
        this.personnelID = personnelID;
    }
    
    public String getpersonnelID()
    {
        return personnelID;
    }
    
    public void firstAdmin()
    {
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(new User().getDataUser()));
        } catch (FileNotFoundException ex) {
            ObjectOutputStream oos = null;
            try {
                oos = new ObjectOutputStream(new FileOutputStream(new User().getDataUser()));
                Personnel firstAdmin = new Personnel();
                firstAdmin.setName("ADMIN");
                firstAdmin.setUsername("ADMIN");
                firstAdmin.setPassword("AdminPersonnel1");
                firstAdmin.setGender("Male");
                firstAdmin.setDate("01-01-1990");
                firstAdmin.setPhoneNum(0111111111);
                firstAdmin.setStatus("Fully Vaccinated");
                firstAdmin.setState("Kuala Lumpur");
                firstAdmin.setUserType("Personnel");
                firstAdmin.setCitizenship("Malaysian");
                oos.writeObject(firstAdmin);
                firstAdmin = null;
                oos.flush();
                oos.close();
            } catch (Exception e) {}
        } catch (IOException ex) { ex.printStackTrace(); }
        finally {
            try {
                if (ois != null) {
                    ois.close();
                }
            } catch (IOException ex) { ex.printStackTrace(); }
        }
    }
    
    public void firstVaccineCentres()
    {
        ObjectInputStream ois = null;
        Vaccine vaccine1 = new Vaccine();
        Vaccine vaccine2 = new Vaccine();
        Vaccine vaccine3 = new Vaccine();
        Vaccine vaccine4 = new Vaccine();
        Vaccine vaccine5 = new Vaccine();
        Vaccine vaccine6 = new Vaccine();

        try {
            ois = new ObjectInputStream(new FileInputStream(new Vaccine().getDataVaccine()));
        } catch (FileNotFoundException ex) {
            ObjectOutputStream oos = null;
            try {
                oos = new ObjectOutputStream(new FileOutputStream(new Vaccine().getDataVaccine()));
                vaccine1.setVaccineName("Pfizer-BioNtech");
                vaccine1.setEffectivePeriod(3);
                vaccine1.setVacID("VID" + Integer.toString(generateNum(100000, 999999)));

                vaccine2.setVaccineName("AstraZeneca");
                vaccine2.setEffectivePeriod(3);
                vaccine2.setVacID("VID" + Integer.toString(generateNum(100000, 999999)));

                vaccine3.setVaccineName("Janssen/Ad26.COV 2.S");
                vaccine3.setEffectivePeriod(1);
                vaccine3.setVacID("VID" + Integer.toString(generateNum(100000, 999999)));

                vaccine4.setVaccineName("Sputnik V");
                vaccine4.setEffectivePeriod(2);
                vaccine4.setVacID("VID" + Integer.toString(generateNum(100000, 999999)));

                vaccine5.setVaccineName("Sinovac-CoronaVac");
                vaccine5.setEffectivePeriod(3);
                vaccine5.setVacID("VID" + Integer.toString(generateNum(100000, 999999)));

                vaccine6.setVaccineName("Cansino Biologics");
                vaccine6.setEffectivePeriod(2);
                vaccine6.setVacID("VID" + Integer.toString(generateNum(100000, 999999)));

                Vaccine vacList[] = {vaccine1, vaccine2, vaccine3, vaccine4, vaccine5};
                
                for (Vaccine eachVaccine:vacList)
                {
                    oos.writeObject(eachVaccine);
                }
                vacList = null;
                oos.flush();
                oos.close();
                } catch (Exception e) {}
        } catch (IOException ex) { ex.printStackTrace(); }
        finally {
            try {
                if (ois != null) {
                    ois.close();
                }
            } catch (IOException ex) { ex.printStackTrace(); }
        }

        Centre centre1 = new Centre();
        Centre centre2 = new Centre();
        Centre centre3 = new Centre();
        Centre centre4 = new Centre();
        Centre centre5 = new Centre();
        Centre centre6 = new Centre();
        Centre centre7 = new Centre();
        Centre centre8 = new Centre();

        ObjectInputStream ois2 = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(new Centre().getDataCentre()));
        } catch (FileNotFoundException ex) {
            ObjectOutputStream oos2 = null;
            try {
                oos2 = new ObjectOutputStream(new FileOutputStream(new Centre().getDataCentre()));
                vaccine1.setVaccineQuantity(500);
                vaccine2.setVaccineQuantity(500);
                vaccine3.setVaccineQuantity(500);
                vaccine4.setVaccineQuantity(500);
                vaccine5.setVaccineQuantity(500);
                vaccine6.setVaccineQuantity(500);
                centre1.setCentreID("CID" + Integer.toString(generateNum(100000, 999999)));
                centre1.setCentreName("World Trade Centre KL");
                centre1.setCentreLocation("Kuala Lumpur");
                centre1.setVaccine(new ArrayList<Vaccine>(Arrays.asList(vaccine1, vaccine2)));
                
                centre2.setCentreID("CID" + Integer.toString(generateNum(100000, 999999)));
                centre2.setCentreName("Wisma Belia");
                centre2.setCentreLocation("Kuala Lumpur");
                centre2.setVaccine(new ArrayList<Vaccine>(Arrays.asList(vaccine3, vaccine4)));

                centre3.setCentreID("CID" + Integer.toString(generateNum(100000, 999999)));
                centre3.setCentreName("Stadium Tun Abdul Razak");
                centre3.setCentreLocation("Pahang");
                centre3.setVaccine(new ArrayList<Vaccine>(Arrays.asList(vaccine5)));
                
                centre4.setCentreID("CID" + Integer.toString(generateNum(100000, 999999)));
                centre4.setCentreName("Pusat Sains dan Kreativiti");
                centre4.setCentreLocation("Terengganu");
                centre4.setVaccine(new ArrayList<Vaccine>(Arrays.asList(vaccine6)));
                
                centre5.setCentreID("CID" + Integer.toString(generateNum(100000, 999999)));
                centre5.setCentreName("Kuala Lumpur Convention Centre");
                centre5.setCentreLocation("Kuala Lumpur");
                centre5.setVaccine(new ArrayList<Vaccine>(Arrays.asList(vaccine1,vaccine3)));
                
                centre6.setCentreID("CID" + Integer.toString(generateNum(100000, 999999)));
                centre6.setCentreName("Ideal Convention Centre");
                centre6.setCentreLocation("Selangor");
                centre6.setVaccine(new ArrayList<Vaccine>(Arrays.asList(vaccine2,vaccine5)));
                
                centre7.setCentreID("CID" + Integer.toString(generateNum(100000, 999999)));
                centre7.setCentreName("Borneo Convention Centre Kuching");
                centre7.setCentreLocation("Sarawak");
                centre7.setVaccine(new ArrayList<Vaccine>(Arrays.asList(vaccine4)));
                
                centre8.setCentreID("CID" + Integer.toString(generateNum(100000, 999999)));
                centre8.setCentreName("Axiata Arena Bukit Jalil");
                centre8.setCentreLocation("Kuala Lumpur");
                centre8.setVaccine(new ArrayList<Vaccine>(Arrays.asList(vaccine2)));
                
                Centre centList[] = {centre1,centre2, centre3, centre4, centre5, centre6, centre7, centre8};
                for (Centre eachCentre:centList)
                {
                    oos2.writeObject(eachCentre);
                }
                centList = null;
                oos2.flush();
                oos2.close();
            } catch (Exception e) {}
        } catch (IOException ex) { ex.printStackTrace(); }
        finally {
            try {
                if (ois2 != null) {
                    ois2.close();
                }
            } catch (IOException ex) { ex.printStackTrace(); }
        }
        
        ObjectInputStream ois3 = null;
        try {
            ois3 = new ObjectInputStream(new FileInputStream(new Appointment().getDataAppointment()));
        } catch (FileNotFoundException ex) {
            ObjectOutputStream oos3 = null;
            try {
                oos3 = new ObjectOutputStream(new FileOutputStream(new Appointment().getDataAppointment()));
                Appointment appointment1 = new Appointment();
                appointment1.setAppointmentID("AID" + Integer.toString(generateNum(100000, 999999)));
                appointment1.setDate("01-12-2021");
                appointment1.setTime(LocalTime.parse("12:00"));
                appointment1.setPeople(new People().getPeopleDetails("010718-14-0985"));
                appointment1.setCentre(centre1);
                appointment1.setVaccine(vaccine1);
                appointment1.setDoseNum(1);

                Appointment appointment2 = new Appointment();
                appointment2.setAppointmentID("AID" + Integer.toString(generateNum(100000, 999999)));
                appointment2.setDate("01-12-2021");
                appointment2.setTime(LocalTime.parse("11:00"));
                appointment2.setPeople(new People().getPeopleDetails("011123-14-1234"));
                appointment2.setCentre(centre1);
                appointment2.setVaccine(vaccine1);
                appointment2.setDoseNum(2);

                Appointment appointment3 = new Appointment();
                appointment3.setAppointmentID("AID" + Integer.toString(generateNum(100000, 999999)));
                appointment3.setDate("02-12-2021");
                appointment3.setTime(LocalTime.parse("13:30"));
                appointment3.setPeople(new People().getPeopleDetails("021123-16-1973"));
                appointment3.setCentre(centre1);
                appointment3.setVaccine(vaccine2);
                appointment3.setDoseNum(1);

                Appointment appointment4= new Appointment();
                appointment4.setAppointmentID("AID" + Integer.toString(generateNum(100000, 999999)));
                appointment4.setDate("03-12-2021");
                appointment4.setTime(LocalTime.parse("14:00"));
                appointment4.setPeople(new People().getPeopleDetails("031201-12-2014"));
                appointment4.setCentre(centre1);
                appointment4.setVaccine(vaccine2);
                appointment4.setDoseNum(1);

                Appointment appointment5 = new Appointment();
                appointment5.setAppointmentID("AID" + Integer.toString(generateNum(100000, 999999)));
                appointment5.setDate("01-12-2021");
                appointment5.setTime(LocalTime.parse("12:00"));
                appointment5.setPeople(new People().getPeopleDetails("020201-12-0531"));
                appointment5.setCentre(centre1);
                appointment5.setVaccine(vaccine1);
                appointment5.setDoseNum(1);
                
                Appointment apList[] = {appointment1, appointment2, appointment3, appointment4, appointment5};
                for (Appointment eachAP:apList){oos3.writeObject(eachAP);}
                oos3.flush();
                oos3.close();
            } catch (Exception e) {}
        } catch (IOException ex) { ex.printStackTrace(); }
        finally {
            try {
                if (ois3 != null) {
                    ois3.close();
                }
            } catch (IOException ex) { ex.printStackTrace(); }
        }
    }
    
    private static int generateNum(int min, int max){
        Random rand = new Random();
        return min + rand.nextInt((max - min) + 1);
    }
}
