package edu.poly.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.hibernate.Transaction;

import edu.poly.model.Video;

public class VideoDAO extends AbstractEntityDAO<Video>{

	public VideoDAO() {
		super(Video.class);
		
	}
	
}
