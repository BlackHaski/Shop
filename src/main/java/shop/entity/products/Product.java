package shop.entity.products;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import shop.entity.Category;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by blackhaski on 26.06.17.
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "productType",discriminatorType = DiscriminatorType.STRING)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productId;
    @Column(nullable = false,updatable = false, insertable = false)
    private String productType;
    private String productName;
    private int price;
    private double rebate = 0.0;
    private String descr;
    private int soldOut = 0;
    private int count;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "product")
    private List<Rating> ratings = new ArrayList<Rating>();
    @ElementCollection(fetch = FetchType.EAGER,targetClass = String.class)
    private List<String> images = new ArrayList<String>();
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "product")
    private List<Comment> comments = new ArrayList<Comment>();
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.DETACH)
    private Category category = null;

    public Product(String productName, int price, double rebate, String descr, int count, Category category, List<String> images) {
        this.productName = productName;
        this.price = price;
        this.rebate = rebate;
        this.descr = descr;
        this.count = count;
        this.category = category;
        this.images = images;
    }
}
