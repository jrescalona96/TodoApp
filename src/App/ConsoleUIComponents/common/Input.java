package App.ConsoleUIComponents.common;

import java.util.Scanner;

public abstract class Input implements Widget {
    protected String value;
    protected Scanner scanner;
    protected String prompt;

    public Input(String prompt) {
        this.prompt = prompt;
        this.scanner = new Scanner(System.in);
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getPrompt() {
        return prompt;
    }
}
