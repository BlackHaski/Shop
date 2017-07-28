package shop.entity.security;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import shop.entity.products.Comment;
import shop.entity.products.Rating;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by blackhaski on 27.06.17.
 */
@Entity
public class UserInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userInfoId;
    @JsonFormat(pattern = "yyyy-MM-dd" ,shape = JsonFormat.Shape.STRING)
    private Date birthday;
    private String img;
    @Enumerated(EnumType.STRING)
    private Role role = Role.ROLE_USER;
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "user")
    private List<Comment> comments;
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "user")
    private List<Rating> ratings;
    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private User user;

    public UserInfo() {
    }

    public UserInfo(Date birthday, String img, Role role, User user) {
        this.birthday = birthday;
        this.img = img;
        this.role = role;
        this.user = user;
    }

    public int getUserInfoId() {
        return userInfoId;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "userInfoId=" + userInfoId +
                ", birthday=" + birthday +
                ", img='" + img + '\'' +
                ", role=" + role +
                ", comments=" + comments +
                ", ratings=" + ratings +
                '}';
    }
}
