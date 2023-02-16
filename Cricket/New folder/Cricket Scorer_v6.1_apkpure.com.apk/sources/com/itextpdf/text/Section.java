package com.itextpdf.text;

import com.itextpdf.text.api.Indentable;
import com.itextpdf.text.error_messages.MessageLocalization;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfObject;
import com.itextpdf.text.pdf.interfaces.IAccessibleElement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class Section extends ArrayList<Element> implements TextElementArray, LargeElement, Indentable, IAccessibleElement {
    public static final int NUMBERSTYLE_DOTTED = 0;
    public static final int NUMBERSTYLE_DOTTED_WITHOUT_FINAL_DOT = 1;
    private static final long serialVersionUID = 3324172577544748043L;
    protected boolean addedCompletely;
    protected boolean bookmarkOpen;
    protected String bookmarkTitle;
    protected boolean complete;
    protected float indentation;
    protected float indentationLeft;
    protected float indentationRight;
    protected boolean notAddedYet;
    protected int numberDepth;
    protected int numberStyle;
    protected ArrayList<Integer> numbers;
    protected int subsections;
    protected Paragraph title;
    protected boolean triggerNewPage;

    public boolean isContent() {
        return true;
    }

    public boolean isInline() {
        return false;
    }

    public boolean isNestable() {
        return false;
    }

    public int type() {
        return 13;
    }

    protected Section() {
        this.numberStyle = 0;
        this.bookmarkOpen = true;
        this.triggerNewPage = false;
        this.subsections = 0;
        this.numbers = null;
        this.complete = true;
        this.addedCompletely = false;
        this.notAddedYet = true;
        this.title = new Paragraph();
        this.numberDepth = 1;
        Paragraph paragraph = this.title;
        paragraph.setRole(new PdfName("H" + this.numberDepth));
    }

    protected Section(Paragraph paragraph, int i) {
        this.numberStyle = 0;
        this.bookmarkOpen = true;
        this.triggerNewPage = false;
        this.subsections = 0;
        this.numbers = null;
        this.complete = true;
        this.addedCompletely = false;
        this.notAddedYet = true;
        this.numberDepth = i;
        this.title = paragraph;
        if (paragraph != null) {
            paragraph.setRole(new PdfName("H" + i));
        }
    }

    public boolean process(ElementListener elementListener) {
        try {
            Iterator it = iterator();
            while (it.hasNext()) {
                elementListener.add((Element) it.next());
            }
            return true;
        } catch (DocumentException unused) {
            return false;
        }
    }

    public boolean isChapter() {
        return type() == 16;
    }

    public boolean isSection() {
        return type() == 13;
    }

    public List<Chunk> getChunks() {
        ArrayList arrayList = new ArrayList();
        Iterator it = iterator();
        while (it.hasNext()) {
            arrayList.addAll(((Element) it.next()).getChunks());
        }
        return arrayList;
    }

    public void add(int i, Element element) {
        if (isAddedCompletely()) {
            throw new IllegalStateException(MessageLocalization.getComposedMessage("this.largeelement.has.already.been.added.to.the.document", new Object[0]));
        }
        try {
            if (element.isNestable()) {
                super.add(i, element);
            } else {
                throw new ClassCastException(MessageLocalization.getComposedMessage("you.can.t.add.a.1.to.a.section", element.getClass().getName()));
            }
        } catch (ClassCastException e) {
            throw new ClassCastException(MessageLocalization.getComposedMessage("insertion.of.illegal.element.1", e.getMessage()));
        }
    }

    public boolean add(Element element) {
        if (isAddedCompletely()) {
            throw new IllegalStateException(MessageLocalization.getComposedMessage("this.largeelement.has.already.been.added.to.the.document", new Object[0]));
        }
        try {
            if (element.type() == 13) {
                Section section = (Section) element;
                int i = this.subsections + 1;
                this.subsections = i;
                section.setNumbers(i, this.numbers);
                return super.add(section);
            } else if ((element instanceof MarkedSection) && ((MarkedObject) element).element.type() == 13) {
                MarkedSection markedSection = (MarkedSection) element;
                int i2 = this.subsections + 1;
                this.subsections = i2;
                ((Section) markedSection.element).setNumbers(i2, this.numbers);
                return super.add(markedSection);
            } else if (element.isNestable()) {
                return super.add(element);
            } else {
                throw new ClassCastException(MessageLocalization.getComposedMessage("you.can.t.add.a.1.to.a.section", element.getClass().getName()));
            }
        } catch (ClassCastException e) {
            throw new ClassCastException(MessageLocalization.getComposedMessage("insertion.of.illegal.element.1", e.getMessage()));
        }
    }

    public boolean addAll(Collection<? extends Element> collection) {
        if (collection.size() == 0) {
            return false;
        }
        for (Element add : collection) {
            add(add);
        }
        return true;
    }

    public Section addSection(float f, Paragraph paragraph, int i) {
        if (isAddedCompletely()) {
            throw new IllegalStateException(MessageLocalization.getComposedMessage("this.largeelement.has.already.been.added.to.the.document", new Object[0]));
        }
        Section section = new Section(paragraph, i);
        section.setIndentation(f);
        add((Element) section);
        return section;
    }

    public Section addSection(float f, Paragraph paragraph) {
        return addSection(f, paragraph, this.numberDepth + 1);
    }

    public Section addSection(Paragraph paragraph, int i) {
        return addSection(0.0f, paragraph, i);
    }

    /* access modifiers changed from: protected */
    public MarkedSection addMarkedSection() {
        MarkedSection markedSection = new MarkedSection(new Section((Paragraph) null, this.numberDepth + 1));
        add((Element) markedSection);
        return markedSection;
    }

    public Section addSection(Paragraph paragraph) {
        return addSection(0.0f, paragraph, this.numberDepth + 1);
    }

    public Section addSection(float f, String str, int i) {
        return addSection(f, new Paragraph(str), i);
    }

    public Section addSection(String str, int i) {
        return addSection(new Paragraph(str), i);
    }

    public Section addSection(float f, String str) {
        return addSection(f, new Paragraph(str));
    }

    public Section addSection(String str) {
        return addSection(new Paragraph(str));
    }

    public void setTitle(Paragraph paragraph) {
        this.title = paragraph;
    }

    public Paragraph getTitle() {
        return constructTitle(this.title, this.numbers, this.numberDepth, this.numberStyle);
    }

    public static Paragraph constructTitle(Paragraph paragraph, ArrayList<Integer> arrayList, int i, int i2) {
        if (paragraph == null) {
            return null;
        }
        int min = Math.min(arrayList.size(), i);
        if (min < 1) {
            return paragraph;
        }
        StringBuffer stringBuffer = new StringBuffer(" ");
        for (int i3 = 0; i3 < min; i3++) {
            stringBuffer.insert(0, ".");
            stringBuffer.insert(0, arrayList.get(i3).intValue());
        }
        if (i2 == 1) {
            stringBuffer.deleteCharAt(stringBuffer.length() - 2);
        }
        Paragraph paragraph2 = new Paragraph((Phrase) paragraph);
        paragraph2.add(0, (Element) new Chunk(stringBuffer.toString(), paragraph.getFont()));
        return paragraph2;
    }

    public void setNumberDepth(int i) {
        this.numberDepth = i;
    }

    public int getNumberDepth() {
        return this.numberDepth;
    }

    public void setNumberStyle(int i) {
        this.numberStyle = i;
    }

    public int getNumberStyle() {
        return this.numberStyle;
    }

    public void setIndentationLeft(float f) {
        this.indentationLeft = f;
    }

    public float getIndentationLeft() {
        return this.indentationLeft;
    }

    public void setIndentationRight(float f) {
        this.indentationRight = f;
    }

    public float getIndentationRight() {
        return this.indentationRight;
    }

    public void setIndentation(float f) {
        this.indentation = f;
    }

    public float getIndentation() {
        return this.indentation;
    }

    public void setBookmarkOpen(boolean z) {
        this.bookmarkOpen = z;
    }

    public boolean isBookmarkOpen() {
        return this.bookmarkOpen;
    }

    public void setTriggerNewPage(boolean z) {
        this.triggerNewPage = z;
    }

    public boolean isTriggerNewPage() {
        return this.triggerNewPage && this.notAddedYet;
    }

    public void setBookmarkTitle(String str) {
        this.bookmarkTitle = str;
    }

    public Paragraph getBookmarkTitle() {
        if (this.bookmarkTitle == null) {
            return getTitle();
        }
        return new Paragraph(this.bookmarkTitle);
    }

    public void setChapterNumber(int i) {
        this.numbers.set(this.numbers.size() - 1, Integer.valueOf(i));
        Iterator it = iterator();
        while (it.hasNext()) {
            Object next = it.next();
            if (next instanceof Section) {
                ((Section) next).setChapterNumber(i);
            }
        }
    }

    public int getDepth() {
        return this.numbers.size();
    }

    private void setNumbers(int i, ArrayList<Integer> arrayList) {
        this.numbers = new ArrayList<>();
        this.numbers.add(Integer.valueOf(i));
        this.numbers.addAll(arrayList);
    }

    public boolean isNotAddedYet() {
        return this.notAddedYet;
    }

    public void setNotAddedYet(boolean z) {
        this.notAddedYet = z;
    }

    /* access modifiers changed from: protected */
    public boolean isAddedCompletely() {
        return this.addedCompletely;
    }

    /* access modifiers changed from: protected */
    public void setAddedCompletely(boolean z) {
        this.addedCompletely = z;
    }

    public void flushContent() {
        setNotAddedYet(false);
        this.title = null;
        Iterator it = iterator();
        while (it.hasNext()) {
            Element element = (Element) it.next();
            if (element instanceof Section) {
                Section section = (Section) element;
                if (section.isComplete() || size() != 1) {
                    section.setAddedCompletely(true);
                } else {
                    section.flushContent();
                    return;
                }
            }
            it.remove();
        }
    }

    public boolean isComplete() {
        return this.complete;
    }

    public void setComplete(boolean z) {
        this.complete = z;
    }

    public void newPage() {
        add((Element) Chunk.NEXTPAGE);
    }

    public PdfObject getAccessibleAttribute(PdfName pdfName) {
        return this.title.getAccessibleAttribute(pdfName);
    }

    public void setAccessibleAttribute(PdfName pdfName, PdfObject pdfObject) {
        this.title.setAccessibleAttribute(pdfName, pdfObject);
    }

    public HashMap<PdfName, PdfObject> getAccessibleAttributes() {
        return this.title.getAccessibleAttributes();
    }

    public PdfName getRole() {
        return this.title.getRole();
    }

    public void setRole(PdfName pdfName) {
        this.title.setRole(pdfName);
    }

    public AccessibleElementId getId() {
        return this.title.getId();
    }

    public void setId(AccessibleElementId accessibleElementId) {
        this.title.setId(accessibleElementId);
    }
}
