package com.example.chefgo.DomainObjects;
/**
 * @author SB_3
 *
 */

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class UsersDomain implements Parcelable{
    /**
     * username of user
     */
    private String username;
    /**
     * email of user
     */
    private String email;
    /**
     * name of user
     */
    private String name;
    private String password;
    /**
     * usertype of user
     */
    private Integer userType;
    private Double rating;
    private String address;
    private String state;
    /**
     * zip of user
     */
    private Integer zip;

    public UsersDomain(){

    }

    /**
     *
     * @param username username of user
     * @param email email of user
     * @param n name of user
     * @param pass password of user
     * @param rating rating of user
     * @param type tpye of user
     * @param address address of user
     * @param state state of user
     * @param zip zip of user
     */
    public UsersDomain(String username, String email, String n, String pass,  Double rating,
                 Integer type, String address, String state, Integer zip) {
        this.username = username;
        this.email = email;
        this.name = n;
        this.password = pass;
        this.userType = type;
        this.rating = rating;
        this.address = address;
        this.state = state;
        this.zip = zip;
    }
    protected UsersDomain(Parcel in){
        this.username = in.readString();
        this.email = in.readString();
        this.name= in.readString();
        this.password = in.readString();
        this.userType = in.readInt();
        this.rating = in.readDouble();
        this.address = in.readString();
        this.state = in.readString();
        this.zip = in.readInt();
    }
    public static final Parcelable.Creator<UsersDomain> CREATOR = new Parcelable.Creator<UsersDomain>() {
        @Override
        public UsersDomain createFromParcel(Parcel parcel) {
            return new UsersDomain(parcel);
        }

        @Override
        public UsersDomain[] newArray(int i) {
            return new UsersDomain[i];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel in, int i) {
        in.writeString(this.username) ;
        in.writeString(this.email);
        in.writeString(this.name);
        in.writeString(this.password);
        in.writeInt(this.userType);
        in.writeDouble(this.rating);
        in.writeString(this.address);
        in.writeString(this.state);
        in.writeInt(this.zip);
    }

    /**
     *
     * @return  usertype of user 0 = admin 1 = customer 2 = cheg
     */
    public Integer getUserType() {
        return this.userType;
    }

    /**
     *
     * @param user sets usertype of user
     */
    public void setUserType(Integer user) {
        this.userType = user;
    }

    /**
     *
     * @return name of user
     */
    public String getName() {
        return this.name;
    }

    /**
     *
     * @param name sets name of user
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return username of user
     */
    public String getUsername() {
        return this.username;
    }

    /**
     *
     * @param name sets username of user
     */
    public void setUsername(String name) {
        this.username = name;
    }

    /**
     *
     * @return password of user
     */
    public String getPassword() {
        return this.password;
    }

    /**
     *
     * @param pass sets password of user
     */
    public void setPassword(String pass) {
        this.password = pass;
    }

    /**
     *
     * @return rating
     */
    public Double getRating() {
        return this.rating;
    }

    /**
     *
     * @param r sets rating
     */
    public void setRating(Double r) {
        this.rating = r;
    }

    /**
     *
     * @return address of user
     */
    public String getAddress() {
        return this.address;
    }

    /**
     *
     * @param address sets address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     *
     * @return states
     */
    public String getState() {
        return this.state;
    }

    /**
     *
     * @param state sets state
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     *
     * @param zip sets zip
     */
    public void setZip(Integer zip) {
        this.zip = zip;
    }

    /**
     *
     * @return zip
     */
    public Integer getZip() {
        return this.zip;
    }

    /**
     *
     * @return email
     */
    public String getEmail() {
        return this.email;
    }

    /**
     *
     * @param email sets email
     */
    public void setEmail(String email){
        this.email = email;
    }

    /**
     *
     * @return json list of user object
     */
    public Map<String, String> toJSON(){
        Map<String, String> map = new HashMap<>();
            map.put("zip",getZip().toString());
            map.put("state",getState());
            map.put("address",getAddress());
            map.put("rating", getRating().toString());
            map.put("userType",getUserType().toString());
            map.put("password", getPassword());
            map.put("name", getName());
            map.put("email", getEmail());
            map.put("username", getUsername());


        return map;
    }
}

