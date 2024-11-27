package com.coding.siteannonce.api;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.coding.siteannonce.dao.AnnonceDao;
import com.coding.siteannonce.dao.IAnnonceDao;
import com.google.gson.Gson;

@WebServlet(
        urlPatterns = {"/api/announcement/*"},
        initParams = {
                @WebInitParam(name = "announcementId", value = "id")
        }
)public class AnnonceApiServlet extends HttpServlet {
    private Gson gson = new Gson();
    private IAnnonceDao annonceDao;
    public AnnonceApiServlet() {
        super();
    }

    public void init() {
        annonceDao = new AnnonceDao();
    }

    private void sendAsJson(HttpServletResponse response, Object obj) throws IOException {
        response.setContentType("application/json");
        String res = gson.toJson(obj);
        PrintWriter out = response.getWriter();
        out.print(res);
        out.flush();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();

        if (pathInfo == null || pathInfo.equals("/")) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        String[] splits = pathInfo.split("/");
        if (splits.length != 2) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        String annonceId = splits[1];
        var annonce = annonceDao.getAnnonceById(Integer.parseInt(annonceId));
        if (annonce == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        sendAsJson(response, annonce);
    }
}