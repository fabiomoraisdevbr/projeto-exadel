package fabiomorais.dev.br.ms_order.listener;

import fabiomorais.dev.br.ms_order.OrderRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class OrderTotalRequestListener {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RabbitListener(queues = "order.total.request")
    public void consume(OrderTotalMessageRequest message) {

        orderRepository.sumTotalByCustomerId(message.getId())
                .defaultIfEmpty(0.0)
                .map(total -> new OrderTotalMessageResponse(message.getId(), total))
                .doOnNext(response ->
                        rabbitTemplate.convertAndSend("order.total.response", response)
                )
                .subscribe();
    }
}