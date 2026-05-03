package fabiomorais.dev.br.ms_customer.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("customer")
public class CustomerEntity {

        @Id
        private Integer id;

        private String customer_name;
        private String email;
}