/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package system.model;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author nigel
 */
public class Centre implements Serializable {
    private String centreID;
    private String centreName;
    private String centreLocation;
    private List<Vaccine> vaccineList;
    private static final String dataCentre = "dataCenter.txt";

    private static final String dataAdditionalVaccine = "additionalVaccine.txt";
    static final long serialVersionUID = 1L;
    
    public void setCentreID(String centreID)
    {
        this.centreID = centreID;
    }
    
    public String getCentreID()
    {
        return centreID;
    }
    
    public void setCentreName(String centreName)
    {
        this.centreName = centreName;
    }
    
    public String getCentreName()
    {
        return centreName;
    }
    
    public void setCentreLocation(String centreLocation)
    {
        this.centreLocation = centreLocation;
    }
    
    public String getCentreLocation()
    {
        return centreLocation;
    }
    
    public void setVaccine(List<Vaccine> vaccineList)
    {
        this.vaccineList = vaccineList;
    }
    
    public List<Vaccine> getVaccine()
    {
        return vaccineList;
    }
    
    public static String getDataCentre()
    {
        return dataCentre;
    }

    public static String getDataAdditionalVaccine()
    {
        return dataAdditionalVaccine;
    }

}
