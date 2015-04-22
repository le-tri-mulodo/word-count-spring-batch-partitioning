/**
 * 
 */
package com.mulodo.slave.processor;

import java.io.File;
import java.io.FileInputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;
import org.springframework.batch.item.ItemProcessor;

import com.mulodo.slave.pojo.FileInfo;

/**
 * @author TriLe
 */
public class Pdf2TxtItemProcessor implements ItemProcessor<FileInfo, String>
{
    private static final Log LOG = LogFactory.getLog(Pdf2TxtItemProcessor.class);

    private String pdfDir = "";

    @Override
    public String process(FileInfo fileInfo) throws Exception
    {
        long startTime = System.currentTimeMillis();
        File pdfFile = new File(pdfDir, fileInfo.getFilePath());

        if (!pdfFile.isFile()) {
            throw new IllegalArgumentException("Input path is not file");
        }

        PDFParser parser = null;
        PDDocument pdDoc = null;
        COSDocument cosDoc = null;
        PDFTextStripper pdfStripper = null;
        String parsedText = null;

        try {
            parser = new PDFParser(new FileInputStream(pdfFile));
            parser.parse();
            cosDoc = parser.getDocument();
            pdfStripper = new PDFTextStripper();
            pdDoc = new PDDocument(cosDoc);
            parsedText = pdfStripper.getText(pdDoc);

        } catch (Exception e) {

            throw e;
        } finally {
            LOG.info("Read PDF file [" + pdfFile.getName() + "] success. Duration: "
                    + (System.currentTimeMillis() - startTime));

            try {
                if (cosDoc != null)
                    cosDoc.close();
                if (pdDoc != null)
                    pdDoc.close();
            } catch (Exception e) {
                throw e;
            }
        }

        return parsedText;
    }

    /**
     * @param pdfDir
     *            the pdfDir to set
     */
    public void setPdfDir(String pdfDir)
    {
        this.pdfDir = pdfDir;
    }
}
