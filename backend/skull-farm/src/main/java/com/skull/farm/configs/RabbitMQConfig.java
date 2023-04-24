package com.skull.farm.configs;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.amqp.dsl.Amqp;
import org.springframework.integration.annotation.Transformer;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.json.ObjectToJsonTransformer;
import org.springframework.messaging.MessageChannel;

@Configuration
public class RabbitMQConfig {
    @Bean
    public RabbitTemplate rabbitTemplateJson(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
        return rabbitTemplate;
    }


//    // send from channel to rabbitmq as if using reabbitTemplate.send
//    @Bean
//    public IntegrationFlow integrationFlow(RabbitTemplate rabbitTemplate) {
//        return IntegrationFlow.from("jsonConversionChannel")
//                .handle(Amqp.outboundAdapter(rabbitTemplate)
//                        .exchangeName("skull-chicken-exchange")
//                        .routingKey("chicken.tasty"))
//                .get();
//    }
//
//    @Bean
//    public MessageChannel jsonConversionChannel() {
//        return new DirectChannel();
//    }
//
//    // subscribe to input channel, convert messages, put in output channel
//    @Bean
//    @Transformer(inputChannel = "jsonConversionChannel", outputChannel = "toRabbit")
//    public ObjectToJsonTransformer objectToJsonTransformer() {
//        return new ObjectToJsonTransformer();
//    }
//
//    @Bean
//    public MessageChannel toRabbit() {
//        return new DirectChannel();
//    }

}
