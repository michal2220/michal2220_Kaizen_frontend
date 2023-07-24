package com.Kaizen_frontend.frontend;

import com.Kaizen_frontend.frontend.domain.User;
import com.Kaizen_frontend.frontend.service.UserService;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("users")
public class UserList extends VerticalLayout {

    public UserList (UserService service) {

        var grid = new Grid<User>(User.class);
        grid.setItems(service.getUsers());

        grid.setColumns("userId", "name", "lastname", "brigade");

        add(grid);
    }
}
