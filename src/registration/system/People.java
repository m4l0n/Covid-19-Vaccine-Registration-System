/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package registration.system;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import java.io.Serializable;

/**
 *
 * @author nigel
 */
public class People extends User implements Serializable{
    private String peopleID;
    private final String dataUser = "dataUser.txt";
    
    public void setPeopleID(String peopleID)
    {
        this.peopleID = peopleID;
    }
    
    public String getPeopleID()
    {
        return peopleID;
    }
    
    public void registerProfile(People newPeople)
    {
        ArrayList<People> tempPeople = new ArrayList<People>();
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(dataUser));
            Object obj = null;
            while ((obj = ois.readObject()) != null) {
                tempPeople.add((People)obj);
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
        
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream(dataUser));
            boolean idExists = false;
            for(People existingPeople:tempPeople){
                if(existingPeople.getPeopleID().equals(newPeople.getPeopleID())) {
                    idExists = true;
                }
            }
            if (idExists == false) {
                tempPeople.add(newPeople);
            } else {
                registerProfile(newPeople);
            }
            for(People existingPeople:tempPeople){
                oos.writeObject(existingPeople);
            }
            JOptionPane.showMessageDialog(null, "Account registered "
                    + "successfully. Plese return to login page to continue.");
            System.out.println(newPeople.toString());
            newPeople = null;
            tempPeople.clear();
            oos.flush();
            oos.close();
            
        } catch (EOFException ex) { ex.printStackTrace(); }
        catch (FileNotFoundException ex) { ex.printStackTrace(); }
        catch (IOException ex) { JOptionPane.showMessageDialog(null, ex.toString(), "Error", JOptionPane.ERROR_MESSAGE); }
        finally {
            try {
                if (ois != null) {
                    ois.close();
                }
            } catch (IOException ex) { ex.printStackTrace(); }
        }
    }
}
