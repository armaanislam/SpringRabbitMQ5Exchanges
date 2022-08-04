package com.rabbitmqtutorial.rabbitmqdemo.controller;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.amqp.support.converter.SimpleMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Producer {

    @Autowired
    private RabbitTemplate rabbitTemplate;


//    // Direct Exchange
//    @Autowired
//    private DirectExchange exchange;
//
//    @PostMapping("/post")
//    public String send(@RequestBody Message message) {
//        rabbitTemplate.convertAndSend(exchange.getName(), "routing.A", message);
//        return "Message sent successfully!";
//    }


//    // Fanout Exchange
//    @Autowired
//    private FanoutExchange exchange;
//
//    @PostMapping("/post")
//    public String send(@RequestBody Message message) {
//        rabbitTemplate.convertAndSend(exchange.getName(), "", message); // Empty String for routing key
//        return "Message sent successfully!";
//    }


//    // Topic Exchange
//    @Autowired
//    private TopicExchange exchange;
//
//    @PostMapping("/post")
//    public String send(@RequestBody Message message) {
//        rabbitTemplate.convertAndSend(exchange.getName(), "routing.A", message); // Will send to Queue A and All Queue
//        return "Message sent successfully!";
//    }

//    // Header Exchange
//    @Autowired
//    private HeadersExchange exchange;
//
//    @PostMapping("/post/{message}")
//    public String send(@PathVariable(value = "message") String message) {
//        MessageProperties messageProperties = new MessageProperties();
//        messageProperties.setHeader("colour", message);
//        MessageConverter messageConverter = new SimpleMessageConverter();
//        Message message1 = messageConverter.toMessage(message, messageProperties); // Message from amqp core
//        rabbitTemplate.send(exchange.getName(), "", message1);
//        return "Message sent successfully!";
//    }

    // Default Exchange
    @Autowired
    private HeadersExchange exchange; // Will remain HeaderExchange

    @PostMapping("/post/{message}")
    public String send(@PathVariable(value = "message") String message) {
        rabbitTemplate.convertAndSend("queue.A", message); // Here routing key contains the queue name
        return "Message sent successfully!";
    }
}
