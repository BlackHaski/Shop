package shop.entity.products;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import shop.entity.security.User;

import javax.persistence.*;

/**
 * Created by blackhaski on 26.06.17.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ratingId;
    private boolean rating = false;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    private Product product = null;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    private User user = null;

    public Rating(boolean rating, Product product, User user) {
        this.rating = rating;
        this.product = product;
        this.user = user;
    }
}
