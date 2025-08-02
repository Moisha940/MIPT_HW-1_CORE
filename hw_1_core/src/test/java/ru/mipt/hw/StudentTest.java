package ru.mipt.hw;

import org.junit.Before;
import org.junit.Test;
import ru.mipt.hw.student.Student;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class StudentTest {
    private Student student;

    @Before
    public void setUp() {
        student = new Student("Михаил");
    }

    @Test
    public void testConstructorWithName() {
        assertEquals("Михаил", student.getName());
        assertTrue(student.getMarks().isEmpty());
    }

    @Test
    public void testConstructorWithNameAndMarks() {
        List<Integer> marks = Arrays.asList(4, 5, 3);
        Student studentWithMarks = new Student("Михаил", marks);
        assertEquals("Михаил", studentWithMarks.getName());
        assertEquals(marks, studentWithMarks.getMarks());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorWithNullName() {
        new Student(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorWithEmptyName() {
        new Student("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorWithInvalidMark() {
        List<Integer> marks = Arrays.asList(4, 6, 3);
        new Student("Михаил", marks);
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
        student.setName("  ");
    }

    @Test
    public void testGetMarks() {
        student.addMark(4);
        student.addMark(5);
        List<Integer> expected = Arrays.asList(4, 5);
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

    @Test(expected = IllegalArgumentException.class)
    public void testDeleteInvalidMark() {
        student.deleteMark(6);
    }


    @Test
    public void testEqualsDifferentObjectsSameValues() {
        Student other = new Student("Михаил");
        other.addMark(4);
        student.addMark(4);
        assertEquals(student, other);
    }

    @Test
    public void testEqualsDifferentName() {
        Student other = new Student("Лиза");
        assertNotEquals(student, other);
    }

    @Test
    public void testEqualsDifferentMarks() {
        Student other = new Student("Лиза");
        other.addMark(3);
        student.addMark(4);
        assertNotEquals(student, other);
    }

    @Test
    public void testEqualsDifferentClass() {
        assertNotEquals(student, new Object());
    }
}