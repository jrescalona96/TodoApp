package App.ConsoleUIComponents.common;

public class Border implements Widget {
    int length;
    String symbol;

    public Border(String symbol ,int length) {
        this.symbol = symbol;
        this.length = length;
    }

    @Override
    public void render() {
        for (int i = 0; i < length; i += symbol.length()) {
            System.out.print(symbol);
        }
    }
}
