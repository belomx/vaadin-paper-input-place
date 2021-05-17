package org.vaadin.addons.belomx.paperinputplace.ui;

import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import org.vaadin.addons.belomx.paperinputplace.PaperInputPlace;

@SuppressWarnings("serial")
@Route("")
@JsModule("./styles/shared-styles.js")
public class DemoView extends VerticalLayout
{

    public DemoView()
    {
        FlexLayout div = new FlexLayout();
        div.getStyle().set("flex-direction", "column");
        div.setWidth("40em");
        div.add(new Label("Paper input place by google api"));
        
        PaperInputPlace inputPlace = new PaperInputPlace("API-KEY");
        inputPlace.setWidth("100%");
        inputPlace.setCountry("DE");
        inputPlace.setLanguage("de");
        inputPlace.setErrorMessage(null);
        final Label selectedValueLabel = new Label("place selected");
        div.add(inputPlace, selectedValueLabel);
        
        inputPlace.addChangePlacejsonCompleteEventListener(e -> {
            e.getSource().getPlaceResponse().ifPresent(response -> {
                selectedValueLabel.setText(response.toString());
            });
        });
        
        add(div);
    }
}
