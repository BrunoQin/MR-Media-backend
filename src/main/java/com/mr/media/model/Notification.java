package com.mr.media.model;

import com.avaje.ebean.Model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by 秦博 on 2017/3/10.
 */

@Entity
@Table(name = "notification")
public class Notification extends Model{

    public static final int READ_NOTIFICATION = 0;
    public static final int UNREAD_NOTIFICATION = 1;

    @Id
    @Column(name = "id")
    int id;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = User.class)
    @JoinColumn(name = "sender", referencedColumnName = "id")
    User sender;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = User.class)
    @JoinColumn(name = "receiver", referencedColumnName = "id")
    User receiver;

    @Column(name = "text_content")
    String textContent;

    @Column(name = "link")
    String link;

    @Column(name = "pictures_url")
    String picturesUrl;

    @Column(name = "status")
    int status;

    @Column(name = "when_created")
    Date whenCreated;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public String getTextContent() {
        return textContent;
    }

    public void setTextContent(String textContent) {
        this.textContent = textContent;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getPicturesUrl() {
        return picturesUrl;
    }

    public void setPicturesUrl(String picturesUrl) {
        this.picturesUrl = picturesUrl;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getWhenCreated() {
        return whenCreated;
    }

    public void setWhenCreated(Date whenCreated) {
        this.whenCreated = whenCreated;
    }
}
