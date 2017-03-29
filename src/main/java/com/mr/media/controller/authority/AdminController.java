package com.mr.media.controller.authority;

import com.mr.media.model.User;
import com.mr.media.response.BaseResp;
import com.mr.media.response.authority.admin.GetPagedActorsResp;
import com.mr.media.response.authority.admin.GetPagedAgentsResp;
import com.mr.media.service.UserService;
import com.mr.media.service.authority.AdminService;
import com.mr.media.tool.Pair;
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

//    /**
//     * 管理员权限查看所有经纪人（分页）
//     */
//    @RequestMapping(value = "/employee/agents", method = RequestMethod.GET)
//    public BaseResp getPagedAgents(String token, Integer pageId, Integer pageSize, Integer disable, String realName){
//
//        User user = userService.findUserByToken(token);
//        if(user.getRole() != User.ACTOR_ROLE){
//            return new BaseResp(BaseResp.PERMITION_NOT_ALLOW);
//        }
//
//        Pair<Integer, List<User>> pair = adminService.getPagedEmployees(pageId, pageSize, User.AGENT_ROLE, disable, realName);
//
//        List<GetPagedAgentsResp.Agent> agents = pair.getSecond().stream().map(
//                o -> {
//                    GetPagedAgentsResp.Agent agent = new GetPagedAgentsResp.Agent();
//                    agent.uid = o.getUid();
//                    agent.realName = o.getRealName();
//                    agent.level = o.getLevel();
//                    agent.parentName = o.getSuperUser().getRealName();
//                    agent.subNumber = adminService.getSubActorsNumber(o.getUid());
//                    agent.tel = o.getPhoneNumber();
//                    agent.weChat = o.getWechatNumber();
//                    return agent;
//                }
//        ).collect(Collectors.toList());
//
//        return new GetPagedAgentsResp(BaseResp.SUCCESS, pageId, pageSize, pair.getFirst(), agents);
//
//    }

//    /**
//     * 管理员权限查看所有艺人（分页）
//     */
//    @RequestMapping(value = "/employee/actors", method = RequestMethod.GET)
//    public BaseResp getPagedActors(String token, Integer pageId, Integer pageSize, Integer disable, String realName){
//
//        User user = userService.findUserByToken(token);
//        if(user.getRole() != User.ADMIN_ROLE){
//            return new BaseResp(BaseResp.PERMITION_NOT_ALLOW);
//        }
//
//        Pair<Integer, List<User>> pair = adminService.getPagedEmployees(pageId, pageSize, User.ACTOR_ROLE, disable, realName);
//
//        List<GetPagedActorsResp.Actor> actors = pair.getSecond().stream().map(
//                o -> {
//                    GetPagedActorsResp.Actor actor = new GetPagedActorsResp.Actor();
//                    actor.uid = o.getUid();
//                    actor.realName = o.getRealName();
//                    actor.idNumber = o.getIdNumber();
//                    actor.tel = o.getPhoneNumber();
//                    actor.weChat = o.getWechatNumber();
//                    actor.active = o.getActive();
//                    actor.level = o.getLevel();
//                    actor.location = o.getLocation();
//                    actor.parentName = o.getSuperUser().getRealName();
//                    return actor;
//                }
//        ).collect(Collectors.toList());
//
//        return new GetPagedActorsResp(BaseResp.SUCCESS, pageId, pageSize, pair.getFirst(), actors);
//
//    }
}
