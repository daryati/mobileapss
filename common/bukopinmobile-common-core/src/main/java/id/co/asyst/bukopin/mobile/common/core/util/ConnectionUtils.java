/*
 * $Id$
 * 
 * Copyright (c) 2016 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.common.core.util;

import id.co.asyst.bukopin.mobile.common.model.BkpmConstants;
//import id.co.asyst.foundation.common.core.util.CoreUtil;
//import id.co.asyst.foundation.common.core.util.JsonUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Connection Util
 * 
 * @author Kartik Dwi H
 * @version $Revision: 29 $, 23 Nov 2016
 * @since 1.0.Alpha1
 */
public class ConnectionUtils {
	/* Constants */
	/**
	 * logger attributes
	 */
	protected static final Logger log = 
			LogManager.getLogger(ConnectionUtils.class);
	/**
	 * maxTimeout
	 */
	private static final int MAX_TIMEOUT = 120000; 
	
    /**
     * prevent instantiation
     */
    private ConnectionUtils() {
        // do nothing.
    }

	/**
	 * 
	 * @param url
	 * @return
	 * @throws IOException
	 */
	public static StringBuilder urlAccess(String url) throws IOException {
		String defaultContentType = BkpmConstants.HTTP_HEADER_CONTENT_JSON;
		Map<String, String> header = new HashMap<String, String>();
		header.put(BkpmConstants.HTTP_HEADER_CONTENTTYPE, defaultContentType);
		return ConnectionUtils.urlAccess(url,header);
	}
	/**
	 * 
	 * @param url
	 * @param header
	 * @return
	 * @throws IOException
	 */
	public static StringBuilder urlAccess(String url, Map<String, String> header) throws IOException {
		
		HttpsURLConnection conHttps = null;
		HttpURLConnection con = null;
		BufferedReader buffer = null;
		StringBuilder strBody = new StringBuilder();
		String responseBody = null;
		try {
			URL preparedUrl = urlSetting(url);
			if(url.contains("https://")){
			    TrustManager[] trustAllCerts = new TrustManager[]{
					new X509TrustManager() {
					    public void checkClientTrusted(
						        java.security.cert.X509Certificate[] certs, String authType) {
						    }
					    public void checkServerTrusted(
						        java.security.cert.X509Certificate[] certs, String authType) {
						    }
					    public java.security.cert.X509Certificate[] getAcceptedIssuers() {
						return new java.security.cert.X509Certificate[] {};
					    }
					}
				};

			   try {
			       	// Install the all-trusting trust manager
				    SSLContext sc = SSLContext.getInstance("SSL");
				    sc.init(null, trustAllCerts, new java.security.SecureRandom());
				    HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
			    } catch (Exception e) {
			    	
			    }
			   	
			   	// Don't verify host names
			        HostnameVerifier hv = new HostnameVerifier() {
			            public boolean verify(String urlHostName, SSLSession session) {
			                return true;
			            }
			        };
			        HttpsURLConnection.setDefaultHostnameVerifier(hv);
			   
				conHttps = (HttpsURLConnection) preparedUrl.openConnection();
				conHttps.setDoOutput(true);
				conHttps.setRequestMethod(BkpmConstants.REQUEST_METHOD_GET);
				conHttps.setUseCaches(false);
				conHttps.setConnectTimeout(MAX_TIMEOUT);
				conHttps.setReadTimeout(MAX_TIMEOUT);

				// Prepare Required HTTP Headers:
				for(String key : header.keySet()){
					conHttps.setRequestProperty(key, header.get(key));
				}
				
				if(!header.containsKey(BkpmConstants.HTTP_HEADER_ACCEPT)){
					conHttps.setRequestProperty(BkpmConstants.HTTP_HEADER_ACCEPT,
							BkpmConstants.HTTP_HEADER_CONTENT_JSON);
					
				}
				if(!header.containsKey(BkpmConstants.HTTP_HEADER_CONTENTTYPE)){
					conHttps.setRequestProperty(BkpmConstants.HTTP_HEADER_CONTENTTYPE,
							BkpmConstants.HTTP_HEADER_CONTENT_JSON);
				}
				
				printRequest(url,header,"");
				
				if ("200".equals(String.valueOf(conHttps.getResponseCode()))) {
					buffer = new BufferedReader(new InputStreamReader(
							conHttps.getInputStream()));
				} else {
					buffer = new BufferedReader(new InputStreamReader(
							conHttps.getErrorStream()));
				}
			}else{
				con = (HttpURLConnection) preparedUrl.openConnection();
				con.setDoOutput(true);
				con.setRequestMethod(BkpmConstants.REQUEST_METHOD_GET);
				con.setUseCaches(false);
				con.setConnectTimeout(MAX_TIMEOUT);
				con.setReadTimeout(MAX_TIMEOUT);

				// Prepare Required HTTP Headers:
				for(String key : header.keySet()){
					con.setRequestProperty(key, header.get(key));
				}
				
				if(!header.containsKey(BkpmConstants.HTTP_HEADER_ACCEPT)){
					con.setRequestProperty(BkpmConstants.HTTP_HEADER_ACCEPT,
							BkpmConstants.HTTP_HEADER_CONTENT_JSON);
					
				}
				if(!header.containsKey(BkpmConstants.HTTP_HEADER_CONTENTTYPE)){
					con.setRequestProperty(BkpmConstants.HTTP_HEADER_CONTENTTYPE,
							BkpmConstants.HTTP_HEADER_CONTENT_JSON);
				}
				
				printRequest(url,header,"");
				
				if ("200".equals(String.valueOf(con.getResponseCode()))) {
					buffer = new BufferedReader(new InputStreamReader(
							con.getInputStream()));
				} else {
					buffer = new BufferedReader(new InputStreamReader(
							con.getErrorStream()));
				}				
			}
			
			if (buffer != null) {
				String inputLine;
				while ((inputLine = buffer.readLine()) != null) {
					strBody.append(inputLine);
				}
			}
			
			printResponse(responseBody, strBody.toString());

		} catch (Exception e) {
			log.error(e.getMessage(), e);
		} finally {
			// Clean buffer and close connection
			if (buffer != null) {
				buffer.close();
			}

			if (con != null) {
				con.disconnect();
			}
			if (conHttps != null) {
				conHttps.disconnect();
			}
		}
		return strBody;
	}

