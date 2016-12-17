package HRPackage;

import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Abstract version of the employee class;
 */
public abstract class Employee {

    // variables declaired on boot (super class variables)
    private String _firstName, _lastName, _position, _gender, _department;
    private int _empId;
    private int _sinNumber;

    private GregorianCalendar _dateOfBirth, _dateOfHire;

    // variables not needed for boot (super class variables)
    private String _status, _phoneNumber, _address;

    private double _earnings;

    //blank place holder constructor. 
   
    public Employee () {
        
    }
    
    /**
     * constructor for a hired employee.
     *
     * @param empId
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
     */
    public Employee(int empId, String firstName, String lastName, String address,
            String phoneNumber, String gender, String position, String department,
            int sinNumber,
            double earnings,
            int yearHire, int monthHire, int dayHire,
            int yearBirth, int monthBirth, int MonthBirth
    ) {

        this(empId, firstName, lastName, address, phoneNumber, gender,
                position, sinNumber,
                yearBirth, monthBirth, MonthBirth
        );

        this._department = department;
        this._dateOfHire = new GregorianCalendar(yearBirth, monthBirth - 1, MonthBirth);
        this._earnings = earnings;
        //will become legecy
    }

    /**
     * Creates the abstract class for employees that the subclass children, used
     * for creating a employee object has not been hired. reference;
     *
     * @param empId
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
     */
    public Employee(int empId, String firstName, String lastName, String address,
            String phoneNumber, String gender, String position,
            int sinNumber,
            int yearBirth, int monthBirth, int dayBirth) {
        this._empId = empId;
        this._firstName = firstName;
        this._lastName = lastName;
        this._gender = gender;
        this._phoneNumber = phoneNumber;
        this._address = address;
        this._sinNumber = sinNumber;
        this._dateOfBirth = new GregorianCalendar(yearBirth, monthBirth, dayBirth);
    }

    /**
     * returns the employees first name
     *
     * @return empFirstName
     */
    public String getFirstName() {
        return this._firstName;
    }

    /**
     * returns the employees last name
     *
     * @return empLastName
     */
    public String getLastName() {
        return this._lastName;
    }

    public String getFullName() {
        return this._firstName + " " + this._lastName;
    }

    /**
     * gets the gender of the employee
     *
     * @return gender string
     */
    public String getGender() {
        return this._gender;
    }

    /**
     * gets the position of the employee as a string.
     *
     * @return String representing the position of the employee
     */
    public String getPosition() {
        return this._position;
    }

    /**
     * returns the status of the employee
     *
     * @return status of the employee
     */
    public String getStatus() {
        return this._status;
    }

    /**
     * gets the employees phone number
     *
     * @return a string of the employees phone number
     */
    public String getPhoneNumber() {
        return this._phoneNumber;
    }

    /**
     * gets the string representing the address of the employee.
     *
     * @return string representing the address of the employee.
     */
    public String getAddress() {
        return this._address;
    }

    /**
     * gets the sin number as an int
     *
     * @return sin Number as int
     */
    public int getSinNumber() {
        return this._sinNumber;
    }

    /**
     * gets the employees ID as a int.
     *
     * @return int the employees Id
     */
    public int getEmpId() {
        return this._empId;
    }

    /**
     * gets the earnings of the employee as a double
     *
     * @return earnings of the employee as double
     */
    public double getEarnings() {
        return this._earnings;
    }

    /**
     * sets the date of hire off a GregorianCalendar object.
     *
     * @param _dateOfHire
     */
    public void setDateOfHire(GregorianCalendar _dateOfHire) {
        this._dateOfHire = _dateOfHire;
    }

    /**
     * sets the date of hire off 3 ints, for year/month/day
     *
     * @param year
     * @param month
     * @param day
     */
    public void setDateOfHire(int year, int month, int day) {
        this._dateOfHire = new GregorianCalendar(year, month, day);
    }

    public void setEarnings(double _earnings) {
        this._earnings = _earnings;
    }

    /**
     * gets the date of birth of the employee as a Date object.
     *
     * @return Date Object
     */
    public GregorianCalendar getDateOfBirth() {
        return this._dateOfBirth;
    }

    /**
     * gets the employees date of hire
     *
     * @return a date object of the date of hire.
     */
    public GregorianCalendar getDateOfHire() {
        return this._dateOfHire;
    }

    /**
     * place holder method for simple information of the employee type in a
     * simplified fashion; Have not used in however in any scope yet.
     *
     * @return "": an empty string.
     */
    public String returnMyTypeAsString() {
        return "";
    }

    // mutator methods;
    /**
     * sets emps first name.
     *
     * @param _firstName
     */
    public void setFirstName(String _firstName) {
        this._firstName = _firstName;
    }

    /**
     * sets the emps last name.
     *
     * @param _lastName
     */
    public void setLastName(String _lastName) {
        this._lastName = _lastName;
    }

    /**
     * sets the position of the employee
     *
     * @param _position
     */
    public void setPosition(String _position) {
        this._position = _position;
    }

    /**
     * sets the department of the employee
     *
     * @param _department
     */
    public void setDepartment(String _department) {
        this._department = _department;
    }

    /**
     * gets the gender of the of the employee
     *
     * @param _gender
     */
    public void setGender(String _gender) {
        this._gender = _gender;
    }

    public void setSinNumber(int _sinNumber) {
        this._sinNumber = _sinNumber;
    }

    /**
     * sets the status of the employee
     *
     * @param _status
     */
    public void setStatus(String _status) {
        this._status = _status;
    }

    /**
     * sets the phone number of the the employee
     *
     * @param _phoneNumber
     */
    public void setPhoneNumber(String _phoneNumber) {
        this._phoneNumber = _phoneNumber;
    }

    /**
     * sets the address string of the employee.
     *
     * @param _address
     */
    public void setAddress(String _address) {
        this._address = _address;
    }

    /**
     * holder for override void input
     *
     * @return no real value
     */
    public abstract double calculatePay();

    /**
     * returns the first name, last name, and the date of hire of the employee
     *
     * @return
     */
    @Override
    public String toString() {

        /* add the following info: 
            age
            position
            date of hire
            empID
         */
        return ("Name: \t\t" + this.getFullName() + "\n"
                + "Position: \t \t" + this.getEmpId() + "\n"
                + "Department" + "\t \t" + "\n"
                + "Employee ID: \t \t " + this.getEmpId() + "\n"
                + "Gender: \t \t " + this.getGender() + "\n"
                + "Address: \t \t " + this.getAddress() + "\n"
                + "Phone Number: \t \t " + this.getPhoneNumber() + "\n"); //
    }
    
    //utility Static methods
    
    
    /**
     * universal utility methods, converts dates from the 
     */
    public static boolean ConvertEmpDates () {
        return true; 
    }

}
