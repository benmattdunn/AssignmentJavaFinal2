package HRPackage;

import HRPackage.PayStyles.PaySalary;

public class SalaryEmployee extends Employee implements PaySalary {

    private double _salary;

    public SalaryEmployee() {
        
    }
    
    /**
     * constructor for a salary hired employee.
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
     * @param salary
     */
    public SalaryEmployee(int empID, String firstName, String lastName, String address, String phoneNumber,
            String gender, String position, String department, int sinNumber,
            double earnings, int yearHire, int monthHire, int dayHire, int yearBirth, int monthBirth, int MonthBirth,
            double salary) {
        super(empID, firstName, lastName, address, phoneNumber, gender, position, department, sinNumber,
                earnings, yearHire, monthHire, dayHire, yearBirth, monthBirth, MonthBirth);
        this._salary = salary;
    }

    /**
     * constructor for a future hire salary employee
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
     * @param salary
     */
    public SalaryEmployee(int empID, String firstName, String lastName, String address, String phoneNumber,
            String gender, String position, int sinNumber, int yearBirth, int monthBirth, int dayBirth,
            double salary) {
        super(empID, firstName, lastName, address, phoneNumber, gender, position, sinNumber, yearBirth, monthBirth, dayBirth);
        this._salary = salary;

    }

    /**
     * sets and updates the earnings of the employee
     *
     * @return double for pay this month
     */
    @Override
    public double calculatePay() {
        this.setEarnings(this.getEarnings() + this._salary / 12);
        return this._salary / 12;
    }

    /**
     * shows the earnings for this period
     *
     * @return
     */
    @Override
    public double getSaleryThisPeriod() {
        return this._salary / 12;
    }

    @Override
    public double getSalery() {
        return this._salary;
    }

    @Override
    public void setSalery(double salery) {
        this._salary = salery;
    }

    @Override
    public String toString() {
        return "Salary Employee: \n" + super.toString();
    }

}
