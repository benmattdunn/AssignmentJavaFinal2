package Sales;

import Products.*;
import HRPackage.*;

public class Sales {
    private final int _saleID, _empID, _productID;
    private double _saleCommission;
    
    // customer constructor
    public Sales(int saleID, int empID, int productID, double saleCommission) {
        this._saleID = saleID;
        this._empID = empID;
        this._productID = productID;
        this._saleCommission = saleCommission;
    }
    
    // accessors
    public int getSaleID() {
        return _saleID;
    }
    public int getEmpID() {
        return _empID;
    }
    public int getProductID() {
        return _productID;
    }
            
    // mutators
    public void setSaleCommission(double saleCommission) {
        this._saleCommission = saleCommission;
    }  
}
