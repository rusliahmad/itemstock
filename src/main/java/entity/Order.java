package entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;
    private Integer qty;
    private String orderNo;

    public Order(Item item, int i, String o1) {

    }
}