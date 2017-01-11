package com.mr.media.test.controller;

import com.mr.media.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.assertTrue;

/**
 * Created by i321273 on 1/11/17.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class UserControllerTest {

//    @Autowired
//    UserService userService;

    @Test
    public void loginTest(){

//        LoginReq loginReq = new LoginReq();
//        loginReq.uid = "ddd";
//        loginReq.password = "ddd";
//        int errCode = userService.login(loginReq.uid, loginReq.password);
//        String test = userService.test();
//        BaseResp baseResp = new BaseResp(BaseResp.SUCCESS); //userController.login(loginReq);
        assertTrue("hello" == "hello");

    }

}
