package br.com.sinart.mapSys.configuration.security;


import br.com.sinart.mapSys.entities.User;
import br.com.sinart.mapSys.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Objects;

@Slf4j
@Component
public class UserDetailConfiguration implements org.springframework.security.core.userdetails.UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserDetailConfiguration(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info(String.format("Trying to get a user by username: { %s }", username));

        User user = userRepository.findByUserNameIgnoreCase(username);

        if (Objects.isNull(user)) {
            throw new UsernameNotFoundException(String.format("User { %s } not found", username));
        }

        log.info(String.format("User { %s } found successfully", username));

        return new org.springframework.security.core.userdetails
                .User(user.getUserName(), user.getPassword(), getAuthorities(user));
    }

    private static Collection<? extends GrantedAuthority> getAuthorities(User user) {

        if (user.getUserProfile() == 0) {
            return AuthorityUtils.createAuthorityList("ROLE_ADMIN");
        }
        return AuthorityUtils.createAuthorityList("ROLE_USER");
    }
}
