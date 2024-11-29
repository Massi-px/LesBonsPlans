package com.coding.siteannonce.api;

import java.io.IOException;
import java.io.PrintWriter;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.coding.siteannonce.dao.AnnouncementDAO;
import com.coding.siteannonce.dao.IAnnouncementDAO;
import com.coding.siteannonce.model.Announcement;
import com.google.gson.Gson;

@WebServlet(name="/api/announcement", value = "/api/announcement")
public class AnnouncementApiServlet extends HttpServlet {
    private final Gson gson = new Gson();
    private IAnnouncementDAO dao;
    public AnnouncementApiServlet() {
        super();
    }

    public void init() {
        dao = new AnnouncementDAO();
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
        String             search       = request.getParameter("keyword");
        List<Announcement> announcement = dao.searchWithParam(search);
        if (announcement == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        sendAsJson(response, announcement);
    }
}