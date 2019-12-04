package aclaudel.codurance.atm;

import java.util.UUID;

public class Account {
    private UUID id;
    private int balance;

    public Account(UUID id, int balance) {
        this.id = id;
        this.balance = balance;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}
