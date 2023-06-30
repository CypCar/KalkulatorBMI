package com.BMI.controller;

import com.BMI.model.BmiRecord;
import com.BMI.model.User;
import com.BMI.repository.BmiRecordRepository;
import com.BMI.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.sql.Timestamp;


import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api/bmi-records")
public class BmiRecordController {
    private final BmiRecordRepository bmiRecordRepository;
    private final UserRepository userRepository;

    @Autowired
    public BmiRecordController(BmiRecordRepository bmiRecordRepository, UserRepository userRepository) {
        this.bmiRecordRepository = bmiRecordRepository;
        this.userRepository = userRepository;
    }

    @GetMapping
    public ResponseEntity<List<BmiRecord>> getAllBmiRecords() {
        try {
            List<BmiRecord> bmiRecords = bmiRecordRepository.findAll();

            if (bmiRecords.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(bmiRecords, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/search")
    public ResponseEntity<List<BmiRecord>> searchBmiRecords(
            @RequestParam(required = false) Long recordId,
            @RequestParam(required = false) Double height,
            @RequestParam(required = false) Double weight,
            @RequestParam(required = false) Double bmi,
            @RequestParam(required = false) Timestamp recordDate,
            @RequestParam(required = false) String bmiCategory) {

        List<BmiRecord> bmiRecords = bmiRecordRepository.findByAnyDate(
                recordId, height, weight, bmi, recordDate, bmiCategory);

        if (bmiRecords.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(bmiRecords, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BmiRecord> createBmiRecord(@RequestBody BmiRecord bmiRecord) {
        try {
            Optional<User> userData = userRepository.findById(bmiRecord.getUser().getUser_id());

            if (userData.isPresent()) {
                User user = userData.get();
                bmiRecord.setUser(user);
                BmiRecord _bmiRecord = bmiRecordRepository.save(bmiRecord);
                return new ResponseEntity<>(_bmiRecord, HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<BmiRecord> updateBmiRecord(@PathVariable("id") long id, @RequestBody BmiRecord bmiRecord) {
        Optional<BmiRecord> bmiRecordData = bmiRecordRepository.findById(id);

        if (bmiRecordData.isPresent()) {
            BmiRecord _bmiRecord = bmiRecordData.get();
            _bmiRecord.setHeight(bmiRecord.getHeight());
            _bmiRecord.setWeight(bmiRecord.getWeight());
            _bmiRecord.setBmi(bmiRecord.getBmi());
            _bmiRecord.setBmiCategory(bmiRecord.getBmiCategory());
            _bmiRecord.setRecordDate(bmiRecord.getRecordDate());
            BmiRecord updatedBmiRecord = bmiRecordRepository.save(_bmiRecord);
            return new ResponseEntity<>(updatedBmiRecord, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteBmiRecord(@PathVariable("id") long id) {
        try {
            bmiRecordRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping
    public ResponseEntity<HttpStatus> deleteAllBmiRecords() {
        try {
            bmiRecordRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
