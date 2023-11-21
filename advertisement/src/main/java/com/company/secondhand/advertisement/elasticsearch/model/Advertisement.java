package com.company.secondhand.advertisement.elasticsearch.model;

import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Document(indexName = "advertisement")
public class Advertisement {

    @Field(type = FieldType.Keyword)
    private String id;
    @Field(type = FieldType.Keyword)
    private String title;
    @Field(type = FieldType.Keyword)
    private String description;
    private Double price;
    @Field(type = FieldType.Date, format = DateFormat.basic_date_time)
    private LocalDateTime creationDate;

    @Field(type = FieldType.Date, format = DateFormat.basic_date_time)
    private LocalDateTime lastModifiedDate;
    private Long userId;

    private Set<String> hashtags = new HashSet<>();

    public Advertisement() {
    }

    public Advertisement(String title,
                         String description,
                         Double price,
                         Long userId,
                         Set<String> hashtags) {
        this(title,
                description,
                price,
                LocalDateTime.now(),
                LocalDateTime.now(),
                userId,
                hashtags);
    }

    public Advertisement(String title,
                         String description,
                         Double price,
                         LocalDateTime creationDate,
                         LocalDateTime lastModifiedDate,
                         Long userId,
                         Set<String> hashtags) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.creationDate = creationDate;
        this.lastModifiedDate = lastModifiedDate;
        this.userId = userId;
        this.hashtags = hashtags;
    }

    public Advertisement(String id,
                         String title,
                         String description,
                         Double price,
                         LocalDateTime creationDate,
                         LocalDateTime lastModifiedDate,
                         Long userId,
                         Set<String> hashtags) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
        this.creationDate = creationDate;
        this.lastModifiedDate = lastModifiedDate;
        this.userId = userId;
        this.hashtags = hashtags;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Double getPrice() {
        return price;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public LocalDateTime getLastModifiedDate() {
        return lastModifiedDate;
    }

    public Long getUserId() {
        return userId;
    }

    public Set<String> getHashtags() {
        return hashtags;
    }

    @Override
    public String toString() {
        return "AdvertisementDB{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", creationDate=" + creationDate +
                ", lastModifiedDate=" + lastModifiedDate +
                ", userId=" + userId +
                ", hashtags=" + hashtags +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Advertisement that = (Advertisement) o;
        return Objects.equals(id, that.id) && Objects.equals(title, that.title) && Objects.equals(description, that.description) && Objects.equals(price, that.price) && Objects.equals(creationDate, that.creationDate) && Objects.equals(lastModifiedDate, that.lastModifiedDate) && Objects.equals(userId, that.userId) && Objects.equals(hashtags, that.hashtags);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, price, creationDate, lastModifiedDate, userId, hashtags);
    }
}

