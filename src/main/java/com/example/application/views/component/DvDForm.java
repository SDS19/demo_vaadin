package com.example.application.views.component;

import com.example.application.entity.DVD;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.shared.Registration;

public class DvDForm extends FormLayout {

    private DVD dvd;

    Binder<DVD> binder = new BeanValidationBinder<>(DVD.class);

    TextField id = new TextField("id");
    TextField title = new TextField("Title");
    TextField imdbScore = new TextField("IMDB score");
    TextField year = new TextField("Year");
    TextField runtime = new TextField("Runtime");

    Button save = new Button("Save");
    Button delete = new Button("Delete");
    Button cancel = new Button("Cancel");

    public DvDForm() {
        addClassName("dvd-form");
        binder.bindInstanceFields(this);

        add(
                title,
                imdbScore,
                year,
                runtime,
                createButtonLayout()//split this out into a method, to keep add method nice and clean
        );
    }

    private Component createButtonLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);//make this button stand out, the primary action that we expect people to do
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        cancel.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        save.addClickListener(event -> validateAndSave());
        delete.addClickListener(event -> fireEvent(new DeleteEvent(this,dvd)));
        cancel.addClickListener(event -> fireEvent(new CloseEvent(this)));

        save.addClickShortcut(Key.ENTER);
        cancel.addClickShortcut(Key.ESCAPE);
        return new HorizontalLayout(save, delete, cancel);
    }

    private void validateAndSave() {
        try {
            binder.writeBean(dvd);
            fireEvent(new SaveEvent(this,dvd));
        } catch (ValidationException e) {
            e.printStackTrace();
        }
    }

    /**
     * binding the dvd values to the UI fields
     * @param dvd selected in the grid
     */
    public void setDvD(DVD dvd) {
        this.dvd = dvd;
        binder.readBean(dvd);
    }

    /**
     * define events for button components
     * "save","delete","update"
     */
    public static abstract class DvDFormEvent extends ComponentEvent<DvDForm> {
        private DVD dvd;

        protected DvDFormEvent(DvDForm source, DVD dvd) {
            super(source, false);
            this.dvd = dvd;
        }

        public DVD getDvD() {
            return dvd;
        }
    }

    public static class SaveEvent extends DvDFormEvent {
        SaveEvent(DvDForm source, DVD dvd) {
            super(source, dvd);
        }
    }

    public static class DeleteEvent extends DvDFormEvent {
        DeleteEvent(DvDForm source, DVD dvd) {
            super(source, dvd);
        }

    }

    public static class CloseEvent extends DvDFormEvent {
        CloseEvent(DvDForm source) {
            super(source, null);
        }
    }

    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType, ComponentEventListener<T> listener) {
        return getEventBus().addListener(eventType, listener);
    }

}
