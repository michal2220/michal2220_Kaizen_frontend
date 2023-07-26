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
        closeEditor();
    }

    private void closeEditor() {
        form.setKaizen(null);
        form.setVisible(false);
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

        form.addSaveListener(this::saveKaizen);
        form.addDeleteListener(this::deleteKaizen);
        form.addCloseListener(e -> closeEditor());
    }

    private void deleteKaizen(KaizenForm.DeleteEvent deleteEvent) {
        service.deleteKaizen(deleteEvent.getKaizen());
        updateList();
    }

    private void saveKaizen(KaizenForm.SaveEvent saveEvent) {
        service.saveKaizen(saveEvent.getKaizen());
        updateList();
    }

    private Component getToolbar() {
        filteringByUserId();


        Button addKaizenButton = new Button("Add Kaizen");
        addKaizenButton.addClickListener(e-> addNewKaizen());

        HorizontalLayout toolbar = new HorizontalLayout(idField, addKaizenButton);
        return toolbar;
    }

    private void addNewKaizen() {
        grid.asSingleSelect().clear();
        editKaizen(new Kaizen());
    }

    private void configureGrid() {
        grid.setSizeFull();
        grid.setColumns("kaizenId", "userId", "fillingDate", "completed", "problem", "solution");

        grid.asSingleSelect().addValueChangeListener(e->editKaizen(e.getValue()));
    }

    private void editKaizen(Kaizen kaizen) {
        if (kaizen==null){
            closeEditor();
        } else {
            form.setKaizen(kaizen);
            form.setVisible(true);
        }
    }

    private void findByUserId(int kaizenId) {
        grid.setItems(service.findKaizenById(kaizenId));
    }

    private void filteringByUserId() throws NullPointerException {
        idField.setPlaceholder("Find by kaizenId...");
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
