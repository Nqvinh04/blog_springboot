package com.example.demo.model;

import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.util.Date;

    @Entity
    @Table(name = "blog")
    public class Blog {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Long id;

        private  String title;

        @ManyToOne
        @JoinColumn(name = "categoryId")
        private Category category;

        private String tags;

        private String image;

        @Transient
        private MultipartFile upload;

        public MultipartFile getUpload() {
            return upload;
        }

        public void setUpload(MultipartFile upload) {
            this.upload = upload;
        }

        private String description;
        private String content_id;

//    @Column(columnDefinition = "LONGTEXT")
//    private String content;

        @Temporal(TemporalType.TIMESTAMP)
//    @CreationTimestamp
        @UpdateTimestamp
        private Date date;

        private String author;

        private String status;

        public Blog() {
        }

        public Blog(Long id, String title, Category category, String tags, String image,
                    MultipartFile upload, String description, String content_id, Date date,
                    String author, String status) {
            this.id = id;
            this.title = title;
            this.category = category;
            this.tags = tags;
            this.image = image;
            this.upload = upload;
            this.description = description;
            this.content_id = content_id;
            this.date = date;
            this.author = author;
            this.status = status;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public Category getCategory() {
            return category;
        }

        public void setCategory(Category category) {
            this.category = category;
        }

        public String getTags() {
            return tags;
        }

        public void setTags(String tags) {
            this.tags = tags;
        }

        public Date getDate() {
            return date;
        }

        public void setDate(Date date) {
            this.date = date;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }


        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getContent_id() {
            return content_id;
        }

        public void setContent_id(String content_id) {
            this.content_id = content_id;
        }
}
