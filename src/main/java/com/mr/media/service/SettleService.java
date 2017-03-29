package com.mr.media.service;

import com.avaje.ebean.Ebean;
import com.mr.media.model.Settle;
import com.mr.media.model.User;
import com.mr.media.response.BaseResp;
import com.mr.media.response.settle.GetAllSettleResp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by tanjingru on 29/03/2017.
 */

@Service
public class SettleService {

    final Logger logger = LoggerFactory.getLogger(getClass());


    public GetAllSettleResp getAllSettle(int year, int month){
        Date date = generateDateFromYearAndMonth(year, month);
        List<Settle>  settles = Ebean.find(Settle.class).where()
                                .eq("date", date).findList();
        return new GetAllSettleResp(BaseResp.SUCCESS, settles);
    }

    private Date generateDateFromYearAndMonth(int year, int month){
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.YEAR, year);
        return calendar.getTime();
    }

}


