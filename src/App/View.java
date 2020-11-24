package App;

import App.ConsoleUIComponents.MenuInput;

import java.util.List;

public interface View {
    void run();
    void update(List<TodoItem> todos);
    void render();
}
