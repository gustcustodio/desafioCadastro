package app.model.entities;

public class Address {
    private Integer numberOfAddress;
    private String cityOfAdress, streetOfAdress;

    public Address(String streetOfAdress, Integer numberOfAddress, String cityOfAdress) {
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

    public Integer getNumberOfAddress() {
        return numberOfAddress;
    }

    public void setNumberOfAddress(Integer numberOfAddress) {
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
        return "Address{" +
                "streetOfAdress='" + streetOfAdress + '\'' +
                ", numberOfAddress=" + numberOfAddress +
                ", cityOfAdress='" + cityOfAdress + '\'' +
                '}';
    }
}
