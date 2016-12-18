package GUI;

import DB.DBConnection;
import DB.*;
import Manufacturers.*;
import Products.*;
import HRPackage.Employee;
import IO.IO;
import Session.Session;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel; 

public class GUI extends JFrame {

    IO errorWriter = new IO();
    // panels
    private JPanel greetingPanel, mainPanel = new JPanel(), employeeTab,
            employeePanel, employeeSearchPanel, hourlyEmpTab, salaryEmpTab,
            commissionEmpTab, productTab, productPanel, manufacturerPanel,
            productSearchPanel, exitPanel,salesTab,salesPanel,sales,salesSearchSubPanel,
            SalesResultsPanel;
    // tabs
    private JTabbedPane mainTabs, employeeTypeTab;

    // main labels
    private JLabel helloLbl;

    // labels for employee tab
    private JLabel firstNameLbl, 
            lastNameLbl, 
            addressLbl, 
            phoneNumberLbl,
            yearLbl, 
            monthLbl, 
            dayLbl, 
            weeklySalaryLbl, 
            hoursLbl, 
            hourRateLbl,
            salesLbl, 
            commissionRateLbl, 
            baseSalaryLbl, 
    //added new generic labels for employee creation
            emphireDateYearlbl,
            empHireDateMonthlbl,
            empHireDateDaylbl,
            empSinlbl,
            empPositionlbl,
            empGenderlbl,
            empDepartmentlbl,
            empStatuslbl;

            

    // text boxes for employee tab
    private JTextField 
            firstNameTxt = new JTextField(15),
            lastNameTxt = new JTextField(15),
            addressTxt = new JTextField(15),
            phoneNumberTxt = new JTextField(15),
            yearTxt = new JTextField(15),
            monthTxt = new JTextField(15),
            dayTxt = new JTextField(15),
            weeklySalaryTxt = new JTextField(15),
            hoursTxt = new JTextField(15),
            hourRateTxt = new JTextField(15),
            salesTxt = new JTextField(15),
            commissionRateTxt = new JTextField(15),
            baseSalaryTxt = new JTextField(15),
            //new submission fields added for employee creation
            
            emphireDateYearTxt = new JTextField(15),
            empHireDateMonthTxt = new JTextField(15),
            empHireDateDayTxt = new JTextField(15),
            empSinTxt = new JTextField(15),
            empPositionTxt = new JTextField(15),
            empGenderTxt = new JTextField(15),
            empDepartmentTxt = new JTextField(15),
            empStatusTxt = new JTextField(15);
    //holder for creating searching employees 
    private JPanel ProductManufacturerCreateSearchMainPanel = new JPanel(); 
    
    
    //area for creating product and manufacturer. 
    private JPanel 
            CreateProductsmanufacturerMainPanel = new JPanel(),
                manufactureCreatePanelMain = new JPanel(),
                    manufactureCreatePanelUp = new JPanel(),
                    manufactureCreatePanelDown = new JPanel(),

                productCreatePanelMain = new JPanel(),
                    productCreatePanelUp = new JPanel(),
                    productCreatePanelDown = new JPanel();

    //manufacturers create
    private JLabel
            manuCreateNamelbl = new JLabel("Name: "),
            manuCreateCountrylbl = new JLabel("Country: "),
            manuCreateDescriptionlbl = new JLabel("Description: ");
    
    private JTextField
            manuCreateNametxt = new JTextField(15),
            manuCreateCountrytxt = new JTextField(15),
            manuCreateDescriptiontxt = new JTextField(15);
    private JButton manuCreateButton = new JButton("Submit");
    
    //products create
    private JLabel 
            prodCreateNamelbl = new JLabel("Name:"),
            prodCreatePriceNamelbl = new JLabel("Price:"),
            prodCreateProductionCostlbl = new JLabel("Production Cost:"),
            prodCreateManufacturerlbl = new JLabel("Manufactuter:"),
            prodCreateRatinglbl = new JLabel("Rating:"),
            prodCreateDescriptionlbl = new JLabel("Description: ");
    private JTextField 
            prodCreateNameTxt = new JTextField(15),
            prodCreatePriceTxt = new JTextField(15),
            prodCreateProductionCostTxt = new JTextField(15),
            //prodCreateManufacturerCostTxt = new JTextField(15),
            prodCreateRatingTxt = new JTextField(15),
            prodCreateDescriptionTxt = new JTextField(15);
    
    private JComboBox manuComboBox = new JComboBox();
    private int manuSelectedID; 
    
    private JButton prodCreateButton = new JButton("Submit");
            
    
    private JPanel 
            sProductSearchMainPanel = new JPanel(),
                sProductSearchLeftPanel = new JPanel(),
                    sProductSearchLeftUpPanel = new JPanel(),
                    sProductSearchLeftDownPanel = new JPanel(),
                sProductSearchRightPanel = new JPanel(),
                    sProductSearchRightUpPanel = new JPanel(),
                    sProductSearchRightDownPanel = new JPanel();
    
    //left Panel Input
    
    private JLabel 
            slProductSearchIDlbl = new JLabel ("ID"),
            slProductSearchNamelbl = new JLabel ("Name"),
            slProductSearchPricelbl = new JLabel ("Price"),
            slProductSearchProductionCostlbl = new JLabel ("Production Cost"),
            slProductSearchRatinglbl = new JLabel ("Rating"),
            slProductSearchDescriptionlbl = new JLabel ("Description");
    
    private JCheckBox
            slProductSearchIDCheckBox = new JCheckBox(),
            slProductSearchNameCheckBox = new JCheckBox(),
            slProductSearchPriceCheckBox = new JCheckBox(),
            slProductSearchProductionCostCheckBox = new JCheckBox(),
            slProductSearchRatingCheckBox = new JCheckBox(),
            slProductSearchDescriptionCheckBox = new JCheckBox();
    
    private JTextField 
            slProductSearchIDTxt = new JTextField(15),
            slProductSearchNameTxt = new JTextField(15),
            slProductSearchPriceTxt = new JTextField(15),
            slProductSearchProductionCostTxt = new JTextField(15),
            slProductSearchRatingTxt = new JTextField(15),
            slProductSearchDescriptionTxt = new JTextField(15);
    
    private JButton srProductSearchButton = new JButton("Search");
    //Right Sidte
    private JTable productSearchResults;
    private JScrollPane productSearchResultsScrollpane;
    
    private SearchProductButtonListener searchProductListener = new SearchProductButtonListener();
    
    private JButton 
            sRProductEditButton = new JButton("Edit"),
            sRProductDeleteButton = new JButton("Delete");
    
    //Build the Manufacturer Search Panel
    
    
    private JPanel
            sManuSearchMainPanel = new JPanel(),
                sManutSearchLeftPanel = new JPanel(),
                    sManuSearchLeftUpPanel = new JPanel(),
                    sManuSearchLeftDownPanel = new JPanel(),
                sManuSearchRightPanel = new JPanel(),
                    sManuSearchRightUpPanel = new JPanel(),
                    sManuSearchRightDownPanel = new JPanel();
    
    private JLabel
            slManuSearchIDlbl = new JLabel ("ID"),
            slManuSearchNamelbl = new JLabel ("Name"),
            slManuSearchCountrylbl = new JLabel ("Country"),
            slManuSearchDescriptionlbl = new JLabel ("Description");
    
    private JCheckBox
            slManuSearchIDCheckBox = new JCheckBox(),
            slManuSearchNameCheckBox = new JCheckBox(),
            slManuSearchCountryCheckBox = new JCheckBox(),
            slManuSearchDescriptionCheckBox = new JCheckBox();
    
    private JTextField 
            slManuSearchIDTxt = new JTextField(15),
            slManuSearchNameTxt = new JTextField(15),
            slManuSearchCountryTxt = new JTextField(15),
            slManuSearchDescriptionTxt = new JTextField(15);
    
    private JButton
            slManuSearchButton = new JButton ("Search");
    private SearchManuButtonListener searchManuButtonListener = new SearchManuButtonListener(); 
    
    //right side
    private JTable manuSearchResults;
    private JScrollPane manuSearchResultsScrollpane;
    
    private JButton 
            sRManuEditButton = new JButton("Edit"),
            sRPmanuDeleteButton = new JButton("Delete");
    


    // buttons
    private JButton createHourlyEmployeeBtn, createSalaryEmployeeBtn,
            createCommEmployeeBtn, searchEmployeeBtn,
            exitBtn, addSalesBtn;
    
    
    //search area panel, removed into sub section for ease of coding.
    
    //required check boxes for building search criteria, Ben Dunn. 
    // <editor-fold>
    private JCheckBox 
            searchEmpIDSelectedCheckBox = new JCheckBox(),
            searchEmpTypeSelectedCheckBo = new JCheckBox(),
            searchFirstNameSelectedCheckBox = new JCheckBox(),
            searchLastNameSelectedCheckBox = new JCheckBox(), 
            searchPositionSelectedCheckBox = new JCheckBox(),
            searchGenderSelectedCheckBox = new JCheckBox(),
            
            searchDepartmentSelectedCheckBox = new JCheckBox(), 
            searchSinSelectedCheckBox = new JCheckBox(), 
            searchBirthdaySelectedCheckBox = new JCheckBox(), 
            searchHiredateSelectedCheckBox = new JCheckBox(),
            searchStatusSelectedCheckBox = new JCheckBox(),
            searchPhoneSelectedCheckBox = new JCheckBox(),
            searchAddressSelectedCheckBox = new JCheckBox();
    
    //Labels
    private JLabel 
            searchEmpIDlbl = new JLabel("Employee ID: "), 
            searchEmpTypelbl = new JLabel("Employee Type: "), 
            searchFirstNamelbl = new JLabel("First Name: "),
            searchLastNamelbl = new JLabel("Last Name: "),
            searchPositionlbl= new JLabel("Position: "),
            searchGenderlbl = new JLabel("Gender: "),
            searchDepartmentlbl = new JLabel("Department: "), 
            searchSinlbl = new JLabel("Sin Number: "),
            searchBirthdaylbl = new JLabel("Birthday: "),
            searchHiredatelbl = new JLabel("Hire Date: "),
            searchStatuslbl = new JLabel("Status: "),
            searchPhonelbl = new JLabel("Phone Number: "),
            searchAddresslbl = new JLabel("Address: ");
    
    //Search areas based on user input
    private JTextArea 
            searchEmpIDTextBox = new JTextArea(1, 20),//emptype is dropdown
            searchFirstNameTextBox = new JTextArea(1, 20), 
            searchLastNameTextBox= new JTextArea(1, 20), 
            searchPositionTextBox= new JTextArea(1, 20),
            searchGenderTextBox= new JTextArea(1, 20), 
            searchDepartmentTextBox= new JTextArea(1, 20), 
            searchSinTextBox= new JTextArea(1, 20),
            searchbirthDayTextBox= new JTextArea(1, 20), 
            searchHireDateTextBox= new JTextArea(1, 20), 
            searchStatusTextBox= new JTextArea(1, 20),
            searchPhoneTextBox= new JTextArea(1, 20), 
            searchAddressTextBox= new JTextArea(1, 20),
    
            //text fields for editing an employee. 
            searchEDITEmpIDTextBox = new JTextArea(1, 20),//emptype is dropdown
            searchEDITFirstNameTextBox = new JTextArea(1, 20), 
            searchEDITLastNameTextBox= new JTextArea(1, 20), 
            searchEDITPositionTextBox= new JTextArea(1, 20),
            searchEDITGenderTextBox= new JTextArea(1, 20), 
            searchEDITDepartmentTextBox= new JTextArea(1, 20), 
            searchEDITSinTextBox= new JTextArea(1, 20),
            searchEDITbirthDayTextBox= new JTextArea(1, 20), 
            searchEDITHireDateTextBox= new JTextArea(1, 20), 
            searchEDITStatusTextBox= new JTextArea(1, 20),
            searchEDITPhoneTextBox= new JTextArea(1, 20), 
            searchEDITAddressTextBox= new JTextArea(1, 20);
    private JPanel 
            searchEditUpperPanel = new JPanel(), 
            searchEditLowerPanel = new JPanel(), 
            searchEditMainMain = new JPanel();
    
    
    
