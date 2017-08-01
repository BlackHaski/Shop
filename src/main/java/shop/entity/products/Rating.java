package shop.entity.products;

import lombok.*;
import shop.entity.security.User;

import javax.persistence.*;

/**
 * Created by blackhaski on 26.06.17.
 */
@Entity
@Getter
@Setter
@ToString(exclude = "product")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ratingId;
    private int posRating=0;
    private int negRating=0;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    private Product product;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    private User user;

}
