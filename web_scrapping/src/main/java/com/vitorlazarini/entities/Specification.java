package com.vitorlazarini.entities;

public class Specification {

    private String label;
    private String value;

    /*
     * 
     * LABEL: Nome da propriedade.
     * VALUE: Texto da propriedade.
     * 
     */

    public Specification() {

    }

    public Specification(String label, String value) {
        this.label = label;
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
