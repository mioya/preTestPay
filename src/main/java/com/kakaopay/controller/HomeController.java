package com.kakaopay.controller;

import lombok.extern.apachecommons.CommonsLog;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@CommonsLog
@Controller
@EnableAutoConfiguration
public class HomeController {

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home(){
        return "home";
    }

}
