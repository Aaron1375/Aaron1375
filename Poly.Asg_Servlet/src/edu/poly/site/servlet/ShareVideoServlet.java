package edu.poly.site.servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.poly.common.EmailUtils;
import edu.poly.common.PageInfo;
import edu.poly.common.PageType;
import edu.poly.common.SessionUtils;
import edu.poly.dao.ShareDAO;
import edu.poly.dao.UserDAO;
import edu.poly.domain.Email;
import edu.poly.model.Share;
import edu.poly.model.User;
import edu.poly.model.Video;

/**
 * Servlet implementation class ShareVideoServlet
 */
@WebServlet("/ShareVideo")
public class ShareVideoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if (!SessionUtils.isLogin(request)) {
			response.sendRedirect("Login");
			return;
		}
		String videoId = request.getParameter("videoId");
		
		if (videoId == null) {
			response.sendRedirect("Homepage");
			return;
		}
		request.setAttribute("videoId", videoId);
		PageInfo.prepareAndForwardSite(request, response, PageType.SITE_SHARE_PAGE);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String emailAddress = request.getParameter("email");
			String videoId = request.getParameter("videoId");
			


			if (videoId == null) {
				request.setAttribute("error", "VideoId is null!");
			}else {
				Email email = new Email();
				email.setFrom("thogprovjp@gmail.com");
				email.setFromPassword("thongnguyen");
				email.setTo(emailAddress);
				email.setSubject("Share favorite Video");
				StringBuilder sb = new StringBuilder();
				sb.append("Dear Ms/Mr. <br>");
				sb.append("tao co cai nay hay lam va tao muon cho may coi. <br>");
				sb.append("Please click the link").append(String.format("<a href=''>View Video</a><br>", videoId));
				sb.append("Regards<br>");
				sb.append("Administrator");
				
				email.setContent(sb.toString());
				EmailUtils.send(email);
				
				ShareDAO dao = new ShareDAO();
				Share share = new Share();
				share.setEmails(emailAddress);
				share.setSharedDate(new Date());
				
				String username = SessionUtils.getLoginedUsername(request);
				User user = new User();
				user.setUsername(username);
				
				share.setUser(user);
				Video video = new Video();
				video.setVideoId(videoId);
				share.setVideo(video);
				
				dao.insert(share);
				request.setAttribute("message", "video link has been sent");
				
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			
			request.setAttribute("error", "Error:" + e.getMessage());
		}
		PageInfo.prepareAndForwardSite(request, response, PageType.SITE_SHARE_PAGE);
	}

}
