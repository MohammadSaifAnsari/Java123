package CourseApp;
 import java.util.ArrayList;
 import java.util.List;
 import java.util.Scanner;
 import java.util.UUID;

 import CourseApp.*;
 class User {
     private String username;
     private String password;
     private UUID userId;
     private long createdAt;
     private List<UUID> enrolledCourseIds;
     private static final List<User> USERS = new ArrayList<>();
     private static final List<Course> COURSES = new ArrayList<>();

     public User(String username, String password) {
         this.username = username;
         this.password = password;
         this.userId = UUID.randomUUID();
         this.createdAt = System.currentTimeMillis();
         this.enrolledCourseIds = new ArrayList<>();
         USERS.add(this);
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
    public void browseCourses() {
        System.out.println("Available courses:");
        for (Course course : COURSES) {
            System.out.println("Course Title: " + course.getTitle() + ", Course ID: " + course.getCourseId());
        }
        System.out.println("Browsing courses completed.");
    }

   public void enrollCourse(UUID courseId) {
       if (enrolledCourseIds.contains(courseId)) {
           System.out.println("Course already enrolled.");
           return;
       }

        for (Course course : COURSES) {
            if (course.getCourseId().equals(courseId)) {
                enrolledCourseIds.add(courseId);
                System.out.println("Course enrolled successfully.");
                return;
            }
        }

        System.out.println("Course not found.");
    }

    public void unenrollCourse(UUID courseId) {
        if (enrolledCourseIds.contains(courseId)) {
            enrolledCourseIds.remove(courseId);
            System.out.println("Course unenrolled successfully.");
        } else {
            System.out.println("Course not enrolled.");
        }
    }

    public void changePassword() {
       Scanner scanner = new Scanner(System.in);
       System.out.print("Enter new password: ");
       String newPassword = scanner.nextLine();
       this.password = newPassword;
       System.out.println("Password changed successfully.");
    }

    public void changeUsername() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter new username: ");
        String newUsername = scanner.nextLine();
        for (User user : USERS) {
            if (user.getUsername().equals(newUsername)) {
                System.out.println("User with same username already exists.");
                return;
            }
        }
        this.username = newUsername;
        System.out.println("Username changed successfully.");
    }

    public void viewEnrolledCourses() {
        System.out.println("Enrolled courses:");
        for (UUID courseId : enrolledCourseIds) {
            for (Course course : COURSES) {
                if (course.getCourseId().equals(courseId)) {
                    System.out.println("Title: " + course.getTitle() + ", ID: " + course.getCourseId());
                }
            }
        }

        if (enrolledCourseIds.isEmpty()) {
            System.out.println("No courses enrolled.");
        } else {
            System.out.println("Viewing enrolled courses completed.");
        }

    }
    public List<Module> getEnrolledCourseIds() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getEnrolledCourseIds'");
    }
    
 
     }
 
