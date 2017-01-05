package com.mr.media.controller;

import org.springframework.web.bind.annotation.*;

/**
 * Created by tanjingru on 06/01/2017.
 */
@RestController
public class TestController {
    @RequestMapping("/")
    @ResponseBody
    String home() {
        return "Hello World!";
    }
}
