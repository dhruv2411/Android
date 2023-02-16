package com.itextpdf.text.html.simpleparser;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.html.HtmlTags;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Deprecated
public class HTMLTagProcessors extends HashMap<String, HTMLTagProcessor> {
    public static final HTMLTagProcessor A = new HTMLTagProcessor() {
        public void startElement(HTMLWorker hTMLWorker, String str, Map<String, String> map) {
            hTMLWorker.updateChain(str, map);
            hTMLWorker.flushContent();
        }

        public void endElement(HTMLWorker hTMLWorker, String str) {
            hTMLWorker.processLink();
            hTMLWorker.updateChain(str);
        }
    };
    public static final HTMLTagProcessor BR = new HTMLTagProcessor() {
        public void endElement(HTMLWorker hTMLWorker, String str) {
        }

        public void startElement(HTMLWorker hTMLWorker, String str, Map<String, String> map) {
            hTMLWorker.newLine();
        }
    };
    public static final HTMLTagProcessor DIV = new HTMLTagProcessor() {
        public void startElement(HTMLWorker hTMLWorker, String str, Map<String, String> map) throws DocumentException {
            hTMLWorker.carriageReturn();
            hTMLWorker.updateChain(str, map);
        }

        public void endElement(HTMLWorker hTMLWorker, String str) throws DocumentException {
            hTMLWorker.carriageReturn();
            hTMLWorker.updateChain(str);
        }
    };
    public static final HTMLTagProcessor EM_STRONG_STRIKE_SUP_SUP = new HTMLTagProcessor() {
        public void startElement(HTMLWorker hTMLWorker, String str, Map<String, String> map) {
            String mapTag = mapTag(str);
            map.put(mapTag, (Object) null);
            hTMLWorker.updateChain(mapTag, map);
        }

        public void endElement(HTMLWorker hTMLWorker, String str) {
            hTMLWorker.updateChain(mapTag(str));
        }

        private String mapTag(String str) {
            if (HtmlTags.EM.equalsIgnoreCase(str)) {
                return HtmlTags.I;
            }
            if (HtmlTags.STRONG.equalsIgnoreCase(str)) {
                return HtmlTags.B;
            }
            return HtmlTags.STRIKE.equalsIgnoreCase(str) ? HtmlTags.S : str;
        }
    };
    public static final HTMLTagProcessor H = new HTMLTagProcessor() {
        public void startElement(HTMLWorker hTMLWorker, String str, Map<String, String> map) throws DocumentException {
            hTMLWorker.carriageReturn();
            if (!map.containsKey(HtmlTags.SIZE)) {
                map.put(HtmlTags.SIZE, Integer.toString(7 - Integer.parseInt(str.substring(1))));
            }
            hTMLWorker.updateChain(str, map);
        }

        public void endElement(HTMLWorker hTMLWorker, String str) throws DocumentException {
            hTMLWorker.carriageReturn();
            hTMLWorker.updateChain(str);
        }
    };
    public static final HTMLTagProcessor HR = new HTMLTagProcessor() {
        public void endElement(HTMLWorker hTMLWorker, String str) {
        }

        public void startElement(HTMLWorker hTMLWorker, String str, Map<String, String> map) throws DocumentException {
            hTMLWorker.carriageReturn();
            hTMLWorker.pushToStack(hTMLWorker.createLineSeparator(map));
        }
    };
    public static final HTMLTagProcessor IMG = new HTMLTagProcessor() {
        public void endElement(HTMLWorker hTMLWorker, String str) {
        }

        public void startElement(HTMLWorker hTMLWorker, String str, Map<String, String> map) throws DocumentException, IOException {
            hTMLWorker.updateChain(str, map);
            hTMLWorker.processImage(hTMLWorker.createImage(map), map);
            hTMLWorker.updateChain(str);
        }
    };
    public static final HTMLTagProcessor LI = new HTMLTagProcessor() {
        public void startElement(HTMLWorker hTMLWorker, String str, Map<String, String> map) throws DocumentException {
            hTMLWorker.carriageReturn();
            if (hTMLWorker.isPendingLI()) {
                hTMLWorker.endElement(str);
            }
            hTMLWorker.setSkipText(false);
            hTMLWorker.setPendingLI(true);
            hTMLWorker.updateChain(str, map);
            hTMLWorker.pushToStack(hTMLWorker.createListItem());
        }

        public void endElement(HTMLWorker hTMLWorker, String str) throws DocumentException {
            hTMLWorker.carriageReturn();
            hTMLWorker.setPendingLI(false);
            hTMLWorker.setSkipText(true);
            hTMLWorker.updateChain(str);
            hTMLWorker.processListItem();
        }
    };
    public static final HTMLTagProcessor PRE = new HTMLTagProcessor() {
        public void startElement(HTMLWorker hTMLWorker, String str, Map<String, String> map) throws DocumentException {
            hTMLWorker.carriageReturn();
            if (!map.containsKey(HtmlTags.FACE)) {
                map.put(HtmlTags.FACE, "Courier");
            }
            hTMLWorker.updateChain(str, map);
            hTMLWorker.setInsidePRE(true);
        }

        public void endElement(HTMLWorker hTMLWorker, String str) throws DocumentException {
            hTMLWorker.carriageReturn();
            hTMLWorker.updateChain(str);
            hTMLWorker.setInsidePRE(false);
        }
    };
    public static final HTMLTagProcessor SPAN = new HTMLTagProcessor() {
        public void startElement(HTMLWorker hTMLWorker, String str, Map<String, String> map) {
            hTMLWorker.updateChain(str, map);
        }

        public void endElement(HTMLWorker hTMLWorker, String str) {
            hTMLWorker.updateChain(str);
        }
    };
    public static final HTMLTagProcessor TABLE = new HTMLTagProcessor() {
        public void startElement(HTMLWorker hTMLWorker, String str, Map<String, String> map) throws DocumentException {
            hTMLWorker.carriageReturn();
            hTMLWorker.pushToStack(new TableWrapper(map));
            hTMLWorker.pushTableState();
            hTMLWorker.setPendingTD(false);
            hTMLWorker.setPendingTR(false);
            hTMLWorker.setSkipText(true);
            map.remove(HtmlTags.ALIGN);
            map.put(HtmlTags.COLSPAN, "1");
            map.put(HtmlTags.ROWSPAN, "1");
            hTMLWorker.updateChain(str, map);
        }

        public void endElement(HTMLWorker hTMLWorker, String str) throws DocumentException {
            hTMLWorker.carriageReturn();
            if (hTMLWorker.isPendingTR()) {
                hTMLWorker.endElement(HtmlTags.TR);
            }
            hTMLWorker.updateChain(str);
            hTMLWorker.processTable();
            hTMLWorker.popTableState();
            hTMLWorker.setSkipText(false);
        }
    };
    public static final HTMLTagProcessor TD = new HTMLTagProcessor() {
        public void startElement(HTMLWorker hTMLWorker, String str, Map<String, String> map) throws DocumentException {
            hTMLWorker.carriageReturn();
            if (hTMLWorker.isPendingTD()) {
                hTMLWorker.endElement(str);
            }
            hTMLWorker.setSkipText(false);
            hTMLWorker.setPendingTD(true);
            hTMLWorker.updateChain(HtmlTags.TD, map);
            hTMLWorker.pushToStack(hTMLWorker.createCell(str));
        }

        public void endElement(HTMLWorker hTMLWorker, String str) throws DocumentException {
            hTMLWorker.carriageReturn();
            hTMLWorker.setPendingTD(false);
            hTMLWorker.updateChain(HtmlTags.TD);
            hTMLWorker.setSkipText(true);
        }
    };
    public static final HTMLTagProcessor TR = new HTMLTagProcessor() {
        public void startElement(HTMLWorker hTMLWorker, String str, Map<String, String> map) throws DocumentException {
            hTMLWorker.carriageReturn();
            if (hTMLWorker.isPendingTR()) {
                hTMLWorker.endElement(str);
            }
            hTMLWorker.setSkipText(true);
            hTMLWorker.setPendingTR(true);
            hTMLWorker.updateChain(str, map);
        }

        public void endElement(HTMLWorker hTMLWorker, String str) throws DocumentException {
            hTMLWorker.carriageReturn();
            if (hTMLWorker.isPendingTD()) {
                hTMLWorker.endElement(HtmlTags.TD);
            }
            hTMLWorker.setPendingTR(false);
            hTMLWorker.updateChain(str);
            hTMLWorker.processRow();
            hTMLWorker.setSkipText(true);
        }
    };
    public static final HTMLTagProcessor UL_OL = new HTMLTagProcessor() {
        public void startElement(HTMLWorker hTMLWorker, String str, Map<String, String> map) throws DocumentException {
            hTMLWorker.carriageReturn();
            if (hTMLWorker.isPendingLI()) {
                hTMLWorker.endElement(HtmlTags.LI);
            }
            hTMLWorker.setSkipText(true);
            hTMLWorker.updateChain(str, map);
            hTMLWorker.pushToStack(hTMLWorker.createList(str));
        }

        public void endElement(HTMLWorker hTMLWorker, String str) throws DocumentException {
            hTMLWorker.carriageReturn();
            if (hTMLWorker.isPendingLI()) {
                hTMLWorker.endElement(HtmlTags.LI);
            }
            hTMLWorker.setSkipText(false);
            hTMLWorker.updateChain(str);
            hTMLWorker.processList();
        }
    };
    private static final long serialVersionUID = -959260811961222824L;

