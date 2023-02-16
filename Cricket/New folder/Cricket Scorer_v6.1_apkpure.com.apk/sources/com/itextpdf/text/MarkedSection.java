package com.itextpdf.text;

import com.itextpdf.text.api.Indentable;
import java.util.Collection;
import java.util.Iterator;

@Deprecated
public class MarkedSection extends MarkedObject implements Indentable {
    protected MarkedObject title = null;

    public MarkedSection(Section section) {
        if (section.title != null) {
            this.title = new MarkedObject(section.title);
            section.setTitle((Paragraph) null);
        }
        this.element = section;
    }

    public void add(int i, Element element) {
        ((Section) this.element).add(i, element);
    }

    public boolean add(Element element) {
        return ((Section) this.element).add(element);
    }

    public boolean process(ElementListener elementListener) {
        try {
            Iterator it = ((Section) this.element).iterator();
            while (it.hasNext()) {
                elementListener.add((Element) it.next());
            }
            return true;
        } catch (DocumentException unused) {
            return false;
        }
    }

    public boolean addAll(Collection<? extends Element> collection) {
        return ((Section) this.element).addAll(collection);
    }

    public MarkedSection addSection(float f, int i) {
        MarkedSection addMarkedSection = ((Section) this.element).addMarkedSection();
        addMarkedSection.setIndentation(f);
        addMarkedSection.setNumberDepth(i);
        return addMarkedSection;
    }

    public MarkedSection addSection(float f) {
        MarkedSection addMarkedSection = ((Section) this.element).addMarkedSection();
        addMarkedSection.setIndentation(f);
        return addMarkedSection;
    }

    public MarkedSection addSection(int i) {
        MarkedSection addMarkedSection = ((Section) this.element).addMarkedSection();
        addMarkedSection.setNumberDepth(i);
        return addMarkedSection;
    }

    public MarkedSection addSection() {
        return ((Section) this.element).addMarkedSection();
    }

    public void setTitle(MarkedObject markedObject) {
        if (markedObject.element instanceof Paragraph) {
            this.title = markedObject;
        }
    }

    public MarkedObject getTitle() {
        MarkedObject markedObject = new MarkedObject(Section.constructTitle((Paragraph) this.title.element, ((Section) this.element).numbers, ((Section) this.element).numberDepth, ((Section) this.element).numberStyle));
        markedObject.markupAttributes = this.title.markupAttributes;
        return markedObject;
    }

    public void setNumberDepth(int i) {
        ((Section) this.element).setNumberDepth(i);
    }

    public void setIndentationLeft(float f) {
        ((Section) this.element).setIndentationLeft(f);
    }

    public void setIndentationRight(float f) {
        ((Section) this.element).setIndentationRight(f);
    }

    public void setIndentation(float f) {
        ((Section) this.element).setIndentation(f);
    }

    public void setBookmarkOpen(boolean z) {
        ((Section) this.element).setBookmarkOpen(z);
    }

    public void setTriggerNewPage(boolean z) {
        ((Section) this.element).setTriggerNewPage(z);
    }

    public void setBookmarkTitle(String str) {
        ((Section) this.element).setBookmarkTitle(str);
    }

    public void newPage() {
        ((Section) this.element).newPage();
    }

    public float getIndentationLeft() {
        return ((Section) this.element).getIndentationLeft();
    }

    public float getIndentationRight() {
        return ((Section) this.element).getIndentationRight();
    }
}
