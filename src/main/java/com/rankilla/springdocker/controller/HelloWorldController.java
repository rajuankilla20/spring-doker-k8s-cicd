package com.rankilla.springdocker.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;

/**
 * Created by Vani on 8/9/2020.
 */
@RestController
@RequestMapping("/api")
public class HelloWorldController {

    private static final  Logger LOGGER = LoggerFactory.getLogger(HelloWorldController.class);

    @Value("${HOSTNAME:LOCAL}")
    private String hostName;


    @GetMapping("/hello-world")
    @ResponseBody
    public String getHello(){
        String data = " Hello world V1 : " + hostName;
        LOGGER.info(data);
        return  data;
    }

    @GetMapping("/hello-india")
    @ResponseBody
    public String getHelloIndia(){

        String data = " Hello world V1 : " + hostName;
        LOGGER.info(data);
        return  data;
    }
}
