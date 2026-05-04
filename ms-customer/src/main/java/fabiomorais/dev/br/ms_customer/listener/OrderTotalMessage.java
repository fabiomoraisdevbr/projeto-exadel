package fabiomorais.dev.br.ms_customer.listener;


import lombok.Getter;

@Getter
public class OrderTotalMessage {

    private Long id;
    private double totalAmount;

    public OrderTotalMessage() {
    }

    public OrderTotalMessage(Long id, double totalAmount) {
        this.id = id;
        this.totalAmount = totalAmount;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
}