	/**
	 * Setting up HTTP Connection with content body
	 * 
	 * @param url
	 * @param inputParam
	 * @return response in string builder
	 * @throws IOException
	 */
	
	public static StringBuilder urlAccess(String url, String inputParam) 
			throws IOException {
		String defaultContentType = BkpmConstants.HTTP_HEADER_CONTENT_JSON;
		Map<String, String> header = new HashMap<String, String>();
		header.put(BkpmConstants.HTTP_HEADER_CONTENTTYPE, defaultContentType);
		return ConnectionUtils.urlAccess(url,inputParam,header);
	}
	/**
	 * 
	 * @param url
	 * @param inputParam
	 * @param header
	 * @return
	 * @throws IOException
	 */
	public static StringBuilder urlAccess(String url, String inputParam, Map<String, String> header)
			throws IOException {
		HttpsURLConnection conHttps = null;
		HttpURLConnection con = null;
		BufferedReader buffer = null;
		StringBuilder strBody = new StringBuilder();
		String responseBody = null;
		
		boolean logging = true;
		try {
			
			URL preparedUrl = urlSetting(url);

			if(url.contains("https://")){
				TrustManager[] trustAllCerts = new TrustManager[]{
					new X509TrustManager() {
					    public void checkClientTrusted(
						        java.security.cert.X509Certificate[] certs, String authType) {
						    }
					    public void checkServerTrusted(
						        java.security.cert.X509Certificate[] certs, String authType) {
						    }
					    public java.security.cert.X509Certificate[] getAcceptedIssuers() {
						return new java.security.cert.X509Certificate[] {};
					    }
					}
				};

			   try {
			       // Install the all-trusting trust manager
				    SSLContext sc = SSLContext.getInstance("SSL");
				    sc.init(null, trustAllCerts, new java.security.SecureRandom());
				    HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
			    } catch (Exception e) {
			    	
			    }
			   	// Don't verify host names
			        HostnameVerifier hv = new HostnameVerifier() {
			            public boolean verify(String urlHostName, SSLSession session) {
			                return true;
			            }
			        };
			        HttpsURLConnection.setDefaultHostnameVerifier(hv);

			   	conHttps = (HttpsURLConnection) preparedUrl.openConnection();
				conHttps.setDoOutput(true);
				// Authentication token
				conHttps.setRequestMethod(BkpmConstants.REQUEST_METHOD_POST);
				conHttps.setUseCaches(false);
				conHttps.setConnectTimeout(MAX_TIMEOUT);
				conHttps.setReadTimeout(MAX_TIMEOUT);

				// Prepare Required HTTP Headers:
				for(String key : header.keySet()){
					if("Disabled-Log".equalsIgnoreCase(key)){
						logging = false;
						continue;
					}
					conHttps.setRequestProperty(key, header.get(key));
				}
				
				if(!header.containsKey(BkpmConstants.HTTP_HEADER_ACCEPT)){
					conHttps.setRequestProperty(BkpmConstants.HTTP_HEADER_ACCEPT,
							BkpmConstants.HTTP_HEADER_CONTENT_JSON);
					
				}
				if(!header.containsKey(BkpmConstants.HTTP_HEADER_CONTENTTYPE)){
					conHttps.setRequestProperty(BkpmConstants.HTTP_HEADER_CONTENTTYPE,
							BkpmConstants.HTTP_HEADER_CONTENT_JSON);
				}
				
				//if(logging){
					printRequest(url,header,inputParam);
				//}else{
					//printRequest(url,header,"");
				//}
				
				// Write content body
				PrintWriter pw = new PrintWriter(conHttps.getOutputStream());
				pw.write(inputParam);
				pw.close();

				responseBody = String.valueOf(conHttps.getResponseCode());
				if (BkpmConstants.HTTP_STATUS_OK.equals(String.valueOf(conHttps.getResponseCode()))
						|| BkpmConstants.HTTP_STATUS_ACCEPTED.equals(String.valueOf(conHttps.getResponseCode())) 
						|| BkpmConstants.HTTP_STATUS_CREATED.equals(String.valueOf(conHttps.getResponseCode()))) {
					buffer = new BufferedReader(new InputStreamReader(
							conHttps.getInputStream()));
				} else {
					log.debug("Check Error :"+conHttps.getErrorStream());
					buffer = new BufferedReader(new InputStreamReader(
							conHttps.getErrorStream()));
				}
			}else{
				con = (HttpURLConnection) preparedUrl.openConnection();
				con.setDoOutput(true);
				// Authentication token
				con.setRequestMethod(BkpmConstants.REQUEST_METHOD_POST);
				con.setUseCaches(false);
				con.setConnectTimeout(MAX_TIMEOUT);
				con.setReadTimeout(MAX_TIMEOUT);

				// Prepare Required HTTP Headers:
				for(String key : header.keySet()){
					if("Disabled-Log".equalsIgnoreCase(key)){
						logging = false;
						continue;
					}
					con.setRequestProperty(key, header.get(key));
				}
				
				if(!header.containsKey(BkpmConstants.HTTP_HEADER_ACCEPT)){
					con.setRequestProperty(BkpmConstants.HTTP_HEADER_ACCEPT,
							BkpmConstants.HTTP_HEADER_CONTENT_JSON);
					
				}
				if(!header.containsKey(BkpmConstants.HTTP_HEADER_CONTENTTYPE)){
					con.setRequestProperty(BkpmConstants.HTTP_HEADER_CONTENTTYPE,
							BkpmConstants.HTTP_HEADER_CONTENT_JSON);
				}
				
				//if(logging){
					printRequest(url,header,inputParam);
				//}else{
				//	printRequest(url,header,"");
				//}
				
				
				// Write content body
				PrintWriter pw = new PrintWriter(con.getOutputStream());
				pw.write(inputParam);
				pw.close();

				responseBody = String.valueOf(con.getResponseCode());
				if (BkpmConstants.HTTP_STATUS_OK.equals(String.valueOf(con.getResponseCode()))
						|| BkpmConstants.HTTP_STATUS_ACCEPTED.equals(String.valueOf(con.getResponseCode())) 
						|| BkpmConstants.HTTP_STATUS_CREATED.equals(String.valueOf(con.getResponseCode()))) {
					buffer = new BufferedReader(new InputStreamReader(
							con.getInputStream()));
				} else {
					log.debug("Check Error :"+con.getErrorStream());
					buffer = new BufferedReader(new InputStreamReader(
							con.getErrorStream()));
				}
			}

			if (buffer != null) {
				String inputLine;
				while ((inputLine = buffer.readLine()) != null) {
					strBody.append(inputLine);
				}
				buffer.close();
			}
			
			printResponse(responseBody, strBody.toString());

		} catch (Exception e) {
			log.error(e.getMessage(), e);
		} finally {

			// Clean buffer and close connection
			if (buffer != null) {
				buffer.close();
			}
			if (con != null) {
				con.disconnect();
			}
			if (conHttps != null) {
				conHttps.disconnect();
			}
		}
		return strBody;
	}

