package Customers;

public class Customer {
    private boolean _admin;
    private String _userName;
    private final int _custID;
    
    // customer constructor
    public Customer(String userName, int custID, boolean admin) {
        this._userName = userName;
        this._admin = admin;
        this._custID = custID;
    }
    
    // accessors
    public String getUserName() {
        return _userName;
    }
    public boolean getAdmin() {
        return _admin;
    }
    public int getCustID() {
        return _custID;
    }
            
    // mutators
    public void setUserName(String userName) {
        this._userName = userName;
    }
    public void setAdmin(boolean admin) {
        this._admin = admin;
    }
}
