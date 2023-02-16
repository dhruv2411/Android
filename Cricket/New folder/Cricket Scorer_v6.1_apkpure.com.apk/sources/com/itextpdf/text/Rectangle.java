package com.itextpdf.text;

import com.itextpdf.text.pdf.GrayColor;
import java.util.ArrayList;
import java.util.List;

public class Rectangle implements Element {
    public static final int BOTTOM = 2;
    public static final int BOX = 15;
    public static final int LEFT = 4;
    public static final int NO_BORDER = 0;
    public static final int RIGHT = 8;
    public static final int TOP = 1;
    public static final int UNDEFINED = -1;
    protected BaseColor backgroundColor;
    protected int border;
    protected BaseColor borderColor;
    protected BaseColor borderColorBottom;
    protected BaseColor borderColorLeft;
    protected BaseColor borderColorRight;
    protected BaseColor borderColorTop;
    protected float borderWidth;
    protected float borderWidthBottom;
    protected float borderWidthLeft;
    protected float borderWidthRight;
    protected float borderWidthTop;
    protected float llx;
    protected float lly;
    protected int rotation;
    protected float urx;
    protected float ury;
    protected boolean useVariableBorders;

    public boolean isContent() {
        return true;
    }

    public boolean isNestable() {
        return false;
    }

    public int type() {
        return 30;
    }

    public Rectangle(float f, float f2, float f3, float f4) {
        this.rotation = 0;
        this.backgroundColor = null;
        this.border = -1;
        this.useVariableBorders = false;
        this.borderWidth = -1.0f;
        this.borderWidthLeft = -1.0f;
        this.borderWidthRight = -1.0f;
        this.borderWidthTop = -1.0f;
        this.borderWidthBottom = -1.0f;
        this.borderColor = null;
        this.borderColorLeft = null;
        this.borderColorRight = null;
        this.borderColorTop = null;
        this.borderColorBottom = null;
        this.llx = f;
        this.lly = f2;
        this.urx = f3;
        this.ury = f4;
    }

    public Rectangle(float f, float f2, float f3, float f4, int i) {
        this(f, f2, f3, f4);
        setRotation(i);
    }

    public Rectangle(float f, float f2) {
        this(0.0f, 0.0f, f, f2);
    }

    public Rectangle(float f, float f2, int i) {
        this(0.0f, 0.0f, f, f2, i);
    }

    public Rectangle(Rectangle rectangle) {
        this(rectangle.llx, rectangle.lly, rectangle.urx, rectangle.ury);
        cloneNonPositionParameters(rectangle);
    }

    public Rectangle(com.itextpdf.awt.geom.Rectangle rectangle) {
        this((float) rectangle.getX(), (float) rectangle.getY(), (float) (rectangle.getX() + rectangle.getWidth()), (float) (rectangle.getY() + rectangle.getHeight()));
    }

    public boolean process(ElementListener elementListener) {
        try {
            return elementListener.add(this);
        } catch (DocumentException unused) {
            return false;
        }
    }

    public List<Chunk> getChunks() {
        return new ArrayList();
    }

    public void setLeft(float f) {
        this.llx = f;
    }

    public float getLeft() {
        return this.llx;
    }

    public float getLeft(float f) {
        return this.llx + f;
    }

    public void setRight(float f) {
        this.urx = f;
    }

    public float getRight() {
        return this.urx;
    }

    public float getRight(float f) {
        return this.urx - f;
    }

    public float getWidth() {
        return this.urx - this.llx;
    }

    public void setTop(float f) {
        this.ury = f;
    }

    public float getTop() {
        return this.ury;
    }

    public float getTop(float f) {
        return this.ury - f;
    }

    public void setBottom(float f) {
        this.lly = f;
    }

    public float getBottom() {
        return this.lly;
    }

    public float getBottom(float f) {
        return this.lly + f;
    }

    public float getHeight() {
        return this.ury - this.lly;
    }

    public void normalize() {
        if (this.llx > this.urx) {
            float f = this.llx;
            this.llx = this.urx;
            this.urx = f;
        }
        if (this.lly > this.ury) {
            float f2 = this.lly;
            this.lly = this.ury;
            this.ury = f2;
        }
    }

