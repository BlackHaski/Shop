package shop.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

/**
 * Created by blackhaski on 23.06.17.
 */
@Entity
@Getter
@Setter
@ToString(exclude = "products")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int categoryId;
    private int parentId;
    private String categoryName;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.DETACH, mappedBy = "category")
    private List<Product> products = null;

    public Category(String categoryName, int parentId) {
        this.categoryName = categoryName;
        this.parentId = parentId;
    }
}
