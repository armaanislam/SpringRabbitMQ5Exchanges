package com.rabbitmqtutorial.rabbitmqdemo.controller;

import com.rabbitmqtutorial.rabbitmqdemo.model.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Consumer {

//    // Direct, Fanout, Topic Exchange
//    @RabbitListener(queues = "queue.A")
//    private void receivedFromA(Message message) {
//        log.info("Message received from QUEUE A -> A{}", message);
//    }
//
//    @RabbitListener(queues = "queue.B")
//    private void receivedFromB(Message message) {
//        log.info("Message received from QUEUE B -> B{}", message);
//    }
//
//    @RabbitListener(queues = "queue.all")
//    private void receivedFromAll(Message message) {
//        log.info("Message received from QUEUE All -> {}", message);
//    }

    // Header, Default Exchange
    @RabbitListener(queues = "queue.A")
    private void receivedFromA(String message) {
        log.info("Message received from QUEUE A -> A{}", message);
    }

    @RabbitListener(queues = "queue.B")
    private void receivedFromB(String message) {
        log.info("Message received from QUEUE B -> B{}", message);
    }

    @RabbitListener(queues = "queue.all")
    private void receivedFromAll(String message) {
        log.info("Message received from QUEUE All -> {}", message);
    }
}
