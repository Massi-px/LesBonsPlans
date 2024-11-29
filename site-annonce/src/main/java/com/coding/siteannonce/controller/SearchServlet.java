package com.coding.siteannonce.controller;

import com.coding.siteannonce.dao.AnnouncementDAO;
import com.coding.siteannonce.dao.IAnnouncementDAO;
import com.coding.siteannonce.model.Announcement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "searchServlet", value = "/search")
public class SearchServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        IAnnouncementDAO dao        = new AnnouncementDAO();
        String             search        = req.getParameter("keyword");
        List<Announcement> announcements = dao.searchWithParam(search);
        req.setAttribute("announcements", announcements);
        RequestDispatcher dispatcher = req.getRequestDispatcher("displayAnnouncements.jsp");

        try {
            dispatcher.forward(req, resp);
        } catch (ServletException e) {
            throw new RuntimeException( e );
        }
    }
}
