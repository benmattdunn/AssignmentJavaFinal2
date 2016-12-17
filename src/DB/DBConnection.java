// Class that contain DB connection variables and all queries
// All ID's are generated inside DB, so never add ID for INSERT statements.
package DB;

import HRPackage.*;
import IO.IO;
import Manufacturers.Manufacturer;
import Products.Product;
import Session.Session;
import java.sql.*;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


// class that store connection variables
public class DBConnection {

    IO errorWriter = new IO();
    Statement stat = null;
    Connection conn = null;
    ResultSet rs = null;
    final String DB_URL
            = "jdbc:mysql://sql.computerstudi.es:3306/gc200325005";
    final String DB_Username = "gc200325005";
    final String DB_Password = "nMWC7cP-";
    
    //general user login information. 
    
    /**
     * Creates a login object and returns a session. 
     * @param userName
     * @param PassWord
     * @return 
     */
    public Session loginRequest (String userName, String PassWord) {
        Session session = new Session ("Something has gone wrong");
        
        
        try {
            conn = DriverManager.getConnection(DB_URL, DB_Username, DB_Password);
            if (conn != null) {
                stat = conn.createStatement();
                rs = stat.executeQuery("SELECT * "
                        + "FROM gc200325005.Login " +
                        "WHERE UserName ='"+userName+"' " +
                        "And PassWord = '"+PassWord +"' " + 
                        "LIMIT 1");
                
                // pull a login session from the DB. Pull and unpack
                if(rs != null) {
                    // create customer object, we need to move it to another location,
                    // not the best place to create customer object

                    DefaultTableModel tableResults = buildTBModel(rs);
                    
                    //System.out.println(tableResults.toString()); Debug
                    System.out.println(tableResults.getValueAt(0, 0).toString());
                    System.out.println(tableResults.getValueAt(0, 1).toString());
                    System.out.println(tableResults.getValueAt(0, 2).toString());
                    System.out.println(tableResults.getValueAt(0, 3).toString());
                    
                    try {
                    session = new Session(
                            Integer.parseInt(tableResults.getValueAt(0, 0).toString()),
                            tableResults.getValueAt(0, 1).toString(),
                            tableResults.getValueAt(0, 2).toString(),
                            Boolean.getBoolean(tableResults.getValueAt(0, 3).toString()));
                    //userLogged = new Customer(rs.getString("login"),rs.getInt("custID"),rs.getBoolean("admin"));
                    //throw new Exception("FUCK");
                    } 
                    catch (Exception e) {
                        session = new Session ("Something has gone wrong with creating the user session!");
                        errorWriter.appendToFile("Something has gone wrong with creating the user session!");
                    }
                    
                                        
                    //newLogin(rs.getInt("custID"));
                } else {
                    session =  new Session("User Account Was not found"); 
                }
                
            }
            
        } catch (Exception e) { //other error messages caused by other values. 
            session = new Session(e.getMessage()); 
        } 
        return session; 
            
    }
    
