package App;

import java.util.ArrayList;
import java.util.List;

/// Singleton class
public class Model {

    private List<TodoItem> todoList = new ArrayList<>();

    private static Model instance;

    private List<View> views = new ArrayList<>();

    private  Model() {}

    public static Model getInstance() {
        if(instance == null) {
            synchronized (Model.class){
                if(instance == null) {
                    instance = new Model();
                }
            }
        }
        return instance;
    }

    public void subscribe(View view) {
        views.add(view);
    }

    public void unsubscribe(View view) {
        views.remove(view);
    }

    public List<TodoItem> getTodoList() {
        return todoList;
    }

    public void setTodoList(String[] todos) {
        for (String s : todos) {
            addTodo(s);
        }
    }

    public void addTodo(String desc) {
        int id = todoList.size() + 1;
        TodoItem todo = new TodoItem(id, desc,false);
        todoList.add(todo);
        notifySubscribers();
    }

    public void editTodo(int id, String desc) {
        try {
            TodoItem todo = getTodoById(id);
            int index = todoList.indexOf(todo);
            todo.description = desc;
            todoList.set(index, todo);
            notifySubscribers();
        } catch (Exception e) {
            throw e;
        }
    }

    public void deleteTodo(int id) {
        todoList.removeIf(item -> item.id == id);
        notifySubscribers();
    }

    public void deleteAllTodos() {
        todoList = new ArrayList<>();
        notifySubscribers();
    }

    public void toggleComplete(int id) {
        try {
            TodoItem todo = getTodoById(id);
            int index = todoList.indexOf(todo);
            todo.complete = !todo.complete;
            todoList.set(index, todo);
            notifySubscribers();
        } catch (Exception e) {
            throw e;
        }
    }

    public TodoItem getTodoById(int id) {
        for(TodoItem todo : todoList) {
            if (todo.id == id) {
                return todo;
            }
        }
        throw new RuntimeException("Item id=" + id + " not found!" );
    }

    public void notifySubscribers() {
        views.forEach(view -> view.update(this.todoList));
    }
}