    public int getRotation() {
        return this.rotation;
    }

    public void setRotation(int i) {
        this.rotation = i % 360;
        int i2 = this.rotation;
        if (i2 != 90 && i2 != 180 && i2 != 270) {
            this.rotation = 0;
        }
    }

    public Rectangle rotate() {
        Rectangle rectangle = new Rectangle(this.lly, this.llx, this.ury, this.urx);
        rectangle.setRotation(this.rotation + 90);
        return rectangle;
    }

    public BaseColor getBackgroundColor() {
        return this.backgroundColor;
    }

    public void setBackgroundColor(BaseColor baseColor) {
        this.backgroundColor = baseColor;
    }

    public float getGrayFill() {
        if (this.backgroundColor instanceof GrayColor) {
            return ((GrayColor) this.backgroundColor).getGray();
        }
        return 0.0f;
    }

    public void setGrayFill(float f) {
        this.backgroundColor = new GrayColor(f);
    }

    public int getBorder() {
        return this.border;
    }

    public boolean hasBorders() {
        switch (this.border) {
            case -1:
            case 0:
                return false;
            default:
                if (this.borderWidth > 0.0f || this.borderWidthLeft > 0.0f || this.borderWidthRight > 0.0f || this.borderWidthTop > 0.0f || this.borderWidthBottom > 0.0f) {
                    return true;
                }
                return false;
        }
    }

    public boolean hasBorder(int i) {
        if (this.border != -1 && (this.border & i) == i) {
            return true;
        }
        return false;
    }

    public void setBorder(int i) {
        this.border = i;
    }

    public boolean isUseVariableBorders() {
        return this.useVariableBorders;
    }

    public void setUseVariableBorders(boolean z) {
        this.useVariableBorders = z;
    }

    public void enableBorderSide(int i) {
        if (this.border == -1) {
            this.border = 0;
        }
        this.border = i | this.border;
    }

    public void disableBorderSide(int i) {
        if (this.border == -1) {
            this.border = 0;
        }
        this.border = (i ^ -1) & this.border;
    }

    public float getBorderWidth() {
        return this.borderWidth;
    }

    public void setBorderWidth(float f) {
        this.borderWidth = f;
    }

    private float getVariableBorderWidth(float f, int i) {
        if ((i & this.border) != 0) {
            return f != -1.0f ? f : this.borderWidth;
        }
        return 0.0f;
    }

    private void updateBorderBasedOnWidth(float f, int i) {
        this.useVariableBorders = true;
        if (f > 0.0f) {
            enableBorderSide(i);
        } else {
            disableBorderSide(i);
        }
    }

    public float getBorderWidthLeft() {
        return getVariableBorderWidth(this.borderWidthLeft, 4);
    }

    public void setBorderWidthLeft(float f) {
        this.borderWidthLeft = f;
        updateBorderBasedOnWidth(f, 4);
    }

    public float getBorderWidthRight() {
        return getVariableBorderWidth(this.borderWidthRight, 8);
    }

    public void setBorderWidthRight(float f) {
        this.borderWidthRight = f;
        updateBorderBasedOnWidth(f, 8);
    }

    public float getBorderWidthTop() {
        return getVariableBorderWidth(this.borderWidthTop, 1);
    }

    public void setBorderWidthTop(float f) {
        this.borderWidthTop = f;
        updateBorderBasedOnWidth(f, 1);
    }

    public float getBorderWidthBottom() {
        return getVariableBorderWidth(this.borderWidthBottom, 2);
    }

    public void setBorderWidthBottom(float f) {
        this.borderWidthBottom = f;
        updateBorderBasedOnWidth(f, 2);
    }

    public BaseColor getBorderColor() {
        return this.borderColor;
    }

    public void setBorderColor(BaseColor baseColor) {
        this.borderColor = baseColor;
    }

    public BaseColor getBorderColorLeft() {
        if (this.borderColorLeft == null) {
            return this.borderColor;
        }
        return this.borderColorLeft;
    }

    public void setBorderColorLeft(BaseColor baseColor) {
        this.borderColorLeft = baseColor;
    }

    public BaseColor getBorderColorRight() {
        if (this.borderColorRight == null) {
            return this.borderColor;
        }
        return this.borderColorRight;
    }

