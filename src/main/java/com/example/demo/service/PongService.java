package com.example.demo.service;



import com.example.demo.client.pong.PongClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.resilience.annotation.ConcurrencyLimit;
import org.springframework.resilience.annotation.Retryable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PongService {
    private final PongClient pongClient;

    public PongService(PongClient pongClient) {
        this.pongClient = pongClient;
    }

    // Just use it! No extra dependencies needed
    // NEW: Native @Retryable from Spring Boot 4.0 core!
    @Retryable(
            includes = {Exception.class, RuntimeException.class},
            maxRetries = 4,
            delayString = "500ms",
            multiplier = 1.5,  // Exponential backoff
            maxDelay = 3000    // Max 3 seconds between retries
    )
    @ConcurrencyLimit(limit = 10)  // NEW: Native concurrency control!
    public String getPong() {
        return pongClient.getPong();
    }



    public boolean pingFallback(Throwable ex) {
        log.warn("Sistema non disponibile");
        return false;
    }
}
