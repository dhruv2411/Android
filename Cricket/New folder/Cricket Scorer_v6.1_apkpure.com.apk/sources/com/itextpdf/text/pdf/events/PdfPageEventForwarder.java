package com.itextpdf.text.pdf.events;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPageEvent;
import com.itextpdf.text.pdf.PdfWriter;
import java.util.ArrayList;
import java.util.Iterator;

public class PdfPageEventForwarder implements PdfPageEvent {
    protected ArrayList<PdfPageEvent> events = new ArrayList<>();

    public void addPageEvent(PdfPageEvent pdfPageEvent) {
        this.events.add(pdfPageEvent);
    }

    public void onOpenDocument(PdfWriter pdfWriter, Document document) {
        Iterator<PdfPageEvent> it = this.events.iterator();
        while (it.hasNext()) {
            it.next().onOpenDocument(pdfWriter, document);
        }
    }

    public void onStartPage(PdfWriter pdfWriter, Document document) {
        Iterator<PdfPageEvent> it = this.events.iterator();
        while (it.hasNext()) {
            it.next().onStartPage(pdfWriter, document);
        }
    }

    public void onEndPage(PdfWriter pdfWriter, Document document) {
        Iterator<PdfPageEvent> it = this.events.iterator();
        while (it.hasNext()) {
            it.next().onEndPage(pdfWriter, document);
        }
    }

    public void onCloseDocument(PdfWriter pdfWriter, Document document) {
        Iterator<PdfPageEvent> it = this.events.iterator();
        while (it.hasNext()) {
            it.next().onCloseDocument(pdfWriter, document);
        }
    }

    public void onParagraph(PdfWriter pdfWriter, Document document, float f) {
        Iterator<PdfPageEvent> it = this.events.iterator();
        while (it.hasNext()) {
            it.next().onParagraph(pdfWriter, document, f);
        }
    }

    public void onParagraphEnd(PdfWriter pdfWriter, Document document, float f) {
        Iterator<PdfPageEvent> it = this.events.iterator();
        while (it.hasNext()) {
            it.next().onParagraphEnd(pdfWriter, document, f);
        }
    }

    public void onChapter(PdfWriter pdfWriter, Document document, float f, Paragraph paragraph) {
        Iterator<PdfPageEvent> it = this.events.iterator();
        while (it.hasNext()) {
            it.next().onChapter(pdfWriter, document, f, paragraph);
        }
    }

    public void onChapterEnd(PdfWriter pdfWriter, Document document, float f) {
        Iterator<PdfPageEvent> it = this.events.iterator();
        while (it.hasNext()) {
            it.next().onChapterEnd(pdfWriter, document, f);
        }
    }

    public void onSection(PdfWriter pdfWriter, Document document, float f, int i, Paragraph paragraph) {
        Iterator<PdfPageEvent> it = this.events.iterator();
        while (it.hasNext()) {
            it.next().onSection(pdfWriter, document, f, i, paragraph);
        }
    }

    public void onSectionEnd(PdfWriter pdfWriter, Document document, float f) {
        Iterator<PdfPageEvent> it = this.events.iterator();
        while (it.hasNext()) {
            it.next().onSectionEnd(pdfWriter, document, f);
        }
    }

    public void onGenericTag(PdfWriter pdfWriter, Document document, Rectangle rectangle, String str) {
        Iterator<PdfPageEvent> it = this.events.iterator();
        while (it.hasNext()) {
            it.next().onGenericTag(pdfWriter, document, rectangle, str);
        }
    }
}
