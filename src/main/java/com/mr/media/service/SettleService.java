package com.mr.media.service;

import com.avaje.ebean.Ebean;
import com.mr.media.model.Actor;
import com.mr.media.model.Settle;
import com.mr.media.model.User;
import com.mr.media.response.BaseResp;
import com.mr.media.response.settle.GetAllSettleResp;
import com.mr.media.service.authority.ActorService;
import com.mr.media.util.DateHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by tanjingru on 29/03/2017.
 */

@Service
public class SettleService {

    final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    UserService userService;

    @Autowired
    ActorService actorService;

    public GetAllSettleResp getAllSettle(int year, int month){
        Date date = DateHelper.generateDateFromYearAndMonth(year, month);
        List<Settle>  settles = Ebean.find(Settle.class).where()
                                .eq("date", date).findList();

        List<GetAllSettleResp.SettleInfo> settleInfos = settles.stream().map(o -> {
            GetAllSettleResp.SettleInfo settleInfo = new GetAllSettleResp.SettleInfo();
            settleInfo.uid = userService.findUserById(o.getActor().getActor().getId()).getUid();
            settleInfo.platform = o.getPlatform();
            settleInfo.platformId = o.getPlatformId();
            settleInfo.platformName = o.getPlatformName();
            settleInfo.amount = o.getAmount();
            settleInfo.date = o.getDate();
            return settleInfo;
        }).collect(Collectors.toList());

        return new GetAllSettleResp(BaseResp.SUCCESS, settleInfos);
    }

    public int addSettleRecord(String uid, String platformId, String platformName, String platform, int year, int month, int amount){

        //容错 todo
        User user = userService.findUserByUid(uid);
        if(user.getRole() != User.ACTOR_ROLE){
            return BaseResp.USER_NOT_ACTOR;
        }
        Actor actor = actorService.findActorByUid(user.getId());

        try{

            Settle settle = new Settle();
            settle.setActor(actor);
            settle.setPlatform(platform);
            settle.setPlatformId(platformId);
            settle.setPlatformName(platformName);
            settle.setDate(DateHelper.generateDateFromYearAndMonth(year, month - 1));
            settle.setAmount(amount);

            settle.save();
            return BaseResp.SUCCESS;

        } catch (Exception e){
            e.printStackTrace();
            return BaseResp.UNKNOWN;
        }

    }


}


