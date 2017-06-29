package shop.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by blackhaski on 26.06.17.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productId;
    private String productName;
    private int price;
    private double rebate;
    private int resultPrice;
    private String descr;
    private int soldOut;
    private int count;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "product")
    private List<Rating> ratings = new ArrayList<Rating>();
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "product")
    private List<Image> images = new ArrayList<Image>();
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "product")
    private List<Comment> comments = new ArrayList<Comment>();
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.DETACH)
    private Category category;

}
