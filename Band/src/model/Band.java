package model;

import java.util.ArrayList;
import java.util.List;

public class Band {

    private String name;
    private double totalMoney;
    private double currentMoney;
    private List<String> members;
    private int numShowsPlayed;

    public Band(String name) {
       this.name = name;
       numShowsPlayed = 0;
       members = new ArrayList<>();
    }

    // getters
    public String getName() {
        return name;
    }
    public double getTotalMoney() {
        return totalMoney;
    }
    public double getCurrentMoney() {
        return currentMoney;
    }
    public List<String> getMembers() {
        return members;
    }
    public int getNumShowsPlayed() {
        return numShowsPlayed;
    }

    // MODIFIES: this
    // EFFECTS: adds a member with the given name to the band
    public void addMember(String name) {
        if(!members.contains(name)) {
            members.add(name);
        }
    }

    // MODIFIES: this
    // EFFECTS: given an amount the band will be played for the show, update
    //          the current money and total money by the amount, and add one to
    //          the number of shows played.
    public void playGig(double amt) {
        currentMoney += amt;
        totalMoney += amt;
        numShowsPlayed ++;

    }

    // MODIFIES: this
    // EFFECTS: pays the given amount to each band member, the total paid
    //          is subtracted from the current money. If there is not enough
    //          money, nothing happens
    public void payMembers(double amt) {
       double needToPay = amt * members.size();
       if(needToPay <= currentMoney) {
           currentMoney -= needToPay;
       }
    }

    // REQUIRES: numShowsPlayed > 0
    // MODIFIES: nothing
    // EFFECTS: computes the average amount the band is payed per show
    public double averagePerShow() {
       return totalMoney / numShowsPlayed;
    }


}
