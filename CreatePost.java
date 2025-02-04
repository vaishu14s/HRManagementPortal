package com.hr.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "CREATE_POST")
public class CreatePost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    private String title;

    @Column(name = "COMMENT", length = 2000)
    @NotNull
    private String comment;

    @Column(name = "CREATED_DATE", nullable = false, updatable = false)
    private Date createdDate;

    // Default constructor
    public CreatePost() {
        super();
    }

    // Lifecycle method to automatically set createdDate before persisting
    @PrePersist
    protected void onCreate() {
        this.createdDate = new Date();
    }

    // Getters and setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date date) {
        this.createdDate = date;
    }

    @Override
    public String toString() {
        return "CreatePost [id=" + id + ", title=" + title + ", comment=" + comment + ", createdDate=" + createdDate + "]";
    }
}
