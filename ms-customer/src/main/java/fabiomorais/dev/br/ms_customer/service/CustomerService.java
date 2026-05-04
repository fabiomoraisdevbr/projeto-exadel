package fabiomorais.dev.br.ms_customer.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import fabiomorais.dev.br.ms_customer.dto.CustomerResponse;
import fabiomorais.dev.br.ms_customer.entity.CustomerEntity;
import fabiomorais.dev.br.ms_customer.listener.OrderTotalMessageRequest;
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
    private RabbitmqTemplate rabbitTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    private static final String QUEUE_NAME = "order.total.request";

    public Mono<CustomerResponse> findById(Long id, boolean includeTotalOrders) {

        if (!includeTotalOrders) {
            return customerRepository.findById(id)
                    .switchIfEmpty(Mono.error(new RuntimeException("Customer not found")))
                    .map(this::toResponse);
        }

        String redisKey = "customer::" + id;

        return redisTemplate.opsForValue().get(redisKey) //get from redis

                .map(obj -> objectMapper.convertValue(obj, CustomerResponse.class)) //return if hit
                .switchIfEmpty( //send event if miss
                        customerRepository.findById(id)
                                .switchIfEmpty(Mono.error(new RuntimeException("Customer not found")))
                                .flatMap(customer -> {
                                    rabbitTemplate.convertAndSend(
                                            QUEUE_NAME,
                                            new OrderTotalMessageRequest(id)
                                    );

                                    return Mono.just(new CustomerResponse(
                                            customer.getId(),
                                            customer.getCustomer_name(),
                                            customer.getEmail(),
                                            null,
                                            "PENDING"
                                    ));
                                })
                );
    }

    private CustomerResponse toResponse(CustomerEntity entity) {
        return new CustomerResponse(
                entity.getId(),
                entity.getCustomer_name(),
                entity.getEmail(),
                null,
                "SUCCESS"
        );
    }
}
