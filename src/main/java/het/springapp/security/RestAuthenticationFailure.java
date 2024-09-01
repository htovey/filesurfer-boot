package het.springapp.security;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class RestAuthenticationFailure implements AuthenticationEntryPoint {
	
	private static Log log = LogFactory.getLog(RestAuthenticationFailure.class);
	
	@Override
	public void commence(
			HttpServletRequest request, 
			HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
			
			log.debug("Authentication Failed, sending error response!!!");
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
	}

}
