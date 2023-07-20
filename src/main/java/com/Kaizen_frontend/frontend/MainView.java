package com.kaizen.frontend;


import com.kaizen.domain.User;
import com.kaizen.frontend.domain.UserForm;
import com.kaizen.service.dbService.KaizenDbService;
import com.kaizen.service.dbService.UserDbService;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@PageTitle("Main")
@Route(value = "main")
public class MainView extends VerticalLayout {

    private UserService userService;
    private Grid<User> userGrid = new Grid<>(User.class);
    @Autowired
    private UserForm form = new UserForm(this, userService);

    public void userRefresh() {
        userGrid.setItems(userService.getUserList());
    }


    public MainView(UserService userService) {
        this.userService = userService;
        userGrid.setColumns("userId", "name", "lastname", "brigade");
        HorizontalLayout mainContent = new HorizontalLayout(userGrid, form);
        mainContent.setSizeFull();
        userGrid.setSizeFull();

        add(mainContent);
        setSizeFull();
        userRefresh();
    }



}
