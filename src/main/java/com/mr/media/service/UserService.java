package com.mr.media.service;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.PagedList;
import com.mr.media.model.Platform;
import com.mr.media.model.User;
import com.mr.media.response.BaseResp;
import com.mr.media.response.user.LookUpAgentSubEmployeesResp;
import com.mr.media.response.user.SubEmployeeDetailResp;
import com.mr.media.response.user.SubEmployeesResp;
import com.mr.media.util.TokenHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.validation.constraints.Null;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Created by i321273 on 1/5/17.
 */

@Service
public class UserService {

    final Logger logger = LoggerFactory.getLogger(getClass());

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

    public SubEmployeesResp lookUpSubEmployees(String token, Integer pageId, Integer pageSize) {
        User user = findUserByToken(token);
        PagedList<User> pl =  Ebean.find(User.class).findPagedList(pageId, pageSize);
        int totalPage = pl.getTotalPageCount();
        return new SubEmployeesResp(0, pageId, pageSize, totalPage, pl.getList());
    }

    public SubEmployeeDetailResp getSubEmployeeDetail(String token, String uid) {
        User employee = findUserByUid(uid);
        if(employee == null) return new SubEmployeeDetailResp(BaseResp.USER_IS_NOT_EXIST, null, null);
        User inspector = findUserByToken(token);
        if(employee.getUid().equals(inspector.getUid())){
            SubEmployeeDetailResp.Employee detail_employee = new SubEmployeeDetailResp.Employee();
            detail_employee.realName = employee.getRealName();
            detail_employee.level = employee.getLevel();
            detail_employee.tel = employee.getPhoneNumber();
            detail_employee.weChat = employee.getWechatNumber();
            detail_employee.parentName = employee.getSuperUser().getRealName();
            detail_employee.settleType = employee.getSettleType();
            detail_employee.settleCount = employee.getSettleAccount();
            detail_employee.idNumber = employee.getIdNumber();
            List<SubEmployeeDetailResp.Platform> platforms = new ArrayList<>();
            if(employee.getAuthority() == User.ACTOR_AUTHORITY){
                List<Platform> platformList = Ebean.find(Platform.class).where()
                        .eq("uid", employee.getUid())
                        .findList();
                platforms = platformList.stream().map(o -> {
                    SubEmployeeDetailResp.Platform platform = new SubEmployeeDetailResp.Platform();
                    platform.name = o.getName();
                    platform.uid = o.getUid();
                    platform.validDay = o.getValidDay();
                    platform.validHour = o.getValidHour();
                    platform.giftCount = o.getGiftCount();
                    platform.settleCount = o.getSettleCount();
                    return platform;
                }).collect(Collectors.toList());
            }
            return new SubEmployeeDetailResp(BaseResp.SUCCESS, detail_employee, platforms);
        }
        User parent = employee.getSuperUser();
        // 向上递归搜索
        while(true){
            if(parent == null) break;
            if(inspector.getUid().equals(parent.getUid())){
                SubEmployeeDetailResp.Employee detail_employee = new SubEmployeeDetailResp.Employee();
                detail_employee.realName = employee.getRealName();
                detail_employee.level = employee.getLevel();
                detail_employee.tel = employee.getPhoneNumber();
                detail_employee.weChat = employee.getWechatNumber();
                detail_employee.parentName = employee.getSuperUser().getRealName();
                detail_employee.settleType = employee.getSettleType();
                detail_employee.settleCount = employee.getSettleAccount();
                detail_employee.idNumber = employee.getIdNumber();
                List<SubEmployeeDetailResp.Platform> platforms = new ArrayList<>();
                if(employee.getAuthority() == User.ACTOR_AUTHORITY){
                    List<Platform> platformList = Ebean.find(Platform.class).where()
                            .eq("uid", employee.getUid())
                            .findList();
                    platforms = platformList.stream().map(o -> {
                        SubEmployeeDetailResp.Platform platform = new SubEmployeeDetailResp.Platform();
                        platform.name = o.getName();
                        platform.uid = o.getUid();
                        platform.validDay = o.getValidDay();
                        platform.validHour = o.getValidHour();
                        platform.giftCount = o.getGiftCount();
                        platform.settleCount = o.getSettleCount();
                        return platform;
                    }).collect(Collectors.toList());
                }
                return new SubEmployeeDetailResp(BaseResp.SUCCESS, detail_employee, platforms);
            }
            parent = parent.getSuperUser();
        }
        return new SubEmployeeDetailResp(BaseResp.PERMISSION_DENIED, null, null);
    }

    public LookUpAgentSubEmployeesResp lookUpAgentSubEmployees(String uid, Integer authority){

        if(StringUtils.isEmpty(uid)){
            return new LookUpAgentSubEmployeesResp(BaseResp.LOOK_UP_SUB_EMPLOYUEES_NULL_UID, null);
        }

        User inspector = findUserByUid(uid);

        List<User> subEmployees = Ebean.find(User.class).where()
                .eq("super_id", inspector.getId())
                .eq("authority", authority)
                .findList();
        List<LookUpAgentSubEmployeesResp.Employee> employees = subEmployees.stream().map(o -> {
            LookUpAgentSubEmployeesResp.Employee employee = new LookUpAgentSubEmployeesResp.Employee();
            employee.realName = o.getRealName();
            employee.avatar = o.getAvatar();
            employee.level = o.getLevel();
            employee.tel = o.getPhoneNumber();
            employee.weChat = o.getWechatNumber();
            employee.idNumber = o.getIdNumber();
            return employee;
        }).collect(Collectors.toList());

        return new LookUpAgentSubEmployeesResp(BaseResp.SUCCESS, employees);

    }

    public BaseResp addPlatformForActor(String name, String uid, int validDay, int validHour, int giftCount, int settleCount){
        //容错
        Platform platform = new Platform();

        try{
            platform.setName(name);
            platform.setUid(uid);
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

}
