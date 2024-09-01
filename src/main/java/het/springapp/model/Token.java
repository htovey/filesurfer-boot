package het.springapp.model;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

@Entity
@Table(name = "persisten_logins")

@NamedQueries({
	@NamedQuery(name="Token.getToken", query="SELECT t FROM Token t WHERE t.userId = :USER_ID")
})

public class Token implements Serializable {

	private static final long serialVersionUID = -5184524106467845909L;
	 
	@Id
	@Column(name = "USER_ID")
	private String userId;
    @Column(name = "series")
    private String series;
    @Column(name = "token")
    private String token;
    @Column(name="last_used")
    private Date lastUsed;
  	
    public Token() {}
    
    public Token(String [] values) {
    	Log log = LogFactory.getLog(Token.class);
    	setUserId(values[1].toString());
    	setSeries(values[2].toString());
    	setToken(values[3].toString());
    	log.info("creating token for "+userId);
    }

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getSeries() {
		return series;
	}

	public void setSeries(String series) {
		this.series = series;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Date getLastUsed() {
		return lastUsed;
	}

	public void setLastUsed(Date lastUsed) {
		this.lastUsed = lastUsed;
	}
    
    
}
