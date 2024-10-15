package com.example.demo.controller;

import com.example.demo.service.PongServiceReceiver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;

@RestController
@RequestMapping("/api")
public class PingController {
    @Autowired
    PongServiceReceiver pongServiceReceiver;

    @GetMapping(path = "/ping")
    public @ResponseBody String ping(){
        try{
            return pingHost();
        } catch (UnknownHostException e) {
            return "ping failed";
        }

    }

    @GetMapping(path = "/pingFromPong")
    public @ResponseBody String pingFromPong(){
        return pongServiceReceiver.getDataFromServicePong();

    }


    private String pingHost() throws UnknownHostException {
        InetAddress inetAddress = InetAddress.getLocalHost();
        String ip = inetAddress.getHostAddress();
        String hostname = inetAddress.getHostName();
        return String.format("pong IP: %s, Hostname: %s", ip, hostname);
    }
}
