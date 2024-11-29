package com.coding.siteannonce.controller;

import com.coding.siteannonce.dao.AnnouncementDAO;
import com.coding.siteannonce.dao.IAnnouncementDAO;
import com.coding.siteannonce.model.Announcement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "displayAnnouncementsServlet", value = "/")
public class DisplayAnnouncementsServlet extends HttpServlet {

    private IAnnouncementDAO dao;
    private List<Announcement> announcements;

    public void init() {
        dao = new AnnouncementDAO();
        announcements = new ArrayList<>();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        announcements = dao.getAll();
        announcements.forEach(System.out::println);

        request.setAttribute("announcements", announcements);
        RequestDispatcher dispatcher = request.getRequestDispatcher("displayAnnouncements.jsp");

        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            throw new RuntimeException( e );
        }
    }

    public void destroy() {
    }
}