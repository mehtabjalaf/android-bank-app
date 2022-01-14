package eecs1022.lab7.bank.model;

import android.annotation.SuppressLint;

public class Transaction {

    String transactionType;
    double amount;

    public Transaction(String type, double amount) {
        this.transactionType = type;
        this.amount = amount;
    }


    public String getStatus(){
        return String.format("Transaction %s: $%.2f", this.transactionType, this.amount);
    }



}
