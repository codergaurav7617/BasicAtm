package com.ril.jpm.test.security.controller;

import com.ril.jpm.test.security.exception.NotHavingSufficentBalance;
import com.ril.jpm.test.security.model.Account;
import com.ril.jpm.test.security.model.Transaction_History;
import com.ril.jpm.test.security.repositories.AccountRepository;
import com.ril.jpm.test.security.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import static com.ril.jpm.test.security.Constant.Withdraw;
@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountRepository accountRepository;

    @RequestMapping("/home")
    public ModelAndView home(
            HttpServletRequest httpServletRequest,
            @RequestParam String type,
            @RequestParam String amount,
            @RequestParam String comment

    ) throws NotHavingSufficentBalance {

        double balance=Double.parseDouble(amount);
        // to find out the current user
        String logged_in_user=httpServletRequest.getRemoteUser();
        // get the account of current user
        setBalanceOfUser(logged_in_user, type, balance);
        Transaction_History th=create_txn(logged_in_user, balance, new Date(),comment,type);
        transactionRepository.save(th);
        ModelAndView mv=new ModelAndView();
        mv.setViewName("sucess");
        return mv;
    }

   public void setBalanceOfUser(String logged_in_user,String type,double balance) throws NotHavingSufficentBalance {
       Account account_of_user=accountRepository.findByUsername(logged_in_user);
       if (account_of_user==null){
           account_of_user=new Account(logged_in_user);
           accountRepository.save(account_of_user);
       }
       if (type.equals(Withdraw)){
           if ( account_of_user.getAmount() < balance){
               throw new NotHavingSufficentBalance("please enter the correct ammount");
           }else{
               account_of_user.setAmount(-balance);
           }
       }else{
           account_of_user.setAmount(balance);
       }
   }

    public Transaction_History create_txn(String logged_in_user,double balance,Date date,String comment,String txn_type){
        return new Transaction_History(logged_in_user,balance,date,comment,txn_type);
    }

    @RequestMapping("/history")
    public ModelAndView history(HttpServletRequest httpServletRequest){
        String logged_in_user=httpServletRequest.getRemoteUser();
        List<Transaction_History> list_of_txn=transactionRepository.findByUsername(logged_in_user);
        ModelAndView mv=new ModelAndView();
        mv.setViewName("Transaction_History");
        mv.addObject("data",list_of_txn);
        return mv;
    }
}
