package het.springapp.dao.impl;

import het.springapp.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.stereotype.Repository;
import het.springapp.dao.UserDao;

import jakarta.persistence.EntityManager;

import org.hibernate.query.Query;
import org.hibernate.Session;

@Repository("userDao")
public class UserDaoImpl implements UserDao {

    @Autowired(required = true)
    private EntityManager manager;
    
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    
    public User login(String userId, String password) {
     
        //String hashedPassword = passwordEncoder.encode(password);
        
        Session session = getSession();
        Query query = session.getNamedQuery("User.authenticate");
        query.setParameter("USER_ID", userId);
        query.setParameter("PASSWORD", password);
        
        User user = (User) query.uniqueResult();
        
        return user;
    }
    
    public User getUser(String userId) {
        return (User) getSession().get(User.class, userId);
    }
    
    private Session getSession() {
    	return manager.unwrap(Session.class);
    }
}
