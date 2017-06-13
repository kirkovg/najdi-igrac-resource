package com.najdiigrac.mk.authentication;

import com.najdiigrac.mk.model.jpa.User;
import com.najdiigrac.mk.persistence.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Win 8 on 07.06.2017.
 */
public class LoginFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse,
            AuthenticationException e
    ) throws IOException, ServletException {
        httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    }
}
