package ru.mipt.hw;

import org.junit.Before;
import org.junit.Test;
import ru.mipt.hw.student.Student;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import static org.junit.Assert.*;

public class StudentTest {
    private Student<Integer> student;
    private Predicate<Integer> markValidator;

    @Before
    public void setUp() {
        markValidator = mark -> mark >= 1 && mark <= 5;
        student = new Student<>("Михаил", markValidator);
    }

    @Test
    public void testConstructorWithName() {
        Student<Integer> student = new Student<>("Михаил");
        assertEquals("Михаил", student.getName());
        assertTrue(student.getMarks().isEmpty());
    }

    @Test
    public void testConstructorWithNameAndPredicate() {
        assertEquals("Михаил", student.getName());
        assertTrue(student.getMarks().isEmpty());
    }

    @Test
    public void testConstructorWithNameAndMarks() {
        List<Integer> marks = List.of(4, 5, 3);
        Student<Integer> student = new Student<>("Михаил", marks);
        assertEquals("Михаил", student.getName());
        assertEquals(marks, student.getMarks());
    }

    @Test
    public void testConstructorWithNameMarksAndPredicate() {
        List<Integer> marks = List.of(4, 5, 3);
        Student<Integer> student = new Student<>("Михаил", marks, markValidator);
        assertEquals("Михаил", student.getName());
        assertEquals(marks, student.getMarks());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorWithNullName() {
        new Student<Integer>(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorWithEmptyName() {
        new Student<Integer>("      ");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorWithInvalidMarkAndPredicate() {
        List<Integer> marks = List.of(4, 6, 3);
        new Student<>("Михаил", marks, markValidator);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorWithNullMark() {
        List<Integer> marks = Arrays.asList(4, null, 3);
        new Student<>("Михаил", marks);
    }

    @Test
    public void testGetName() {
        assertEquals("Михаил", student.getName());
    }

    @Test
    public void testSetName() {
        student.setName("Лиза");
        assertEquals("Лиза", student.getName());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetNullName() {
        student.setName(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetEmptyName() {
        student.setName("   ");
    }

    @Test
    public void testGetMarks() {
        student.addMark(4);
        student.addMark(5);
        List<Integer> expected = List.of(4, 5);
        assertEquals(expected, student.getMarks());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testGetMarksReturnsUnmodifiableList() {
        student.getMarks().add(3);
    }

    @Test
    public void testAddMark() {
        student.addMark(3);
        assertEquals(List.of(3), student.getMarks());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddInvalidMark() {
        student.addMark(6);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddNullMark() {
        student.addMark(null);
    }

    @Test
    public void testDeleteMark() {
        student.addMark(4);
        student.addMark(5);
        student.deleteMark(4);
        assertEquals(List.of(5), student.getMarks());
    }

    @Test
    public void testDeleteNullMark() {
        student.addMark(4);
        student.deleteMark(null);
        assertEquals(List.of(4), student.getMarks());
    }
}