package com.vitorlazarini.entities;

public class Reviews {

    private String name;
    private String date;
    private Integer score;
    private String text;

    /*
     * NAME: Nome da pessoa.
     * DATE: Data da avaliação
     * SCORE: Número de estrelas dadas.
     * TEXT: Texto da avaliação.
     */
    public Reviews() {

    }

    public Reviews(String name, String date, Integer score, String text) {
        this.name = name;
        this.date = date;
        this.score = score;
        this.text = text;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "Reviews [name=" + name + ", date=" + date + ", score=" + score + ", text=" + text + "]";
    }

}
