package com.itextpdf.text.xml.simpleparser;

import com.itextpdf.text.error_messages.MessageLocalization;
import com.itextpdf.text.xml.XMLUtil;
import com.itextpdf.text.xml.simpleparser.handler.HTMLNewLineHandler;
import com.itextpdf.text.xml.simpleparser.handler.NeverNewLineHandler;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.Stack;

public final class SimpleXMLParser {
    private static final int ATTRIBUTE_EQUAL = 13;
    private static final int ATTRIBUTE_KEY = 12;
    private static final int ATTRIBUTE_VALUE = 14;
    private static final int CDATA = 7;
    private static final int COMMENT = 8;
    private static final int ENTITY = 10;
    private static final int EXAMIN_TAG = 3;
    private static final int IN_CLOSETAG = 5;
    private static final int PI = 9;
    private static final int QUOTE = 11;
    private static final int SINGLE_TAG = 6;
    private static final int TAG_ENCOUNTERED = 2;
    private static final int TAG_EXAMINED = 4;
    private static final int TEXT = 1;
    private static final int UNKNOWN = 0;
    private String attributekey = null;
    private HashMap<String, String> attributes = null;
    private String attributevalue = null;
    private int character = 0;
    private int columns = 0;
    private final SimpleXMLDocHandlerComment comment;
    private final SimpleXMLDocHandler doc;
    private final StringBuffer entity = new StringBuffer();
    private boolean eol = false;
    private final boolean html;
    private int lines = 1;
    private int nested = 0;
    private NewLineHandler newLineHandler;
    private boolean nowhite = false;
    private int previousCharacter = -1;
    private int quoteCharacter = 34;
    private final Stack<Integer> stack;
    private int state;
    private String tag = null;
    private final StringBuffer text = new StringBuffer();

