package com.Kaizen_frontend.frontend.views.reward;

import com.Kaizen_frontend.frontend.domain.Reward;
import com.Kaizen_frontend.frontend.domain.User;
import com.Kaizen_frontend.frontend.service.RewardService;
import com.Kaizen_frontend.frontend.service.UserService;
import com.Kaizen_frontend.frontend.views.MainLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;


@Route(value = "rewards", layout = MainLayout.class)
public class RewardList extends VerticalLayout {

    private Grid<Reward> grid = new Grid<>(Reward.class);
    private IntegerField idField = new IntegerField();

    private RewardForm form;
    private RewardService rewardService;

    public RewardList (RewardService rewardService) {
        this.rewardService = rewardService;
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
        grid.setItems(rewardService.getRewards());
    }

    private Component getContent() {
        HorizontalLayout content = new HorizontalLayout(grid, form);
        content.setFlexGrow(2, grid);
        content.setFlexGrow(1, form);
        content.setSizeFull();

        return content ;
    }

    private void configureForm() {
        form = new RewardForm();
        form.setWidth("25em");

    }

    private Component getToolbar() {
        idField.setPlaceholder("Filter by id...");
        idField.setClearButtonVisible(true);
        idField.setValueChangeMode(ValueChangeMode.LAZY);
        idField.addValueChangeListener(e->{
            try {
                filterById(idField.getValue());
            } catch (NullPointerException ex) {
                updateList();
            }
        });
        Button addRewardButton = new Button("Add reward");

        HorizontalLayout toolbar = new HorizontalLayout(idField, addRewardButton);
        return toolbar;
    }

    private void configureGrid() {
        grid.setSizeFull();
        grid.setColumns("rewardId", "name", "price");
    }

    private void filterById(int rewardId) {
            grid.setItems(rewardService.getRewardsById(rewardId));
    }
}
