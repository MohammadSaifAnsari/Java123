package CourseApp;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class MainActivity {
    
    private static final List<Course> COURSES = new ArrayList<>();
    private static final List<Instructor> INSTRUCTORS = new ArrayList<>();
    private static final List<User> USERS = new ArrayList<>();
    private static User CURRENT_LOGIN = null;
    private static Instructor CURRENT_LOGIN_INSTRUCTOR = null;
    private UUID userId;
    private long createdAt;
    private List<UUID> enrolledCourseIds;
    private String username;
    private String password;
     public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Courshell\n");

        while (true) {
            System.out.println("1. Sign Up as User");
            System.out.println("2. Sign Up as Instructor");
            System.out.println("3. Login as User");
            System.out.println("4. Login as Instructor");
            System.out.println("5. Exit Program");

            int choice;
            try {
                System.out.print("\nEnter your choice: ");
                choice = scanner.nextInt();
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println("\nInvalid choice. Please try again.\n");
                continue;
            }

            if (choice == 1) {
                signUpUser();
            } else if (choice == 2) {
                signUpInstructor();
            } else if (choice == 3) {
                loginUser();
            } else if (choice == 4) {
                loginInstructor();
            } else if (choice == 5) {
                System.out.println("Exiting...");
                break;
            } else {
                System.out.println("\nInvalid choice. Please try again.\n");
                continue;
            }

            
            if (CURRENT_LOGIN instanceof User) {
                while (true) {
                    System.out.println("1. Browse Courses");
                    System.out.println("2. Enroll Course");
                    System.out.println("3. View Enrolled Courses");
                    System.out.println("4. View Course");
                    System.out.println("5. Change Password");
                    System.out.println("6. Change Username");
                    System.out.println("7. Logout");
                    System.out.println("8. Exit Program");

                    try {
                        System.out.print("\nEnter your choice: ");
                        choice = scanner.nextInt();
                        scanner.nextLine();
                    } catch (Exception e) {
                        System.out.println("\nInvalid choice. Please try again.\n");
                        continue;
                    }

                    if (choice == 1) {
                        CURRENT_LOGIN.browseCourses();
                    } else if (choice == 2) {
                        System.out.print("Enter course ID: ");
                        UUID courseId = UUID.fromString(scanner.nextLine());
                        CURRENT_LOGIN.enrollCourse(courseId);
                    } else if (choice == 3) {
                        CURRENT_LOGIN.viewEnrolledCourses();
                    } else if (choice == 4) {
                        System.out.print("Enter course ID: ");
                        UUID courseId = UUID.fromString(scanner.nextLine());
                        if (CURRENT_LOGIN.getEnrolledCourseIds().contains(courseId)) {
                            Course course = findCourse(courseId);
                            if (course != null) {
                                System.out.println("Title: " + course.getTitle());
                                System.out.println("Description: " + course.getDescription());
                                System.out.println("Modules:");
                                List<Module> modules = new ArrayList<>();
                                for (UUID moduleId : course.getModuleIds()) {
                                    Module module = course.findModule(moduleId);
                                    if (module != null) {
                                        System.out.println("Module: " + module.getTitle());
                                        modules.add(module);
                                    }
                                }
                                if (modules.isEmpty()) {
                                    System.out.println("No modules found.");
                                    continue;
                                }
                                while (true) {
                                    System.out.println("1. Enter Module");
                                    System.out.println("2. Unenroll from Course");
                                    System.out.println("3. Back to User Menu");
                                    System.out.println("4. Exit Program");

                                    try {
                                        System.out.print("\nEnter your choice: ");
                                        choice = scanner.nextInt();
                                        scanner.nextLine();
                                    } catch (Exception e) {
                                        System.out.println("\nInvalid choice. Please try again.\n");
                                        continue;
                                    }

                                    if (choice == 1) {
                                        System.out.print("Enter module index: ");
                                        int moduleIndex = scanner.nextInt() - 1;
                                        scanner.nextLine();
                                        if (moduleIndex < 0 || moduleIndex >= modules.size()) {
                                            System.out.println("\nInvalid module index. Please try again.\n");
                                            continue;
                                        }
                                        Module module = modules.get(moduleIndex);
                                        System.out.println(module.getTitle());
                                        System.out.println("Articles:");
                                        List<Article> articles = new ArrayList<>();
                                        for (UUID articleId : module.getArticleIds()) {
                                            Article article = module.findArticle(articleId);
                                            if (article != null) {
                                                System.out.println("Article: " + article.getTitle());
                                                articles.add(article);
                                            }
                                        }
                                        if (articles.isEmpty()) {
                                            System.out.println("No articles found.");
                                            continue;
                                        }
                                        while (true) {
                                            System.out.println("1. Enter Article");
                                            System.out.println("2. Back to Module Menu");
                                            System.out.println("3. Exit Program");

                                            try {
                                                System.out.print("\nEnter your choice: ");
                                                choice = scanner.nextInt();
                                                scanner.nextLine();
                                            } catch (Exception e) {
                                                System.out.println("\nInvalid choice. Please try again.\n");
                                                continue;
                                            }

                                            if (choice == 1) {
                                                System.out.print("Enter article index: ");
                                                int articleIndex = scanner.nextInt() - 1;
                                                scanner.nextLine();
                                                if (articleIndex < 0 || articleIndex >= articles.size()) {
                                                    System.out.println("\nInvalid article index. Please try again.\n");
                                                    continue;
                                                }
                                                Article article = articles.get(articleIndex);
                                                int nrDashes = (80 - article.getTitle().length()) / 2;
                                                System.out.println("-".repeat(nrDashes) + article.getTitle() + "-".repeat(nrDashes));
                                                System.out.println(article.getContent());
                                                System.out.println("-".repeat(80));
                                            } else if (choice == 2) {
                                                break;
                                            } else if (choice == 3) {
                                                System.out.println("Exiting...");
                                                return;
                                            } else {
                                                System.out.println("\nInvalid choice. Please try again.\n");
                                                continue;
                                            }
                                        }
                                    } else if (choice == 2) {
                                        CURRENT_LOGIN.unenrollCourse(courseId);
                                        break;
                                    } else if (choice == 3) {
                                        break;
                                    } else if (choice == 4) {
                                        System.out.println("Exiting...");
                                        return;
                                    } else {
                                        System.out.println("\nInvalid choice. Please try again.\n");
                                        continue;
                                    }
                                }
                            } else {
                                System.out.println("Course not found.");
                            }
                        }
                    } else if (choice == 5) {
                        CURRENT_LOGIN.changePassword();
                    } else if (choice == 6) {
                        CURRENT_LOGIN.changeUsername();
                    } else if (choice == 7) {
                        logout();
                        break;
                    } else if (choice == 8) {
                        System.out.println("Exiting...");
                        return;
                    } else {
                        System.out.println("\nInvalid choice. Please try again.\n");
                        continue;
                    }
                }
            }
        }

        //     if (CURRENT_LOGIN instanceof Instructor) {
        //         while (true) {
        //             System.out.println("1. Create Course");
        //             System.out.println("2. Remove Course");
        //             System.out.println("3. Change Password");
        //             System.out.println("4. Change Username");
        //             System.out.println("5. View Created Courses");
        //             System.out.println("6. Update Course");
        //             System.out.println("7. Logout");
        //             System.out.println("8. Exit Program");

        //             try {
        //                 System.out.print("\nEnter your choice: ");
        //                 choice = scanner.nextInt();
        //                 scanner.nextLine();
        //             } catch (Exception e) {
        //                 System.out.println("\nInvalid choice. Please try again.\n");
        //                 continue;
        //             }

        //             if (choice == 1) {
        //                 CURRENT_LOGIN.createCourse();
        //             } else if (choice == 2) {
        //                 System.out.print("Enter course index: ");
        //                 int courseIndex = scanner.nextInt() - 1;
        //                 scanner.nextLine();
        //                 if (courseIndex < 0 || courseIndex >= CURRENT_LOGIN.getCreatedCourseIds().size()) {
        //                     System.out.println("\nInvalid course index. Please try again.\n");
        //                     continue;
        //                 }
        //                 UUID courseId = CURRENT_LOGIN.getCreatedCourseIds().get(courseIndex);
        //                 CURRENT_LOGIN.removeCourse(courseId);
        //             } else if (choice == 3) {
        //                 CURRENT_LOGIN.changePassword();
        //             } else if (choice == 4) {
        //                 CURRENT_LOGIN.changeUsername();
        //             } else if (choice == 5) {
        //                 CURRENT_LOGIN.viewCreatedCourses();
        //             } else if (choice == 6) {
        //                 System.out.print("Enter course index: ");
        //                 int courseIndex = scanner.nextInt() - 1;
        //                 scanner.nextLine();
        //                 if (courseIndex < 0 || courseIndex >= CURRENT_LOGIN.getCreatedCourseIds().size()) {
        //                     System.out.println("\nInvalid course index. Please try again.\n");
        //                     continue;
        //                 }
        //                 UUID courseId = CURRENT_LOGIN.getCreatedCourseIds().get(courseIndex);
        //                 Course course = findCourse(courseId);
        //                 if (course != null) {
        //                     int nrDashes = (80 - course.getTitle().length()) / 2;
        //                     System.out.println("-".repeat(nrDashes) + course.getTitle() + "-".repeat(nrDashes));
        //                     while (true) {
        //                         System.out.println("1. Update Course Title");
        //                         System.out.println("2. Update Course Description");
        //                         System.out.println("3. Add Module");
        //                         System.out.println("4. Remove Module");
        //                         System.out.println("5. View Modules");
        //                         System.out.println("6. Update Module");
        //                         System.out.println("7. Back to Instructor Menu");
        //                         System.out.println("8. Exit Program");

        //                         try {
        //                             System.out.print("\nEnter your choice: ");
        //                             choice = scanner.nextInt();
        //                             scanner.nextLine();
        //                         } catch (Exception e) {
        //                             System.out.println("\nInvalid choice. Please try again.\n");
        //                             continue;
        //                         }

        //                         if (choice == 1) {
        //                             System.out.print("Enter new title: ");
        //                             String title = scanner.nextLine();
        //                             course.setTitle(title);
        //                             System.out.println("Course title updated successfully.");
        //                         } else if (choice == 2) {
        //                             System.out.print("Enter new description: ");
        //                             String description = scanner.nextLine();
        //                             course.setDescription(description);
        //                             System.out.println("Course description updated successfully.");
        //                         } else if (choice == 3) {
        //                             course.addModule();
        //                         } else if (choice == 4) {
        //                             System.out.print("Enter module index: ");
        //                             int moduleIndex = scanner.nextInt() - 1;
        //                             scanner.nextLine();
        //                             if (moduleIndex < 0 || moduleIndex >= course.getModuleIds().size()) {
        //                                 System.out.println("\nInvalid module index. Please try again.\n");
        //                                 continue;
        //                             }
        //                             UUID moduleId = course.getModuleIds().get(moduleIndex);
        //                             course.removeModule(moduleId);
        //                         } else if (choice == 5) {
        //                             System.out.println("Modules:");
        //                             for (UUID moduleId : course.getModuleIds()) {
        //                                 Module module = course.findModule(moduleId);
        //                                 if (module != null) {
        //                                     System.out.println("Module: " + module.getTitle());
        //                                 }
        //                             }
        //                             if (course.getModuleIds().isEmpty()) {
        //                                 System.out.println("No modules found.");
        //                             }
        //                         } else if (choice == 6) {
        //                             System.out.print("Enter module index: ");
        //                             int moduleIndex = scanner.nextInt() - 1;
        //                             scanner.nextLine();
        //                             if (moduleIndex < 0 || moduleIndex >= course.getModuleIds().size()) {
        //                                 System.out.println("\nInvalid module index. Please try again.\n");
        //                                 continue;
        //                             }
        //                             UUID moduleId = course.getModuleIds().get(moduleIndex);
        //                             Module module = course.findModule(moduleId);
        //                             if (module != null) {
        //                                 int nrDashes = (80 - module.getTitle().length()) / 2;
        //                                 System.out.println("-".repeat(nrDashes) + module.getTitle() + "-".repeat(nrDashes));
        //                                 while (true) {
        //                                     System.out.println("1. Update Module Title");
        //                                     System.out.println("2. Add Article");
        //                                     System.out.println("3. Remove Article");
        //                                     System.out.println("4. View Articles");
        //                                     System.out.println("5. Update Article");
        //                                     System.out.println("6. Back to Course Menu");
        //                                     System.out.println("7. Exit Program");

        //                                     try {
        //                                         System.out.print("\nEnter your choice: ");
        //                                         choice = scanner.nextInt();
        //                                         scanner.nextLine();
        //                                     } catch (Exception e) {
        //                                         System.out.println("\nInvalid choice. Please try again.\n");
        //                                         continue;
        //                                     }

        //                                     if (choice == 1) {
        //                                         System.out.print("Enter new title: ");
        //                                         String title = scanner.nextLine();
        //                                         module.setTitle(title);
        //                                         System.out.println("Module title updated successfully.");
        //                                     } else if (choice == 2) {
        //                                         module.addArticle();
        //                                     } else if (choice == 3) {
        //                                         System.out.print("Enter article index: ");
        //                                         int articleIndex = scanner.nextInt() - 1;
        //                                         scanner.nextLine();
        //                                         if (articleIndex < 0 || articleIndex >= module.getArticleIds().size()) {
        //                                             System.out.println("\nInvalid article index. Please try again.\n");
        //                                             continue;
        //                                         }
        //                                         UUID articleId = module.getArticleIds().get(articleIndex);
        //                                         module.removeArticle(articleId);
        //                                     } else if (choice == 4) {
        //                                         System.out.println("Articles:");
        //                                         for (UUID articleId : module.getArticleIds()) {
        //                                             Article article = module.findArticle(articleId);
        //                                             if (article != null) {
        //                                                 System.out.println("Article: " + article.getTitle() + ", Optional: " + article.isOptional());
        //                                             }
        //                                         }
        //                                         if (module.getArticleIds().isEmpty()) {
        //                                             System.out.println("No articles found.");
        //                                         }
        //                                     } else if (choice == 5) {
        //                                         System.out.print("Enter article index: ");
        //                                         int articleIndex = scanner.nextInt() - 1;
        //                                         scanner.nextLine();
        //                                         if (articleIndex < 0 || articleIndex >= module.getArticleIds().size()) {
        //                                             System.out.println("\nInvalid article index. Please try again.\n");
        //                                             continue;
        //                                         }
        //                                         UUID articleId = module.getArticleIds().get(articleIndex);
        //                                         Article article = module.findArticle(articleId);
        //                                         if (article != null) {
        //                                             int nrDashes = (80 - article.getTitle().length()) / 2;
        //                                             System.out.println("-".repeat(nrDashes) + article.getTitle() + "-".repeat(nrDashes));
        //                                             while (true) {
        //                                                 System.out.println("1. Update Article Title");
        //                                                 System.out.println("2. Update Article Duration");
        //                                                 System.out.println("3. Update Article Content");
        //                                                 System.out.println("4. Set Article Optional");
        //                                                 System.out.println("5. Set Article Compulsory");
        //                                                 System.out.println("6. Back to Module Menu");
        //                                                 System.out.println("7. Exit Program");

        //                                                 try {
        //                                                     System.out.print("\nEnter your choice: ");
        //                                                     choice = scanner.nextInt();
        //                                                     scanner.nextLine();
        //                                                 } catch (Exception e) {
        //                                                     System.out.println("\nInvalid choice. Please try again.\n");
        //                                                     continue;
        //                                                 }

        //                                                 if (choice == 1) {
        //                                                     System.out.print("Enter new title: ");
        //                                                     String title = scanner.nextLine();
        //                                                     article.setTitle(title);
        //                                                     System.out.println("Article title updated successfully.");
        //                                                 } else if (choice == 2) {
        //                                                     System.out.print("Enter new duration: ");
        //                                                     int duration = scanner.nextInt();
        //                                                     scanner.nextLine();
        //                                                     article.setDuration(duration);
        //                                                     System.out.println("Article duration updated successfully.");
        //                                                 } else if (choice == 3) {
        //                                                     System.out.print("Enter new content: ");
        //                                                     String content = scanner.nextLine();
        //                                                     article.setContent(content);
        //                                                     System.out.println("Article content updated successfully.");
        //                                                 } else if (choice == 4) {
        //                                                     article.setOptional();
        //                                                     System.out.println("Article set as optional.");
        //                                                 } else if (choice == 5) {
        //                                                     article.setCompulsory();
        //                                                     System.out.println("Article set as compulsory.");
        //                                                 } else if (choice == 6) {
        //                                                     break;
        //                                                 } else if (choice == 7) {
        //                                                     System.out.println("Exiting...");
        //                                                     return;
        //                                                 } else {
        //                                                     System.out.println("\nInvalid choice. Please try again.\n");
        //                                                     continue;
        //                                                 }
        //                                             }
        //                                         }
        //                                     } else if (choice == 6) {
        //                                         break;
        //                                     } else if (choice == 7) {
        //                                         System.out.println("Exiting...");
        //                                         return;
        //                                     } else {
        //                                         System.out.println("\nInvalid choice. Please try again.\n");
        //                                         continue;
        //                                     }
        //                                 }
        //                             }
        //                         } else if (choice == 7) {
        //                             break;
        //                         } else if (choice == 8) {
        //                             System.out.println("Exiting...");
        //                             return;
        //                         } else {
        //                             System.out.println("\nInvalid choice. Please try again.\n");
        //                             continue;
        //                         }
        //                     }
        //                 } else {
        //                     System.out.println("Course not found.");
        //                 }
        //             } else if (choice == 7) {
        //                 logout();
        //                 break;
        //             } else if (choice == 8) {
        //                 System.out.println("Exiting...");
        //                 return;
        //             } else {
        //                 System.out.println("\nInvalid choice. Please try again.\n");
        //                 continue;
        //             }
        //         }
        //     }
        // }
    
    }
     private static void signUpUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        for (User user : USERS) {
            if (user.getUsername().equals(username)) {
                System.out.println("User with same username already exists.");
                return;
            }
        }
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        User user = new User(username, password);
        USERS.add(user);
        CURRENT_LOGIN = user;
    }

    private static void signUpInstructor() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        for (Instructor instructor : INSTRUCTORS) {
            if (instructor.getUsername().equals(username)) {
                System.out.println("Instructor with same username already exists.");
                return;
            }
        }
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        Instructor instructor = new Instructor(username, password);
        INSTRUCTORS.add(instructor);
        CURRENT_LOGIN_INSTRUCTOR = instructor;
    }

    private static void loginUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        for (User user : USERS) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                CURRENT_LOGIN = user;
                System.out.println("Login successful.");
                return;
            }
        }
        System.out.println("Invalid username or password.");
    }

    private static void loginInstructor() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        for (Instructor instructor : INSTRUCTORS) {
            if (instructor.getUsername().equals(username) && instructor.getPassword().equals(password)) {
                CURRENT_LOGIN_INSTRUCTOR = instructor;
                System.out.println("Login successful.");
                return;
            }
        }
        System.out.println("Invalid username or password.");
    }

    private static void logout() {
        CURRENT_LOGIN = null;
        System.out.println("Logout successful.");
    }

    private static Course findCourse(UUID courseId) {
        for (Course course : COURSES) {
            if (course.getCourseId().equals(courseId)) {
                return course;
            }
        }
        return null;
    }

    
}

