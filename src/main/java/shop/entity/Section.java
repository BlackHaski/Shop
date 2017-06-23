package shop.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

/**
 * Created by blackhaski on 21.06.17.
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Section {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL,mappedBy = "section")
    private List<Category> categories;
}
