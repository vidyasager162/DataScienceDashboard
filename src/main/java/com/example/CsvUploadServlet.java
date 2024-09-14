package com.example;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;

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
