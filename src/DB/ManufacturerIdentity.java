/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB;
import Manufacturers.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author User
 */
public class ManufacturerIdentity {
    private Manufacturer[] manuArrayReturn;
    private DefaultTableModel manuTable; 
    
    public ManufacturerIdentity () {
        
    }
    
    public ManufacturerIdentity (Manufacturer[] empList, DefaultTableModel empTable) {
        this.manuArrayReturn = empList;
        this.manuTable = empTable;
        
    }
    
    //auto generated getters 

    public Manufacturer[] getManuArrayReturn() {
        return manuArrayReturn;
    }

    public DefaultTableModel getManuTable() {
        return manuTable;
    }
}