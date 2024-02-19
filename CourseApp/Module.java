package CourseApp;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

class Module {
    private String title;
    private UUID courseId;
    private UUID moduleId;
    private long createdAt;
    private List<UUID> articleIds;
    private static final List<Module> MODULES = new ArrayList<>();
    private static final List<Article> ARTICLES = new ArrayList<>();

    public Module(String title, UUID courseId) {
        this.title = title;
        this.courseId = courseId;
        this.moduleId = UUID.randomUUID();
        this.createdAt = System.currentTimeMillis();
        this.articleIds = new ArrayList<>();
        MODULES.add(this);
    }
    public void setModuleId(UUID moduleId) {
        this.moduleId = moduleId;
    }
    public UUID getModuleId() {
        return moduleId;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public String getTitle() {
        return title;
    }
    public List<UUID> getArticleIds() {
        return articleIds;
    }
    

    public Article findArticle(UUID articleId) {
        if (!articleIds.contains(articleId)) {
            return null;
        }
        for (Article article : ARTICLES) {
            if (article.getArticleId().equals(articleId)) {
                return article;
            }
        }
        return null;
    }

    public void addArticle() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter article title: ");
        String title = scanner.nextLine();
        System.out.print("Enter article content: ");
        String content = scanner.nextLine();
        System.out.print("Enter duration in minutes: ");
        int duration = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Is this article optional? (T/F): ");
        String isOptionalStr = scanner.nextLine();
        boolean isOptional = isOptionalStr.equalsIgnoreCase("T");
        Article article = new Article(title, content, duration, moduleId, isOptional);
        articleIds.add(article.getArticleId());
        System.out.println("Article added successfully.");
    }

    public void removeArticle(UUID articleId) {
        if (articleIds.contains(articleId)) {
            articleIds.remove(articleId);
            System.out.println("Article removed successfully.");
        } else {
            System.out.println("Article not found in this module.");
        }
    }
}