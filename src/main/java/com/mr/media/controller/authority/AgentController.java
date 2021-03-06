package com.mr.media.controller.authority;

import com.mr.media.request.authority.agent.AddEmployeeReq;
import com.mr.media.request.authority.agent.AgentRegisterReq;
import com.mr.media.response.BaseResp;
import com.mr.media.response.authority.agent.AddEmployeeResp;
import com.mr.media.response.review.GetAllReviewsResp;
import com.mr.media.service.authority.AgentService;

import com.mr.media.tool.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by i321273 on 1/6/17.
 */

@RestController
@RequestMapping(value = "/agent")
public class AgentController {

    final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    AgentService agentService;


    @RequestMapping(value = "/add_employee", method = RequestMethod.POST)
    public BaseResp addEmployee(String token, @RequestBody AddEmployeeReq addEmployeeReq){
        Pair<Integer, String> pair = agentService.addEmployee(token, addEmployeeReq.realName, addEmployeeReq.role);
        AddEmployeeResp.Employee employee = new AddEmployeeResp.Employee();
        employee.uid = pair.getSecond();
        return new AddEmployeeResp(pair.getFirst(), employee);
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public BaseResp agentRegister(@RequestBody AgentRegisterReq agentRegisterReq){

        int errCode = agentService.agentRegister(agentRegisterReq.uid, agentRegisterReq.realName, agentRegisterReq.avatar, agentRegisterReq.phoneNumber, agentRegisterReq.weChatNumber, agentRegisterReq.email, agentRegisterReq.idNumber);
        return new BaseResp(errCode);

    }

    @RequestMapping(value = "/reviews", method = RequestMethod.GET)
    public BaseResp getReviews(String token){
        return agentService.getAllReviewsResp(token);
    }

//    @RequestMapping(value = "register/upload_picture", method = RequestMethod.POST)
//    public BaseResp agentUploadPictures(String token, MultipartFile frontPicture, MultipartFile backPicture){
//        return agentService.uploadPictures(token, frontPicture, backPicture);
//    }

}
