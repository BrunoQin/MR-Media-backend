package com.mr.media.test.controller;

import com.google.gson.Gson;
import com.mr.media.Application;
import com.mr.media.model.User;
import com.mr.media.request.BaseReq;
import com.mr.media.request.user.ChangePasswordReq;
import com.mr.media.request.user.LoginReq;
import com.mr.media.response.BaseResp;
import com.mr.media.response.user.LoginResp;
import com.mr.media.service.UserService;

import org.json.JSONObject;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by tonyP on 2017/1/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@WebAppConfiguration
public class AdminControllerTest {
    private MockMvc mockMvc;
    private static String token;
    private static User user;
    private static UserService userservice;
    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setUp(){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    @BeforeClass
    public static void setUpOnce(){
        try{
            userservice = new UserService();
            User parent = userservice.findUserByUid("ddd");
            user = new User();
            user.setUid("pd");
            user.setUsername("kkk");
            user.setPassword("kkk");
            user.setAuthority(1);
            user.setLevel(1);
            user.setSuperUser(parent);
            user.setDisable(User.USER_ACTIVE);
            user.save();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @AfterClass
    public static void tearDownOnce(){
        user.delete();
    }
    @Test
    public void test_0001_getPagedAgents() throws Exception {
       mockMvc.perform(get("/admin/employee/agents")
                .param("token", token)
        ).andExpect(status().isOk());

    }
   /* public void test_0002_getPagedActors() throws Exception {
        mockMvc.perform(get("/admin/employee/actors")
                .param("token", token)
        ).andExpect(status().isOk());
    }*/
}
