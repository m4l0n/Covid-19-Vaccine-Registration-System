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
public class Centre implements Serializable{
    private int centreID;
    private String centreName;
    private String centreLocation;
    private Vaccine vaccine;
    
    public void setCentreID()
    {
        this.centreID = centreID;
    }
    
    public int getCentreID()
    {
        return centreID;
    }
    
    public void setCentreName()
    {
        this.centreName = centreName;
    }
    
    public String getCentreName()
    {
        return centreName;
    }
    
    public void setCentreLocation()
    {
        this.centreLocation = centreLocation;
    }
    
    public String getCentreLocation()
    {
        return centreLocation;
    }
    
    public void setVaccine()
    {
        this.vaccine = vaccine;
    }
    
    public Vaccine vaccine()
    {
        return vaccine;
    }
}
