package controller;

import data.Country;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import server.DB_Access;

@WebServlet(name = "CountryController", urlPatterns = {"/CountryController"})
public class CountryController extends HttpServlet {

    private DB_Access db = DB_Access.getInstance();

    private String path;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        System.out.println("CountryController: start");

        path = request.getSession().getServletContext().getRealPath("/res/countries.xml");

        System.out.println("Pfad: " + path);

        db.persistDataHolder(path);

        List<Country> countries = db.getAllCountries();

        System.out.println("All countries: " + countries);

        request.setAttribute("countries", countries);

        request.getRequestDispatcher("view.jsp").forward(request, response);

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
