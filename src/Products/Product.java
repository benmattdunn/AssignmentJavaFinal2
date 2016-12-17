package Products;

import Manufacturers.Manufacturer;

public class Product {

    private int _productID, _manuID = -1; 
    private double _price, _rating, _productionCost;
    private String _description, _name;
    
    private Manufacturer _manufacturer;

    /**
     * Short constructor for general products.
     *
     * @param productID
     * @param name
     * @param price
     * @param productionCost
     * @param manufacturer
     */
    public Product(int productID, String name, double price, double productionCost, Manufacturer manufacturer) {
        this._productID = productID;
        this._name = name;
        this._price = price;
        this._productionCost = productionCost;
        this._manufacturer = manufacturer;
        this._manuID = manufacturer.getManufacturerID(); 
    }
    
    public Product(int productID, String name, double price, double productionCost, int manuID) {
        this._productID = productID;
        this._name = name;
        this._price = price;
        this._productionCost = productionCost;
        //this._manufacturer = manufacturer;
        this._manuID = manuID;
        
    }
    
    


    /**
     * Extended constructor for added functionality.
     *
     * @param productID
     * @param name
     * @param price
     * @param productionCost
     * @param manufacturer
     * @param rating
     * @param description
     */
    public Product(int productID, String name, double price, double productionCost, Manufacturer manufacturer,
            double rating, String description) {
        this(productID, name, price, productionCost, manufacturer);
        this._description = description;
        this._rating = rating;
    }
    
    public Product(int productID, String name, double price, double productionCost, int manuID,
            double rating, String description) {
        this(productID, name, price, productionCost, manuID);
        this._description = description;
        this._rating = rating;
    }

    
    
    // generation auto code shit.
    public int getManuID() {
        return _manuID;
    }

    
    
    // accessors
    public double getPrice() {
        return _price;
    }

    public double getRating() {
        return _rating;
    }

    public double getProductionCost() {
        return _productionCost;
    }

    public String getDescription() {
        return _description;
    }

    public String getName() {
        return _name;
    }

    public Manufacturer getManufacturer() {
        return _manufacturer;
    }
    
    public int getProductID() {
        return _productID;
    }

    public void setPrice(double _price) {
        this._price = _price;
    }

    // mutator Methods
    public void setRating(double _rating) {
        this._rating = _rating;
    }

    public void setProductionCost(double _productionCost) {
        this._productionCost = _productionCost;
    }

    public void setDescription(String _description) {
        this._description = _description;
    }

    public void setName(String _name) {
        this._name = _name;
    }

    public void setManufacturer(Manufacturer _manufacturer) {
        this._manufacturer = _manufacturer;
    }

    /**
     * @return a double of the price - production cost;
     */
    public double getCostVSProductionCost() {

        return this._price - this._productionCost;
    }

    @Override
    /**
     * returns a string representing the product;
     *
     * @return a string representing the product
     */
    public String toString() {

        return " product Name: " + this._name + " \n"
                + "Manufacture: " + this._manufacturer.toString() + " \n"
                + "Price: " + this._price + " \n"
                + "Production Cost: " + this._productionCost + " \n";

    }

}