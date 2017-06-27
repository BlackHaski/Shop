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
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;
    private String email;
    private String password;
    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.REMOVE,mappedBy = "user")
    private UserInfo userInfo;
}
