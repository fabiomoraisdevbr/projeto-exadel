package fabiomorais.dev.br.ms_customer.controller;

import fabiomorais.dev.br.ms_customer.dto.CustomerResponse;
import fabiomorais.dev.br.ms_customer.service.CustomerService;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/customers")
public class CustomerController {

        @Autowired
        private CustomerService customerService;

        @GetMapping("/{id}")
        public Mono<CustomerResponse> getCustomerById(
                @PathVariable
                @NotNull(message = "id is required")
                Long id,

                @RequestParam(defaultValue = "false")
                boolean includeTotalOrders) {
            return customerService.findById(id, includeTotalOrders);
        }

}
