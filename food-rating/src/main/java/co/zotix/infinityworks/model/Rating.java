/**
 * Rating.java 26/11/2015 06:52 akhettar $$
 * Copyright 2015 INPS.co.uk, L.P. All rights reserved. $$
 */
package co.zotix.infinityworks.model;

/**
 * Rating class
 */
public class Rating {

    private static final String PERCENT = "%";
    private String rating;
    private double percentage;


    public Rating(final String rating, final double percentage) {
        this.rating = rating;
        this.percentage = percentage;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }

    @Override
    public String toString() {
        return percentage + PERCENT;

    }
}
