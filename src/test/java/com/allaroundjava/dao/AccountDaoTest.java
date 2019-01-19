package com.allaroundjava.dao;

import com.allaroundjava.model.Account;
import com.allaroundjava.model.CreditCard;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.*;

public class AccountDaoTest {
    public static Logger LOGGER = LogManager.getLogger(AccountDaoTest.class);
    private EntityManagerFactory emf;
    private Dao<Account> accountDao;
    private Dao<CreditCard> creditCardDao;

    public AccountDaoTest() {
        emf = Persistence.createEntityManagerFactory("bidirectionalManyToOne");
        accountDao = new AccountDao(emf);
        creditCardDao = new CreditCardDao(emf);
    }

    @Test
    public void whenAccessingCreditCards_thenNonemptyCollection() {
        Account account = Account.newInstance("345-321-346", "Henry Ford");
        CreditCard cardOne = CreditCard.newInstance("12345", LocalDate.now(), account);
        CreditCard cardTwo = CreditCard.newInstance("6789", LocalDate.now(), account);

        accountDao.persist(account);
        creditCardDao.persist(cardOne);
        creditCardDao.persist(cardTwo);

        Account fetchedAccount = accountDao.getById(account.getId()).get();
        Set<CreditCard> creditCards = fetchedAccount.getCreditCards();
        LOGGER.debug("Credit cards will be fetched now");
        Assert.assertEquals(2, creditCards.size());
    }
}