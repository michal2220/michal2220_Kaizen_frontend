package com.Kaizen_frontend.frontend.views.user;

import com.Kaizen_frontend.frontend.domain.User;
import com.Kaizen_frontend.frontend.exception.UserNotFoundException;
import com.Kaizen_frontend.frontend.service.UserService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
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
    IntegerField lessKaizenField = new IntegerField();
    IntegerField moreKaizenField = new IntegerField();
    IntegerField brigadeField = new IntegerField();
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
        closeEditor();
    }

    private void closeEditor() {
        form.setUser(null);
        form.setVisible(false);
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

        form.addSaveListener(this::saveUser);
        form.addDeleteListener(this::deleteContact);
        form.addCloseListener(e->closeEditor());
    }

    private void deleteContact(UserForm.DeleteEvent deleteEvent) {
        service.deleteUser(deleteEvent.getUser());
        updateList();
        closeEditor();
    }

    private void saveUser(UserForm.SaveEvent saveEvent) {
        service.saveUser(saveEvent.getUser());
        updateList();
    }

    private Component getToolbar() {
        filteringByLastname();
        filteringByKaizenCount();
        filteringByLessKaizen();
        filteringByMoreKaizen();
        filteringByBrigade();

        Button addUser = new Button("Add User");
        addUser.addClickListener(e-> addUser());



        HorizontalLayout toolbar = new HorizontalLayout(filterField, brigadeField, kaizenCountField,
                lessKaizenField, moreKaizenField, addUser);

        return toolbar;
    }

    private void addUser() {
        grid.asSingleSelect().clear();
        editUser(new User());
    }


    private void updateList() {
        grid.setItems(service.getUsers());
    }


    private void configureGrid() {

        grid.addClassName("user-grid");
        grid.setSizeFull();

        grid.setColumns("userId", "name", "lastname", "brigade", "kaizenListSize");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));

        grid.asSingleSelect().addValueChangeListener(e -> editUser(e.getValue()));
    }

    private void editUser(User user) {
        if (user==null){
            closeEditor();
        } else {
            form.setUser(user);
            form.setVisible(true);
        }
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

    private void filterUsersWithLessKaizen(int kaizenCount) throws UserNotFoundException {
        grid.setItems(service.findWithLessKaizenThen(kaizenCount));
    }

    private void filteringByLessKaizen() {
        lessKaizenField.setPlaceholder("Filter users with less then...");
        lessKaizenField.setClearButtonVisible(true);
        lessKaizenField.setValueChangeMode(ValueChangeMode.LAZY);
        lessKaizenField.addValueChangeListener(e -> {
            try {
                if (lessKaizenField.getValue() != null) {
                    filterUsersWithLessKaizen(lessKaizenField.getValue());
                } else {
                    updateList();
                }
            } catch (UserNotFoundException ex) {
                updateList();
            }
        });
    }

    private void filterUsersWithMoreKaizen(int kaizenCount) throws UserNotFoundException {
        grid.setItems(service.findWithMoreKaizenThen(kaizenCount));
    }

    private void filteringByMoreKaizen() {
        moreKaizenField.setPlaceholder("Filter users with more then...");
        moreKaizenField.setClearButtonVisible(true);
        moreKaizenField.setValueChangeMode(ValueChangeMode.LAZY);
        moreKaizenField.addValueChangeListener(e -> {
            try {
                if (moreKaizenField.getValue() != null) {
                    filterUsersWithMoreKaizen(moreKaizenField.getValue());
                } else {
                    updateList();
                }
            } catch (UserNotFoundException ex) {
                updateList();
            }
        });
    }

    private void filterUsersByBrigade(int brigade) throws UserNotFoundException {
        grid.setItems(service.findByBrigade(brigade));
    }

    private void filteringByBrigade() {
        brigadeField.setPlaceholder("Filter by brigade...");
        brigadeField.setClearButtonVisible(true);
        brigadeField.setValueChangeMode(ValueChangeMode.LAZY);
        brigadeField.addValueChangeListener(e -> {
            try {
                if (brigadeField.getValue() != null) {
                    filterUsersByBrigade(brigadeField.getValue());
                } else {
                    updateList();
                }
            } catch (UserNotFoundException ex) {
                updateList();
            }
        });
    }

}
