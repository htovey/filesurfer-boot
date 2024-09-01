/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package het.springapp.model;

/**
 *
 * @author heather
 */
import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@Entity
@Table(name = "USER")

@NamedQueries({
    @NamedQuery(name="User.authenticate", query="SELECT u FROM User u WHERE u.userId = :USER_ID and u.password = :PASSWORD"),
    @NamedQuery(name="User.findUser", query="SELECT u FROM User u WHERE u.userId = :USER_ID")
})

public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name = "USER_ID", nullable = false)
    private String userId;

    @Column(name = "PASSWORD", nullable = false)
    private String password;
    
    public User() {
    }
    
    public User(String [] values) {
        Log log = LogFactory.getLog(User.class);
        setUserId(values[0]);
        setPassword(values[1]);
        log.info("converting json object to user for userId "+values[0]);
    }

    public String getUserId() {
            return userId;
    }

    public void setUserId(String userId) {
            this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
