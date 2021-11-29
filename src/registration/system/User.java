/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package registration.system;

import java.io.Serializable;

/**
 *
 * @author nigel
 */
public class User implements Serializable{
    private String name;
    private int phoneNum;
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
    
    public void setPhoneNum(int phoneNum)
    {
        this.phoneNum = phoneNum;
    }
    
    public int getPhoneNum()
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
}
