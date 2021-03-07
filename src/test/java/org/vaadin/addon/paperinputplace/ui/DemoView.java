package org.vaadin.addon.paperinputplace.ui;

import org.vaadin.addon.paperinputplace.PaperInputPlace;

import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@SuppressWarnings("serial")
@Route("")
@JsModule("./styles/shared-styles.js")
public class DemoView extends VerticalLayout
{

    public DemoView()
    {
        FlexLayout div = new FlexLayout();
        div.getStyle().set("flex-direction", "column");
        div.add(new Label("Paper input place by google api"));
        
        PaperInputPlace inputPlace = new PaperInputPlace("AIzaSyCLypeI6z_2Bw9So7P_dLwe3xWemzargpI");
        inputPlace.setCountry("DE");
        final Label selectedValueLabel = new Label("place selected");
        div.add(inputPlace, selectedValueLabel);
        
        inputPlace.addChangePlacejsonCompleteEventListener(e -> {
            selectedValueLabel.setText(e.getSource().getPlace());
        });
        
        add(div);
    }
}