    public HTMLTagProcessors() {
        put("a", A);
        put(HtmlTags.B, EM_STRONG_STRIKE_SUP_SUP);
        put("body", DIV);
        put("br", BR);
        put("div", DIV);
        put(HtmlTags.EM, EM_STRONG_STRIKE_SUP_SUP);
        put(HtmlTags.FONT, SPAN);
        put(HtmlTags.H1, H);
        put(HtmlTags.H2, H);
        put(HtmlTags.H3, H);
        put(HtmlTags.H4, H);
        put(HtmlTags.H5, H);
        put(HtmlTags.H6, H);
        put(HtmlTags.HR, HR);
        put(HtmlTags.I, EM_STRONG_STRIKE_SUP_SUP);
        put(HtmlTags.IMG, IMG);
        put(HtmlTags.LI, LI);
        put(HtmlTags.OL, UL_OL);
        put("p", DIV);
        put(HtmlTags.PRE, PRE);
        put(HtmlTags.S, EM_STRONG_STRIKE_SUP_SUP);
        put("span", SPAN);
        put(HtmlTags.STRIKE, EM_STRONG_STRIKE_SUP_SUP);
        put(HtmlTags.STRONG, EM_STRONG_STRIKE_SUP_SUP);
        put(HtmlTags.SUB, EM_STRONG_STRIKE_SUP_SUP);
        put(HtmlTags.SUP, EM_STRONG_STRIKE_SUP_SUP);
        put(HtmlTags.TABLE, TABLE);
        put(HtmlTags.TD, TD);
        put(HtmlTags.TH, TD);
        put(HtmlTags.TR, TR);
        put(HtmlTags.U, EM_STRONG_STRIKE_SUP_SUP);
        put(HtmlTags.UL, UL_OL);
    }
}
