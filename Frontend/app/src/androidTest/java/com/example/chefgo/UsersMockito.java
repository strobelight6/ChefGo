package com.example.chefgo;
/**
 * @author SB_3
 *
 */

import com.example.chefgo.DomainObjects.UsersDomain;
import com.example.chefgo.LoginorRegistrationActivity.LoginActivity;
import com.example.chefgo.LoginorRegistrationActivity.RegistrationActivity;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
@RunWith(MockitoJUnitRunner.class)
public class UsersMockito {
    @Mock
    UsersDomain mockUser;
    public String testName = "mockito mojito";
    public String testUserName= "mock";
    public String testPassword = "pass";
    public String testEmail = "mockito@gmail.com";

    @Before
    public void setup(){
        mockUser = new UsersDomain();

    mockUser.setUsername(testUserName);
    mockUser.setName(testName);
    mockUser.setPassword(testPassword);
    mockUser.setEmail(testEmail);

    }

    @Test
    public void testSetters(){
        //make sure username setter works
        Assert.assertEquals(mockUser.getUsername(),"mock");

        Assert.assertEquals(mockUser.getName(),"mockito mojito");

        Assert.assertEquals(mockUser.getPassword(),"pass");

        Assert.assertEquals(mockUser.getEmail(),"mockito@gmail.com");
    }
}
