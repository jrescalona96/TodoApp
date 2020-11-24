package App.Testing;

import App.Model;
import App.TodoItem;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class ModelTest {

    String[] sampleTodos = {
            "Reinvent the wheel",
            "Rewrite MVC in Java",
            "Research Companies",
            "Apply for Jobs",
            "Get a Job"
    };

    @Test
    void getTodoListShouldSucceed() {

        // SETUP
        Model model = Model.getInstance();
        model.deleteAllTodos();
        model.addTodo(sampleTodos[0]);
        model.addTodo(sampleTodos[1]);
        model.addTodo(sampleTodos[2]);

        // EXECUTE
        List<TodoItem> todos = model.getTodoList();

        // ASSERT
        assertEquals(todos.get(0).getDescription(),  sampleTodos[0]);
        assertEquals(todos.get(1).getDescription(),  sampleTodos[1]);
        assertEquals(todos.get(2).getDescription(),  sampleTodos[2]);
    }

    @Test
    void addTodoShouldSucceed() {
        // SETUP
        Model model = Model.getInstance();
        model.deleteAllTodos();
        List<TodoItem> todos = model.getTodoList();

        // EXECUTE
        for(String s : sampleTodos) {
            model.addTodo(s);
        }

        // ASSERT
        // test number of items
        assertEquals(todos.size(), sampleTodos.length);

        // test item contents
        int id = 0;
        for(TodoItem todo : todos) {
            assertEquals(todo.getDescription(), sampleTodos[id]);
            assertEquals(todo.getId(), ++id);
            assertFalse(todo.isComplete());
        }
    }

    @Test
    void editTodoShouldSucceed() {
        // SETUP
        Model model = Model.getInstance();
        model.deleteAllTodos();
        model.addTodo(sampleTodos[0]);
        model.addTodo(sampleTodos[1]);
        model.addTodo(sampleTodos[2]);

        // EXECUTE
        String updatedTask = "Write Tests for MVC";
        model.editTodo(1, updatedTask);
        model.editTodo(2, updatedTask);
        model.editTodo(3, updatedTask);

        // ASSERT
        List<TodoItem> todos = model.getTodoList();
        assertEquals(todos.get(0).getDescription(), updatedTask);
        assertEquals(todos.get(1).getDescription(), updatedTask);
        assertEquals(todos.get(2).getDescription(), updatedTask);
    }


    @Test
    void editTodoShouldThrowException() {
        // SETUP
        Model model = Model.getInstance();
        model.deleteAllTodos();
        model.addTodo(sampleTodos[0]);
        model.addTodo(sampleTodos[1]);
        model.addTodo(sampleTodos[2]);
        String updatedTask = "Write Tests for MVC";

        // EXECUTE & ASSERT
        assertThrows(RuntimeException.class, () -> {
            model.editTodo(100, updatedTask);
        });
    }

    @Test
    void deleteTodoShouldSucceed() {
        // SETUP
        Model model = Model.getInstance();
        model.deleteAllTodos();
        model.addTodo(sampleTodos[0]);
        model.addTodo(sampleTodos[1]); // will be deleted
        model.addTodo(sampleTodos[2]);

        List<TodoItem> todos = model.getTodoList();
        List<TodoItem> expected = new ArrayList<>();
        expected.add(todos.get(0));
        expected.add(todos.get(2));

        // EXECUTE
        model.deleteTodo(2);

        // ASSERT
        assertArrayEquals(todos.toArray(), expected.toArray());
    }

    @Test
    void toggleCompleteShouldSucceed() {
        // SETUP
        Model model = Model.getInstance();
        model.deleteAllTodos();
        model.addTodo(sampleTodos[0]);
        model.addTodo(sampleTodos[1]); // will be deleted
        model.addTodo(sampleTodos[2]);
        List<TodoItem> todos = model.getTodoList();

        // EXECUTE
        model.toggleComplete(1);
        model.toggleComplete(2);
        model.toggleComplete(3);

        // ASSERT
        assertTrue(todos.get(0).isComplete());
        assertTrue(todos.get(1).isComplete());
        assertTrue(todos.get(2).isComplete());

        model.toggleComplete(1);
        assertFalse(todos.get(0).isComplete());
    }

    @Test
    void toggleCompleteShouldThrowException() {
        // SETUP
        Model model = Model.getInstance();
        model.deleteAllTodos();
        model.addTodo(sampleTodos[0]);
        model.addTodo(sampleTodos[1]); // will be deleted
        model.addTodo(sampleTodos[2]);

        assertThrows(RuntimeException.class, () -> model.toggleComplete(100));
    }

    @Test
    void getTodoByIdShouldSucceed() {
        // SETUP
        Model model = Model.getInstance();
        model.deleteAllTodos();
        model.setTodoList(sampleTodos);
        List<TodoItem> todos = model.getTodoList();

        System.out.println(model.getTodoById(1));

        // EXECUTE
        TodoItem td1 = model.getTodoById(1);
        TodoItem td3 = model.getTodoById(3);
        TodoItem td5 = model.getTodoById(5);

        // ASSERT
        assertEquals(td1,todos.get(0));
        assertEquals(td3,todos.get(2));
        assertEquals(td5,todos.get(4));
    }

    @Test
    void getTodoByIdShouldThrowException() {

        // SETUP
        Model model = Model.getInstance();
        model.deleteAllTodos();
        TodoItem todo1 = new TodoItem(1, sampleTodos[0], false);
        TodoItem todo2 = new TodoItem(2, sampleTodos[1], false);

        model.setTodoList(sampleTodos);

        // EXECUTE & ASSERT
        assertThrows(RuntimeException.class, () -> model.getTodoById(120));
    }

    @Test
    void deleteAllTodosShouldSucceed() {
        // SETUP
        Model model = Model.getInstance();
        model.setTodoList(sampleTodos);

        // EXECUTE
        model.deleteAllTodos();

        // ASSERT
        assertEquals(model.getTodoList().size(),0);
    }
}