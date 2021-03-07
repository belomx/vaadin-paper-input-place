package org.vaadin.addon.paperinputplace;

import com.vaadin.flow.component.AbstractSinglePropertyField;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.DomEvent;
import com.vaadin.flow.component.EventData;
import com.vaadin.flow.component.PropertyDescriptor;
import com.vaadin.flow.component.PropertyDescriptors;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.dependency.NpmPackage;
import com.vaadin.flow.shared.Registration;

@Tag("paper-input-place")
@NpmPackage(value = "@belomx/paper-input-place", version = "^2.0.6")
@JsModule("@belomx/paper-input-place/paper-input-place.js")

public class PaperInputPlace extends AbstractSinglePropertyField<PaperInputPlace, String> {    
    private static final long serialVersionUID = 1L;
    private static final PropertyDescriptor<String,String> placeJSON = PropertyDescriptors.propertyWithDefault("placeJSON", "not entered");
    private static boolean geoReady = false;
    private static String value = "not set";
    public PaperInputPlace(String k) {
        super("value", "not set", false);
        setKey(k);
        minimizeAPI(true);
    }
    public void setKey(String k)
    {
        getElement().setProperty("apiKey",k);
    }
    public void minimizeAPI(Boolean k)
    {
        getElement().setProperty("minimizeAPI", k);
    }
    public void setCountry(String countryCode) {
        getElement().setProperty("searchCountryCode",countryCode);
    }
    public void fillValue(String placeString)
    {

        // this does not need to be encoded...
        String comm = "this.fillValue($0)";
        if(geoReady) {
            getElement().executeJs(comm, placeString);
        }        
    }
    public String getPlace()
    {
        //this should return a string...but does not
        return placeJSON.get(this);
    }
    public void setPlace(String place)
    {
        //this should return a string...but does not
        placeJSON.set(this, place);
    }
    //
    @DomEvent("api-loaded")
    public static class GeoReadyEvent extends ComponentEvent<PaperInputPlace>
    {
        private String value;
        //was event.detail.text
        public GeoReadyEvent(PaperInputPlace source,boolean fromClient,@EventData("event.detail.text") String value)
        {
            super(source,fromClient);
            this.value=value;
            geoReady=true;
        }
    }
    public Registration addGeoReadyEventListener(ComponentEventListener<GeoReadyEvent> listener)
    {
        return addListener(GeoReadyEvent.class, listener);
    }
    
    @DomEvent("change-placejson-complete")
    public static class ChangePlacejsonCompleteEvent extends ComponentEvent<PaperInputPlace>
    {        
        //was event.detail.text
        public ChangePlacejsonCompleteEvent(PaperInputPlace source,boolean fromClient,@EventData("event.detail.placeJSON") String placeJSON)
        {
            super(source,fromClient);
            source.setPlace(placeJSON);
        }
    }
    public Registration addChangePlacejsonCompleteEventListener(ComponentEventListener<ChangePlacejsonCompleteEvent> listener)
    {
        return addListener(ChangePlacejsonCompleteEvent.class, listener);
    }
    
    //
    @DomEvent("input-change")
    public static class ValueChangeEvent extends ComponentEvent<PaperInputPlace>
    {
        private String value;
        //was event.detail.text
        public ValueChangeEvent(PaperInputPlace source,boolean fromClient,@EventData("event.detail.text") String value)
        {
            super(source,fromClient);
            this.value=value;
        }
    }
    public Registration addValueChangeEventListener(ComponentEventListener<ValueChangeEvent> listener)
    {
        return addListener(ValueChangeEvent.class, listener);
    }
    //
    @DomEvent("change-complete")
    public static class ValueCompleteEvent extends ComponentEvent<PaperInputPlace>
    {
        private String value;
        //was event.detail.text
        public ValueCompleteEvent(PaperInputPlace source,boolean fromClient,@EventData("event.detail.text") String value)
        {
            super(source,fromClient);
            this.value=value;
        }
    }
    public Registration addValueCompleteEventListener(ComponentEventListener<ValueCompleteEvent> listener)
    {
        return addListener(ValueCompleteEvent.class, listener);
    }
    @DomEvent("click")
    public static class ClickEvent extends ComponentEvent<PaperInputPlace>
    {
        private String value;
        //was event.detail.text
        public ClickEvent(PaperInputPlace source,boolean fromClient,@EventData("event.detail.text") String value)
        {
            super(source,fromClient);
            this.value=value;
        }
    }
    public Registration addClickEventListener(ComponentEventListener<ClickEvent> listener)
    {
        return addListener(ClickEvent.class, listener);
    }
}
