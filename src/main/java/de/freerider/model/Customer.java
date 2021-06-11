/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package de.freerider.model;

/**
 *
 * @author ykohn
 */
public class Customer {
    private String id;
    private String lastName;
    private String firstName;
    private String contact;
    public enum Status {NEW, ACTIVE, DELETED;}
    private Status status;
    
    
    
    public Customer () {
        
        this.id = null;
        this.lastName = "";
        this.firstName = "";
        this.contact = "";
        this.status = Status.NEW;
    }
    
    
    //GETTERS
    public String getId() {
        return this.id;
    }
    
    public String getLastName() {
        return this.lastName;
    }
    
    public String getFirstName() {
        return this.firstName;
    }
    
    public String getContact() {
        return this.contact;
    }
    
    
    //SETTERS
    public boolean setId(String newId) {
        if(newId == null) {
            this.id = newId;
            return true;
        }
        if(this.id == null ){
            this.id = newId;
            return true;
        } else {
            return false;
        }
        
    }
    
    public boolean setLastName(String newLastName) {
        if(newLastName == null) {
            this.lastName = "";
            return true;
        }
        else {
            this.lastName = newLastName;
            return true;
        }
    }
    
    public boolean setFirstName(String newFirstName) {
        if(newFirstName == null) {
            this.firstName = "";
            return true;
        }
        else {
            this.firstName = newFirstName;
            return true;
        }
    }
    
    
    public boolean setContact(String newContact) {
        if(newContact == null) {
            this.contact = "";
            return true;
        }
        else {
            this.contact = newContact;
            return true;
        }
    }
    
    public Status getStatus() {
        return this.status;
            }
    
    public void setStatus(Status status) {
        this.status = status;
    }
    
    
    
}