    /**
     * Queries the database and gets an encapsulated object with a table
     * and array of employees. 
     * @param EmpTableQuery
     * @return 
     */
    public EmployeesIdentity getEmployeeInformation (String EmpTableQuery) {
        DefaultTableModel tempTable; //initialize place holder
                tempTable = querySQLDataBase(EmpTableQuery); //set variable
                System.out.println("EmpTableQuery: "
                        + "\n The Table Size for creating the EMP obects: "
                        +tempTable.getColumnCount());
                buildEmployeeArray(tempTable);
                return new EmployeesIdentity(buildEmployeeArray(tempTable),tempTable );    
    }
    
    
    /***
     * Returns a table based on a query to the database that is constructed
     * from the items in the GUI. 
     * @param query
     * @return 
     */
    public DefaultTableModel querySQLDataBase (String query) {
        try {
            System.out.println(query);
            conn = DriverManager.getConnection(DB_URL, DB_Username, DB_Password);
            if (conn != null) {
                stat = conn.createStatement(); 
                return buildTBModel(rs = stat.executeQuery(query)); 
            }
            } catch(SQLException e) {
                System.out.println("Something has gone wrong with the SQL: " + e.getMessage());
                
            } catch (Exception e) {
                System.out.println("Something has gone wrong with the Query: " + e.getMessage());
            }
        return new DefaultTableModel(); //incase anything goes wrong return
            
    } 
    
    
    
    
    public String turnIntDataFieldsIntoSQLDate (int year, int month, int day) {
                //last chance error validation to prevent rouge inputs into DB, 
        // this code is an absolute LAST restort and hopefully should 
        // never be triggered. 
        String sMonth = "";
        String sDay = ""; 
        if (year < 1900) {
            year += 1900;
        }
        if (year < 0) {
            System.out.println("DATE CONVERSION ERROR ON YEAR!");
            return ""; 
        }
        if (month < 1 || month > 12) {
            System.out.println("DATE CONVERSION ERROR ON MONTH!");
            return ""; 
        }
        if (day < 1 || day > 31) {
            System.out.println("DATE CONVERSION ERROR ON DAY!");
            return ""; 
        }
        if (month < 10 ){
            sMonth = "0"+month;
        } else {
            sMonth = String.valueOf(month);
        }
        if (day < 10 ){
            sDay = "0"+day;
        } else {
            sDay = String.valueOf(day);
        }
        
        System.out.println("CONVERTED DATE: " + ""+year+"-"+sMonth+"-"+sDay);
        
        
        return ""+year+"-"+sMonth+"-"+sDay;
    }
    

    // prepares an insert into the employee table for base values. while no 
    //field is really required. But they are validated by the GUI
    public String prepInsertIntoEmployee (int EmpType, String firstName, 
            String lastName, String Gender, int Sin, String Birthdate, String HireDate,
            String position, String department, String status, String addresss,
            String phonenumber,
            double earnings,
            double payRate,
            double hours,
            double salary,
            double sales,
            double comRate,
            double totalSalary) {
         //INSERT INTO  gc200325005.Employee (EMPType, firstName, lastName, Gender, sin, Birthdate) VALUES (1, 'John', 'Smith', 'Male', 111111111, '11-04-05');
        //String backHalf
        String inserts ="(EMPType, firstName, lastName, Gender, sin, Birthdate, HireDate, position, department, status, address, phone,"
                + "earnings, hours , payrate , salary , sales , commissionrate , totalSalary) VALUES ("+
                EmpType + ",'"+firstName+ "','" + lastName + "','" + Gender + "',"+ Sin+ ",'" + Birthdate + "','" + HireDate  +"','"+position+"','"
                + department + "','" + status +"','"+ addresss + "','"+ phonenumber + "',"+ earnings + ","+ payRate + ","+hours + ","+
                salary + ","+ sales + ","+ comRate + ","+ totalSalary +
                ");"; 
                
                //1, 'John', 'Smith', 'Male', 111111111, '11-04-05');
                return inserts;
                

    }

    //preps the insert statements for the product table.
    public String prepInsertIntoProduct (String name, double price, double productioncost, int manufacturerID, int rating, String description) 
    {
        String inserts = "(name, price, productionCost, manufacturerID, rating, description)"
                + "Values ('" + name + "'," + price + "," + productioncost + "," + manufacturerID + "," + rating + ",'" + description +"');";
      
        return inserts;
    }

    //preps the insert statements for the manufacturer table. 
    public String prepInsertIntoManufacturer (String name, String county, String description) {
        //INSERT INTO gc200325005.Manufacturers (name, country, description) VALUES ('javaCO', 'Canada', 'The Second best Java Programming company');
    String inserts ="(name, country, description) "
            + "Values ('" + name + "','" + county + "','" + description + "');";
    return inserts;
    }
    
