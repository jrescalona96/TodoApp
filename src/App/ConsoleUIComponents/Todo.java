package App.ConsoleUIComponents;

import App.ConsoleUIComponents.common.Widget;
import App.TodoItem;

public class Todo implements Widget {
    private TodoItem todo;

    public Todo(TodoItem todo) {
        this.todo = todo;
    }

    @Override
    public void render() {
        System.out.printf("%s %d : %s\n", todo.isComplete() ? "✅" : "🔳", todo.getId(), todo.getDescription());
    }
}
