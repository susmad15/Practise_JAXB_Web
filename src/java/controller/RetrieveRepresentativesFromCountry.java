package controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import data.Representative;
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

@WebServlet(name = "RetrieveRepresentativesFromCountry", urlPatterns = {"/RetrieveRepresentativesFromCountry"})
public class RetrieveRepresentativesFromCountry extends HttpServlet {
    
    private DB_Access db = DB_Access.getInstance();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("Start: RetrieveRepresentativesFromCountry");
        
        String bodyText = new BufferedReader(new InputStreamReader(request.getInputStream())).readLine();
        
        List<Representative> representatives = db.getRepresentativesOfCountry(bodyText);
        
        System.out.println("Representatives: " + representatives);
        
        response.setContentType("application/json");
        
        OutputStreamWriter out = new OutputStreamWriter(response.getOutputStream());
        
        Gson gson = (new GsonBuilder()).excludeFieldsWithoutExposeAnnotation().create();
        
        System.out.println("Hey 1");
        
        String json_text = gson.toJson(representatives);
        
        System.out.println("Hey 2 ");
        
        System.out.println("Json Text: " + json_text);
        
        out.write(json_text);
        
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
