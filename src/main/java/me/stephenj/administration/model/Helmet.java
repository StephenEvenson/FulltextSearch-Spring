package me.stephenj.administration.model;

public class Helmet {
    private String name;
    private Boolean helmet;

    public Helmet(){}

    public Helmet(String name, Boolean helmet) {
        this.name = name;
        this.helmet = helmet;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getHelmet() {
        return helmet;
    }

    public void setHelmet(Boolean helmet) {
        this.helmet = helmet;
    }
}
