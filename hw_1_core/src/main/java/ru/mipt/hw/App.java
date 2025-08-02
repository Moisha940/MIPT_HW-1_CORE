package ru.mipt.hw;

import ru.mipt.hw.student.Student;

import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
        Student student = new Student("alex", List.of(1,2,3,4,5,6,7,8,9,10));
        System.out.println(student);
    }
}
