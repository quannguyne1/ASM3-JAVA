package vn.funix.FX40619.asm03;


import vn.funix.FX40619.asm02.Account;
import vn.funix.FX40619.asm02.Customer;


import static org.junit.Assert.*;

public class DigitalBankTest {
    private static final String CUSTOMER_ID = "001200039070";
    private static final String CUSTOMER_NAME = "Nguyen Dinh Quan";
    private DigitalBank activeBank;
    private Customer newCustomer;
    private Customer newCustomer2;

    @org.junit.Before
    public void setup() {
        activeBank = new DigitalBank();
        newCustomer = new Customer(CUSTOMER_NAME, CUSTOMER_ID);
        newCustomer2 = new Customer("Nguyen Van A", "001200039070");
        activeBank.addCustomer(newCustomer);
        activeBank.addCustomer(newCustomer2);
        Account testSavingsAccount = new SavingAccount("100001", 5_000_000.0);
        activeBank.addAccount(CUSTOMER_ID, testSavingsAccount);
        Account testLoanAccount = new LoanAccount("100002");
        activeBank.addAccount(CUSTOMER_ID, testLoanAccount);
        System.out.println("Running a test...");
    }

    @org.junit.Test
    public void getCustomerById() throws Exception {
        assertEquals("Nguyen Dinh Quan", activeBank.getCustomerById("001200039070").getName());
        assertNotEquals("Nguyen Van A", activeBank.getCustomerById("001200039070").getName());
    }

    @org.junit.Test
    public void withdraw() throws Exception {
        assertTrue(activeBank.withdraw("001200039070", "100001", 500000));
        assertTrue(activeBank.withdraw("001200039070", "100002", 5000000));
        assertFalse(activeBank.withdraw("001200039070", "100002", 5000000));// số tk không thuộc KH này
        assertFalse(activeBank.withdraw("001200039070", "100003", 5000000));// KH không có số TK này
        assertFalse(activeBank.withdraw("001200039070", "100001", -50000));// số âm
        assertFalse(activeBank.withdraw("001200039070", "100001", 500010));// tk saving cần rút bội số 10k
        assertTrue(activeBank.withdraw("001200039070", "100002", 500010));// TK Loan không cần kiểm tra bội số 10K
        assertFalse(activeBank.withdraw("001200039070", "100003", 100000010));
    }
}