package vn.funix.FX40619.asm03;


import vn.funix.FX40619.asm02.Account;

public class SavingAccount extends Account implements Withdraw, ReportService {

    public SavingAccount(String accountNumber, double balance) {
        super(accountNumber, balance, ACC_TYPE_SAVINGS);
    }

    @Override
    public void log(double amount) {
        long millis = System.currentTimeMillis();
        java.util.Date date = new java.util.Date(millis);
        System.out.println("+------+-----------------------+------+");
        System.out.println("      BIEN LAI GIAO DICH SAVINGS       ");
        System.out.printf("NGAY G/D: %28s%n", date);
        System.out.printf("ATM ID: %30s%n", "DIGITAL-BANK-ATM 2024");
        System.out.printf("SO TK: %31s%n", getAccountNumber());
            System.out.printf("SO TIEN: %29s%n", String.format("%.1f", amount));
        System.out.printf("SO DU: %31s%n", String.format("%.1f", getBalance()));
        System.out.printf("PHI + VAT: %27s%n", String.format("%.1f", getFee(amount)));
        System.out.println("+------+-----------------------+------+");
    }

    @Override
    public boolean withdraw(double amount) {
        if (isAccepted(amount)) {
            double newBalance = getBalance() - amount - getFee(amount);
            Transaction transaction = new Transaction(this.getAccountNumber(), amount, getFee(amount), newBalance, "Withdraw Savings Account", true);
            addTransaction(transaction);
            setBalance(newBalance);
            System.out.println("G/D thanh cong");
            log(amount);
            return true;
        }
        System.out.println("G/D khong thanh cong");
        return false;
    }

    @Override
    public boolean isAccepted(double amount) {
        double newBalance = getBalance() - amount - getFee(amount);
        if (newBalance % 10000 != 0) {
            System.out.println("So tien rut phai la boi so cua 10,000");
            return false;
        } else if (amount > getBalance()) {
            System.out.println("So du hien tai khong du");
            return false;
        } else if (amount < SAVINGS_ACCOUNT_MIN_WITHDRAW) {
            System.out.println("han muc rut toi thieu la " + SAVINGS_ACCOUNT_MIN_WITHDRAW);
            return false;
        } else if (newBalance < ACCOUNT_MIN_BALANCE) {
            System.out.println("So du toi thieu khong thap hon " + ACCOUNT_MIN_BALANCE);
            return false;
        } else if (isPremiumAccount() == false && amount > SAVINGS_ACCOUNT_MAX_WITHDRAW) {
            System.out.println("TK thuong khong the rut qua han muc " + SAVINGS_ACCOUNT_MAX_WITHDRAW);
            return false;
        }
        return true;
    }

    public double getFee(double amount) {
        if (isPremiumAccount()) {
            return SAVINGS_ACCOUNT_WITHDRAW_PREMIUM_FEE * amount;
        } else {
            return SAVINGS_ACCOUNT_WITHDRAW_FEE * amount;
        }
    }



    public void logTranfer(Account receivedAccount, double amount) {
        long millis = System.currentTimeMillis();
        java.util.Date date = new java.util.Date(millis);
        System.out.println("+------+-----------------------+------+");
        System.out.println("      BIEN LAI GIAO DICH SAVINGS       ");
        System.out.printf("NGAY G/D: %28s%n", date);
        System.out.printf("ATM ID: %30s%n", "DIGITAL-BANK-ATM 2023");
        System.out.printf("SO TK: %31s%n", getAccountNumber());
        System.out.printf("SO TK NHAN: %26s%n", receivedAccount.getAccountNumber());
        System.out.printf("SO TIEN CHUYEN: %22s%n", String.format("%.1f", amount));
        System.out.printf("SO DU: %31s%n", String.format("%.1f", getBalance()));
        System.out.printf("PHI + VAT: %27s%n", String.format("%.1f", getFee(amount)));
        System.out.println("+------+-----------------------+------+");
    }
}
