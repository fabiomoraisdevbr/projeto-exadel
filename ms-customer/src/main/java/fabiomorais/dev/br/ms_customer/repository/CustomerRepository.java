package fabiomorais.dev.br.ms_customer.repository;

import fabiomorais.dev.br.ms_customer.entity.CustomerEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface CustomerRepository extends ReactiveCrudRepository<CustomerEntity, Long> {
}