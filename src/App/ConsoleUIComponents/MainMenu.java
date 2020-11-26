package App.ConsoleUIComponents;

import App.ConsoleUIComponents.common.Break;
import App.ConsoleUIComponents.common.Container;
import App.ConsoleUIComponents.common.Widget;

import java.util.Arrays;
import java.util.List;

public class MainMenu implements Widget {
    private final MenuInput form;

    public MainMenu() {
        String prompt = "What would you like to do❓";
        List<String> options = Arrays.asList("[A] 📌 Add Task", "[E] ✏️ Edit Task", "[D] ❌ Delete Task", "[C] ✅ Complete Task", "[Q] ⛔️ Quit");
        this.form = new MenuInput(options, prompt);
    }

    public MenuInput getForm() {
        return form;
    }

    @Override
    public void render() {
        Container container = new Container(Arrays.asList(new Break(), this.form));
        container.render();
    }
}