package shop.entity;

import lombok.*;

import javax.persistence.*;

/**
 * Created by blackhaski on 26.06.17.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int imageId;
    private String path;
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.DETACH)
    private Product product;
}
