package com.deviceregistry.conf;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * The Class RabbitMqConfiguration.
 */
@Configuration
public class RabbitMqConfiguration {
	
	/** The queue name. */
	@Value("${service.rabbitmq.queue}")
	private String queueName;
	
	/** The exchange name. */
	@Value("${service.rabbitmq.exhange}")
	private String exchangeName;

    /**
     * Queue.
     *
     * @return the queue
     */
    @Bean
    Queue queue() {
        return new Queue(queueName, false);
    }

    /**
     * Exchange.
     *
     * @return the topic exchange
     */
    @Bean
    TopicExchange exchange() {
        return new TopicExchange(exchangeName);
    }

    /**
     * Binding.
     *
     * @param queue the queue
     * @param exchange the exchange
     * @return the binding
     */
    @Bean
    Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(queueName);
    }
}