    // insert new row to DB
    public void insertSQLDataBase(String TableName, String insertStatementValues) {
        try {
            System.out.println("\n \n \n REACHED INSERT STATEMENT ");
            
            
            conn = DriverManager.getConnection(DB_URL, DB_Username, DB_Password);
            stat = conn.createStatement();
            //INSERT INTO  gc200325005.Employee (EMPType, firstName, lastName, Gender, sin, Birthdate) VALUES (1, 'John', 'Smith', 'Male', 111111111, '11-04-05');
            String insertStatement = "INSERT INTO " + TableName +" "+ insertStatementValues;
            System.out.println("Insert Statement: " + insertStatement);
            stat.execute(insertStatement);
        } catch (SQLException err) {
            System.out.println("ERROR ON INSERT: \n"
                    + "TABLE NAME: " + TableName + "\n" +
                     "STATEMENT: " + insertStatementValues + "\n" +
                     "MESSAGE: " + err.getMessage()
                    
                    );
            errorWriter.appendToFile("ERROR ON INSERT: " +
                     "TABLE NAME: " + TableName +
                     "STATEMENT: " + insertStatementValues +
                     "MESSAGE: " + err.getMessage());
        }
    }

 


    // update row at DB
    public void updateSQLDataBase(String TableName, String columnName, String columnData, String key, int rowID) {
        
        String updateString = "UPDATE " + TableName + " SET " + columnName + " = " + columnData
                +" WHERE " + key + " = " + rowID +";";
        System.out.println(updateString);
        try {
        conn = DriverManager.getConnection(DB_URL, DB_Username, DB_Password);
            stat = conn.createStatement();
            stat.executeUpdate(updateString);
            
        }catch(Exception ex) {
            System.out.println("Update Statement ERR on: " +TableName+ "" );
            errorWriter.appendToFile("Update Statement ERR on: " +TableName);
        }
        
    }


    /**
    * Deletes from the DB the user. 
     * @param TableName
    * @param IDType
    * @param ID
     * @throws SQLException 
     */
    public void deleteSQLDataBase(String TableName, String IDType, int ID) throws SQLException {
                
        System.out.println("\n\n\n\n\n Reached Delete Statement!");
		
               
                conn = DriverManager.getConnection(DB_URL, DB_Username, DB_Password);
                
                /*
                DELETE 
                FROM gc200325005.Employee
                WHERE EMPID = 1;
                */
                
		String deleteStatement = "DELETE FROM "+ TableName+ " WHERE "+IDType+ " = " +ID;
                System.out.println("Delete statement :" + deleteStatement);
		try {
                     stat = conn.createStatement(); 
                     stat.executeUpdate(deleteStatement); 
			
		} catch (SQLException e) {
                    System.out.println("Error on delete:" + e.getMessage());
                    errorWriter.appendToFile("Error on delete:" + e.getMessage());

		} 

	}


    /**
     * turns table into an array of employees
     * @param empTable
     * @return Employee[]
     */
    
