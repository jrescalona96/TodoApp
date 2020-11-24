package App;

import java.util.HashMap;

public class ConsoleController {
    private static ConsoleController instance;
    private ConsoleView view;
    private Model data;

    private ConsoleController() {
        _initialize();
    }

    private void _initialize() {
        data = Model.getInstance();
        view = new ConsoleView(data.getTodoList());
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
        data.subscribe(view);
        String userInput = " ";
        view.render();
        while(!userInput.equals("Q")) {
           userInput = view.getActiveInput().getValue().toUpperCase();
           int taskId = 0;
           switch (userInput) {
               case "1":
                   String taskDescription = handleAddTodo();
                   data.addTodo(taskDescription);
                   view.run();
                   break;
               case "2":
                   try {
                       HashMap<String, String> res = handleEditTodo();
                       data.editTodo(Integer.parseInt(res.get("id")), res.get("description"));
                       view.run();
                       break;
                   } catch (Exception e ) {
                       // catch exception for ID not found only
                       System.out.println("‼️ Cannot find task. ‼️");
                       continue;
                   }
               case "3":
                   taskId = handleDeleteTodo();
                   data.deleteTodo(taskId);
                   view.run();
                   break;
               case "4":
                   try {
                       taskId = handleToggleTodo();
                       data.toggleComplete(taskId);
                       view.run();
                   } catch(Exception e) {
                       // catch exception for ID not found only
                       System.out.println("‼️ Cannot find task. ‼️");

                   }
                   continue;
               case "Q":
                   System.out.println("\n🇺🇸  Exiting... 🇵🇭");
                   break;
               default:
                   System.out.print("‼️ Invalid Input. Please choose from the following options. ‼️\n");
                   view.run();
                   view.render();
                   break;
           }

        }

    }

    public String handleAddTodo() {
       return view.onAddTodo();
    }
    public HashMap<String, String> handleEditTodo() {
        return view.onEditTodo();
    }
    public int handleDeleteTodo() {
        return view.onDeleteTodo();
    }
    public int handleToggleTodo() {
        return view.onToggleCompleteTodo();
    }
}
