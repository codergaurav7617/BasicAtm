package com.ril.jpm.test.security.controller;

import com.ril.jpm.test.security.exception.NotHavingSufficentBalance;
import com.ril.jpm.test.security.model.Transaction_History;
import com.ril.jpm.test.security.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import static com.ril.jpm.test.security.Constant.VIEW;
@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @RequestMapping("/type")
    public ModelAndView home(
            HttpServletRequest httpServletRequest,
            Transaction_History th
    ) throws NotHavingSufficentBalance {
        System.out.println(th);
        double balance=th.getAmount();

        // to find out the current user
        String logged_in_user=httpServletRequest.getRemoteUser();

        // get the account of current user
        transactionService.setBalanceOfUser(logged_in_user, th.getTxnType(), balance);

        // add the transaction record .
        transactionService.create_txn(logged_in_user, balance, new Date(),th.getComment(),th.getTxnType());


        // holder for model and view
        ModelAndView mv=new ModelAndView();

        if (th.getTxnType().equals(VIEW)){
            // get the total balance in the logged in user account
            double amount=transactionService.getBalance(logged_in_user);
            //get all the view transaction
            List<Transaction_History> list_of_txn=transactionService.getAllTheViewTransaction(logged_in_user);

            mv.setViewName("balance");
            mv.addObject("val",amount);
            mv.addObject("data",list_of_txn);
            return mv;
        }else{
            mv.setViewName("sucess");
            return mv;
        }

    }

    @RequestMapping("/history")
    public ModelAndView history(HttpServletRequest httpServletRequest){

        // to find the current user
        String logged_in_user=httpServletRequest.getRemoteUser();

        // get all the transaction of the particular user
        List<Transaction_History> list_of_txn=transactionService.getHistory(logged_in_user);

        // holder for model and view
        ModelAndView mv=new ModelAndView();
        mv.setViewName("Transaction_History");
        mv.addObject("data",list_of_txn);
        return mv;
    }

    @RequestMapping("/showtransaction")
    public ModelAndView showTransaction(HttpServletRequest httpServletRequest){
        String logged_in_user=httpServletRequest.getRemoteUser();
        List<Transaction_History> list_of_txn=transactionService.getAllTheTransaction(logged_in_user);

        // holder for model and view
        ModelAndView mv=new ModelAndView();
        mv.setViewName("Transaction_History");
        mv.addObject("data",list_of_txn);
        return mv;
    }
}
