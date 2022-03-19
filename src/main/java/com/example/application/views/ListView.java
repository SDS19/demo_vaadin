package com.example.application.views;

import com.example.application.entity.DVD;
import com.example.application.exception.DaoException;
import com.example.application.service.DvDService;
import com.example.application.views.component.DvDForm;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("DVD lib")
@Route(value = "", layout = MainLayout.class)
public class ListView extends VerticalLayout {

    private DvDService service;

    Grid<DVD> grid = new Grid<>(DVD.class);//a component for showing tabular data
    TextField title = new TextField();
    TextField score = new TextField();
    DvDForm form;

    public ListView(DvDService service) {
        this.service = service;
        addClassName("list-view");//add CSS class for style it later
        setSizeFull();//make the view the same size as the browser window

        configureGrid();
        configureForm();

        add(
                getToolbar(),
                getContent()
        );

        updateList();//update the list of contact
        closeEditor();
    }

    /*============================== configuration ==============================  */

    private void configureGrid() {
        grid.addClassName("dvd-grid");
        grid.setSizeFull();
        grid.setColumns("title","imdbScore","year","runtime");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));//automatically resize the column width

        grid.asSingleSelect().addValueChangeListener(e -> editDvD(e.getValue()));//select or deselect contact
    }

    private void configureForm() {
        form = new DvDForm();
        form.setWidth("25em");

        form.addListener(DvDForm.SaveEvent.class, this::saveDvD);
        form.addListener(DvDForm.DeleteEvent.class, this::deleteDvD);
        form.addListener(DvDForm.CloseEvent.class, e -> closeEditor());
    }

    /*============================== component ==============================  */

    private Component getToolbar() {
        title.setPlaceholder("Filter by title...");
        title.setClearButtonVisible(true);//button for clear text field
        title.setValueChangeMode(ValueChangeMode.LAZY);//wait user to stop typing, don't hit the db on every single keystroke
        title.addValueChangeListener(e -> updateList());

        score.setPlaceholder("Greater than score...");
        score.setClearButtonVisible(true);//button for clear text field
        score.setValueChangeMode(ValueChangeMode.LAZY);//wait user to stop typing, don't hit the db on every single keystroke
        score.addValueChangeListener(e -> updateList());

        Button addDvDBtn = new Button("Add DVD");
        addDvDBtn.addClickListener(e -> addDvD());

        //score
        HorizontalLayout toolbar = new HorizontalLayout(title,score,addDvDBtn);
        toolbar.addClassName("toolbar");
        return toolbar;
    }

    private Component getContent() {
        HorizontalLayout content = new HorizontalLayout(grid,form);
        content.setFlexGrow(2,grid);//set space allocation
        content.setFlexGrow(1,form);
        content.addClassName("content");
        content.setSizeFull();
        return content;
    }

    /*============================== CRUD ==============================  */

    private void editDvD(DVD dvd) {
        if (dvd == null) closeEditor();
        else {
            form.setDvD(dvd);
            form.setVisible(true);
            addClassName("editing");
        }
    }

    private void closeEditor() {
        form.setDvD(null);
        form.setVisible(false);
        removeClassName("editing");
    }

    private void saveDvD(DvDForm.SaveEvent event) {
        try {
            service.saveDvD(event.getDvD());
        } catch (DaoException e) {
            e.printStackTrace();
        } finally {
            updateList();
            closeEditor();
        }
    }

    private void deleteDvD(DvDForm.DeleteEvent event) {
        try {
            service.deleteDvD(event.getDvD());
        } catch (DaoException e) {
            e.printStackTrace();
        } finally {
            updateList();
            closeEditor();
        }
    }

    private void addDvD() {
        grid.asSingleSelect().clear();
        editDvD(new DVD());
    }

    private void updateList() {
        grid.setItems(service.findAllDvDs(title.getValue(),score.getValue()));
    }

}
