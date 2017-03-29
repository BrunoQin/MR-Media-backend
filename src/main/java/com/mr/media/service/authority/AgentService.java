package com.mr.media.service.authority;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.Query;
import com.mr.media.model.Actor;
import com.mr.media.model.Agent;
import com.mr.media.model.User;
import com.mr.media.response.BaseResp;
import com.mr.media.response.authority.actor.UploadAvatarResp;
import com.mr.media.response.authority.agent.PositionResp;
import com.mr.media.response.authority.agent.UploadPictureResp;
import com.mr.media.service.UploadService;
import com.mr.media.service.UserService;
import com.mr.media.tool.Pair;
import com.mr.media.util.UidHelper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;


/**
 * Created by i321273 on 1/6/17.
 */

@Service
public class AgentService {

    @Autowired
    UserService userService;

    @Autowired
    UidHelper uidHelper;

    @Autowired
    UploadService uploadService;

    final Logger logger = LoggerFactory.getLogger(getClass());

//    public UploadAvatarResp upLoadAvatar(String token, MultipartFile uploadFile){
//        return uploadService.upLoadAvatar(token, uploadFile);
//    }
//
//    public UploadPictureResp upLoadPicture(String token, MultipartFile multipartFile){
//        return uploadService.UploadPicture(token, multipartFile);
//    }

    public Agent findAgentByUid(int uid){
        return Ebean.find(Agent.class).where()
                .eq("uid", uid)
                .findUnique();
    }

    public Pair<Integer, String> addEmployee(String token, String realname, Integer role){
        User parentUser = userService.findUserByToken(token);
        if(StringUtils.isEmpty(realname)){
            return new Pair<>(BaseResp.CREATED_REALNAME_NULL, null);
        }
        User childUser = userService.findUserByRealName(realname);
        if(childUser != null){
            return new Pair<>(BaseResp.CREATED_REALNAME_EXIST, null);
        }
        if(role == null){
            return new Pair<>(BaseResp.CREATED_AUTHORITY_NULL, null);
        }
        if(parentUser.getRole() > role || role > 3){
            return new Pair<>(BaseResp.CREATED_AUTHORITY_INVALID, null);
        }

        int employeeCount = Ebean.find(User.class).where()
                .eq("role", role)
                .findRowCount();

        String uid = uidHelper.generateUid(employeeCount, role);

        Ebean.beginTransaction();
        try{
            childUser = new User();
            childUser.setUid(uid);
            childUser.setPassword(User.DEFAULT_PWD);
            childUser.setRole(role);
            childUser.setLevel(parentUser.getLevel() + 1);
            childUser.setDisable(User.USER_ACTIVE);
            childUser.setSuperUser(parentUser);

            switch (role){
                case User.ACTOR_ROLE:
                    Actor actor = new Actor();
                    actor.setActive(Actor.ACTOR_OFFLINE);
                    actor.setRealName(realname);
                    actor.setActor(childUser);
                    actor.save();
                    break;
                case User.AGENT_ROLE:
                    Agent agent = new Agent();
                    agent.setRealName(realname);
                    agent.setAgent(childUser);
                    agent.save();
                    agent.save();
                    break;
            }

            childUser.save();
            Ebean.commitTransaction();
            return new Pair<>(BaseResp.SUCCESS, uid);
        } catch (Exception e){
            logger.error("failed to add employee", e);
            Ebean.rollbackTransaction();
            return new Pair<>(BaseResp.UNKNOWN, null);
        }

    }

    public Pair<Integer,PositionResp.Position> getUserPosition(String token) {
//        User parent = userService.findUserByToken(token);
//        Query<User> el;
//        List<User> children;
//        el = Ebean.find(User.class).where().
//                gt("level", parent.getLevel())
//                .orderBy("level");
//
//        children = el.findList();
//        HashSet<String> candidates = new HashSet<>();
//        List<User> users = new ArrayList<>();
//        String r = String.valueOf(parent.getId());
//        users.add(parent);
//        candidates.add(r);
//        for(int i = 0; i<children.size();i++){
//            User child = children.get(i);
//            String p = String.valueOf(child.getSuperUser().getId());
//            if(candidates.contains(p)){
//                candidates.add(String.valueOf(child.getId()));
//                users.add(child);
//            }
//        }
//
//        PositionResp.Position position = new PositionResp.Position();
//        position.parent =  new PositionResp.UserNode(parent.getSuperUser().getId(), parent.getSuperUser().getRealName(), 0);
//        users.remove(0);
//        HashMap<String, Integer> idMap = new HashMap<>();
//        for(int i = 0; i< users.size(); i ++){
//            idMap.put(String.valueOf(users.get(i).getId()), i);
//        }
//        position.children = users.stream().map(
//               o -> {
//                   PositionResp.UserNode userNode = new PositionResp.UserNode();
//                   userNode.realName = o.getRealName();
//                   userNode.uid = o.getId();
//                   Integer idx = idMap.get(String.valueOf(o.getSuperUser().getId()));
//                   if(idx == null){
//                       userNode.positionIndex = idMap.get(String.valueOf(o.getId()));
//                   }
//                   else{
//                       userNode.positionIndex = idx;
//                   }
//                   return userNode;
//               }
//        ).collect(Collectors.toList());
//
//        return new Pair<>(BaseResp.SUCCESS, position);
        return new Pair<>(BaseResp.SUCCESS, null);
    }

    public int agentRegister(String uid, String realName, String phoneNumber, String weChatNumber, String email, String idNumber){

        User user = userService.findUserByUid(uid);
        if(user != null) {
            return BaseResp.AGENT_REGISTER_EXIST_UID;
        }
        // 创建通知以及审核×××××待完成
        // 找到超级管理员，以后要改
        User superAdmin = userService.findUserByUid("ddd");

//        user.setRealName(realName);
//        user.setPhoneNumber(phoneNumber);
//        user.setWechatNumber(weChatNumber);
//        user.setEmail(email);
//        user.setSettleType(settleType);
//        user.setSettleAccount(settleAccount);
//        user.setAuthority(1);

        Ebean.beginTransaction();
        try{
            user = new User();
            user.setUid(uid);
            user.setLevel(1);
            user.setRole(User.AGENT_ROLE);
            user.setDisable(User.USER_ACTIVE);
            user.setSuperUser(superAdmin);

            Agent agent = new Agent();
            agent.setRealName(realName);
            agent.setPhoneNumber(phoneNumber);
            agent.setWechatNumber(weChatNumber);
            agent.setEmail(email);
            agent.setIdNumber(idNumber);

            agent.save();
            user.save();
            Ebean.commitTransaction();
            return BaseResp.SUCCESS;
        } catch (Exception e) {
            logger.error("failed to register agent", e);
            Ebean.rollbackTransaction();
            return BaseResp.UNKNOWN;
        }

    }

//    public UploadPictureResp uploadPictures(String token, MultipartFile frontPicture, MultipartFile backPicture) {
//        UploadPictureResp result1 = upLoadPicture(token, frontPicture);
//        UploadPictureResp result2 = upLoadPicture(token, backPicture);
//        int error = 0;
//        if(result1.errCode != BaseResp.SUCCESS) error = result1.errCode;
//        if(result2.errCode != BaseResp.SUCCESS) error = result2.errCode;
//        return new UploadPictureResp(error);
//    }
}
