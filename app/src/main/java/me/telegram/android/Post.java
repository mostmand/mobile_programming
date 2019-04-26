package me.telegram.android;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;


@Entity(indexes = {
        @Index(value = "id", unique = true)
})

public class Post {
    @Id
    private Long id;

    @NotNull
    private Long userId;
    private String title;
    private String body;
@Generated(hash = 392168960)
public Post(Long id, @NotNull Long userId, String title, String body) {
    this.id = id;
    this.userId = userId;
    this.title = title;
    this.body = body;
}
@Generated(hash = 1782702645)
public Post() {
}
public Long getId() {
    return this.id;
}
public void setId(Long id) {
    this.id = id;
}
public Long getUserId() {
    return this.userId;
}
public void setUserId(Long userId) {
    this.userId = userId;
}
public String getTitle() {
    return this.title;
}
public void setTitle(String title) {
    this.title = title;
}
public String getBody() {
    return this.body;
}
public void setBody(String body) {
    this.body = body;
}
}
