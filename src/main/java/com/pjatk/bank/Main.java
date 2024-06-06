package com.pjatk.bank;

import java.time.LocalDateTime;

public class Main {

    public static void main(String[] args){

        /**
         * Utwórzę kilka przykładowych kont bankowych
         */
        String accountNumber1 = "PL 19 1240 6957 6648 5356 2227 1000";
        String accountNumber2 = "PL 96 1030 1090 1009 2214 7993 1993";
        String accountNumber3 = "PL 95 9279 0007 4775 7001 9528 9787";

        /**
         * Utwórzę klasę 'BankAccount' która ma konstruktor
         * uzupełaniający pole numeru bankowego 'accountNumber'
         */
        BankAccount kontoJanusza = new BankAccount(accountNumber1);
        BankAccount kontoZdzislawa = new BankAccount(accountNumber2);
        BankAccount kontoGrazyny = new BankAccount(accountNumber3);

        /**
         * Utwórzę klasę 'BankAccountManager', która będzie zarządzać kontami bankowymi,
         * oraz wykonwyać różne operacje na nich
         */
        BankAccountManager bankAccountManager = new BankAccountManager();

        /**
         * obiekt klasy 'BankAccountManager' będzie mieć metodę
         * dodawania kont bankowych do kolekcji, którą będzie zarządzać
         */
        bankAccountManager.addAccount(kontoGrazyny);
        bankAccountManager.addAccount(kontoJanusza);
        bankAccountManager.addAccount(kontoZdzislawa);

        /**
         * utwórzę klasę 'BankAccountOperation', która będzie definiować typ operacji
         * na kontach bankowych.
         *
         * Klasa będzie mieć konstruktor,
         * który jako wartość przyjmuje wartość typu wyliczeniowego enum 'BankOperationType':
         *  - DEPOSIT
         *  - WITHDRAWAL
         *  - TRANSFER
         */
        BankAccountOperation depositJanusza = new BankAccountOperation(BankOperationType.DEPOSIT);

        /**
         * Depozyt:
         * -> określamy na które konto wpłacamy środki,
         * -> okreslamy ilość pieniedzy
         * -> określamy date i czas wpłaty środków
         */


        depositJanusza.setSourceBankAccount(kontoJanusza);
        depositJanusza.setMoney(1000.00);
        depositJanusza.setDate(LocalDateTime.now());

        BankAccountOperation depositGrazyny = new BankAccountOperation(BankOperationType.DEPOSIT);
        depositGrazyny.setSourceBankAccount(kontoGrazyny);
        depositGrazyny.setMoney(1300);
        depositGrazyny.setDate(LocalDateTime.now());

        BankAccountOperation depositZdzislawa = new BankAccountOperation(BankOperationType.DEPOSIT);
        depositZdzislawa.setSourceBankAccount(kontoZdzislawa);
        depositZdzislawa.setMoney(900);
        depositZdzislawa.setDate(LocalDateTime.now());


        /**
         * Zdefiniowane wpłaty środków rejestrujemy
         * w obiekcie do zarządzania kontami
         */
        bankAccountManager.registerOperation(depositGrazyny);
        bankAccountManager.registerOperation(depositJanusza);
        bankAccountManager.registerOperation(depositZdzislawa);

        /**
         * obiekt zarządzający ma wykonać wpłaty środków na konta
         * -> tutaj ma nastąpić zmiana wartości pieniężnych na kontach
         */
        bankAccountManager.performDeposits();

        /**
         * wyświetlam aktualne stany kont
         */
        System.out.println("======== Wpłaty ========");
        System.out.println("Stan konta  Janusza: " +  kontoJanusza.getCurrentMoney());
        System.out.println("Stan konta  Grazyny: " +  kontoGrazyny.getCurrentMoney());
        System.out.println("Stan konta  Zdzislawa: " +  kontoZdzislawa.getCurrentMoney());

        /**
         * Definiuję wypłaty środków
         */
        BankAccountOperation wyplataSrodkowJanusza = new BankAccountOperation(BankOperationType.WITHDRAWAL);
        wyplataSrodkowJanusza.setMoney(200);
        wyplataSrodkowJanusza.setDate(LocalDateTime.now());
        wyplataSrodkowJanusza.setSourceBankAccount(kontoJanusza);

        /**
         * rejestruję operacje wypłat środków w obiekcie zarządzającymi kontami
         */
        bankAccountManager.registerOperation(wyplataSrodkowJanusza);

        /**
         * obiekt zarządzającymi kontami ma wykonać wypłaty środków
         * -> tutaj ma nastąpić zmiana wartości pieniężnych na kontach
         */
        bankAccountManager.performWithdrawals();


        /**
         * wyświetlam aktualne stany kont
         */
        System.out.println("======== Wypłaty ========");
        System.out.println("Stan konta  Janusza: " +  kontoJanusza.getCurrentMoney());


        /**
         * Definiuję transfery sródków z konta Janusza na konto Grazyny,
         * który został dziś zlecony
         */
        BankAccountOperation transferOdJanuszadoGrazyny = new BankAccountOperation(BankOperationType.TRANSFER);
        transferOdJanuszadoGrazyny.setSourceBankAccount(kontoJanusza);
        transferOdJanuszadoGrazyny.setTargetBankAccount(kontoGrazyny);
        transferOdJanuszadoGrazyny.setDate(LocalDateTime.now());
        transferOdJanuszadoGrazyny.setMoney(120);

        /**
         * Definiuję transfery sródków z konta Grazyny na konto Zdzislawa,
         * który został zlecony na jutro
         */
        BankAccountOperation transferOdGrazynyDoZdzislawa = new BankAccountOperation(BankOperationType.TRANSFER);
        transferOdGrazynyDoZdzislawa.setSourceBankAccount(kontoGrazyny);
        transferOdGrazynyDoZdzislawa.setTargetBankAccount(kontoZdzislawa);
        transferOdGrazynyDoZdzislawa.setDate(LocalDateTime.now().plusDays(1));
        transferOdGrazynyDoZdzislawa.setMoney(200);


        /**
         * Definiuję transfery sródków z konta Zdzislawa na konto Janusza,
         * który został zlecony wczoraj
         */
        BankAccountOperation transferOdZdzislawaDoJanusza = new BankAccountOperation(BankOperationType.TRANSFER);
        transferOdZdzislawaDoJanusza.setSourceBankAccount(kontoZdzislawa);
        transferOdZdzislawaDoJanusza.setTargetBankAccount(kontoJanusza);
        transferOdZdzislawaDoJanusza.setDate(LocalDateTime.now().minusDays(1));
        transferOdZdzislawaDoJanusza.setMoney(100);

        /**
         * rejestruję wszystkie transfery w obiekcie zarządzającym kontami bankowymi
         */
        bankAccountManager.registerOperation(transferOdJanuszadoGrazyny);
        bankAccountManager.registerOperation(transferOdGrazynyDoZdzislawa);
        bankAccountManager.registerOperation(transferOdZdzislawaDoJanusza);

        /**
         * wykonuję transfery, które zostały zlecone na datę i czas
         * wczesniejszy niż teraz.
         * Transfery zlecone na jutro NIE mają się wykonać
         */
        bankAccountManager.performTransfers();


        System.out.println("======== transfery ========");
        System.out.println("Stan konta  Janusza: " +  kontoJanusza.getCurrentMoney());
        System.out.println("Stan konta  Grazyny: " +  kontoGrazyny.getCurrentMoney());
        System.out.println("Stan konta  Zdzislawa: " +  kontoZdzislawa.getCurrentMoney());

    }
}
