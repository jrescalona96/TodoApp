package App.ConsoleUIComponents;

import App.ConsoleUIComponents.common.Input;

public class SingleLineInput extends Input {
    String header;
    public SingleLineInput(String prompt) {
        super(prompt);
    }

    public SingleLineInput(String header, String prompt) {
        super(prompt);
        this.header = header;
    }

    @Override
    public void render() {
        if(header != null) {
            System.out.println('\n' +header.toUpperCase());
        }
        System.out.print(prompt);
        value = scanner.nextLine();
    }
}
