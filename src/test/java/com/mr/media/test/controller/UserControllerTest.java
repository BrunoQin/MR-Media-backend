package com.mr.media.test.controller;

import com.mr.media.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Created by i321273 on 1/11/17.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class UserControllerTest {

    @Test
    public void loginTest(){

        //BaseResp baseResp = new BaseResp(BaseResp.SUCCESS); //userController.login(loginReq);
        return ;

    }

}
