package com.BMI.repository;

import com.BMI.model.BmiRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.sql.Timestamp;
import java.util.List;

@Repository
public interface BmiRecordRepository extends JpaRepository<BmiRecord, Long> {
    @Query("SELECT r FROM BmiRecord r WHERE r.recordId = :id OR r.height = :height OR r.weight = :weight OR r.bmi = :bmi OR r.recordDate = :recordDate OR r.bmiCategory = :bmiCategory")
    List<BmiRecord> findByAnyDate(@Param("id") Long recordId, @Param("height") Double height, @Param("weight") Double weight, @Param("bmi") Double bmi, @Param("recordDate") Timestamp recordDate, @Param("bmiCategory") String bmiCategory);
}


