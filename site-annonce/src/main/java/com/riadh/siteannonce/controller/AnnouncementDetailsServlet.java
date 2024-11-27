package com.riadh.siteannonce.controller;

import com.riadh.siteannonce.dao.AnnonceDao;
import com.riadh.siteannonce.dao.IAnnonceDao;
import com.riadh.siteannonce.model.Annonce;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "detailsServlet", value = "/detail")
public class AnnouncementDetailsServlet extends HttpServlet {
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
            System.out.println("Début de redirection");
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            System.out.println("Erreur de redirection");
            throw new RuntimeException( e );
        }

    }

    public void destroy() {
    }
}