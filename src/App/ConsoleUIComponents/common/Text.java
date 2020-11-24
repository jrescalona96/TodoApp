package App.ConsoleUIComponents.common;

public class Text implements Widget {
    protected String text;

    public Text(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public void render() {
        System.out.println(text);
    }
}
