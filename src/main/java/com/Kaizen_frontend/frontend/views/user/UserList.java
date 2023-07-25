package com.Kaizen_frontend.frontend.views.user;

import com.Kaizen_frontend.frontend.domain.User;
import com.Kaizen_frontend.frontend.exception.UserNotFoundException;
import com.Kaizen_frontend.frontend.service.UserService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;


@Route("users")
public class UserList extends VerticalLayout {

    TextField filterField = new TextField();
    IntegerField kaizenCountField = new IntegerField();
    Grid<User> grid = new Grid<>(User.class);
    UserForm form;
    private final UserService service;


    public UserList(UserService service) {
        this.service = service;
        setSizeFull();

        configureGrid();
        configureForm();

        add(
                getToolbar(),
                getContent()
        );

        updateList();

    }


    private Component getContent() {
        HorizontalLayout content = new HorizontalLayout(grid, form);
        content.setFlexGrow(2, grid);
        content.setFlexGrow(1, form);
        content.setSizeFull();

        return content;
    }

    private void configureForm() {
        form = new UserForm();
        form.setWidth("25em");
    }

    private Component getToolbar() {
        filteringByLastname();

        filteringByKaizenCount();

        Button filterButton = new Button("Add User");

        HorizontalLayout toolbar = new HorizontalLayout(filterField, kaizenCountField, filterButton);

        return toolbar;
    }


    private void updateList() {
        grid.setItems(service.getUsers());
    }


    private void configureGrid() {

        grid.addClassName("user-grid");
        grid.setSizeFull();

        grid.setColumns("userId", "name", "lastname", "brigade");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
    }

    private void filterUsersByLastname(String lastname) throws UserNotFoundException {
        try {
            grid.setItems(service.findByLastname(lastname));
        } catch (Exception e) {
            updateList();
        }
    }

    private void filteringByLastname() {
        filterField.setPlaceholder("Filter by lastname...");
        filterField.setClearButtonVisible(true);
        filterField.setValueChangeMode(ValueChangeMode.LAZY);
        filterField.addValueChangeListener(e -> {
            try {
                filterUsersByLastname(
                        filterField.getValue());
            } catch (UserNotFoundException ex) {
                updateList();
            }
        });
    }

    private void filterUsersByKaizenCount(int kaizenCount) throws UserNotFoundException {
        grid.setItems(service.findByKaizenCount(kaizenCount));
    }

    private void filteringByKaizenCount() {
        kaizenCountField.setPlaceholder("Filter by kaizen count...");
        kaizenCountField.setClearButtonVisible(true);
        kaizenCountField.setValueChangeMode(ValueChangeMode.LAZY);
        kaizenCountField.addValueChangeListener(e -> {
            try {
                if (kaizenCountField.getValue() != null) {
                    filterUsersByKaizenCount(kaizenCountField.getValue());
                } else {
                    updateList();
                }
            } catch (UserNotFoundException ex) {
                updateList();
            }
        });
    }

}
