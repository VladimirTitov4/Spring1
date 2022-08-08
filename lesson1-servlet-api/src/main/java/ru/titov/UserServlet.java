package ru.titov;

import ru.titov.persist.User;
import ru.titov.persist.UserRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/user/*")
public class UserServlet extends HttpServlet {

    private UserRepository userRepository;

    @Override
    public void init() throws ServletException {
        this.userRepository = new UserRepository();
        this.userRepository.insert(new User("Vladimir"));
        this.userRepository.insert(new User("Anastasia"));
        this.userRepository.insert(new User("Evgeniy"));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*String pathInfo = request.getPathInfo();
        String contextPath = request.getContextPath();
        response.getWriter().println("request.getPathInfo() " + "<p>" + pathInfo + "</p>");
        response.getWriter().println("request.getContextPath() " + "<p>" + contextPath + "</p>");*/

        PrintWriter writer = response.getWriter();
        writer.println("<table>");
        writer.println("<tr>");
        writer.println("<th>id</th>");
        writer.println("<th>username</th>");
        writer.println("</tr>");

        for (User user : userRepository.findAll()) {
            writer.println("<tr>");
            writer.println("<td>" + user.getId() + "</td>");
//            writer.println("<td><a href='" + getServletContext().getContextPath() + "/user/" + user.getId() + "'>" + user.getId() + "</a></td>");
            writer.println("<td>" + user.getUsername() + "</td>");
            writer.println("</tr>");
        }

        writer.println("</table>");
    }
}
