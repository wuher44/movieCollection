public class Movie {

    private long id;
    private String title;
    private int length;
    private long userId;

    public Movie(long id, String title, int length, long userId) {
        this.id = id;
        this.title = title;
        this.length = length;
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", length=" + length +
                ", userId=" + userId +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
