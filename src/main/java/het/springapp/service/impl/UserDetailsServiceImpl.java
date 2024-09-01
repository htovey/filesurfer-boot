/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package het.springapp.service.impl;


import het.springapp.dao.UserDao;
import het.springapp.model.User;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * from Spring docs:  "Think of UserDetails as the adapter between your database and what Spring Security needs
 * inside the SecurityContextHolder
 * @author heather
 */
@Service("coreUserDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserDao userDao;
    
    @Override
    @Transactional(propagation=Propagation.REQUIRED)
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        User user = userDao.getUser(userId);
        
        List<SimpleGrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority("user"));
        
        
        return new org.springframework.security.core.userdetails.User(user.getUserId(), user.getPassword(), authorities);
        
    }
}
