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

    public String getNumberOfAddress() {
        return numberOfAddress;
    }

    public String getCityOfAdress() {
        return cityOfAdress;
    }

    @Override
    public String toString() {
        return "Rua " + streetOfAdress + ", " + numberOfAddress + ", " + cityOfAdress;
    }
}
