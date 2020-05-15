package controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import data.Customer;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import server.DB_Access;

@WebServlet(name = "RetrieveCustomersFromRepresentative", urlPatterns = {"/RetrieveCustomersFromRepresentative"})
public class RetrieveCustomersFromRepresentative extends HttpServlet {
    
    private DB_Access db = DB_Access.getInstance();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        
        System.out.println("Start: RetrieveCustomersFromRepresentative");
        
        String bodyText = new BufferedReader(new InputStreamReader(request.getInputStream())).readLine();
        
        System.out.println("RetrieveCustomersFromRepresentative BodyText: " + bodyText);
        
        List<Customer> customers = db.getCustomersFromRepresentative(bodyText);
        
        System.out.println("List Customers: " + customers);
        
        GsonBuilder builder = new GsonBuilder();
        
        Gson gson = builder.excludeFieldsWithoutExposeAnnotation().create();
        
        String jsonString = gson.toJson(customers);
        
        System.out.println("RetrieveCustomers: " + jsonString);
        
        OutputStreamWriter out = new OutputStreamWriter(response.getOutputStream());
        
        out.write(jsonString);
        
        out.flush();

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
