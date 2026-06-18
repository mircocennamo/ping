package com.example.demo.controller;

import com.example.demo.service.PongService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@Slf4j
public class PingController {

    private final PongService pongService;

    public PingController(PongService pongService) {
        this.pongService = pongService;
    }

    @GetMapping(path = "/ping")
    public String ping() {
        log.info("PingController -> Ping request received");
        return "pong";
    }

    @GetMapping(path = "/pingInvokePongService")
    public String pingInvokePongService() {
        log.info("PingController -> Ping pingInvokePongService request received");
        return pongService.getPong();
    }
}
