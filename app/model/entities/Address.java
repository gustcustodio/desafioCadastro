package app.model.entities;

public class Address {
    private String cityOfAdress, numberOfAddress, streetOfAdress;

    public Address(String streetOfAdress, String numberOfAddress, String cityOfAdress) {
        this.streetOfAdress = streetOfAdress;
        this.numberOfAddress = numberOfAddress;
        this.cityOfAdress = cityOfAdress;
    }

    public String getStreetOfAdress() {
        return streetOfAdress;
    }

    public void setStreetOfAdress(String streetOfAdress) {
        this.streetOfAdress = streetOfAdress;
    }

    public String getNumberOfAddress() {
        return numberOfAddress;
    }

    public void setNumberOfAddress(String numberOfAddress) {
        this.numberOfAddress = numberOfAddress;
    }

    public String getCityOfAdress() {
        return cityOfAdress;
    }

    public void setCityOfAdress(String cityOfAdress) {
        this.cityOfAdress = cityOfAdress;
    }

    @Override
    public String toString() {
        return streetOfAdress + ", " + numberOfAddress + ", " + cityOfAdress;
    }
}
