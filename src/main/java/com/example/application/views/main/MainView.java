package com.example.application.views.main;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

//@Route("")//makes the view accessible to the end user "http://localhost:8080/"
public class MainView extends VerticalLayout {//components added to MainView will be ordered vertically

    public MainView() {
        VerticalLayout todosList = new VerticalLayout();//vertical layout

        TextField taskField = new TextField();//text input field
        Button addButton = new Button("Add");

        addButton.addClickListener(click -> {
            Checkbox checkbox = new Checkbox(taskField.getValue());
            todosList.add(checkbox);
        });
        addButton.addClickShortcut(Key.ENTER);

        add(
                new H1("Vaadin Todo"),
                todosList,
                new HorizontalLayout(//in Horizontal Layout
                        taskField,
                        addButton
                )
        );
    }
}