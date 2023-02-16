package com.itextpdf.text.pdf;

import com.itextpdf.text.AccessibleElementId;
import com.itextpdf.text.error_messages.MessageLocalization;
import com.itextpdf.text.pdf.interfaces.IAccessibleElement;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class PdfArtifact implements IAccessibleElement {
    private static final HashSet<String> allowedArtifactTypes = new HashSet<>(Arrays.asList(new String[]{"Pagination", "Layout", "Page", "Background"}));
    protected HashMap<PdfName, PdfObject> accessibleAttributes = null;
    protected AccessibleElementId id = new AccessibleElementId();
    protected PdfName role = PdfName.ARTIFACT;

    public enum ArtifactType {
        PAGINATION,
        LAYOUT,
        PAGE,
        BACKGROUND
    }

    public boolean isInline() {
        return true;
    }

    public void setRole(PdfName pdfName) {
    }

    public PdfObject getAccessibleAttribute(PdfName pdfName) {
        if (this.accessibleAttributes != null) {
            return this.accessibleAttributes.get(pdfName);
        }
        return null;
    }

    public void setAccessibleAttribute(PdfName pdfName, PdfObject pdfObject) {
        if (this.accessibleAttributes == null) {
            this.accessibleAttributes = new HashMap<>();
        }
        this.accessibleAttributes.put(pdfName, pdfObject);
    }

    public HashMap<PdfName, PdfObject> getAccessibleAttributes() {
        return this.accessibleAttributes;
    }

    public PdfName getRole() {
        return this.role;
    }

    public AccessibleElementId getId() {
        return this.id;
    }

    public void setId(AccessibleElementId accessibleElementId) {
        this.id = accessibleElementId;
    }

    public PdfString getType() {
        if (this.accessibleAttributes == null) {
            return null;
        }
        return (PdfString) this.accessibleAttributes.get(PdfName.TYPE);
    }

    public void setType(PdfString pdfString) {
        if (!allowedArtifactTypes.contains(pdfString.toString())) {
            throw new IllegalArgumentException(MessageLocalization.getComposedMessage("the.artifact.type.1.is.invalid", pdfString));
        } else {
            setAccessibleAttribute(PdfName.TYPE, pdfString);
        }
    }

    public void setType(ArtifactType artifactType) {
        PdfString pdfString;
        switch (artifactType) {
            case BACKGROUND:
                pdfString = new PdfString("Background");
                break;
            case LAYOUT:
                pdfString = new PdfString("Layout");
                break;
            case PAGE:
                pdfString = new PdfString("Page");
                break;
            case PAGINATION:
                pdfString = new PdfString("Pagination");
                break;
            default:
                pdfString = null;
                break;
        }
        setAccessibleAttribute(PdfName.TYPE, pdfString);
    }

    public PdfArray getBBox() {
        if (this.accessibleAttributes == null) {
            return null;
        }
        return (PdfArray) this.accessibleAttributes.get(PdfName.BBOX);
    }

    public void setBBox(PdfArray pdfArray) {
        setAccessibleAttribute(PdfName.BBOX, pdfArray);
    }

    public PdfArray getAttached() {
        if (this.accessibleAttributes == null) {
            return null;
        }
        return (PdfArray) this.accessibleAttributes.get(PdfName.ATTACHED);
    }

    public void setAttached(PdfArray pdfArray) {
        setAccessibleAttribute(PdfName.ATTACHED, pdfArray);
    }
}
