/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package edu.iit.sat.itmd4515.ckolathabraham.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Cini
 */
@WebServlet(name = "DemoServlet", urlPatterns = {"/demo"})
public class DemoServlet extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(DemoServlet.class.getName());

    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet DemoServlet GET Request</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DemoServlet at GET Request" + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");

            String name = request.getParameter("user_name");
            LOG.info("This is an HTTP GET with " + name);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //LOG.finest("this is a debug message, it will only be written if logging is configured to write debug message");
        //LOG.info("By default,logging is going to write info messages and above");
        //LOG.severe("Severe would also be written by default");
        String name = request.getParameter("user_name");
        LOG.info("This is an HTTP POST with " + name);
    }

    /**
     *
     * @return
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
