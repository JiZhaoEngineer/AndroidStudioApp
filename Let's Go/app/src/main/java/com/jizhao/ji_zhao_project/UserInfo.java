package com.jizhao.ji_zhao_project;

import java.io.Serializable;

public class UserInfo implements Serializable {

    //Fields for UserInfo Class
    private int _id;
    private String name;
    private String email;
    private String phone;//always save as string, not int. If phone starts with 0, 0 will not be showed.
    private String password;
    private String address;
    private String city;
    private String state;
    private String zip;
    /**
     * Constructor for userInfo class
     * @param idIn
     * @param nameIn
     * @param emailIn
     * @param phoneIn
     * @param passwordIn
     * @param addressIn
     * @param cityIn
     * @param stateIn
     * @param zipIn
     */
    //need constructor
    public UserInfo(int idIn, String nameIn, String emailIn, String phoneIn, String passwordIn, String addressIn,
                    String cityIn, String stateIn, String zipIn){
        this._id = idIn;
        this.name = nameIn;
        this.phone = phoneIn;
        this.email = emailIn;
        this.password = passwordIn;
        this.address = addressIn;
        this.city = cityIn;
        this.state = stateIn;
        this.zip = zipIn;
    }

    //set/get value to all variables
    public void setId (int newId) {
        this._id = newId;
    }
    public int getId() {
        return _id;
    }

    public void setName (String newName) {
        this.name = newName;
    }
    public String getName() {
        return name;
    }

    public void setEmail (String newEmail) {
        this.email = newEmail;
    }
    public String getEmail() {
        return email;
    }

    public void setPhone (String newPhone) {
        this.phone = newPhone;
    }
    public String getPhone() {
        return phone;
    }

    public void setPassword (String newPassword) {
        this.password = newPassword;
    }
    public String getPassword() {
        return password;
    }

    public void setAdd (String newAdd) {
        this.address = newAdd;
    }
    public String getAdd() {
        return address;
    }

    public void setCity (String newCity) {
        this.city = newCity; }
    public String getCity() {
        return city;
    }

    public void setState (String newState) {
        this.state = newState;
    }
    public String getState() {
        return state;
    }

    public void setZip (String newZip) {
        this.zip = newZip;
    }
    public String getZip() {
        return zip;
    }

}