    private JButton
            searchEmpEditButton = new JButton("Edit"),
            searchEmpDeleteButton = new JButton("Delete"),
            searchEmpSubmitEditButton = new JButton("Submit Changes");
            
    
    // </editor-fold>
    
    //table layout for view, is overriding hte cell editable to
    //prevent a user from changing data from the table model
    JTable searchResultsTable = new JTable() {
        private static final long serialVersionUID = 1L;

        public boolean isCellEditable(int row, int column) {                
                return false;               
        };
    };;
    JTable saleResultsTable = new JTable() {
        private static final long serialVersionUID = 1L;

        public boolean isCellEditable(int row, int column) {                
                return false;               
        };
    };
    
    private ListSelectionModel searchEmployeeListSelectionModel;
    private ListSelectionModel searchProductListSelectionModel;
    private Employee[] employeeStorage;
    private Employee selectedEmployee; 
    JScrollPane searchResultsScrollTable; //accessor. 
    
    JScrollPane salesResultsScrollTable;
    DefaultTableModel searchResultsDefaultTableModel; 
    
    SalesIdentity salesIdenity; 
    DefaultTableModel saleResultsDefaultTableModel;
    
    
    private ListSelectionModel manuListSelectionModel;
    private ManufacturerIdentity manuIdenity; 
    private Manufacturer[] manuStorage;
    private int ManuSelectedInt = 0;
    private Manufacturer manuSelected;
    private DefaultTableModel manuTblModel;
    
    private ListSelectionModel prodListSelectionModel;
    private ProductIdentity prodIdentity;
    private Product[] prodStorage;
    private int prodSelectedInt = 0;
    private Product prodSelected;
    private DefaultTableModel prodTblModel;
    
    //JPanel panel = new JPanel();
    
    //layouts for search
    private JPanel 
            searchMainPanel, //divided in half 
            searchResultsPanel, //right side
            searchCriteriaPanel, //upper lower divided in half (no center)
            searchCriteriaButtonsPanel, //holds the search button for a gridlayout. 
            searchCriteriaEntryPanel; //holds the areas. 
    
    
    //created by reference to classes. 
    private JComboBox searchEmpTypeComboBox = new JComboBox (new String[] 
    {"Commission Employee",
    "Hourly Employee",
    "Salary Employee"} );

    
    
    private int empCreateGetIndexofTabPane;
    
    //edit manufactuer panel popup setup
    private JPanel
            editManufacturerPanelMain = new JPanel(),
                editManufacturerPanelup = new JPanel(),
                editManufacturerPaneldown= new JPanel();
    
    private JButton editManuButton = new JButton("Submit to Database");
    
    private JLabel 
            editManuNamelbl = new JLabel("Name: "),
            editManuCountrylbl = new JLabel("Country: "),
            editManuDescriptionlbl = new JLabel("Description: ");
    
    private JTextField 
            editManuNameTxt = new JTextField(15),
            editManuCountryTxt = new JTextField(15),
            editManuDescriptioneTxt = new JTextField(15);
    
    //edit product information setup, 
    private JPanel
            editProductPaneMain = new JPanel(),
                editProductPaneup = new JPanel(),
                editProductPaneDown = new JPanel();
    
    private JLabel //chaning manufacturers makes no sense. 
            editProductNamelbl = new JLabel("Name: "),
            editProductPricelbl = new JLabel("Price: "),
            editProductionCostlbl = new JLabel("Production Cost: "),
            editProductRatinglbl = new JLabel("Rating: "),
            editProductDescriptionlbl = new JLabel("Name: ");
    
    private JTextField
            editProductNametxt = new JTextField(),
            editProductPricetxt = new JTextField(),
            editProductionCosttxt = new JTextField(),
            editProductRatingtxt = new JTextField(),
            editProductDescriptiontxt = new JTextField();
    
    private JButton editProductSubmitButton = new JButton("Submit");
    
    //
    private JComboBox salesProductList = new JComboBox ();
    private JComboBox salesEmp = new JComboBox ();
    
    private JScrollPane SalesResultsScrollPane;
    
    private Session thisSession;

                    
    
    //buttons
    private JButton searchEmployees; //activates the search for the database. 
    //SimpleVectorTable SearchResultsVectors = new SimpleVectorTable(); //results holder object
    //listeners
    SearchEmployeeButtonListener searchEmployeeButtonListener;
    
    //end of ben search initalization 

    // form font
    private Font mainFont = new Font("Verdana", Font.PLAIN, 14);

    // form border
    private Border mainBorder = BorderFactory.createEtchedBorder(EtchedBorder.RAISED);

    // interface constructor
    public GUI(Session session) {
       

        // set title layout and 
        super("Assignment 4 CRUD FOR FUN.");
         thisSession = session;
        setLayout(new FlowLayout());
        PullSalesValues(); 
        
        // create tabs
        mainTabs = new JTabbedPane();

        // create main panels
        this.buildGreetingsPanel("Welcome to HR/PROdUCT/MANFACTURER MANAGER~!");
        this.buildEmployeeTab();
        //buildProductTab();
        this.buildExitPanel();
        this.buildSearchPanel();
        this.CreateManufacturerProductMain();
        this.BuildProductSearchTab();
        this.buildManuSearchPanel();
        this.BuildEditManufacturerPopupPanel(); //builds a popup for editing manufactuer. 
        buildSalesTab();

        buildEditProductPopupSetup();
        

        // add tabs
        mainTabs.addTab("Create an Employee", null, employeeTab, "Employee");
        //mainTabs.addTab("Products", null, productTab, "Products");
        mainTabs.addTab("Search and Edit Employees", null, this.searchMainPanel, "Search Employees");
        mainTabs.addTab("Products & manufactuerers", this.ProductManufacturerCreateSearchMainPanel);
        mainTabs.addTab("Employee Sales", null, this.salesTab,"Sales");

        // add subpanels to main panel
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(greetingPanel, BorderLayout.NORTH);
        mainPanel.add(mainTabs, BorderLayout.CENTER);
        mainPanel.add(exitPanel, BorderLayout.SOUTH);

        add(mainPanel);
        
        //Lastly disable buttons not used in the seassion
            sRProductEditButton.setVisible(this.thisSession.isAdminStatus());
            sRProductDeleteButton.setVisible(this.thisSession.isAdminStatus());

            sRManuEditButton.setVisible(this.thisSession.isAdminStatus());
            sRPmanuDeleteButton.setVisible(this.thisSession.isAdminStatus());;
    
            searchEmpEditButton.setVisible(this.thisSession.isAdminStatus());
            searchEmpDeleteButton.setVisible(this.thisSession.isAdminStatus());
            searchEmpSubmitEditButton.setVisible(this.thisSession.isAdminStatus());
        
        
        
        // prevent resize. 
        this.pack();
        this.setResizable(false);
    }
    
        
    private void buildSearchPanel () {
        //init panels
        this.searchMainPanel = new JPanel();
        this.searchResultsPanel = new JPanel();
        this.searchCriteriaPanel = new JPanel();
        this.searchCriteriaButtonsPanel = new JPanel();
        this.searchCriteriaEntryPanel = new JPanel(); 
        
        
        //System.out.println(this.SearchResultsVectors.getColumnNames().toString());
        searchResultsTable = new JTable(this.searchResultsDefaultTableModel);
        //searchResultsTable.add
        //        empJTableListSelectionListener
        
        //set all important layouts. 
        this.searchMainPanel.setLayout(new BorderLayout());
        this.searchCriteriaPanel.setLayout(new BorderLayout());
        this.searchCriteriaEntryPanel.setLayout(new GridLayout(0,3));
        this.searchCriteriaButtonsPanel.setLayout(new GridLayout(0,1));
        this.searchResultsPanel.setLayout(new BorderLayout());
        
        //add searchButton for executing Queries. 
        this.searchEmployees = new JButton("Search DataBase for Employee");
        this.searchEmployeeButtonListener = new SearchEmployeeButtonListener(); 
        this.searchEmployees.addActionListener(this.searchEmployeeButtonListener);
        this.searchEmployees.doClick(); //force a click
        this.searchResultsDefaultTableModel = searchEmployeeButtonListener.returnMyTable();
        //this.searchBtnListener.actionPerformed(new ActionEvent());
        this.searchCriteriaButtonsPanel.add(this.searchEmployees);
        
        
        
        
        //add the components to the criteria entry panel;
       
        // <editor-fold>
        this.searchCriteriaEntryPanel.add(this.searchEmpIDlbl);
        this.searchCriteriaEntryPanel.add(this.searchEmpIDSelectedCheckBox);
        this.searchCriteriaEntryPanel.add(this.searchEmpIDTextBox);
        
        this.searchCriteriaEntryPanel.add(this.searchEmpTypelbl);
        this.searchCriteriaEntryPanel.add(this.searchEmpTypeSelectedCheckBo);
        this.searchCriteriaEntryPanel.add(this.searchEmpTypeComboBox); // combo box
        
        
        this.searchCriteriaEntryPanel.add(this.searchFirstNamelbl);
        this.searchCriteriaEntryPanel.add(this.searchFirstNameSelectedCheckBox);
        this.searchCriteriaEntryPanel.add(this.searchFirstNameTextBox);
        
      
        this.searchCriteriaEntryPanel.add(this.searchLastNamelbl);
        this.searchCriteriaEntryPanel.add(this.searchLastNameSelectedCheckBox);
        this.searchCriteriaEntryPanel.add(this.searchLastNameTextBox);
        
        this.searchCriteriaEntryPanel.add(this.searchPositionlbl);
        this.searchCriteriaEntryPanel.add(this.searchPositionSelectedCheckBox);
        this.searchCriteriaEntryPanel.add(this.searchPositionTextBox);
       

        this.searchCriteriaEntryPanel.add(this.searchGenderlbl);
        this.searchCriteriaEntryPanel.add(this.searchGenderSelectedCheckBox);
        this.searchCriteriaEntryPanel.add(this.searchGenderTextBox);
        
        this.searchCriteriaEntryPanel.add(this.searchDepartmentlbl);
        this.searchCriteriaEntryPanel.add(this.searchDepartmentSelectedCheckBox);
        this.searchCriteriaEntryPanel.add(this.searchDepartmentTextBox);
        
        
        
        this.searchCriteriaEntryPanel.add(this.searchSinlbl);
        this.searchCriteriaEntryPanel.add(this.searchSinSelectedCheckBox);
        this.searchCriteriaEntryPanel.add(this.searchSinTextBox);
        
        this.searchCriteriaEntryPanel.add(this.searchBirthdaylbl);
        this.searchCriteriaEntryPanel.add(this.searchBirthdaySelectedCheckBox);
        this.searchCriteriaEntryPanel.add(this.searchbirthDayTextBox);
        
        this.searchCriteriaEntryPanel.add(this.searchHiredatelbl);
        this.searchCriteriaEntryPanel.add(this.searchHiredateSelectedCheckBox);
        this.searchCriteriaEntryPanel.add(this.searchHireDateTextBox);
        
        
        this.searchCriteriaEntryPanel.add(this.searchStatuslbl);
        this.searchCriteriaEntryPanel.add(this.searchStatusSelectedCheckBox);
        this.searchCriteriaEntryPanel.add(this.searchStatusTextBox);
        
        
        this.searchCriteriaEntryPanel.add(this.searchPhonelbl);
        this.searchCriteriaEntryPanel.add(this.searchPhoneSelectedCheckBox);
        this.searchCriteriaEntryPanel.add(this.searchPhoneTextBox);
        
        this.searchCriteriaEntryPanel.add(this.searchAddresslbl);
        this.searchCriteriaEntryPanel.add(this.searchAddressSelectedCheckBox);
        this.searchCriteriaEntryPanel.add(this.searchAddressTextBox);
        // </editor-fold>
        
        //empJTableListSelectionListener
        searchEmpEditButton.setEnabled(false);
        searchResultsTable.setModel(this.searchResultsDefaultTableModel);
            searchEmployeeListSelectionModel = searchResultsTable.getSelectionModel();
        searchEmployeeListSelectionModel.addListSelectionListener(new empJTableListSelectionListener());
        searchResultsTable.setSelectionModel(searchEmployeeListSelectionModel);
                
                
        //this.searchResultsTable = new JTable(this.searchResultsDefaultTableModel);
        this.searchResultsTable.setSize(300, 800);
        
            try {
            //placed inline as this is merely setup. 
                for (int i = 1; i <= (searchResultsTable.getColumnCount()); i++) 
                {
                    searchResultsTable.getColumn(i).setPreferredWidth(150);
                } 
            }
            catch (Exception e ) {
                System.out.println(e.getMessage());
                errorWriter.appendToFile("Packing issue: " + e.getMessage());
                //errorWriter.writeError(e);
            }
            
            //searchEmpEditButton.addActionListener();
            searchEmpDeleteButton.addActionListener(new DeleteEmployeeButtonListener());
            this.searchEmpEditButton.addActionListener(new SelectForEditListener());
            JPanel searchEditDeletePanel = new JPanel(); 
            searchEditDeletePanel.setLayout(new GridLayout(0,2));
            searchEditDeletePanel.add(searchEmpDeleteButton);
            searchEditDeletePanel.add(this.searchEmpEditButton);
            
            
        this.searchResultsTable.doLayout(); //auto adusts the table. 
        
        System.out.println(this.searchResultsDefaultTableModel.getDataVector().size());
        this.buildSearchPanelSelection();
        //add the components to the results set panel
        this.searchResultsScrollTable = new JScrollPane(this.searchResultsTable); 
        
        
        this.searchResultsPanel.add(this.searchResultsScrollTable, BorderLayout.NORTH);
        //this.searchResultsPanel.add(this.searchEditMainMain, BorderLayout.CENTER);
        this.searchResultsPanel.add(searchEditDeletePanel, BorderLayout.SOUTH);
        
        this.searchCriteriaPanel.add(this.searchCriteriaEntryPanel,BorderLayout.NORTH);
        this.searchCriteriaPanel.add(this.searchCriteriaButtonsPanel, BorderLayout.SOUTH);
        this.searchMainPanel.add(this.searchCriteriaPanel, BorderLayout.WEST); //searchResultsPanel
        this.searchMainPanel.add(this.searchResultsPanel, BorderLayout.EAST);
        
    }
    
