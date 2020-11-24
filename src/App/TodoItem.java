package App;

public class TodoItem {
    int id;
    String description;
    boolean complete;

    public TodoItem(int id, String description, boolean complete) {
        this.id = id;
        this.description = description;
        this.complete = complete;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }
}
