package org.marroquin.apiservlet.webapp.headers.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.marroquin.apiservlet.webapp.headers.service.LoginService;
import org.marroquin.apiservlet.webapp.headers.service.LoginServiceCookieImpl;
import org.marroquin.apiservlet.webapp.headers.service.LoginServiceSessionImpl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

@WebServlet({"/login","/login.html"})
public class LoginServlet extends HttpServlet {
    final static String USERNAME= "admin";
    final static String PASSWORD = "12345";


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //LoginService auth = new LoginServiceCookieImpl();
        LoginService auth = new LoginServiceSessionImpl();
        Optional<String> usernameOptional = auth.getUsername(req);

        if (usernameOptional.isPresent()){
            resp.setContentType("text/html;charset=UTF-8");
            try (PrintWriter out = resp.getWriter()){
            out.print("<!DOCTYPE hml>");
            out.print("<html>");
            out.print("     <head>");
            out.print("         <meta charset=\"UTF-8\">");
            out.print("         <title>Hola "+usernameOptional.get()+"</title>");
            out.print("     </head>");
            out.print("     <body>");
            out.print("         <h1>Hola "+usernameOptional.get()+" has iniciado sesion con exito </h1>");
            out.println("<p><a href='"+req.getContextPath()+"/index.html'>Volver</a></p>");
            out.println("<p><a href='"+req.getContextPath()+"/logout'>Cerrar Sesion</a></p>");
            out.print("     </body>");
            out.print("</html>");
            }

        }else {
            getServletContext().getRequestDispatcher("/login.jsp").forward(req,resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        if(USERNAME.equals(username) && PASSWORD.equals(password)){
            req.getSession().setAttribute("username",username);

            resp.sendRedirect(req.getContextPath()+ "/login.html");

        }else {
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED,"Lo sentimos no esta autorizado para ingresar esta pagina");
        }

    }
}
