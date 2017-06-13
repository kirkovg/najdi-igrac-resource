package com.najdiigrac.mk.authentication;

import com.najdiigrac.mk.model.enums.UserType;
import com.najdiigrac.mk.model.jpa.User;
import com.najdiigrac.mk.persistence.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Win 8 on 07.06.2017.
 */
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    @Autowired
    private UsersRepository usersRepository;

    public LoginSuccessHandler() {

    }

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication
    ) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = usersRepository.findByUserName(authentication.getName());
        session.setAttribute("user", user);
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
