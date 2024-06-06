package com.pjatk.bank;

import java.util.ArrayList;
import java.util.HashMap;

public class BankAccountManager {
    private BankAccount bankAccount;
    private ArrayList<BankAccountOperation> historyOfTransactions = new ArrayList<>();
    public void addAccount(BankAccount konto) {
        this.bankAccount = konto;
    }

    public void registerOperation(BankAccountOperation operation) {
        historyOfTransactions.add(operation);
    }

    public void performDeposits() {
        for(BankAccountOperation bankAccountOperation : historyOfTransactions){
            if(bankAccountOperation.bankOperationType == BankOperationType.DEPOSIT){
                bankAccountOperation.sourceBankAcoount.setCurrentMoney(bankAccountOperation.money);
            }
        }
    }

    public void performWithdrawals() {
        for(BankAccountOperation bankAccountOperation : historyOfTransactions){
            if(bankAccountOperation.bankOperationType == BankOperationType.WITHDRAWAL){
                bankAccountOperation.sourceBankAcoount.setCurrentMoney(-bankAccountOperation.money);
            }
        }
    }

    public void performTransfers() {
        for(BankAccountOperation bankAccountOperation : historyOfTransactions){
            if(bankAccountOperation.bankOperationType == BankOperationType.TRANSFER){
                bankAccountOperation.sourceBankAcoount.setCurrentMoney(-bankAccountOperation.money);
                bankAccountOperation.targetBankAccount.setCurrentMoney(bankAccountOperation.money);
            }
        }
    }
}
