package me.telegram.android;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;


@Entity
public class Comment {
    @Id
    private Long Id;

    @NotNull
    private Long PostId;
    private String Name;
    private String Email;
    private String Body;
    @Generated(hash = 1234151076)
    public Comment(Long Id, @NotNull Long PostId, String Name, String Email,
            String Body) {
        this.Id = Id;
        this.PostId = PostId;
        this.Name = Name;
        this.Email = Email;
        this.Body = Body;
    }
    @Generated(hash = 1669165771)
    public Comment() {
    }
    public Long getId() {
        return this.Id;
    }
    public void setId(Long Id) {
        this.Id = Id;
    }
    public Long getPostId() {
        return this.PostId;
    }
    public void setPostId(Long PostId) {
        this.PostId = PostId;
    }
    public String getName() {
        return this.Name;
    }
    public void setName(String Name) {
        this.Name = Name;
    }
    public String getEmail() {
        return this.Email;
    }
    public void setEmail(String Email) {
        this.Email = Email;
    }
    public String getBody() {
        return this.Body;
    }
    public void setBody(String Body) {
        this.Body = Body;
    }
}
