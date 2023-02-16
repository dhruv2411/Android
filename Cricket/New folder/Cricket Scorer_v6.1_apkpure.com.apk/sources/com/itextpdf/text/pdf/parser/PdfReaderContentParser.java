package com.itextpdf.text.pdf.parser;

import com.itextpdf.text.pdf.PdfDictionary;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class PdfReaderContentParser {
    private final PdfReader reader;

    public PdfReaderContentParser(PdfReader pdfReader) {
        this.reader = pdfReader;
    }

    public <E extends RenderListener> E processContent(int i, E e, Map<String, ContentOperator> map) throws IOException {
        PdfDictionary asDict = this.reader.getPageN(i).getAsDict(PdfName.RESOURCES);
        PdfContentStreamProcessor pdfContentStreamProcessor = new PdfContentStreamProcessor(e);
        for (Map.Entry next : map.entrySet()) {
            pdfContentStreamProcessor.registerContentOperator((String) next.getKey(), (ContentOperator) next.getValue());
        }
        pdfContentStreamProcessor.processContent(ContentByteUtils.getContentBytesForPage(this.reader, i), asDict);
        return e;
    }

    public <E extends RenderListener> E processContent(int i, E e) throws IOException {
        return processContent(i, e, new HashMap());
    }
}
