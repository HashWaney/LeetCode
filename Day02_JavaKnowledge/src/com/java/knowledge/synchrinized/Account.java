package com.java.knowledge.synchrinized;

/**
 * 演示死锁的发生 可能会发生死锁，细粒度锁
 */

public class Account {
    private double balance;

    public void setBalance(double balance) {
        this.balance = balance;
    }


    public double getBalance() {
        return balance;
    }

    public void transfer(Account target, int money) {
        synchronized (this) {
            synchronized (target) {
                if (this.balance > money) {
                    this.balance -= money;
                    target.balance += money;
                }
            }
        }

    }

    public static void main(String[] args) {
        Account account = new Account();
        account.setBalance(1000.2);
        Account account1 = new Account();
        account1.setBalance(102.01);
        account.transfer(account1, 22);

        Account account2 =new Account();
        account2.setBalance(112.3);

        account1.transfer(account2,10);
        System.err.println("export account  balance is "+account.balance+" import account balance is "+account1.balance+" account2 balance is "+account2.balance);

    }
}
