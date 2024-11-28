package com.coding.siteannonce.api;

import java.io.IOException;
import java.io.PrintWriter;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.coding.siteannonce.dao.AnnonceDao;
import com.coding.siteannonce.dao.IAnnonceDao;
import com.coding.siteannonce.model.Annonce;
import com.google.gson.Gson;

@WebServlet(name="/api/announcement", value = "/api/announcement")
public class AnnonceApiServlet extends HttpServlet {
    private final Gson gson = new Gson();
    private IAnnonceDao annonceDao;
    public AnnonceApiServlet() {
        super();
    }

    public void init() {
        annonceDao = new AnnonceDao();
    }

    private void sendAsJson(HttpServletResponse response, Object obj) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        String res = gson.toJson(obj);
        PrintWriter out = response.getWriter();
        out.print(res);
        out.flush();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String search = request.getParameter("keyword");
        System.out.println(search);
        List<Annonce>announcement = annonceDao.searchWithParam(search);
        System.out.println(announcement);
        if (announcement == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        sendAsJson(response, announcement);
    }
}