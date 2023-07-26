package com.Kaizen_frontend.frontend.views.user;

import com.Kaizen_frontend.frontend.domain.User;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;

public class UserForm extends FormLayout {
    Binder<User> binder = new BeanValidationBinder<>(User.class);

    TextField name = new TextField("First name");
    TextField lastName = new TextField("Last name");
    TextField brigade = new TextField("Brigade");

    Button save = new Button("Save");
    Button delete = new Button("Delete");
    Button cancel = new Button("Cancel");

    public UserForm() {
        binder.bindInstanceFields(this);

        add(
                name,
                lastName,
                brigade,
                createButtonLayout()
        );

    }

    public void setUser(User user) {
        binder.setBean(user);
    }

    private Component createButtonLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        cancel.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        save.addClickShortcut(Key.ENTER);
        cancel.addClickShortcut(Key.ESCAPE);

        return new HorizontalLayout(save, delete, cancel);
    }



}
