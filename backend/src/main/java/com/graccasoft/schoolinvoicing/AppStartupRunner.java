package com.graccasoft.schoolinvoicing;

import com.graccasoft.schoolinvoicing.enums.UserRole;
import com.graccasoft.schoolinvoicing.model.User;
import com.graccasoft.schoolinvoicing.service.UserService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class AppStartupRunner implements ApplicationRunner {

    private final UserDetailsService userDetailsService;
    private final UserService userService;

    public AppStartupRunner(UserDetailsService userDetailsService, UserService userService) {
        this.userDetailsService = userDetailsService;
        this.userService = userService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        //create default user. I don't think this is the best place to do this
        try{
            userDetailsService.loadUserByUsername("admin");
        }catch(UsernameNotFoundException ex){
            User adminUser = new User();
            adminUser.setUsername("admin");
            adminUser.setPassword("Admin1234");
            adminUser.setRole(UserRole.ADMINISTRATOR);
            adminUser.setFirstName("Default");
            adminUser.setLastName("Admin");

            userService.saveUser(adminUser);
        }
    }
}
