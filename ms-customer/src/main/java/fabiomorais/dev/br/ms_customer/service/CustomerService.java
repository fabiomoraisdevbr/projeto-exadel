package fabiomorais.dev.br.ms_customer.service;


import fabiomorais.dev.br.ms_customer.dto.CustomerResponse;
import fabiomorais.dev.br.ms_customer.entity.CustomerEntity;
import fabiomorais.dev.br.ms_customer.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;


@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ReactiveRedisTemplate<String, Object> redisTemplate;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    private static final String QUEUE_NAME = "order.total.request";

    public Mono<CustomerResponse> findById(Long id, boolean includeTotalOrders) {

        if (!includeTotalOrders) {
            return customerRepository.findById(id)
                    .switchIfEmpty(Mono.error(new RuntimeException("Customer not found")))
                    .map(this::toResponse);
        }

        String redisKey = "customer::" + id;

        // 🔥 Fluxo com Redis + Rabbit
        return redisTemplate.opsForValue().get(redisKey)

                // 🔹 Se tiver no cache → retorna
                .flatMap(Mono::just)

                // 🔹 Se NÃO tiver no cache
                .switchIfEmpty(
                        customerRepository.findById(id)
                                .switchIfEmpty(Mono.error(new RuntimeException("Customer not found")))
                                .flatMap(customer -> {

                                    // 📩 envia mensagem pro RabbitMQ
                                    rabbitTemplate.convertAndSend(QUEUE_NAME, id);

                                    // 🔄 resposta imediata
                                    return Mono.just(new CustomerResponse(
                                            customer.getId(),
                                            customer.getName(),
                                            customer.getEmail(),
                                            null // ainda não tem total
                                    ));
                                })
                );


    }

    private CustomerResponse toResponse(CustomerEntity entity) {
        return new CustomerResponse(
                entity.getId(),
                entity.getCustomer_name(),
                entity.getEmail(),
                -1.0,
                "SUCCESS"
        );
    }
}
