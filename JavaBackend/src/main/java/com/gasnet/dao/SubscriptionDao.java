package com.gasnet.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gasnet.pojo.Subscription;
import com.gasnet.pojo.User;

public interface SubscriptionDao extends JpaRepository<Subscription, Long> {

	List<Subscription> findByVendor(User vendor);

}
