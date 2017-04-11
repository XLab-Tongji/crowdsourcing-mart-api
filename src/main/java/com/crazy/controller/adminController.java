package com.crazy.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

/**
 * Created by jieping on 2017-04-06.
 */
@Controller
public class adminController {

    private static final Logger LOGGER = LoggerFactory.getLogger(adminController.class);

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView getLoginPage(@RequestParam Optional<String> error) {
        LOGGER.debug("Getting login page, error={}", error);
        return new ModelAndView("login", "error", error);

    }
    @RequestMapping(value="/login",method = RequestMethod.POST)
    public String getLoginPage(String name)
    {
        return "aa";
    }




}