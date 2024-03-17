package org.sprphnx;

import lombok.extern.log4j.Log4j2;
import org.sprphnx.model.Student;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.sprphnx.util.DataUtil.getStudents;

public class Main {


    public static void main(String[] args) {
        List<Student> students = getStudents();

        // Convert the list of marks to a Set to remove duplicates
        Set<Integer> marksSet = students.stream()
                .map(Student::getMarks)
                .collect(Collectors.toSet());

        // Convert the Set to a List and sort it in reverse order
        List<Integer> sortedMarks = marksSet.stream()
                .sorted(Comparator.reverseOrder())
                .toList();

        // Get the second element from the sorted list
        if (sortedMarks.size() >= 2) {
            int secondHighestMark = sortedMarks.get(1);

            // Filter the students to get the students with the second highest mark
            List<Student> studentsWithSecondHighestMark = students.stream()
                    .filter(student -> student.getMarks() == secondHighestMark)
                    .toList();

            // Print the students with the second highest mark

            studentsWithSecondHighestMark.forEach(student -> System.out.println(student.getName() + " scored " + student.getMarks()));
        } else {
            System.out.println("There are less than two distinct marks among the students.");
        }

    }


}