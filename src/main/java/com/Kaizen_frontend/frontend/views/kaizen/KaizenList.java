package com.Kaizen_frontend.frontend.views.kaizen;

import com.Kaizen_frontend.frontend.domain.Kaizen;
import com.Kaizen_frontend.frontend.domain.User;
import com.Kaizen_frontend.frontend.service.KaizenService;
import com.Kaizen_frontend.frontend.service.UserService;
import com.Kaizen_frontend.frontend.views.MainLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;


@Route(value = "kaizens", layout = MainLayout.class)
public class KaizenList extends VerticalLayout {

    Grid<Kaizen> grid = new Grid<>(Kaizen.class);
    TextField textField = new TextField();


    public KaizenList(KaizenService service) {
        setSizeFull();

        configureGrid();

        var grid = new Grid<Kaizen>(Kaizen.class);
        grid.setItems(service.getKaizens());


        add(
                getToolbar(),
                grid
        );
    }

    private Component getToolbar() {
        textField.setPlaceholder("Filter by userId");
        textField.setClearButtonVisible(true);
        textField.setValueChangeMode(ValueChangeMode.LAZY);

        Button addKaizen = new Button("Add Kaizen");

        HorizontalLayout toolbar = new HorizontalLayout(textField, addKaizen);
        return toolbar;
    }

    private void configureGrid() {
        grid.setSizeFull();
        grid.setColumns("kaizenId", "userId", "fillingDate", "completed", "problem", "solution");
    }
}
