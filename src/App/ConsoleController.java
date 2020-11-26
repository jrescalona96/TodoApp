package App;

import java.util.HashMap;

public class ConsoleController {
    private static ConsoleController instance;
    private final Model data;
    private final ConsoleView console;

    private ConsoleController() {
        data = Model.getInstance();
        console = new ConsoleView(data.getTodoList());
        data.subscribe(console);
    }

    public static ConsoleController getInstance() {
        if(instance == null) {
            synchronized (ConsoleController.class) {
                if(instance == null) {
                    instance = new ConsoleController();
                }
            }
        }
        return instance;
    }

    public void run() {
        String userInput = "";
        while(!userInput.equals("Q")) {
            userInput = console.getMainMenuInput();
            switch (userInput) {
                case "A" -> handleAddTodo();
                case "E" -> handleEditTodo();
                case "D" -> handleDeleteTodo();
                case "C" -> handleToggleTodo();
                case "Q" -> handleExit();
                default -> System.out.print("Invalid Input. Please choose from the following options. ‼️\n");
            }
        }
    }

    public void handleAddTodo() {
        String taskDescription = console.onAddTodo();
        data.addTodo(taskDescription);
    }

    public void handleEditTodo() {
        if(data.getTodoList().isEmpty()) {
            console.renderErrorMessage("⛔️ Nothing to edit! \"Enter\" to continue...");
        } else {
            try {
                HashMap<String, String> res = console.onEditTodo();
                int id = Integer.parseInt(res.get("id"));
                String description = res.get("description");
                data.editTodo(id, description);
            } catch (Exception e ) {
                System.out.println(e.getMessage() + " ‼️");
            }
        }
    }

    public void handleDeleteTodo()  {
        if(data.getTodoList().isEmpty()) {
            console.renderErrorMessage("⛔️ Nothing to delete! \"Enter\" to continue...");
        } else {
            int taskId = console.onDeleteTodo();
            data.deleteTodo(taskId);
        }
    }

    public void handleToggleTodo() {
        if(data.getTodoList().isEmpty()) {
            console.renderErrorMessage("⛔️ No Tasks to complete! \"Enter\" to continue...");
        } else {
            try {
                int taskId = console.onToggleCompleteTodo();
                data.toggleComplete(taskId);
            } catch(Exception e) {
                // catch exception for ID not found only
                System.out.println("Cannot find task. ‼️");
            }
        }
    }

    public void handleExit() {
        console.onExit();
    }
}
