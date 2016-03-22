/**
 * Authority.java 22/11/2015 22:55 akhettar $$
 * Copyright 2015 INPS.co.uk, L.P. All rights reserved. $$
 */

package co.zotix.infinityworks.model;

/**
 * Authority class
 */

public class Authority {

    private String LocalAuthorityId;
    private String Name;
    private String RegionName;

    public String getLocalAuthorityId() {
        return LocalAuthorityId;
    }

    public void setLocalAuthorityId(String localAuthorityId) {
        LocalAuthorityId = localAuthorityId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getRegionName() {
        return RegionName;
    }

    public void setRegionName(String regionName) {
        RegionName = regionName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final Authority authority = (Authority) o;

        if (!LocalAuthorityId.equals(authority.LocalAuthorityId)) return false;
        if (!Name.equals(authority.Name)) return false;
        return RegionName.equals(authority.RegionName);

    }

    @Override
    public int hashCode() {
        int result = LocalAuthorityId.hashCode();
        result = 31 * result + Name.hashCode();
        result = 31 * result + RegionName.hashCode();
        return result;
    }
}

