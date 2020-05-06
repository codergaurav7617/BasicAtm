package com.ril.jpm.test.security.repositories;

import com.ril.jpm.test.security.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account,Integer> {
       Account findByUsername(String username);
}
