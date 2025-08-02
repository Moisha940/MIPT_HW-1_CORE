package ru.mipt.hw;

import ru.mipt.hw.student.Student;

import java.util.Scanner;

public class App  {
    public static void main(String[] args) {
        Student<String> student = new Student<>("qwertasaqwfefefe");
        Scanner consoleReader = new Scanner(System.in);
        boolean stopProcess = false;

        System.out.println("\n\n\nСначала выберите пункт из меню, затем, нажмите Enter.");
        while (!stopProcess) {
            System.out.println("~~~~~~~МЕНЮ~~~~~~~");
            System.out.println("1. Создать студента с именем");
            System.out.println("2. Добавить оценку");
            System.out.println("3. Удалить оценку");
            System.out.println("4. Вывести информацию о текущем студенте");
            System.out.println("5. Выход");

            stopProcess =  switch (consoleReader.nextLine()) {
                case "1":
                    System.out.println("Введите имя студента");
                    student.setName(consoleReader.nextLine());
                    while (!student.getMarks().isEmpty()) {
                        student.deleteMark(student.getMarks().getLast());
                    }
                    System.out.println("----- Создан студент " + student);
                    yield false;
                case "2":
                    if (student.getName().equals("qwertasaqwfefefe")) {
                        System.out.println("----- Студент еще не создан!");
                    } else {
                        System.out.println("Введите оценку");
                        student.addMark(consoleReader.nextLine());
                        System.out.println("----- Студент " + student + " получил оценку " + student.getMarks().getLast());
                    }
                    yield false;
                case "3":
                    if (student.getName().equals("qwertasaqwfefefe")) {
                        System.out.println("----- Студент еще не создан!");
                    } else {
                        System.out.println("Введите оценку");
                        String mark = consoleReader.nextLine();
                        if (student.getMarks().contains(mark)) {
                            System.out.println("----- Удалена оценка " + mark);
                            student.deleteMark(mark);
                        } else {
                            System.out.println("----- У студента нет такой оценки");
                        }
                    }
                    yield false;
                case "4":
                    if (student.getName().equals("qwertasaqwfefefe")) {
                        System.out.println("----- Студент еще не создан!");
                    } else {
                        System.out.println("----- Студент выведен на экран. " + student);
                    }
                    yield false;
                case "5":
                    System.out.println("----- До свидания!");
                    yield true;
                default:
                    System.out.println("Выберите команду из списка!");
                    yield false;
            };
            System.out.println("\n");
        }
    }
}
