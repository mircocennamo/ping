package com.example.demo.controller;

import com.example.demo.exceptions.PongException;
import com.example.demo.response.ApiResponse;
import com.example.demo.response.Responses;
import com.example.demo.service.PongService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    public ResponseEntity<ApiResponse<String>> ping() {
        log.info("PingController -> Ping request received");
        throw new PongException("PingController -> Ping request received");
        //return ResponseEntity.ok(Responses.success("pong"));

    }

    @GetMapping(path = "/pingInvokePongService")
    public ApiResponse<String> pingInvokePongService() {
        log.info("PingController -> Ping pingInvokePongService request received");

        return Responses.success(pongService.getPong());
    }
}
