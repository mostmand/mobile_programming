package me.telegram.android;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;


@Entity
public class Comment {
    @Id
    private Long id;

    @NotNull
    private Long postId;
    private String name;
    private String email;
    private String body;
    @Generated(hash = 2087673961)
    public Comment(Long id, @NotNull Long postId, String name, String email,
            String body) {
        this.id = id;
        this.postId = postId;
        this.name = name;
        this.email = email;
        this.body = body;
    }
    @Generated(hash = 1669165771)
    public Comment() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getPostId() {
        return this.postId;
    }
    public void setPostId(Long postId) {
        this.postId = postId;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return this.email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getBody() {
        return this.body;
    }
    public void setBody(String body) {
        this.body = body;
    }
}
