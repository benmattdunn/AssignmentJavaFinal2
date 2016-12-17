package Manufacturers;

public class Manufacturer {

    private final int _manufacturerID;
    private String _name, _country, _description;

    public Manufacturer(int manufacturerID, String name, String country, String description) {
        this._manufacturerID = manufacturerID;
        this._name = name;
        this._country = country;
        this._description = description;
    }

    //accessors
    public String getName() {
        return _name;
    }
    
    public String getCountry() {
        return _country;
    }

    public String getDescription() {
        return _description;
    }
    
    public int getManufacturerID() {
        return _manufacturerID;
    }

    //mutators
    public void setName(String _name) {
        this._name = _name;
    }
    
    public void setCountry(String _country) {
        this._country = _country;
    }

    public void setDescription(String _description) {
        this._description = _description;
    }

}
