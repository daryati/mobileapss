package id.co.asyst.bukopin.mobile.service.model;

public class ServicesConstants {
	
	 /** Regular Expression */
    public static final String ALPHABETIC_COMMA_REGEX                   = "[A-Za-z, ]+";
    public static final String ALPHABETIC_HYPHEN_APOSTROPHE_REGEX       = "[0-9A-Za-z-' ]+";
    public static final String ALPHABETIC_REGEX                         = "[A-Za-z]+";
    public static final String ALPHABETIC_SPACE_REGEX                   = "[A-Za-z ]+";
    public static final String ALPHANUMERIC_REGEX                       = "[0-9A-Za-z]+";
    public static final String ALPHANUMERIC_UPPER_REGEX                 = "[0-9A-Z]+";
    public static final String EMAIL_REGEX                              = ".+@.+\\\\.[a-z]+";
    public static final String NPWP_REGEX                               = "([0-9]){2}[.]{0,1}([0-9]{3}[.]{0,1}){2}[0-9]{1}[-]{0,1}[0-9]{3}[.]{0,1}[0-9]{3}"; /*xx.xxx.xxx.x-xxx.xxxx*/
    public static final String NUMERIC_REGEX                            = "[0-9]+";
    public static final String POSTAL_CODE_REGEX                        = "^[0-9A-Za-z]{1}[0-9A-Za-z]{0,9}[- ]{0,1}[0-9A-Za-z]{0,9}[0-9A-Za-z]{1}$";
    public static final String UPPER_CASE_LETTERS_REGEX                 = "[A-Z]+";
    public static final String USERNAME_REGEX                           = "[0-9A-Za-z]+[._]{0,1}[0-9A-Za-z]+";
	
    public ServicesConstants() {
		// TODO Auto-generated constructor stub
	}
 
    
}
