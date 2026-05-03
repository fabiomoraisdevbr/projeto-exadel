package fabiomorais.dev.br.ms_customer;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("customer")
public class CustomerEntity {

        @Id
        private Integer id;

        private String name;
        private String email;
}