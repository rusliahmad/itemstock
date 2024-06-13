package entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;
    private Integer qty;
    private String type; // T or W

    public Inventory(Item item, int i, String t) {
    }
}