package fabiomorais.dev.br.ms_customer.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import fabiomorais.dev.br.ms_customer.dto.CustomerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Service
public class CustomerRedisService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private ObjectMapper objectMapper;


    public Mono<Void> saveCustomerResponse(Long id, CustomerResponse response) {
        String key = "customer::" + id;

        return Mono.fromRunnable(() ->
                redisTemplate.opsForValue().set(
                        key,
                        response,
                        Duration.ofMinutes(5)
                )
        );
    }
}