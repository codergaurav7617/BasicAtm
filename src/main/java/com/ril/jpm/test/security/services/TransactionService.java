package com.ril.jpm.test.security.services;

import com.ril.jpm.test.security.exception.NotHavingSufficentBalance;
import com.ril.jpm.test.security.model.Account;
import com.ril.jpm.test.security.model.Transaction_History;
import com.ril.jpm.test.security.repositories.AccountRepository;
import com.ril.jpm.test.security.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import static com.ril.jpm.test.security.Constant.VIEW;
import static com.ril.jpm.test.security.Constant.WITHDRAW;

@Service
public class TransactionService {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private TransactionRepository transactionRepository;
    public void setBalanceOfUser(String logged_in_user,String type,double balance) throws NotHavingSufficentBalance {
        Account account_of_user=accountRepository.findByUsername(logged_in_user);
        if (account_of_user==null){
            account_of_user=new Account(logged_in_user);
            accountRepository.save(account_of_user);
        }
        if (type.equals(WITHDRAW)){
            if ( account_of_user.getAmount() < balance){
                throw new NotHavingSufficentBalance("please enter the correct ammount");
            }else{
                account_of_user.setAmount(-balance);
            }
        }else{
            account_of_user.setAmount(balance);
        }

    }

    public double getBalance(String username){
        Account account_of_user=accountRepository.findByUsername(username);
        return account_of_user.getAmount();
    }

    public void create_txn(String logged_in_user, double balance, Date date, String comment, String txn_type){
        Transaction_History th= new Transaction_History(logged_in_user,balance,date,comment,txn_type);
        transactionRepository.save(th);
    }
    public List<Transaction_History> getHistory(String username){

        return transactionRepository.findByUsername(username);
    }

    public List<Transaction_History> getAllTheTransaction(String username){
        return transactionRepository.findByUsernameAndTxnTypeIsNotContaining(username,"view");
    }

    public List<Transaction_History> getAllTheViewTransaction(String username){
        return transactionRepository.findByUsernameAndTxnTypeIsContaining(username, "view");
    }

}
