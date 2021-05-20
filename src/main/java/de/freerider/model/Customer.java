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
    private enum Status {NEW;}
    private Status status;
    
    
    
   public Customer (String lastName,String firstName,String contact) {
       
       this.id = null;
       this.lastName = lastName;
       this.firstName = firstName;
       this.contact = contact;
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
   public void setId(String newId) {
    this.id = newId;
}
    
   public void setLastName(String newLastName) {
    this.lastName = newLastName;
}
   
   public void setFirstName(String newFirstName) {
    this.firstName = newFirstName;
}
   
   public void setContact(String newContact) {
    this.contact = newContact;
}
   
   
   
}
