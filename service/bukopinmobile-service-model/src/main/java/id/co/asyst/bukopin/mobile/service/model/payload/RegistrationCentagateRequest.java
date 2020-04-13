package id.co.asyst.bukopin.mobile.service.model.payload;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

public class RegistrationCentagateRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5023703964412783591L;

	@NotBlank
	private String firstName;
	@NotBlank
	private String lastName;
	@NotBlank
	private String username;
	@NotBlank
	private String userApp;
	@NotBlank
	private String useruniqueId;
	@NotBlank
	private String userClientId;

	private String userAdditionalData1;
	private String userAdditionalData2;
	private String userAdditionalData3;
	private String userAdditionalData4;
	private String userAdditionalData5;
	
	private String userEmail;
	
	private String cenToken;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserApp() {
		return userApp;
	}

	public void setUserApp(String userApp) {
		this.userApp = userApp;
	}

	public String getUseruniqueId() {
		return useruniqueId;
	}

	public void setUseruniqueId(String useruniqueId) {
		this.useruniqueId = useruniqueId;
	}

	public String getUserClientId() {
		return userClientId;
	}

	public void setUserClientId(String userClientId) {
		this.userClientId = userClientId;
	}

	public String getUserAdditionalData1() {
		return userAdditionalData1;
	}

	public void setUserAdditionalData1(String userAdditionalData1) {
		this.userAdditionalData1 = userAdditionalData1;
	}

	public String getUserAdditionalData2() {
		return userAdditionalData2;
	}

	public void setUserAdditionalData2(String userAdditionalData2) {
		this.userAdditionalData2 = userAdditionalData2;
	}

	public String getUserAdditionalData3() {
		return userAdditionalData3;
	}

	public void setUserAdditionalData3(String userAdditionalData3) {
		this.userAdditionalData3 = userAdditionalData3;
	}

	public String getUserAdditionalData4() {
		return userAdditionalData4;
	}

	public void setUserAdditionalData4(String userAdditionalData4) {
		this.userAdditionalData4 = userAdditionalData4;
	}

	public String getUserAdditionalData5() {
		return userAdditionalData5;
	}

	public void setUserAdditionalData5(String userAdditionalData5) {
		this.userAdditionalData5 = userAdditionalData5;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getCenToken() {
		return cenToken;
	}

	public void setCenToken(String cenToken) {
		this.cenToken = cenToken;
	}

}
