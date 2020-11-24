package App;

import App.ConsoleUIComponents.*;
import App.ConsoleUIComponents.common.*;

import java.util.*;

public class ConsoleView implements View {
    private Container viewport;
    private Input activeInput;
    private TodoList todoList;
    private MainMenu mainMenu;

    final private Widget title = new HeaderText("TODO CONSOLE APP : VERSION 1.0.0");;

    // CONSTRUCTOR
    public ConsoleView(List<TodoItem> todos) {
        mainMenu = new MainMenu();
        viewport = new Container();
        viewport.addWidget(title);
        update(todos);
        _renderMainMenu();
    }

    public Input getActiveInput() {
        return activeInput;
    }

    public void setActiveInput(Input activeInput) {
        this.activeInput = activeInput;
    }

    public String onAddTodo() {
        _clearScreen();
        // build and render add form
        _renderSingleInputForm("📌 ADD NEW TASK", "📝 Enter description: ");
        _clearScreen();
        return activeInput.getValue();
    }

    public HashMap<String, String> onEditTodo() {
        _clearScreen();
        HashMap<String, String> res = new HashMap<>();

        // render current todos
        _renderTodoList();

        // get task id
        _renderSingleInputForm("✏️ EDIT A TASK","🆔 Select task number: ");
        res.put("id", activeInput.getValue());

        // get new task description
        activeInput = new SingleLineInput("📝 Enter description: ");
        activeInput.render();
        res.put("description", activeInput.getValue());
        _clearScreen();
        return res;
    }

    public int onDeleteTodo() {
        _clearScreen();
        // build and render add form
        _renderTodoList();
        _renderSingleInputForm("❌ DELETE A TASK","🆔 Select task number: ");
        _clearScreen();
        return Integer.parseInt(activeInput.getValue());
    }

    public int onToggleCompleteTodo() {
        _clearScreen();
        _renderTodoList();
        _renderSingleInputForm("✅ TOGGLE A TASK","🆔 Select task number: ");
        _clearScreen();
        return Integer.parseInt(activeInput.getValue());
    }

    private void _renderMainMenu() {
        _resetViewport();
        activeInput = this.mainMenu.getForm();
        viewport.addWidget(activeInput);
        render();
        _resetViewport();
    }

    private void _renderTodoList() {
        _resetViewport();
        viewport.addWidget(todoList);
        render();
        _resetViewport();
    }

    private void _renderSingleInputForm(String header, String prompt) {
        _resetViewport();
        activeInput = new SingleLineInput(header, prompt);
        viewport.addWidget(activeInput);
        render();
    }

    private void _resetViewport() {
        viewport.setWidgets(new ArrayList<>());
    }

    private static void _clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    @Override
    public void run() {
        _renderMainMenu();
    }

    @Override
    public void update(List<TodoItem> todos) {
        todoList = new TodoList(todos);
        _renderTodoList();
    }

    @Override
    public void render() {
        viewport.render();
    }
}


