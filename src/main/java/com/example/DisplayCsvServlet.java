package com.example;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/displayCsv")
public class DisplayCsvServlet extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String filePath = (String) request.getAttribute("filePath");

        List<String> headers = new ArrayList<>();
        List<List<String>> rows = new ArrayList<>();

        // Read the CSV file
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean isHeader = true;

            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");

                if (isHeader) {
                    // First row as headers
                    for (String header : values) {
                        headers.add(header);
                    }
                    isHeader = false;
                } else {
                    // Remaining rows as data
                    List<String> row = new ArrayList<>();
                    for (String value: values) {
                        row.add(value);
                    }
                    rows.add(row);
                }
            }
        }

        // Pass the CSV data to JSP
        request.setAttribute("headers", headers);
        request.setAttribute("rows", rows);
        request.getRequestDispatcher("csvDisplay.jsp").forward(request, response);
    }
}
