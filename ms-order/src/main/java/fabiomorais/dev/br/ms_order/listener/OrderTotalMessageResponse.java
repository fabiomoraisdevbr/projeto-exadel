package fabiomorais.dev.br.ms_order.listener;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OrderTotalMessageResponse {

    private Long id;
    private double totalAmount;
}
