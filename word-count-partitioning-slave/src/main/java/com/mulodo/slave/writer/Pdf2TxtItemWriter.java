package com.mulodo.slave.writer;

import java.io.File;
import java.io.FileInputStream;
import java.io.PrintWriter;
import java.util.List;

import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;
import org.springframework.batch.item.ItemWriter;

public class Pdf2TxtItemWriter implements ItemWriter<String>
{

    private String inputDir = "";
    private String outputDir = "";

    @Override
    public void write(List<? extends String> items) throws Exception
    {
        for (String filePath : items) {
            pdf2txt(filePath, inputDir, outputDir);
        }
    }

    private String pdf2txt(String path, String inputDir, String outputDir) throws Exception
    {
        File pdfFile = new File(inputDir, path);

        if (!pdfFile.isFile()) {
            throw new IllegalArgumentException("Input path is not file");
        }

        File txtFile = new File(outputDir, pdfFile.getName() + ".txt");

        PDFParser parser = null;
        PDDocument pdDoc = null;
        COSDocument cosDoc = null;
        PDFTextStripper pdfStripper = null;
        PrintWriter writer = null;

        try {
            parser = new PDFParser(new FileInputStream(pdfFile));
            parser.parse();
            cosDoc = parser.getDocument();
            pdfStripper = new PDFTextStripper();
            pdDoc = new PDDocument(cosDoc);
            String parsedText = pdfStripper.getText(pdDoc);

            writer = new PrintWriter(txtFile, "UTF-8");
            writer.append(parsedText);
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            try {
                if (cosDoc != null)
                    cosDoc.close();
                if (pdDoc != null)
                    pdDoc.close();
                if (writer != null)
                    writer.close();
            } catch (Exception e) {
                throw e;
            }
        }

        return txtFile.getAbsolutePath();
    }

    /**
     * @param inputDir
     *            the inputDir to set
     */
    public void setInputDir(String inputDir)
    {
        this.inputDir = inputDir;
    }

    /**
     * @param outputDir
     *            the outputDir to set
     */
    public void setOutputDir(String outputDir)
    {
        this.outputDir = outputDir;
    }

}
