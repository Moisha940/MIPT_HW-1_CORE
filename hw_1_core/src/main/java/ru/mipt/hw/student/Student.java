package ru.mipt.hw.student;

import java.util.*;
import java.util.function.Predicate;

public class Student <T> {
    private String name;
    private final List<T> marks;
    private final Predicate<T> condition;
    private final List<LogNode> logs = new ArrayList<>();
    private boolean rememberLog = true;


    private void validateName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Неверный формат имени.");
        }
    }

    private void validateMark(T mark) {
        if (mark == null) {
            throw new IllegalArgumentException("Оценка не может быть null.");
        }
        if (condition != null && !condition.test(mark)) {
            throw new IllegalArgumentException("Неверная оценка.");
        }
    }

    public Student(String name) {
        validateName(name);
        this.name = name;
        this.marks = new ArrayList<T>();
        this.condition = null;
    }

    public Student(String name, Predicate<T> condition) {
        validateName(name);
        this.name = name;
        this.marks = new ArrayList<T>();
        this.condition = condition;

    }

    public Student(String name, List<T> marks) {
        validateName(name);
        this.name = name;
        this.condition = null;
        this.marks = new ArrayList<T>();
        marks.forEach(mark -> {
            validateMark(mark);
            this.marks.add(mark);
        });

    }

    public Student(String name, List<T> marks, Predicate<T> condition) {
        validateName(name);
        this.name = name;
        this.condition = condition;
        this.marks = new ArrayList<T>();
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
        if (this.rememberLog) {
            logs.add(new LogNode("setName", this.name));
        }
        this.name = name;
    }

    public List<T> getMarks() {
        return Collections.unmodifiableList(this.marks);
    }

    public void addMark(T mark) throws IllegalArgumentException {
        validateMark(mark);
        this.marks.add(mark);
        if (this.rememberLog) {
            logs.add(new LogNode("addMark", mark));
        }
    }

    public void deleteMark(T mark) {
        if (mark == null) {
            return;
        }
        this.marks.remove(mark);
        if (this.rememberLog) {
            logs.add(new LogNode("deleteMark", mark));
        }
    }

    public void rollBack() {
        if (this.logs.isEmpty()) {
            System.out.println("Студент в своем начальном состоянии.");
        } else {
            this.rememberLog = false;
            if (this.logs.getLast().getCommand().equals("setName")) {
                this.setName(this.logs.getLast().getNameValue());
                logs.removeLast();
            } else if (this.logs.getLast().getCommand().equals("addMark")) {
                this.deleteMark(this.logs.getLast().getMarkValue());
                logs.removeLast();
            } else if (this.logs.getLast().getCommand().equals("deleteMark")) {
                this.addMark(this.logs.getLast().getMarkValue());
                logs.removeLast();
            }
        }
        this.rememberLog = true;
        System.out.println("Последнее действие отменено.");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if(o == null  ||  this.getClass() != o.getClass()) {
            return false;
        }
        Student<?> s = (Student<?>) o;
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
        return "Имя: " + this.name + " " + this.marks;
    }

    private class LogNode {
        private final String command;
        private String nameValue;
        private T markValue;

        private LogNode(String command, T markValue) {
            this.command = command;
            this.markValue = markValue;
        }

        private LogNode(String command, String nameValue) {
            this.command = command;
            this.nameValue = nameValue;
        }

        private String getCommand() {
            return command;
        }

        private T getMarkValue() {
            return this.markValue;
        }

        private String getNameValue() {
            return this.nameValue;
        }
    }
}











