package com.example.fit.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = {"/Home","/Home2"})
public class ControllerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        PrintWriter out = resp.getWriter();

        if(action.equalsIgnoreCase("xxx")){
            out.println("You call xxx");
        }else if(action.equalsIgnoreCase("yyy")){
            out.println("You call yyy");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
         String action = req.getParameter("action");
        PrintWriter out = resp.getWriter();

        if(action.equalsIgnoreCase("xxx")){
            out.println("You call xxx");
        }else if(action.equalsIgnoreCase("yyy")){
            resp.sendRedirect("form2.jsp");
        }
    }
}
