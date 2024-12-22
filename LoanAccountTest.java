package vn.funix.FX40619.asm03;

import org.junit.Test;


import static org.junit.Assert.*;

public class LoanAccountTest {
    private LoanAccount la;
    private LoanAccount la1;

    @org.junit.Before
    public void setup() {
        la = new LoanAccount("100009");
        la1 = new LoanAccount("100010");
    }

    @org.junit.Test
    public void withdraw() throws Exception {
        assertFalse(la.withdraw(-50000));//kỳ vọng false vì số âm
        assertTrue(la.withdraw(50000));//true vì lớn hơn 0 và bé hơn hạn mức khởi tạo 100mio
        assertFalse(la.withdraw(100000001));//false vì lớn hơn hạn mức tối đa cũng như số dư hiện tại
        assertTrue(la.withdraw(5000000));//true vì bé hơn hạn mức hiện tại
    }

    @org.junit.Test
    public void isAccepted() throws Exception {
        assertFalse(la.isAccepted(-50000));// False vì số âm
        assertTrue(la.isAccepted(50000));// True vì bé hơn hạn mức
        assertFalse(la.isAccepted(100000001));// False vì lớn hơn hạn mức
        assertTrue(la.isAccepted(5000000));// True vì bé hơn hạn mức
        assertFalse(la.isAccepted(99995001));// False vì khi rút ra sẽ không đủ s dư 50K còn lại
    }

    @org.junit.Test
    public void getFee() throws Exception {
        // Fee ở mức 0.01 vì tk mới khởi tạo có số dư 100mio nên vẫn là TK premium
        assertEquals(0.01 * 500000, la.getFee(500000.0), 0);
        assertEquals(0.01 * 1000000000, la.getFee(1000000000), 0);

        assertEquals(0.01 * 500000, la1.getFee(500000.0), 0);
        assertEquals(0.01 * 1000000000, la1.getFee(1000000000), 0);
        la1.withdraw(91000000);// Giảm số dư của Tk xuống mức 9tr
        
        // Do số dư ở mức 9tr nên TK chuyển thành TK thường nên mức phí tăng lên 0.05
        assertEquals(0.05 * 500000, la1.getFee(500000.0), 0);
    }
}