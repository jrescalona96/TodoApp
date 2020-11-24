package App.Testing;

import App.TodoItem;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TodoItemTest {

    static final String DESCRIPTION = "Test TodoItem Class";

    @Test
    void getId() {
        TodoItem todo = new TodoItem(1, DESCRIPTION ,false);
        assertEquals(todo.getId(), 1);
    }

    @Test
    void setId() {
        TodoItem todo = new TodoItem(1, DESCRIPTION ,false);
        todo.setId(100);
        int id = todo.getId();
        assertEquals(id, 100);
    }

    @Test
    void getDescription() {
        TodoItem todo = new TodoItem(1, DESCRIPTION ,false);
        assertEquals(todo.getDescription(), DESCRIPTION);
    }

    @Test
    void setDescription() {
        TodoItem todo = new TodoItem(1, "DESCRIPTION IS SOMETHING DIFFERENT" ,false);
        todo.setDescription(DESCRIPTION);
        assertEquals(todo.getDescription(), DESCRIPTION);
    }

    @Test
    void isComplete() {
        TodoItem todo = new TodoItem(1, DESCRIPTION ,false);
        assertEquals(todo.isComplete(), false);
    }

    @Test
    void setComplete() {
        TodoItem todo = new TodoItem(1, DESCRIPTION ,false);
        todo.setComplete(true);
        assertEquals(todo.isComplete(), true);

    }
}