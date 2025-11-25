import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Orchestrates business logic, validation, and calls to DatabaseHelper.
 */
public class StudentManager {
    private final DatabaseHelper db;

    public StudentManager(DatabaseHelper db) {
        this.db = db;
    }

    // --- CRUD ---
    public Student addStudent(String name, int age, String course, String roll) throws SQLException {
        validateBasicInfo(name, age, course, roll);
        if (db.getStudentByRoll(roll) != null) throw new IllegalArgumentException("Roll already exists.");
        Student s = new Student(name.trim(), age, course.trim(), roll.trim());
        db.insertStudent(s);
        return s;
    }

    public boolean updateStudent(int id, String name, Integer age, String course, String roll) throws SQLException {
        Student s = db.getStudentById(id);
        if (s == null) return false;

        if (name != null && !name.isBlank()) s.setName(name.trim());
        if (age != null && age > 0) s.setAge(age);
        if (course != null && !course.isBlank()) s.setCourse(course.trim());
        if (roll != null && !roll.isBlank()) {
            roll = roll.trim();
            Student other = db.getStudentByRoll(roll);
            if (other != null && other.getId() != id) {
                throw new IllegalArgumentException("Another student already has this roll.");
            }
            s.setRoll(roll);
        }
        return db.updateStudent(s);
    }

    public boolean deleteStudent(int id) throws SQLException {
        return db.deleteStudent(id);
    }

    public Student findById(int id) throws SQLException { return db.getStudentById(id); }
    public Student findByRoll(String roll) throws SQLException { return db.getStudentByRoll(roll); }
    public List<Student> searchByName(String q) throws SQLException { return db.searchStudentsByName(q); }
    public List<Student> getAllStudents() throws SQLException { return db.getAllStudents(); }

    // --- Subjects & Marks ---
    public List<String> listSubjects() throws SQLException { return db.listSubjects(); }

    public boolean addOrUpdateMark(int studentId, String subject, double marks) throws SQLException {
        if (subject == null || subject.isBlank()) throw new IllegalArgumentException("Subject required.");
        if (marks < 0 || marks > 100) throw new IllegalArgumentException("Marks must be 0..100.");
        Student s = db.getStudentById(studentId);
        if (s == null) return false;
        db.upsertMark(studentId, subject.trim(), marks);
        return true;
    }

    public Performance getPerformance(int studentId) throws SQLException {
        Map<String, Double> map = db.getMarksForStudent(studentId);
        Performance p = new Performance();
        map.forEach(p::putMark);
        return p;
    }

    public String buildResultCard(int studentId) throws SQLException {
        Student s = db.getStudentById(studentId);
        if (s == null) return "Student not found.";
        Performance p = getPerformance(studentId);
        int[] att = db.getAttendance(studentId);
        double attPct = db.getAttendancePercentage(studentId);
        StringBuilder sb = new StringBuilder();
        sb.append("\n===== Result Card =====\n");
        sb.append(s).append("\n");
        sb.append("Subjects & Marks:\n");
        if (p.getMarks().isEmpty()) {
            sb.append("  No marks recorded.\n");
        } else {
            p.getMarks().forEach((sub, m) ->
                    sb.append(String.format("  - %s: %.2f\n", sub, m)));
        }
        sb.append(String.format("Total: %.2f | Percentage: %.2f%% | Grade: %s\n",
                p.getTotal(), p.getPercentage(), p.getGrade()));
        sb.append(String.format("Attendance: %d/%d (%.2f%%)\n", att[0], att[1], attPct));
        if (attPct < 75.0) sb.append("Warning: Attendance below 75%.\n");
        sb.append("=======================\n");
        return sb.toString();
    }

    // --- Attendance ---
    public boolean updateAttendance(int studentId, int addPresent, int addTotal) throws SQLException {
        if (addPresent < 0 || addTotal < 0) throw new IllegalArgumentException("Days cannot be negative.");
        if (addPresent > addTotal) throw new IllegalArgumentException("Present cannot exceed total.");
        Student s = db.getStudentById(studentId);
        if (s == null) return false;
        db.addAttendance(studentId, addPresent, addTotal);
        return true;
    }

    // --- Ranking & Summary ---
    public List<Student> getRankedStudents() throws SQLException { return db.getRankedStudents(); }

    public String buildSummaryReport() throws SQLException {
        int total = db.getAllStudents().size();
        double classAvg = db.getClassAveragePercentage();
        long pass = db.getPassCount();
        long fail = total - pass;
        Student highest = db.getHighestScorer();
        double avgAtt = db.getAverageAttendancePercentage();

        StringBuilder sb = new StringBuilder();
        sb.append("\n===== Summary Report =====\n");
        sb.append("Total Registered Students  : ").append(total).append("\n");
        sb.append(String.format("Average Class Percentage   : %.2f%%\n", classAvg));
        sb.append("Pass Count                 : ").append(pass).append("\n");
        sb.append("Fail Count                 : ").append(fail).append("\n");
        if (highest != null) {
            double pct = db.getPercentageForStudent(highest.getId());
            sb.append("Highest Scorer             : ")
                    .append(highest.getName()).append(" (ID ").append(highest.getId()).append(") - ")
                    .append(String.format("%.2f%%\n", pct));
        } else {
            sb.append("Highest Scorer             : N/A\n");
        }
        sb.append(String.format("Average Attendance         : %.2f%%\n", avgAtt));
        sb.append("============================\n");
        return sb.toString();
    }

    private void validateBasicInfo(String name, int age, String course, String roll) {
        if (name == null || name.isBlank()) throw new IllegalArgumentException("Name required.");
        if (age <= 0) throw new IllegalArgumentException("Age must be positive.");
        if (course == null || course.isBlank()) throw new IllegalArgumentException("Course required.");
        if (roll == null || roll.isBlank()) throw new IllegalArgumentException("Roll required.");
    }
}
