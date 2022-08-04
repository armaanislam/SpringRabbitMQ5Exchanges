package com.rabbitmqtutorial.rabbitmqdemo.configuration;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfiguration {

    public static final String ROUTING_A = "routing.A";
    public static final String ROUTING_B = "routing.B";

    @Bean
    Queue queueA() { // Queue from amqp
        return new Queue("queue.A", false);
    }

    @Bean
    Queue queueB() { // Queue from amqp
        return new Queue("queue.B", false);
    }

    @Bean
    Queue allQueue() { // Queue from amqp
        return new Queue("queue.all", false);
    }

//    // Direct Exchange
//    @Bean
//    DirectExchange exchange() {
//        return new DirectExchange("exchange.direct");
//    }
//
//    @Bean
//     Binding bindingA(Queue queueA, DirectExchange exchange) {
//        return BindingBuilder.bind(queueA).to(exchange).with(ROUTING_A);
//    }
//
//    @Bean
//    Binding bindingB(Queue queueB, DirectExchange exchange) {
//        return BindingBuilder.bind(queueB).to(exchange).with(ROUTING_B);
//    }

//    // Fanout Exchange
//    @Bean
//    FanoutExchange exchange() {
//        return new FanoutExchange("exchange.fanout");
//    }
//
//    @Bean
//    Binding bindingA(Queue queueA, FanoutExchange exchange) {
//        return BindingBuilder.bind(queueA).to(exchange); // Fanout does not contain any routing key
//    }
//
//    @Bean
//    Binding bindingB(Queue queueB, FanoutExchange exchange) {
//        return BindingBuilder.bind(queueB).to(exchange); // Fanout does not contain any routing key
//    }

//    // Topic Exchange
//    @Bean
//    TopicExchange exchange() {
//        return new TopicExchange("exchange.topic");
//    }
//
//    @Bean
//    Binding bindingA(Queue queueA, TopicExchange exchange) {
//        return BindingBuilder.bind(queueA).to(exchange).with(ROUTING_A);
//    }
//
//    @Bean
//    Binding bindingB(Queue queueB, TopicExchange exchange) {
//        return BindingBuilder.bind(queueB).to(exchange).with(ROUTING_B);
//    }
//
//    @Bean
//    Binding bindingC(Queue allQueue, TopicExchange exchange) {
//        return BindingBuilder.bind(allQueue).to(exchange).with("routing.*");
//    }

    // Header, Default Exchange
    @Bean
    HeadersExchange exchange() {
        return new HeadersExchange("exchange.header");
    }

    @Bean
    Binding bindingA(Queue queueA, HeadersExchange exchange) {
        return BindingBuilder.bind(queueA).to(exchange).where("colour").matches("red");
    }

    @Bean
    Binding bindingB(Queue queueB, HeadersExchange exchange) {
        return BindingBuilder.bind(queueB).to(exchange).where("colour").matches("blue");
    }

    @Bean
    Binding bindingC(Queue allQueue, HeadersExchange exchange) {
        return BindingBuilder.bind(allQueue).to(exchange).where("colour").matches("green");
    }

    // Message Conversion, RabbitTemplate
    @Bean
    MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    RabbitTemplate rabbitTemplate(ConnectionFactory factory) { // Connection factory from amqp.core
        RabbitTemplate rabbitTemplate = new RabbitTemplate(factory);
        rabbitTemplate.setMessageConverter(messageConverter());
        return rabbitTemplate;
    }
}


