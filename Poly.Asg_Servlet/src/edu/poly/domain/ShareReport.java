package edu.poly.domain;

import java.util.Date;

public class ShareReport {
	private String username;
	private String videoId;
	private String emails;
	private Date sharedDate;
	
	public ShareReport() {
		super();
	}

	public ShareReport(String username, String videoId, String emails, Date sharedDate) {
		super();
		this.username = username;
		this.videoId = videoId;
		this.emails = emails;
		this.sharedDate = sharedDate;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getVideoId() {
		return videoId;
	}

	public void setVideoId(String videoId) {
		this.videoId = videoId;
	}

	public String getEmails() {
		return emails;
	}

	public void setEmails(String emails) {
		this.emails = emails;
	}

	public Date getSharedDate() {
		return sharedDate;
	}

	public void setSharedDate(Date sharedDate) {
		this.sharedDate = sharedDate;
	}

	
}
