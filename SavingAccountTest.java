package vn.funix.FX40619.asm03;


import vn.funix.FX40619.asm02.Account;

import static org.junit.Assert.*;

public class SavingAccountTest {
    private SavingAccount sa;
    private SavingAccount sa1;

    @org.junit.Before
    public void setup() {
        sa = new SavingAccount("100009", 100_000_000);
        sa1 = new SavingAccount("100010", 9_000_000);
    }

    @org.junit.Test
    public void withdraw() throws Exception {
        assertFalse(sa.withdraw(-50000));// False vì nhập số âm
        assertTrue(sa.withdraw(50000));// True vì nhập ít nhất 50K và là bội của 10K, nhỏ hơn số dư hiện tại.
        assertFalse(sa.withdraw(100000000));// False vượt 5tr tối đa 1 lần rút và khi rút ra se không đủ 50K số dư còn lại
        assertFalse(sa.withdraw(450001));// False vì ko chia hết cho 10K
        assertTrue(sa.withdraw(5000000));// True vì chưa vượt hạn mưc tối đa 1 lần rút và khi rút ra  vẫn còn hơn 50K số dư còn lại
        assertFalse(sa.withdraw(5000001)); // false vì số vượt 5tr và số ko chia hết cho 10K

        assertFalse(sa1.withdraw(6_000_000));// False vì lớn hoen 5mio của tk thường
        sa1.setBalance(10_000_000);
        assertTrue(sa1.withdraw(6_000_000));// True vì TK đã lên premium nên có thể rút hơn 5tr
        sa1.setBalance(10_000_000);
        assertFalse(sa1.withdraw(6_000_001));// False vì lớn không chia hết 10k
    }

    @org.junit.Test
    public void isAccepted() throws Exception {
        SavingAccount sa = new SavingAccount("100009", 10000000);
        assertFalse(sa.isAccepted(-50000));// False vì nhập số âm
        assertTrue(sa.isAccepted(50000));// True vì nhập ít nhất 50K và là bội của 10K, nhỏ hơn số dư hiện tại.
        assertFalse(sa.isAccepted(10000000));// False vượt 5mio tối đa 1 lần rút và khi rút ra se không đủ 50K số dư còn lại
        assertFalse(sa.isAccepted(450001));// False vì ko chia hết cho 10K
        assertTrue(sa.isAccepted(5000000));// True vì chưa vượt hạn mưc tối đa 1 lần rút và khi rút ra  vẫn còn hơn 50K số dưu còn lại
        assertFalse(sa.isAccepted(5000001));// False vì số vượt 5mi và số ko chia hết cho 10K

        assertFalse(sa1.isAccepted(951000));// False vì ko chia hết cho 10K và khi rút ra sẽ còn lại ít hơn 50K
    }

    @org.junit.Test
    public void getFee() throws Exception {
        assertEquals(0, sa.getFee(500000.0), 0);
        assertEquals(0, sa.getFee(1000000000), 0);
        assertEquals(0, sa1.getFee(500000.0), 0);
        assertEquals(0, sa1.getFee(1000000000), 0);
    }

    @org.junit.Test
    public void isPremium() throws Exception {
        Account ac = new Account("100010", 90000000);
        assertTrue(ac.isPremiumAccount()); // True vì lớn hơn 10tr
        ac.setBalance(8_000_000d);// set lại số dư bé hơn 10tr
        assertFalse(ac.isPremiumAccount());
        ac.setBalance(10_000_000); // set lại đúng 10tr
        assertTrue(ac.isPremiumAccount());// True vì ở mức 10tr
        ac.setBalance(9_999_999); // set lại đúng 10tr
        assertFalse(ac.isPremiumAccount());// False vì bé hơn 10tr
    }
}