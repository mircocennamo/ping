package com.example.demo.controller;

import com.example.demo.exceptions.PongException;
import com.example.demo.service.PongService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import it.interno.platform.starter.core.response.ApiResponse;
import it.interno.platform.starter.core.response.Responses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<ApiResponse<String>> ping() {
        log.info("PingController -> Ping request received");
        throw new PongException("PingController -> Ping request received");
        //return ResponseEntity.ok(Responses.success("pong"));

    }

    @Parameter(name = "traceparent", in = ParameterIn.HEADER, description = "Traceparent header for distributed tracing", required = false)
    @GetMapping(path = "/pingInvokePongService")
    public ApiResponse<String> pingInvokePongService() {
        log.info("PingController -> Ping pingInvokePongService request received");

        return Responses.success(pongService.getPong());
    }
}
