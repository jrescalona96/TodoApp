package App.ConsoleUIComponents;

import App.ConsoleUIComponents.common.Input;
import App.ConsoleUIComponents.common.Text;
import App.ConsoleUIComponents.common.Widget;

import java.util.ArrayList;
import java.util.List;

public class MenuInput extends Input {
    private List<Widget> options;

    public MenuInput(List<String> options, String prompt) {
        super(prompt);
        this.options = new ArrayList<>();
        for (String item : options) {
            Text text = new Text(item);
            this.options.add(text);
        }
    }

    public List<Widget> getOptions() {
        return options;
    }

    public void setOptions(List<Widget> options) {
        this.options = options;
    }

    @Override
    public void render() {
        options.forEach(Widget::render);
        System.out.print(prompt);
        value = scanner.next();
    }
}
