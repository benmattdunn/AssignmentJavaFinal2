package HRPackage.PayStyles;

public interface PayCommission {
    //returns the pay out this period without actually paying out
    public double getSalesRatePay();
    
    
    public void setTotalSales(double totalSales);
    public void setCommissionRate(double percent);
    
    public double getTotalSales();
    public double getCommissionRate();
}
