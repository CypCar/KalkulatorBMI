package com.BMI.model;

import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name="BmiRecord")
public class BmiRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long recordId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "height")
    private Double height;

    @Column(name = "weight")
    private Double weight;

    @Column(name = "bmi")
    private Double bmi;

    @Column(name = "record_date")
    private Timestamp recordDate;

    @Column(name = "bmi_category")
    private String bmiCategory;

    public BmiRecord() {

    }

    public BmiRecord(User user, Double height, Double weight, Double bmi, Timestamp recordDate, String bmiCategory) {
        this.user = user;
        this.height = height;
        this.weight = weight;
        this.bmi = bmi;
        this.recordDate = recordDate;
        this.bmiCategory = bmiCategory;
    }

    public Long getRecordId() {
        return recordId;
    }

    public void setRecordId(Long recordId) {
        this.recordId = recordId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Double getBmi() {
        return bmi;
    }

    public void setBmi(Double bmi) {
        this.bmi = bmi;
    }

    public Timestamp getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(Timestamp recordDate) {
        this.recordDate = recordDate;
    }

    public String getBmiCategory() {
        return bmiCategory;
    }

    public void setBmiCategory(String bmiCategory) {
        this.bmiCategory = bmiCategory;
    }

    @Override
    public String toString() {
        return "BmiRecord [recordId=" + recordId + ", user=" + user + ", height=" + height + ", weight=" + weight
                + ", bmi=" + bmi + ", recordDate=" + recordDate + ", bmiCategory=" + bmiCategory + "]";
    }
}
