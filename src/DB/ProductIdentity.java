/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB;

import Products.Product;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author User
 */
public class ProductIdentity {
    private Product[] prodArrayReturn;
    private DefaultTableModel prodTable; 
    
    public ProductIdentity () {
        
    }
    
    public ProductIdentity (Product[] empList, DefaultTableModel empTable) {
        this.prodArrayReturn = empList;
        this.prodTable = empTable;
        
    }
    
    //auto generated code

    public Product[] getProdArrayReturn() {
        return prodArrayReturn;
    }

    public DefaultTableModel getProdTable() {
        return prodTable;
    }
    
}