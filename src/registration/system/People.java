/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package registration.system;

import java.awt.List;
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
import java.util.Random;

/**
 *
 * @author nigel
 */
public class People extends User implements Serializable{
    private String peopleID;
    private String dose1;
    private String dose2;
    private final String dataUser = "dataUser.txt";
    static final long serialVersionUID = 1L;
    
    public void setPeopleID(String peopleID)
    {
        this.peopleID = peopleID;
    }
    
    public String getPeopleID()
    {
        return peopleID;
    }
    
    public void setDose1(String dose1)
    {
        this.dose1 = dose1;
    }
    
    public String getDose1()
    {
        return dose1;
    }
    
    public void setDose2(String dose2)
    {
        this.dose2 = dose2;
    }
    
    public String getDose2()
    {
        return dose2;
    }
    
    public void registerProfile(People newPeople)
    {
        ArrayList<People> tempPeople = new ArrayList<People>();
        ObjectInputStream ois = null;
        Personnel personnel = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(dataUser));
            Object obj = null;
            while ((obj = ois.readObject()) != null) {
                if (!((User)obj).getUsername().equals("ADMIN"))
                {
                    tempPeople.add((People)obj);
                }
                else { personnel = ((Personnel)obj); }
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
            newPeople.setPeopleID("UID" + Integer.toString(generateNum(100000, 999999)));
            oos = new ObjectOutputStream(new FileOutputStream(dataUser));
            boolean idExists = false;
            for(People existingPeople:tempPeople){
                if(existingPeople.getPeopleID().equals(newPeople.getPeopleID())) {
                    idExists = true;
                }
            }
            if (idExists == false) {
                System.out.println(newPeople.getName() + "\n" + newPeople.getDate());
                tempPeople.add(newPeople);
            } else {
                registerProfile(newPeople);
            }
            for(People existingPeople:tempPeople){
                oos.writeObject(existingPeople);
            }
            oos.writeObject(personnel);
            newPeople = null;
            personnel = null;
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
    
    public void modifyProfile(People modifiedPpl)
    {
        ArrayList<People> tempUsers = new ArrayList <>();
        ObjectInputStream ois = null;
        Personnel personnel = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(dataUser));
            Object obj = null;
            while ((obj = ois.readObject()) != null) {
                if (!((User)obj).getUsername().equals("ADMIN"))
                {
                    if (!((People)obj).getUsername().equals(modifiedPpl.getUsername()))
                    {
                        tempUsers.add((People)obj);
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, "Profile Updated Successfully!");
//                        People newPeople = new People();
//                        newPeople.setUsername(ppl.getUsername());
//                        newPeople.setName(ppl.getName());
//                        newPeople.setPhoneNum(ppl.getPhoneNum());
//                        newPeople.setDate(ppl.getDate());
//                        newPeople.setState(ppl.getState());
//                        newPeople.setCitizenship(ppl.getCitizenship());
//                        newPeople.setGender(ppl.getGender());
//                        newPeople.setStatus(((People)obj).getStatus());
//                        newPeople.setPassword(((People)obj).getPassword());
//                        newPeople.setDose1(((People)obj).getDose1());
//                        newPeople.setDose2(((People)obj).getDose2());
//                        newPeople.setPeopleID(((People)obj).getPeopleID());
//                        newPeople.setPeopleID(((People)obj).getUserType());
//                        tempUsers.add(newPeople);
                        tempUsers.add(modifiedPpl);
                    }
                }
                else
                {
                    personnel = ((Personnel)obj);
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
        
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream(dataUser));
            for(People eachUser: tempUsers)
            {
                oos.writeObject(eachUser);
            }
            oos.writeObject(personnel);
            personnel = null;
            tempUsers.clear();
            oos.flush();
            oos.close();
        } catch (Exception e) { e.printStackTrace(); }
    }
    
    public void changePassword(People changedPassPpl)
    {
        ArrayList<People> tempUsers = new ArrayList <>();
        ObjectInputStream ois = null;
        Personnel personnel = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(dataUser));
            Object obj = null;
            while ((obj = ois.readObject()) != null) {
                if (!((User)obj).getUsername().equals("ADMIN"))
                {
                    if (!((People)obj).getUsername().equals(changedPassPpl.getUsername()))
                    {
                        tempUsers.add((People)obj);
                    }
                    else
                    {
                        tempUsers.add(changedPassPpl);
                    }
                }
                else {
                    personnel = ((Personnel)obj);
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
        
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream(dataUser));
            for(People eachUser: tempUsers)
            {
                oos.writeObject(eachUser);
            }
            oos.writeObject(personnel);
            JOptionPane.showMessageDialog(null, "Password Changed Successfully!");
            personnel = null;
            tempUsers.clear();
            oos.flush();
            oos.close();
        } catch (Exception e) { e.printStackTrace(); }
    }
    
    public People searchPeople(String peopleID)
    {
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(dataUser));
            Object obj = null;
            while ((obj = ois.readObject()) != null) {
                if (!((User)obj).getUsername().equals("ADMIN"))
                {
                    if(((People)obj).getPeopleID().equals(peopleID)){
                        return ((People)obj);
                    }
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
    
    public People getPeopleDetails(String userID)
    {
        People ppl = new People();
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(dataUser));
            Object obj = null;
            while ((obj = ois.readObject()) != null) {
                if(((People)obj).getUsername().equals(userID)){
                    ppl = ((People)obj);
                    break;
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
        return ppl;
    }
    
    public boolean deletePeople(String userID)
    {
        boolean idFound = false;
        ArrayList<People> tempUsers = new ArrayList <>();
        ObjectInputStream ois = null;
        Personnel personnel = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(dataUser));
            Object obj = null;
            while ((obj = ois.readObject()) != null) {
                if (!((User)obj).getUsername().equals("ADMIN"))
                {
                    if (!((People)obj).getUsername().equals(userID))
                    {
                        tempUsers.add((People)obj);
                    }
                    else
                    {
                        idFound = true;
                    }
                }
                else {
                    personnel = ((Personnel)obj);
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
        
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream(dataUser));
            for(People eachUser: tempUsers)
            {
                oos.writeObject(eachUser);
            }
            oos.writeObject(personnel);
            personnel = null;
            tempUsers.clear();
            oos.flush();
            oos.close();
        } catch (Exception e) { e.printStackTrace(); }
        finally { return idFound; }
    }
    
    private static int generateNum(int min, int max){
        Random rand = new Random();
        return min + rand.nextInt((max - min) + 1);
    }

}
