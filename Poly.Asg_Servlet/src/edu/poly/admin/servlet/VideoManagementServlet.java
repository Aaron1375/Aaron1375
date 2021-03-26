 package edu.poly.admin.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.sun.mail.imap.OlderTerm;

import edu.poly.common.PageInfo;
import edu.poly.common.PageType;
import edu.poly.common.UploadUtils;
import edu.poly.dao.VideoDAO;
import edu.poly.model.Video;

@WebServlet({"/Admin/VideoManagement",
	"/VideoManagement","/Admin/VideoManagement/create",
	"/Admin/VideoManagement/update",
		"/Admin/VideoManagement/delete", 
		"/Admin/VideoManagement/reset", 
		"/Admin/VideoManagement/edit" })
@MultipartConfig
public class VideoManagementServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURL().toString();
		if (url.contains("edit")) {
			edit(req, resp);
			return;
		} else if (url.contains("delete")) {
			delete(req, resp);
			return;
		} else if (url.contains("reset")) {
			reset(req, resp);
			return;
		}

		Video video = new Video();
		video.setPoster("images/laptop.jpg");
		
		findAll(req, resp);
		req.setAttribute("video", video);

		PageInfo.prepareAndForward(req, resp, PageType.VIDEO_MANAGEMENT_PAGE);
	}
//		RESET USER
	private void reset(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Video video = new Video();
		
		video.setPoster("images/laptop.jpg");
		req.setAttribute("video", video);
		
		findAll(req, resp);
		PageInfo.prepareAndForward(req, resp, PageType.VIDEO_MANAGEMENT_PAGE);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String url = req.getRequestURL().toString();
		if (url.contains("create")) {
			create(req, resp);
			return;
		} else if (url.contains("update")) {
			update(req, resp);
			return;
		} else if (url.contains("delete")) {
			delete(req, resp);
			return;
		} else if (url.contains("reset")) {
			reset(req, resp);
			return;
		}
	}
//	DELETE VIDEO
private void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	String id = req.getParameter("videoId");

	if (id == null) {
		req.setAttribute("error", "video id is required");
		PageInfo.prepareAndForward(req, resp, PageType.VIDEO_MANAGEMENT_PAGE);
		return;
	}

	try {
		VideoDAO dao = new VideoDAO();
		Video video = dao.findById(id);

		if (video == null) {
			req.setAttribute("error", "Video id not found!");
			PageInfo.prepareAndForward(req, resp, PageType.VIDEO_MANAGEMENT_PAGE);
			return;
		}
		dao.delete(id);
		req.setAttribute("message", "Video is deleted!");
		req.setAttribute("video", new Video());
		
		findAll(req, resp);
		
	} catch (Exception e) {
		e.printStackTrace();
		req.setAttribute("error", "Error:" + e.getMessage());
	}
	PageInfo.prepareAndForward(req, resp, PageType.VIDEO_MANAGEMENT_PAGE);

	}

//	FIND ALL
	private void findAll(HttpServletRequest req, HttpServletResponse resp) {

		try {
			VideoDAO dao = new VideoDAO();
			
			List<Video> list = dao.findAll();
					
			req.setAttribute("videos", list);
			
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("error", "Error:" + e.getMessage());
		}
	}
//	UPDATE VIDEO
	
	private void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Video video = new Video();
		try {
			BeanUtils.populate(video, req.getParameterMap());

			VideoDAO dao = new VideoDAO();
			Video oldVideo = dao.findById(video.getVideoId());

			if (req.getPart("cover").getSize() == 0) {
				video.setPoster(oldVideo.getPoster());
			} else {
				video.setPoster("uploads/" + UploadUtils.processUpladField("cover", req,
						"/uploads", video.getVideoId()));
//				video.setPoster("uploads/");
			}
			dao.update(video);
			
			req.setAttribute("video", video);
			req.setAttribute("message", "Video is updated!");
			
			findAll(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("erroe", "Error:" + e.getMessage());
		}
		PageInfo.prepareAndForward(req, resp, PageType.VIDEO_MANAGEMENT_PAGE);
	}
//	CREATE VIDEO
	private void create(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Video video = new Video();
		try {
			BeanUtils.populate(video, req.getParameterMap());

			video.setPoster("uploads/" + UploadUtils.processUpladField("cover", req, 
					"/uploads", video.getVideoId()));
//			video.setPoster("uploads/");
			VideoDAO dao = new VideoDAO();
			dao.insert(video);

			req.setAttribute("video", video);
			req.setAttribute("message", "Video is inserted!!!");
		} catch (Exception e) {
			
			e.printStackTrace();
			req.setAttribute("error","Error:" + e.getMessage());
		}
		findAll(req, resp);
		PageInfo.prepareAndForward(req, resp, PageType.VIDEO_MANAGEMENT_PAGE);
		
	}
	
//	EDIT VIDEO
	private void edit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("videoId");

		if (id == null) {
			req.setAttribute("error", "video id is required");
			PageInfo.prepareAndForward(req, resp, PageType.VIDEO_MANAGEMENT_PAGE);
			return;
		}

		try {
			VideoDAO dao = new VideoDAO();
			Video video = dao.findById(id);

			req.setAttribute("video", video);
			findAll(req, resp);
			
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("erroe", "Error:" + e.getMessage());
		}
		PageInfo.prepareAndForward(req, resp, PageType.VIDEO_MANAGEMENT_PAGE);

	}

	

}
