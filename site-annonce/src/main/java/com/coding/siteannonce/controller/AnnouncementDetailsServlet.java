package com.coding.siteannonce.controller;

import com.coding.siteannonce.dao.AnnonceDao;
import com.coding.siteannonce.dao.IAnnonceDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "detailsServlet", value = "/detail")
public class AnnouncementDetailsServlet extends HttpServlet {

    private IAnnonceDao annonceDao;

    public void init() {
        annonceDao = new AnnonceDao();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");
        var annonce = annonceDao.getAnnonceById(Integer.parseInt(id));
        request.setAttribute("annonceDetails", annonce);
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