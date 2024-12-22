package vn.funix.FX40619.asm03;


import vn.funix.FX40619.asm02.Customer;

public class DigitalCustomer extends Customer {

    public DigitalCustomer(String name, String customerID) {
        super(name, customerID);
    }

    @Override
    public void displayInformation() {
        String isPre = "normal";
        if (isPremiumAccount() == true) {
            isPre = "Premium";
        }
        System.out.printf("%-15s | %-20s | %-15s | %.1fđ\n", getCustomerId(), getName(), isPre, getTotalBalance());
        for (int i = 0; i < getAccounts().size(); i++) {
            System.out.printf("%-15s | %-20s | %15s\t\n", i + 1, getAccounts().get(i).getAccountType(), getAccounts().get(i).toString());
        }
    }
}
