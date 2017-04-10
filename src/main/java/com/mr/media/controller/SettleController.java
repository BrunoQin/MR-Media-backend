package com.mr.media.controller;

import com.mr.media.request.settle.AddSettleRecordReq;
import com.mr.media.response.BaseResp;
import com.mr.media.response.settle.GetAllSettleResp;
import com.mr.media.service.SettleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by tanjingru on 29/03/2017.
 */

@RestController
@RequestMapping(value = "/settle")
public class SettleController {
    final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    public SettleService settleService;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public GetAllSettleResp all(String token, int year, int month){
        return settleService.getAllSettle(year, month);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public BaseResp addSettleRecord(@RequestBody AddSettleRecordReq addSettleRecordReq){

        int errCode = settleService.addSettleRecord(addSettleRecordReq.uid, addSettleRecordReq.platformId, addSettleRecordReq.platformName, addSettleRecordReq.platform, addSettleRecordReq.year, addSettleRecordReq.month, addSettleRecordReq.amount);

        return new BaseResp(errCode);

    }

}
