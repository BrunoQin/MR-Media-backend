package com.mr.media.controller;

import com.mr.media.response.settle.GetAllSettleResp;
import com.mr.media.service.SettleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    @RequestMapping(value = "all", method = RequestMethod.GET)
    public GetAllSettleResp all(String token, int year, int month){
        return settleService.getAllSettle(year, month);
    }
}
