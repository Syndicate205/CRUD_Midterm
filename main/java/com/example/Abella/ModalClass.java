package com.example.Abella;

public class ModalClass {

    private String name;
    private String age;
    private String gender;
    private String address;
    private String status;
    private int id;

//Getter and Setter sa mga fields
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }
    public void setAge(String age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

//Constructor
    public ModalClass(String name, String age, String gender, String address, String status) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.address = address;
        this.status = status;
    }
}
