package br.com.sinart.mapSys.services;

import br.com.sinart.mapSys.entities.User;
import br.com.sinart.mapSys.repositories.UserRepository;
import br.com.sinart.mapSys.security.UserSS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User usr = repository.findByUserName(userName);
       if (usr == null) {
            throw new UsernameNotFoundException(userName);
        }
        System.out.println(usr.getUserProfile());
        return new UserSS(usr.getId(), usr.getUserName(), usr.getPassword(), usr.getUserProfile());
    }
}
