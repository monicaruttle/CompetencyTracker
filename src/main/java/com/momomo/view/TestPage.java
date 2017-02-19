package com.momomo.view;

import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;

/**
 * Created by Charberg on 2/19/2017.
 */
@SpringUI
public class TestPage extends UI {
    @Override
    protected void init(VaadinRequest vaadinRequest) {
        setContent(new Label("CANDAN THE KANBAN MAN"));
    }
}