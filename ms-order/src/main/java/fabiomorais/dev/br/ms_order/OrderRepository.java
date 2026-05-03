package fabiomorais.dev.br.ms_order;

import fabiomorais.dev.br.ms_order.OrderEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface OrderRepository extends ReactiveCrudRepository<OrderEntity, Long> {
}