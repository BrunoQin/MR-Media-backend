package com.mr.media.controller.authority;

import com.mr.media.model.User;
import com.mr.media.response.BaseResp;
import com.mr.media.response.authority.admin.GetPagedActorsResp;
import com.mr.media.response.authority.admin.GetPagedAgentsResp;
import com.mr.media.service.UserService;
import com.mr.media.service.authority.AdminService;
import javafx.util.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by i321273 on 1/6/17.
 */

@RestController
@RequestMapping(value = "/admin")
public class AdminController {

    final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    AdminService adminService;

    @Autowired
    UserService userService;

    /**
     * 管理员权限查看所有经纪人（分页）
     */
    @RequestMapping(value = "/employee/agents", method = RequestMethod.GET)
    public BaseResp getPagedAgents(String token, Integer pageId, Integer pageSize, Integer disable, String username){

        User user = userService.findUserByToken(token);
        if(user.getAuthority() != User.ADMIN_AUTHORITY){
            return new BaseResp(BaseResp.PERMITION_NOT_ALLOW);
        }

        Pair<Integer, List<User>> pair = adminService.getPagedEmployees(pageId, pageSize, User.AGENT_AUTHORITY, disable, username);

        List<GetPagedAgentsResp.Agent> agents = pair.getValue().stream().map(
                o -> {
                    GetPagedAgentsResp.Agent agent = new GetPagedAgentsResp.Agent();
                    agent.uid = o.getUid();
                    agent.username = o.getUsername();
                    return agent;
                }
        ).collect(Collectors.toList());

        return new GetPagedAgentsResp(BaseResp.SUCCESS, pageId, pageSize, pair.getKey(), agents);

    }

    /**
     * 管理员权限查看所有艺人（分页）
     */
    @RequestMapping(value = "/employee/actors", method = RequestMethod.GET)
    public BaseResp getPagedActors(String token, Integer pageId, Integer pageSize, Integer disable, String username){

        User user = userService.findUserByToken(token);
        if(user.getAuthority() != User.ADMIN_AUTHORITY){
            return new BaseResp(BaseResp.PERMITION_NOT_ALLOW);
        }

        Pair<Integer, List<User>> pair = adminService.getPagedEmployees(pageId, pageSize, User.ACTOR_AUTHORITY, disable, username);

        List<GetPagedActorsResp.Actor> actors = pair.getValue().stream().map(
                o -> {
                    GetPagedActorsResp.Actor actor = new GetPagedActorsResp.Actor();
                    actor.uid = o.getUid();
                    actor.username = o.getUsername();
                    return actor;
                }
        ).collect(Collectors.toList());

        return new GetPagedActorsResp(BaseResp.SUCCESS, pageId, pageSize, pair.getKey(), actors);

    }
}
