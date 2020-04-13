/*
 * $Id$
 * 
 * Copyright (c) 2019 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.service.model.payload;

/**
 * Update User Profile Request Model
 * 
 * @author Eka Ariyansyah
 * @version $Revision$, Nov 8, 2019
 * @since 1.0.Alpha1-SNAPSHOT
 */
public class UpdateUserProfileRequest {
    /* Constants: */

    /* Attributes: */
    private String username;
    private String firstName;
    private String lastName;
    private String address;
    private String zip;
    private String city;
    private String state;
    private String statusUser;
    private String email;
    private String userCountryId;
    private String userTimeZoneId;
    private String availApps;
    private String clientId;
    private String userType;
    private String userGroupName;
    private String cenToken;

    /* Constructors: */

    /* Getters & setters for attributes: */
    /**
     * Gets <code>username</code>.
     * @return The <code>username</code>.
     */
    public String getUsername() {
        return username;
    }
    /**
     * Sets <code>username</code>.
     * @param username The <code>username</code> to set.
     */
    public void setUsername(String username) {
        this.username = username;
    }
    /**
     * Gets <code>firstName</code>.
     * @return The <code>firstName</code>.
     */
    public String getFirstName() {
        return firstName;
    }
    /**
     * Sets <code>firstName</code>.
     * @param firstName The <code>firstName</code> to set.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    /**
     * Gets <code>lastName</code>.
     * @return The <code>lastName</code>.
     */
    public String getLastName() {
        return lastName;
    }
    /**
     * Sets <code>lastName</code>.
     * @param lastName The <code>lastName</code> to set.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    /**
     * Gets <code>address</code>.
     * @return The <code>address</code>.
     */
    public String getAddress() {
        return address;
    }
    /**
     * Sets <code>address</code>.
     * @param address The <code>address</code> to set.
     */
    public void setAddress(String address) {
        this.address = address;
    }
    /**
     * Gets <code>zip</code>.
     * @return The <code>zip</code>.
     */
    public String getZip() {
        return zip;
    }
    /**
     * Sets <code>zip</code>.
     * @param zip The <code>zip</code> to set.
     */
    public void setZip(String zip) {
        this.zip = zip;
    }
    /**
     * Gets <code>city</code>.
     * @return The <code>city</code>.
     */
    public String getCity() {
        return city;
    }
    /**
     * Sets <code>city</code>.
     * @param city The <code>city</code> to set.
     */
    public void setCity(String city) {
        this.city = city;
    }
    /**
     * Gets <code>state</code>.
     * @return The <code>state</code>.
     */
    public String getState() {
        return state;
    }
    /**
     * Sets <code>state</code>.
     * @param state The <code>state</code> to set.
     */
    public void setState(String state) {
        this.state = state;
    }
    /**
     * Gets <code>statusUser</code>.
     * @return The <code>statusUser</code>.
     */
    public String getStatusUser() {
        return statusUser;
    }
    /**
     * Sets <code>statusUser</code>.
     * @param statusUser The <code>statusUser</code> to set.
     */
    public void setStatusUser(String statusUser) {
        this.statusUser = statusUser;
    }
    /**
     * Gets <code>email</code>.
     * @return The <code>email</code>.
     */
    public String getEmail() {
        return email;
    }
    /**
     * Sets <code>email</code>.
     * @param email The <code>email</code> to set.
     */
    public void setEmail(String email) {
        this.email = email;
    }
    /**
     * Gets <code>userCountryId</code>.
     * @return The <code>userCountryId</code>.
     */
    public String getUserCountryId() {
        return userCountryId;
    }
    /**
     * Sets <code>userCountryId</code>.
     * @param userCountryId The <code>userCountryId</code> to set.
     */
    public void setUserCountryId(String userCountryId) {
        this.userCountryId = userCountryId;
    }
    /**
     * Gets <code>userTimeZoneId</code>.
     * @return The <code>userTimeZoneId</code>.
     */
    public String getUserTimeZoneId() {
        return userTimeZoneId;
    }
    /**
     * Sets <code>userTimeZoneId</code>.
     * @param userTimeZoneId The <code>userTimeZoneId</code> to set.
     */
    public void setUserTimeZoneId(String userTimeZoneId) {
        this.userTimeZoneId = userTimeZoneId;
    }
    /**
     * Gets <code>availApps</code>.
     * @return The <code>availApps</code>.
     */
    public String getAvailApps() {
        return availApps;
    }
    /**
     * Sets <code>availApps</code>.
     * @param availApps The <code>availApps</code> to set.
     */
    public void setAvailApps(String availApps) {
        this.availApps = availApps;
    }
    /**
     * Gets <code>clientId</code>.
     * @return The <code>clientId</code>.
     */
    public String getClientId() {
        return clientId;
    }
    /**
     * Sets <code>clientId</code>.
     * @param clientId The <code>clientId</code> to set.
     */
    public void setClientId(String clientId) {
        this.clientId = clientId;
    }
    /**
     * Gets <code>userType</code>.
     * @return The <code>userType</code>.
     */
    public String getUserType() {
        return userType;
    }
    /**
     * Sets <code>userType</code>.
     * @param userType The <code>userType</code> to set.
     */
    public void setUserType(String userType) {
        this.userType = userType;
    }
    /**
     * Gets <code>userGroupName</code>.
     * @return The <code>userGroupName</code>.
     */
    public String getUserGroupName() {
        return userGroupName;
    }
    /**
     * Sets <code>userGroupName</code>.
     * @param userGroupName The <code>userGroupName</code> to set.
     */
    public void setUserGroupName(String userGroupName) {
        this.userGroupName = userGroupName;
    }
    /**
     * Gets <code>cenToken</code>.
     * @return The <code>cenToken</code>.
     */
    public String getCenToken() {
        return cenToken;
    }
    /**
     * Sets <code>cenToken</code>.
     * @param cenToken The <code>cenToken</code> to set.
     */
    public void setCenToken(String cenToken) {
        this.cenToken = cenToken;
    }

    /* Functionalities: */

    /* Overrides: */
}
