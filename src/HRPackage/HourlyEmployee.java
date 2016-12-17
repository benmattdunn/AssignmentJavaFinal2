package HRPackage;

import HRPackage.PayStyles.PayHourly;

public class HourlyEmployee extends Employee implements PayHourly {

    private double _hoursWorkedThisPeriod, _totalHoursWorked, _payRate;

    /**
     * Blank place holder constructor. 
     */
    public HourlyEmployee() {
        
    }
    
    /**
     * setup method for an hourly employee that has been hired.
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
     * @param payRate
     */
    public HourlyEmployee(int empID, String firstName, String lastName, String address, String phoneNumber,
            String gender, String position, String department, int sinNumber, double earnings,
            int yearHire, int monthHire, int dayHire, int yearBirth, int monthBirth, int MonthBirth,
            double payRate) {

        super(empID, firstName, lastName, address, phoneNumber, gender, position, department, sinNumber, earnings, yearHire, monthHire, dayHire, yearBirth, monthBirth, MonthBirth);
        this._payRate = payRate;
    }

    /**
     * Set up file for a hourly employee object that has yet to be hired.
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
     * @param payRate
     */
    public HourlyEmployee(int empID, String firstName, String lastName, String address, String phoneNumber,
            String gender, String position, int sinNumber, int yearBirth, int monthBirth, int dayBirth,
            double payRate) {
        super(empID, firstName, lastName, address, phoneNumber, gender, position, sinNumber, yearBirth, monthBirth, dayBirth);
        this._payRate = payRate;
    }

    /**
     * returns the pay of the employee and moves the values into total earnings
     * and resets the field variables back to 0. After using this method a user
     * should be aware hours and values will be reset.
     *
     * @return
     */
    @Override
    public double calculatePay() {
        this.setEarnings(this.getEarnings() + this._hoursWorkedThisPeriod * this._totalHoursWorked);
        return this._hoursWorkedThisPeriod * this._totalHoursWorked;
    }

    /**
     * returns the total pay due this period, but does not save it into
     * earnings.
     *
     * @param HoursWorkedPeriod
     * @param PayRate
     * @return double pay for this period.
     */
    @Override
    public double getHourlyPay() {
        return this._hoursWorkedThisPeriod * this._totalHoursWorked;
    }

    //accessors
    @Override
    public double getTotalHoursWorked() {
        return this._hoursWorkedThisPeriod;
    }

    @Override
    public double getPayRate() {
        return this._payRate;
    }

    //mutators
    @Override
    public void setPayRate(double payRate) {
        this._payRate = payRate;
    }

    @Override
    public void setHoursWorked(double hoursWorked) {
        this._hoursWorkedThisPeriod = hoursWorked;
    }

    @Override
    public String toString() {
        return "Hourly Employee: \n" + super.toString();
    }

}
