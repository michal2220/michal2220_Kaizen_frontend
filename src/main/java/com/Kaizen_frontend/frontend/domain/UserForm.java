package com.kaizen.frontend.domain;

import com.kaizen.domain.User;
import com.kaizen.frontend.MainView;
import com.kaizen.frontend.UserService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserForm extends FormLayout {

    private final MainView mainView;
    @Autowired
    private UserService userService;
    private TextField name = new TextField("Name");
    private TextField lastname = new TextField("Lastname");
    private TextField brigade = new TextField("Brigade");

    private Button save = new Button("Save");
    private Button delete = new Button("Delete");
    private Binder<User> binder = new Binder<User>(User.class);


    private void save() {
        User user = binder.getBean();
        userService.save(user);
        mainView.userRefresh();
/*        setUser(null);*/
    }

    private void delete() {
        User user = binder.getBean();
        userService.delete(user);
        mainView.userRefresh();
        /*        setUser(null);*/
    }

    public UserForm(MainView mainView, UserService userService) {
        this.mainView = mainView;
        this.userService = userService;
        HorizontalLayout buttons = new HorizontalLayout(save, delete);
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        add(name, lastname, brigade, buttons);

        save.addClickListener(event -> save());
        delete.addClickListener(event -> delete());

        binder.bindInstanceFields(this);
    }
}
