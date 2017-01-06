package com.mr.media.controller.authority;

import com.mr.media.service.authority.AgentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
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

}
