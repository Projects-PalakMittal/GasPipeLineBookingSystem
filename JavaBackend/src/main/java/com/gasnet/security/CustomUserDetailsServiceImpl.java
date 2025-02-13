package com.gasnet.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gasnet.dao.UserDao;
import com.gasnet.pojo.User;

@Service
@Transactional
public class CustomUserDetailsServiceImpl implements UserDetailsService {
//depcy
	@Autowired
	private UserDao userDao;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		// invoke dao' s method
		User user = userDao.findByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException("Email not found !!!!!"));
		return new CustomUserDetailsImpl(user);
	}

}
