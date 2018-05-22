package com.nicchagil.exercise.quickreturn.syncexecute;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.nicchagil.exercise.quickreturn.syncexecute.mapper.entity.User;


@SpringBootApplication
public class MyspringbootexerciseApplication {
    
    private static Logger logger = LoggerFactory.getLogger(MyspringbootexerciseApplication.class);
    
    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(MyspringbootexerciseApplication.class, args);
        
        QuickReturnService quickReturnService = applicationContext.getBean(QuickReturnService.class);
        
        User user = new User();
        user.setName("Nick Huang");
        
        quickReturnService.quickReturnAndAsyncExecute(user);
    }
}