    private void buildSearchPanelSelection() {
            searchEditUpperPanel.setLayout(new GridLayout(0,2));
            searchEditLowerPanel.setLayout(new GridLayout(0,1));
            searchEditMainMain = new JPanel(new BorderLayout());
            
            //searchEditUpperPanel.add(searchEDITEmpIDTextBox);
            searchEditUpperPanel.add(new JLabel("First Name"));
            searchEditUpperPanel.add(searchEDITFirstNameTextBox);
            searchEditUpperPanel.add(new JLabel("Last Name"));
            searchEditUpperPanel.add(searchEDITLastNameTextBox);
            searchEditUpperPanel.add(new JLabel("Position"));
            searchEditUpperPanel.add(searchEDITPositionTextBox);
            searchEditUpperPanel.add(new JLabel("Gender"));
            searchEditUpperPanel.add(searchEDITGenderTextBox );
            searchEditUpperPanel.add(new JLabel("Department"));
            searchEditUpperPanel.add(searchEDITDepartmentTextBox );
            searchEditUpperPanel.add(new JLabel("Sin Number"));
            searchEditUpperPanel.add(searchEDITSinTextBox);
            searchEditUpperPanel.add(new JLabel("Status"));
            searchEditUpperPanel.add(searchEDITStatusTextBox);
            searchEditUpperPanel.add(new JLabel("Phone"));
            searchEditUpperPanel.add(searchEDITPhoneTextBox );
            searchEditUpperPanel.add(new JLabel("Address"));
            searchEditUpperPanel.add(searchEDITAddressTextBox);
            searchEmpSubmitEditButton.addActionListener(new UpdateEmployeeButtonListener());
            this.searchEditLowerPanel.add(this.searchEmpSubmitEditButton);
            searchEditMainMain.add(this.searchEditUpperPanel, BorderLayout.NORTH);
            searchEditMainMain.add(this.searchEditLowerPanel, BorderLayout.SOUTH);
    }
    
    //build query tab for general searching of the employees 

    // build panels 
    // build greeting panel
    private void buildGreetingsPanel(String greetMessage) {
        greetingPanel = new JPanel();
        helloLbl = new JLabel(greetMessage);
        helloLbl.setFont(mainFont);
        greetingPanel.add(helloLbl);
        greetingPanel.setBorder(mainBorder);
    }

    // build employee tab and subpanels
    private void buildEmployeeTab() {
        // create tab
        employeeTab = new JPanel();
        buildEmployeePanel();
        buildEmployeeTypeTab();
        //buildEmployeeSearchPanel();

        // set layout and add all subpanels to employee tab
        employeeTab.setLayout(new BorderLayout());
        employeeTab.add(employeePanel, BorderLayout.NORTH);
        employeeTab.add(employeeTypeTab, BorderLayout.SOUTH);
        //employeeTab.add(employeeSearchPanel, BorderLayout.SOUTH);
    }

    private void buildEmployeePanel() {
        // create new panel
        employeePanel = new JPanel();
        // set grid
        employeePanel.setLayout(new GridLayout(0, 2));
        // set border
        employeePanel.setBorder(BorderFactory.createTitledBorder("Employee info"));
        // set labels
        firstNameLbl = new JLabel("First Name");
        lastNameLbl = new JLabel("Last Name");
        addressLbl = new JLabel("Address");
        phoneNumberLbl = new JLabel("Phone Number");
        yearLbl = new JLabel("Year of birth");
        monthLbl = new JLabel("Month of birth");
        dayLbl = new JLabel("Day of birth");
        
        //added code
        emphireDateYearlbl = new JLabel("Year of Hire");
        empHireDateMonthlbl = new JLabel("Month of Hire");
        empHireDateDaylbl = new JLabel("Day of Hire");
        empSinlbl = new JLabel("Sin Number");
        empPositionlbl = new JLabel("Position");
        empGenderlbl = new JLabel("Gender");
        empDepartmentlbl = new JLabel("Deparment");
        empStatuslbl =  new JLabel("Status");
        
        

        // add everything to panel
        employeePanel.add(firstNameLbl);
        employeePanel.add(firstNameTxt);
        employeePanel.add(lastNameLbl);
        employeePanel.add(lastNameTxt);
        employeePanel.add(addressLbl);
        employeePanel.add(addressTxt);
        employeePanel.add(phoneNumberLbl);
        employeePanel.add(phoneNumberTxt);
        employeePanel.add(yearLbl); 
        employeePanel.add(yearTxt);
        employeePanel.add(monthLbl);
        employeePanel.add(monthTxt);
        employeePanel.add(dayLbl);
        employeePanel.add(dayTxt);
        //added code new inputs
        employeePanel.add(emphireDateYearlbl); // needs handle
        employeePanel.add(emphireDateYearTxt); // needs handle
        employeePanel.add(empHireDateMonthlbl); // needs handle
        employeePanel.add(empHireDateMonthTxt); // needs handle
        employeePanel.add(empHireDateDaylbl); // needs handle
        employeePanel.add(empHireDateDayTxt); // needs handle
        employeePanel.add(empSinlbl); // needs handle
        employeePanel.add(empSinTxt); // needs handle
        employeePanel.add(empPositionlbl); 
        employeePanel.add(empPositionTxt);
        employeePanel.add(empGenderlbl);
        employeePanel.add(empGenderTxt);
        employeePanel.add(empDepartmentlbl); 
        employeePanel.add(empDepartmentTxt);
        employeePanel.add(empStatuslbl);
        employeePanel.add(empStatusTxt);
        
        
        
    }

    private void buildEmployeeTypeTab() {
        // create new tabs
        employeeTypeTab = new JTabbedPane();
        buildHourlyEmployeePanel();
        buildSalaryEmployeePanel();
        buildCommissionEmployeePanel();
 
        //controls what type of employee will be created for the idenity 
        // to the DB by listening to changes to the emp tab. 
        ChangeListener changeListener = new ChangeListener() {
            public void stateChanged(ChangeEvent changeEvent) {
                employeeTypeTab = (JTabbedPane) changeEvent.getSource();
                empCreateGetIndexofTabPane = employeeTypeTab.getSelectedIndex();
                System.out.println("Emp creation Tab changed to: " + 
                        employeeTypeTab.getTitleAt(empCreateGetIndexofTabPane)+
                        " \n Index of: " + empCreateGetIndexofTabPane);
        }
        };
        employeeTypeTab.addChangeListener(changeListener); //adds the 

        
        // add panels to tabs
        employeeTypeTab.addTab("Hourly Emp", null, hourlyEmpTab, "Hourly Emp");
        employeeTypeTab.addTab("Salary Emp", null, salaryEmpTab, "Salary Emp");
        employeeTypeTab.addTab("Commission Emp", null, commissionEmpTab, "Commission Emp");
    }

    
    
    // build three subpanels for each employee type
    private void buildHourlyEmployeePanel() {
        // create new panel
        hourlyEmpTab = new JPanel();
        // set grid
        hourlyEmpTab.setLayout(new GridLayout(3, 2));
        // set border
        hourlyEmpTab.setBorder(BorderFactory.createTitledBorder("Create Hourly Employee"));
        // set labels
        hoursLbl = new JLabel("Hours");
        hourRateLbl = new JLabel("Hour Rate");
        // create button
        createHourlyEmployeeBtn = new JButton("Create Hourly Employee");
        createHourlyEmployeeBtn.addActionListener(new NewEmployeeButtonLisenter()); //ExitButtonListener
        // add everything to panel
        hourlyEmpTab.add(hoursLbl);
        hourlyEmpTab.add(hoursTxt);
        hourlyEmpTab.add(hourRateLbl);
        hourlyEmpTab.add(hourRateTxt);
        hourlyEmpTab.add(createHourlyEmployeeBtn);

    }
    
    private void buildSalaryEmployeePanel() {
        // create new panel
        salaryEmpTab = new JPanel();
        // set grid
        salaryEmpTab.setLayout(new GridLayout(2, 2));
        // set border
        salaryEmpTab.setBorder(BorderFactory.createTitledBorder("Create Salary Employee"));
        // set labels
        weeklySalaryLbl = new JLabel("Weekly salary");
        // create button
        createSalaryEmployeeBtn = new JButton("Create Salary Employee");
        createSalaryEmployeeBtn.addActionListener(new NewEmployeeButtonLisenter());
        // add everything to panel
        salaryEmpTab.add(weeklySalaryLbl);
        salaryEmpTab.add(weeklySalaryTxt);
        salaryEmpTab.add(createSalaryEmployeeBtn);
    }

    private void buildCommissionEmployeePanel() {
        // create new panel
        commissionEmpTab = new JPanel();
        // set grid
        commissionEmpTab.setLayout(new GridLayout(4, 2));
        // set border
        commissionEmpTab.setBorder(BorderFactory.createTitledBorder("Create Commission Employee"));
        // set labels
        salesLbl = new JLabel("Sales");
        commissionRateLbl = new JLabel("Commission Rate");
        baseSalaryLbl = new JLabel("Base salary");
        // create button
        createCommEmployeeBtn = new JButton("Create Commission Employee");
        createCommEmployeeBtn.addActionListener(new NewEmployeeButtonLisenter());
        
        // add everything to panel
        commissionEmpTab.add(salesLbl);
        commissionEmpTab.add(salesTxt);
        commissionEmpTab.add(commissionRateLbl);
        commissionEmpTab.add(commissionRateTxt);
        commissionEmpTab.add(baseSalaryLbl);
        commissionEmpTab.add(baseSalaryTxt);
        commissionEmpTab.add(createCommEmployeeBtn);
    }

