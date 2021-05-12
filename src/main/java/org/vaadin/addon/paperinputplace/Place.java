package org.vaadin.addon.paperinputplace;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Place implements Serializable {

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the latLng
     */
    public GlobalLocation getLatLng() {
        return latLng;
    }

    /**
     * @param latLng the latLng to set
     */
    public void setLatLng(GlobalLocation latLng) {
        this.latLng = latLng;
    }

    /**
     * @return the viewport
     */
    public Viewport getViewport() {
        return viewport;
    }

    /**
     * @param viewport
     */
    public void setViewport(Viewport viewport) {
        this.viewport = viewport;
    }

    private String name;

    private GlobalLocation latLng;

    private Viewport viewport;

}
