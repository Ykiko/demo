package com.example.demo.confing;

import com.example.demo.person.Person;
import com.example.demo.repositorys.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private Repository repository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails loadedUser;

        try {
            Person client = repository.findByUsername(username);
            loadedUser = new org.springframework.security.core.userdetails.User(
                    client.getUsername(), client.getPassword(),
                    UserAuthority.getAuth());
        } catch (Exception repositoryProblem) {
            throw new InternalAuthenticationServiceException(repositoryProblem.getMessage(), repositoryProblem);
        }
        return loadedUser;
    }

    static class UserAuthority implements GrantedAuthority
    {
        static Collection<GrantedAuthority> getAuth()
        {
            List<GrantedAuthority> res = new ArrayList<>(1);
            res.add(new UserAuthority());
            return res;
        }
        @Override
        public String getAuthority() {
            return "USER";
        }
    }
}
