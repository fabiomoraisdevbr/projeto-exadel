package fabiomorais.dev.br.ms_customer.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.JacksonJsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String QUEUE_REQUEST= "order.total.request";
    public static final String QUEUE_RESPONSE= "order.total.response";

    @Bean
    public Queue orderTotalRequestQueue() {
        return new Queue(QUEUE_REQUEST, true);
    }

    @Bean
    public Queue orderTotalResponseQueue() {
        return new Queue(QUEUE_RESPONSE, true);
    }

    @Bean
    public JacksonJsonMessageConverter messageConverter() {
        return new JacksonJsonMessageConverter();
    }
}