package shop.entity.products;

import lombok.*;
import shop.entity.Category;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.List;

@Entity
@DiscriminatorValue("Pc")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pc extends Product{
    private String cpu;
    private String video;
    private int ram;

    public Pc(String productName, int price, double rebate, String descr, int count, Category category, String cpu, String video, int ram,List<String> images) {
        super(productName, price, rebate, descr, count, category,images);
        this.cpu = cpu;
        this.video = video;
        this.ram = ram;
    }
}
