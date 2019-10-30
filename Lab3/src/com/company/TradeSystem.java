package com.company;

import java.util.ArrayList;

public class TradeSystem {
    public ArrayList<Client> users;
    public ArrayList<Request> sellerRequests;
    public ArrayList<Request> buyerRequests;
    private Client currentUser;

    void addUser(String n, String l, String p){
        users.add(new Client(n, l, p));
    }

    boolean findUser(String l, String p){
        for (Client person : users) {
            if (person.enter(l, p)){
                currentUser = person;
                return true;
            }
        }
        return false;
    }

    void processRequests(){
        sellerRequests.sort(Request.priceComparator);
        buyerRequests.sort(Request.priceComparator);
    }

    public Client getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(Client currentUser) {
        this.currentUser = currentUser;
    }
}
