package vn.funix.FX40619.asm03;


import vn.funix.FX40619.asm02.Account;

public class LoanAccount extends Account implements Withdraw, ReportService {
    private static final double LOAN_ACCOUNT_MAX_BALANCE = 100000000.0;

    public LoanAccount(String accountNumber) {
        super(accountNumber, LOAN_ACCOUNT_MAX_BALANCE, ACC_TYPE_LOAN);
    }

    @Override
    public void log(double amount) {
        long millis = System.currentTimeMillis();
        java.util.Date date = new java.util.Date(millis);
        System.out.println("+------+-----------------------+------+");
        System.out.println("      BIEN LAI GIAO DICH LOAN       ");
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
            Transaction transaction = new Transaction(this.getAccountNumber(), amount, getFee(amount), newBalance, "Withdraw Loan Account", true);
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
        if (amount < 0) {
            System.out.println("So tien rut phai >= 0");
            return false;
        } else if (amount > LOAN_ACCOUNT_MAX_BALANCE) {
            System.out.println("Khong the rut qua han muc " + LOAN_ACCOUNT_MAX_BALANCE);
            return false;
        } else if (amount > getBalance()) {
            System.out.println("So du hien tai khong du");
            return false;
        } else if (newBalance < ACCOUNT_MIN_BALANCE) {
            System.out.println("So du con lai phai >= " + ACCOUNT_MIN_BALANCE);
            return false;
        }
        return true;
    }

    public double getFee(double amount) {
        if (isPremiumAccount()) {
            return LOAN_ACCOUNT_WITHDRAW_PREMIUM_FEE * amount;
        } else {
            return LOAN_ACCOUNT_WITHDRAW_FEE * amount;
        }
    }
}
