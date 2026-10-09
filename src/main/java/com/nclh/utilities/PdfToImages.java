package com.nclh.utilities;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PdfToImages {

    public static void convertAllPdfsInInputDirectory() throws IOException {

        // Project root directory
        String projectRoot = System.getProperty("user.dir");

        // Input and Output directories
        File inputDir = new File(projectRoot + File.separator + "Downloads"
                + File.separator + "inputDirPath");

        File outputDir = new File(projectRoot + File.separator + "Downloads"
                + File.separator + "outputDirPath");

        // Create output directory if it doesn't exist
        if (!outputDir.exists()) {
            outputDir.mkdirs();
        }

        // Filter only PDF files
        File[] pdfFiles = inputDir.listFiles((dir, name) -> name.toLowerCase().endsWith(".pdf"));

        if (pdfFiles == null || pdfFiles.length == 0) {
            System.out.println("No PDF files found in input directory.");
            return;
        }

        for (File pdfFile : pdfFiles) {
            convertSinglePdf(pdfFile, outputDir);
        }

        System.out.println("All PDFs converted successfully!");
    }

    private static void convertSinglePdf(File pdfFile, File outputDir) throws IOException {

        try (PDDocument document = PDDocument.load(pdfFile)) {

            PDFRenderer pdfRenderer = new PDFRenderer(document);

            int numberOfPages = document.getNumberOfPages();

            // Create subfolder with PDF name
            String pdfName = pdfFile.getName().replace(".pdf", "");
            File pdfOutputDir = new File(outputDir, pdfName);

            if (!pdfOutputDir.exists()) {
                pdfOutputDir.mkdirs();
            }

            for (int page = 0; page < numberOfPages; page++) {

                BufferedImage image = pdfRenderer.renderImageWithDPI(page, 300);

                File outputFile = new File(pdfOutputDir,
                        "page_" + (page + 1) + ".png");

                ImageIO.write(image, "PNG", outputFile);
            }
        }

        System.out.println("Converted: " + pdfFile.getName());
    }

    public static void main(String[] args) throws IOException {
        convertAllPdfsInInputDirectory();
    }
}
