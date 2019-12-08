package com.example.roomatebillsplitter;

public class BillSplitter {
    // List of roommates
    // Relationship ties
    // Calculate splits
    // Expandable listView

    public BillSplitter() {

    }

    public double splitRent(double rent, int roommates) {
        return (rent / roommates);
    }

    public double splitElectricity(double electricity, int roommates) {
        return (electricity / roommates);
    }

    public double splitWater(double water, int roommates){
        return (water / roommates);
    }

    public double splitInternet(double internet, int roommates){
        return (internet / roommates);
    }

    public double splitGroceries(double groceries, int roommates){
        return (groceries / roommates);
    }
}
