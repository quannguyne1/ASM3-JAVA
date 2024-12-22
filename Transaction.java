package vn.funix.FX40619.asm03;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

public class Transaction implements Serializable {
    private final String id;
    private final String accountNumber;
    private final double amount;
    private final String time;
    private final double transactionFee;
    private final double currentBalance;
    private final String description;
    private final boolean status;

    public Transaction(String accountNumber, double amount, double transactionFee, double currentBalance, String description, boolean status) {
        this.id = String.valueOf(UUID.randomUUID());
        this.accountNumber = accountNumber;
        this.amount = amount;
        this.transactionFee = transactionFee;
        this.currentBalance = currentBalance;
        this.description = description;
        this.status = status;
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        this.time = dateFormat.format(date);
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getAmount() {
        return amount;
    }

    public String getTime() {
        return time;
    }

    public double getTransactionFee() {
        return transactionFee;
    }

    public double getCurrentBalance() {
        return currentBalance;
    }

    public String getDescription() {
        return description;
    }

    public boolean isStatus() {
        return status;
    }

    // Xuất thông tin tài khoản gồm mã tài khoản và số dư
    public String toString() {
        String s = String.format("%22s", getTime()) + " | " + String.format("%18s", String.format("%,d", (long) (getAmount() + getTransactionFee()) * (-1))) +
                "đ |" + String.format("%12s", isStatus() ? "Thành công" : "Thất bại") + "| " + String.format("%-25s", getDescription()) + "|\n";
        return s;
    }
}
