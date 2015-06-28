package de.l3s.elasticquery;

import java.net.URI;
import java.net.URISyntaxException;

public class Url {

	private String timestamp;
	private String origUrl;
	private String redirectUrl;	
	private String compressedsize;
	private String offset;
	private String filename;
	private String domain;
	
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public String getOrigUrl() {
		return origUrl;
	}
	public void setOrigUrl(String origUrl) {
		this.origUrl = origUrl;
	}
	public String getRedirectUrl() {
		return redirectUrl;
	}
	public void setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
	}
	public String getCompressedsize() {
		return compressedsize;
	}
	public void setCompressedsize(String compressedsize) {
		this.compressedsize = compressedsize;
	}
	public String getOffset() {
		return offset;
	}
	public void setOffset(String offset) {
		this.offset = offset;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getDomain() {
		return domain;
	}
	public void setDomain(String url) {
	
		URI uri = null;
		try {
			uri = new URI(url);
		} catch (URISyntaxException e) {
		
			domain = url;
		}
	
		try {
	    domain = uri.getHost();
	    domain = domain.startsWith("www.") ? domain.substring(4) : domain;
		} catch (Exception e) {
			domain = url;
		}
		}
	
}
