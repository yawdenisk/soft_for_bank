package com.pjatk.bank;

import java.time.LocalDateTime;

public class BankAccountOperation {
    public BankOperationType bankOperationType;
    public BankAccount sourceBankAcoount;
    public BankAccount targetBankAccount;
    public double money;
    private LocalDateTime timeOfOperation;

    public BankAccountOperation(BankOperationType bankOperationType) {
        this.bankOperationType = bankOperationType;
    }

    public void setSourceBankAccount(BankAccount bankAccount) {
        this.sourceBankAcoount = bankAccount;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public void setDate(LocalDateTime timeNow) {
        this.timeOfOperation = timeNow;
    }

    public void setTargetBankAccount(BankAccount konto) {
        this.targetBankAccount = konto;
    }
}
