package edu.poly.admin.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.poly.common.PageInfo;
import edu.poly.common.PageType;
import edu.poly.dao.FavoriteDAO;
import edu.poly.dao.ShareDAO;
import edu.poly.dao.VideoDAO;
import edu.poly.domain.FavoriteReport;
import edu.poly.domain.FavoriteUserReport;
import edu.poly.domain.ShareReport;
import edu.poly.model.Video;

/**
 * Servlet implementation class ReportManagementServlet
 */
@WebServlet("/ReportManagement")
public class ReportManagementServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		reportFavoritesByVideo(request, response);
		reportFavoriteUsersByVideo(request, response);
		shareVideo(request, response);
		PageInfo.prepareAndForward(request, response, PageType.REPORT_MANAGEMENT_PAGE);
	}


	


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
// favorite
	protected void reportFavoritesByVideo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			FavoriteDAO dao = new FavoriteDAO();
			List<FavoriteReport> list = dao.reportFavoritesByVideo();
			
			request.setAttribute("favList", list);
		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());
		}
	}
//	user favorite
	protected void reportFavoriteUsersByVideo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			String videoUserId = request.getParameter("videoUserId");
			
			VideoDAO vdao = new VideoDAO();
			List<Video> vlist = vdao.findAll();
			
			if (videoUserId == null && vlist.size() > 0) {
				videoUserId = vlist.get(0).getVideoId();
			}
			
			FavoriteDAO dao = new FavoriteDAO();
			List<FavoriteUserReport> list = dao.reportFavvoriteUserByVideo(videoUserId);
			
			request.setAttribute("videoUserId", videoUserId);
			request.setAttribute("vidList", vlist);
			request.setAttribute("favUser", list);
		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());
		}
	}
	private void shareVideo(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		try {
			String userShareVideo = request.getParameter("userShareVideo");
			VideoDAO vdao = new VideoDAO();
			List<Video> vlist = vdao.findAll();
			
			if (userShareVideo == null && vlist.size() > 0) {
				userShareVideo = vlist.get(0).getVideoId();
			}
			
			ShareDAO dao = new ShareDAO();
			List<ShareReport> list = dao.ReportShareByVideo(userShareVideo);
			
			request.setAttribute("userShareVideo", userShareVideo);
			request.setAttribute("shareList", vlist);
			request.setAttribute("shareUser", list);
			
		} catch (Exception e) {
			// TODO: handle exception
			request.setAttribute("error", e.getMessage());
		}
	}
}
