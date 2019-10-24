package me.stephenj.administration.model;

import java.util.List;

public class Image {
   private String image;
   private List<Helmet> helmets;
   private Boolean existFace;

   public Image() {}

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<Helmet> getHelmets() {
        return helmets;
    }

    public void setHelmets(List<Helmet> helmets) {
        this.helmets = helmets;
    }

    public Boolean getExistFace() {
        return existFace;
    }

    public void setExistFace(Boolean existFace) {
        this.existFace = existFace;
    }
}
