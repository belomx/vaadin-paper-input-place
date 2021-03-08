package org.vaadin.addon.paperinputplace;

import java.io.Serializable;

@SuppressWarnings("serial")
public class PlaceResponse implements Serializable {       
    
    private Place place;     

    /**
     * @return the place
     */
    public Place getPlace() {
        return place;
    }

    /**
     * @param place the place to set
     */
    public void setPlace(Place place) {
        this.place = place;
    }        
    
    @Override
    public String toString() {
        return "{ \"place\": {\"name\":\""+this.getPlace().getName()+"\",\"latLng\":{\"lat\":"+this.getPlace().getLatLng().getLat()+" ,\"long\":"+this.getPlace().getLatLng().getLng()+"}}}";
    }

}
