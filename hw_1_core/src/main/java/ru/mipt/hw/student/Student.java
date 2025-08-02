package ru.mipt.hw.student;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Student {
    private String name;
    private final List<Integer> marks;

    private void validateName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Неверный формат имени");
        }
    }

    private void validateMark(Integer mark) {
        if (mark == null || mark < 1 || mark > 5) {
            throw new IllegalArgumentException("Неверная оценка");
        }
    }

    public Student(String name) {
        validateName(name);
        this.name = name;
        this.marks = new ArrayList<>();

    }

    public Student(String name, List<Integer> marks) {
        validateName(name);
        this.name = name;
        this.marks = new ArrayList<>();
        marks.forEach(mark -> {
            validateMark(mark);
            this.marks.add(mark);
        });

    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        validateName(name);
        this.name = name;
    }

    public List<Integer> getMarks() {
        return Collections.unmodifiableList(this.marks);
    }

    public void addMark(Integer mark) throws IllegalArgumentException {
        validateMark(mark);
        this.marks.add(mark);
    }

    public void deleteMark(Integer mark) {
        validateMark(mark);
        this.marks.remove(mark);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if(o == null  ||  this.getClass() != o.getClass()) {
            return false;
        }
        Student s = (Student) o;
        return this.name != null
                && this.name.equals(s.name)
                && this.marks != null
                && this.marks.equals(s.marks);
    }

    @Override
    public int hashCode() {
        int result = this.name != null ? this.name.hashCode() : 0;
        result = 31 * result + (this.marks != null ? this.marks.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        if (this.marks != null) {
            return "Имя: " + this.name + " " + this.marks;
        } else {
            return "Имя: " + this.name;
        }
    }
}