    private SimpleXMLParser(SimpleXMLDocHandler simpleXMLDocHandler, SimpleXMLDocHandlerComment simpleXMLDocHandlerComment, boolean z) {
        this.doc = simpleXMLDocHandler;
        this.comment = simpleXMLDocHandlerComment;
        this.html = z;
        if (z) {
            this.newLineHandler = new HTMLNewLineHandler();
        } else {
            this.newLineHandler = new NeverNewLineHandler();
        }
        this.stack = new Stack<>();
        this.state = z ? 1 : 0;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:250:0x0012, code lost:
        continue;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void go(java.io.Reader r15) throws java.io.IOException {
        /*
            r14 = this;
            boolean r0 = r15 instanceof java.io.BufferedReader
            if (r0 == 0) goto L_0x0007
            java.io.BufferedReader r15 = (java.io.BufferedReader) r15
            goto L_0x000d
        L_0x0007:
            java.io.BufferedReader r0 = new java.io.BufferedReader
            r0.<init>(r15)
            r15 = r0
        L_0x000d:
            com.itextpdf.text.xml.simpleparser.SimpleXMLDocHandler r0 = r14.doc
            r0.startDocument()
        L_0x0012:
            int r0 = r14.previousCharacter
            r1 = -1
            if (r0 != r1) goto L_0x001e
            int r0 = r15.read()
            r14.character = r0
            goto L_0x0024
        L_0x001e:
            int r0 = r14.previousCharacter
            r14.character = r0
            r14.previousCharacter = r1
        L_0x0024:
            int r0 = r14.character
            r2 = 1
            r3 = 0
            if (r0 != r1) goto L_0x004b
            boolean r15 = r14.html
            if (r15 == 0) goto L_0x003f
            boolean r15 = r14.html
            if (r15 == 0) goto L_0x0039
            int r15 = r14.state
            if (r15 != r2) goto L_0x0039
            r14.flush()
        L_0x0039:
            com.itextpdf.text.xml.simpleparser.SimpleXMLDocHandler r15 = r14.doc
            r15.endDocument()
            goto L_0x004a
        L_0x003f:
            java.lang.String r15 = "missing.end.tag"
            java.lang.Object[] r0 = new java.lang.Object[r3]
            java.lang.String r15 = com.itextpdf.text.error_messages.MessageLocalization.getComposedMessage((java.lang.String) r15, (java.lang.Object[]) r0)
            r14.throwException(r15)
        L_0x004a:
            return
        L_0x004b:
            int r0 = r14.character
            r1 = 10
            if (r0 != r1) goto L_0x0058
            boolean r0 = r14.eol
            if (r0 == 0) goto L_0x0058
            r14.eol = r3
            goto L_0x0012
        L_0x0058:
            boolean r0 = r14.eol
            r4 = 13
            if (r0 == 0) goto L_0x0061
            r14.eol = r3
            goto L_0x0082
        L_0x0061:
            int r0 = r14.character
            if (r0 != r1) goto L_0x006d
            int r0 = r14.lines
            int r0 = r0 + r2
            r14.lines = r0
            r14.columns = r3
            goto L_0x0082
        L_0x006d:
            int r0 = r14.character
            if (r0 != r4) goto L_0x007d
            r14.eol = r2
            r14.character = r1
            int r0 = r14.lines
            int r0 = r0 + r2
            r14.lines = r0
            r14.columns = r3
            goto L_0x0082
        L_0x007d:
            int r0 = r14.columns
            int r0 = r0 + r2
            r14.columns = r0
        L_0x0082:
            int r0 = r14.state
            r5 = 14
            r6 = 61
            r7 = 4
            r8 = 6
            r9 = 2
            r10 = 38
            r11 = 47
            r12 = 32
            r13 = 62
            switch(r0) {
                case 0: goto L_0x04da;
                case 1: goto L_0x0464;
                case 2: goto L_0x043c;
                case 3: goto L_0x03af;
                case 4: goto L_0x037c;
                case 5: goto L_0x034e;
                case 6: goto L_0x0319;
                case 7: goto L_0x02e6;
                case 8: goto L_0x02b3;
                case 9: goto L_0x02a1;
                case 10: goto L_0x0212;
                case 11: goto L_0x0191;
                case 12: goto L_0x0151;
                case 13: goto L_0x00f2;
                case 14: goto L_0x0098;
                default: goto L_0x0096;
            }
        L_0x0096:
            goto L_0x0012
        L_0x0098:
            int r0 = r14.character
            r1 = 34
            r4 = 11
            if (r0 == r1) goto L_0x00ea
            int r0 = r14.character
            r1 = 39
            if (r0 != r1) goto L_0x00a7
            goto L_0x00ea
        L_0x00a7:
            int r0 = r14.character
            char r0 = (char) r0
            boolean r0 = java.lang.Character.isWhitespace(r0)
            if (r0 == 0) goto L_0x00b2
            goto L_0x0012
        L_0x00b2:
            boolean r0 = r14.html
            if (r0 == 0) goto L_0x00cb
            int r0 = r14.character
            if (r0 != r13) goto L_0x00cb
            r14.flush()
            r14.processTag(r2)
            r14.initTag()
            int r0 = r14.restoreState()
            r14.state = r0
            goto L_0x0012
        L_0x00cb:
            boolean r0 = r14.html
            if (r0 == 0) goto L_0x00dd
            java.lang.StringBuffer r0 = r14.text
            int r1 = r14.character
            char r1 = (char) r1
            r0.append(r1)
            r14.quoteCharacter = r12
            r14.state = r4
            goto L_0x0012
        L_0x00dd:
            java.lang.String r0 = "error.in.attribute.processing"
            java.lang.Object[] r1 = new java.lang.Object[r3]
            java.lang.String r0 = com.itextpdf.text.error_messages.MessageLocalization.getComposedMessage((java.lang.String) r0, (java.lang.Object[]) r1)
            r14.throwException(r0)
            goto L_0x0012
        L_0x00ea:
            int r0 = r14.character
            r14.quoteCharacter = r0
            r14.state = r4
            goto L_0x0012
        L_0x00f2:
            int r0 = r14.character
            if (r0 != r6) goto L_0x00fa
            r14.state = r5
            goto L_0x0012
        L_0x00fa:
            int r0 = r14.character
            char r0 = (char) r0
            boolean r0 = java.lang.Character.isWhitespace(r0)
            if (r0 == 0) goto L_0x0105
            goto L_0x0012
        L_0x0105:
            boolean r0 = r14.html
            if (r0 == 0) goto L_0x0120
            int r0 = r14.character
            if (r0 != r13) goto L_0x0120
            java.lang.StringBuffer r0 = r14.text
            r0.setLength(r3)
            r14.processTag(r2)
            r14.initTag()
            int r0 = r14.restoreState()
            r14.state = r0
            goto L_0x0012
        L_0x0120:
            boolean r0 = r14.html
            if (r0 == 0) goto L_0x012f
            int r0 = r14.character
            if (r0 != r11) goto L_0x012f
            r14.flush()
            r14.state = r8
            goto L_0x0012
        L_0x012f:
            boolean r0 = r14.html
            if (r0 == 0) goto L_0x0144
            r14.flush()
            java.lang.StringBuffer r0 = r14.text
            int r1 = r14.character
            char r1 = (char) r1
            r0.append(r1)
            r0 = 12
            r14.state = r0
            goto L_0x0012
        L_0x0144:
            java.lang.String r0 = "error.in.attribute.processing"
            java.lang.Object[] r1 = new java.lang.Object[r3]
            java.lang.String r0 = com.itextpdf.text.error_messages.MessageLocalization.getComposedMessage((java.lang.String) r0, (java.lang.Object[]) r1)
            r14.throwException(r0)
            goto L_0x0012
        L_0x0151:
            int r0 = r14.character
            char r0 = (char) r0
            boolean r0 = java.lang.Character.isWhitespace(r0)
            if (r0 == 0) goto L_0x0161
            r14.flush()
            r14.state = r4
            goto L_0x0012
        L_0x0161:
            int r0 = r14.character
            if (r0 != r6) goto L_0x016c
            r14.flush()
            r14.state = r5
            goto L_0x0012
        L_0x016c:
            boolean r0 = r14.html
            if (r0 == 0) goto L_0x0187
            int r0 = r14.character
            if (r0 != r13) goto L_0x0187
            java.lang.StringBuffer r0 = r14.text
            r0.setLength(r3)
            r14.processTag(r2)
            r14.initTag()
            int r0 = r14.restoreState()
            r14.state = r0
            goto L_0x0012
        L_0x0187:
            java.lang.StringBuffer r0 = r14.text
            int r1 = r14.character
            char r1 = (char) r1
            r0.append(r1)
            goto L_0x0012
        L_0x0191:
            boolean r0 = r14.html
            if (r0 == 0) goto L_0x01ae
            int r0 = r14.quoteCharacter
            if (r0 != r12) goto L_0x01ae
            int r0 = r14.character
            if (r0 != r13) goto L_0x01ae
            r14.flush()
            r14.processTag(r2)
            r14.initTag()
            int r0 = r14.restoreState()
            r14.state = r0
            goto L_0x0012
        L_0x01ae:
            boolean r0 = r14.html
            if (r0 == 0) goto L_0x01c6
            int r0 = r14.quoteCharacter
            if (r0 != r12) goto L_0x01c6
            int r0 = r14.character
            char r0 = (char) r0
            boolean r0 = java.lang.Character.isWhitespace(r0)
            if (r0 == 0) goto L_0x01c6
            r14.flush()
            r14.state = r7
            goto L_0x0012
        L_0x01c6:
            boolean r0 = r14.html
            if (r0 == 0) goto L_0x01d8
            int r0 = r14.quoteCharacter
            if (r0 != r12) goto L_0x01d8
            java.lang.StringBuffer r0 = r14.text
            int r1 = r14.character
            char r1 = (char) r1
            r0.append(r1)
            goto L_0x0012
        L_0x01d8:
            int r0 = r14.character
            int r2 = r14.quoteCharacter
            if (r0 != r2) goto L_0x01e5
            r14.flush()
            r14.state = r7
            goto L_0x0012
        L_0x01e5:
            java.lang.String r0 = " \r\n\t"
            int r2 = r14.character
            int r0 = r0.indexOf(r2)
            if (r0 < 0) goto L_0x01f6
            java.lang.StringBuffer r0 = r14.text
            r0.append(r12)
            goto L_0x0012
        L_0x01f6:
            int r0 = r14.character
            if (r0 != r10) goto L_0x0208
            int r0 = r14.state
            r14.saveState(r0)
            r14.state = r1
            java.lang.StringBuffer r0 = r14.entity
            r0.setLength(r3)
            goto L_0x0012
        L_0x0208:
            java.lang.StringBuffer r0 = r14.text
            int r1 = r14.character
            char r1 = (char) r1
            r0.append(r1)
            goto L_0x0012
        L_0x0212:
            int r0 = r14.character
            r1 = 59
            if (r0 != r1) goto L_0x0245
            int r0 = r14.restoreState()
            r14.state = r0
            java.lang.StringBuffer r0 = r14.entity
            java.lang.String r0 = r0.toString()
            java.lang.StringBuffer r1 = r14.entity
            r1.setLength(r3)
            char r1 = com.itextpdf.text.xml.simpleparser.EntitiesToUnicode.decodeEntity(r0)
            if (r1 != 0) goto L_0x023e
            java.lang.StringBuffer r1 = r14.text
            r1.append(r10)
            r1.append(r0)
            r0 = 59
            r1.append(r0)
            goto L_0x0012
        L_0x023e:
            java.lang.StringBuffer r0 = r14.text
            r0.append(r1)
            goto L_0x0012
        L_0x0245:
            int r0 = r14.character
            r1 = 35
            if (r0 == r1) goto L_0x026f
            int r0 = r14.character
            r1 = 48
            if (r0 < r1) goto L_0x0257
            int r0 = r14.character
            r1 = 57
            if (r0 <= r1) goto L_0x026f
        L_0x0257:
            int r0 = r14.character
            r1 = 97
            if (r0 < r1) goto L_0x0263
            int r0 = r14.character
            r1 = 122(0x7a, float:1.71E-43)
            if (r0 <= r1) goto L_0x026f
        L_0x0263:
            int r0 = r14.character
            r1 = 65
            if (r0 < r1) goto L_0x0278
            int r0 = r14.character
            r1 = 90
            if (r0 > r1) goto L_0x0278
        L_0x026f:
            java.lang.StringBuffer r0 = r14.entity
            int r0 = r0.length()
            r1 = 7
            if (r0 < r1) goto L_0x0297
        L_0x0278:
            int r0 = r14.restoreState()
            r14.state = r0
            int r0 = r14.character
            r14.previousCharacter = r0
            java.lang.StringBuffer r0 = r14.text
            r0.append(r10)
            java.lang.StringBuffer r1 = r14.entity
            java.lang.String r1 = r1.toString()
            r0.append(r1)
            java.lang.StringBuffer r0 = r14.entity
            r0.setLength(r3)
            goto L_0x0012
        L_0x0297:
            java.lang.StringBuffer r0 = r14.entity
            int r1 = r14.character
            char r1 = (char) r1
            r0.append(r1)
            goto L_0x0012
        L_0x02a1:
            int r0 = r14.character
            if (r0 != r13) goto L_0x0012
            int r0 = r14.restoreState()
            r14.state = r0
            int r0 = r14.state
            if (r0 != r2) goto L_0x0012
            r14.state = r3
            goto L_0x0012
        L_0x02b3:
            int r0 = r14.character
            if (r0 != r13) goto L_0x02dc
            java.lang.StringBuffer r0 = r14.text
            java.lang.String r0 = r0.toString()
            java.lang.String r1 = "--"
            boolean r0 = r0.endsWith(r1)
            if (r0 == 0) goto L_0x02dc
            java.lang.StringBuffer r0 = r14.text
            java.lang.StringBuffer r1 = r14.text
            int r1 = r1.length()
            int r1 = r1 - r9
            r0.setLength(r1)
            r14.flush()
            int r0 = r14.restoreState()
            r14.state = r0
            goto L_0x0012
        L_0x02dc:
            java.lang.StringBuffer r0 = r14.text
            int r1 = r14.character
            char r1 = (char) r1
            r0.append(r1)
            goto L_0x0012
        L_0x02e6:
            int r0 = r14.character
            if (r0 != r13) goto L_0x030f
            java.lang.StringBuffer r0 = r14.text
            java.lang.String r0 = r0.toString()
            java.lang.String r1 = "]]"
            boolean r0 = r0.endsWith(r1)
            if (r0 == 0) goto L_0x030f
            java.lang.StringBuffer r0 = r14.text
            java.lang.StringBuffer r1 = r14.text
            int r1 = r1.length()
            int r1 = r1 - r9
            r0.setLength(r1)
            r14.flush()
            int r0 = r14.restoreState()
            r14.state = r0
            goto L_0x0012
        L_0x030f:
            java.lang.StringBuffer r0 = r14.text
            int r1 = r14.character
            char r1 = (char) r1
            r0.append(r1)
            goto L_0x0012
        L_0x0319:
            int r0 = r14.character
            if (r0 == r13) goto L_0x032c
            java.lang.String r0 = "expected.gt.for.tag.lt.1.gt"
            java.lang.Object[] r1 = new java.lang.Object[r2]
            java.lang.String r4 = r14.tag
            r1[r3] = r4
            java.lang.String r0 = com.itextpdf.text.error_messages.MessageLocalization.getComposedMessage((java.lang.String) r0, (java.lang.Object[]) r1)
            r14.throwException(r0)
        L_0x032c:
            r14.doTag()
            r14.processTag(r2)
            r14.processTag(r3)
            r14.initTag()
            boolean r0 = r14.html
            if (r0 != 0) goto L_0x0346
            int r0 = r14.nested
            if (r0 != 0) goto L_0x0346
            com.itextpdf.text.xml.simpleparser.SimpleXMLDocHandler r15 = r14.doc
            r15.endDocument()
            return
        L_0x0346:
            int r0 = r14.restoreState()
            r14.state = r0
            goto L_0x0012
        L_0x034e:
            int r0 = r14.character
            if (r0 != r13) goto L_0x0369
            r14.doTag()
            r14.processTag(r3)
            boolean r0 = r14.html
            if (r0 != 0) goto L_0x0361
            int r0 = r14.nested
            if (r0 != 0) goto L_0x0361
            return
        L_0x0361:
            int r0 = r14.restoreState()
            r14.state = r0
            goto L_0x0012
        L_0x0369:
            int r0 = r14.character
            char r0 = (char) r0
            boolean r0 = java.lang.Character.isWhitespace(r0)
            if (r0 != 0) goto L_0x0012
            java.lang.StringBuffer r0 = r14.text
            int r1 = r14.character
            char r1 = (char) r1
            r0.append(r1)
            goto L_0x0012
        L_0x037c:
            int r0 = r14.character
            if (r0 != r13) goto L_0x038e
            r14.processTag(r2)
            r14.initTag()
            int r0 = r14.restoreState()
            r14.state = r0
            goto L_0x0012
        L_0x038e:
            int r0 = r14.character
            if (r0 != r11) goto L_0x0396
            r14.state = r8
            goto L_0x0012
        L_0x0396:
            int r0 = r14.character
            char r0 = (char) r0
            boolean r0 = java.lang.Character.isWhitespace(r0)
            if (r0 == 0) goto L_0x03a1
            goto L_0x0012
        L_0x03a1:
            java.lang.StringBuffer r0 = r14.text
            int r1 = r14.character
            char r1 = (char) r1
            r0.append(r1)
            r0 = 12
            r14.state = r0
            goto L_0x0012
        L_0x03af:
            int r0 = r14.character
            if (r0 != r13) goto L_0x03c4
            r14.doTag()
            r14.processTag(r2)
            r14.initTag()
            int r0 = r14.restoreState()
            r14.state = r0
            goto L_0x0012
        L_0x03c4:
            int r0 = r14.character
            if (r0 != r11) goto L_0x03cc
            r14.state = r8
            goto L_0x0012
        L_0x03cc:
            int r0 = r14.character
            r1 = 45
            if (r0 != r1) goto L_0x03e9
            java.lang.StringBuffer r0 = r14.text
            java.lang.String r0 = r0.toString()
            java.lang.String r1 = "!-"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x03e9
            r14.flush()
            r0 = 8
            r14.state = r0
            goto L_0x0012
        L_0x03e9:
            int r0 = r14.character
            r1 = 91
            if (r0 != r1) goto L_0x0405
            java.lang.StringBuffer r0 = r14.text
            java.lang.String r0 = r0.toString()
            java.lang.String r1 = "![CDATA"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0405
            r14.flush()
            r0 = 7
            r14.state = r0
            goto L_0x0012
        L_0x0405:
            int r0 = r14.character
            r1 = 69
            if (r0 != r1) goto L_0x0422
            java.lang.StringBuffer r0 = r14.text
            java.lang.String r0 = r0.toString()
            java.lang.String r1 = "!DOCTYP"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0422
            r14.flush()
            r0 = 9
            r14.state = r0
            goto L_0x0012
        L_0x0422:
            int r0 = r14.character
            char r0 = (char) r0
            boolean r0 = java.lang.Character.isWhitespace(r0)
            if (r0 == 0) goto L_0x0432
            r14.doTag()
            r14.state = r7
            goto L_0x0012
        L_0x0432:
            java.lang.StringBuffer r0 = r14.text
            int r1 = r14.character
            char r1 = (char) r1
            r0.append(r1)
            goto L_0x0012
        L_0x043c:
            r14.initTag()
            int r0 = r14.character
            if (r0 != r11) goto L_0x0448
            r0 = 5
            r14.state = r0
            goto L_0x0012
        L_0x0448:
            int r0 = r14.character
            r1 = 63
            if (r0 != r1) goto L_0x0457
            r14.restoreState()
            r0 = 9
            r14.state = r0
            goto L_0x0012
        L_0x0457:
            java.lang.StringBuffer r0 = r14.text
            int r1 = r14.character
            char r1 = (char) r1
            r0.append(r1)
            r0 = 3
            r14.state = r0
            goto L_0x0012
        L_0x0464:
            int r0 = r14.character
            r4 = 60
            if (r0 != r4) goto L_0x0476
            r14.flush()
            int r0 = r14.state
            r14.saveState(r0)
            r14.state = r9
            goto L_0x0012
        L_0x0476:
            int r0 = r14.character
            if (r0 != r10) goto L_0x048a
            int r0 = r14.state
            r14.saveState(r0)
            java.lang.StringBuffer r0 = r14.entity
            r0.setLength(r3)
            r14.state = r1
            r14.nowhite = r2
            goto L_0x0012
        L_0x048a:
            int r0 = r14.character
            if (r0 != r12) goto L_0x04af
            boolean r0 = r14.html
            if (r0 == 0) goto L_0x049f
            boolean r0 = r14.nowhite
            if (r0 == 0) goto L_0x049f
            java.lang.StringBuffer r0 = r14.text
            r0.append(r12)
            r14.nowhite = r3
            goto L_0x0012
        L_0x049f:
            boolean r0 = r14.nowhite
            if (r0 == 0) goto L_0x04ab
            java.lang.StringBuffer r0 = r14.text
            int r1 = r14.character
            char r1 = (char) r1
            r0.append(r1)
        L_0x04ab:
            r14.nowhite = r3
            goto L_0x0012
        L_0x04af:
            int r0 = r14.character
            char r0 = (char) r0
            boolean r0 = java.lang.Character.isWhitespace(r0)
            if (r0 == 0) goto L_0x04ce
            boolean r0 = r14.html
            if (r0 == 0) goto L_0x04be
            goto L_0x0012
        L_0x04be:
            boolean r0 = r14.nowhite
            if (r0 == 0) goto L_0x04ca
            java.lang.StringBuffer r0 = r14.text
            int r1 = r14.character
            char r1 = (char) r1
            r0.append(r1)
        L_0x04ca:
            r14.nowhite = r3
            goto L_0x0012
        L_0x04ce:
            java.lang.StringBuffer r0 = r14.text
            int r1 = r14.character
            char r1 = (char) r1
            r0.append(r1)
            r14.nowhite = r2
            goto L_0x0012
        L_0x04da:
            int r0 = r14.character
            r1 = 60
            if (r0 != r1) goto L_0x0012
            r14.saveState(r2)
            r14.state = r9
            goto L_0x0012
        */
        throw new UnsupportedOperationException("Method not decompiled: com.itextpdf.text.xml.simpleparser.SimpleXMLParser.go(java.io.Reader):void");
    }

    private int restoreState() {
        if (!this.stack.empty()) {
            return this.stack.pop().intValue();
        }
        return 0;
    }

    private void saveState(int i) {
        this.stack.push(Integer.valueOf(i));
    }

    private void flush() {
        switch (this.state) {
            case 1:
            case 7:
                if (this.text.length() > 0) {
                    this.doc.text(this.text.toString());
                    break;
                }
                break;
            case 8:
                if (this.comment != null) {
                    this.comment.comment(this.text.toString());
                    break;
                }
                break;
            case 11:
            case 14:
                this.attributevalue = this.text.toString();
                this.attributes.put(this.attributekey, this.attributevalue);
                break;
            case 12:
                this.attributekey = this.text.toString();
                if (this.html) {
                    this.attributekey = this.attributekey.toLowerCase();
                    break;
                }
                break;
        }
        this.text.setLength(0);
    }

    private void initTag() {
        this.tag = null;
        this.attributes = new HashMap<>();
    }

    private void doTag() {
        if (this.tag == null) {
            this.tag = this.text.toString();
        }
        if (this.html) {
            this.tag = this.tag.toLowerCase();
        }
        this.text.setLength(0);
    }

    private void processTag(boolean z) {
        if (z) {
            this.nested++;
            this.doc.startElement(this.tag, this.attributes);
            return;
        }
        if (this.newLineHandler.isNewLineTag(this.tag)) {
            this.nowhite = false;
        }
        this.nested--;
        this.doc.endElement(this.tag);
    }

    private void throwException(String str) throws IOException {
        throw new IOException(MessageLocalization.getComposedMessage("1.near.line.2.column.3", str, String.valueOf(this.lines), String.valueOf(this.columns)));
    }

    public static void parse(SimpleXMLDocHandler simpleXMLDocHandler, SimpleXMLDocHandlerComment simpleXMLDocHandlerComment, Reader reader, boolean z) throws IOException {
        new SimpleXMLParser(simpleXMLDocHandler, simpleXMLDocHandlerComment, z).go(reader);
    }

    public static void parse(SimpleXMLDocHandler simpleXMLDocHandler, InputStream inputStream) throws IOException {
        String declaredEncoding;
        byte[] bArr = new byte[4];
        if (inputStream.read(bArr) != 4) {
            throw new IOException(MessageLocalization.getComposedMessage("insufficient.length", new Object[0]));
        }
        String encodingName = XMLUtil.getEncodingName(bArr);
        String str = null;
        if (encodingName.equals("UTF-8")) {
            StringBuffer stringBuffer = new StringBuffer();
            while (true) {
                int read = inputStream.read();
                if (read == -1 || read == 62) {
                    str = stringBuffer.toString();
                } else {
                    stringBuffer.append((char) read);
                }
            }
            str = stringBuffer.toString();
        } else if (encodingName.equals("CP037")) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            while (true) {
                int read2 = inputStream.read();
                if (read2 == -1 || read2 == 110) {
                    str = new String(byteArrayOutputStream.toByteArray(), "CP037");
                } else {
                    byteArrayOutputStream.write(read2);
                }
            }
            str = new String(byteArrayOutputStream.toByteArray(), "CP037");
        }
        if (!(str == null || (declaredEncoding = getDeclaredEncoding(str)) == null)) {
            encodingName = declaredEncoding;
        }
        parse(simpleXMLDocHandler, (Reader) new InputStreamReader(inputStream, IanaEncodings.getJavaEncoding(encodingName)));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:22:0x003a, code lost:
        r3 = r3 + 1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String getDeclaredEncoding(java.lang.String r5) {
        /*
            r0 = 0
            if (r5 != 0) goto L_0x0004
            return r0
        L_0x0004:
            java.lang.String r1 = "encoding"
            int r1 = r5.indexOf(r1)
            if (r1 >= 0) goto L_0x000d
            return r0
        L_0x000d:
            r2 = 34
            int r3 = r5.indexOf(r2, r1)
            r4 = 39
            int r1 = r5.indexOf(r4, r1)
            if (r3 != r1) goto L_0x001c
            return r0
        L_0x001c:
            if (r3 >= 0) goto L_0x0020
            if (r1 > 0) goto L_0x0024
        L_0x0020:
            if (r1 <= 0) goto L_0x0032
            if (r1 >= r3) goto L_0x0032
        L_0x0024:
            int r1 = r1 + 1
            int r2 = r5.indexOf(r4, r1)
            if (r2 >= 0) goto L_0x002d
            return r0
        L_0x002d:
            java.lang.String r5 = r5.substring(r1, r2)
            return r5
        L_0x0032:
            if (r1 >= 0) goto L_0x0036
            if (r3 > 0) goto L_0x003a
        L_0x0036:
            if (r3 <= 0) goto L_0x0048
            if (r3 >= r1) goto L_0x0048
        L_0x003a:
            int r3 = r3 + 1
            int r1 = r5.indexOf(r2, r3)
            if (r1 >= 0) goto L_0x0043
            return r0
        L_0x0043:
            java.lang.String r5 = r5.substring(r3, r1)
            return r5
        L_0x0048:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.itextpdf.text.xml.simpleparser.SimpleXMLParser.getDeclaredEncoding(java.lang.String):java.lang.String");
    }

    public static void parse(SimpleXMLDocHandler simpleXMLDocHandler, Reader reader) throws IOException {
        parse(simpleXMLDocHandler, (SimpleXMLDocHandlerComment) null, reader, false);
    }

    @Deprecated
    public static String escapeXML(String str, boolean z) {
        return XMLUtil.escapeXML(str, z);
    }
}
