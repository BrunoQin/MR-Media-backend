package com.mr.media.service;

import com.avaje.ebean.Ebean;
import com.mr.media.model.Actor;
import com.mr.media.model.Agent;
import com.mr.media.model.Platform;
import com.mr.media.model.User;
import com.mr.media.response.BaseResp;
import com.mr.media.response.user.LookUpAgentSubEmployeesResp;
import com.mr.media.response.user.LookUpSubEmployeeDetailResp;
import com.mr.media.service.authority.ActorService;
import com.mr.media.service.authority.AgentService;
import com.mr.media.util.TokenHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by i321273 on 1/5/17.
 */

@Service
public class UserService {

    final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    ActorService actorService;

    @Autowired
    AgentService agentService;

    public User findUserByRealName(String realName){
        return Ebean.find(User.class).where()
                .eq("real_name", realName)
                .findUnique();
    }

    public User findUserByUid(String uid){
        return Ebean.find(User.class).where()
                .eq("uid", uid)
                .findUnique();
    }

    public User findUserByToken(String token){
        return Ebean.find(User.class).where()
                .eq("token", token)
                .findUnique();
    }

    public User findUserByOpenId(String openId){
        return Ebean.find(User.class).where()
                .eq("open_id", openId)
                .findUnique();
    }

    public int login(String uid, String password){

        User user = Ebean.find(User.class).where()
                .eq("uid", uid)
                .eq("password", password)
                .findUnique();

        if(user == null){
            return BaseResp.PASSWORD_NOT_MATCH;
        }

        if(user.getDisable() == User.USER_DEACTIVE){
            return BaseResp.USER_DEACTIVE;
        }

        user.setToken(TokenHelper.newToken(uid));
        user.setValidTime(TokenHelper.newTokenValidTime());
        try {
            user.update();
            return BaseResp.SUCCESS;
        } catch (Exception e) {
            logger.error("用户登录失败", e);
            return BaseResp.UNKNOWN;
        }
    }

    public int changePassword(String token, String oldPassword, String newPassword, String confirmPassword){
        User user = findUserByToken(token);
        if(!user.getPassword().equals(oldPassword)){
            return BaseResp.WRONG_OLD_PASSWORD;
        }
        if(!newPassword.equals(confirmPassword)){
            return BaseResp.CONFIRM_NOT_MATCH;
        }
        if(oldPassword.equals(newPassword)){
            return BaseResp.NEW_AND_OLD_SAME;
        }

        user.setPassword(newPassword);
        try{
            user.update();
            return BaseResp.SUCCESS;
        } catch (Exception e){
            logger.error("修改密码错误", e);
            return BaseResp.UNKNOWN;
        }
    }

    public LookUpSubEmployeeDetailResp lookUpSubEmployeeDetail(String token, String uid) {
        User employee = findUserByUid(uid);
        if(employee == null) return new LookUpSubEmployeeDetailResp(BaseResp.USER_IS_NOT_EXIST, null, null);
        User inspector = findUserByToken(token);
        if(employee.getUid().equals(inspector.getUid()) || havePermissionToLookUp(inspector, employee)){
            LookUpSubEmployeeDetailResp.Employee detail_employee = userToEmployee(employee);
            List<LookUpSubEmployeeDetailResp.Platform> platforms = new ArrayList<>();
            if(employee.getRole() == User.ACTOR_ROLE){
                List<Platform> platformList = findActorPlatforms(employee);
                platforms = platformList.stream().map(o -> {
                    LookUpSubEmployeeDetailResp.Platform platform = new LookUpSubEmployeeDetailResp.Platform();
                    platform.name = o.getName();
                    platform.uid = employee.getUid();
                    platform.validDay = o.getValidDay();
                    platform.validHour = o.getValidHour();
                    platform.giftCount = o.getGiftCount();
                    platform.settleCount = o.getSettleCount();
                    return platform;
                }).collect(Collectors.toList());
            }
            return new LookUpSubEmployeeDetailResp(BaseResp.SUCCESS, detail_employee, platforms);
        }
        return new LookUpSubEmployeeDetailResp(BaseResp.PERMISSION_DENIED, null, null);
    }

