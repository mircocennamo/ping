package com.example.demo.client.pong;

import it.interno.platform.starter.core.commons.ClientGroup;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.service.registry.ImportHttpServices;

@Configuration
@ImportHttpServices(
        group = ClientGroup.PINGPONGGROUPNAME,
        types = PongClient.class
)
public class ClientsConfiguration {
}
