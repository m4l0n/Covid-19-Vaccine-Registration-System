package system.service;

import system.model.People;
import system.model.Personnel;
import system.model.User;

import javax.swing.*;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Random;

public class PeopleService implements ReadNWrite<Object> {
    public void registerProfile(People newPeople)
    {
        ArrayList<Object> tempUsers = new ArrayList<>();
        try {
            tempUsers = readFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
        newPeople.setPeopleID("PID" + Integer.toString(generateNum(100000, 999999)));
        boolean idExist = false;
        for(Object existingUser:tempUsers){
            if (!((User) existingUser).getUsername().equals("ADMIN")) {
                if (((People) existingUser).getPeopleID().equals(newPeople.getPeopleID()))
                {
                    idExist = true;
                    break;
                }
            }
        }
        if (!idExist) {
            tempUsers.add(newPeople);
        }
        else { registerProfile(newPeople); }
        try {
            update(tempUsers);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void modifyProfile(People modifiedPpl)
    {
        ArrayList<Object> tempUsers = new ArrayList<>();
        ArrayList<Object> newUserList = new ArrayList <>();
        Personnel personnel = null;
        try {
            tempUsers = readFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (Object existingUser:tempUsers) {
            if (!((User) existingUser).getUsername().equals("ADMIN")) {
                if (!((User) existingUser).getUsername().equals(modifiedPpl.getUsername())) {
                    newUserList.add(existingUser);
                } else {
                    JOptionPane.showMessageDialog(null, "Profile Updated Successfully!");
                    newUserList.add(modifiedPpl);
                }
            } else personnel = (Personnel) existingUser;
        }
        newUserList.add(personnel);
        try {
            update(newUserList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public People getPeopleDetails(String userID)
    {
        ArrayList<Object> tempUsers = new ArrayList<>();
        try {
            tempUsers = readFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
        for(Object existingUser:tempUsers){
            if (!((User) existingUser).getUsername().equals("ADMIN")) {
                if (((People) existingUser).getUsername().equals(userID))
                {
                    return (People) existingUser;
                }
            }
        }
        return null;
    }

    public boolean deletePeople(String userID)
    {
        ArrayList<Object> tempUsers = new ArrayList<>();
        ArrayList<Object> newUserList = new ArrayList<>();
        try {
            tempUsers = readFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
        boolean idExist = false;
        Personnel personnel = null;
        for(Object existingUser:tempUsers){
            if (!((User)existingUser).getUsername().equals("ADMIN"))
            {
                if (!((People)existingUser).getUsername().equals(userID))
                {
                    newUserList.add((People)existingUser);
                }
                else
                {
                    idExist = true;
                }
            }
            else {
                personnel = ((Personnel)existingUser);
            }
        }
        try {
            newUserList.add(personnel);
            update(newUserList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return idExist;
    }

    private static int generateNum(int min, int max){
        Random rand = new Random();
        return min + rand.nextInt((max - min) + 1);
    }

    public boolean checkUserID(String userID)
    {
        ArrayList<Object> tempUsers = new ArrayList<>();
        try {
            tempUsers = readFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
        for(Object existingUser:tempUsers){
            if(((User)existingUser).getUsername().equals(userID)){
                return true;
            }
        }
        return false;
    }

    @Override
    public void update(ArrayList<Object> arrayList) throws Exception {
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(Files.newOutputStream(Paths.get(User.getDataUser())));
            for (Object eachPeople:arrayList) {
                if (!((User) eachPeople).getUsername().equals("ADMIN")) oos.writeObject((People) eachPeople);
                else oos.writeObject((Personnel)eachPeople);
            }
            arrayList.clear();
            oos.flush();
            oos.close();
        } catch (Exception e) { e.printStackTrace(); }
    }

    @Override
    public ArrayList<Object> readFile() throws Exception {
        ArrayList<Object> tempPeople = new ArrayList <>();
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(Files.newInputStream(Paths.get(User.getDataUser())));
            Object obj = null;
            while ((obj = ois.readObject()) != null) tempPeople.add(obj);
        } catch (EOFException ex) {} catch (ClassNotFoundException | IOException ex) { ex.printStackTrace(); } finally {
            try {
                if (ois != null) {
                    ois.close();
                }
            } catch (IOException ex) { ex.printStackTrace(); }
        }
        return tempPeople;
    }
}
