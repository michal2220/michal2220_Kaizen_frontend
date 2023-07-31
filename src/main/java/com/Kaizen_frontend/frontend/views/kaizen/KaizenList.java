package com.Kaizen_frontend.frontend.views.kaizen;

import com.Kaizen_frontend.frontend.domain.Kaizen;
import com.Kaizen_frontend.frontend.service.KaizenService;
import com.Kaizen_frontend.frontend.views.MainLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import org.springframework.web.reactive.function.client.WebClientResponseException;


@Route(value = "kaizens", layout = MainLayout.class)
public class KaizenList extends VerticalLayout {

    Grid<Kaizen> grid = new Grid<>(Kaizen.class);
    IntegerField idField = new IntegerField();
    DatePicker olderThan = new DatePicker();
    TextField createdByName = new TextField();
    TextField createdByLastname = new TextField();
    Button filterByCreatorButton = new Button("Filter");
    Button shwoAllButton = new Button("Show all");
    Button addKaizenButton = new Button("Add Kaizen");

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
        form.translate.addClickListener(event -> {
            form.translateField.setValue(service.getTranslation(form.kaizenId.getValue()));
        });
    }

    private void deleteKaizen(KaizenForm.DeleteEvent deleteEvent) {
        service.deleteKaizen(deleteEvent.getKaizen());
        updateList();
    }

    private void saveKaizen(KaizenForm.SaveEvent saveEvent) {
        service.saveKaizen(saveEvent.getKaizen());
        updateList();
        closeEditor();
    }

    private Component getToolbar() {
        filteringByKaizenId();
        filterOlderThan();
        filterCreatedBy();


        addKaizenButton.addClickListener(e -> addNewKaizen());

        shwoAllButton.addClickListener(e->updateList());


        return new VerticalLayout(
                new HorizontalLayout(idField, olderThan, addKaizenButton, shwoAllButton),
                new HorizontalLayout(createdByName,createdByLastname,filterByCreatorButton));
    }

    private void addNewKaizen() {
        grid.asSingleSelect().clear();
        editKaizen(new Kaizen());
    }

    private void configureGrid() {
        grid.setSizeFull();
        grid.setColumns("kaizenId", "userId", "fillingDate", "completed", "problem", "solution");

        grid.asSingleSelect().addValueChangeListener(e -> editKaizen(e.getValue()));
    }

    private void editKaizen(Kaizen kaizen) {
        if (kaizen == null) {
            closeEditor();
        } else {
            form.setKaizen(kaizen);
            form.setVisible(true);
        }
    }

    private void findByKaizenId(int kaizenId) {
        try {
            grid.setItems(service.findKaizenById(kaizenId));
        } catch (Exception e) {
            updateList();
        }
    }

    private void filteringByKaizenId() throws NullPointerException {
        idField.setPlaceholder("Find by kaizenId...");
        idField.setClearButtonVisible(true);
        idField.setValueChangeMode(ValueChangeMode.LAZY);
        idField.addValueChangeListener(e -> {
            try {
                findByKaizenId(idField.getValue());

            } catch (NullPointerException ex) {
                updateList();
            }
        });
    }

    private void filterOlderThan() {
        olderThan.setClearButtonVisible(true);
        olderThan.setPlaceholder("Older than");
        olderThan.addValueChangeListener(e -> {
            try {
                grid.setItems(service.getKaizensOlderThan(olderThan.getValue()));
            } catch (NullPointerException | WebClientResponseException exception) {
                updateList();
            }
        });
    }

    private void filterCreatedBy() {
        createdByName.setClearButtonVisible(true);
        createdByName.setPlaceholder("Name");
        createdByLastname.setClearButtonVisible(true);
        createdByLastname.setPlaceholder("Lastname");


        filterByCreatorButton.addClickListener(e -> {
            try {
                grid.setItems(service.getKaizensCreatedBy(createdByName.getValue(),
                        createdByLastname.getValue()));
            } catch (Exception exception) {
                updateList();
            }
        });
    }

}