    /**
     * Creates the tabbed areas that ultimately hold
     * the following
     * Manufactuerer and produvct creation
     * manufacturer searh/edit/delete
     * product search/edit/delete
     */
    private void CreateManufacturerProductMain() {
        this.CreateManufactureProductCreatePanel();  //creates the create panels.
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.add("Create Product or manufacturer",this.CreateProductsmanufacturerMainPanel);
        tabbedPane.add("Search/edit/delete Manufacturer", this.sManuSearchMainPanel);
        tabbedPane.add("Search/edit/delete Product", this.sProductSearchMainPanel);
        ProductManufacturerCreateSearchMainPanel.add(tabbedPane);
        
    }
    
    //Creation
    
    //creates the tab to be placed into the ProductManufacturerCreateSearchMainPanel panel. 
    private void CreateManufactureProductCreatePanel(){
        this.CreateManufactureCreationPanel();
        this.CreateProductCreationPanel(); 
        this.CreateProductsmanufacturerMainPanel.setLayout(new BorderLayout());
        this.CreateProductsmanufacturerMainPanel.add(this.manufactureCreatePanelMain, BorderLayout.NORTH);
        this.CreateProductsmanufacturerMainPanel.add(this.productCreatePanelMain, BorderLayout.SOUTH);
    }
    
    //creates the panel for creating a new manufacturer
    private void CreateManufactureCreationPanel() {
        
        
        this.manufactureCreatePanelMain.setLayout(new BorderLayout());
        this.manufactureCreatePanelUp.setLayout(new GridLayout(0,2));
        this.manufactureCreatePanelDown.setLayout(new GridLayout(0,1));
        
        this.manufactureCreatePanelUp.add(new JLabel("Create A Manufacturer"));
        this.manufactureCreatePanelUp.add(new JLabel("")); //to keep layout correct. 
        
        this.manufactureCreatePanelUp.add(this.manuCreateNamelbl);
        this.manufactureCreatePanelUp.add(this.manuCreateNametxt);
        this.manufactureCreatePanelUp.add(this.manuCreateCountrylbl);
        this.manufactureCreatePanelUp.add(this.manuCreateCountrytxt);
        this.manufactureCreatePanelUp.add(this.manuCreateDescriptionlbl);
        this.manufactureCreatePanelUp.add(this.manuCreateDescriptiontxt);
        
        this.manuCreateButton.addActionListener(new submitManufacturerButtonListener());
        
        this.manufactureCreatePanelDown.add(this.manuCreateButton);
        this.manufactureCreatePanelMain.add(this.manufactureCreatePanelUp, BorderLayout.NORTH);
        this.manufactureCreatePanelMain.add(this.manufactureCreatePanelDown, BorderLayout.SOUTH);
    }
    
    //creates the create product Tab
    private void CreateProductCreationPanel() {
        pullManufacturerAndProductFromDB(false);
        
        this.productCreatePanelMain.setLayout(new BorderLayout());
        this.productCreatePanelUp.setLayout(new GridLayout(0,2));
        this.productCreatePanelDown.setLayout(new GridLayout(0,1));
        this.productCreatePanelUp.add(new JLabel("Create New Product"));
        this.productCreatePanelUp.add(new JLabel(""));//blank to keep aligned. 
        this.productCreatePanelUp.add(this.prodCreateNamelbl);
        this.productCreatePanelUp.add(this.prodCreateNameTxt);
        this.productCreatePanelUp.add(this.prodCreatePriceNamelbl);
        this.productCreatePanelUp.add(this.prodCreatePriceTxt);
        this.productCreatePanelUp.add(this.prodCreateProductionCostlbl);
        this.productCreatePanelUp.add(this.prodCreateProductionCostTxt);
        
        this.productCreatePanelUp.add(this.prodCreateManufacturerlbl);
        this.manuComboBox.addActionListener(new manuComboBoxListener());
        this.productCreatePanelUp.add(this.manuComboBox);
        this.productCreatePanelUp.add(this.prodCreateRatinglbl);
        this.productCreatePanelUp.add(this.prodCreateRatingTxt);
        this.productCreatePanelUp.add(this.prodCreateDescriptionlbl);
        this.productCreatePanelUp.add(this.prodCreateDescriptionTxt);
        
        this.prodCreateButton.addActionListener(new submitProductButtonListener());
        this.productCreatePanelDown.add(this.prodCreateButton);
        
        productCreatePanelMain.add(productCreatePanelUp, BorderLayout.NORTH);
        productCreatePanelMain.add(productCreatePanelDown, BorderLayout.SOUTH);
        
    }

    //builds the main product search tab
    public void BuildProductSearchTab() {
        buildProductSearchRightSide();
        buildProductSearchLeftSide();
        
        sProductSearchMainPanel.setLayout(new BorderLayout());
        sProductSearchMainPanel.add(sProductSearchRightPanel, BorderLayout.EAST);
        sProductSearchMainPanel.add(sProductSearchLeftPanel, BorderLayout.WEST);
    }
    
    //builds the right side of the product search
    private void buildProductSearchLeftSide() {
        sProductSearchLeftPanel.setLayout(new BorderLayout());
        sProductSearchLeftUpPanel.setLayout(new GridLayout(0,3));
        sProductSearchLeftDownPanel.setLayout(new GridLayout(0,1));
        
        sProductSearchLeftUpPanel.add(slProductSearchIDlbl);
        sProductSearchLeftUpPanel.add(slProductSearchIDCheckBox);
        sProductSearchLeftUpPanel.add(slProductSearchIDTxt);
        
        sProductSearchLeftUpPanel.add(slProductSearchNamelbl);
        sProductSearchLeftUpPanel.add(slProductSearchNameCheckBox);
        sProductSearchLeftUpPanel.add(slProductSearchNameTxt);
        
        sProductSearchLeftUpPanel.add(slProductSearchPricelbl);
        sProductSearchLeftUpPanel.add(slProductSearchPriceCheckBox);
        sProductSearchLeftUpPanel.add(slProductSearchPriceTxt);
        
        sProductSearchLeftUpPanel.add(slProductSearchProductionCostlbl);
        sProductSearchLeftUpPanel.add(slProductSearchProductionCostCheckBox);
        sProductSearchLeftUpPanel.add(slProductSearchProductionCostTxt);
        
        sProductSearchLeftUpPanel.add(slProductSearchRatinglbl);
        sProductSearchLeftUpPanel.add(slProductSearchRatingCheckBox);
        sProductSearchLeftUpPanel.add(slProductSearchRatingTxt);
        
        sProductSearchLeftUpPanel.add(slProductSearchDescriptionlbl);
        sProductSearchLeftUpPanel.add(slProductSearchDescriptionCheckBox);
        sProductSearchLeftUpPanel.add(slProductSearchDescriptionTxt);
        srProductSearchButton.addActionListener(searchProductListener);
        sProductSearchLeftDownPanel.add(srProductSearchButton);
        
        sProductSearchLeftPanel.add(sProductSearchLeftUpPanel,BorderLayout.NORTH);
        sProductSearchLeftPanel.add(sProductSearchLeftDownPanel,BorderLayout.SOUTH);

    }
    
    //builds the left side of the product search
    private void buildProductSearchRightSide() {
        
        sProductSearchRightPanel.setLayout(new BorderLayout());
        sProductSearchRightDownPanel.setLayout(new GridLayout(0,2));
        sProductSearchRightUpPanel.setLayout(new GridLayout(0,1));
        
        productSearchResults = new JTable(this.prodTblModel);
        //add selection listener. 
        prodListSelectionModel = productSearchResults.getSelectionModel();
        prodListSelectionModel.addListSelectionListener(new selectProductTableListener());
        productSearchResults.setSelectionModel(prodListSelectionModel);
        
        productSearchResultsScrollpane = new JScrollPane(productSearchResults);
        
        sProductSearchRightUpPanel.add(productSearchResultsScrollpane);
        sRProductDeleteButton.addActionListener(new DeleteProductButtonListener());
        sRProductEditButton.addActionListener(new showUpdateProductWindow());
        
        sRProductEditButton.setEnabled(false);
        sRProductDeleteButton.setEnabled(false);
        
        //new showUpdateProductWindow()
        sProductSearchRightDownPanel.add(sRProductEditButton);
        sProductSearchRightDownPanel.add(sRProductDeleteButton);
        
        sProductSearchRightPanel.add(sProductSearchRightUpPanel, BorderLayout.NORTH);
        sProductSearchRightPanel.add(sProductSearchRightDownPanel, BorderLayout.SOUTH);
        
        

        
        
        
    }

 
    /**
     * builds the total manufactuerer search panel. 
     */
    private void buildManuSearchPanel() {
        this.buildSearchManuLeftSide();
        this.buldSearchManuRightSide();
        
                
        sManuSearchMainPanel.setLayout(new BorderLayout());
        sManuSearchMainPanel.add(sManuSearchRightPanel, BorderLayout.EAST);
        sManuSearchMainPanel.add(sManutSearchLeftPanel, BorderLayout.WEST);
    }
    
    /**
     * builds the left sife of the search manufacturer
     */
    private void buildSearchManuLeftSide() {
        sManutSearchLeftPanel.setLayout(new BorderLayout());
        sManuSearchLeftUpPanel.setLayout(new GridLayout (0,3));
        sManuSearchLeftDownPanel.setLayout(new GridLayout (0,1));
        
        sManuSearchLeftUpPanel.add(slManuSearchIDlbl);
        sManuSearchLeftUpPanel.add(slManuSearchIDCheckBox);
        sManuSearchLeftUpPanel.add(slManuSearchIDTxt);
        
        sManuSearchLeftUpPanel.add(slManuSearchNamelbl);
        sManuSearchLeftUpPanel.add(slManuSearchNameCheckBox);
        sManuSearchLeftUpPanel.add(slManuSearchNameTxt);
        
        sManuSearchLeftUpPanel.add(slManuSearchCountrylbl);
        sManuSearchLeftUpPanel.add(slManuSearchCountryCheckBox);
        sManuSearchLeftUpPanel.add(slManuSearchCountryTxt);
        
        sManuSearchLeftUpPanel.add(slManuSearchDescriptionlbl);
        sManuSearchLeftUpPanel.add(slManuSearchDescriptionCheckBox);
        sManuSearchLeftUpPanel.add(slManuSearchDescriptionTxt);
        
        slManuSearchButton.addActionListener(searchManuButtonListener);
        sManuSearchLeftDownPanel.add(slManuSearchButton);
        sManutSearchLeftPanel.add(sManuSearchLeftUpPanel, BorderLayout.NORTH);
        sManutSearchLeftPanel.add(sManuSearchLeftDownPanel, BorderLayout.SOUTH);
        
        
        
    }
    
