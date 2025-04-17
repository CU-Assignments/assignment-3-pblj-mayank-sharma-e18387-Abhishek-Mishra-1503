import java.util.*;

// Custom exception for full course
class CourseFullException extends Exception {
    public CourseFullException(String message) {
        super(message);
    }
}

// Custom exception for missing prerequisites
class PrerequisiteNotMetException extends Exception {
    public PrerequisiteNotMetException(String message) {
        super(message);
    }
}

// Course class
class Course {
    private String name;
    private int capacity;
    private List<String> prerequisites;
    private List<String> enrolledStudents;

    public Course(String name, int capacity, List<String> prerequisites) {
        this.name = name;
        this.capacity = capacity;
        this.prerequisites = prerequisites;
        this.enrolledStudents = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<String> getPrerequisites() {
        return prerequisites;
    }

    public void enrollStudent(String studentName, List<String> completedCourses)
            throws CourseFullException, PrerequisiteNotMetException {
        if (enrolledStudents.size() >= capacity) {
            throw new CourseFullException("Course " + name + " is already full.");
        }

        for (String pre : prerequisites) {
            if (!completedCourses.contains(pre)) {
                throw new PrerequisiteNotMetException(
                    "Cannot enroll in " + name + ". Missing prerequisite: " + pre);
            }
        }

        enrolledStudents.add(studentName);
        System.out.println(studentName + " successfully enrolled in " + name + ".");
    }

    public int getRemainingSeats() {
        return capacity - enrolledStudents.size();
    }
}

// Main driver
public class UniversityEnrollmentSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Sample course setup
        List<String> prereqs = Arrays.asList("Math 101");
        Course course = new Course("Physics 201", 2, prereqs);

        try {
            // Input
            System.out.print("Enter student name: ");
            String name = sc.nextLine();

            System.out.print("Enter completed courses (comma-separated): ");
            String[] completed = sc.nextLine().split(",");
            List<String> completedCourses = new ArrayList<>();
            for (String c : completed) {
                completedCourses.add(c.trim());
            }

            // Try enrolling
            course.enrollStudent(name, completedCourses);

        } catch (CourseFullException | PrerequisiteNotMetException e) {
            System.out.println("Enrollment failed: " + e.getMessage());
        } finally {
            System.out.println("Remaining seats in course: " + course.getRemainingSeats());
            sc.close();
        }
    }
}
