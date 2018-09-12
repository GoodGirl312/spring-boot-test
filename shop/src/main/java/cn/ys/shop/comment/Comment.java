package cn.ys.shop.comment;

import cn.ys.shop.product.Product;
import cn.ys.shop.user.User;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.Date;

/**
 * @author :yuanshuo
 * @date :2018/3/5
 */
@Entity
@Table(name="comment")
public class Comment {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;
    @Column
    private String content;
    @Column
    private Integer parentID;
    @Column
    @ApiModelProperty(value = "被回复的次数")
    private Integer reply;

    @Column
    private Integer goodNum;
    @Column
    private String commentDate;


    @ManyToOne(fetch =FetchType.LAZY)
    @JoinColumn(name="pid")
    private Product product;
    @ManyToOne(targetEntity = User.class,fetch = FetchType.LAZY)
    @JoinColumn(name = "uid")
    private User user;

    public Integer getReply() {
        return reply;
    }

    public void setReply(Integer reply) {
        this.reply = reply;
    }

    public String getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(String commentDate) {
        this.commentDate = commentDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getParentID() {
        return parentID;
    }

    public void setParentID(Integer parentID) {
        this.parentID = parentID;
    }

    public Integer getGoodNum() {
        return goodNum;
    }

    public void setGoodNum(Integer goodNum) {
        this.goodNum = goodNum;
    }

}
