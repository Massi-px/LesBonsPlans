package com.coding.siteannonce.controller;

import com.coding.siteannonce.dao.AnnouncementDAO;
import com.coding.siteannonce.dao.IAnnouncementDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "detailsServlet", value = "/detail")
public class AnnouncementDetailsServlet extends HttpServlet {

    private IAnnouncementDAO dao;

    public void init() {
        dao = new AnnouncementDAO();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String id = request.getParameter("id");
        var announcement = dao.getById(Integer.parseInt(id));
        request.setAttribute("announcementDetails", announcement);
        RequestDispatcher dispatcher = request.getRequestDispatcher("announcementDetails.jsp");

        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            throw new RuntimeException( e );
        }
    }

    public void destroy() {
    }
}