    /**
     * builds the right side of the search manufacturer
     */
    private void buldSearchManuRightSide() {
        sManuSearchRightPanel.setLayout(new BorderLayout());
        sManuSearchRightUpPanel.setLayout(new GridLayout(0,1));
        sManuSearchRightDownPanel.setLayout(new GridLayout(0,2));
        
        manuSearchResults = new JTable (this.manuTblModel);
        
        manuListSelectionModel = manuSearchResults.getSelectionModel();
        manuListSelectionModel.addListSelectionListener(new selectManutTableListener());
        manuSearchResults.setSelectionModel(manuListSelectionModel);
        
        this.manuSearchResultsScrollpane = new JScrollPane(manuSearchResults);
        sManuSearchRightUpPanel.add(manuSearchResultsScrollpane);
        sRPmanuDeleteButton.addActionListener(new DeleteManuButtonListener());
        sRManuEditButton.addActionListener(new showUpdateManufacturerWindow());
        //new showUpdateManufacturerWinodow()
        
        sRManuEditButton.setEnabled(false);
        sRPmanuDeleteButton.setEnabled(false);
        
        
        sManuSearchRightDownPanel.add(this.sRManuEditButton);
        sManuSearchRightDownPanel.add(this.sRPmanuDeleteButton);
        
        
        sManuSearchRightPanel.add(sManuSearchRightUpPanel, BorderLayout.NORTH);
        sManuSearchRightPanel.add(sManuSearchRightDownPanel, BorderLayout.SOUTH);
        
    }
        
    
    private void BuildEditManufacturerPopupPanel() {
        editManufacturerPanelMain.setLayout(new BorderLayout());
        editManufacturerPanelup.setLayout(new GridLayout(0,2));
        editManufacturerPaneldown.setLayout(new GridLayout(0,1));
        
        editManufacturerPanelup.add(editManuNamelbl);
        editManufacturerPanelup.add(editManuNameTxt);
        editManufacturerPanelup.add(editManuCountrylbl);
        editManufacturerPanelup.add(editManuCountryTxt);
        editManufacturerPanelup.add(editManuDescriptionlbl);
        editManufacturerPanelup.add(editManuDescriptioneTxt);
        
        //editManuButton.addActionListener();
        //showUpdateManufacturerWinodow
        editManufacturerPaneldown.add(editManuButton);
        editManuButton.addActionListener(new UpdateManuMenuButtonListener());
        
        editManufacturerPanelMain.add(editManufacturerPanelup, BorderLayout.NORTH);
        editManufacturerPanelMain.add(editManufacturerPaneldown, BorderLayout.SOUTH);
        
        
                
                
    }
    /**
     * Builds the product popup. 
     */
    private void buildEditProductPopupSetup () {
        editProductPaneMain.setLayout(new BorderLayout());
        editProductPaneup.setLayout(new GridLayout(0,2));
        editProductPaneDown.setLayout(new GridLayout(0,1));
        editProductPaneup.add(editProductNamelbl);
        editProductPaneup.add(editProductNametxt);
        editProductPaneup.add(editProductPricelbl);
        editProductPaneup.add(editProductPricetxt);
        editProductPaneup.add(editProductionCostlbl);
        editProductPaneup.add(editProductionCosttxt);
        editProductPaneup.add(editProductRatinglbl);
        editProductPaneup.add(editProductRatingtxt);
        editProductPaneup.add(editProductDescriptionlbl);
        editProductPaneup.add(editProductDescriptiontxt);
        
        editProductSubmitButton.addActionListener(new UpdateProdMenuButtonListener());
        editProductPaneDown.add(editProductSubmitButton);
        
        editProductPaneMain.add(editProductPaneup, BorderLayout.NORTH);
        editProductPaneMain.add(editProductPaneDown, BorderLayout.SOUTH);
        
        
        
    }

    
    // build bottom panel with exit button
    private void buildExitPanel() {
        exitPanel = new JPanel();
        exitBtn = new JButton("Exit");
        exitBtn.addActionListener(new ExitButtonListener());
        exitPanel.add(exitBtn);
    }

    /**
     * Creates the search criteria for a product
     * @param setupFlag
     * @return 
     */
     private String[] createProductSearchCriteria (int setupFlag)  {
        ArrayList<String> temp = new ArrayList<String>();
        
        if (this.slProductSearchIDCheckBox.isSelected() == true) {
            if (setupFlag == 1) {
                temp.add(this.slProductSearchIDTxt.getText());
            } 
            else if (setupFlag == 2) {
                temp.add("ID");
            }
        }
        if (this.slProductSearchNameCheckBox.isSelected() == true) {
            if (setupFlag == 1) {
                temp.add("'"+this.slProductSearchNameTxt.getText()+"'");
            } 
            else if (setupFlag == 2) {
                temp.add("name");
            }
        }
        if (this.slProductSearchPriceCheckBox.isSelected() == true) {
            if (setupFlag == 1) {
                temp.add(this.slProductSearchPriceTxt.getText());
            } 
            else if (setupFlag == 2) {
                temp.add("Price");
            }
        }
        if (this.slProductSearchProductionCostCheckBox.isSelected() == true) {
            if (setupFlag == 1) {
                temp.add(this.slProductSearchProductionCostTxt.getText());
            } 
            else if (setupFlag == 2) {
                temp.add("productionCost");
            }
        }
        if (this.slProductSearchRatingCheckBox.isSelected() == true) {
            if (setupFlag == 1) {
                temp.add(this.slProductSearchRatingTxt.getText());
            } 
            else if (setupFlag == 2) {
                temp.add("rating");
            }
        }
        if (this.slProductSearchDescriptionCheckBox.isSelected() == true) {
            if (setupFlag == 1) {
                temp.add("'"+this.slProductSearchDescriptionTxt.getText());
            } 
            else if (setupFlag == 2) {
                temp.add("rating");
            }
        }
        
        String[] returnValue = temp.toArray(new String[temp.size()]);
        return returnValue;
     }
    
     /**
      * Creates the search criteria for searching a manufacturer
      * @param setupFlag
      * @return 
      */
     private String[] createManufacturerSearchCriteria (int setupFlag){
         ArrayList<String> temp = new ArrayList<String>();
        
        if (this.slManuSearchIDCheckBox.isSelected() == true) {
            if (setupFlag == 1) {
                temp.add("'"+this.slManuSearchIDTxt.getText()+"'");
            } 
            else if (setupFlag == 2) {
                temp.add("ID");
            }
        }
        if (this.slManuSearchNameCheckBox.isSelected() == true) {
            if (setupFlag == 1) {
                temp.add("'"+this.slManuSearchNameTxt.getText()+"'");
            } 
            else if (setupFlag == 2) {
                temp.add("Name");
            }
        }
        if (this.slManuSearchCountryCheckBox.isSelected() == true) {
            if (setupFlag == 1) {
                temp.add("'"+this.slManuSearchCountryTxt.getText()+"'");
            } 
            else if (setupFlag == 2) {
                temp.add("Country");
            }
        }
        if (this.slManuSearchDescriptionCheckBox.isSelected() == true) {
            if (setupFlag == 1) {
                temp.add("'"+this.slManuSearchDescriptionTxt.getText()+"'");
            } 
            else if (setupFlag == 2) {
                temp.add("Description");
            }
        }
        String[] returnValue = temp.toArray(new String[temp.size()]);
        
        return returnValue;
     }
    
    /**
     * Sets up either a search criteria of values (1), or sections (2) 
     * @param setupFlag
     * @return 
     */
    private String[] createEmpSearchCriteria (int setupFlag)  {
        ArrayList<String> temp = new ArrayList<String>();
        
        if (this.searchEmpIDSelectedCheckBox.isSelected() == true) {
            if (setupFlag == 1) {
                temp.add(this.searchEmpIDTextBox.getText());
            } 
            else if (setupFlag == 2) {
                temp.add("EmpID");
            }
            
        }
        //comboBox.getSelectedItem().toString()
        if (this.searchEmpTypeSelectedCheckBo.isSelected() == true) {
            if (setupFlag == 1) {
                temp.add(String.valueOf(this.searchEmpTypeComboBox.getSelectedIndex()));
            } 
            else if (setupFlag == 2) {
                temp.add("EmpType");
            }
            
        }
        if (this.searchFirstNameSelectedCheckBox.isSelected() == true) {
            if (setupFlag == 1) {
                temp.add("'"+this.searchFirstNameTextBox.getText()+"'");
            } 
            else if (setupFlag == 2) {
                temp.add("firstName");
            }
            
        }
        if (this.searchLastNameSelectedCheckBox.isSelected() == true) {
            if (setupFlag == 1) {
                temp.add("'"+this.searchLastNameTextBox.getText()+"'");
            } 
            else if (setupFlag == 2) {
                temp.add("lastName");
            }
        }
        
        if (this.searchPositionSelectedCheckBox.isSelected() == true) {
            if (setupFlag == 1) {
                temp.add("'"+this.searchPositionTextBox.getText()+"'");
            } 
            else if (setupFlag == 2) {
                temp.add("position");
            }
            
        }
        if (this.searchGenderSelectedCheckBox.isSelected() == true) {
            if (setupFlag == 1) {
                temp.add("'"+this.searchGenderTextBox.getText()+"'");
            } 
            else if (setupFlag == 2) {
                temp.add("gender");
            }
        }
        if (this.searchDepartmentSelectedCheckBox.isSelected() == true) {
            if (setupFlag == 1) {
                temp.add("'"+this.searchDepartmentTextBox.getText()+"'");
            } 
            else if (setupFlag == 2) {
                temp.add("department");
            }
            
        }
        if (this.searchSinSelectedCheckBox.isSelected() == true) {
            if (setupFlag == 1) {
                temp.add("'"+this.searchSinTextBox.getText()+"'");
            } 
            else if (setupFlag == 2) {
                temp.add("sin");
            }
        }
        if (this.searchBirthdaySelectedCheckBox.isSelected() == true) {
            if (setupFlag == 1) {
                temp.add("'"+this.searchbirthDayTextBox.getText()+"'");
            } 
            else if (setupFlag == 2) {
                temp.add("birthDate");
            }
            
        }
        if (this.searchHiredateSelectedCheckBox.isSelected() == true) {
            if (setupFlag == 1) {
                temp.add("'"+this.searchHireDateTextBox.getText()+"'");
            } 
            else if (setupFlag == 2) {
                temp.add("hireDate");
            }
        }
        
        if (this.searchStatusSelectedCheckBox.isSelected() == true) {
            if (setupFlag == 1) {
                temp.add("'"+this.searchStatusTextBox.getText()+"'");
            } 
            else if (setupFlag == 2) {
                temp.add("status");
            }
        }
        if (this.searchPhoneSelectedCheckBox.isSelected() == true) {
            if (setupFlag == 1) {
                temp.add("'"+this.searchPhoneTextBox.getText()+"'");
            } 
            else if (setupFlag == 2) {
                temp.add("phone");
            }
        }
        if (this.searchAddressSelectedCheckBox.isSelected() == true) {
            if (setupFlag == 1) {
                temp.add("'" + this.searchAddressTextBox.getText()+"'");
            } 
            else if (setupFlag == 2) {
                temp.add("address");
            }
        }
        
        // String [] stockArr = stockList.toArray(new String[stockList.size()]);
        //could probably do this in a statement, but brain fried. 
        
        String[] returnValue = temp.toArray(new String[temp.size()]);
        
        return returnValue;
        
    }

/**
 * Creates an SQL string statement to pull from a database. Created by Ben
 * Dunn. 
 * @param SearchCriteria
 * @param searchKey
 * @param TableName
 * @return a string representing the SQL query that will need to be run on
 * the datadbase. 
 * @throws IndexOutOfBoundsException 
 */
    private String searchSetup (String[] SearchCriteria, String[] searchKey, String TableName) 
    throws IndexOutOfBoundsException
    {
        System.out.println("Reached Creation of statement");
        //throw the exception if the searchKeys.legnth =! SearchCriteria
        if (SearchCriteria.length!=searchKey.length) {
            throw new IndexOutOfBoundsException("Array sizes are not equal in search criteria"); 
        } //no issues. 
        
        
        String searchString = "SELECT * FROM " + TableName + " ";
        //System.out.println(searchString);
        try {
        for (int k =0; k<= SearchCriteria.length-1; k++) 
        {
            
            //flip the switch for and/where queries. 
            if (k == 0) {
                searchString += "WHERE ";
            }
            else {
                searchString += "AND ";
            }
            searchString += SearchCriteria[k] + " = " + searchKey[k]; //add the value to the end. 
            
        }} catch (IndexOutOfBoundsException e) {
            //errorWriter.writeError(e);
            errorWriter.appendToFile("index of the legnth was out of bounds" + e.getMessage());
            System.out.println("index of the legnth was out of bounds");
        }
        
        
        searchString += ";";
        //debug system out for checking
        System.out.println("Query = " + searchString);
        
        return searchString; 
        
    }
    
    
    
    
    /**
     * deletes the instance of the employee at the index selected
     * by the user. 
     */
    private void deleteEmployee() {
        int Delete = this.employeeStorage[this.searchResultsTable.getSelectedRow()].getEmpId();
        //this.employeeStorage[0];
        DBConnection conn = new DBConnection(); 
        try {
            conn.deleteSQLDataBase("gc200325005.Employee","EmpID", Delete);
            this.searchEmployees.doClick(); //force and update through an event click
            updateAddSalesButtonComboBozes();
            
        } catch(Exception e)
        {
            errorWriter.appendToFile("Delete Error" + e.getMessage());
            System.out.println("Delete Error" + e.getMessage());
        };
        this.searchEmployees.doClick();//and do it again to prevent errors. 
    }

