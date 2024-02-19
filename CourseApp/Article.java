package CourseApp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

class Article {
    private String title;
    private String content;
    private int duration;
    private UUID moduleId;
    private boolean isOptional;
    private UUID articleId;
    private long createdAt;
    private static final List<Article> ARTICLES = new ArrayList<>();

    public Article(String title, String content, int duration, UUID moduleId, boolean isOptional) {
        this.title = title;
        this.content = content;
        this.duration = duration;
        this.moduleId = moduleId;
        this.isOptional = isOptional;
        this.articleId = UUID.randomUUID();
        this.createdAt = System.currentTimeMillis();
        ARTICLES.add(this);
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public String getTitle() {
        return title;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setContent(String content) {
        this.content = content;
    }
    public String getContent() {
        return content;
    }

    public void setOptional() {
        this.isOptional = true;
    }

    public void setCompulsory() {
        this.isOptional = false;
    }
    public void setArticleId(UUID articleId) {
        this.articleId = articleId;
    }
    public UUID getArticleId() {
        return articleId;
    }
}
