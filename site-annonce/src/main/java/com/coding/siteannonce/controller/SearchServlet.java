package com.coding.siteannonce.controller;

import com.coding.siteannonce.dao.AnnonceDao;
import com.coding.siteannonce.dao.IAnnonceDao;
import com.coding.siteannonce.model.Annonce;

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

        IAnnonceDao annonceDao = new AnnonceDao();
        String search = req.getParameter("keyword");
        List<Annonce> annonces = annonceDao.searchWithParam(search);
        req.setAttribute("annonces", annonces);
        RequestDispatcher dispatcher = req.getRequestDispatcher("displayAnnouncements.jsp");

        try {
            dispatcher.forward(req, resp);
        } catch (ServletException e) {
            throw new RuntimeException( e );
        }
    }
}
