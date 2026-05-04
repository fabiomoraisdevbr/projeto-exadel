package fabiomorais.dev.br.ms_customer.service;

import fabiomorais.dev.br.ms_customer.dto.CustomerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import java.time.Duration;
import org.springframework.data.redis.core.ReactiveRedisTemplate;

@Service
public class CustomerRedisService {

    @Autowired
    private ReactiveRedisTemplate<String, Object> redisTemplate;

    public Mono<Void> saveCustomerResponse(Long id, CustomerResponse response) {
        String key = "customer::" + id;

        return redisTemplate
                .opsForValue()
                .set(key, response, Duration.ofMinutes(5))
                .then();
    }
}