/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sales;

/**
 * Sale object. sales are non relational, Products, manufacturers and sales people
 * may be 
 * @author BenDunn
 */
public class SalesObject {

    private int EMPID, PRODID;
    private String SalesPerson, productName;
    private double SalesValue; 

    
    public SalesObject (int EMPID, String SalesPersonFullName, int ProdID, String ProductName, Double SalesValue) {
        this.EMPID = EMPID;
        this.SalesPerson = SalesPersonFullName;
        this.PRODID = ProdID;
        this.productName = ProductName;
        this.SalesValue = SalesValue;
        
    }

    //auto generated code. 
    public int getEMPID() {
        return EMPID;
    }

    public void setEMPID(int EMPID) {
        this.EMPID = EMPID;
    }

    public int getPRODID() {
        return PRODID;
    }

    public void setPRODID(int PRODID) {
        this.PRODID = PRODID;
    }

    public String getSalesPerson() {
        return SalesPerson;
    }

    public void setSalesPerson(String SalesPerson) {
        this.SalesPerson = SalesPerson;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getSalesValue() {
        return SalesValue;
    }

    public void setSalesValue(double SalesValue) {
        this.SalesValue = SalesValue;
    }
    
    
}
