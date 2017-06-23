package shop.entity;

import lombok.*;

import javax.persistence.*;

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
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @ManyToOne
    private Section section;
}
