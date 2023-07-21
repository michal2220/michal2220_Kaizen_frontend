package com.Kaizen_frontend.frontend;


import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Main")
@Route(value = "main")
public class MainView extends VerticalLayout {

    public MainView() {
        add(new Button("Click me", e -> Notification.show("Hello World")));
    }

}
