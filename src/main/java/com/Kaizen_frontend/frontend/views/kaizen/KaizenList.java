package com.Kaizen_frontend.frontend.views.kaizen;

import com.Kaizen_frontend.frontend.domain.Kaizen;
import com.Kaizen_frontend.frontend.service.KaizenService;
import com.Kaizen_frontend.frontend.views.MainLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;


@Route(value = "kaizens", layout = MainLayout.class)
public class KaizenList extends VerticalLayout {

    Grid<Kaizen> grid = new Grid<>(Kaizen.class);
    IntegerField idField = new IntegerField();
    private KaizenForm form;
    private final KaizenService service;

    public KaizenList(KaizenService service) {
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

    private void updateList() {
        grid.setItems(service.getKaizens());
    }

    private Component getContent() {
        HorizontalLayout content = new HorizontalLayout(grid, form);
        content.setFlexGrow(2, grid);
        content.setFlexGrow(1, form);
        content.setSizeFull();

        return content;
    }

    private void configureForm() {
        form = new KaizenForm();
        form.setWidth("25em");
    }

    private Component getToolbar() {
        filteringByUserId();


        Button addKaizen = new Button("Add Kaizen");

        HorizontalLayout toolbar = new HorizontalLayout(idField, addKaizen);
        return toolbar;
    }

    private void configureGrid() {
        grid.setSizeFull();
        grid.setColumns("kaizenId", "userId", "fillingDate", "completed", "problem", "solution");
    }

    private void findByUserId(int kaizenId) {
        grid.setItems(service.findKaizenById(kaizenId));
    }

    private void filteringByUserId() throws NullPointerException {
        idField.setPlaceholder("Find by kaizenId");
        idField.setClearButtonVisible(true);
        idField.setValueChangeMode(ValueChangeMode.LAZY);
        idField.addValueChangeListener(e -> {
            try {



                    findByUserId(idField.getValue());

            } catch (NullPointerException ex) {
                updateList();
            }
        });

    }


}
