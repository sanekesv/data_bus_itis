package ru.kpfu.itis.auth;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ru.kpfu.itis.model.User;
import ru.kpfu.itis.repository.UserRepository;
import ru.kpfu.jbl.auth.util.PasswordHelper;

import java.util.ArrayList;
import java.util.List;

@Component
public class AuthProvider implements AuthenticationProvider {

    @Autowired
    UserRepository userRepository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String login = authentication.getName();
        String password = authentication.getCredentials().toString();
        User user = userRepository.findOneByLogin(login);
        System.out.println("test");
        if(user == null)
            throw new UsernameNotFoundException("Incorrect login");
        if(PasswordHelper.encrypt(password, user.getSalt())/*(password*/.equals(user.getPassword())){
            List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
            if (user.getRole()!=null)
            grantedAuthorities.add(new SimpleGrantedAuthority(user.getRole().name()));
            else
                grantedAuthorities.add(new SimpleGrantedAuthority("STUDENT"));

            Authentication auth = new UsernamePasswordAuthenticationToken(user, null, grantedAuthorities);
            return auth;
        } else {
            throw new BadCredentialsException("Bad user password");
        }

    }


    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

}
