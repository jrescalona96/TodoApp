package App;

import java.util.HashMap;
import java.util.List;

public interface View {
    void update(List<TodoItem> todos);
    void render();
    String onAddTodo();
    HashMap<String, String> onEditTodo();
    int onDeleteTodo();
    int onToggleCompleteTodo();
}
