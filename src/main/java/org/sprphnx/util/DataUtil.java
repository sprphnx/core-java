package org.sprphnx.util;

import com.fasterxml.jackson.core.type.TypeReference;

import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.sprphnx.model.Student;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DataUtil {
    public static final String STUDENTS_JSON_FILE_PATH = "src/main/resources/students.json";
    private static final Logger log = LogManager.getLogger(DataUtil.class);
    public static List<Student> getStudents() {
        List<Student> students;
        File studentsJson = new File(STUDENTS_JSON_FILE_PATH);
        if (studentsJson.exists()) {
            try {
                log.info("Reading students from file: " + STUDENTS_JSON_FILE_PATH);
                students = JsonMapperUtil.getInstance()
                                         .readValue(studentsJson, new TypeReference<>(){});
            } catch (IOException e) {
                log.error("Error reading students from file: " + STUDENTS_JSON_FILE_PATH, e);
                throw new RuntimeException(e);
            }
        } else {
            log.info("Generating students");
            students = generateStudents();
        }
        return students;
    }

    private static List<Student> generateStudents() {
        List<Student> students = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < 100; i++) {
            String name = "Student" + (i + 1);
            int score = random.nextInt(101); // Generate a random score between 0 and 100
            students.add(new Student(i + 1, name, score));
        }

        try {
            JsonMapperUtil.getInstance().writeValue(new File(STUDENTS_JSON_FILE_PATH), students);
            return students;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