    public LookUpAgentSubEmployeesResp lookUpAgentSubEmployees(String uid, Integer role){

        if(StringUtils.isEmpty(uid)){
            return new LookUpAgentSubEmployeesResp(BaseResp.LOOK_UP_SUB_EMPLOYUEES_NULL_UID, null);
        }

        User inspector = findUserByUid(uid);

        List<User> subEmployees = Ebean.find(User.class).where()
                .eq("super_id", inspector.getId())
                .eq("role", role)
                .findList();
        List<LookUpAgentSubEmployeesResp.Employee> employees = subEmployees.stream().map(o -> {
            LookUpAgentSubEmployeesResp.Employee employee = new LookUpAgentSubEmployeesResp.Employee();
            switch(role){
                case User.AGENT_ROLE:
                    Agent agent = agentService.findAgentByUid(o.getId());
                    employee.realName = agent.getRealName();
                    employee.avatar = agent.getAvatar();
                    employee.level = o.getLevel();
                    employee.tel = agent.getPhoneNumber();
                    employee.weChat = agent.getWechatNumber();
                    employee.idNumber = agent.getIdNumber();
                    break;
                case User.ACTOR_ROLE:
                    Actor actor = actorService.findActorByUid(o.getId());
                    employee.realName = actor.getRealName();
                    employee.avatar = actor.getAvatar();
                    employee.level = o.getLevel();
                    employee.tel = actor.getPhoneNumber();
                    employee.weChat = actor.getWechatNumber();
                    employee.idNumber = actor.getIdNumber();
                    break;
            }

            return employee;
        }).collect(Collectors.toList());

        return new LookUpAgentSubEmployeesResp(BaseResp.SUCCESS, employees);

    }

    public BaseResp addPlatformForActor(String name, String uid, int validDay, int validHour, int giftCount, int settleCount){
        //容错
        Platform platform = new Platform();

        try{
            platform.setName(name);
            platform.setActor(actorService.findActorByUid(findUserByUid(uid).getId()));
            platform.setValidDay(validDay);
            platform.setValidHour(validHour);
            platform.setGiftCount(giftCount);
            platform.setSettleCount(settleCount);
            platform.save();
            return new BaseResp(BaseResp.SUCCESS);
        } catch (Exception e){
            e.printStackTrace();
            return  new BaseResp(BaseResp.UNKNOWN);
        }

    }

    public List<Platform> findActorPlatforms(User actor){
        return Ebean.find(Platform.class).where()
                .eq("actor_id", actor.getId())
                .findList();
    }

    public LookUpSubEmployeeDetailResp.Employee userToEmployee(User employee){

        LookUpSubEmployeeDetailResp.Employee detail_employee = new LookUpSubEmployeeDetailResp.Employee();
        switch(employee.getRole()){
            case User.AGENT_ROLE:
                Agent agent = agentService.findAgentByUid(employee.getId());
                detail_employee.realName = agent.getRealName();
                detail_employee.level = employee.getLevel();
                detail_employee.tel = agent.getPhoneNumber();
                detail_employee.weChat = agent.getWechatNumber();
                detail_employee.parentName = agentService.findAgentByUid(employee.getSuperUser().getId()).getRealName();
                detail_employee.idNumber = agent.getIdNumber();
                break;
            case User.ACTOR_ROLE:
                Actor actor = actorService.findActorByUid(employee.getId());
                detail_employee.realName = actor.getRealName();
                detail_employee.level = employee.getLevel();
                detail_employee.tel = actor.getPhoneNumber();
                detail_employee.weChat = actor.getWechatNumber();
                detail_employee.parentName = actorService.findActorByUid(employee.getSuperUser().getId()).getRealName();
                detail_employee.idNumber = actor.getIdNumber();
                detail_employee.settleType = actor.getSettleType();
                detail_employee.settleCount = actor.getSettleAccount();
                detail_employee.idNumber = actor.getIdNumber();
                break;
        }
        return detail_employee;
    }

    public boolean havePermissionToLookUp(User inspector, User inspected){

        User parent = inspected.getSuperUser();

        int step = 0;

        while(true) {
            if (parent == null) return false;
            if (inspector.getUid().equals(parent.getUid())) {
                return true;
            }
            parent = parent.getSuperUser();
            step++;
            if(step > 100) break;
        }
        return false;
    }

}
