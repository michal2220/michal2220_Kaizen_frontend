package com.Kaizen_frontend.frontend.views.reward;

import com.Kaizen_frontend.frontend.domain.Reward;
import com.Kaizen_frontend.frontend.domain.User;
import com.Kaizen_frontend.frontend.service.RewardService;
import com.Kaizen_frontend.frontend.service.UserService;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("rewards")
public class RewardList extends VerticalLayout {

    public RewardList (RewardService service) {

        var grid = new Grid<Reward>(Reward.class);
        grid.setItems(service.getRewards());

        grid.setColumns("rewardId", "name", "price");

        add(grid);
    }
}
