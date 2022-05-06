package com.sigulia.test;

import com.sigulia.config.CredentialsConfig;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.Test;

public class OwnerTest {
    CredentialsConfig config = ConfigFactory.create(CredentialsConfig.class);
    @Test
    void loginTest(){
        String login = config.login();
        String password = config.password();
        String selenoid = config.selenoid();

        System.out.println("Login: " + login + "\nPassword: " + password + "\nSelenoid address: " + selenoid);
    }


}

