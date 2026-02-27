package com.hrms.config;
import com.hrms.entity.User;
import com.hrms.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
 
@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
        @Override
        public UserDetails loadUserByUsername(String username)
                throws UsernameNotFoundException {
     
            User user = userRepository.findByEmail(username)
                    .orElseThrow(() ->
                            new UsernameNotFoundException(
                                    "User not found with email: " + username
                            )
                    );
            return org.springframework.security.core.userdetails.User
                    .withUsername(user.getEmail())
                    .password(user.getPassword())
                    .authorities("ROLE_USER") 
                    .accountExpired(false)
                    .accountLocked(false)
                    .credentialsExpired(false)
                    .disabled(false)
                    .build();
        }
    } 