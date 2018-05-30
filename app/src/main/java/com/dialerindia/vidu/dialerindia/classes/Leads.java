package com.dialerindia.vidu.dialerindia.classes;

public class Leads {
    public String Name;
    public int id;
    public String Contact1;
    public String Contact2;
    public String Email;
    public String Address;
    public String City;
    public String Group;
    public boolean Pending = true;
    public boolean Missed = false;

    public Leads(String Name, String Contact1, String Contact2, String Email, String Address, String City, String Group){
        this.Name = Name;
        this.Contact1 = Contact1;
        this.Contact2 = Contact2;
        this.Email = Email;
        this.Address = Address;
        this.City = City;
        this.Group = Group;
    }

    public void setPending(boolean pending) {
        Pending = pending;
    }

    public void setMissed(boolean missed) {
        Missed = missed;
    }

}
