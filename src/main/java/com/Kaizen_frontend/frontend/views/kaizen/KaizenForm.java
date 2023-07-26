package com.Kaizen_frontend.frontend.views.kaizen;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;

public class KaizenForm extends FormLayout {

    TextField kaizenProblem = new TextField("problem");
    TextField kaizenSolution = new TextField("solution");
    IntegerField userId = new IntegerField("userId");
    DatePicker fillingPicker = new DatePicker("fillingDate");
    TextField completed = new TextField("completed");
    DatePicker completionPicker = new DatePicker("completionDate");
    TextField rewarded = new TextField("rewarded");
    IntegerField rewardId = new IntegerField("rewardId");

    Button save = new Button("Save");
    Button delete = new Button("Delete");
    Button cancel = new Button("Cancel");

    public KaizenForm() {
        add(
                kaizenProblem,
                kaizenSolution,
                userId,
                fillingPicker,
                completed,
                completionPicker,
                rewarded,
                rewardId,
                createButtonLayout()
        );
    }

    private Component createButtonLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_SUCCESS);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        save.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        save.addClickShortcut(Key.ENTER);
        cancel.addClickShortcut(Key.ESCAPE);

        return new HorizontalLayout(save, delete, cancel);
    }
}
