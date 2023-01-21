/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package system.model;

import java.io.Serializable;

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
    
    public String getPersonnelID()
    {
        return personnelID;
    }
}
