import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    private static Map<String, List<Integer>> students = new HashMap<>();
    private static Scanner scanner = new Scanner(System.in);
    private static Student student;

    public static void main(String[] args) {

        int choice;
        System.out.println("1. Создать новый файл данных");
        System.out.println("2. Загрузить существующий файл данных");

        choice = scanner.nextInt();
        switch (choice) {
            case 1 -> createFile();
            case 2 -> students = readMapFromFile("students.txt");
            default -> System.out.println("Некорректный ввод");

            }

        do {
                scanner.nextLine();
                System.out.println("1. Добавить учащегося");
                System.out.println("2. Удалить учащегося");
                System.out.println("3. Обновить оценку учащегося");
                System.out.println("4. Просмотр оценок всех учащихся");
                System.out.println("5. Просмотр оценок конкретного учащегося");
                System.out.println("6. Выход");
                choice = scanner.nextInt();
                switch (choice) {
                    case 1 -> addStudent();
                    case 2 -> deleteStudent();
                    case 3 -> {
                        System.out.println("Для обновления оценки введите имя учащегося:");
                        String name = scanner.next();
                        if (students.containsKey(name)) {
                            System.out.println("Введите оценку:");
                            int grade = scanner.nextInt();
                            List<Integer> grades = students.get(name);
                            grades.add(grade);
                            students.put(name, grades);
                        }
                        else {
                            System.out.println("Такого учащегося нет");
                        }
                        }
                    case 4 -> displayStudents();
                    case 5 -> displayStudent();
                    //default -> System.out.println("Некорректный ввод");
                }
            }while (choice != 6);

        writeToFile("students.txt", students);


    }

    private static void displayStudent() {
        System.out.println("Введите имя учащегося:");
        String name = scanner.next();
        if (students.containsKey(name)) {
            System.out.println(name + ": " + students.get(name));
        }
        else {
            System.out.println("Такого учащегося нет");
        }
    }

    private static void displayStudents() {
        for (Map.Entry<String, List<Integer>> entry : students.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    private static void addStudent() {
        student = new Student();
        System.out.println("Введите имя учащегося:");
        student.setName(scanner.next());
        System.out.println("Введите оценки (для завершения введите 'q'):");
        while (scanner.hasNextInt()) {
            student.addGrade(scanner.nextInt());
        }
        students.put(student.getName(), student.getGrades());
    }

    private static void deleteStudent() {
        System.out.println("Для удаления учащегося введите его имя:");
        String name = scanner.next();
        if (students.containsKey(name)) {
            students.remove(name);
        }
        else {
            System.out.println("Такого учащегося нет");
        }
    }




    private static void createFile() {
        File file = new File("students.txt");
        if (!file.exists()) {
            try {
                if (file.createNewFile()) {
                    System.out.println("Файл студентов создан");
                }
                else {
                    System.out.println("Не удалось создать файл.");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            System.out.println("Файл студентов уже существует");
        }
    }

    private static Map<String, List<Integer>> readMapFromFile(String filename) {
        Map<String, List<Integer>> map = new HashMap<>();
        try (BufferedReader reader = Files.newBufferedReader(Path.of(filename), StandardCharsets.UTF_8)) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                String name = parts[0];
                List<Integer> marks = new ArrayList<>();
                for (String s : parts[1].split(",")) {
                    Integer parseInt = Integer.parseInt(s);
                    marks.add(parseInt);
                }
                map.put(name, marks);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }

    private static boolean fileIsEmpty(File file) {
        return file.length() == 0;
    }

    private static void writeToFile(String filename, Map<String, List<Integer>> map) {
        try (BufferedWriter writer = Files.newBufferedWriter(Path.of(filename), StandardCharsets.UTF_8, StandardOpenOption.APPEND, StandardOpenOption.CREATE)) {
            for (Map.Entry<String, List<Integer>> entry : map.entrySet()) {
                String valueStr = entry.getValue().stream().map(Object::toString).reduce((s1, s2) -> s1 + "," + s2).orElse("");
                writer.write(entry.getKey() + ":" + valueStr + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

