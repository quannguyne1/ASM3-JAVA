package vn.funix.FX40619.asm03;



import vn.funix.FX40619.asm02.Account;
import vn.funix.FX40619.asm02.Customer;

import java.util.Scanner;

public class Asm03 {

    private static final Scanner scanner = new Scanner(System.in);
    private static final Scanner sc = new Scanner(System.in);
    private static final Scanner input = new Scanner(System.in);
    private static final DigitalBank activeBank = new DigitalBank();
    private static final String CUSTOMER_ID = "001200039070";
    private static final String CUSTOMER_NAME = "Nguyen Dinh Quan";

    public static void main(String[] args) {
        thucThiChuongTrinh();
        scanner.close();
    }

    public static void hienThiMenu() {
        System.out.println("+--------+-----------------+----------+");
        System.out.println("| NGAN HANG SO | FX40619@v3.0.0       |");
        System.out.println("+--------+-----------------+----------+");
        System.out.println("| 1. Thong tin khach hang              ");
        System.out.println("| 2. Them tai khoan ATM                ");
        System.out.println("| 3. Them tai khoan tin dung           ");
        System.out.println("| 4. Rut tien                          ");
        System.out.println("| 5. Lich su giao dich                 ");
        System.out.println("| 0. Thoat                             ");
        System.out.println("+--------+-----------------+----------+");
    }

    public static void thucThiChuongTrinh() {
        Customer customer = new Customer(CUSTOMER_NAME, CUSTOMER_ID);
        Customer customer1 = new Customer("Quoc", "037089002282");
        activeBank.addCustomer(customer);
        activeBank.addCustomer(customer1);

        boolean check = true;
        while (check) {
            hienThiMenu();
            int chon = -1;
            System.out.print("Chuc nang: ");
            try {
                chon = sc.nextInt();
            } catch (Exception e) {
                System.out.println("E: Nhap sai gia tri!!");
                sc.nextLine();
                thucThiChuongTrinh();
            }
            switch (chon) {
                case 1:
                    showCustomer();
                    break;
                case 2:
                    addSavingsAccount();
                    break;
                case 3:
                    addLoanAccount();
                    break;
                case 4:
                    withdraw();
                    break;
                case 5:
                    showLog();
                    break;
                case 0:
                    System.exit(1);
                    break;
                default:
                    System.out.println("Vui long chon lai chuc nang co trong menu!!");
                    break;
            }
        }
    }

    private static void showCustomer() {
        try {
            Customer customer = activeBank.getCustomerById(CUSTOMER_ID);
            if (customer != null) {
                customer.displayInformation();
            }
        } catch (Exception e) {
            System.out.println("Customer does NOT exiest");
        }
    }

    private static void addSavingsAccount() {
        System.out.println("Nhap STK gom 6 chu so: ");
        String STK = scanner.nextLine();
        while (STK.length() != 6) {
            System.out.println("STK co 6 chu so, vui long nhap lai: ");
            STK = scanner.nextLine();
        }
        System.out.println("Nhap so du: ");
        double soDu = input.nextDouble();
        while (soDu < 50000) {
            System.out.println("So du phai lon hon hoac bang 50k");
            System.out.println("Nhap so du: ");
            soDu = input.nextDouble();
        }
        Account savingsAccount = new SavingAccount(STK, soDu);
        activeBank.addAccount(CUSTOMER_ID, savingsAccount);
    }

    private static void addLoanAccount() {
        System.out.println("Nhap STK gom 6 chu so: ");
        String STK = scanner.nextLine();
        while (STK.length() != 6) {
            System.out.println("STK co 6 chu so, vui long nhap lai: ");
            STK = scanner.nextLine();
        }
        Account loanAccount = new LoanAccount(STK);
        activeBank.addAccount(CUSTOMER_ID, loanAccount);
    }

    private static void withdraw() {
        System.out.println("Nhap STK muon rut: ");
        String STK = scanner.nextLine();
        System.out.println("Nhap so tien can rut: ");
        double soTien = input.nextDouble();
        activeBank.withdraw(CUSTOMER_ID, STK, soTien);
    }

    private static void showLog() {
        Customer customer = activeBank.getCustomerById(CUSTOMER_ID);
        System.out.println("Tra cuu lich su giao dich: ");
        customer.displayInformation();
        System.out.println("Nhap STK muon sao ke: ");
        String STK = scanner.nextLine();
        while (STK.length() != 6) {
            System.out.println("STK co 6 chu so, vui long nhap lai: ");
            STK = scanner.nextLine();
        }
        Account acc = customer.getAccountByAccountNumber(STK);
        if (acc != null) {
            acc.displaydisplayAllTransactions();
        } else {
            System.out.println("STK khong ton tai!!");
        }
    }
}
