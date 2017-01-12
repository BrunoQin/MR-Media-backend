package com.mr.media.test.controller;

import com.google.gson.Gson;
import com.mr.media.Application;
import com.mr.media.request.user.LoginReq;
import com.mr.media.response.BaseResp;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by i321273 on 1/11/17.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class UserControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    @Test
    public void loginTest() throws Exception {

        LoginReq loginReq = new LoginReq();
        loginReq.uid = "ddd";
        loginReq.password = "ddd";

        mockMvc.perform(post("/user/login")
                .contentType("application/json")
                .content(new Gson().toJson(loginReq))
        ).andExpect(status().isOk())
                .andExpect(jsonPath("$.errCode", is(BaseResp.SUCCESS)));

    }

}
