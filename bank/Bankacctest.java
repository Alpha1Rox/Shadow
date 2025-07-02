import org.junit.Test;
import static org.junit.Assert.*;
import java.util.List;

public class BankAccountTest {

    @Test
    public void depositIncreasesBalance() {
        BankAccount acct = new BankAccount("Riya", "R2025", 1000);
        acct.addFunds(250);
        assertEquals(1250, acct.checkBalance(), 0.001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void depositZeroOrNegativeThrows() {
        BankAccount acct = new BankAccount("Riya", "R2025", 1000);
        acct.addFunds(0);
    }

    @Test
    public void withdrawDecreasesBalance() {
        BankAccount acct = new BankAccount("Amit", "A3030", 500);
        acct.removeFunds(200);
        assertEquals(300, acct.checkBalance(), 0.001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void withdrawMoreThanBalanceThrows() {
        BankAccount acct = new BankAccount("Amit", "A3030", 500);
        acct.removeFunds(600);
    }

    @Test
    public void balanceInquiryWorks() {
        BankAccount acct = new BankAccount("Sana", "S4040", 800);
        assertEquals(800, acct.checkBalance(), 0.001);
    }

    @Test
    public void logsAreRecordedCorrectly() {
        BankAccount acct = new BankAccount("Sam", "S5050", 100);
        acct.addFunds(50);
        acct.removeFunds(30);
        List<String> logs = acct.getLogs();
        assertEquals(3, logs.size());
        assertTrue(logs.get(1).contains("Deposited: 50"));
        assertTrue(logs.get(2).contains("Withdrew: 30"));
    }
}
