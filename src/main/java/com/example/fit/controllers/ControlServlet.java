package com.example.fit.controllers;

import com.example.fit.entities.Account;
import com.example.fit.entities.GrantAccess;
import com.example.fit.entities.Log;
import com.example.fit.entities.Status;
import com.example.fit.repositories.AccountRepositories;
import com.example.fit.repositories.LogRepositories;
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
import java.util.List;

@WebServlet("/")
public class ControlServlet extends HttpServlet {
//    @Inject
//    AccountServices services;
private final AccountRepositories services = new AccountRepositories();
private final LogRepositories servicesLog = new LogRepositories();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();
        switch (action) {
            case "/login":
                logIn(request, response);
                break;
            case "/logout":
                logOut(request, response);
                break;
            case "/formAdd":
                formAdd(request, response);
                break;
            case "/addAccount":
                addAccount(request, response);
                break;
            case "/delete":
                deleteAccount(request,response);
                break;
            case "/loadInfIntoUpdateForm":
                loadInfIntoUpdateForm(request,response);
                break;
            case "/listLog":
                listTimeLog(request,response);
                break;
            case "/update":
                updateAccount(request,response);
                break;
            case "/cancel":
               cancelAdd(request,response);
                break;
            default:

        }
    }

    private void logIn(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        Account acc = new Account();
        String uName = request.getParameter("txtUserName");
        String uPassword = request.getParameter("txtPassWord");
        GrantAccess gr = services.getAccountRole(uName, uPassword);
        List<Account> dsAccount = services.getAll();
        List<GrantAccess> dsRole = services.getDsAccount();
        List<Log> list = servicesLog.getAllLog();
        String logout = request.getParameter("logout-button");
        if (gr.getRole_id().getRole_id().equals("admin")) {
            session.setAttribute("dsTimeLog",list);
            acc = services.accountLogin(uName, uPassword);
            session.setAttribute("dsAcc", dsAccount);
            session.setAttribute("admin-role", gr);
            session.setAttribute("dsRole", dsRole);
            RequestDispatcher dispatcher = request.getRequestDispatcher("dashboard.jsp");
            dispatcher.forward(request, response);
        } else {
            acc = services.accountLogin(uName, uPassword);
            session.setAttribute("userLogin", acc);
            RequestDispatcher dispatcher = request.getRequestDispatcher("userLogin.jsp");
            dispatcher.forward(request, response);
        }
    }

    private void logOut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("userID");
        boolean rs = services.accountLogout(id);
        if(rs) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
            dispatcher.forward(request, response);
        }
    }
    private void listTimeLog(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
    private void formAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("addAccount.jsp").forward(request, response);
    }

    public void addAccount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uname = request.getParameter("username");
        String pass = request.getParameter("password");
        String fullName = request.getParameter("fullName");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        Account ac = new Account(uname,fullName,pass,email,phone,Status.Active);
        boolean rs = services.addAccount(ac);
        PrintWriter out = response.getWriter();
        if (rs) {
            List<Account> updateList = services.getAll();
            HttpSession session = request.getSession(true);
            session.setAttribute("dsAcc", updateList);
            out.println("<script type=\"text/javascript\">");
            out.println("alert('Insert Success!');");
            out.println("location='listAccount.jsp';");
            out.println("</script>");

        }
    }
    public void updateAccount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uname = request.getParameter("username");
        String pass = request.getParameter("password");
        String fullName = request.getParameter("fullName");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        Account ac = new Account(uname,fullName,pass,email,phone,Status.Active);
        boolean rs = services.updateAccount(ac);
        PrintWriter out = response.getWriter();
        if (rs) {
            List<Account> updateList = services.getAll();
            HttpSession session = request.getSession(true);
            session.setAttribute("dsAcc", updateList);
            out.println("<script type=\"text/javascript\">");
            out.println("alert('Update Success!');");
            out.println("location='listAccount.jsp';");
            out.println("</script>");

        }
    }
    public void deleteAccount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("idAccount");
        boolean rs = services.delAccountById(id);
        PrintWriter out = response.getWriter();
        if (rs) {
            List<Account> updateList = services.getAll();
            HttpSession session = request.getSession(true);
            session.setAttribute("dsAcc", updateList);
            out.println("<script type=\"text/javascript\">");
            out.println("alert('Delete Success!');");
            out.println("location='listAccount.jsp';");
            out.println("</script>");
        }
    };
    public void loadInfIntoUpdateForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            String accountId = request.getParameter("accountID");
            Account account = services.findAccountById(accountId);
            request.setAttribute("loadInformation",account);
            request.getRequestDispatcher("updateAccount.jsp").forward(request,response);
    }
    public void cancelAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("listAccount.jsp");
            dispatcher.forward(request,response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}

