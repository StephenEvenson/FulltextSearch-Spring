package me.stephenj.administration.model;

public class Helmet {
    private String name;
    private String helmet;

    public Helmet(){}

    public Helmet(String name, String helmet) {
        this.name = name;
        this.helmet = helmet;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHelmet() {
        return helmet;
    }

    public void setHelmet(String helmet) {
        this.helmet = helmet;
    }
}
