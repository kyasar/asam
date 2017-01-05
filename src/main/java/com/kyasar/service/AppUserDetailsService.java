package com.kyasar.service;

import com.kyasar.model.Role;
import com.kyasar.model.User;
import com.kyasar.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
public class AppUserDetailsService implements UserDetailsService {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.debug("Authenticating user: " + username);
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("User %s does not exist!", username));
        }
        return new AppUserDetailsService.UserRepositoryUserDetails(user);
    }

    private final static class UserRepositoryUserDetails extends User implements UserDetails {

        private static final long serialVersionUID = 1L;

        private final Logger log = LoggerFactory.getLogger(this.getClass());

        private UserRepositoryUserDetails(User user) {
            super(user);
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            log.debug("Getting authorities for " + super.getUsername() + " roles: " + getRoles().toString());
            Set<GrantedAuthority> roles = new HashSet<>();
            for (String s : getRoles())
                roles.add(new SimpleGrantedAuthority(s));
            log.debug(" roles: " + roles.toString());
            return roles;
        }

        @Override
        public String getUsername() {
            return super.getUsername();
        }

        @Override
        public boolean isAccountNonExpired() {
            return true;
        }

        @Override
        public boolean isAccountNonLocked() {
            return true;
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return true;
        }

        @Override
        public boolean isEnabled() {
            return true;
        }
    }
}
