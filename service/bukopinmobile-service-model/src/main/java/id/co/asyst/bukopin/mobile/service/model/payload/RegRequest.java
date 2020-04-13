package id.co.asyst.bukopin.mobile.service.model.payload;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import id.co.asyst.bukopin.mobile.service.model.ServicesConstants;

// TODO move to user-model module. Document Me!
public class RegRequest implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 8029390657476798691L;

    private String username;

    private String email;

    @Size(message = "{password.max}", min = 6, max = 10)
    @Pattern(regexp = ServicesConstants.ALPHANUMERIC_REGEX, message = "{password.regex}")
    private String password;

    @Size(message = "{pin.max}", min = 6, max = 6)
    @Pattern(regexp = ServicesConstants.NUMERIC_REGEX, message = "{pin.number}")
    private String pin;
    
    @Size(max=6, message="Max OTP length is 6 chars.")
    @NotBlank(message="otp is required")
    @Pattern(regexp = ServicesConstants.NUMERIC_REGEX, message="OTP is numeric only.")
    private String otp;

    public String getUsername() {
	return username;
    }

    public void setUsername(String username) {
	this.username = username;
    }

    public String getEmail() {
	return email;
    }

    public void setEmail(String email) {
	this.email = email;
    }

    public String getPassword() {
	return password;
    }

    public void setPassword(String password) {
	this.password = password;
    }

    public String getPin() {
	return pin;
    }

    public void setPin(String pin) {
	this.pin = pin;
    }

    /**
     * Gets <code>otp</code>.
     * @return The <code>otp</code>.
     */
    public String getOtp() {
        return otp;
    }

    /**
     * Sets <code>otp</code>.
     * @param otp The <code>otp</code> to set.
     */
    public void setOtp(String otp) {
        this.otp = otp;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "RegRequest [username=" + username + ", email=" + email + ", password=" + password + ", pin=" + pin
		+ ", otp=" + otp + "]";
    }
    
}
