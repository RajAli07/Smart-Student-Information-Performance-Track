/**
 * Encapsulates student identity and metadata.
 * Persistence handled via DatabaseHelper; this class represents the domain model.
 */
public class Student {
    private int id;          // DB-generated
    private String name;
    private int age;
    private String course;
    private String roll;

    public Student(int id, String name, int age, String course, String roll) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.course = course;
        this.roll = roll;
    }

    public Student(String name, int age, String course, String roll) {
        this(-1, name, age, course, roll);
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public int getAge() { return age; }
    public String getCourse() { return course; }
    public String getRoll() { return roll; }

    public void setName(String name) { this.name = name; }
    public void setAge(int age) { this.age = age; }
    public void setCourse(String course) { this.course = course; }
    public void setRoll(String roll) { this.roll = roll; }

    @Override
    public String toString() {
        return String.format("Student[ID=%d, Name=%s, Age=%d, Course=%s, Roll=%s]",
                id, name, age, course, roll);
    }
}
