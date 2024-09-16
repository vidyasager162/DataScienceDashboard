package com.example.DataScienceDashboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class FileUploadController {
    private static final String UPLOADED_FOLDER = "D://Code//Projects//DataScienceDashboard//src//main//resources//files//";

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/uploadCsv")
    public String singleFileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes, HttpSession session) {
        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a CSV file to upload.");
            return "redirect:/";
        }

        try {
            // Save the file to the specified directory
            File dir = new File(UPLOADED_FOLDER);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            File serverFile = new File(dir.getAbsolutePath() + File.separator + file.getOriginalFilename());
            file.transferTo(serverFile);

            // Parse the file and store data in the session
            List<String> headers = new ArrayList<>();
            List<List<String>> rows = new ArrayList<>();

            try (BufferedReader br = new BufferedReader(new FileReader(serverFile))) {
                String line;
                boolean isHeader = true;

                while ((line = br.readLine()) != null) {
                    String[] values = line.split(",");

                    if (isHeader) {
                        for (String header : values) {
                            headers.add(header);
                        }
                        isHeader = false;
                    } else {
                        List<String> row = new ArrayList<>();
                        for (String value : values) {
                            row.add(value);
                        }
                        rows.add(row);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Store headers and rows in the session
            session.setAttribute("headers", headers);
            session.setAttribute("rows", rows);

            // Redirect to display page
            return "redirect:/displayCsv";
        } catch (IOException e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("message", "Failed to upload file.");
            return "redirect:/";
        }
    }

    @GetMapping("/displayCsv")
    public String displayCsv(Model model, HttpSession session) {
        // Retrieve the headers and rows from the session
        List<String> headers = (List<String>) session.getAttribute("headers");
        List<List<String>> rows = (List<List<String>>) session.getAttribute("rows");

        if (headers == null || rows == null) {
            model.addAttribute("message", "No CSV data found. Please upload a file first.");
            return "error";
        }

        model.addAttribute("headers", headers);
        model.addAttribute("rows", rows);

        return "csvDisplay";
    }

    @PostMapping("/generateChart")
    @ResponseBody
    public Map<String, Object> generateChart(@RequestParam("chartType") String chartType, HttpSession session) {
        List<String> headers = (List<String>) session.getAttribute("headers");
        List<List<String>> rows = (List<List<String>>) session.getAttribute("rows");

        if (headers == null || rows == null) {
            return Map.of("error", "No CSV data found. Please upload a file first.");
        }

        // Prepare chart data based on chart type
        // Here, example is given for a simple chart; customize as needed
        Map<String, Object> chartData = Map.of(
                "labels", headers, // Assuming headers are used as labels
                "datasets", List.of(Map.of(
                        "label", "Data",
                        "data", rows.stream().flatMap(List::stream).map(Double::parseDouble).collect(Collectors.toList())
                ))
        );

        return chartData;
    }
}

