package com.Kaizen_frontend.frontend.views.user;

import com.Kaizen_frontend.frontend.domain.User;
import com.Kaizen_frontend.frontend.service.UserService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;


@Route("users")
public class UserList extends VerticalLayout {

    TextField filterText = new TextField();
    Grid<User> grid = new Grid<>(User.class);


    public UserList (UserService service) {
        setSizeFull();

        configureGrid();

        add(
                getToolbar(),
                grid
        );
    }

    private Component getToolbar() {
        filterText.setPlaceholder("Filter by ID...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);

        Button addUserButton = new Button("Add user");

        HorizontalLayout toolbar = new HorizontalLayout(filterText, addUserButton);

        return toolbar;
    }

    private void configureGrid() {
        grid.addClassName("user-grid");
        grid.setSizeFull();

        grid.setColumns("userId", "name", "lastname", "brigade");
        grid.getColumns().forEach(col-> col.setAutoWidth(true));
    }
}