    public Employee[] buildEmployeeArray (DefaultTableModel empTable) {
        System.out.println("About to build Data for employees");
         ArrayList<Employee> empTempArryList = new ArrayList<Employee>();
         try {
             for (int k = 0; k<= empTable.getRowCount()-1;k++) {

                 //setup and clear variables
                             String FN = " ";
                             String LN = " ";
                             String Add = " ";
                             String PhnN = " ";
                             String Gen = " ";
                             String Pos = " ";
                             String Dept = " ";
                             int sin = 0;
                             double earns =0;
                             // dates
                             int BY = 0;
                             int BM = 0;
                             int BD = 0;
                             int HY = 0;
                             int HM = 0;
                             int HD = 0;
                             
                             //specifics
                             double payRate = 0.0;
                             double hours = 0.0;
                             double salary = 0.0;
                             double sales = 0.0;
                             double commissionRate = 0.0;
                             double totalSalary = 0.0; 
                             
                             int empID =Integer.parseInt(empTable.getValueAt(k, 0).toString());//want this to throw an error
                            try {
                                FN = preventNull(empTable.getValueAt(k, 2).toString(),0);
                            } catch(Exception e){System.out.println(e.getMessage());};try {
                                LN = preventNull(empTable.getValueAt(k, 3).toString(),0);
                            } catch(Exception e){System.out.println(e.getMessage());};try {
                                Add = preventNull(empTable.getValueAt(k, 12).toString(),0);
                            } catch(Exception e){System.out.println(e.getMessage());};try {
                                PhnN = preventNull(empTable.getValueAt(k, 11).toString(),0);
                            } catch(Exception e){System.out.println(e.getMessage());};try {
                                Gen =  preventNull(empTable.getValueAt(k, 5).toString(),0);
                            } catch(Exception e){System.out.println(e.getMessage());};try {
                            Pos =  preventNull(empTable.getValueAt(k, 8).toString(),0);
                            } catch(Exception e){System.out.println(e.getMessage());};try {
                                Dept =  preventNull(empTable.getValueAt(k, 6).toString(),0);
                            } catch(Exception e){System.out.println(e.getMessage());};try {
                                sin = Integer.parseInt( preventNull(empTable.getValueAt(k, 7).toString(),0));
                            } catch(Exception e){System.out.println(e.getMessage());};try {
                                earns = Double.parseDouble( preventNull(empTable.getValueAt(k, 13).toString(),0));
                            } catch(Exception e){System.out.println(e.getMessage());};
                             // dates
                           
                            try {
                                BY = this.pullDate( preventNull(empTable.getValueAt(k, 8).toString(),1), 1);
                                BM = this.pullDate( preventNull(empTable.getValueAt(k, 8).toString(),1), 2);
                                BD = this.pullDate( preventNull(empTable.getValueAt(k, 8).toString(),1), 3);
                            } catch(Exception e){};
                            try {
                                HY = this.pullDate( preventNull(empTable.getValueAt(k, 9).toString(),1), 1);
                                HM = this.pullDate( preventNull(empTable.getValueAt(k, 9).toString(),1), 2);
                                HD = this.pullDate( preventNull(empTable.getValueAt(k, 9).toString(),1), 3);
                            } catch(Exception e){};
                             System.out.println("Reached here without error");
                            System.out.println("DRAW EMP DATA: " + " FIRSTNAME: "+  FN + " LASTNAME: " + LN + " ADDRESS: " +  Add + " PHONE: "+ PhnN + " GENDER: " + Gen + " DEPT: " + Dept + " SIN: " + sin +" EARNS: "+  earns);
                            
                            //comm rate
                 if (Integer.parseInt(empTable.getValueAt(k, 1).toString()) == 0) {
                    try {sales = Double.parseDouble( preventNull(empTable.getValueAt(k, 13).toString(),17)); } catch(Exception e){};
                    try {commissionRate = Double.parseDouble( preventNull(empTable.getValueAt(k, 13).toString(),18)); } catch(Exception e){};
                        
                    CommissionEmployee tempEmp = new CommissionEmployee(empID, FN,LN,Add,PhnN,Gen,Pos,Dept,sin,earns,BY,BM,BD,HY,HM,HD,commissionRate);
                    tempEmp.toString();
                    tempEmp.setTotalSales(sales); //as it was restricted in the orginal class
                    empTempArryList.add(tempEmp);
                }
                 else if (Integer.parseInt(empTable.getValueAt(k, 1).toString()) == 1) {
                    try {payRate = Double.parseDouble( preventNull(empTable.getValueAt(k, 13).toString(),14)); } catch(Exception e){};
                    try {hours = Double.parseDouble( preventNull(empTable.getValueAt(k, 13).toString(),14)); } catch(Exception e){};     
                    
                    HourlyEmployee tempEmp = new HourlyEmployee(empID, FN,LN,Add,PhnN,Gen,Pos,Dept,sin,earns,BY,BM,BD,HY,HM,HD,payRate);
                    tempEmp.setHoursWorked(hours); //as it was restricted in the orginal class
                    empTempArryList.add(tempEmp);
                 } else if (Integer.parseInt(empTable.getValueAt(k, 1).toString()) == 1) {
                    try {salary = Double.parseDouble( preventNull(empTable.getValueAt(k, 13).toString(),16)); } catch(Exception e){};
                    try {totalSalary = Double.parseDouble( preventNull(empTable.getValueAt(k, 13).toString(),19)); } catch(Exception e){};
                    SalaryEmployee tempEmp = new SalaryEmployee(empID, FN,LN,Add,PhnN,Gen,Pos,Dept,sin,earns,BY,BM,BD,HY,HM,HD,salary);
                    empTempArryList.add(tempEmp);

                 }
             
             }
         }catch (Exception e) {
             System.out.println("Error In employee array creation :" + e.getMessage());
             return null;
         }
         return empTempArryList.toArray(new Employee[empTempArryList.size()]);
         
    }
    /**
     * Converts a date string from SQL databases into a series of ints
     * 1 = year
     * 2 = month
     * 3 = day
     * @param dateString
     * @param MDY
     * @return 
     */
    private int pullDate (String dateString, int MDY) {
        try{
            if (MDY == 1)
            {
                return Integer.parseInt(dateString.substring(0,4));
            }else if (MDY == 2) {
                return Integer.parseInt((dateString).substring(5,7));
            }else if (MDY == 3) {
                return Integer.parseInt((dateString).substring(8,9));
            }else {
                return 0;
            }
        } catch(Exception e) {
            System.out.println("Date conversion error: " + e.getMessage());
        } //YYYY-MM-DD
        return 0; //if a fail happens, however will never reach. 
        
        
    }
    
