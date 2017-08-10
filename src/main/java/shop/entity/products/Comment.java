package shop.entity.products;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import shop.entity.security.User;

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
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy/MM/dd hh:mm" ,shape = JsonFormat.Shape.STRING,timezone = "Europe/Kiev")
    private Date date;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    private Product product = null;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    private User user = null;

    public Comment(String text, Date date, Product product, User user) {
        this.text = text;
        this.date = date;
        this.product = product;
        this.user = user;
    }
}