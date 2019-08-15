package com.isgm.chatting.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

public class EntityListener {
	@PrePersist
	@PreUpdate
	public void CreateDate(User user) {
		Date date= new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
	    String strDate= formatter.format(date);  
		System.err.println("Account Created Date...."+strDate);
		user.setCreateDate(strDate);
	}

}