    private String preventNull (String in, int Op) {
        try {
            System.out.println("Safe Test Value: " + in);
            if (in != null) {
            
                return in;
            } else {
                if(Op == 1) {
                    return "0";
            }
                else {
                    return " ";
                }
            }
        } catch (Exception e) 
        {
            if(Op == 1) {return "0";}
            else {return " ";}
        } //final catch on the conversion
    }
    
    /**
     * Returned values as default table model, JTables need 
     * a different constructor, made a new simpleVectorTable to 
     * correct this problem 
     * @param rs
     * @return
     * @throws SQLException 
     */
    public DefaultTableModel buildTBModel(ResultSet rs) throws SQLException {
        ResultSetMetaData metaData = rs.getMetaData();
        
                Vector<String> columnNames = new Vector<String>();
        // column count
        int columnCount = metaData.getColumnCount();
        System.out.println("Column Count: " + metaData.getColumnCount());
        System.out.println("Reached data Meta Data: "+ metaData.getColumnName(1));

        //System.out.println("Column Count:"+ columnCount);
        //System.out.println("Reached A");
        // loop to build the column names
        
            for (int i = 0; i < columnCount; i++) {
                columnNames.add(metaData.getColumnName(i+1)); //index for table names are 1 greater
                System.out.println(metaData.getColumnName(i+1));
            }

        // create the Vector to hold the data (Vectoe of Vectors)
        Vector<Vector<Object>> tableData = new Vector<Vector<Object>>();

         //System.out.println("Reached B");
        // go throught the resultset
        while (rs.next()) {
            System.out.println("Running Row: ");
            // this will store each row
            Vector<Object> rowVector = new Vector<Object>();
            // loop through the result set and get each object
            try {
            for (int colIndex = 1; colIndex <= columnCount; colIndex++) {
                rowVector.add(rs.getObject(colIndex));
                //System.out.println(rs.getObject(colIndex).toString());
            }
            tableData.add(rowVector);
            } catch (Exception e) {
                errorWriter.appendToFile("Error on DB pull: " + e.getMessage());
            //System.out.println("Hey This is where the fuck up is");
            System.out.println(e.getMessage());
        }
        }

        System.out.println("Final Table Size: " + tableData.size());
        //System.out.println("Reached C");
        // return
        return new DefaultTableModel(tableData, columnNames);
    }


