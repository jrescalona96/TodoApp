package App.ConsoleUIComponents.common;

import java.util.ArrayList;
import java.util.List;

public class Container implements Widget {

    private List<Widget> widgets = new ArrayList<>();

    public Container() { }

    public Container(List<Widget> widgets) {
        this.widgets = widgets;
    }

    public List<Widget> getWidgets() {
        return widgets;
    }

    public void setWidgets(List<Widget> widgets) {
        this.widgets = widgets;
    }

    public void addWidget(Widget widget) {
        widgets.add(widget);
    }

    public void removeWidget(Widget widget) {
        widgets.remove(widget);
    }

    @Override
    public void render() {
        for (Widget w : widgets) {
            w.render();
        }
    }
}