    /**
     * calls the methods to delete a manufacturer
     */
    private void deleteManufacturer() {
        int Delete = this.manuStorage[this.manuSearchResults.getSelectedRow()].getManufacturerID();
        //this.employeeStorage[0];
        DBConnection conn = new DBConnection(); 
        try {
            conn.deleteSQLDataBase("gc200325005.Products","ManufacturerID", Delete); //delete the tied in products to prevent errors in DB
            conn.deleteSQLDataBase("gc200325005.Manufacturers","ID", Delete);
            
            //this.slManuSearchButton.doClick(); //force and update through an event click
            updateManufacturerAndProductFromDB();
        } catch(Exception e)
        {
            errorWriter.appendToFile("Delete Error" + e.getMessage());
            System.out.println("Delete Error" + e.getMessage());
        };
        //this.slManuSearchButton.doClick();//and do it again to prevent errors. 
    }
    
    /**
     * calls the methods to delete a product
     */
    private void deleleteProduct() {
        int Delete = this.prodStorage[this.productSearchResults.getSelectedRow()].getProductID();
        DBConnection conn = new DBConnection(); 
        try {
            conn.deleteSQLDataBase("gc200325005.Products","ID", Delete); //delete the tied in products to prevent errors in DB
            updateManufacturerAndProductFromDB();
            //this.srProductSearchButton.doClick(); //force and update through an event click
            updateAddSalesButtonComboBozes();
            
        } catch(Exception e)
        {
            errorWriter.appendToFile("Delete Error" + e.getMessage());
            System.out.println("Delete Error" + e.getMessage());
        };
        //this.srProductSearchButton.doClick();//and do it again to prevent errors. 
    }
    
    
    
    /**
     * Isolated return for values. 
     * @return 
     */
    private EmployeesIdentity searchEmployee() {
        EmployeesIdentity empIden;
        try {
                    //System.out.println("Hey I did fucking shit!");
                    DBConnection Conn = new DBConnection(); 
                    empIden = Conn.getEmployeeInformation(searchSetup(createEmpSearchCriteria(2), 
                            createEmpSearchCriteria(1), "gc200325005.Employee"));

                    searchResultsTable.setModel(empIden.getEmpTable());
                    employeeStorage = empIden.getEmpArrayReturn();
                    System.out.println("Size of Current employeeStorage array: "+ employeeStorage.length);
                    updateAddSalesButtonComboBozes();

            }catch (Exception e) {
                //errorWriter.writeError(e);
                errorWriter.appendToFile("ERROR: " + e.getMessage());
                System.out.println("Error!");
                System.out.println(e.getMessage());
                empIden = new EmployeesIdentity(); //return empty empIdenity. 
                
            }
        return empIden;
    }
    /**
     * Updates the values in the held object and readies it to be written. 
     */
    private void updateEmployeeStoredEmployee() {
        try {
            selectedEmployee.setSinNumber(Integer.getInteger(searchEDITSinTextBox.getText()));
        } catch (Exception e) {errorWriter.appendToFile("Error: " + e.getMessage());} //does not need to be thrown, but required for code
        selectedEmployee.setFirstName(searchEDITFirstNameTextBox.getText());
        selectedEmployee.setLastName(searchEDITLastNameTextBox.getText());
        selectedEmployee.setPosition(searchEDITPositionTextBox.getText());
        selectedEmployee.setGender(searchEDITGenderTextBox.getText());
        selectedEmployee.setStatus(searchEDITStatusTextBox.getText());
        selectedEmployee.setPhoneNumber(searchEDITPhoneTextBox.getText());
        selectedEmployee.setAddress(searchEDITAddressTextBox.getText());
        DBUpdateEmployee();
        updateAddSalesButtonComboBozes();
    }
    
    
    /**
     * Updates the values in the held manufacturer value from
     * the text fields and updates the 
     */
    private void updateStoredManufactuter() {
        manuSelected.setName(editManuNameTxt.getText());
        manuSelected.setCountry(editManuCountryTxt.getText());
        manuSelected.setDescription(editManuDescriptioneTxt.getText());
        DBUpdateManufacturer(); 
    }
    /**
     * grabs the values for updating the products by passing them into 
     * the current product object. 
     */
    private void updateStoredProducts() {
        if(updateStoredProductsValidate()) {
            prodSelected.setName(this.editProductNametxt.getText());
            prodSelected.setPrice(Double.parseDouble(this.editProductPricetxt.getText()));
            prodSelected.setRating(Double.parseDouble(this.editProductRatingtxt.getText()));
            prodSelected.setProductionCost(Double.parseDouble(this.editProductionCosttxt.getText()));
            prodSelected.setDescription(this.editManuDescriptioneTxt.getText());
            DBUpdateProduct();
            updateAddSalesButtonComboBozes();
        }
        
    }
    /**
     * validates the inputs of the update product tab. 
     * @return 
     */
    private boolean updateStoredProductsValidate() {
        if (this.DoubleTryParse(this.editProductPricetxt.getText()) && 
                this.DoubleTryParse(this.editProductRatingtxt.getText())&&
                this.DoubleTryParse(this.editProductionCosttxt.getText()))
        {
            return true;
        }
        
        
        return false;
        
    }
    
    /**
     * Submits the new product informaton to the DB. 
     */
    public void DBUpdateProduct() {
        DBConnection conn = new DBConnection();
        conn.updateSQLDataBase("gc200325005.Products", "name",prodSelected.getName(), "ID", prodSelected.getManuID());
        conn.updateSQLDataBase("gc200325005.Products", "description", prodSelected.getDescription(), "ID", prodSelected.getManuID());
        conn.updateSQLDataBase("gc200325005.Products", "rating",String.valueOf(prodSelected.getRating()), "ID", prodSelected.getManuID());
        conn.updateSQLDataBase("gc200325005.Products", "price", String.valueOf(prodSelected.getPrice()), "ID", prodSelected.getManuID());
        conn.updateSQLDataBase("gc200325005.Products", "productionCost", String.valueOf(prodSelected.getProductionCost()), "ID", prodSelected.getManuID());
        
    }
    
    
    //updates the manufacturer to the server. 
    public void  DBUpdateManufacturer() {
        DBConnection conn = new DBConnection();
        
        conn.updateSQLDataBase("gc200325005.Manufacturers", "Name", "'"+manuSelected.getName()+"'", "ID", manuSelected.getManufacturerID());
        conn.updateSQLDataBase("gc200325005.Manufacturers", "country", "'"+manuSelected.getCountry()+"'", "ID", manuSelected.getManufacturerID());
        conn.updateSQLDataBase("gc200325005.Manufacturers", "description", "'"+manuSelected.getDescription()+"'", "ID", manuSelected.getManufacturerID());
        updateManufacturerAndProductFromDB();
    }
    
    /**
     * Simply way of updating the DB, puts the stored values into the array for update.
     */
    public void DBUpdateEmployee() {
        DBConnection conn = new DBConnection();
        //
        conn.updateSQLDataBase("Employee", "firstName", "'"+selectedEmployee.getFirstName()+"'", "EmpID", selectedEmployee.getEmpId());
        conn.updateSQLDataBase("Employee", "lastName",  "'"+selectedEmployee.getLastName()+"'", "EmpID", selectedEmployee.getEmpId());
        conn.updateSQLDataBase("Employee", "position",  "'"+selectedEmployee.getPosition()+"'", "EmpID", selectedEmployee.getEmpId());
        conn.updateSQLDataBase("Employee", "gender",  "'"+selectedEmployee.getGender()+"'", "EmpID", selectedEmployee.getEmpId());
        conn.updateSQLDataBase("Employee", "status",  "'"+selectedEmployee.getStatus()+"'", "EmpID", selectedEmployee.getEmpId());
        conn.updateSQLDataBase("Employee", "Phone",  "'"+selectedEmployee.getPhoneNumber()+"'", "EmpID", selectedEmployee.getEmpId());
        conn.updateSQLDataBase("Employee", "address",  "'"+selectedEmployee.getAddress()+"'", "EmpID", selectedEmployee.getEmpId());
        this.searchEmployeeBtn.doClick();
    }
    
        //pulls and sets the identities and updates the combo box with the apropriate 
        //values for creating a new product or manufacturer. 
    private void pullManufacturerAndProductFromDB(boolean AfterFirstRun) {
     DBConnection conn = new DBConnection(); 
        this.manuIdenity = conn.getManufacturerInformation("SELECT * FROM gc200325005.Manufacturers;");
        this.manuStorage = this.manuIdenity.getManuArrayReturn();
        this.manuTblModel = this.manuIdenity.getManuTable();
        
        
        this.prodIdentity = conn.getProductInformation("SELECT * FROM gc200325005.Products;");
        this.prodStorage = prodIdentity.getProdArrayReturn();
        this.prodTblModel = prodIdentity.getProdTable();
        
        if (AfterFirstRun) {
            manuListSelectionModel = manuSearchResults.getSelectionModel();
            manuListSelectionModel.addListSelectionListener(new selectManutTableListener());
            manuSearchResults.setSelectionModel(manuListSelectionModel);

            prodListSelectionModel = productSearchResults.getSelectionModel();
            prodListSelectionModel.addListSelectionListener(new selectProductTableListener());
            productSearchResults.setSelectionModel(prodListSelectionModel);
        }

        for (int k = 0; k< manuStorage.length; k++){
            this.manuComboBox.addItem(this.manuStorage[k].getName());
        }
        sRManuEditButton.setEnabled(false);
        sRPmanuDeleteButton.setEnabled(false);
        sRProductEditButton.setEnabled(false);
        sRProductDeleteButton.setEnabled(false);
        
    }

    //used to update the tables of product and manufacturer after crud operations
    private void updateManufacturerAndProductFromDB() {
        pullManufacturerAndProductFromDB(true);
        this.manuSearchResults.setModel(this.manuTblModel);
        this.productSearchResults.setModel(this.prodTblModel);
    }
    
    private class NewEmployeeButtonLisenter implements ActionListener 
        {

        @Override
        public void actionPerformed(ActionEvent event) 
        {
            createEmployeeAndSubmitToDB();
        }
    }
    //calls the methods to delete a product
    private class DeleteProductButtonListener implements ActionListener 
        {

        @Override
        public void actionPerformed(ActionEvent event) 
        {
            if (JOptionPane.showConfirmDialog(null, "Doing this action delete the product, are you sure?", 
                    "Delete?", JOptionPane.YES_NO_OPTION) == 0) {
                deleleteProduct(); 
                prodSelectedInt = 0;
           }
        }
    }
  // call the methods to delete a manudacturerer  
    private class DeleteManuButtonListener implements ActionListener 
        {

        @Override
        public void actionPerformed(ActionEvent event) 
        {
            if (JOptionPane.showConfirmDialog(null, "Doing this action will also delete all products related to this manufacturer in the products table, are you sure?", 
                    "Delete?", JOptionPane.YES_NO_OPTION) == 0) {
                deleteManufacturer();
                ManuSelectedInt = 0;
           }
        }
    }
    
    /**
     * action listener class to perform the search actios
     */
    private class SearchManuButtonListener implements ActionListener 
    {

        private ManufacturerIdentity mymanufacturerIdentity;
        @Override
        public void actionPerformed(ActionEvent event) 
        {
            /*
                private ListSelectionModel manuListSelectionModel;
    private ManufacturerIdentity manuIdenity; 
    private Manufacturer[] manuStorage;
    private int ManuSelectedInt = 0;
    private Manufacturer manuSelected;
    private DefaultTableModel manuTblModel;
            */

            try {
                    //System.out.println("Hey I did fucking shit!");
                    DBConnection Conn = new DBConnection(); 
                    mymanufacturerIdentity = Conn.getManufacturerInformation(searchSetup(createManufacturerSearchCriteria(2), 
                            createManufacturerSearchCriteria(1), "gc200325005.Manufacturers"));

                    manuSearchResults.setModel(mymanufacturerIdentity.getManuTable());
                    manuStorage = mymanufacturerIdentity.getManuArrayReturn();
                    System.out.println("Size of Current employeeStorage array: "+ employeeStorage.length);

            }catch (Exception e) {
                //errorWriter.writeError(e);
                System.out.println("Error!");
                System.out.println(e.getMessage());
                errorWriter.appendToFile("Error! " + e.getMessage());
            }
            searchEmpEditButton.setEnabled(false);
            
        }
        /**
         * 
         * @return the table model for the search results if needed. 
         */
        public ManufacturerIdentity returnMyIdentiy() {
            return mymanufacturerIdentity;
        } 
        
    }
    
