
import java.util.LinkedList;
import java.util.Collections;
import java.util.List;

public class BankAccount {
    private String hname;
    private String accID;
    private double CB;
    private LinkedList<String> logs;

    public BankAccount(String hname, String accID, double initialBalance) {
        this.hname = hname;
        this.accID = accID;
        this.CB = initialBalance;
        this.logs = new LinkedList<>();
        logs.add("Account opened with balance: " + initialBalance);
    }

    public void addFunds(double amount) {
        if (amount <= 0) throw new IllegalArgumentException("Amount must be positive");
        CB += amount;
        logs.add("Deposited: " + amount);
    }

    public void removeFunds(double amount) {
        if (amount <= 0) throw new IllegalArgumentException("Amount must be positive");
        if (amount > CB) throw new IllegalArgumentException("Insufficient balance");
        CB -= amount;
        logs.add("Withdrew: " + amount);
    }

    public double checkBalance() {
        return CB;
    }

    public List<String> getLogs() {
        return Collections.unmodifiableList(logs);
    }

    public String gethname() {
        return hname;
    }

    public String getaccID() {
        return accID;
    }
}
