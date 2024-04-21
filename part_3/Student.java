import java.util.ArrayList;
import java.util.List;

public class Student {
    private String name;
    private List<Integer> grades = new ArrayList<>();

    public Student() {
    }

    public Student(String name) {
        this.name = name;
        this.grades = new ArrayList<>();
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<Integer> getGrades() {
        return grades;
    }

    public void addGrade(int grade) {
        grades.add(grade);
    }

    public void updateGrade(int index, int grade) {
        if (index >= 0 && index < grades.size()) {
            grades.set(index, grade);
        } else {
            System.out.println("Invalid index.");
        }
    }

    public void removeGrade(int index) {
        if (index >= 0 && index < grades.size()) {
            grades.remove(index);
        } else {
            System.out.println("Invalid index.");
        }
    }

    public void displayGrades() {
        System.out.println("Grades for " + name + ": " + grades);
    }

}
