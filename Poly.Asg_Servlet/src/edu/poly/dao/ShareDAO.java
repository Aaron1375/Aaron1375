package edu.poly.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import edu.poly.domain.ShareReport;
import edu.poly.model.Share;

public class ShareDAO extends AbstractEntityDAO<Share>{

	public ShareDAO() {
		super(Share.class);
	}

	
	public List<ShareReport> ReportShareByVideo(String videoId){
		String jpql = "select new edu.poly.domain.ShareReport(s.user.username, s.user.email, s.video.videoId, s.sharedDate) "
				+ "from Share s where s.video.videoId=:videoId ";
		
		EntityManager em = JpaUtils.getEntityManager();
		
		List<ShareReport> list = null;
		
		
		try {
			TypedQuery<ShareReport> query =  em.createQuery(jpql, ShareReport.class);
			
			query.setParameter("videoId", videoId);
			
			list = query.getResultList();

		} finally {
			em.close();
		}
		
		
		
		return list;
	}
	
	
}
