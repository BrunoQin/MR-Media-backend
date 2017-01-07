package com.mr.media.controller.authority;

import com.mr.media.request.BaseReq;
import com.mr.media.request.authority.agent.AddEmployeeReq;
import com.mr.media.response.BaseResp;
import com.mr.media.response.authority.agent.AddEmployeeResp;
import com.mr.media.response.authority.agent.PositionResp;
import com.mr.media.service.authority.AgentService;
import javafx.util.Pair;
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
        Pair<Integer, String> pair = agentService.addEmployee(token, addEmployeeReq.username, addEmployeeReq.authority);
        AddEmployeeResp.Employee employee = new AddEmployeeResp.Employee();
        employee.uid = pair.getValue();
        return new AddEmployeeResp(pair.getKey(), employee);
    }

    @RequestMapping(value = "/position", method = RequestMethod.GET)
    public BaseResp showAgentPosition(String token){
        Pair<Integer, PositionResp.Position> pair = agentService.getUserPosition(token);
        return new PositionResp(pair.getKey(), pair.getValue());
    }

}
