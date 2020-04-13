/*
 * $Id$
 * 
 * Copyright (c) 2020 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.telco.model.payload;

import java.util.Arrays;

/**
 * 
 * 
 * @author Gibran Haq
 * @version $Revision$, Feb 24, 2020
 * @since 1.1.Alpha1-SNAPSHOT
 */
public class TelcoPostpaidPrefixResponse {
    /* Constants: */

    /* Attributes: */
    private String provider;
    
    private String group;
    
    private byte[] picture;
    
    private Long id;
    
    private String codeArra;
    
    private String codeCbs;

    /* Transient Attributes: */

    /* Constructors: */

    /* Getters & setters for attributes: */


    /**
     * Gets <code>provider</code>.
     * @return The <code>provider</code>.
     */
    public String getProvider() {
        return provider;
    }

    /**
     * Sets <code>provider</code>.
     * @param provider The <code>provider</code> to set.
     */
    public void setProvider(String provider) {
        this.provider = provider;
    }

    /**
     * Gets <code>group</code>.
     * @return The <code>group</code>.
     */
    public String getGroup() {
        return group;
    }

    /**
     * Sets <code>group</code>.
     * @param group The <code>group</code> to set.
     */
    public void setGroup(String group) {
        this.group = group;
    }

    /**
     * Gets <code>picture</code>.
     * @return The <code>picture</code>.
     */
    public byte[] getPicture() {
        return picture;
    }

    /**
     * Sets <code>picture</code>.
     * @param picture The <code>picture</code> to set.
     */
    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    /**
     * Gets <code>id</code>.
     * @return The <code>id</code>.
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets <code>id</code>.
     * @param id The <code>id</code> to set.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets <code>codeArra</code>.
     * @return The <code>codeArra</code>.
     */
    public String getCodeArra() {
        return codeArra;
    }

    /**
     * Sets <code>codeArra</code>.
     * @param codeArra The <code>codeArra</code> to set.
     */
    public void setCodeArra(String codeArra) {
        this.codeArra = codeArra;
    }

    /**
     * Gets <code>codeCbs</code>.
     * @return The <code>codeCbs</code>.
     */
    public String getCodeCbs() {
        return codeCbs;
    }

    /**
     * Sets <code>codeCbs</code>.
     * @param codeCbs The <code>codeCbs</code> to set.
     */
    public void setCodeCbs(String codeCbs) {
        this.codeCbs = codeCbs;
    }

    /* Getters & setters for transient attributes: */

    /* Functionalities: */

    /* Overrides: */


    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "TelcoPostpaidPrefixResponse [provider=" + provider + ", group=" + group + ", picture="
		+ Arrays.toString(picture) + ", id=" + id + ", codeArra=" + codeArra + ", codeCbs=" + codeCbs + "]";
    }

}
