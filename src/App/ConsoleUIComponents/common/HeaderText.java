package App.ConsoleUIComponents.common;

public class HeaderText extends Text {
    public HeaderText(String text) {
        super(text.toUpperCase());
    }

    @Override
    public void render() {
        System.out.println("\n" + text);
    }
}
