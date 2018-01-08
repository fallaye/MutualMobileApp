package mobileappscompany.mutualmobileapp;

/**
 * Created by fallaye on 1/7/18.
 */

public class Receipe {

    String uri, label, image;

    public Receipe() {
    }

    public Receipe(String uri, String label, String image) {
        this.uri = uri;
        this.label = label;
        this.image = image;
    }

    public String getUri() {
        return uri;
    }

    public String getLabel() {
        return label;
    }

    public String getImage() {
        return image;
    }
}
