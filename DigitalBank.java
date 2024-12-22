package vn.funix.FX40619.asm03;


import vn.funix.FX40619.asm02.Account;
import vn.funix.FX40619.asm02.Bank;
import vn.funix.FX40619.asm02.Customer;


public class DigitalBank extends Bank {
    public Customer getCustomerById(String customerId) {
        for (int i = 0; i < getCustomers().size(); i++) {
            if (getCustomers().get(i).getCustomerId().equals(customerId)) {
                return getCustomers().get(i);
            }
        }
        return null;
    }

    @Override
    public void addCustomer(Customer newCustomer) {
        for (int i = 0; i < getCustomers().size(); i++) {
            if (newCustomer.getCustomerId().equals(getCustomers().get(i).getCustomerId())) {
                System.out.println("ID number is duplicated");
                return;
            }
        }
        getCustomers().add(newCustomer);
        System.out.println("Đã thêm khách hàng " + newCustomer.getCustomerId() + " vào danh sách");
    }

    // Ham withdraw kiem tra su ton tai cua khach hang va tai khoan ma khach hang muon rut => goi ham withdraw dung loai account
    public boolean withdraw(String customerId, String accountNumber, double amount) {
        Customer customer = getCustomerById(customerId);
        if (customer != null) {
            Account acc = customer.getAccountByAccountNumber(accountNumber);
            if (acc != null) {
                if (acc.getAccountType().equals(Account.ACC_TYPE_SAVINGS)) {
                    SavingAccount sa = (SavingAccount) acc;
                    return sa.withdraw(amount);
                } else {
                    LoanAccount la = (LoanAccount) acc;
                    return la.withdraw(amount);
                }
            } else {
                System.out.println("Khong tim thay so account nay!!");
                return false;
            }
        } else {
            System.out.println("Khong tim thay khach hang nay!!");
            return false;
        }
    }
}
