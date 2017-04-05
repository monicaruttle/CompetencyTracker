package com.momomo.control;


import com.vaadin.event.FieldEvents;
import com.vaadin.event.ShortcutAction;
import com.vaadin.ui.Button;

/**
 * Created by Charberg on 4/4/2017.
 */
public class EnterListener implements FieldEvents.FocusListener {

    Button submit;

    public EnterListener (Button submit){
        super();
        this.submit = submit;
    }

    public void focus(FieldEvents.FocusEvent f) {
        submit.setClickShortcut(ShortcutAction.KeyCode.ENTER);
    }

}
