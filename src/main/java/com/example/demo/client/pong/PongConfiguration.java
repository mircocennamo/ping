package com.example.demo.client.pong;

import it.interno.platform.starter.core.commons.ClientGroup;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.support.RestClientHttpServiceGroupConfigurer;

public class PongConfiguration {

    @Bean
    RestClientHttpServiceGroupConfigurer pingPongConfigurer() {

        // questa logica è applicata a tutti i client del gruppo ClientGroup.PINGPONGGROUPNAME

        return groups -> groups
                .filterByName(ClientGroup.PINGPONGGROUPNAME)
                .forEachClient((group, builder) -> {
                    builder.defaultHeader("X-SERVICE", "PINGPONG");
                });
    }
}
