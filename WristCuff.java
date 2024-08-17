package sakinana_CSCI201_Assignment1;

import java.io.BufferedReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.annotations.SerializedName;

public class WristCuff {

	@SerializedName("chiralFrequency")
	public int chiralFrequency;
	
	@SerializedName("timefall")
	public boolean timefall;
	
	@SerializedName("guid")
	public String guid;
	
	@SerializedName("name")
	public String name;
	
	@SerializedName("phone")
	public String phone;
	
	@SerializedName("address")
	public String address;
	
    //set initial values for needed members
    public WristCuff(int chiralFrequency, boolean timefall, String guid, String name, String phone, String address) {
    	this.chiralFrequency = chiralFrequency;
    	this.timefall = timefall;
    	this.guid = guid;
    	this.name  = name;
    	this.phone = phone;
    	this.address = address;
    }
	public WristCuff() {
		
	}
    
    public int getCF() {
    	return chiralFrequency;
    }
    public boolean getTimeFall() {
    	return timefall;
    }
    public String getGuid() {
    	return guid;
    }
    public String getName() {
    	return name;
    }
    public String getPhone() {
    	return phone;
    }
    public String getAddress() {
    	return address;
    }
    
    public void setCF(int chiralFrequency) {
    	this.chiralFrequency = chiralFrequency;
    }
    public void setTimeFall(boolean timefall) {
    	 this.timefall = timefall;
    }
    public void setGuid(String guid) {
    	this.guid = guid;
    }
    public void setName(String name) {
    	this.name = name;
    }
    public void setPhone(String phone) {
    	this.phone = phone;
    }
    public void setAddress(String address) {
    	this.address = address;
    }
    
    /**
     * List all available shelters within the min and max of supported Chiral frequencies
     */
    /*void listAllShelters(Set<Integer> chiralFrequencies) {

    }


    /**
     * Functions for:
     * Search for a shelter by Chiral frequency
     * Search for a shelter by name
     */


    /**
     * Find an available shelter with the lowest supported Chiral frequency
     */
    /*public TimefallShelter findShelter(ArrayList<Integer> chiralFrequencies) {

    }*/



    /**
     * Sort shelters by Chiral frequency
     */
   /* public void sortShelters() throws FileNotFoundException {

    }

    /**
     * Saves the updated list of shelters to disk
     */
    /*public void save() throws FileNotFoundException {

    }*/
}
