package com.Kaizen_frontend.frontend.views;

import com.Kaizen_frontend.frontend.domain.Reward;
import com.Kaizen_frontend.frontend.views.kaizen.KaizenList;
import com.Kaizen_frontend.frontend.views.reward.RewardList;
import com.Kaizen_frontend.frontend.views.user.UserList;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.HighlightCondition;
import com.vaadin.flow.router.HighlightConditions;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.theme.lumo.Lumo;
import com.vaadin.flow.theme.lumo.LumoUtility;

public class MainLayout extends AppLayout {

    public MainLayout() {
        createHeader();
        createDrawer();
    }

    private void createHeader() {
        H1 logo = new H1("Kaizen app");
        logo.addClassNames(
                LumoUtility.FontSize.LARGE,
                LumoUtility.Margin.MEDIUM
        );

        var header = new HorizontalLayout(new DrawerToggle(), logo);

        header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        header.expand(logo);
        header.setWidthFull();
        header.addClassNames(
                LumoUtility.Padding.Vertical.NONE,
                LumoUtility.Padding.Horizontal.MEDIUM);

        addToNavbar(header);
    }

    private void createDrawer() {
        RouterLink userLink = new RouterLink("User", UserList.class);
        userLink.setHighlightCondition(HighlightConditions.sameLocation());

        RouterLink kaizenLink = new RouterLink("Kaizen", KaizenList.class);
        kaizenLink.setHighlightCondition(HighlightConditions.sameLocation());

        RouterLink rewardLink = new RouterLink("Reward", RewardList.class);
        rewardLink.setHighlightCondition(HighlightConditions.sameLocation());

        addToDrawer(new VerticalLayout(
                userLink,
                kaizenLink,
                rewardLink
        ));

    }
}
