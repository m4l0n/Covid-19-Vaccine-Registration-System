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
import java.io.Serializable;
import static registration.system.Login.isValidPassword;

/**
 *
 * @author nigel
 */
public class User implements Serializable{
    private String name;
    private String phoneNum;
    private String gender;
    private String state;
    private String citizenship;
    private String status;
    private String dob;
    private String userName;
    private String userPass;
    private String userType;
    static final long serialVersionUID = 1L;
    private final String dataUser = "dataUser.txt";
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    public String getName()
    {
        return name;
    }
    
    public void setPhoneNum(String phoneNum)
    {
        this.phoneNum = phoneNum;
    }
    
    public String getPhoneNum()
    {
        return phoneNum;
    }
    
    public void setGender(String gender)
    {
        this.gender = gender;
    }
    
    public String getGender()
    {
        return gender;
    }
    
    public String getState()
    {
        return state;
    }
    
    public void setState(String state)
    {
        this.state = state;
    }

    public void setCitizenship(String citizenship)
    {
        this.citizenship = citizenship;
    }
    
    public String getCitizenship()
    {
        return citizenship;
    }
    
    public void setDate(String dob)
    {
        this.dob = dob;
    }
    
    public String getDate()
    {
        return dob;
    }
    
    public void setUsername(String userName)
    {
        this.userName = userName;
    }
    
    public String getUsername()
    {
        return userName;
    }
    
    public void setPassword(String userPass)
    {
        this.userPass = userPass;
    }
    
    public String getPassword()
    {
        return userPass;
    }
    
    public void setUserType(String userType)
    {
        this.userType = userType;
    }
    
    public String getUserType()
    {
        return userType;
    }
    
    public void setStatus(String status)
    {
        this.status = status;
    }
    
    public String getStatus()
    {
        return status;
    }
    
    public String getDataUser()
    {
        return dataUser;
    }
    
    public String verifyLogin(String userUsername, String userPass)
    {
        //Checks if the password format is valid before validating the credential
        if (isValidPassword(userPass)){
            ObjectInputStream ois = null;
            try {
                ois = new ObjectInputStream(new FileInputStream(dataUser));
                Object obj = null;
                while ((obj = ois.readObject()) != null)
                {
                    if (((User) obj).getUsername().equals(userUsername) && 
                            ((User) obj).getPassword().equals(userPass))
                    {
                        return ((User) obj).getUsername();
                    }
                }
            }
            catch (EOFException ex) {System.out.println("client closed");}
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
        }
        return "Invalid";
    }
    
    public int getUserCount()
    {
        ObjectInputStream ois = null;
        int count = 0;
        try {
            ois = new ObjectInputStream(new FileInputStream(dataUser));
            Object obj = null;
            while ((obj = ois.readObject()) != null)
            {
                if (!((User) obj).getUsername().equals("ADMIN"))
                {
                    count++;
                }
            }
        }
        catch (EOFException ex) {System.out.println("client closed");}
        catch (ClassNotFoundException ex) { ex.printStackTrace(); }
        catch (FileNotFoundException ex) { ex.printStackTrace(); }
        catch (IOException ex) { ex.printStackTrace(); }
        finally {
            try {
                if (ois != null) {
                    ois.close();
                }
            } catch (IOException ex) { ex.printStackTrace(); }
            return count;
        }
    }
}
