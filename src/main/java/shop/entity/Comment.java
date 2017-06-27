package shop.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

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
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int commentId;
    private String text;
    private Date date;
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.DETACH)
    private Product product;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    private User user;

}