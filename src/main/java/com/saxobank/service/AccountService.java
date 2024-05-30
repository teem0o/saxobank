package com.saxobank.service;

import com.saxobank.entity.Account;
import com.saxobank.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }

    public Optional<Account> getAccount(Long id) {
        return accountRepository.findById(id);
    }

    public Account updateAccount(Account account) {
        return accountRepository.save(account);
    }


    public boolean canPlaceOrder(Long accountId, double orderValue) {
        Account account = getAccount(accountId).orElseThrow(() -> new RuntimeException("Account not found"));
        double potentialDayLoss = account.getCurrentDayLoss() + orderValue;
        double potentialMaxLoss = account.getCurrentMaxLoss() + orderValue;

        if (account.getDailyRisk() > 0 && potentialDayLoss > account.getDailyRisk()) {
            closeOpenTrades(accountId);
            return false;
        }

        return !(account.getMaxRisk() > 0) || !(potentialMaxLoss > account.getMaxRisk());
    }

    private void closeOpenTrades(Long accountId) {
        // close all open trades
    }
}