    /**
     * no longer supported login method. 
     * @param login
     * @param password
     * @return 
     */
    public boolean systemLogin(String login, String password) {
        int dbLoopCounter = 0;
        try {
            conn = DriverManager.getConnection(DB_URL, DB_Username, DB_Password);
            if (conn != null) {
                // create the statement object that will manipulate with DB
                stat = conn.createStatement();
                rs = stat.executeQuery("SELECT * FROM Login WHERE login = `" + 
                        login + "` AND password = `" + password + "` LIMIT 1");

                if(rs != null) {
                    // create customer object, we need to move it to another location,
                    // not the best place to create customer object
                    //userLogged = new Customer(rs.getString("login"),rs.getInt("custID"),rs.getBoolean("admin"));
                    //newLogin(rs.getInt("custID"));
                    
                } else {
                        JOptionPane.showMessageDialog(null, "Your Credentials were invalid. Please try again!");
                }
                rs.close();
            }
        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, "Your Credentials were invalid. Please try again!");
            dbLoopCounter++;

            if (dbLoopCounter > 2) {
                JOptionPane.showMessageDialog(null, "Failure to authenticate to database. System will exit.");
                System.exit(0);
            }
        }
        
        return false;
    }
        public ManufacturerIdentity getManufacturerInformation (String manuTableQuery) {
            DefaultTableModel tempTable; //initialize place holder
                tempTable = querySQLDataBase(manuTableQuery); //set variable
                System.out.println("manuTableQuery: "
                        + "\n The Table Size for creating the MANU obects: "
                        +tempTable.getColumnCount());
                buildManufacturerArray(tempTable);
                return new ManufacturerIdentity(buildManufacturerArray(tempTable),tempTable );
    }
     /**
     * Queries the database and gets an encapsulated object with a table
     * and array of employees. 
     * @param prodTableQuery
     * @return 
     */
    public ProductIdentity getProductInformation (String prodTableQuery) {
        DefaultTableModel tempTable; //initialize place holder
                tempTable = querySQLDataBase(prodTableQuery); //set variable
                System.out.println("manuTableQuery: "
                        + "\n The Table Size for creating the MANU objects: "
                        +tempTable.getColumnCount());
                buildProductArray(tempTable);
                return new ProductIdentity(buildProductArray(tempTable),tempTable );    
    }
    
    /* 
     * turns table into an array of employees
     * @param manuTable
     * 
     */
    public Manufacturer[] buildManufacturerArray (DefaultTableModel manuTable) {
    ArrayList<Manufacturer> manufacturers = new ArrayList<Manufacturer>();

    int manuID = 0;
    String manuName;
    String manuCountry;
    String manuDesc;
    try {
        
        for (int i = 0; i<= manuTable.getRowCount()-1;i++)  {
            manuID = Integer.parseInt(manuTable.getValueAt(i, 0).toString());
            manuName = manuTable.getValueAt(i, 1).toString();
            manuCountry = manuTable.getValueAt(i, 2).toString();
            manuDesc = manuTable.getValueAt(i, 3).toString();

        
            Manufacturer manufacturer = new Manufacturer(manuID,manuName,manuCountry,manuDesc);
            manufacturers.add(manufacturer);
        }
       
    } catch (Exception e) {
        System.out.println(e);
    }
    return manufacturers.toArray(new Manufacturer[manufacturers.size()]);
    }
    
    public Product[] buildProductArray (DefaultTableModel prodtable) {
    ArrayList<Product> products = new ArrayList<Product>();

    int prodID = 0;
    String name ="";
    double price = 0.00;
    double productionCost = 0.00;
    int manufacturerID = 0;
    int rating = 0;
    String description = "";
    
    
    try {
        
        for (int i = 0; i<= prodtable.getRowCount()-1;i++)  {
            prodID = Integer.parseInt(prodtable.getValueAt(i, 0).toString());
            name = prodtable.getValueAt(i, 1).toString();
            price = Double.parseDouble(prodtable.getValueAt(i, 2).toString());
            productionCost = Double.parseDouble(prodtable.getValueAt(i, 3).toString());
            manufacturerID = Integer.parseInt(prodtable.getValueAt(i, 4).toString());
            rating = Integer.parseInt(prodtable.getValueAt(i, 5).toString());
            description = prodtable.getValueAt(i, 6).toString();
            
            Product prod = new Product(prodID,name,price,productionCost,manufacturerID,rating,description );
            products.add(prod);
        }
       
    } catch (Exception e) {
        System.out.println(e);
    }
    return products.toArray(new Product[products.size()]);//return the arrayu. 
    }
    

    

}