package App;

import App.ConsoleUIComponents.*;
import App.ConsoleUIComponents.common.*;

import java.util.*;

public class ConsoleView implements View {
    private final Container viewport;
    private final MainMenu mainMenu;
    private List<TodoItem> todos;
    private Input activeInput;
    private TodoList todoList;


    // CONSTRUCTOR
    public ConsoleView(List<TodoItem> todos) {
        mainMenu = new MainMenu();
        viewport = new Container();
        this.todos = todos;
        _renderAppTitle();
        _renderTodoList();
    }

    public Input getActiveInput() {
        return activeInput;
    }

    public void setActiveInput(Input activeInput) {
        this.activeInput = activeInput;
    }

    @Override
    public String onAddTodo() {
        _renderAppTitle();
        _renderTodoList();
        _renderSingleInputForm("📌 ADD NEW TASK", "📝 Enter description: ");
        return activeInput.getValue();
    }

    @Override
    public HashMap<String, String> onEditTodo() {
        _renderAppTitle();
        HashMap<String, String> res = new HashMap<>();

        _renderTodoList();

        // get task id
        _renderSingleInputForm("✏️ EDIT A TASK", "🆔 Select task number: ");
        res.put("id", activeInput.getValue());

        // get new task description
        _resetViewport();
        activeInput = new SingleLineInput("📝 Enter description: ");
        activeInput.render();
        res.put("description", activeInput.getValue());

        _clearScreen();

        return res;
    }

    @Override
    public int onDeleteTodo() {
        _renderAppTitle();
        _renderTodoList();
        int res = 0;
        _renderSingleInputForm("❌ DELETE A TASK", "🆔 Select task number: ");
        res = Integer.parseInt(activeInput.getValue());
        return res;
    }

    @Override
    public int onToggleCompleteTodo() {
        _renderAppTitle();
        _renderTodoList();
        _renderSingleInputForm("✅ TOGGLE A TASK","🆔 Select task number: ");
        _clearScreen();
        return Integer.parseInt(activeInput.getValue());
    }

    public void renderErrorMessage(String message) {
        _renderAppTitle();
        viewport.addWidget(new SingleLineInput(message));
        render();
        _resetViewport();
        _clearScreen();
    }

    /**
     * Renders console Main Menu
     * @return Uppercase user input
     */
    public String getMainMenuInput() {
        _renderMainMenu();
        return activeInput.getValue().toUpperCase();
    }

    public void onExit() {
        _clearScreen();
        System.out.println("\n 👋 Bye... \n\n");
    }

    private void _renderAppTitle() {
        _resetViewport();
        _clearScreen();
        Widget title = new HeaderText("💯 TODO CONSOLE APP : VERSION 1.0.0");;
        viewport.addWidget(title);
        render();
        _resetViewport();
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
        this.todoList = new TodoList(todos);
        viewport.addWidget(todoList);
        render();
        _resetViewport();
    }

    private void _renderSingleInputForm(String header, String prompt) {
        _resetViewport();
        activeInput = new SingleLineInput(header, prompt);
        viewport.addWidget(activeInput);
        render();
        _resetViewport();
    }

    private void _resetViewport() {
        viewport.setWidgets(new ArrayList<>());
    }

    private static void _clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    @Override
    public void update(List<TodoItem> todos) {
        this.todos = todos;
        _clearScreen();
        _renderAppTitle();
        _renderTodoList();
    }

    @Override
    public void render() {
        viewport.render();
    }
}


