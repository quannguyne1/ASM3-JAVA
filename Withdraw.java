package vn.funix.FX40619.asm03;

public interface Withdraw {
    double SAVINGS_ACCOUNT_MAX_WITHDRAW = 5000000;
    double SAVINGS_ACCOUNT_MIN_WITHDRAW = 50000;
    double ACCOUNT_MIN_BALANCE = 50000;
    double SAVINGS_ACCOUNT_WITHDRAW_FEE = 0.0;
    double SAVINGS_ACCOUNT_WITHDRAW_PREMIUM_FEE = 0.0;
    double LOAN_ACCOUNT_WITHDRAW_FEE = 0.05;
    double LOAN_ACCOUNT_WITHDRAW_PREMIUM_FEE = 0.01;

    boolean withdraw(double amount);

    boolean isAccepted(double amount);
}
