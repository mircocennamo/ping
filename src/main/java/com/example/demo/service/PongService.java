package com.example.demo.service;

import com.example.demo.client.PongClient;
import org.springframework.stereotype.Service;

@Service
public class PongService {
    private final PongClient pongClient;

    public PongService(PongClient pongClient) {
        this.pongClient = pongClient;
    }

    public String getPong() {
        return pongClient.getPong();
    }
}