	/**
	 * Configure Proxy setting
	 * 
	 * @param url
	 * @return
	 * @throws IOException
	 */
	public static URL urlSetting(String url) throws IOException {
		URL preparedUrl = new URL(url);

		String proxyHost = "";
		String proxyPort = "";	

		Properties systemProperties = System.getProperties();
		systemProperties.setProperty(BkpmConstants.HTTP_PROXYHOST_KEY, proxyHost);
		systemProperties.setProperty(BkpmConstants.HTTP_PROXYPORT_KEY, proxyPort);
		systemProperties.setProperty(BkpmConstants.HTTPS_PROXYHOST_KEY, proxyHost);
		systemProperties.setProperty(BkpmConstants.HTTPS_PROXYPORT_KEY, proxyPort);
		Authenticator authenticator = new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				String proxyUsername = "";
				String proxyPassword = "";
				return new PasswordAuthentication(proxyUsername,
						proxyPassword == null ? new char[] {}
								: proxyPassword.toCharArray());
			}
		};
		Authenticator.setDefault(authenticator);
		return preparedUrl;
	}
	
	public static  void printRequest(String url, Map<String, String> header, String requestBody){
		StringBuilder sb = new StringBuilder();
        sb.append(JsonUtil.NEW_LINE);
        sb.append("----------Request-------------").append(JsonUtil.NEW_LINE);
        sb.append("URL : ").append(url).append(JsonUtil.NEW_LINE);
        sb.append("Header :").append(JsonUtil.NEW_LINE);
        for(String key : header.keySet()){
        	sb.append(key).append(" : ").append(header.get(key)).append(JsonUtil.NEW_LINE);
        }
        sb.append("Body :").append(JsonUtil.NEW_LINE);
        sb.append(requestBody).append(JsonUtil.NEW_LINE);
        sb.append("------------------------------");
        
        log.debug(sb.toString());
	}
	
	public static  void printResponse(String responseCode, String responseBody){
		StringBuilder sb = new StringBuilder();
		sb.append(JsonUtil.NEW_LINE);
        sb.append("-----------Response-----------").append(JsonUtil.NEW_LINE);
        sb.append("Status :").append(responseCode).append(JsonUtil.NEW_LINE);
        sb.append("Body :").append(JsonUtil.NEW_LINE);
        sb.append(responseBody).append(JsonUtil.NEW_LINE);
        sb.append("------------------------------");
        
        log.debug(sb.toString());
	}
}