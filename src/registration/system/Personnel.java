/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package registration.system;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 *
 * @author nigel
 */
public class Personnel extends User implements Serializable{
    private String personnelID;
    private final String dataUser = "dataUser.txt";
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
            ois = new ObjectInputStream(new FileInputStream(dataUser));
        } catch (FileNotFoundException ex) {
            ObjectOutputStream oos = null;
            try {
                oos = new ObjectOutputStream(new FileOutputStream(dataUser));
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
}
