package fabiomorais.dev.br.ms_customer.dto;

public record CustomerResponse(
        Long id,
        String name,
        String email,
        Double totalOrdersAmount,
        String status // SUCCESS | PROCESSING
) {}