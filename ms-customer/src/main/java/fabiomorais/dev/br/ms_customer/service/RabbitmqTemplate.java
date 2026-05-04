package fabiomorais.dev.br.ms_customer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

@Component
public class RabbitmqTemplate {

    @Autowired
    private  RabbitTemplate springRabbitTemplate;

    public void convertAndSend(String queue, Object message) {

        springRabbitTemplate.convertAndSend("", queue, message);
    }
}