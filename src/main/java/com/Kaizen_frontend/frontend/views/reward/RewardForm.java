package com.Kaizen_frontend.frontend.views.reward;

import com.Kaizen_frontend.frontend.domain.Reward;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.shared.Registration;

public class RewardForm extends FormLayout {

    Binder<Reward> binder = new BeanValidationBinder<>(Reward.class);
    TextField name = new TextField("name");
    IntegerField price = new IntegerField("price");
    Button save = new Button("Save");
    Button delete = new Button("Delete");
    Button cancel = new Button("Cancel");

    public RewardForm() {
        binder.bindInstanceFields(this);

        add(
                name,
                price,
                createButtonLayout()
        );

    }

    public void setReward(Reward reward) {
        binder.setBean(reward);
    }

    private Component createButtonLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        cancel.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

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

    public static abstract class RewardFormEvent extends ComponentEvent<RewardForm> {
        private final Reward reward;

        protected RewardFormEvent(RewardForm source, Reward reward) {
            super(source, false);
            this.reward = reward;
        }

        public Reward getReward() {
            return reward;
        }
    }

    public static class SaveEvent extends RewardForm.RewardFormEvent {
        SaveEvent(RewardForm source, Reward reward) {
            super(source, reward);
        }
    }

    public static class DeleteEvent extends RewardForm.RewardFormEvent {
        DeleteEvent(RewardForm source, Reward reward) {
            super(source, reward);
        }

    }

    public static class CloseEvent extends RewardForm.RewardFormEvent {
        CloseEvent(RewardForm source) {
            super(source, null);
        }
    }

    public Registration addDeleteListener(ComponentEventListener<RewardForm.DeleteEvent> listener) {
        return addListener(RewardForm.DeleteEvent.class, listener);
    }

    public Registration addSaveListener(ComponentEventListener<RewardForm.SaveEvent> listener) {
        return addListener(RewardForm.SaveEvent.class, listener);
    }

    public Registration addCloseListener(ComponentEventListener<RewardForm.CloseEvent> listener) {
        return addListener(RewardForm.CloseEvent.class, listener);
    }
}
