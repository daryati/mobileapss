package id.co.asyst.bukopin.mobile.user.model.centagate;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Field 'object' of Centagate QA Challange Result
 * 
 * @author Ihsan Firman
 * @version $Revision$, Oct 29, 2019
 * @since 1.0.Alpha1-SNAPSHOT
 */

@JsonIgnoreProperties(ignoreUnknown=true)
public class ReqQAChallengeResultData implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1842244747445712168L;
	
	
	private String id;
	private String question;
	
	
	
	/**
	 * Gets <code>id</code>.
	 * @return The <code>id</code>.
	 */
	public String getId() {
		return id;
	}



	/**
	 * Sets <code>id</code>.
	 * @param id The <code>id</code> to set.
	 */
	public void setId(String id) {
		this.id = id;
	}



	/**
	 * Gets <code>question</code>.
	 * @return The <code>question</code>.
	 */
	public String getQuestion() {
		return question;
	}



	/**
	 * Sets <code>question</code>.
	 * @param question The <code>question</code> to set.
	 */
	public void setQuestion(String question) {
		this.question = question;
	}



	@Override
	public String toString() {
		return "ReqQAChallengeRequest  [id = " + id + ", question = " + question + "]";
	}
	
}
