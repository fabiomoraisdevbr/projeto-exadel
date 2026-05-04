package fabiomorais.dev.br.ms_order;


import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;


public interface OrderRepository extends ReactiveCrudRepository<OrderEntity, Long> {
    @Query("SELECT COALESCE(SUM(amount), 0) FROM orders WHERE customer_id = :customerId")
    Mono<Double> sumTotalByCustomerId(Long customerId);
}