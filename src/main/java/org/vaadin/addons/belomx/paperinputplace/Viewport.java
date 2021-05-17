package org.vaadin.addons.belomx.paperinputplace;

public class Viewport {
    
    private GlobalLocation southwest;
    private GlobalLocation northeast;

    public GlobalLocation getSouthwest() {
        return southwest;
    }

    public void setSouthwest(GlobalLocation southwest) {
        this.southwest = southwest;
    }

    public GlobalLocation getNortheast() {
        return northeast;
    }

    public void setNortheast(GlobalLocation northeast) {
        this.northeast = northeast;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((northeast == null) ? 0 : northeast.hashCode());
        result = prime * result + ((southwest == null) ? 0 : southwest.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Viewport other = (Viewport) obj;
        if (northeast == null) {
            if (other.northeast != null)
                return false;
        } else if (!northeast.equals(other.northeast))
            return false;
        if (southwest == null) {
            if (other.southwest != null)
                return false;
        } else if (!southwest.equals(other.southwest))
            return false;
        return true;
    }

}
