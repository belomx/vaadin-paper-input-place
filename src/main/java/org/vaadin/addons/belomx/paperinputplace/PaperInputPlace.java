package org.vaadin.addons.belomx.paperinputplace;

import java.util.Optional;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.vaadin.flow.component.AbstractSinglePropertyField;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.DomEvent;
import com.vaadin.flow.component.EventData;
import com.vaadin.flow.component.HasSize;
import com.vaadin.flow.component.HasStyle;
import com.vaadin.flow.component.HasText;
import com.vaadin.flow.component.PropertyDescriptor;
import com.vaadin.flow.component.PropertyDescriptors;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.dependency.NpmPackage;
import com.vaadin.flow.shared.Registration;

@Tag("paper-input-place")
@NpmPackage(value = "@belomx/paper-input-place", version = "^2.0.11")
@JsModule("@belomx/paper-input-place/paper-input-place.js")

public class PaperInputPlace extends AbstractSinglePropertyField<PaperInputPlace, String> implements HasStyle, HasText, HasSize {    
    private static final String NOT_SET = "not set";
    private static final long serialVersionUID = 1L;
    private static final PropertyDescriptor<String,String> placeJSON = PropertyDescriptors.propertyWithDefault("placeJSON", NOT_SET);
    private static boolean geoReady = false;
    
    public PaperInputPlace(String k) {
        super("value", NOT_SET, false);
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
    
    /**
     * bias search results to a country code  (ISO 3166-1 Alpha-2 country code, case insensitive).
     */
    public void setCountry(String countryCode) {
        getElement().setProperty("searchCountryCode",countryCode);
    }
    
    public void setErrorMessage(String errorMessage) {
        getElement().setProperty("errorMessage", errorMessage);
    }
    
    public void setHideError(String hideError) {
        getElement().setProperty("hideError", hideError);
    }
    
    @Override
    public void setText(String text) {
        setLabel(text);
    }

    @Override
    public String getText() {
        return getElement().getProperty("label");
    }
    
    private void setLabel(String label) {
        getElement().setProperty("label", label);
    }
    
    public void setPlaceholder(String placeholder) {
        getElement().setProperty("placeholder",
                placeholder == null ? "" : placeholder);
    }
    
    public void setRequired(boolean required) {
        getElement().setProperty("required", required);
    }
    
    /** 
     * Sets the desired language for the input and the autocomplete list.
     * Normally, Google Places Autocomplete defaults to the browser default language.
     * This value allows the language to be set to a desired language regardless of the browser default.
     * 
     * For a list of language codes supported see https://developers.google.com/maps/faq#languagesupport
     * 
     * *** the value should not be modified after the element is loaded ***
     */
    public void setLanguage(String language) {
        getElement().setProperty("language", language);
    }
    
    public void fillValue(String placeString)
    {
        String comm = "this.fillValue($0)";
        if(geoReady) {
            getElement().executeJs(comm, placeString);
        }        
    }

    @Override
    public void setValue(String placeString)
    {
        super.setValue(placeString);
        String comm = "this._value = $0";
        getElement().executeJs(comm, placeString);        
    }
    
    public String getPlace()
    {
        return placeJSON.get(this);
    }
    
    public void setPlace(String place)
    {
        placeJSON.set(this, place);
    }
    
    public Optional<PlaceResponse> getPlaceResponse () {        
        if (isPlaceReady()) {
            String addressJson = getPlace();
            Gson gsonParser = new GsonBuilder().create();
            return Optional.of(gsonParser.fromJson(addressJson, PlaceResponse.class));
        }
        return Optional.empty();
    }
    
    private boolean isPlaceReady() {
        String addressJson = getPlace();
        return addressJson != null && !addressJson.isEmpty() && !NOT_SET.equals(addressJson);
    }
        
    @SuppressWarnings("serial")
    @DomEvent("api-loaded")
    public static class GeoReadyEvent extends ComponentEvent<PaperInputPlace>
    {
        private String value;

        public GeoReadyEvent(PaperInputPlace source,boolean fromClient,@EventData("event.detail.text") String value)
        {
            super(source,fromClient);
            this.value=value;
            geoReady=true;
        }
        
        public String getValue() {
            return value;
        }
    }
    public Registration addGeoReadyEventListener(ComponentEventListener<GeoReadyEvent> listener)
    {
        return addListener(GeoReadyEvent.class, listener);
    }
    
    @SuppressWarnings("serial")
    @DomEvent("change-placejson-complete")
    public static class ChangePlacejsonCompleteEvent extends ComponentEvent<PaperInputPlace>
    {
        private PlaceResponse reponse;

        public ChangePlacejsonCompleteEvent(PaperInputPlace source,boolean fromClient,@EventData("event.detail.placeJSON") String placeJSON)
        {
            super(source,fromClient);
            source.setPlace(placeJSON);
            reponse = source.getPlaceResponse().orElse(null);
        }

        public PlaceResponse getPlace() {
            return reponse;
        }
    }
    public Registration addChangePlacejsonCompleteEventListener(ComponentEventListener<ChangePlacejsonCompleteEvent> listener)
    {
        return addListener(ChangePlacejsonCompleteEvent.class, listener);
    }
        
    @SuppressWarnings("serial")
    @DomEvent("input-change")
    public static class ValueChangeEvent extends ComponentEvent<PaperInputPlace>
    {
        private String value;

        public ValueChangeEvent(PaperInputPlace source,boolean fromClient,@EventData("event.detail.text") String value)
        {
            super(source,fromClient);
            this.value=value;
        }
        
        public String getValue() {
            return value;
        }
    }
    public Registration addValueChangeEventListener(ComponentEventListener<ValueChangeEvent> listener)
    {
        return addListener(ValueChangeEvent.class, listener);
    }

    @SuppressWarnings("serial")
    @DomEvent("change-complete")
    public static class ValueCompleteEvent extends ComponentEvent<PaperInputPlace>
    {
        private String value;

        public ValueCompleteEvent(PaperInputPlace source,boolean fromClient,@EventData("event.detail.text") String value)
        {
            super(source,fromClient);
            this.value=value;
        }
        
        public String getValue() {
            return value;
        }
    }
    public Registration addValueCompleteEventListener(ComponentEventListener<ValueCompleteEvent> listener)
    {
        return addListener(ValueCompleteEvent.class, listener);
    }

    @SuppressWarnings("serial")
    @DomEvent("click")
    public static class ClickEvent extends ComponentEvent<PaperInputPlace>
    {
        private String value;

        public ClickEvent(PaperInputPlace source,boolean fromClient,@EventData("event.detail.text") String value)
        {
            super(source,fromClient);
            this.value=value;
        }
        
        public String getValue() {
            return value;
        }
    }
    public Registration addClickEventListener(ComponentEventListener<ClickEvent> listener)
    {
        return addListener(ClickEvent.class, listener);
    }

}
