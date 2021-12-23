package com.domain.services;

import javax.transaction.Transactional;

// import javax.management.RuntimeErrorException;

import com.domain.models.entities.AppUser;
import com.domain.models.repos.AppUserRepo;
// import com.domain.utils.PasswordEncoder;

// import org.modelmapper.internal.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;




@Service
@Transactional
public class AppUserService implements UserDetailsService {

    @Autowired
    private AppUserRepo appUserRepo;
    
    @Autowired
    // @Qualifier("bCryptPasswordEncoder")
    private BCryptPasswordEncoder  bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        
        return appUserRepo.findByEmail(email)
            .orElseThrow( () -> new UsernameNotFoundException(
                String.format("user with email '%s' not found ", email 
            )));
    }


    public AppUser registerAppUser(AppUser user){
        boolean userExists = appUserRepo.findByEmail(user.getEmail()).isPresent();
        if (userExists){
            throw new RuntimeException(
                String.format("user with email '%s' already exist", user.getEmail()));
        }

        String encodePassword =bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword( encodePassword );

        return appUserRepo.save(user);

    }
    
    
}
