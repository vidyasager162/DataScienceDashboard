package com.example;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.io.File;
@MultipartConfig
public class CsvUploadServlet extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Handle file upload
        Part filePart = request.getPart("file");
        String fileName = filePart.getSubmittedFileName();

        // Save the file to a temporary directory
        File file = new File(System.getProperty("java.io.tmpdir"), fileName);
        filePart.write(file.getAbsolutePath());

        // Forward to CSV display servlet with file path
        request.setAttribute("filePath", file.getAbsolutePath());
        request.getRequestDispatcher("displayCsv").forward(request, response);
    }
}
