/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB;
import HRPackage.*; 
import javax.swing.table.DefaultTableModel;

/**
 * Return identity object for the creation of employee information, but 
 * returning a table and a (hidden) array of employees the information
 * of the DB can be mostly hidden from the user session until an update
 * has been made. 
 * @author ben Dunn
 */
public class EmployeesIdentity {
    private Employee[] empArrayReturn;
    private DefaultTableModel empTable; 
    
    public EmployeesIdentity () {
        
    }
    
    public EmployeesIdentity (Employee[] empList, DefaultTableModel empTable) {
        this.empArrayReturn = empList;
        this.empTable = empTable;
        
    }
    
    //auto generated getters 

    public Employee[] getEmpArrayReturn() {
        return empArrayReturn;
    }

    public DefaultTableModel getEmpTable() {
        return empTable;
    }
    
    
}
