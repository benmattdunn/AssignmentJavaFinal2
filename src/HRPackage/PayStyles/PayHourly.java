package HRPackage.PayStyles;

public interface PayHourly {
    public double getTotalHoursWorked(); 
    public double getPayRate(); 
    public double getHourlyPay();
    
    public void setPayRate(double payRate);
    public void setHoursWorked(double hoursWorked);    
}
