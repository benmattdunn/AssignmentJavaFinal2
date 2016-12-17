package HRPackage;

import HRPackage.PayStyles.PayCommission;


public class CommissionEmployee extends Employee implements PayCommission {

    
    private double _sales, _commissionRate;

    
    public CommissionEmployee() {} 
    
    /**
     * constructor for a new hire employee
     *
     * @param empID
     * @param firstName
     * @param lastName
     * @param address
     * @param phoneNumber
     * @param gender
     * @param position
     * @param department
     * @param sinNumber
     * @param earnings
     * @param yearHire
     * @param monthHire
     * @param dayHire
     * @param yearBirth
     * @param monthBirth
     * @param MonthBirth
     * @param commissionRate
     */
    public CommissionEmployee(int empID, String firstName, String lastName, String address, String phoneNumber,
            String gender, String position, String department, int sinNumber, double earnings,
            int yearHire, int monthHire, int dayHire, int yearBirth, int monthBirth, int MonthBirth,
            double commissionRate) {

        super(empID, firstName, lastName, address, phoneNumber, gender, position, department, sinNumber,
                earnings, yearHire, monthHire, dayHire, yearBirth, monthBirth, MonthBirth);
    }

    /**
     * constructor for a future hire employee
     *
     * @param empID
     * @param firstName
     * @param lastName
     * @param address
     * @param phoneNumber
     * @param gender
     * @param position
     * @param sinNumber
     * @param yearBirth
     * @param monthBirth
     * @param dayBirth
     * @param commissionRate
     */
    public CommissionEmployee(int empID, String firstName, String lastName, String address, String phoneNumber,
            String gender, String position, int sinNumber, int yearBirth, int monthBirth, int dayBirth,
            double commissionRate) {

        super(empID, firstName, lastName, address, phoneNumber, gender, position, sinNumber, yearBirth, monthBirth, dayBirth);
        this._commissionRate = commissionRate;
    }

    @Override
    public double calculatePay() {
        this.setEarnings(this._sales * this._commissionRate + this.getEarnings());
        return this._sales * this._commissionRate;

    }

    @Override
    public double getSalesRatePay() {
        return this._sales * this._commissionRate;
    }

    @Override
    public void setTotalSales(double totalSales) {
        this._sales = totalSales;
    }

    @Override
    public void setCommissionRate(double percent) {
        this._commissionRate = percent;
    }

    @Override
    public double getTotalSales() {
        return this._sales;
    }

    @Override
    public double getCommissionRate() {
        return this._commissionRate;
    }

    @Override
    public String toString() {
        return "Commission Employee: \n" + super.toString();
    }

}
