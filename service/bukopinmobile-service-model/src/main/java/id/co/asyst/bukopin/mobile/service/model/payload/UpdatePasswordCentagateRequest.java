package id.co.asyst.bukopin.mobile.service.model.payload;

import java.io.Serializable;

public class UpdatePasswordCentagateRequest implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /* Constants: */
    private String username;
    private String password;
    private String newPassword;

   
    /* Attributes: */

    /* Transient Attributes: */

    /* Constructors: */

    /* Getters & setters for attributes: */
    
    /**
     * Gets <code>password</code>.
     * @return The <code>password</code>.
     */
    public String getPassword() {
        return password;
    }
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
     * Sets <code>password</code>.
     * @param password The <code>password</code> to set.
     */
    public void setPassword(String password) {
        this.password = password;
    }
    /**
     * Gets <code>newPassword</code>.
     * @return The <code>newPassword</code>.
     */
    public String getNewPassword() {
        return newPassword;
    }
    /**
     * Sets <code>newPassword</code>.
     * @param newPassword The <code>newPassword</code> to set.
     */
    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    /* Getters & setters for transient attributes: */

    /* Functionalities: */

    /* Overrides: */
    @Override
    public String toString() {
	return "updatePassword = [password =" + password + ", newPassword =" + newPassword + "]";
    }
}
