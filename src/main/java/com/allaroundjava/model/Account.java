package com.allaroundjava.model;

import org.hibernate.annotations.Immutable;

import javax.persistence.*;
import java.util.Set;

@Entity
@Immutable
public final class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String accountNumber;
    private String accountOwnerName;

    @OneToMany(mappedBy = "account")
    private Set<CreditCard> creditCards;

    Account() {}

    Account(String accountNumber, String accountOwnerName) {
        this.accountNumber = accountNumber;
        this.accountOwnerName = accountOwnerName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountOwnerName() {
        return accountOwnerName;
    }

    public Set<CreditCard> getCreditCards() {
        return creditCards;
    }

    public Long getId() {
        return id;
    }

    public void addCreditCard(CreditCard creditCard) {
        this.creditCards.add(creditCard);
    }

    public static Account newInstance(String accountNumber, String accountOwnerName) {
        return new Account(accountNumber, accountOwnerName);
    }
}
