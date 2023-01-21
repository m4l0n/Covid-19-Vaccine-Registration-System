package system.service;

import system.model.Vaccine;

import java.io.*;

public class VaccineService {
    public Vaccine searchVaccine(String vaccineName)
    {
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(Vaccine.getDataVaccine()));
            Object obj = null;
            while ((obj = ois.readObject()) != null) {
                if (((Vaccine)obj).getVaccineName().equals(vaccineName))
                {
                    return ((Vaccine)obj);
                }
            }
        } catch (EOFException ex) {}
        catch (ClassNotFoundException ex) { ex.printStackTrace(); }
        catch (FileNotFoundException ex) { ex.printStackTrace(); }
        catch (IOException ex) { ex.printStackTrace(); }
        finally {
            try {
                if (ois != null) {
                    ois.close();
                }
            } catch (IOException ex) { ex.printStackTrace(); }
        }
        return null;
    }
}
