package com.example.demo.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;

@RestController
@RequestMapping("/api")
@Slf4j
public class PingController {


    // Valore iniettato da ConfigMap definita nel file deployment.yaml
    //@Value("${app.message:ciao}")
    //private String appMessage;

    @GetMapping(path = "/ping")
    public @ResponseBody String ping(){
        try{
            log.info("Ping request received");
            return pingHost();
        } catch (UnknownHostException e) {
            return "ping failed";
        }

    }





    private String pingHost() throws UnknownHostException {
        InetAddress inetAddress = InetAddress.getLocalHost();
        String ip = inetAddress.getHostAddress();
        String hostname = inetAddress.getHostName();
        return String.format("ciaociao" + "pong IP: %s, Hostname: %s", ip, hostname);
    }
}
