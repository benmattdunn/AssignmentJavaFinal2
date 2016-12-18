/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB;

import Sales.SalesObject;
import javax.swing.table.DefaultTableModel;

/**
 * Sales object idenity used for DB communication. 
 * @author User
 */
public class SalesIdentity {
    private SalesObject[] SalesArrayReturn;
    private DefaultTableModel salesTable; 
    
    public SalesIdentity (){}
    
    public SalesIdentity (SalesObject[] salesObjects, DefaultTableModel salesTable)
    {
        this.SalesArrayReturn = salesObjects;
        this.salesTable = salesTable;
    }

    //auto generated getters. 
    public SalesObject[] getSalesArrayReturn() {
        return SalesArrayReturn;
    }

    public DefaultTableModel getSalesTable() {
        return salesTable;
    }
    
    
    
}