    public void setBorderColorRight(BaseColor baseColor) {
        this.borderColorRight = baseColor;
    }

    public BaseColor getBorderColorTop() {
        if (this.borderColorTop == null) {
            return this.borderColor;
        }
        return this.borderColorTop;
    }

    public void setBorderColorTop(BaseColor baseColor) {
        this.borderColorTop = baseColor;
    }

    public BaseColor getBorderColorBottom() {
        if (this.borderColorBottom == null) {
            return this.borderColor;
        }
        return this.borderColorBottom;
    }

    public void setBorderColorBottom(BaseColor baseColor) {
        this.borderColorBottom = baseColor;
    }

    public Rectangle rectangle(float f, float f2) {
        Rectangle rectangle = new Rectangle(this);
        if (getTop() > f) {
            rectangle.setTop(f);
            rectangle.disableBorderSide(1);
        }
        if (getBottom() < f2) {
            rectangle.setBottom(f2);
            rectangle.disableBorderSide(2);
        }
        return rectangle;
    }

    public void cloneNonPositionParameters(Rectangle rectangle) {
        this.rotation = rectangle.rotation;
        this.backgroundColor = rectangle.backgroundColor;
        this.border = rectangle.border;
        this.useVariableBorders = rectangle.useVariableBorders;
        this.borderWidth = rectangle.borderWidth;
        this.borderWidthLeft = rectangle.borderWidthLeft;
        this.borderWidthRight = rectangle.borderWidthRight;
        this.borderWidthTop = rectangle.borderWidthTop;
        this.borderWidthBottom = rectangle.borderWidthBottom;
        this.borderColor = rectangle.borderColor;
        this.borderColorLeft = rectangle.borderColorLeft;
        this.borderColorRight = rectangle.borderColorRight;
        this.borderColorTop = rectangle.borderColorTop;
        this.borderColorBottom = rectangle.borderColorBottom;
    }

    public void softCloneNonPositionParameters(Rectangle rectangle) {
        if (rectangle.rotation != 0) {
            this.rotation = rectangle.rotation;
        }
        if (rectangle.backgroundColor != null) {
            this.backgroundColor = rectangle.backgroundColor;
        }
        if (rectangle.border != -1) {
            this.border = rectangle.border;
        }
        if (this.useVariableBorders) {
            this.useVariableBorders = rectangle.useVariableBorders;
        }
        if (rectangle.borderWidth != -1.0f) {
            this.borderWidth = rectangle.borderWidth;
        }
        if (rectangle.borderWidthLeft != -1.0f) {
            this.borderWidthLeft = rectangle.borderWidthLeft;
        }
        if (rectangle.borderWidthRight != -1.0f) {
            this.borderWidthRight = rectangle.borderWidthRight;
        }
        if (rectangle.borderWidthTop != -1.0f) {
            this.borderWidthTop = rectangle.borderWidthTop;
        }
        if (rectangle.borderWidthBottom != -1.0f) {
            this.borderWidthBottom = rectangle.borderWidthBottom;
        }
        if (rectangle.borderColor != null) {
            this.borderColor = rectangle.borderColor;
        }
        if (rectangle.borderColorLeft != null) {
            this.borderColorLeft = rectangle.borderColorLeft;
        }
        if (rectangle.borderColorRight != null) {
            this.borderColorRight = rectangle.borderColorRight;
        }
        if (rectangle.borderColorTop != null) {
            this.borderColorTop = rectangle.borderColorTop;
        }
        if (rectangle.borderColorBottom != null) {
            this.borderColorBottom = rectangle.borderColorBottom;
        }
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("Rectangle: ");
        stringBuffer.append(getWidth());
        stringBuffer.append('x');
        stringBuffer.append(getHeight());
        stringBuffer.append(" (rot: ");
        stringBuffer.append(this.rotation);
        stringBuffer.append(" degrees)");
        return stringBuffer.toString();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Rectangle)) {
            return false;
        }
        Rectangle rectangle = (Rectangle) obj;
        if (rectangle.llx == this.llx && rectangle.lly == this.lly && rectangle.urx == this.urx && rectangle.ury == this.ury && rectangle.rotation == this.rotation) {
            return true;
        }
        return false;
    }
}
