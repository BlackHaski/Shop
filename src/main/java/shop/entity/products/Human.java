package shop.entity.products;

import lombok.*;
import shop.entity.Category;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.List;

@Entity
@DiscriminatorValue("Human")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Human extends Product{
    private int age;
    private int height;
    private int width;

    public Human(String productName, int price, double rebate, String descr, int count, Category category, int age, int height, int width, List<String> images) {
        super(productName, price, rebate, descr, count, category,images);
        this.age = age;
        this.height = height;
        this.width = width;
    }
}
