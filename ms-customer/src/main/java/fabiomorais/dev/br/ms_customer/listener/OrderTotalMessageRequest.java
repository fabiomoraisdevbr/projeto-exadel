package fabiomorais.dev.br.ms_customer.listener;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.io.Serializable;

@Getter
@AllArgsConstructor
public class OrderTotalMessageRequest implements Serializable {
    private static final long serialVersionUID = 1L;
    private final Long id;
}
