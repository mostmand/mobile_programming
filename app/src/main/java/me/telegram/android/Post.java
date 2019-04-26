package me.telegram.android;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;


@Entity(indexes = {
        @Index(value= "Id", unique = true)
})

public class Post {
    @Id
    private Long Id;

    @NotNull
    private Long UserId;
    private String Title;
    private String Body;
@Generated(hash = 953099827)
public Post(Long Id, @NotNull Long UserId, String Title, String Body) {
    this.Id = Id;
    this.UserId = UserId;
    this.Title = Title;
    this.Body = Body;
}
@Generated(hash = 1782702645)
public Post() {
}
public Long getId() {
    return this.Id;
}
public void setId(Long Id) {
    this.Id = Id;
}
public Long getUserId() {
    return this.UserId;
}
public void setUserId(Long UserId) {
    this.UserId = UserId;
}
public String getTitle() {
    return this.Title;
}
public void setTitle(String Title) {
    this.Title = Title;
}
public String getBody() {
    return this.Body;
}
public void setBody(String Body) {
    this.Body = Body;
}
}
