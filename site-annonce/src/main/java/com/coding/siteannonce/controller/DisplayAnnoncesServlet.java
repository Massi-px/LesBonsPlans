package com.coding.siteannonce.controller;

import com.coding.siteannonce.dao.AnnonceDao;
import com.coding.siteannonce.dao.IAnnonceDao;
import com.coding.siteannonce.model.Annonce;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "displayAnnoncesServlet", value = "/")
public class DisplayAnnoncesServlet extends HttpServlet {

    private IAnnonceDao annonceDao;
    private List<Annonce> annonces;

    public void init() {
        annonceDao = new AnnonceDao();
        annonces = new ArrayList<>();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        annonces = annonceDao.getAllAnnonces();
        annonces.forEach(System.out::println);

        request.setAttribute("annonces", annonces);
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