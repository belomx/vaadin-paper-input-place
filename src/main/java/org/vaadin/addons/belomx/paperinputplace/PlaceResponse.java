package org.vaadin.addons.belomx.paperinputplace;

import java.io.Serializable;

@SuppressWarnings("serial")
public class PlaceResponse implements Serializable {       
    
    private Place place;

    private Address address;

    /**
     * @return get the address
     */
    public Address getAddress() {
        return address;
    }

    /**
     * @param address
     */
    public void setAddress(Address address) {
        this.address = address;
    }

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
        return "{\"address\": "+getStringAddress()+" , \"place\": {\"name\":\""+this.getPlace().getName()+"\",\"latLng\":{\"lat\":"+this.getPlace().getLatLng().getLat()+" ,\"long\":"+this.getPlace().getLatLng().getLng()+"}, \"viewport\": { \"northeast\": {\"lat\":"+this.getPlace().getViewport().getNortheast().getLat()+" ,\"long\":"+this.getPlace().getViewport().getNortheast().getLng()+"}, \"southeast\":{\"lat\":"+this.getPlace().getViewport().getSouthwest().getLat()+" ,\"long\":"+this.getPlace().getViewport().getSouthwest().getLng()+"} }}}";
    }

    private String getStringAddress() {
        if (getAddress() == null) {
            return "";
        }
        return getAddress().toString();
    }

}
