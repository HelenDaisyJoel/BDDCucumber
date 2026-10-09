package com.nclh.utilities;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

import java.nio.file.Path;
import java.nio.file.Paths;
public class ImagesToPDF {



    public static void main(String[] args) throws IOException {

//        Path projectRoot = Paths.get(System.getProperty("user.dir"));
//
//        Path inputDirPath = projectRoot.resolve("Downloads").resolve("inputDirPath");
//        Path outputDirPath = projectRoot.resolve("Downloads").resolve("outputDirPath");

        // Project root directory
        String projectRoot = System.getProperty("user.dir");

        // Input and Output directories
        File inputDir = new File(projectRoot + File.separator + "Downloads"
                + File.separator + "inputDir");
        System.out.println("Inpu dir is: "+inputDir);

        File outputDir = new File(projectRoot + File.separator + "Downloads"
                + File.separator + "outputDir");

        // Create output directory if it doesn't exist
        if (!outputDir.exists()) {
            outputDir.mkdirs();
        }

        convertImagesToPDF(
                inputDir.toString(),
                outputDir.toString(),
                "C1051021_screenshot.pdf");
    }


    public static void convertImagesToPDF(String inputDirPath, String outputDirPath, String outputFileName)
            throws IOException {

        File inputDir = new File(inputDirPath);
        File outputDir = new File(outputDirPath);

        if (!inputDir.exists() || !inputDir.isDirectory()) {
            throw new IllegalArgumentException("Invalid input directory");
        }

        if (!outputDir.exists()) {
            outputDir.mkdirs();
        }

        // Filter image files (jpg, jpeg, png)
        File[] imageFiles = inputDir.listFiles((dir, name) ->
                name.toLowerCase().endsWith(".jpg") ||
                        name.toLowerCase().endsWith(".jpeg") ||
                        name.toLowerCase().endsWith(".png"));

        if (imageFiles == null || imageFiles.length == 0) {
            throw new IllegalArgumentException("No image files found in directory");
        }

        // Sort files by name (important for screenshots in order)
        Arrays.sort(imageFiles, Comparator.comparing(File::getName));

        try (PDDocument document = new PDDocument()) {

            for (File imageFile : imageFiles) {

                PDImageXObject pdImage = PDImageXObject.createFromFile(
                        imageFile.getAbsolutePath(), document);

                PDPage page = new PDPage(
                        new PDRectangle(pdImage.getWidth(), pdImage.getHeight()));

                document.addPage(page);

                try (PDPageContentStream contentStream =
                             new PDPageContentStream(document, page)) {

                    contentStream.drawImage(pdImage, 0, 0,
                            pdImage.getWidth(), pdImage.getHeight());
                }
            }

            File outputFile = new File(outputDir, outputFileName);
            document.save(outputFile);
        }

        System.out.println("PDF created successfully at: "
                + new File(outputDir, outputFileName).getAbsolutePath());
    }
}

