import java.util.*;

class Student {
    private Integer id;
    private String name;
    private Integer age;
    private Set<String> courses;

    public Student(Integer id, String name, Integer age, Set<String> courses) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.courses = new HashSet<>(courses);
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public Set<String> getCourses() {
        return courses;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setCourses(Set<String> courses) {
        this.courses = new HashSet<>(courses);
    }

    public String getInfo() {
        return "ID: " + id + ", Имя: " + name + ", Возраст: " + age + ", Курсы: " + courses;
    }
}

class StudentManager {
    private Map<Integer, Student> students = new HashMap<>();

    public void addStudent(Student student) {
        students.put(student.getId(), student);
    }

    public void removeStudent(Integer id) {
        students.remove(id);
    }

    public void updateStudent(Integer id, String name, Integer age, Set<String> courses) {
        if (students.containsKey(id)) {
            Student student = students.get(id);
            student.setName(name);
            student.setAge(age);
            student.setCourses(courses);
        }
    }

    public void printAllStudents() {
        for (Student student : students.values()) {
            System.out.println(student.getInfo());
        }
    }

    public void findStudentById(Integer id) {
        if (students.containsKey(id)) {
            System.out.println(students.get(id).getInfo());
        } else {
            System.out.println("Студент с таким ID не найден");
        }
    }

    public void findStudentsByCourse(String course) {
        for (Student student : students.values()) {
            if (student.getCourses().contains(course)) {
                System.out.println(student.getInfo());
            }
        }
    }
}

public class StudentSystem {
    public static void main(String[] args) {
        StudentManager manager = new StudentManager();

        Set<String> courses1 = new HashSet<>();
        courses1.add("Java");
        courses1.add("Алгоритмы");

        Set<String> courses2 = new HashSet<>();
        courses2.add("Базы данных");
        courses2.add("Java");

        Set<String> courses3 = new HashSet<>();
        courses3.add("Математика");
        courses3.add("Физика");

        Student student1 = new Student(1, "john doe", 18, courses1);
        Student student2 = new Student(2, "maria doe", 19, courses2);
        Student student3 = new Student(3, "jane doe", 20, courses3);

        manager.addStudent(student1);
        manager.addStudent(student2);
        manager.addStudent(student3);

        System.out.println("Все студенты:");
        manager.printAllStudents();

        System.out.println("\nПоиск студента с ID 2:");
        manager.findStudentById(2);

        System.out.println("\nСтуденты на курсе Java:");
        manager.findStudentsByCourse("Java");

        Set<String> newCourses = new HashSet<>();
        newCourses.add("Java");
        newCourses.add("Python");
        manager.updateStudent(1, "john doe", 19, newCourses);

        System.out.println("\nПосле обновления:");
        manager.printAllStudents();

        manager.removeStudent(2);

        System.out.println("\nПосле удаления:");
        manager.printAllStudents();
    }
}
