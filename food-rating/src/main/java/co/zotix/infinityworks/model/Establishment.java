/**
 * Establishment.java 25/11/2015 22:30 akhettar $$
 * Copyright 2015 INPS.co.uk, L.P. All rights reserved. $$
 */
package co.zotix.infinityworks.model;

import java.util.Date;

/**
 * Establishment class
 */
public class Establishment {


    private String FHRSID;
    private boolean NewRatingPending;
    private String BusinessName;
    private String Distance;
    private String PostCode;
    private String RightToReply;
    private Scores scores;
    private String LocalAuthorityBusinessID;
    private String[] links;
    private String AddressLine4;
    private String RatingDate;
    private String AddressLine3;
    private String BusinessTypeID;
    private String RatingValue;
    private String AddressLine2;
    private String SchemeType;
    private String RatingKey;
    private String AddressLine1;
    private String Phone;
    private int LocalAuthorityCode;
    private String LocalAuthorityEmailAddress;
    private String BusinessType;
    private String LocalAuthorityWebSite;
    private String LocalAuthorityName;
    private Geocode geocode;
    public String getFHRSID() {
        return FHRSID;
    }


    public void setFHRSID(final String FHRSID) {
        this.FHRSID = FHRSID;
    }

    public boolean isNewRatingPending() {
        return NewRatingPending;
    }

    public Scores getScores() {
        return scores;
    }

    public void setScores(final Scores scores) {
        this.scores = scores;
    }

    public void setNewRatingPending(final boolean newRatingPending) {
        NewRatingPending = newRatingPending;
    }

    public String getBusinessName() {
        return BusinessName;
    }

    public void setBusinessName(final String businessName) {
        BusinessName = businessName;
    }

    public String getDistance() {
        return Distance;
    }

    public void setDistance(final String distance) {
        Distance = distance;
    }

    public String getPostCode() {
        return PostCode;
    }

    public void setPostCode(final String postCode) {
        PostCode = postCode;
    }

    public String getRightToReply() {
        return RightToReply;
    }

    public void setRightToReply(final String rightToReply) {
        RightToReply = rightToReply;
    }

    public String getLocalAuthorityBusinessID() {
        return LocalAuthorityBusinessID;
    }

    public void setLocalAuthorityBusinessID(final String localAuthorityBusinessID) {
        LocalAuthorityBusinessID = localAuthorityBusinessID;
    }

    public String[] getLinks() {
        return links;
    }

    public void setLinks(final String[] links) {
        this.links = links;
    }

    public String getAddressLine4() {
        return AddressLine4;
    }

    public void setAddressLine4(final String addressLine4) {
        AddressLine4 = addressLine4;
    }

    public String getRatingDate() {
        return RatingDate;
    }

    public void setRatingDate(final String ratingDate) {
        RatingDate = ratingDate;
    }

    public String getAddressLine3() {
        return AddressLine3;
    }

    public void setAddressLine3(final String addressLine3) {
        AddressLine3 = addressLine3;
    }

    public String getBusinessTypeID() {
        return BusinessTypeID;
    }

    public void setBusinessTypeID(final String businessTypeID) {
        BusinessTypeID = businessTypeID;
    }

    public String getRatingValue() {
        return RatingValue;
    }

    public void setRatingValue(final String ratingValue) {
        RatingValue = ratingValue;
    }

    public String getAddressLine2() {
        return AddressLine2;
    }

    public void setAddressLine2(final String addressLine2) {
        AddressLine2 = addressLine2;
    }

    public String getSchemeType() {
        return SchemeType;
    }

    public void setSchemeType(final String schemeType) {
        SchemeType = schemeType;
    }

    public String getRatingKey() {
        return RatingKey;
    }

    public void setRatingKey(final String ratingKey) {
        RatingKey = ratingKey;
    }

    public String getAddressLine1() {
        return AddressLine1;
    }

    public void setAddressLine1(final String addressLine1) {
        AddressLine1 = addressLine1;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(final String phone) {
        Phone = phone;
    }

    public int getLocalAuthorityCode() {
        return LocalAuthorityCode;
    }

    public void setLocalAuthorityCode(final int localAuthorityCode) {
        LocalAuthorityCode = localAuthorityCode;
    }

    public String getLocalAuthorityEmailAddress() {
        return LocalAuthorityEmailAddress;
    }

    public void setLocalAuthorityEmailAddress(final String localAuthorityEmailAddress) {
        LocalAuthorityEmailAddress = localAuthorityEmailAddress;
    }

    public String getBusinessType() {
        return BusinessType;
    }

    public void setBusinessType(final String businessType) {
        BusinessType = businessType;
    }

    public String getLocalAuthorityWebSite() {
        return LocalAuthorityWebSite;
    }

    public void setLocalAuthorityWebSite(final String localAuthorityWebSite) {
        LocalAuthorityWebSite = localAuthorityWebSite;
    }

    public String getLocalAuthorityName() {
        return LocalAuthorityName;
    }

    public void setLocalAuthorityName(final String localAuthorityName) {
        LocalAuthorityName = localAuthorityName;
    }

    public Geocode getGeocode() {
        return geocode;
    }

    public void setGeocode(final Geocode geocode) {
        this.geocode = geocode;
    }
}
