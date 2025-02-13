package com.gasnet.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gasnet.pojo.Subscription;
import com.gasnet.pojo.Transaction;
import com.gasnet.pojo.User;

public interface TransactionDao extends JpaRepository<Transaction, Long> {

	User findBySubscription(Subscription subscription);
}
