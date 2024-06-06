package com.pjatk.bank;

public class BankAccount {
    private double currentMoney;
    public BankAccount(String accountNumber) {
    }

    public double getCurrentMoney() {
        return currentMoney;
    }

    public void setCurrentMoney(double currentMoney) {
        this.currentMoney += currentMoney;
    }
}
