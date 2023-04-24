package com.skull.shop.configs;

import org.springframework.amqp.core.*;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.CompositeMessageConverter;
import org.springframework.messaging.converter.StringMessageConverter;
import org.springframework.retry.backoff.ExponentialBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.*;
import java.util.concurrent.Executor;

@Configuration
public class RabbitMQConfig {
    public static final String EXCHANGE_NAME = "skull-chicken-exchange";
    public static final String QUEUE_NAME = "skull-chicken-queue";
    public static final String DEAD_QUEUE_NAME = "skull-dead-queue";

    @Bean
    Queue queue() {
        return QueueBuilder
                .durable(QUEUE_NAME)
                .withArgument("x-dead-letter-exchange", EXCHANGE_NAME)
                .withArgument("x-dead-letter-routing-key", "chicken.faulty")
                .build();
    }

    @Bean
    Queue deadQueue() {
        return new Queue(DEAD_QUEUE_NAME, false);
    }

    @Bean
    DirectExchange exchange() {
        return new DirectExchange(EXCHANGE_NAME);
    }

    @Bean
    Binding binding(Queue queue, DirectExchange exchange) {
        return BindingBuilder
                .bind(queue)
                .to(exchange)
                .with("chicken.tasty");
    }

    @Bean
    Binding deadBinding(Queue deadQueue, DirectExchange exchange) {
        return BindingBuilder
                .bind(deadQueue)
                .to(exchange)
                .with("chicken.faulty");
    }


// must be set manually using containerFactory
//    @Bean
//    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactoryCustom(ConnectionFactory connectionFactory) {
//        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
//        factory.setConnectionFactory(connectionFactory);
//        factory.setMessageConverter(new Jackson2JsonMessageConverter());
//        return factory;
//    }

    //        set default
    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(
            SimpleRabbitListenerContainerFactoryConfigurer configurer,
            ConnectionFactory connectionFactory, Executor executor,
            @Qualifier("simpleConverter") MessageConverter messageConverter) {

        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        configurer.configure(factory, connectionFactory);
        factory.setDefaultRequeueRejected(false);
        factory.setMessageConverter(messageConverter);
        factory.setTaskExecutor(executor);

        return factory;
    }

    @Bean
    public ThreadPoolTaskExecutor executor() {
        ThreadPoolTaskExecutor pool = new ThreadPoolTaskExecutor();
        pool.setCorePoolSize(10);
        pool.setMaxPoolSize(10);
        pool.setThreadNamePrefix("SkullExecutor-");
        pool.setWaitForTasksToCompleteOnShutdown(true);
        return pool;
    }

    @Bean
    public MessageConverter contentTypeDelegatingMessageConverter() {
        ContentTypeDelegatingMessageConverter converter = new ContentTypeDelegatingMessageConverter();
        converter.addDelegate("application/json", new Jackson2JsonMessageConverter());
        converter.addDelegate("application/xml", new MarshallingMessageConverter());
        return converter;
    }

    @Bean
    public MessageConverter jsonConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public MessageConverter simpleConverter() {
        return new SimpleMessageConverter();
    }

    @Bean
    public MessageConverter customDelegatingConverter(MessageConverter simpleConverter, MessageConverter jsonConverter) {
        return new MessageConverter() {
            @Override
            public Message toMessage(Object object, MessageProperties messageProperties) throws MessageConversionException {
                return jsonConverter.toMessage(object, messageProperties);
            }

            @Override
            public Object fromMessage(Message message) throws MessageConversionException {
                try {
                    return jsonConverter.fromMessage(message);
                } catch (MessageConversionException e) {
                    return simpleConverter.fromMessage(message);
                }
            }
        };
    }


}
