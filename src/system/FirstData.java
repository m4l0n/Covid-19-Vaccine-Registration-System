package system;

import system.model.*;
import system.service.PeopleService;

import java.io.*;
import java.nio.file.Paths;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import static java.nio.file.Files.newOutputStream;

public class FirstData {
    public static void firstAdmin()
    {
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(newOutputStream(Paths.get(User.getDataUser())));
            Personnel firstAdmin = new Personnel();
            firstAdmin.setName("ADMIN");
            firstAdmin.setUsername("ADMIN");
            firstAdmin.setPassword("AdminPersonnel1");
            firstAdmin.setGender("Male");
            firstAdmin.setDate("01-01-1990");
            firstAdmin.setPhoneNum("0111111111");
            firstAdmin.setStatus("Fully Vaccinated");
            firstAdmin.setState("Kuala Lumpur");
            firstAdmin.setUserType("Personnel");
            firstAdmin.setCitizenship("Malaysian");
            oos.writeObject(firstAdmin);
            firstAdmin = null;
            oos.flush();
            oos.close();
        } catch (Exception e) {}
    }

    public static void firstUsers()
    {
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(User.getDataUser()));
        } catch (FileNotFoundException ex) {
            ObjectOutputStream oos = null;
            try {
                oos = new ObjectOutputStream(newOutputStream(Paths.get(User.getDataUser())));
                oos.flush();
                oos.close();
                //create admin account first
                firstAdmin();
                Appointment emptyAppointment = new Appointment();
                emptyAppointment.setAppointmentID("none");
                People newPeople1 = new People();
                newPeople1.setName("Ali");
                newPeople1.setPhoneNum("0104234524");
                newPeople1.setUsername("011201-14-1032");
                newPeople1.setPassword("Testing1");
                newPeople1.setDate("01-12-1996");
                newPeople1.setState("Kelantan");
                newPeople1.setCitizenship("Malaysian");
                newPeople1.setGender("Male");
                newPeople1.setUserType("People");
                newPeople1.setStatus("Fully Vaccinated");
                newPeople1.setDose1(emptyAppointment);
                newPeople1.setDose2(emptyAppointment);
                new PeopleService().registerProfile(newPeople1);

                People newPeople2 = new People();
                newPeople2.setName("Muthu");
                newPeople2.setPhoneNum("0123258934");
                newPeople2.setUsername("020314-13-0853");
                newPeople2.setPassword("Testing2");
                newPeople2.setDate("14-03-2001");
                newPeople2.setState("Kuala Lumpur");
                newPeople2.setCitizenship("Malaysian");
                newPeople2.setGender("Female");
                newPeople2.setUserType("People");
                newPeople2.setStatus("Unvaccinated");
                newPeople2.setDose1(emptyAppointment);
                newPeople2.setDose2(emptyAppointment);
                new PeopleService().registerProfile(newPeople2);

                People newPeople3 = new People();
                newPeople3.setName("Powell");
                newPeople3.setPhoneNum("0123417648");
                newPeople3.setUsername("050219-10-1942");
                newPeople3.setPassword("Testing3");
                newPeople3.setDate("19-02-1983");
                newPeople3.setState("Selangor");
                newPeople3.setCitizenship("Malaysian");
                newPeople3.setGender("Male");
                newPeople3.setUserType("People");
                newPeople3.setStatus("Unvaccinated");
                newPeople3.setDose1(emptyAppointment);
                newPeople3.setDose2(emptyAppointment);
                new PeopleService().registerProfile(newPeople3);

                People newPeople4 = new People();
                newPeople4.setName("Omar");
                newPeople4.setPhoneNum("0173174375");
                newPeople4.setUsername("010329-02-1934");
                newPeople4.setPassword("Testing4");
                newPeople4.setDate("29-03-1999");
                newPeople4.setState("Sarawak");
                newPeople4.setCitizenship("Malaysian");
                newPeople4.setGender("Female");
                newPeople4.setUserType("People");
                newPeople4.setStatus("Unvaccinated");
                newPeople4.setDose1(emptyAppointment);
                newPeople4.setDose2(emptyAppointment);
                new PeopleService().registerProfile(newPeople4);

                People newPeople5 = new People();
                newPeople5.setName("Lee Sheng Yi");
                newPeople5.setPhoneNum("0146732674");
                newPeople5.setUsername("020213-14-0921");
                newPeople5.setPassword("Testing5");
                newPeople5.setDate("13-02-1994");
                newPeople5.setState("Labuan");
                newPeople5.setCitizenship("Malaysian");
                newPeople5.setGender("Male");
                newPeople5.setUserType("People");
                newPeople5.setStatus("Partially Vaccinated");
                newPeople5.setDose1(emptyAppointment);
                newPeople5.setDose2(emptyAppointment);
                new PeopleService().registerProfile(newPeople5);

                People newPeople6 = new People();
                newPeople6.setName("Wong Jie Hao");
                newPeople6.setPhoneNum("0124527854");
                newPeople6.setUsername("030713-14-9782");
                newPeople6.setPassword("Testing6");
                newPeople6.setDate("13-07-1990");
                newPeople6.setState("Pahang");
                newPeople6.setCitizenship("Malaysian");
                newPeople6.setGender("Female");
                newPeople6.setUserType("People");
                newPeople6.setStatus("Unvaccinated");
                newPeople6.setDose1(emptyAppointment);
                newPeople6.setDose2(emptyAppointment);
                new PeopleService().registerProfile(newPeople6);

                People newPeople7 = new People();
                newPeople7.setName("Camille");
                newPeople7.setPhoneNum("0137532785");
                newPeople7.setUsername("021014-13-0932");
                newPeople7.setPassword("Testing7");
                newPeople7.setDate("14-10-1983");
                newPeople7.setState("Kuala Lumpur");
                newPeople7.setCitizenship("Malaysian");
                newPeople7.setGender("Male");
                newPeople7.setUserType("People");
                newPeople7.setStatus("Unvaccinated");
                newPeople7.setDose1(emptyAppointment);
                newPeople7.setDose2(emptyAppointment);
                new PeopleService().registerProfile(newPeople7);

                People newPeople8 = new People();
                newPeople8.setName("Liew Yee Jing");
                newPeople8.setPhoneNum("0127538762");
                newPeople8.setUsername("010201-13-1832");
                newPeople8.setPassword("Testing8");
                newPeople8.setDate("01-02-2001");
                newPeople8.setState("Perak");
                newPeople8.setCitizenship("Malaysian");
                newPeople8.setGender("Female");
                newPeople8.setUserType("People");
                newPeople8.setStatus("Unvaccinated");
                newPeople8.setDose1(emptyAppointment);
                newPeople8.setDose2(emptyAppointment);
                new PeopleService().registerProfile(newPeople8);

                People newPeople9 = new People();
                newPeople9.setName("Booma");
                newPeople9.setPhoneNum("0153276538");
                newPeople9.setUsername("031219-13-0124");
                newPeople9.setPassword("Testing9");
                newPeople9.setDate("19-12-1999");
                newPeople9.setState("Kedah");
                newPeople9.setCitizenship("Indonesian");
                newPeople9.setGender("Male");
                newPeople9.setUserType("People");
                newPeople9.setStatus("Unvaccinated");
                newPeople9.setDose1(emptyAppointment);
                newPeople9.setDose2(emptyAppointment);
                new PeopleService().registerProfile(newPeople9);

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

    public static void firstVaccineCentres()
    {
        ObjectInputStream ois = null;
        Vaccine vaccine1 = new Vaccine();
        Vaccine vaccine2 = new Vaccine();
        Vaccine vaccine3 = new Vaccine();
        Vaccine vaccine4 = new Vaccine();
        Vaccine vaccine5 = new Vaccine();
        Vaccine vaccine6 = new Vaccine();

        try {
            ois = new ObjectInputStream(new FileInputStream(Vaccine.getDataVaccine()));
        } catch (FileNotFoundException ex) {
            ObjectOutputStream oos = null;
            try {
                oos = new ObjectOutputStream(newOutputStream(Paths.get(Vaccine.getDataVaccine())));
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
            ois = new ObjectInputStream(new FileInputStream(Centre.getDataCentre()));
        } catch (FileNotFoundException ex) {
            ObjectOutputStream oos2 = null;
            try {
                oos2 = new ObjectOutputStream(newOutputStream(Paths.get(Centre.getDataCentre())));
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
            ois3 = new ObjectInputStream(new FileInputStream(Appointment.getDataAppointment()));
        } catch (FileNotFoundException ex) {
            ObjectOutputStream oos3 = null;
            try {
                oos3 = new ObjectOutputStream(newOutputStream(Paths.get(Appointment.getDataAppointment())));

                Appointment appointment2 = new Appointment();
                appointment2.setAppointmentID("AID" + Integer.toString(generateNum(100000, 999999)));
                appointment2.setDate("05-12-2021");
                appointment2.setTime(LocalTime.parse("11:00"));
                appointment2.setPeople(new PeopleService().getPeopleDetails("020213-14-0921"));
                appointment2.setCentre(centre1);
                appointment2.setVaccine(vaccine1);
                appointment2.setExpDate("05-12-2021");
                appointment2.setDoseNum(true);

                Appointment appointment3 = new Appointment();
                appointment3.setAppointmentID("AID" + Integer.toString(generateNum(100000, 999999)));
                appointment3.setDate("08-12-2021");
                appointment3.setTime(LocalTime.parse("13:30"));
                appointment3.setPeople(new PeopleService().getPeopleDetails("020314-13-0853"));
                appointment3.setCentre(centre1);
                appointment3.setVaccine(vaccine2);
                appointment3.setExpDate("08-12-2021");
                appointment3.setDoseNum(false);

                Appointment appointment4= new Appointment();
                appointment4.setAppointmentID("AID" + Integer.toString(generateNum(100000, 999999)));
                appointment4.setDate("09-12-2021");
                appointment4.setTime(LocalTime.parse("14:00"));
                appointment4.setPeople(new PeopleService().getPeopleDetails("050219-10-1942"));
                appointment4.setCentre(centre1);
                appointment4.setVaccine(vaccine2);
                appointment4.setExpDate("09-12-2021");
                appointment4.setDoseNum(false);

                Appointment appointment5 = new Appointment();
                appointment5.setAppointmentID("AID" + Integer.toString(generateNum(100000, 999999)));
                appointment5.setDate("10-12-2021");
                appointment5.setTime(LocalTime.parse("12:00"));
                appointment5.setPeople(new PeopleService().getPeopleDetails("010329-02-1934"));
                appointment5.setCentre(centre1);
                appointment5.setVaccine(vaccine1);
                appointment5.setExpDate("10-12-2021");
                appointment5.setDoseNum(false);

                Appointment appointment6 = new Appointment();
                appointment6.setAppointmentID("AID" + Integer.toString(generateNum(100000, 999999)));
                appointment6.setDate("04-12-2021");
                appointment6.setTime(LocalTime.parse("12:00"));
                appointment6.setPeople(new PeopleService().getPeopleDetails("011201-14-1032"));
                appointment6.setCentre(centre1);
                appointment6.setVaccine(vaccine1);
                appointment6.setExpDate("04-12-2021");
                appointment6.setDoseNum(false);

                Appointment appointment7 = new Appointment();
                appointment7.setAppointmentID("AID" + Integer.toString(generateNum(100000, 999999)));
                appointment7.setDate("05-12-2021");
                appointment7.setTime(LocalTime.parse("12:00"));
                appointment7.setPeople(new PeopleService().getPeopleDetails("011201-14-1032"));
                appointment7.setCentre(centre1);
                appointment7.setVaccine(vaccine1);
                appointment7.setExpDate("05-12-2021");
                appointment7.setDoseNum(true);

                Appointment apList[] = {appointment2, appointment3, appointment4, appointment5, appointment6, appointment7};
                for (Appointment eachAP:apList){
                    oos3.writeObject(eachAP);
                    System.out.println(eachAP.getPeople().getUsername());
                }
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
