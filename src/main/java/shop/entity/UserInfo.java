package shop.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by blackhaski on 27.06.17.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userInfoId;
    private String userName;
    private String userSurname;
    private Date birthday;
    private String img;
    @Enumerated(EnumType.STRING)
    private Role role = Role.ROLE_USER;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "user")
    private List<Comment> comments;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "user")
    private List<Rating> ratings;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    private User user;
}
