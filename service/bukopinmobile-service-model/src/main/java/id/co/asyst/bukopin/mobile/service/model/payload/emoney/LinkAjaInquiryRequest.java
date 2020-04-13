package id.co.asyst.bukopin.mobile.service.model.payload.emoney;

public class LinkAjaInquiryRequest {
    /* Constants: */

    /* Attributes: */
    private EMoneyIdentityRequest identity;
    private LinkAjaDataRequest parameter;

    /* Transient Attributes: */

    /* Constructors: */

    /* Getters & setters for attributes: */
    /**
     * Gets <code>identity</code>.
     * @return The <code>identity</code>.
     */
    public EMoneyIdentityRequest getIdentity() {
        return identity;
    }
    /**
     * Sets <code>identity</code>.
     * @param identity The <code>identity</code> to set.
     */
    public void setIdentity(EMoneyIdentityRequest identity) {
        this.identity = identity;
    }
    /**
     * Gets <code>parameter</code>.
     * @return The <code>parameter</code>.
     */
    public LinkAjaDataRequest getParameter() {
        return parameter;
    }
    /**
     * Sets <code>parameter</code>.
     * @param parameter The <code>parameter</code> to set.
     */
    public void setParameter(LinkAjaDataRequest parameter) {
        this.parameter = parameter;
    }

    /* Getters & setters for transient attributes: */

    /* Functionalities: */

    /* Overrides: */
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "LinkAjaInquiryRequest [identity=" + identity + ", parameter=" + parameter + "]";
    }

    
}
