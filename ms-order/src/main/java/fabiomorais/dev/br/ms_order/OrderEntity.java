package fabiomorais.dev.br.ms_order;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("orders")
public class OrderEntity {

    @Id
    private Integer id;

    private Integer customerId;
    private Double total;

    // getters/setters
}