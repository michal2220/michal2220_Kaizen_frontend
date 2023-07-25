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
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;


@Route("users")
public class UserList extends VerticalLayout {

    TextField filterField = new TextField();
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

    private void filterUsersByLastname(String lastname) throws UserNotFoundException {
        grid.setItems(service.findByLastname(lastname));
    }

    private void updateList() {
        grid.setItems(service.getUsers());
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
        filterField.setPlaceholder("Filter by lastname...");
        filterField.setClearButtonVisible(true);
        filterField.setValueChangeMode(ValueChangeMode.LAZY);
        filterField.addValueChangeListener(e -> updateList());

        Button filterButton = new Button("Filter");

        filterButton.addClickListener(e -> {
            try {
                filterUsersByLastname(filterField.getValue());
            } catch (UserNotFoundException ex) {
                updateList();
            }
        });

        HorizontalLayout toolbar = new HorizontalLayout(filterField, filterButton);

        return toolbar;
    }

    private void configureGrid() {

        grid.addClassName("user-grid");
        grid.setSizeFull();

        grid.setColumns("userId", "name", "lastname", "brigade");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
    }
}
