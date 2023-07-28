package com.Kaizen_frontend.frontend.views.kaizen;

import com.Kaizen_frontend.frontend.domain.Kaizen;
import com.Kaizen_frontend.frontend.domain.Reward;
import com.Kaizen_frontend.frontend.domain.User;
import com.Kaizen_frontend.frontend.views.user.UserForm;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.shared.Registration;

import java.util.List;

public class KaizenForm extends FormLayout {

    Binder<Kaizen> binder = new BeanValidationBinder<>(Kaizen.class);

    TextField problem = new TextField("problem");
    TextField solution = new TextField("solution");
    IntegerField userId = new IntegerField("userId");
    DatePicker fillingDate = new DatePicker("fillingDate");
    ComboBox<Boolean> completed = new ComboBox<>("completed");
    DatePicker completionDate = new DatePicker("completionDate");
    ComboBox<Boolean> rewarded = new ComboBox<>("rewarded");
    IntegerField rewardId = new IntegerField("rewardId");

    TextField translateField = new TextField("Translation");

    Button save = new Button("Save");
    Button delete = new Button("Delete");
    Button cancel = new Button("Cancel");
    Button translate = new Button("Translate");

    public KaizenForm() {
        binder.bindInstanceFields(this);
        completed.setItems(true, false);
        rewarded.setItems(true, false);
        translateField.setSizeFull();

        add(
                problem,
                solution,
                userId,
                fillingDate,
                completed,
                completionDate,
                rewarded,
                rewardId,
                createButtonLayout(),
                translateField
        );
    }

    public void setKaizen(Kaizen kaizen){
        binder.setBean(kaizen);
    }

    private Component createButtonLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        save.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        translate.addThemeVariants(ButtonVariant.LUMO_PRIMARY,
                ButtonVariant.LUMO_SUCCESS);


        save.addClickListener(event -> validateAndSave());
        delete.addClickListener(event -> fireEvent(new DeleteEvent(this, binder.getBean())));
        cancel.addClickListener(event -> fireEvent(new CloseEvent(this)));

        save.addClickShortcut(Key.ENTER);
        cancel.addClickShortcut(Key.ESCAPE);

        return new HorizontalLayout(save, delete, cancel);
    }

    private void validateAndSave() {
        if (binder.isValid()) {
            fireEvent(new SaveEvent(this, binder.getBean()));
        }
    }

    // Events
    public static abstract class KaizenFormEvent extends ComponentEvent<KaizenForm> {
        private final Kaizen kaizen;

        protected KaizenFormEvent(KaizenForm source, Kaizen kaizen) {
            super(source, false);
            this.kaizen = kaizen;
        }

        public Kaizen getKaizen() {
            return kaizen;
        }
    }

    public static class SaveEvent extends KaizenForm.KaizenFormEvent {
        SaveEvent(KaizenForm source, Kaizen kaizen) {
            super(source, kaizen);
        }
    }

    public static class DeleteEvent extends KaizenForm.KaizenFormEvent {
        DeleteEvent(KaizenForm source, Kaizen kaizen) {
            super(source, kaizen);
        }

    }

    public static class CloseEvent extends KaizenForm.KaizenFormEvent {
        CloseEvent(KaizenForm source) {
            super(source, null);
        }
    }


    public Registration addDeleteListener(ComponentEventListener<KaizenForm.DeleteEvent> listener) {
        return addListener(KaizenForm.DeleteEvent.class, listener);
    }

    public Registration addSaveListener(ComponentEventListener<KaizenForm.SaveEvent> listener) {
        return addListener(KaizenForm.SaveEvent.class, listener);
    }

    public Registration addCloseListener(ComponentEventListener<KaizenForm.CloseEvent> listener) {
        return addListener(KaizenForm.CloseEvent.class, listener);
    }

}