    private class SearchProductButtonListener implements ActionListener 
    {

        private ProductIdentity myProdIdenity;

        @Override
        public void actionPerformed(ActionEvent event) {
            //call and execute the query 
            
            
                try {
                    System.out.println("Reached Product Search:");
                    //System.out.println("Hey I did fucking shit!");
                    DBConnection Conn = new DBConnection(); 
                    this.myProdIdenity = Conn.getProductInformation(searchSetup(createProductSearchCriteria(2),
                            createProductSearchCriteria(1), "gc200325005.Products"));
                    
                    

                    productSearchResults.setModel(myProdIdenity.getProdTable()); 
                    prodStorage = myProdIdenity.getProdArrayReturn();
                    System.out.println("Size of Current product array: "+ prodStorage.length);

                    
            }catch (Exception e) {
                //errorWriter.writeError(e);
                System.out.println("Error!");
                System.out.println(e.getMessage());
                errorWriter.appendToFile("Error! " + e.getMessage());
            }
            sRProductEditButton.setEnabled(false);
            sRProductDeleteButton.setEnabled(false);
            
        }
        
        public ProductIdentity getProdIdenity() {
            return myProdIdenity;
        }
        
    }
    
    
    //listener for the selection of the manufacture table row
    private class selectManutTableListener implements ListSelectionListener {
        @Override
        public void valueChanged(ListSelectionEvent event) {
        

            try {
                ManuSelectedInt = manuSearchResults.getSelectedRow();
                System.out.println("Select manufacturer Table Index: " + ManuSelectedInt);
                sRManuEditButton.setEnabled(true);
                sRPmanuDeleteButton.setEnabled(true);
                manuSelected = manuStorage[ManuSelectedInt];
            } catch (Exception e) {
                //this catch is is caused by the new model being passed in, it is not
                // true ERROR, just the way the table listener recongizes data changes.
                //no true error happens here!
            }

        }
    }



    //listener for the selection of the product table row
     private class selectProductTableListener implements ListSelectionListener {
        @Override
        public void valueChanged(ListSelectionEvent event) { 
        
            try {
                prodSelectedInt = productSearchResults.getSelectedRow();
                System.out.println("Select product Table Index: " + prodSelectedInt);
                sRProductEditButton.setEnabled(true);
                sRProductDeleteButton.setEnabled(true);
                prodSelected = prodStorage[prodSelectedInt];
            }catch (Exception e) {
            //an index out of bounds happens due to a change that this causes to the 
            //listener and table changes. 
            }
        }
    }

     /*
     * Listens for changes in the selection of the rows of the Jtable and allows
     * the editing of the information.
     */
    private class empJTableListSelectionListener implements ListSelectionListener {
        @Override
        public void valueChanged(ListSelectionEvent event) {
            try {
            System.out.println("This is Done: " + searchResultsTable.getSelectedRow());
            selectedEmployee = employeeStorage[searchResultsTable.getSelectedRow()];
            System.out.println(selectedEmployee.getStatus());
            searchEmpEditButton.setEnabled(true);
            }catch(Exception e) {
                //index out of bounds happens here, it's not an error, just 
                //a problem with the listener and JTable never playing nice.
            }

        }
    }
    
