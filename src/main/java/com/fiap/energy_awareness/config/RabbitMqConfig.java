package com.fiap.energy_awareness.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Bean
    public Queue cadastroProdutoQueue() {
        return new Queue("consumo-queue", true);
    }

    public void send(String email){
        rabbitTemplate.convertAndSend("consumo-queue", email);
    }
}
