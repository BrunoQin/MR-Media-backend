package com.mr.media.service.authority;

import com.avaje.ebean.Ebean;
import com.mr.media.model.User;
import com.mr.media.request.authority.agent.AddEmployeeReq;
import com.mr.media.response.BaseResp;
import com.mr.media.service.UserService;
import com.mr.media.util.UidHelper;
import javafx.util.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.PrintStream;

/**
 * Created by i321273 on 1/6/17.
 */

@Service
public class AgentService {

    @Autowired
    UserService userService;

    @Autowired
    UidHelper uidHelper;

    final Logger logger = LoggerFactory.getLogger(getClass());

    public Pair<Integer, String> addEmployee(String token, String username, Integer authority){
        User parentUser = userService.findUserByToken(token);
        if(StringUtils.isEmpty(username)){
            return new Pair<>(BaseResp.CREATED_USERNAME_NULL, null);
        }
        User childUser = userService.findUserByUsername(username);
        if(childUser != null){
            return new Pair<>(BaseResp.CREATED_USERNAME_EXIST, null);
        }
        if(authority == null){
            return new Pair<>(BaseResp.CREATED_AUTHORITY_NULL, null);
        }
        if(parentUser.getAuthority() > authority || authority > 3){
            return new Pair<>(BaseResp.CREATED_AUTHORITY_INVALID, null);
        }

        int employeeCount = Ebean.find(User.class).where()
                .eq("authority", authority)
                .findRowCount();

        String uid = uidHelper.generateUid(employeeCount, authority);
        try{
            childUser = new User();
            childUser.setUid(uid);
            childUser.setUsername(username);
            childUser.setPassword(User.DEFAULT_PWD);
            childUser.setAuthority(authority);
            childUser.setLevel(parentUser.getLevel() + 1);
            childUser.setDisable(0);
            childUser.setSuperUser(parentUser);
            childUser.save();
            return new Pair<>(BaseResp.SUCCESS, uid);
        } catch (Exception e){
            logger.error("failed to add employee", e);
            return new Pair<>(BaseResp.UNKNOWN, null);
        }

    }


}
