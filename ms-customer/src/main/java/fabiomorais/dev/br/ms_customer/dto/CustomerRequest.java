package fabiomorais.dev.br.ms_customer.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CustomerRequest(

        @NotBlank(message = "Name is required")
        String name,

        @Email(message = "Invalid email")
        @NotBlank(message = "Email is required")
        String email
) {}