    /**
     * for space saving on the main pannel, calls a pip u 
     */
    private class SelectForEditListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
             EditEmpWindow editWindow = new EditEmpWindow();
             editWindow.setVisible(true);
        }
    }
    /**
     * Creates a temp pop up for editing, non blocking. 
     */
    private class EditEmpWindow extends JFrame {
        
        public EditEmpWindow() {
            //searchEDITEmpIDTextBox.setText(selectedEmployee.get
            searchEDITFirstNameTextBox.setText(selectedEmployee.getFirstName());
            searchEDITLastNameTextBox .setText(selectedEmployee.getLastName());
            searchEDITPositionTextBox.setText(selectedEmployee.getPosition());
            searchEDITGenderTextBox.setText(selectedEmployee.getGender());
            //searchEDITDepartmentTextBox.setText(selectedEmployee);
            searchEDITSinTextBox.setText(String.valueOf(selectedEmployee.getSinNumber()));
            //searchEDITbirthDayTextBox.setText(selectedEmployee.get
            //searchEDITHireDateTextBox.setText(selectedEmployee
            searchEDITStatusTextBox.setText(selectedEmployee.getStatus());
            searchEDITPhoneTextBox .setText(selectedEmployee.getPhoneNumber());
            searchEDITAddressTextBox.setText(selectedEmployee.getAddress());
            this.add(searchEditMainMain);
            this.pack();
        }
    }
    
    /**
     * edit manufactuerer window. 
     */
    private class EditManufacturerWindow extends JFrame {
        
        public EditManufacturerWindow() {

            editManuNameTxt.setText(manuSelected.getName());
            editManuCountryTxt.setText(manuSelected.getCountry());
            editManuDescriptioneTxt.setText(manuSelected.getDescription());
            
            this.add(editManufacturerPanelMain);
            this.pack();
        }
    }
    /**
     * Creates the edit product window
     */
    private class EditProductWindow extends JFrame {
       public EditProductWindow () {
        
           editProductNametxt.setText(prodSelected.getName());
           editProductPricetxt.setText(String.valueOf(prodSelected.getPrice()));
           editProductionCosttxt.setText(String.valueOf(prodSelected.getCostVSProductionCost()));
           editProductDescriptiontxt.setText(prodSelected.getDescription());
           this.add(editProductPaneMain);
           
           this.pack();
           
       }
        //editProductPaneMain
    }
    
    /**
     * listens for calls on the update button. 
     */
    private class UpdateEmployeeButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            updateEmployeeStoredEmployee();
            
        }
    } 
    
    //calls the update manufacturer event
    private class UpdateManuMenuButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            updateStoredManufactuter();
            
        }
    }
    
    private class UpdateProdMenuButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            if (JOptionPane.showConfirmDialog(null, "Are you sure?", "Update?", JOptionPane.YES_NO_OPTION) == 0) {
                updateStoredProducts();
            }
            
        }
    }
          

    
    /**
     * listens for call on the delete button. 
     */
    private class DeleteEmployeeButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
           if (JOptionPane.showConfirmDialog(null, "Are you sure?", "Exit?", JOptionPane.YES_NO_OPTION) == 0) {
                deleteEmployee();
           }
        }
    }
    
    /**
     * Action listener for updating and searching the tables. 
     */
    public class SearchEmployeeButtonListener implements ActionListener {
        /***
         * private inner class with overwritten table to prevent editing.
         */

        private EmployeesIdentity empIden;
        //action listener's table
       
        @Override
        public void actionPerformed(ActionEvent event) {
            //call and execute the query 
            
                try {
                    //System.out.println("Hey I did fucking shit!");
                    DBConnection Conn = new DBConnection(); 
                    empIden = Conn.getEmployeeInformation(searchSetup(createEmpSearchCriteria(2), 
                            createEmpSearchCriteria(1), "gc200325005.Employee"));

                    searchResultsTable.setModel(empIden.getEmpTable());
                    employeeStorage = empIden.getEmpArrayReturn();
                    System.out.println("Size of Current employeeStorage array: "+ employeeStorage.length);
                    updateAddSalesButtonComboBozes();

            }catch (Exception e) {
                //errorWriter.writeError(e);
                System.out.println("Error!");
                System.out.println(e.getMessage());
                errorWriter.appendToFile("Error! " + e.getMessage());
            }
            searchEmpEditButton.setEnabled(false);
            
        }
        /**
         * 
         * @return the table model for the search results if needed. 
         */
        public DefaultTableModel returnMyTable() {
            return this.empIden.getEmpTable();
        }        
    }

    // event handlers
    // close form
    private class ExitButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent event) {
            // are you sure box
            if (JOptionPane.showConfirmDialog(null, "Are you sure?", "Exit?", JOptionPane.YES_NO_OPTION) == 0) {
                System.exit(0);
            }
        }
    }
    
    //EditManufacturerWindow
  private class showUpdateProductWindow implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            EditProductWindow editProdWindow = new EditProductWindow();
            
            editProdWindow.setVisible(true);
        }
    }
    
    
    private class showUpdateManufacturerWindow implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            EditManufacturerWindow editManuWindow = new EditManufacturerWindow();
            
            
            //fdsgsafrhfha
            editManuWindow.setVisible(true);
        }
    }

        //validates date inputes 
        public boolean checkValidDate (String Date) 
        {
            //Error on date Conversion: Unparseable date: "1988-10-10"

            try {
                DateFormat df = new SimpleDateFormat("YYYY-MM-DD");
                df.setLenient(true);
                df.parse(Date);
                return false;
            }
            catch (ParseException e) {
                System.out.println("Error on date Conversion: " + e.getMessage());
                errorWriter.appendToFile("Error on date Conversion: " + e.getMessage());
                return true;
            }
        }
        //as the C# try parse int method. 
        private boolean tryParseInt(String value) 
        {
            try {  
                Integer.parseInt(value);  
            return true;  
        
            } catch (NumberFormatException e) {  
                //no true error happens here.
            return false;  
            }  
        }
                //as the C# try parse int method. 
        private boolean DoubleTryParse(String value) 
        {
            try {  
                Double.parseDouble(value);  
            return true;  
        
            } catch (NumberFormatException e) {
                //no true error happens here.
            return false;  
            }  
        }

        
        //validates for date format, not if the date is 
        //valid for DB insert however (different format)
        //"dd-MM-yyyy" is format. 
        public boolean validateCreateEmployeeRequiredFieldsFilled()
        {
         if (
                    firstNameTxt.getText().equals("") ||  
                    lastNameTxt.getText().equals("") ||
                    empGenderTxt.getText().equals("") ||
                    empSinTxt.getText().equals("") ||
                    yearTxt.getText().equals("") ||
                    monthTxt.getText().equals("") ||
                    dayTxt.getText().equals("") ||
                    this.empPositionTxt.getText().equals("")
                 )
                    
            {
                JOptionPane.showMessageDialog(null,"You Cannot Submit to the database a new employee "
                        + "without hte folloing fields, First Name, Last Name, Gender, Sin Number, "
                        + "year of birth, month of birth, day of birth, and position","Submission Error",JOptionPane.WARNING_MESSAGE);
                return false; 
            }
         
         //field validations that are REQUIRED for all types. 
         if (this.checkValidDate(yearTxt.getText()+"-"+monthTxt.getText()+"-"+dayTxt.getText())){
             JOptionPane.showMessageDialog(null,"You must enter a valid date for employee birth format is YYYY, MM, DD in the seperate fields","Submission Error",JOptionPane.WARNING_MESSAGE);
                return false; 
         }
         if (empSinTxt.getText().length()!=9 && !tryParseInt(empSinTxt.getText())) {
             JOptionPane.showMessageDialog(null,"Sin number must be 9 digits long with no spaces, and contain no letter characters","Submission Error",JOptionPane.WARNING_MESSAGE);
                return false; 
         }
         //
         if (this.checkValidDate(emphireDateYearTxt.getText()+"-"+empHireDateMonthTxt.getText()+"-"+empHireDateDayTxt.getText())){
             JOptionPane.showMessageDialog(null,"You must enter a valid date for employee hire format is YYYY, MM, DD in the seperate fields, keep in mind this is not a required "
                     + "field","Submission Error",JOptionPane.WARNING_MESSAGE);
                return false; 
         }

         return true; 
         
        }
        

        /*
        validates the input for the create employee. 
        */
        public boolean validateInputTabe (int Tab) {
            if (empCreateGetIndexofTabPane == 0)
            {
                if(     this.DoubleTryParse(this.hourRateTxt.getText())
                        &&
                        this.DoubleTryParse(this.hoursTxt.getText())) {
                    return true;
                    
                }
                else {
                    JOptionPane.showConfirmDialog(null, "One of the fields in the pay field is entered incorrectly?", "OK", JOptionPane.OK_OPTION);
                    return false;
                }
            } else if (empCreateGetIndexofTabPane == 1)
            {
                if(     this.DoubleTryParse(this.baseSalaryTxt.getText())){
                    return true;
                    
                }
                else {
                    JOptionPane.showConfirmDialog(null, "One of the fields in the pay field is entered incorrectly?", "OK", JOptionPane.OK_OPTION);
                    return false;
                }
            } else if (empCreateGetIndexofTabPane == 2)
            {
                if(     this.DoubleTryParse(this.salesTxt.getText())
                        &&
                        this.DoubleTryParse(this.commissionRateTxt.getText())
                        &&
                        this.DoubleTryParse(this.baseSalaryTxt.getText())
                        ) {
                    return true;
                }
                else {
                    JOptionPane.showConfirmDialog(null, "One of the fields in the pay field is entered incorrectly?", "OK", JOptionPane.OK_OPTION);
                    return false;
                }
            }
            JOptionPane.showConfirmDialog(null, "Did you fill in the require fields?", "OK", JOptionPane.OK_OPTION);            return false ;
            
        }
        
        /**
         * Creates a new employee and submits it to the DB. 
         */
        public void createEmployeeAndSubmitToDB(){
            
            
            
            if ((validateCreateEmployeeRequiredFieldsFilled())&&(validateInputTabe(empCreateGetIndexofTabPane))) {
            
                double PayRate = 0;
                double hours = 0;
                double salary = 0;
                double sales = 0;
                double comrate = 0;
                double totalSalary = 0; //odd name, but used for naming variables
                
                if (empCreateGetIndexofTabPane==0) {
                    hours = Double.parseDouble(hourRateTxt.getText());
                    PayRate = Double.parseDouble(this.hourRateTxt.getText());
                } else if (empCreateGetIndexofTabPane==1) {
                    salary = Double.parseDouble(this.baseSalaryTxt.getText());
                }else if (empCreateGetIndexofTabPane==2) {
                    sales = Double.parseDouble(this.salesTxt.getText());
                    comrate = Double.parseDouble(this.commissionRateTxt.getText());
                    totalSalary = Double.parseDouble(this.baseSalaryTxt.getText());
                }
                
                        
                EmployeesIdentity empIden;
                DBConnection conn = new DBConnection();
                //prepInsertIntoEmployee
                //turnIntDataFieldsIntoSQLData
                //insertSQLDataBase
                conn.insertSQLDataBase("gc200325005.Employee", conn.prepInsertIntoEmployee(
                        this.empCreateGetIndexofTabPane, 
                        firstNameTxt.getText(), 
                        lastNameTxt.getText(), 
                        empGenderTxt.getText(), 
                        Integer.parseInt(empSinTxt.getText()), 
                        conn.turnIntDataFieldsIntoSQLDate(
                                Integer.parseInt(yearTxt.getText()), 
                                Integer.parseInt(monthTxt.getText()),
                                Integer.parseInt(dayTxt.getText())),
                        conn.turnIntDataFieldsIntoSQLDate(
                                Integer.parseInt(this.emphireDateYearTxt.getText()), 
                                Integer.parseInt(this.empHireDateMonthTxt.getText()),
                                Integer.parseInt(this.empHireDateDayTxt.getText())),
                        this.empPositionTxt.getText(),
                        this.empDepartmentTxt.getText(),
                        this.empStatusTxt.getText(),
                        this.addressTxt.getText(),
                        this.phoneNumberTxt.getText(),
                       0.00,
                       hours,
                       PayRate,
                       salary,
                       sales,
                       comrate,
                       totalSalary
                )
                      
                     

                );
                conn.getEmployeeInformation("SELECT * FROM gc200325005.Employee");
                empIden = conn.getEmployeeInformation(searchSetup(createEmpSearchCriteria(2), 
                            createEmpSearchCriteria(1), "gc200325005.Employee"));

                    searchResultsTable.setModel(empIden.getEmpTable());
                    employeeStorage = empIden.getEmpArrayReturn();
                    System.out.println("Size of Current employeeStorage array: "+ employeeStorage.length);
            
            }
        }

    //combo box listener. 
    private class manuComboBoxListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
                   manuSelectedID = manuComboBox.getSelectedIndex();
                   System.out.println("Current Selected manuComboBox Int: "+ manuSelectedID);
                }
        }
    
    //event to submit a new product.
    private class submitProductButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent event) {
            submitNewProduct();
            pullManufacturerAndProductFromDB(true);
        }
    }
    //event to submit a new manufacturer
    private class submitManufacturerButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent event) {
            if (JOptionPane.showConfirmDialog(null, "Are you sure?", "Exit?", JOptionPane.YES_NO_OPTION) == 0) {
                submitNewManufacturer();
                pullManufacturerAndProductFromDB(true); //to update the product field
            } 
        }
    }
    
    /**
     * submits a new manufacturer to the DB. 
     */
    private void submitNewManufacturer () {
        DBConnection conn = new DBConnection();
        if (!this.manuCreateNametxt.getText().equals("")){
            String Insert = conn.prepInsertIntoManufacturer(this.manuCreateNametxt.getText(), this.manuCreateCountrytxt.getText(), this.manuCreateDescriptiontxt.getText());
            conn.insertSQLDataBase("gc200325005.Manufacturers", Insert);
            JOptionPane.showMessageDialog(null,"You Have Successfully Created a new Manufacturer","Submission Complete",JOptionPane.WARNING_MESSAGE);
            updateManufacturerAndProductFromDB();
        } else {
            JOptionPane.showMessageDialog(null,"To create a manufacturer you need to at least enter a name","Submission Error",JOptionPane.WARNING_MESSAGE);
        }
        
    }
    
    /**
     * submits a new product to the DB
     */
    private void submitNewProduct () {
        DBConnection conn = new DBConnection();
        if (this.submitNewProductValidate()) {
            String insert = conn.prepInsertIntoProduct(this.prodCreateNameTxt.getText(), Double.parseDouble(this.prodCreatePriceTxt.getText()), 
                    Double.parseDouble(this.prodCreateProductionCostTxt.getText()), this.manuSelectedID, 
                    Integer.parseInt(this.prodCreateRatingTxt.getText()), this.prodCreateDescriptionTxt.getText());
            conn.insertSQLDataBase("gc200325005.Products", insert);
            JOptionPane.showMessageDialog(null,"You Have Successfully Created a new product!","Submission Complete",JOptionPane.WARNING_MESSAGE);
            updateManufacturerAndProductFromDB();
        }
    }
    
    //submits a new product to te DB
    private boolean submitNewProductValidate() {
        if (this.prodCreateNameTxt.getText().equals("")){
            JOptionPane.showMessageDialog(null,"All Products Must have a name, price, product cost and rating! (Manufacture should be set as well to the prefered value)","Submission Error",JOptionPane.WARNING_MESSAGE);
            return false; 
        }
        if (!this.DoubleTryParse(this.prodCreatePriceTxt.getText()) || !this.DoubleTryParse(this.prodCreateProductionCostTxt.getText())) {
            JOptionPane.showMessageDialog(null,"All values in the Price and productcost field must be valid money values!","Submission Error",JOptionPane.WARNING_MESSAGE);
            return false; 
        }
        if (!this.tryParseInt(this.prodCreateRatingTxt.getText())){
            JOptionPane.showMessageDialog(null,"All ratting values must be a whole number!","Submission Error",JOptionPane.WARNING_MESSAGE);
            return false; 
        }
            
        return true;
    }
    
    
    //salestab 
    //labels
    private JLabel salesCommLbl,salesProductLbl,salesEmpLbl;
    //text box
    //private JTextField salesCommTxt = new JTextField();
    private void buildSalesTab() {
        salesTab = new JPanel();
        salesTab.setLayout(new BorderLayout());
        this.SalesResultsPanel = new JPanel();
        saleResultsTable = new JTable(this.saleResultsDefaultTableModel);
        //saleResultsTable.setModel(this.saleResultsDefaultTableModel);
        updateAddSalesButtonComboBozes(); 
        salesResultsScrollTable = new JScrollPane(saleResultsTable);
        
        salesPanel = new JPanel();
        salesPanel.setLayout(new GridLayout(5,1));
        salesPanel.setBorder(BorderFactory.createTitledBorder("Add new Sale"));
        
        salesSearchSubPanel = new JPanel();
        addSalesBtn = new JButton("Add sale");
        addSalesBtn.addActionListener(new addSaleBtnListener());
        salesSearchSubPanel.add(addSalesBtn);
 
        
        
        salesProductLbl = new JLabel("Choose Product");
        salesEmpLbl = new JLabel("Choose Employee");
        salesCommLbl = new JLabel("Sale commission");
        //salesCommTxt = new JTextField(5);
        //salesCommTxt.setEditable(false);
        

                
            

        
        //this.saleResultsTable.doLayout(); //auto adusts the table. 
        
        System.out.println(this.saleResultsDefaultTableModel.getDataVector().size());
        this.buildSearchPanelSelection();
        //add the components to the results set panel
        //this.salesResultsScrollTable = new JScrollPane(this.saleResultsTable); 
        
        
        
        SalesResultsPanel.add(salesResultsScrollTable);
        //this.searchResultsPanel.add(this.searchEditMainMain, BorderLayout.CENTER);
        salesPanel.add(salesProductLbl);
        salesPanel.add(salesEmpLbl);
        salesPanel.add(salesCommLbl);
        salesPanel.add(salesProductList);
        salesPanel.add(salesEmpLbl);
        salesPanel.add(salesEmp);
        
        //salesPanel.add(salesCommLbl);
        //salesPanel.add(salesCommTxt);
        
        
        salesTab.add(SalesResultsPanel, BorderLayout.EAST);
        salesTab.add(salesPanel, BorderLayout.CENTER);
        salesTab.add(salesSearchSubPanel, BorderLayout.SOUTH);
    }
    
    
        private class addSaleBtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            //InsertSaleIntoDB(); 
            InserSaleIntoDB();
            PullSalesValues();
            }
        }

        //due to the simplicity of inserting a sale, this code 
        // simply creates the informatin. 
        private void InserSaleIntoDB (){
            String insertStatement 
                    ="(EMPID, SALESPERSONFULLNAME, PRODUCTID, PRODUCTNAME, SALEVALUE) " +
                    "VALUES (" + this.employeeStorage[this.salesEmp.getSelectedIndex()].getEmpId()+",'" 
                    + this.employeeStorage[this.salesEmp.getSelectedIndex()].getFullName() + "',"
                    + this.prodStorage[this.salesProductList.getSelectedIndex()].getProductID() + ",'"
                    + this.prodStorage[this.salesProductList.getSelectedIndex()].getName() + "',"
                    + this.prodStorage[this.salesProductList.getSelectedIndex()].getCostVSProductionCost() + ");";
                    
            DBConnection conn = new DBConnection();
            conn.insertSQLDataBase("gc200325005.FINALSALES", insertStatement);
        }
        
         /**
         * updates the combo boxes on data changes to the employee or
         * product information. 
         */
        private void updateAddSalesButtonComboBozes () {
                    //salesProductList = new JComboBox<String>();
                    //salesEmp = new JComboBox<String>();
                    
            for (int k =0; k < this.prodStorage.length; k++)
            {
                salesProductList.addItem(prodStorage[k].getName());
            }
            for (int k=0; k< this.employeeStorage.length;k++){
                salesEmp.addItem(employeeStorage[k].getFirstName());
            }
        }
        
        private void PullSalesValues () {
            DBConnection conn = new DBConnection();
            salesIdenity = conn.getSalesInformation("SELECT * FROM gc200325005.FINALSALES;");
            saleResultsDefaultTableModel = salesIdenity.getSalesTable();
            saleResultsTable.setModel(salesIdenity.getSalesTable());
        }
    
}
