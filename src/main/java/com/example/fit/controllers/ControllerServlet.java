package com.example.fit.controllers;

import com.example.fit.entities.Account;
import com.example.fit.entities.GrantAccess;
import com.example.fit.repositories.AccountRepositories;
import com.example.fit.repositories.DatabaseRepository;
import com.example.fit.services.AccountServices;
import jakarta.inject.Inject;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.http.HttpRequest;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = {"/ControlServlet"})
public class ControllerServlet extends HttpServlet {
    @Inject
    AccountServices services;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        switch (action) {
            case "login":
                logIn(request, response);
                break;
            case "logout":
                logOut(request, response);
                break;
            case "addAccount":
                addAccount(request, response);
                break;
            case "submitAddAccount":
                submitaddAccount(request, response);
                break;
            default:
                break;
        }
    }

    public void logIn(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uName = request.getParameter("txtUserName");
        String uPassword = request.getParameter("txtPassWord");
        PrintWriter out = response.getWriter();
        GrantAccess gr = services.getAccountRole(uName, uPassword);
        List<GrantAccess> dsAccount = services.getDsAccount();
        if (gr.getRole_id().getRole_id().equals("admin")) {
            Account admin = services.accountLogin(uName, uPassword);
            RequestDispatcher dispatcher = request.getRequestDispatcher("dashboard.jsp");
            request.setAttribute("dsAcc", dsAccount);
            request.setAttribute("admin-role", gr);
            dispatcher.forward(request, response);

        } else {
            Account ac = services.accountLogin(uName, uPassword);
            RequestDispatcher dispatcher = request.getRequestDispatcher("userLogin.jsp");
            request.setAttribute("userLogin", ac);
            dispatcher.forward(request, response);
        }
    }

    public void logOut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uName = request.getParameter("userID");
        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
    }

    public void addAccount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("addAccount.jsp");
        dispatcher.forward(request, response);

    }

    public void submitaddAccount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String user = request.getParameter("username");
        String fullName = request.getParameter("fullName");
        String pass = request.getParameter("password");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        Account ac = new Account(user, fullName, pass, email, phone, 1);
        boolean rs = services.addAccount(ac);
        request.getRequestDispatcher("dashboard.jsp").forward(request, response);

    }
}

