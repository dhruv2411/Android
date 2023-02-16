package com.itextpdf.text.pdf.parser;

import com.itextpdf.text.pdf.PdfReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public final class PdfTextExtractor {
    private PdfTextExtractor() {
    }

    public static String getTextFromPage(PdfReader pdfReader, int i, TextExtractionStrategy textExtractionStrategy, Map<String, ContentOperator> map) throws IOException {
        return ((TextExtractionStrategy) new PdfReaderContentParser(pdfReader).processContent(i, textExtractionStrategy, map)).getResultantText();
    }

    public static String getTextFromPage(PdfReader pdfReader, int i, TextExtractionStrategy textExtractionStrategy) throws IOException {
        return getTextFromPage(pdfReader, i, textExtractionStrategy, new HashMap());
    }

    public static String getTextFromPage(PdfReader pdfReader, int i) throws IOException {
        return getTextFromPage(pdfReader, i, new LocationTextExtractionStrategy());
    }
}
