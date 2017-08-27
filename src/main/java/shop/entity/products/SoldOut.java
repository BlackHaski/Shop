package shop.entity.products;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import shop.entity.security.User;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SoldOut {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idSoldOutProduct;
    private int count;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.DETACH)
    private User user;
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.DETACH)
    private Product product;

    public SoldOut(User user, Product product,int count) {
        this.count = count;
        this.user = user;
        this.product = product;
    }
}
