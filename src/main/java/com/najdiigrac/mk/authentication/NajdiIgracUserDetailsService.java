package com.najdiigrac.mk.authentication;

import com.najdiigrac.mk.model.jpa.User;
import com.najdiigrac.mk.persistence.UsersRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;


@Service
public class NajdiIgracUserDetailsService implements UserDetailsService {

  private UsersRepository usersRepository;

  @Autowired
  public NajdiIgracUserDetailsService(UsersRepository usersRepository) {
    this.usersRepository = usersRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    User user = usersRepository.findByUserName(username);

    if (user != null) {
      List<SimpleGrantedAuthority> roles = Collections.singletonList(
        new SimpleGrantedAuthority(user.userType.toString())
      );

      return new org.springframework.security.core.userdetails.User(
              user.userName, user.password, roles
      );
    } else {
      return new org.springframework.security.core.userdetails.User(
        "anonymous", "", Collections.emptyList()
      );
    }
  }
}
