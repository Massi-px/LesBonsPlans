package com.riadh.siteannonce.controller;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import com.riadh.siteannonce.dao.AnnonceDao;
import com.riadh.siteannonce.dao.IAnnonceDao;
import com.riadh.siteannonce.model.Annonce;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private IAnnonceDao daoAcces;
    private List<Annonce> annonces;

    public void init() {
        daoAcces = new AnnonceDao();
        annonces = new ArrayList<>();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        annonces = daoAcces.getAllAnnonces();
        annonces.forEach(System.out::println);

        request.setAttribute("annonces", annonces);

        RequestDispatcher dispatcher = request.getRequestDispatcher("DisplayAnnonces.jsp");
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