/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package registration.system;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author nigel
 */
public class Vaccine {
    private int vaccineBatchID;
    private String vaccineName;
    private int vaccineQuantity;
    private LocalDate vaccineExpiry;
    private final String dataVaccine = "dataVaccine.txt";
    static final long serialVersionUID = 1L;

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
    
    public String getVaccineName()
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
    
    public String getDataVacine()
    {
        return dataVaccine;
    }
    
    public ArrayList<Vaccine> searchVaccine(String vaccineName)
    {
        ArrayList<Vaccine> vacList = new ArrayList<Vaccine>();
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(dataVaccine));
            Object obj = null;
            while ((obj = ois.readObject()) != null) {
                if (!((Vaccine)obj).getVaccineName().equals(vaccineName))
                {
                    vacList.add(((Vaccine)obj));
                }
            }
            return vacList;
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
