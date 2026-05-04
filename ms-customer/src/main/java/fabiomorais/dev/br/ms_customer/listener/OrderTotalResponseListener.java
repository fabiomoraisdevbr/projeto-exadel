package fabiomorais.dev.br.ms_customer.listener;

import fabiomorais.dev.br.ms_customer.dto.CustomerResponse;
import fabiomorais.dev.br.ms_customer.repository.CustomerRepository;
import fabiomorais.dev.br.ms_customer.service.CustomerRedisService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;


@Component
public class OrderTotalResponseListener {

    @Autowired
    private CustomerRedisService customerRedisService;

    @Autowired
    private CustomerRepository customerRepository;


    @RabbitListener(queues = "order.total.response")
    public Mono<Void> consume(OrderTotalMessage message) {
        Long id = message.getId();
        double totalAmount = message.getTotalAmount();

        return customerRepository.findById(id)
                .map(customer -> new CustomerResponse(
                        id,
                        customer.getCustomer_name(),
                        customer.getEmail(),
                        totalAmount,
                        "SUCCESS"
                ))
                .flatMap(response -> customerRedisService.saveCustomerResponse(id, response))
                .onErrorResume(error -> {
                    // Tratar erro
                    return Mono.empty();
                });
    }
}