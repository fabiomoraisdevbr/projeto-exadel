package fabiomorais.dev.br.ms_customer;

import fabiomorais.dev.br.ms_customer.CustomerEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface CustomerRepository extends ReactiveCrudRepository<CustomerEntity, Long> {
}