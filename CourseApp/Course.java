package CourseApp;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

class Course {
    private String title;
    private String description;
    private UUID instructorId;
    private UUID courseId;
    private long createdAt;
    private List<UUID> moduleIds;
    private static final List<Module> MODULES = new ArrayList<>();

    public Course(String title, String description, UUID instructorId) {
        this.title = title;
        this.description = description;
        this.instructorId = instructorId;
        this.courseId = UUID.randomUUID();
        this.createdAt = System.currentTimeMillis();
        this.moduleIds = new ArrayList<>();
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public String getTitle() {
        return title;
    }

    public void setCourseId(UUID courseId) {
        this.courseId = courseId;
    }
    public UUID getCourseId() {
        return courseId;
    }
    public void setModuleIds(List<UUID> moduleIds) {
        this.moduleIds = moduleIds;
    }
    public List<UUID> getModuleIds() {
        return moduleIds;
    }

    public String getDescription() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getDescription'");
    }

    public Module findModule(UUID moduleId) {
        if (!moduleIds.contains(moduleId)) {
            return null;
        }
        for (Module module : MODULES) {
            if (module.getModuleId().equals(moduleId)) {
                return module;
            }
        }
        return null;
    }

    // public void addModule() {
    //     Scanner scanner = new Scanner(System.in);
    //     System.out.print("Enter module title: ");
    //     String title = scanner.nextLine();
    //     Module module = new Module(title, courseId);
    //     moduleIds.add(module.getModuleId());
    //     System.out.println("Module added successfully.");
    // }

    // public void removeModule(UUID moduleId) {
    //     if (moduleIds.contains(moduleId)) {
    //         moduleIds.remove(moduleId);
    //         System.out.println("Module removed successfully.");
    //     } else {
    //         System.out.println("Module not found in this course.");
    //     }
    // }
}
