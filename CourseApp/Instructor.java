package CourseApp;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

class Instructor {
    private String username;
    private String password;
    private UUID instructorId;
    private long createdAt;
    private List<UUID> createdCourseIds;
    private static final List<Instructor> INSTRUCTORS = new ArrayList<>();

    public Instructor(String username, String password) {
        this.username = username;
        this.password = password;
        this.instructorId = UUID.randomUUID();
        this.createdAt = System.currentTimeMillis();
        this.createdCourseIds = new ArrayList<>();
        INSTRUCTORS.add(this);
        System.out.println("SignUp successful.");
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public String getUsername() {
        return username;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getPassword() {
        return password;
    }

    // public void createCourse() {
    //     Scanner scanner = new Scanner(System.in);
    //     System.out.print("Enter course title: ");
    //     String title = scanner.nextLine();
    //     System.out.print("Enter course description: ");
    //     String description = scanner.nextLine();
    //     Course course = new Course(title, description, instructorId);
    //     COURSES.add(course);
    //     createdCourseIds.add(course.getCourseId());
    //     System.out.println("Course created successfully.");
    // }

    // public void removeCourse(UUID courseId) {
    //     if (createdCourseIds.contains(courseId)) {
    //         createdCourseIds.remove(courseId);
    //         COURSES.removeIf(course -> course.getCourseId().equals(courseId));
    //         System.out.println("Course removed successfully.");
    //     } else {
    //         System.out.println("Course not found in this instructor.");
    //     }
    // }

    // public void viewCreatedCourses() {
    //     System.out.println("Created courses:");
    //     for (Course course : COURSES) {
    //         if (course.getInstructorId().equals(instructorId)) {
    //             System.out.println("Course: " + course.getTitle());
    //         }
    //     }
    //     System.out.println("Viewing created courses completed.");
    // }

    // public void changePassword() {
    //     Scanner scanner = new Scanner(System.in);
    //     System.out.print("Enter new password: ");
    //     String newPassword = scanner.nextLine();
    //     this.password = newPassword;
    //     System.out.println("Password changed successfully.");
    // }

    // public void changeUsername() {
    //     Scanner scanner = new Scanner(System.in);
    //     System.out.print("Enter new username: ");
    //     String newUsername = scanner.nextLine();
    //     for (Instructor instructor : INSTRUCTORS) {
    //         if (instructor.getUsername().equals(newUsername)) {
    //             System.out.println("Instructor with same username already exists.");
    //             return;
    //         }
    //     }
    //     this.username = newUsername;
    //     System.out.println("Username changed successfully.");
    // }
}