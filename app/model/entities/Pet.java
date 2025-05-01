package app.model.entities;

import app.model.enums.Sex;
import app.model.enums.Type;

public class Pet {
    private String name, breed, age, weight;

    private Sex sex;
    private Type type;
    private Address address;

    public Pet() {
    }

    public Pet(String name, Type type, Sex sex, Address address,
               String age, String weight, String breed) {
        this.name = name;
        this.type = type;
        this.sex = sex;
        this.address = address;
        this.age = age;
        this.weight = weight;
        this.breed = breed;
    }

    public String getName() {
        return name;
    }

    public Type getType() {
        return type;
    }

    public Sex getSex() {
        return sex;
    }

    public Address getAddress() {
        return address;
    }

    public String getAge() {
        return age;
    }

    public String getWeight() {
        return weight;
    }

    public String getBreed() {
        return breed;
    }

    @Override
    public String toString() {
        return "Pet{" +
                "name='" + name + '\'' +
                ", type=" + type +
                ", sex=" + sex +
                ", address=" + address +
                ", age=" + age +
                ", weight=" + weight +
                ", breed='" + breed + '\'' +
                '}';
    }
}
