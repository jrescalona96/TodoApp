package App.ConsoleUIComponents;


import App.ConsoleUIComponents.common.*;
import App.TodoItem;

import java.util.List;

public class TodoList implements Widget {

    private List<TodoItem> todoList;
    private final String header = "📌 TODO ITEMS";
    private final String emptyTodoListMessage = " 🥳 Nothing here. Press 1 to add task";

    public TodoList(List<TodoItem> todoList) {
        this.todoList = todoList;
    }

    private int _getBorderLength() {
        int length = 0;
        int paddingLeft = 7;
        if(todoList.isEmpty()) {
            length = emptyTodoListMessage.length();
        } else {
            for (TodoItem todo : todoList) {
                int l = todo.getDescription().length();
                if (l > length) {
                    length = l;
                }
            }
            length += paddingLeft;
        }
        return length;
    }

    public List<TodoItem> getTodoList() {
        return todoList;
    }

    public void setTodoList(List<TodoItem> todoList) {
        this.todoList = todoList;
    }

    @Override
    public void render() {
        Container container = new Container();
        int borderLength =  _getBorderLength();

        Border border = new Border("-", borderLength);
        container.addWidget(new Break());
        container.addWidget(border);
        container.addWidget(new Break());
        container.addWidget(new Text(header));

        if(todoList.isEmpty()) {
            container.addWidget(new Text(emptyTodoListMessage));
        } else {
            for (TodoItem todo : todoList) {
                container.addWidget(new Todo(todo));
            }
        }

        container.addWidget(border);
        container.addWidget(new Break());
        container.render();
    }
}
