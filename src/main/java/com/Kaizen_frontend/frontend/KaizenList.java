package com.Kaizen_frontend.frontend;

import com.Kaizen_frontend.frontend.domain.Kaizen;
import com.Kaizen_frontend.frontend.domain.User;
import com.Kaizen_frontend.frontend.service.KaizenService;
import com.Kaizen_frontend.frontend.service.UserService;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("kaizen")
public class KaizenList extends VerticalLayout {

    public KaizenList (KaizenService service) {

        var grid = new Grid<Kaizen>(Kaizen.class);
        grid.setItems(service.getKaizens());

        grid.setColumns("kaizenId", "fillingDate", "completed", "problem","solution");

        add(grid);
    }
}
