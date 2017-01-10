package com.mr.media.service.authority;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.Query;
import com.mr.media.model.User;
import com.mr.media.response.BaseResp;
import com.mr.media.response.authority.agent.PositionResp;
import com.mr.media.service.UserService;
import com.mr.media.tool.Pair;
import com.mr.media.util.UidHelper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import static org.codehaus.groovy.runtime.DefaultGroovyMethods.collect;

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
            childUser.setDisable(User.USER_ACTIVE);
            childUser.setSuperUser(parentUser);
            childUser.save();
            return new Pair<>(BaseResp.SUCCESS, uid);
        } catch (Exception e){
            logger.error("failed to add employee", e);
            return new Pair<>(BaseResp.UNKNOWN, null);
        }

    }

    public Pair<Integer,PositionResp.Position> getUserPosition(String token) {
        User parent = userService.findUserByToken(token);
        Query<User> el;
        List<User> children;
        el = Ebean.find(User.class).where().
                gt("level", parent.getLevel())
                .orderBy("level");

        children = el.findList();
        HashSet<String> candidates = new HashSet<>();
        List<User> users = new ArrayList<>();
        String r = String.valueOf(parent.getId());
        users.add(parent);
        candidates.add(r);
        for(int i = 0; i<children.size();i++){
            User child = children.get(i);
            String p = String.valueOf(child.getSuperUser().getId());
            if(candidates.contains(p)){
                candidates.add(String.valueOf(child.getId()));
                users.add(child);
            }
        }

        PositionResp.Position position = new PositionResp.Position();
        position.parent =  new PositionResp.UserNode(parent.getSuperUser().getId(), parent.getSuperUser().getUsername(), 0);
        users.remove(0);
        HashMap<String, Integer> idMap = new HashMap<>();
        for(int i = 0; i< users.size(); i ++){
            idMap.put(String.valueOf(users.get(i).getId()), i);
        }
        position.children = users.stream().map(
               o -> {
                   PositionResp.UserNode userNode = new PositionResp.UserNode();
                   userNode.username = o.getUsername();
                   userNode.uid = o.getId();
                   Integer idx = idMap.get(String.valueOf(o.getSuperUser().getId()));
                   if(idx == null){
                       userNode.positionIndex = idMap.get(String.valueOf(o.getId()));
                   }
                   else{
                       userNode.positionIndex = idx;
                   }
                   return userNode;
               }
        ).collect(Collectors.toList());

        return new Pair<>(BaseResp.SUCCESS, position);
    }
}
