package fabiomorais.dev.br.ms_customer.dto;

public record CustomerResponse(
        Integer id,
        String name,
        String email,
        double totalOrdersAmount,
        String status // SUCCESS | PROCESSING
) {}