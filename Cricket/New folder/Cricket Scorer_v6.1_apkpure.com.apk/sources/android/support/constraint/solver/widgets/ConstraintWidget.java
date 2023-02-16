package android.support.constraint.solver.widgets;

import android.support.constraint.solver.ArrayRow;
import android.support.constraint.solver.Cache;
import android.support.constraint.solver.LinearSystem;
import android.support.constraint.solver.SolverVariable;
import android.support.constraint.solver.widgets.ConstraintAnchor;
import java.util.ArrayList;

public class ConstraintWidget {
    private static final boolean AUTOTAG_CENTER = false;
    public static final int CHAIN_PACKED = 2;
    public static final int CHAIN_SPREAD = 0;
    public static final int CHAIN_SPREAD_INSIDE = 1;
    public static float DEFAULT_BIAS = 0.5f;
    protected static final int DIRECT = 2;
    public static final int GONE = 8;
    public static final int HORIZONTAL = 0;
    public static final int INVISIBLE = 4;
    public static final int MATCH_CONSTRAINT_SPREAD = 0;
    public static final int MATCH_CONSTRAINT_WRAP = 1;
    protected static final int SOLVER = 1;
    public static final int UNKNOWN = -1;
    public static final int VERTICAL = 1;
    public static final int VISIBLE = 0;
    protected ArrayList<ConstraintAnchor> mAnchors;
    ConstraintAnchor mBaseline;
    int mBaselineDistance;
    ConstraintAnchor mBottom;
    boolean mBottomHasCentered;
    ConstraintAnchor mCenter;
    ConstraintAnchor mCenterX;
    ConstraintAnchor mCenterY;
    private Object mCompanionWidget;
    private int mContainerItemSkip;
    private String mDebugName;
    protected float mDimensionRatio;
    protected int mDimensionRatioSide;
    int mDistToBottom;
    int mDistToLeft;
    int mDistToRight;
    int mDistToTop;
    private int mDrawHeight;
    private int mDrawWidth;
    private int mDrawX;
    private int mDrawY;
    int mHeight;
    float mHorizontalBiasPercent;
    boolean mHorizontalChainFixedPosition;
    int mHorizontalChainStyle;
    DimensionBehaviour mHorizontalDimensionBehaviour;
    ConstraintWidget mHorizontalNextWidget;
    public int mHorizontalResolution;
    float mHorizontalWeight;
    boolean mHorizontalWrapVisited;
    ConstraintAnchor mLeft;
    boolean mLeftHasCentered;
    int mMatchConstraintDefaultHeight;
    int mMatchConstraintDefaultWidth;
    int mMatchConstraintMaxHeight;
    int mMatchConstraintMaxWidth;
    int mMatchConstraintMinHeight;
    int mMatchConstraintMinWidth;
    protected int mMinHeight;
    protected int mMinWidth;
    protected int mOffsetX;
    protected int mOffsetY;
    ConstraintWidget mParent;
    ConstraintAnchor mRight;
    boolean mRightHasCentered;
    private int mSolverBottom;
    private int mSolverLeft;
    private int mSolverRight;
    private int mSolverTop;
    ConstraintAnchor mTop;
    boolean mTopHasCentered;
    private String mType;
    float mVerticalBiasPercent;
    boolean mVerticalChainFixedPosition;
    int mVerticalChainStyle;
    DimensionBehaviour mVerticalDimensionBehaviour;
    ConstraintWidget mVerticalNextWidget;
    public int mVerticalResolution;
    float mVerticalWeight;
    boolean mVerticalWrapVisited;
    private int mVisibility;
    int mWidth;
    private int mWrapHeight;
    private int mWrapWidth;
    protected int mX;
    protected int mY;

    public enum ContentAlignment {
        BEGIN,
        MIDDLE,
        END,
        TOP,
        VERTICAL_MIDDLE,
        BOTTOM,
        LEFT,
        RIGHT
    }

    public enum DimensionBehaviour {
        FIXED,
        WRAP_CONTENT,
        MATCH_CONSTRAINT,
        MATCH_PARENT
    }

    public void connectedTo(ConstraintWidget constraintWidget) {
    }

    public void reset() {
        this.mLeft.reset();
        this.mTop.reset();
        this.mRight.reset();
        this.mBottom.reset();
        this.mBaseline.reset();
        this.mCenterX.reset();
        this.mCenterY.reset();
        this.mCenter.reset();
        this.mParent = null;
        this.mWidth = 0;
        this.mHeight = 0;
        this.mDimensionRatio = 0.0f;
        this.mDimensionRatioSide = -1;
        this.mX = 0;
        this.mY = 0;
        this.mDrawX = 0;
        this.mDrawY = 0;
        this.mDrawWidth = 0;
        this.mDrawHeight = 0;
        this.mOffsetX = 0;
        this.mOffsetY = 0;
        this.mBaselineDistance = 0;
        this.mMinWidth = 0;
        this.mMinHeight = 0;
        this.mWrapWidth = 0;
        this.mWrapHeight = 0;
        this.mHorizontalBiasPercent = DEFAULT_BIAS;
        this.mVerticalBiasPercent = DEFAULT_BIAS;
        this.mHorizontalDimensionBehaviour = DimensionBehaviour.FIXED;
        this.mVerticalDimensionBehaviour = DimensionBehaviour.FIXED;
        this.mCompanionWidget = null;
        this.mContainerItemSkip = 0;
        this.mVisibility = 0;
        this.mDebugName = null;
        this.mType = null;
        this.mHorizontalWrapVisited = false;
        this.mVerticalWrapVisited = false;
        this.mHorizontalChainStyle = 0;
        this.mVerticalChainStyle = 0;
        this.mHorizontalChainFixedPosition = false;
        this.mVerticalChainFixedPosition = false;
        this.mHorizontalWeight = 0.0f;
        this.mVerticalWeight = 0.0f;
        this.mHorizontalResolution = -1;
        this.mVerticalResolution = -1;
    }

    public ConstraintWidget() {
        this.mHorizontalResolution = -1;
        this.mVerticalResolution = -1;
        this.mMatchConstraintDefaultWidth = 0;
        this.mMatchConstraintDefaultHeight = 0;
        this.mMatchConstraintMinWidth = 0;
        this.mMatchConstraintMaxWidth = 0;
        this.mMatchConstraintMinHeight = 0;
        this.mMatchConstraintMaxHeight = 0;
        this.mLeft = new ConstraintAnchor(this, ConstraintAnchor.Type.LEFT);
        this.mTop = new ConstraintAnchor(this, ConstraintAnchor.Type.TOP);
        this.mRight = new ConstraintAnchor(this, ConstraintAnchor.Type.RIGHT);
        this.mBottom = new ConstraintAnchor(this, ConstraintAnchor.Type.BOTTOM);
        this.mBaseline = new ConstraintAnchor(this, ConstraintAnchor.Type.BASELINE);
        this.mCenterX = new ConstraintAnchor(this, ConstraintAnchor.Type.CENTER_X);
        this.mCenterY = new ConstraintAnchor(this, ConstraintAnchor.Type.CENTER_Y);
        this.mCenter = new ConstraintAnchor(this, ConstraintAnchor.Type.CENTER);
        this.mAnchors = new ArrayList<>();
        this.mParent = null;
        this.mWidth = 0;
        this.mHeight = 0;
        this.mDimensionRatio = 0.0f;
        this.mDimensionRatioSide = -1;
        this.mSolverLeft = 0;
        this.mSolverTop = 0;
        this.mSolverRight = 0;
        this.mSolverBottom = 0;
        this.mX = 0;
        this.mY = 0;
        this.mDrawX = 0;
        this.mDrawY = 0;
        this.mDrawWidth = 0;
        this.mDrawHeight = 0;
        this.mOffsetX = 0;
        this.mOffsetY = 0;
        this.mBaselineDistance = 0;
        this.mHorizontalBiasPercent = DEFAULT_BIAS;
        this.mVerticalBiasPercent = DEFAULT_BIAS;
        this.mHorizontalDimensionBehaviour = DimensionBehaviour.FIXED;
        this.mVerticalDimensionBehaviour = DimensionBehaviour.FIXED;
        this.mContainerItemSkip = 0;
        this.mVisibility = 0;
        this.mDebugName = null;
        this.mType = null;
        this.mHorizontalChainStyle = 0;
        this.mVerticalChainStyle = 0;
        this.mHorizontalWeight = 0.0f;
        this.mVerticalWeight = 0.0f;
        this.mHorizontalNextWidget = null;
        this.mVerticalNextWidget = null;
        addAnchors();
    }

    public ConstraintWidget(int i, int i2, int i3, int i4) {
        this.mHorizontalResolution = -1;
        this.mVerticalResolution = -1;
        this.mMatchConstraintDefaultWidth = 0;
        this.mMatchConstraintDefaultHeight = 0;
        this.mMatchConstraintMinWidth = 0;
        this.mMatchConstraintMaxWidth = 0;
        this.mMatchConstraintMinHeight = 0;
        this.mMatchConstraintMaxHeight = 0;
        this.mLeft = new ConstraintAnchor(this, ConstraintAnchor.Type.LEFT);
        this.mTop = new ConstraintAnchor(this, ConstraintAnchor.Type.TOP);
        this.mRight = new ConstraintAnchor(this, ConstraintAnchor.Type.RIGHT);
        this.mBottom = new ConstraintAnchor(this, ConstraintAnchor.Type.BOTTOM);
        this.mBaseline = new ConstraintAnchor(this, ConstraintAnchor.Type.BASELINE);
        this.mCenterX = new ConstraintAnchor(this, ConstraintAnchor.Type.CENTER_X);
        this.mCenterY = new ConstraintAnchor(this, ConstraintAnchor.Type.CENTER_Y);
        this.mCenter = new ConstraintAnchor(this, ConstraintAnchor.Type.CENTER);
        this.mAnchors = new ArrayList<>();
        this.mParent = null;
        this.mWidth = 0;
        this.mHeight = 0;
        this.mDimensionRatio = 0.0f;
        this.mDimensionRatioSide = -1;
        this.mSolverLeft = 0;
        this.mSolverTop = 0;
        this.mSolverRight = 0;
        this.mSolverBottom = 0;
        this.mX = 0;
        this.mY = 0;
        this.mDrawX = 0;
        this.mDrawY = 0;
        this.mDrawWidth = 0;
        this.mDrawHeight = 0;
        this.mOffsetX = 0;
        this.mOffsetY = 0;
        this.mBaselineDistance = 0;
        this.mHorizontalBiasPercent = DEFAULT_BIAS;
        this.mVerticalBiasPercent = DEFAULT_BIAS;
        this.mHorizontalDimensionBehaviour = DimensionBehaviour.FIXED;
        this.mVerticalDimensionBehaviour = DimensionBehaviour.FIXED;
        this.mContainerItemSkip = 0;
        this.mVisibility = 0;
        this.mDebugName = null;
        this.mType = null;
        this.mHorizontalChainStyle = 0;
        this.mVerticalChainStyle = 0;
        this.mHorizontalWeight = 0.0f;
        this.mVerticalWeight = 0.0f;
        this.mHorizontalNextWidget = null;
        this.mVerticalNextWidget = null;
        this.mX = i;
        this.mY = i2;
        this.mWidth = i3;
        this.mHeight = i4;
        addAnchors();
        forceUpdateDrawPosition();
    }

    public ConstraintWidget(int i, int i2) {
        this(0, 0, i, i2);
    }

    public void resetSolverVariables(Cache cache) {
        this.mLeft.resetSolverVariable(cache);
        this.mTop.resetSolverVariable(cache);
        this.mRight.resetSolverVariable(cache);
        this.mBottom.resetSolverVariable(cache);
        this.mBaseline.resetSolverVariable(cache);
        this.mCenter.resetSolverVariable(cache);
        this.mCenterX.resetSolverVariable(cache);
        this.mCenterY.resetSolverVariable(cache);
    }

    public void resetGroups() {
        int size = this.mAnchors.size();
        for (int i = 0; i < size; i++) {
            this.mAnchors.get(i).mGroup = Integer.MAX_VALUE;
        }
    }

    private void addAnchors() {
        this.mAnchors.add(this.mLeft);
        this.mAnchors.add(this.mTop);
        this.mAnchors.add(this.mRight);
        this.mAnchors.add(this.mBottom);
        this.mAnchors.add(this.mCenterX);
        this.mAnchors.add(this.mCenterY);
        this.mAnchors.add(this.mBaseline);
    }

    public boolean isRoot() {
        return this.mParent == null;
    }

    public boolean isRootContainer() {
        return (this instanceof ConstraintWidgetContainer) && (this.mParent == null || !(this.mParent instanceof ConstraintWidgetContainer));
    }

    public boolean isInsideConstraintLayout() {
        ConstraintWidget parent = getParent();
        if (parent == null) {
            return false;
        }
        while (parent != null) {
            if (parent instanceof ConstraintWidgetContainer) {
                return true;
            }
            parent = parent.getParent();
        }
        return false;
    }

    public boolean hasAncestor(ConstraintWidget constraintWidget) {
        ConstraintWidget parent = getParent();
        if (parent == constraintWidget) {
            return true;
        }
        if (parent == constraintWidget.getParent()) {
            return false;
        }
        while (parent != null) {
            if (parent == constraintWidget || parent == constraintWidget.getParent()) {
                return true;
            }
            parent = parent.getParent();
        }
        return false;
    }

    public WidgetContainer getRootWidgetContainer() {
        ConstraintWidget constraintWidget = this;
        while (constraintWidget.getParent() != null) {
            constraintWidget = constraintWidget.getParent();
        }
        if (constraintWidget instanceof WidgetContainer) {
            return (WidgetContainer) constraintWidget;
        }
        return null;
    }

    public ConstraintWidget getParent() {
        return this.mParent;
    }

    public void setParent(ConstraintWidget constraintWidget) {
        this.mParent = constraintWidget;
    }

    public String getType() {
        return this.mType;
    }

    public void setType(String str) {
        this.mType = str;
    }

    public void setVisibility(int i) {
        this.mVisibility = i;
    }

    public int getVisibility() {
        return this.mVisibility;
    }

    public String getDebugName() {
        return this.mDebugName;
    }

    public void setDebugName(String str) {
        this.mDebugName = str;
    }

    public void setDebugSolverName(LinearSystem linearSystem, String str) {
        this.mDebugName = str;
        SolverVariable createObjectVariable = linearSystem.createObjectVariable(this.mLeft);
        SolverVariable createObjectVariable2 = linearSystem.createObjectVariable(this.mTop);
        SolverVariable createObjectVariable3 = linearSystem.createObjectVariable(this.mRight);
        SolverVariable createObjectVariable4 = linearSystem.createObjectVariable(this.mBottom);
        createObjectVariable.setName(str + ".left");
        createObjectVariable2.setName(str + ".top");
        createObjectVariable3.setName(str + ".right");
        createObjectVariable4.setName(str + ".bottom");
        if (this.mBaselineDistance > 0) {
            SolverVariable createObjectVariable5 = linearSystem.createObjectVariable(this.mBaseline);
            createObjectVariable5.setName(str + ".baseline");
        }
    }

    public String toString() {
        String str;
        String str2;
        StringBuilder sb = new StringBuilder();
        if (this.mType != null) {
            str = "type: " + this.mType + " ";
        } else {
            str = "";
        }
        sb.append(str);
        if (this.mDebugName != null) {
            str2 = "id: " + this.mDebugName + " ";
        } else {
            str2 = "";
        }
        sb.append(str2);
        sb.append("(");
        sb.append(this.mX);
        sb.append(", ");
        sb.append(this.mY);
        sb.append(") - (");
        sb.append(this.mWidth);
        sb.append(" x ");
        sb.append(this.mHeight);
        sb.append(")");
        sb.append(" wrap: (");
        sb.append(this.mWrapWidth);
        sb.append(" x ");
        sb.append(this.mWrapHeight);
        sb.append(")");
        return sb.toString();
    }

    /* access modifiers changed from: package-private */
    public int getInternalDrawX() {
        return this.mDrawX;
    }

    /* access modifiers changed from: package-private */
    public int getInternalDrawY() {
        return this.mDrawY;
    }

    public int getInternalDrawRight() {
        return this.mDrawX + this.mDrawWidth;
    }

    public int getInternalDrawBottom() {
        return this.mDrawY + this.mDrawHeight;
    }

    public int getX() {
        return this.mX;
    }

    public int getY() {
        return this.mY;
    }

    public int getWidth() {
        if (this.mVisibility == 8) {
            return 0;
        }
        return this.mWidth;
    }

    public int getOptimizerWrapWidth() {
        int i;
        int i2 = this.mWidth;
        if (this.mHorizontalDimensionBehaviour != DimensionBehaviour.MATCH_CONSTRAINT) {
            return i2;
        }
        if (this.mMatchConstraintDefaultWidth == 1) {
            i = Math.max(this.mMatchConstraintMinWidth, i2);
        } else if (this.mMatchConstraintMinWidth > 0) {
            i = this.mMatchConstraintMinWidth;
            this.mWidth = i;
        } else {
            i = 0;
        }
        return (this.mMatchConstraintMaxWidth <= 0 || this.mMatchConstraintMaxWidth >= i) ? i : this.mMatchConstraintMaxWidth;
    }

    public int getOptimizerWrapHeight() {
        int i;
        int i2 = this.mHeight;
        if (this.mVerticalDimensionBehaviour != DimensionBehaviour.MATCH_CONSTRAINT) {
            return i2;
        }
        if (this.mMatchConstraintDefaultHeight == 1) {
            i = Math.max(this.mMatchConstraintMinHeight, i2);
        } else if (this.mMatchConstraintMinHeight > 0) {
            i = this.mMatchConstraintMinHeight;
            this.mHeight = i;
        } else {
            i = 0;
        }
        return (this.mMatchConstraintMaxHeight <= 0 || this.mMatchConstraintMaxHeight >= i) ? i : this.mMatchConstraintMaxHeight;
    }

    public int getWrapWidth() {
        return this.mWrapWidth;
    }

    public int getHeight() {
        if (this.mVisibility == 8) {
            return 0;
        }
        return this.mHeight;
    }

    public int getWrapHeight() {
        return this.mWrapHeight;
    }

    public int getDrawX() {
        return this.mDrawX + this.mOffsetX;
    }

    public int getDrawY() {
        return this.mDrawY + this.mOffsetY;
    }

    public int getDrawWidth() {
        return this.mDrawWidth;
    }

    public int getDrawHeight() {
        return this.mDrawHeight;
    }

    public int getDrawBottom() {
        return getDrawY() + this.mDrawHeight;
    }

    public int getDrawRight() {
        return getDrawX() + this.mDrawWidth;
    }

    /* access modifiers changed from: protected */
    public int getRootX() {
        return this.mX + this.mOffsetX;
    }

    /* access modifiers changed from: protected */
    public int getRootY() {
        return this.mY + this.mOffsetY;
    }

    public int getMinWidth() {
        return this.mMinWidth;
    }

    public int getMinHeight() {
        return this.mMinHeight;
    }

    public int getLeft() {
        return getX();
    }

    public int getTop() {
        return getY();
    }

    public int getRight() {
        return getX() + this.mWidth;
    }

    public int getBottom() {
        return getY() + this.mHeight;
    }

    public float getHorizontalBiasPercent() {
        return this.mHorizontalBiasPercent;
    }

    public float getVerticalBiasPercent() {
        return this.mVerticalBiasPercent;
    }

    public boolean hasBaseline() {
        return this.mBaselineDistance > 0;
    }

    public int getBaselineDistance() {
        return this.mBaselineDistance;
    }

    public Object getCompanionWidget() {
        return this.mCompanionWidget;
    }

    public ArrayList<ConstraintAnchor> getAnchors() {
        return this.mAnchors;
    }

    public void setX(int i) {
        this.mX = i;
    }

    public void setY(int i) {
        this.mY = i;
    }

    public void setOrigin(int i, int i2) {
        this.mX = i;
        this.mY = i2;
    }

    public void setOffset(int i, int i2) {
        this.mOffsetX = i;
        this.mOffsetY = i2;
    }

    public void setGoneMargin(ConstraintAnchor.Type type, int i) {
        switch (type) {
            case LEFT:
                this.mLeft.mGoneMargin = i;
                return;
            case TOP:
                this.mTop.mGoneMargin = i;
                return;
            case RIGHT:
                this.mRight.mGoneMargin = i;
                return;
            case BOTTOM:
                this.mBottom.mGoneMargin = i;
                return;
            default:
                return;
        }
    }

    public void updateDrawPosition() {
        int i = this.mX;
        int i2 = this.mY;
        int i3 = this.mX + this.mWidth;
        this.mDrawX = i;
        this.mDrawY = i2;
        this.mDrawWidth = i3 - i;
        this.mDrawHeight = (this.mY + this.mHeight) - i2;
    }

    public void forceUpdateDrawPosition() {
        int i = this.mX;
        int i2 = this.mY;
        int i3 = this.mX + this.mWidth;
        this.mDrawX = i;
        this.mDrawY = i2;
        this.mDrawWidth = i3 - i;
        this.mDrawHeight = (this.mY + this.mHeight) - i2;
    }

    public void setDrawOrigin(int i, int i2) {
        this.mDrawX = i - this.mOffsetX;
        this.mDrawY = i2 - this.mOffsetY;
        this.mX = this.mDrawX;
        this.mY = this.mDrawY;
    }

    public void setDrawX(int i) {
        this.mDrawX = i - this.mOffsetX;
        this.mX = this.mDrawX;
    }

    public void setDrawY(int i) {
        this.mDrawY = i - this.mOffsetY;
        this.mY = this.mDrawY;
    }

    public void setDrawWidth(int i) {
        this.mDrawWidth = i;
    }

    public void setDrawHeight(int i) {
        this.mDrawHeight = i;
    }

    public void setWidth(int i) {
        this.mWidth = i;
        if (this.mWidth < this.mMinWidth) {
            this.mWidth = this.mMinWidth;
        }
    }

    public void setHeight(int i) {
        this.mHeight = i;
        if (this.mHeight < this.mMinHeight) {
            this.mHeight = this.mMinHeight;
        }
    }

    public void setHorizontalMatchStyle(int i, int i2, int i3) {
        this.mMatchConstraintDefaultWidth = i;
        this.mMatchConstraintMinWidth = i2;
        this.mMatchConstraintMaxWidth = i3;
    }

    public void setVerticalMatchStyle(int i, int i2, int i3) {
        this.mMatchConstraintDefaultHeight = i;
        this.mMatchConstraintMinHeight = i2;
        this.mMatchConstraintMaxHeight = i3;
    }

    /* JADX WARNING: Removed duplicated region for block: B:39:0x0089  */
    /* JADX WARNING: Removed duplicated region for block: B:43:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setDimensionRatio(java.lang.String r9) {
        /*
            r8 = this;
            r0 = 0
            if (r9 == 0) goto L_0x008e
            int r1 = r9.length()
            if (r1 != 0) goto L_0x000b
            goto L_0x008e
        L_0x000b:
            r1 = -1
            int r2 = r9.length()
            r3 = 44
            int r3 = r9.indexOf(r3)
            r4 = 0
            r5 = 1
            if (r3 <= 0) goto L_0x0037
            int r6 = r2 + -1
            if (r3 >= r6) goto L_0x0037
            java.lang.String r6 = r9.substring(r4, r3)
            java.lang.String r7 = "W"
            boolean r7 = r6.equalsIgnoreCase(r7)
            if (r7 == 0) goto L_0x002c
            r1 = r4
            goto L_0x0035
        L_0x002c:
            java.lang.String r4 = "H"
            boolean r4 = r6.equalsIgnoreCase(r4)
            if (r4 == 0) goto L_0x0035
            r1 = r5
        L_0x0035:
            int r4 = r3 + 1
        L_0x0037:
            r3 = 58
            int r3 = r9.indexOf(r3)
            if (r3 < 0) goto L_0x0075
            int r2 = r2 - r5
            if (r3 >= r2) goto L_0x0075
            java.lang.String r2 = r9.substring(r4, r3)
            int r3 = r3 + r5
            java.lang.String r9 = r9.substring(r3)
            int r3 = r2.length()
            if (r3 <= 0) goto L_0x0084
            int r3 = r9.length()
            if (r3 <= 0) goto L_0x0084
            float r2 = java.lang.Float.parseFloat(r2)     // Catch:{ NumberFormatException -> 0x0084 }
            float r9 = java.lang.Float.parseFloat(r9)     // Catch:{ NumberFormatException -> 0x0084 }
            int r3 = (r2 > r0 ? 1 : (r2 == r0 ? 0 : -1))
            if (r3 <= 0) goto L_0x0084
            int r3 = (r9 > r0 ? 1 : (r9 == r0 ? 0 : -1))
            if (r3 <= 0) goto L_0x0084
            if (r1 != r5) goto L_0x006f
            float r9 = r9 / r2
            float r9 = java.lang.Math.abs(r9)     // Catch:{ NumberFormatException -> 0x0084 }
            goto L_0x0085
        L_0x006f:
            float r2 = r2 / r9
            float r9 = java.lang.Math.abs(r2)     // Catch:{ NumberFormatException -> 0x0084 }
            goto L_0x0085
        L_0x0075:
            java.lang.String r9 = r9.substring(r4)
            int r2 = r9.length()
            if (r2 <= 0) goto L_0x0084
            float r9 = java.lang.Float.parseFloat(r9)     // Catch:{ NumberFormatException -> 0x0084 }
            goto L_0x0085
        L_0x0084:
            r9 = r0
        L_0x0085:
            int r0 = (r9 > r0 ? 1 : (r9 == r0 ? 0 : -1))
            if (r0 <= 0) goto L_0x008d
            r8.mDimensionRatio = r9
            r8.mDimensionRatioSide = r1
        L_0x008d:
            return
        L_0x008e:
            r8.mDimensionRatio = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.constraint.solver.widgets.ConstraintWidget.setDimensionRatio(java.lang.String):void");
    }

    public void setDimensionRatio(float f, int i) {
        this.mDimensionRatio = f;
        this.mDimensionRatioSide = i;
    }

    public float getDimensionRatio() {
        return this.mDimensionRatio;
    }

    public int getDimensionRatioSide() {
        return this.mDimensionRatioSide;
    }

    public void setHorizontalBiasPercent(float f) {
        this.mHorizontalBiasPercent = f;
    }

    public void setVerticalBiasPercent(float f) {
        this.mVerticalBiasPercent = f;
    }

    public void setMinWidth(int i) {
        if (i < 0) {
            this.mMinWidth = 0;
        } else {
            this.mMinWidth = i;
        }
    }

    public void setMinHeight(int i) {
        if (i < 0) {
            this.mMinHeight = 0;
        } else {
            this.mMinHeight = i;
        }
    }

    public void setWrapWidth(int i) {
        this.mWrapWidth = i;
    }

    public void setWrapHeight(int i) {
        this.mWrapHeight = i;
    }

    public void setDimension(int i, int i2) {
        this.mWidth = i;
        if (this.mWidth < this.mMinWidth) {
            this.mWidth = this.mMinWidth;
        }
        this.mHeight = i2;
        if (this.mHeight < this.mMinHeight) {
            this.mHeight = this.mMinHeight;
        }
    }

    public void setFrame(int i, int i2, int i3, int i4) {
        int i5 = i3 - i;
        int i6 = i4 - i2;
        this.mX = i;
        this.mY = i2;
        if (this.mVisibility == 8) {
            this.mWidth = 0;
            this.mHeight = 0;
            return;
        }
        if (this.mHorizontalDimensionBehaviour == DimensionBehaviour.FIXED && i5 < this.mWidth) {
            i5 = this.mWidth;
        }
        if (this.mVerticalDimensionBehaviour == DimensionBehaviour.FIXED && i6 < this.mHeight) {
            i6 = this.mHeight;
        }
        this.mWidth = i5;
        this.mHeight = i6;
        if (this.mHeight < this.mMinHeight) {
            this.mHeight = this.mMinHeight;
        }
        if (this.mWidth < this.mMinWidth) {
            this.mWidth = this.mMinWidth;
        }
    }

    public void setHorizontalDimension(int i, int i2) {
        this.mX = i;
        this.mWidth = i2 - i;
        if (this.mWidth < this.mMinWidth) {
            this.mWidth = this.mMinWidth;
        }
    }

    public void setVerticalDimension(int i, int i2) {
        this.mY = i;
        this.mHeight = i2 - i;
        if (this.mHeight < this.mMinHeight) {
            this.mHeight = this.mMinHeight;
        }
    }

    public void setBaselineDistance(int i) {
        this.mBaselineDistance = i;
    }

    public void setCompanionWidget(Object obj) {
        this.mCompanionWidget = obj;
    }

    public void setContainerItemSkip(int i) {
        if (i >= 0) {
            this.mContainerItemSkip = i;
        } else {
            this.mContainerItemSkip = 0;
        }
    }

    public int getContainerItemSkip() {
        return this.mContainerItemSkip;
    }

    public void setHorizontalWeight(float f) {
        this.mHorizontalWeight = f;
    }

    public void setVerticalWeight(float f) {
        this.mVerticalWeight = f;
    }

    public void setHorizontalChainStyle(int i) {
        this.mHorizontalChainStyle = i;
    }

    public int getHorizontalChainStyle() {
        return this.mHorizontalChainStyle;
    }

    public void setVerticalChainStyle(int i) {
        this.mVerticalChainStyle = i;
    }

    public int getVerticalChainStyle() {
        return this.mVerticalChainStyle;
    }

    public void immediateConnect(ConstraintAnchor.Type type, ConstraintWidget constraintWidget, ConstraintAnchor.Type type2, int i, int i2) {
        getAnchor(type).connect(constraintWidget.getAnchor(type2), i, i2, ConstraintAnchor.Strength.STRONG, 0, true);
    }

    public void connect(ConstraintAnchor constraintAnchor, ConstraintAnchor constraintAnchor2, int i, int i2) {
        connect(constraintAnchor, constraintAnchor2, i, ConstraintAnchor.Strength.STRONG, i2);
    }

    public void connect(ConstraintAnchor constraintAnchor, ConstraintAnchor constraintAnchor2, int i) {
        connect(constraintAnchor, constraintAnchor2, i, ConstraintAnchor.Strength.STRONG, 0);
    }

    public void connect(ConstraintAnchor constraintAnchor, ConstraintAnchor constraintAnchor2, int i, ConstraintAnchor.Strength strength, int i2) {
        if (constraintAnchor.getOwner() == this) {
            connect(constraintAnchor.getType(), constraintAnchor2.getOwner(), constraintAnchor2.getType(), i, strength, i2);
        }
    }

    public void connect(ConstraintAnchor.Type type, ConstraintWidget constraintWidget, ConstraintAnchor.Type type2, int i) {
        connect(type, constraintWidget, type2, i, ConstraintAnchor.Strength.STRONG);
    }

    public void connect(ConstraintAnchor.Type type, ConstraintWidget constraintWidget, ConstraintAnchor.Type type2) {
        connect(type, constraintWidget, type2, 0, ConstraintAnchor.Strength.STRONG);
    }

    public void connect(ConstraintAnchor.Type type, ConstraintWidget constraintWidget, ConstraintAnchor.Type type2, int i, ConstraintAnchor.Strength strength) {
        connect(type, constraintWidget, type2, i, strength, 0);
    }

    public void connect(ConstraintAnchor.Type type, ConstraintWidget constraintWidget, ConstraintAnchor.Type type2, int i, ConstraintAnchor.Strength strength, int i2) {
        boolean z;
        ConstraintAnchor.Type type3 = type;
        ConstraintWidget constraintWidget2 = constraintWidget;
        ConstraintAnchor.Type type4 = type2;
        int i3 = i2;
        int i4 = 0;
        if (type3 == ConstraintAnchor.Type.CENTER) {
            if (type4 == ConstraintAnchor.Type.CENTER) {
                ConstraintAnchor anchor = getAnchor(ConstraintAnchor.Type.LEFT);
                ConstraintAnchor anchor2 = getAnchor(ConstraintAnchor.Type.RIGHT);
                ConstraintAnchor anchor3 = getAnchor(ConstraintAnchor.Type.TOP);
                ConstraintAnchor anchor4 = getAnchor(ConstraintAnchor.Type.BOTTOM);
                boolean z2 = true;
                if ((anchor == null || !anchor.isConnected()) && (anchor2 == null || !anchor2.isConnected())) {
                    ConstraintWidget constraintWidget3 = constraintWidget2;
                    ConstraintAnchor.Strength strength2 = strength;
                    int i5 = i3;
                    connect(ConstraintAnchor.Type.LEFT, constraintWidget3, ConstraintAnchor.Type.LEFT, 0, strength2, i5);
                    connect(ConstraintAnchor.Type.RIGHT, constraintWidget3, ConstraintAnchor.Type.RIGHT, 0, strength2, i5);
                    z = true;
                } else {
                    z = false;
                }
                if ((anchor3 == null || !anchor3.isConnected()) && (anchor4 == null || !anchor4.isConnected())) {
                    ConstraintWidget constraintWidget4 = constraintWidget2;
                    ConstraintAnchor.Strength strength3 = strength;
                    int i6 = i3;
                    connect(ConstraintAnchor.Type.TOP, constraintWidget4, ConstraintAnchor.Type.TOP, 0, strength3, i6);
                    connect(ConstraintAnchor.Type.BOTTOM, constraintWidget4, ConstraintAnchor.Type.BOTTOM, 0, strength3, i6);
                } else {
                    z2 = false;
                }
                if (z && z2) {
                    getAnchor(ConstraintAnchor.Type.CENTER).connect(constraintWidget2.getAnchor(ConstraintAnchor.Type.CENTER), 0, i3);
                } else if (z) {
                    getAnchor(ConstraintAnchor.Type.CENTER_X).connect(constraintWidget2.getAnchor(ConstraintAnchor.Type.CENTER_X), 0, i3);
                } else if (z2) {
                    getAnchor(ConstraintAnchor.Type.CENTER_Y).connect(constraintWidget2.getAnchor(ConstraintAnchor.Type.CENTER_Y), 0, i3);
                }
            } else if (type4 == ConstraintAnchor.Type.LEFT || type4 == ConstraintAnchor.Type.RIGHT) {
                ConstraintWidget constraintWidget5 = constraintWidget2;
                ConstraintAnchor.Type type5 = type4;
                ConstraintAnchor.Strength strength4 = strength;
                int i7 = i3;
                connect(ConstraintAnchor.Type.LEFT, constraintWidget5, type5, 0, strength4, i7);
                connect(ConstraintAnchor.Type.RIGHT, constraintWidget5, type5, 0, strength4, i7);
                getAnchor(ConstraintAnchor.Type.CENTER).connect(constraintWidget.getAnchor(type2), 0, i3);
            } else if (type4 == ConstraintAnchor.Type.TOP || type4 == ConstraintAnchor.Type.BOTTOM) {
                ConstraintWidget constraintWidget6 = constraintWidget2;
                ConstraintAnchor.Type type6 = type4;
                ConstraintAnchor.Strength strength5 = strength;
                int i8 = i3;
                connect(ConstraintAnchor.Type.TOP, constraintWidget6, type6, 0, strength5, i8);
                connect(ConstraintAnchor.Type.BOTTOM, constraintWidget6, type6, 0, strength5, i8);
                getAnchor(ConstraintAnchor.Type.CENTER).connect(constraintWidget.getAnchor(type2), 0, i3);
            }
        } else if (type3 == ConstraintAnchor.Type.CENTER_X && (type4 == ConstraintAnchor.Type.LEFT || type4 == ConstraintAnchor.Type.RIGHT)) {
            ConstraintAnchor anchor5 = getAnchor(ConstraintAnchor.Type.LEFT);
            ConstraintAnchor anchor6 = constraintWidget.getAnchor(type2);
            ConstraintAnchor anchor7 = getAnchor(ConstraintAnchor.Type.RIGHT);
            anchor5.connect(anchor6, 0, i3);
            anchor7.connect(anchor6, 0, i3);
            getAnchor(ConstraintAnchor.Type.CENTER_X).connect(anchor6, 0, i3);
        } else if (type3 == ConstraintAnchor.Type.CENTER_Y && (type4 == ConstraintAnchor.Type.TOP || type4 == ConstraintAnchor.Type.BOTTOM)) {
            ConstraintAnchor anchor8 = constraintWidget.getAnchor(type2);
            getAnchor(ConstraintAnchor.Type.TOP).connect(anchor8, 0, i3);
            getAnchor(ConstraintAnchor.Type.BOTTOM).connect(anchor8, 0, i3);
            getAnchor(ConstraintAnchor.Type.CENTER_Y).connect(anchor8, 0, i3);
        } else if (type3 == ConstraintAnchor.Type.CENTER_X && type4 == ConstraintAnchor.Type.CENTER_X) {
            getAnchor(ConstraintAnchor.Type.LEFT).connect(constraintWidget2.getAnchor(ConstraintAnchor.Type.LEFT), 0, i3);
            getAnchor(ConstraintAnchor.Type.RIGHT).connect(constraintWidget2.getAnchor(ConstraintAnchor.Type.RIGHT), 0, i3);
            getAnchor(ConstraintAnchor.Type.CENTER_X).connect(constraintWidget.getAnchor(type2), 0, i3);
        } else if (type3 == ConstraintAnchor.Type.CENTER_Y && type4 == ConstraintAnchor.Type.CENTER_Y) {
            getAnchor(ConstraintAnchor.Type.TOP).connect(constraintWidget2.getAnchor(ConstraintAnchor.Type.TOP), 0, i3);
            getAnchor(ConstraintAnchor.Type.BOTTOM).connect(constraintWidget2.getAnchor(ConstraintAnchor.Type.BOTTOM), 0, i3);
            getAnchor(ConstraintAnchor.Type.CENTER_Y).connect(constraintWidget.getAnchor(type2), 0, i3);
        } else {
            ConstraintAnchor anchor9 = getAnchor(type);
            ConstraintAnchor anchor10 = constraintWidget.getAnchor(type2);
            if (anchor9.isValidConnection(anchor10)) {
                if (type3 == ConstraintAnchor.Type.BASELINE) {
                    ConstraintAnchor anchor11 = getAnchor(ConstraintAnchor.Type.TOP);
                    ConstraintAnchor anchor12 = getAnchor(ConstraintAnchor.Type.BOTTOM);
                    if (anchor11 != null) {
                        anchor11.reset();
                    }
                    if (anchor12 != null) {
                        anchor12.reset();
                    }
                } else {
                    if (type3 == ConstraintAnchor.Type.TOP || type3 == ConstraintAnchor.Type.BOTTOM) {
                        ConstraintAnchor anchor13 = getAnchor(ConstraintAnchor.Type.BASELINE);
                        if (anchor13 != null) {
                            anchor13.reset();
                        }
                        ConstraintAnchor anchor14 = getAnchor(ConstraintAnchor.Type.CENTER);
                        if (anchor14.getTarget() != anchor10) {
                            anchor14.reset();
                        }
                        ConstraintAnchor opposite = getAnchor(type).getOpposite();
                        ConstraintAnchor anchor15 = getAnchor(ConstraintAnchor.Type.CENTER_Y);
                        if (anchor15.isConnected()) {
                            opposite.reset();
                            anchor15.reset();
                        }
                    } else if (type3 == ConstraintAnchor.Type.LEFT || type3 == ConstraintAnchor.Type.RIGHT) {
                        ConstraintAnchor anchor16 = getAnchor(ConstraintAnchor.Type.CENTER);
                        if (anchor16.getTarget() != anchor10) {
                            anchor16.reset();
                        }
                        ConstraintAnchor opposite2 = getAnchor(type).getOpposite();
                        ConstraintAnchor anchor17 = getAnchor(ConstraintAnchor.Type.CENTER_X);
                        if (anchor17.isConnected()) {
                            opposite2.reset();
                            anchor17.reset();
                        }
                    }
                    i4 = i;
                }
                anchor9.connect(anchor10, i4, strength, i3);
                anchor10.getOwner().connectedTo(anchor9.getOwner());
            }
        }
    }

    public void resetAllConstraints() {
        resetAnchors();
        setVerticalBiasPercent(DEFAULT_BIAS);
        setHorizontalBiasPercent(DEFAULT_BIAS);
        if (!(this instanceof ConstraintWidgetContainer)) {
            if (getHorizontalDimensionBehaviour() == DimensionBehaviour.MATCH_CONSTRAINT) {
                if (getWidth() == getWrapWidth()) {
                    setHorizontalDimensionBehaviour(DimensionBehaviour.WRAP_CONTENT);
                } else if (getWidth() > getMinWidth()) {
                    setHorizontalDimensionBehaviour(DimensionBehaviour.FIXED);
                }
            }
            if (getVerticalDimensionBehaviour() != DimensionBehaviour.MATCH_CONSTRAINT) {
                return;
            }
            if (getHeight() == getWrapHeight()) {
                setVerticalDimensionBehaviour(DimensionBehaviour.WRAP_CONTENT);
            } else if (getHeight() > getMinHeight()) {
                setVerticalDimensionBehaviour(DimensionBehaviour.FIXED);
            }
        }
    }

    public void resetAnchor(ConstraintAnchor constraintAnchor) {
        if (getParent() == null || !(getParent() instanceof ConstraintWidgetContainer) || !((ConstraintWidgetContainer) getParent()).handlesInternalConstraints()) {
            ConstraintAnchor anchor = getAnchor(ConstraintAnchor.Type.LEFT);
            ConstraintAnchor anchor2 = getAnchor(ConstraintAnchor.Type.RIGHT);
            ConstraintAnchor anchor3 = getAnchor(ConstraintAnchor.Type.TOP);
            ConstraintAnchor anchor4 = getAnchor(ConstraintAnchor.Type.BOTTOM);
            ConstraintAnchor anchor5 = getAnchor(ConstraintAnchor.Type.CENTER);
            ConstraintAnchor anchor6 = getAnchor(ConstraintAnchor.Type.CENTER_X);
            ConstraintAnchor anchor7 = getAnchor(ConstraintAnchor.Type.CENTER_Y);
            if (constraintAnchor == anchor5) {
                if (anchor.isConnected() && anchor2.isConnected() && anchor.getTarget() == anchor2.getTarget()) {
                    anchor.reset();
                    anchor2.reset();
                }
                if (anchor3.isConnected() && anchor4.isConnected() && anchor3.getTarget() == anchor4.getTarget()) {
                    anchor3.reset();
                    anchor4.reset();
                }
                this.mHorizontalBiasPercent = 0.5f;
                this.mVerticalBiasPercent = 0.5f;
            } else if (constraintAnchor == anchor6) {
                if (anchor.isConnected() && anchor2.isConnected() && anchor.getTarget().getOwner() == anchor2.getTarget().getOwner()) {
                    anchor.reset();
                    anchor2.reset();
                }
                this.mHorizontalBiasPercent = 0.5f;
            } else if (constraintAnchor == anchor7) {
                if (anchor3.isConnected() && anchor4.isConnected() && anchor3.getTarget().getOwner() == anchor4.getTarget().getOwner()) {
                    anchor3.reset();
                    anchor4.reset();
                }
                this.mVerticalBiasPercent = 0.5f;
            } else if (constraintAnchor == anchor || constraintAnchor == anchor2) {
                if (anchor.isConnected() && anchor.getTarget() == anchor2.getTarget()) {
                    anchor5.reset();
                }
            } else if ((constraintAnchor == anchor3 || constraintAnchor == anchor4) && anchor3.isConnected() && anchor3.getTarget() == anchor4.getTarget()) {
                anchor5.reset();
            }
            constraintAnchor.reset();
        }
    }

    public void resetAnchors() {
        ConstraintWidget parent = getParent();
        if (parent == null || !(parent instanceof ConstraintWidgetContainer) || !((ConstraintWidgetContainer) getParent()).handlesInternalConstraints()) {
            int size = this.mAnchors.size();
            for (int i = 0; i < size; i++) {
                this.mAnchors.get(i).reset();
            }
        }
    }

    public void resetAnchors(int i) {
        ConstraintWidget parent = getParent();
        if (parent == null || !(parent instanceof ConstraintWidgetContainer) || !((ConstraintWidgetContainer) getParent()).handlesInternalConstraints()) {
            int size = this.mAnchors.size();
            for (int i2 = 0; i2 < size; i2++) {
                ConstraintAnchor constraintAnchor = this.mAnchors.get(i2);
                if (i == constraintAnchor.getConnectionCreator()) {
                    if (constraintAnchor.isVerticalAnchor()) {
                        setVerticalBiasPercent(DEFAULT_BIAS);
                    } else {
                        setHorizontalBiasPercent(DEFAULT_BIAS);
                    }
                    constraintAnchor.reset();
                }
            }
        }
    }

    public void disconnectWidget(ConstraintWidget constraintWidget) {
        ArrayList<ConstraintAnchor> anchors = getAnchors();
        int size = anchors.size();
        for (int i = 0; i < size; i++) {
            ConstraintAnchor constraintAnchor = anchors.get(i);
            if (constraintAnchor.isConnected() && constraintAnchor.getTarget().getOwner() == constraintWidget) {
                constraintAnchor.reset();
            }
        }
    }

    public void disconnectUnlockedWidget(ConstraintWidget constraintWidget) {
        ArrayList<ConstraintAnchor> anchors = getAnchors();
        int size = anchors.size();
        for (int i = 0; i < size; i++) {
            ConstraintAnchor constraintAnchor = anchors.get(i);
            if (constraintAnchor.isConnected() && constraintAnchor.getTarget().getOwner() == constraintWidget && constraintAnchor.getConnectionCreator() == 2) {
                constraintAnchor.reset();
            }
        }
    }

    public ConstraintAnchor getAnchor(ConstraintAnchor.Type type) {
        switch (type) {
            case LEFT:
                return this.mLeft;
            case TOP:
                return this.mTop;
            case RIGHT:
                return this.mRight;
            case BOTTOM:
                return this.mBottom;
            case BASELINE:
                return this.mBaseline;
            case CENTER_X:
                return this.mCenterX;
            case CENTER_Y:
                return this.mCenterY;
            case CENTER:
                return this.mCenter;
            default:
                return null;
        }
    }

    public DimensionBehaviour getHorizontalDimensionBehaviour() {
        return this.mHorizontalDimensionBehaviour;
    }

    public DimensionBehaviour getVerticalDimensionBehaviour() {
        return this.mVerticalDimensionBehaviour;
    }

    public void setHorizontalDimensionBehaviour(DimensionBehaviour dimensionBehaviour) {
        this.mHorizontalDimensionBehaviour = dimensionBehaviour;
        if (this.mHorizontalDimensionBehaviour == DimensionBehaviour.WRAP_CONTENT) {
            setWidth(this.mWrapWidth);
        }
    }

    public void setVerticalDimensionBehaviour(DimensionBehaviour dimensionBehaviour) {
        this.mVerticalDimensionBehaviour = dimensionBehaviour;
        if (this.mVerticalDimensionBehaviour == DimensionBehaviour.WRAP_CONTENT) {
            setHeight(this.mWrapHeight);
        }
    }

    public boolean isInHorizontalChain() {
        if (this.mLeft.mTarget == null || this.mLeft.mTarget.mTarget != this.mLeft) {
            return this.mRight.mTarget != null && this.mRight.mTarget.mTarget == this.mRight;
        }
        return true;
    }

    public ConstraintWidget getHorizontalChainControlWidget() {
        ConstraintAnchor constraintAnchor;
        ConstraintWidget constraintWidget;
        ConstraintAnchor constraintAnchor2;
        if (!isInHorizontalChain()) {
            return null;
        }
        ConstraintWidget constraintWidget2 = this;
        ConstraintWidget constraintWidget3 = null;
        while (constraintWidget3 == null && constraintWidget2 != null) {
            ConstraintAnchor anchor = constraintWidget2.getAnchor(ConstraintAnchor.Type.LEFT);
            if (anchor == null) {
                constraintAnchor = null;
            } else {
                constraintAnchor = anchor.getTarget();
            }
            if (constraintAnchor == null) {
                constraintWidget = null;
            } else {
                constraintWidget = constraintAnchor.getOwner();
            }
            if (constraintWidget == getParent()) {
                return constraintWidget2;
            }
            if (constraintWidget == null) {
                constraintAnchor2 = null;
            } else {
                constraintAnchor2 = constraintWidget.getAnchor(ConstraintAnchor.Type.RIGHT).getTarget();
            }
            if (constraintAnchor2 == null || constraintAnchor2.getOwner() == constraintWidget2) {
                constraintWidget2 = constraintWidget;
            } else {
                constraintWidget3 = constraintWidget2;
            }
        }
        return constraintWidget3;
    }

    public boolean isInVerticalChain() {
        if (this.mTop.mTarget == null || this.mTop.mTarget.mTarget != this.mTop) {
            return this.mBottom.mTarget != null && this.mBottom.mTarget.mTarget == this.mBottom;
        }
        return true;
    }

    public ConstraintWidget getVerticalChainControlWidget() {
        ConstraintAnchor constraintAnchor;
        ConstraintWidget constraintWidget;
        ConstraintAnchor constraintAnchor2;
        if (!isInVerticalChain()) {
            return null;
        }
        ConstraintWidget constraintWidget2 = this;
        ConstraintWidget constraintWidget3 = null;
        while (constraintWidget3 == null && constraintWidget2 != null) {
            ConstraintAnchor anchor = constraintWidget2.getAnchor(ConstraintAnchor.Type.TOP);
            if (anchor == null) {
                constraintAnchor = null;
            } else {
                constraintAnchor = anchor.getTarget();
            }
            if (constraintAnchor == null) {
                constraintWidget = null;
            } else {
                constraintWidget = constraintAnchor.getOwner();
            }
            if (constraintWidget == getParent()) {
                return constraintWidget2;
            }
            if (constraintWidget == null) {
                constraintAnchor2 = null;
            } else {
                constraintAnchor2 = constraintWidget.getAnchor(ConstraintAnchor.Type.BOTTOM).getTarget();
            }
            if (constraintAnchor2 == null || constraintAnchor2.getOwner() == constraintWidget2) {
                constraintWidget2 = constraintWidget;
            } else {
                constraintWidget3 = constraintWidget2;
            }
        }
        return constraintWidget3;
    }

    public void addToSolver(LinearSystem linearSystem) {
        addToSolver(linearSystem, Integer.MAX_VALUE);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:213:0x0403, code lost:
        if (r14 == -1) goto L_0x0407;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:254:0x0531, code lost:
        if (r9.mBottom.mGroup == r12) goto L_0x053c;
     */
    /* JADX WARNING: Removed duplicated region for block: B:170:0x02d6 A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:178:0x02e9  */
    /* JADX WARNING: Removed duplicated region for block: B:179:0x02eb  */
    /* JADX WARNING: Removed duplicated region for block: B:188:0x0303  */
    /* JADX WARNING: Removed duplicated region for block: B:199:0x03d7  */
    /* JADX WARNING: Removed duplicated region for block: B:202:0x03ec A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:203:0x03ed  */
    /* JADX WARNING: Removed duplicated region for block: B:220:0x0413  */
    /* JADX WARNING: Removed duplicated region for block: B:249:0x0514  */
    /* JADX WARNING: Removed duplicated region for block: B:267:0x05d1  */
    /* JADX WARNING: Removed duplicated region for block: B:289:0x065f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void addToSolver(android.support.constraint.solver.LinearSystem r45, int r46) {
        /*
            r44 = this;
            r15 = r44
            r14 = r45
            r13 = r46
            r0 = 0
            r12 = 2147483647(0x7fffffff, float:NaN)
            if (r13 == r12) goto L_0x0015
            android.support.constraint.solver.widgets.ConstraintAnchor r1 = r15.mLeft
            int r1 = r1.mGroup
            if (r1 != r13) goto L_0x0013
            goto L_0x0015
        L_0x0013:
            r11 = r0
            goto L_0x001c
        L_0x0015:
            android.support.constraint.solver.widgets.ConstraintAnchor r1 = r15.mLeft
            android.support.constraint.solver.SolverVariable r1 = r14.createObjectVariable(r1)
            r11 = r1
        L_0x001c:
            if (r13 == r12) goto L_0x0027
            android.support.constraint.solver.widgets.ConstraintAnchor r1 = r15.mRight
            int r1 = r1.mGroup
            if (r1 != r13) goto L_0x0025
            goto L_0x0027
        L_0x0025:
            r10 = r0
            goto L_0x002e
        L_0x0027:
            android.support.constraint.solver.widgets.ConstraintAnchor r1 = r15.mRight
            android.support.constraint.solver.SolverVariable r1 = r14.createObjectVariable(r1)
            r10 = r1
        L_0x002e:
            if (r13 == r12) goto L_0x0039
            android.support.constraint.solver.widgets.ConstraintAnchor r1 = r15.mTop
            int r1 = r1.mGroup
            if (r1 != r13) goto L_0x0037
            goto L_0x0039
        L_0x0037:
            r9 = r0
            goto L_0x0040
        L_0x0039:
            android.support.constraint.solver.widgets.ConstraintAnchor r1 = r15.mTop
            android.support.constraint.solver.SolverVariable r1 = r14.createObjectVariable(r1)
            r9 = r1
        L_0x0040:
            if (r13 == r12) goto L_0x004b
            android.support.constraint.solver.widgets.ConstraintAnchor r1 = r15.mBottom
            int r1 = r1.mGroup
            if (r1 != r13) goto L_0x0049
            goto L_0x004b
        L_0x0049:
            r8 = r0
            goto L_0x0052
        L_0x004b:
            android.support.constraint.solver.widgets.ConstraintAnchor r1 = r15.mBottom
            android.support.constraint.solver.SolverVariable r1 = r14.createObjectVariable(r1)
            r8 = r1
        L_0x0052:
            if (r13 == r12) goto L_0x005d
            android.support.constraint.solver.widgets.ConstraintAnchor r1 = r15.mBaseline
            int r1 = r1.mGroup
            if (r1 != r13) goto L_0x005b
            goto L_0x005d
        L_0x005b:
            r7 = r0
            goto L_0x0064
        L_0x005d:
            android.support.constraint.solver.widgets.ConstraintAnchor r0 = r15.mBaseline
            android.support.constraint.solver.SolverVariable r0 = r14.createObjectVariable(r0)
            goto L_0x005b
        L_0x0064:
            android.support.constraint.solver.widgets.ConstraintWidget r0 = r15.mParent
            r6 = 0
            r5 = 1
            if (r0 == 0) goto L_0x01d7
            android.support.constraint.solver.widgets.ConstraintAnchor r0 = r15.mLeft
            android.support.constraint.solver.widgets.ConstraintAnchor r0 = r0.mTarget
            if (r0 == 0) goto L_0x007a
            android.support.constraint.solver.widgets.ConstraintAnchor r0 = r15.mLeft
            android.support.constraint.solver.widgets.ConstraintAnchor r0 = r0.mTarget
            android.support.constraint.solver.widgets.ConstraintAnchor r0 = r0.mTarget
            android.support.constraint.solver.widgets.ConstraintAnchor r1 = r15.mLeft
            if (r0 == r1) goto L_0x008a
        L_0x007a:
            android.support.constraint.solver.widgets.ConstraintAnchor r0 = r15.mRight
            android.support.constraint.solver.widgets.ConstraintAnchor r0 = r0.mTarget
            if (r0 == 0) goto L_0x0093
            android.support.constraint.solver.widgets.ConstraintAnchor r0 = r15.mRight
            android.support.constraint.solver.widgets.ConstraintAnchor r0 = r0.mTarget
            android.support.constraint.solver.widgets.ConstraintAnchor r0 = r0.mTarget
            android.support.constraint.solver.widgets.ConstraintAnchor r1 = r15.mRight
            if (r0 != r1) goto L_0x0093
        L_0x008a:
            android.support.constraint.solver.widgets.ConstraintWidget r0 = r15.mParent
            android.support.constraint.solver.widgets.ConstraintWidgetContainer r0 = (android.support.constraint.solver.widgets.ConstraintWidgetContainer) r0
            r0.addChain(r15, r6)
            r0 = r5
            goto L_0x0094
        L_0x0093:
            r0 = r6
        L_0x0094:
            android.support.constraint.solver.widgets.ConstraintAnchor r1 = r15.mTop
            android.support.constraint.solver.widgets.ConstraintAnchor r1 = r1.mTarget
            if (r1 == 0) goto L_0x00a4
            android.support.constraint.solver.widgets.ConstraintAnchor r1 = r15.mTop
            android.support.constraint.solver.widgets.ConstraintAnchor r1 = r1.mTarget
            android.support.constraint.solver.widgets.ConstraintAnchor r1 = r1.mTarget
            android.support.constraint.solver.widgets.ConstraintAnchor r2 = r15.mTop
            if (r1 == r2) goto L_0x00b4
        L_0x00a4:
            android.support.constraint.solver.widgets.ConstraintAnchor r1 = r15.mBottom
            android.support.constraint.solver.widgets.ConstraintAnchor r1 = r1.mTarget
            if (r1 == 0) goto L_0x00bd
            android.support.constraint.solver.widgets.ConstraintAnchor r1 = r15.mBottom
            android.support.constraint.solver.widgets.ConstraintAnchor r1 = r1.mTarget
            android.support.constraint.solver.widgets.ConstraintAnchor r1 = r1.mTarget
            android.support.constraint.solver.widgets.ConstraintAnchor r2 = r15.mBottom
            if (r1 != r2) goto L_0x00bd
        L_0x00b4:
            android.support.constraint.solver.widgets.ConstraintWidget r1 = r15.mParent
            android.support.constraint.solver.widgets.ConstraintWidgetContainer r1 = (android.support.constraint.solver.widgets.ConstraintWidgetContainer) r1
            r1.addChain(r15, r5)
            r1 = r5
            goto L_0x00be
        L_0x00bd:
            r1 = r6
        L_0x00be:
            android.support.constraint.solver.widgets.ConstraintWidget r2 = r15.mParent
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r2 = r2.getHorizontalDimensionBehaviour()
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r3 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            if (r2 != r3) goto L_0x0148
            if (r0 != 0) goto L_0x0148
            android.support.constraint.solver.widgets.ConstraintAnchor r2 = r15.mLeft
            android.support.constraint.solver.widgets.ConstraintAnchor r2 = r2.mTarget
            if (r2 == 0) goto L_0x00f3
            android.support.constraint.solver.widgets.ConstraintAnchor r2 = r15.mLeft
            android.support.constraint.solver.widgets.ConstraintAnchor r2 = r2.mTarget
            android.support.constraint.solver.widgets.ConstraintWidget r2 = r2.mOwner
            android.support.constraint.solver.widgets.ConstraintWidget r3 = r15.mParent
            if (r2 == r3) goto L_0x00db
            goto L_0x00f3
        L_0x00db:
            android.support.constraint.solver.widgets.ConstraintAnchor r2 = r15.mLeft
            android.support.constraint.solver.widgets.ConstraintAnchor r2 = r2.mTarget
            if (r2 == 0) goto L_0x0109
            android.support.constraint.solver.widgets.ConstraintAnchor r2 = r15.mLeft
            android.support.constraint.solver.widgets.ConstraintAnchor r2 = r2.mTarget
            android.support.constraint.solver.widgets.ConstraintWidget r2 = r2.mOwner
            android.support.constraint.solver.widgets.ConstraintWidget r3 = r15.mParent
            if (r2 != r3) goto L_0x0109
            android.support.constraint.solver.widgets.ConstraintAnchor r2 = r15.mLeft
            android.support.constraint.solver.widgets.ConstraintAnchor$ConnectionType r3 = android.support.constraint.solver.widgets.ConstraintAnchor.ConnectionType.STRICT
            r2.setConnectionType(r3)
            goto L_0x0109
        L_0x00f3:
            android.support.constraint.solver.widgets.ConstraintWidget r2 = r15.mParent
            android.support.constraint.solver.widgets.ConstraintAnchor r2 = r2.mLeft
            android.support.constraint.solver.SolverVariable r2 = r14.createObjectVariable(r2)
            android.support.constraint.solver.ArrayRow r3 = r45.createRow()
            android.support.constraint.solver.SolverVariable r4 = r45.createSlackVariable()
            r3.createRowGreaterThan(r11, r2, r4, r6)
            r14.addConstraint(r3)
        L_0x0109:
            android.support.constraint.solver.widgets.ConstraintAnchor r2 = r15.mRight
            android.support.constraint.solver.widgets.ConstraintAnchor r2 = r2.mTarget
            if (r2 == 0) goto L_0x0132
            android.support.constraint.solver.widgets.ConstraintAnchor r2 = r15.mRight
            android.support.constraint.solver.widgets.ConstraintAnchor r2 = r2.mTarget
            android.support.constraint.solver.widgets.ConstraintWidget r2 = r2.mOwner
            android.support.constraint.solver.widgets.ConstraintWidget r3 = r15.mParent
            if (r2 == r3) goto L_0x011a
            goto L_0x0132
        L_0x011a:
            android.support.constraint.solver.widgets.ConstraintAnchor r2 = r15.mRight
            android.support.constraint.solver.widgets.ConstraintAnchor r2 = r2.mTarget
            if (r2 == 0) goto L_0x0148
            android.support.constraint.solver.widgets.ConstraintAnchor r2 = r15.mRight
            android.support.constraint.solver.widgets.ConstraintAnchor r2 = r2.mTarget
            android.support.constraint.solver.widgets.ConstraintWidget r2 = r2.mOwner
            android.support.constraint.solver.widgets.ConstraintWidget r3 = r15.mParent
            if (r2 != r3) goto L_0x0148
            android.support.constraint.solver.widgets.ConstraintAnchor r2 = r15.mRight
            android.support.constraint.solver.widgets.ConstraintAnchor$ConnectionType r3 = android.support.constraint.solver.widgets.ConstraintAnchor.ConnectionType.STRICT
            r2.setConnectionType(r3)
            goto L_0x0148
        L_0x0132:
            android.support.constraint.solver.widgets.ConstraintWidget r2 = r15.mParent
            android.support.constraint.solver.widgets.ConstraintAnchor r2 = r2.mRight
            android.support.constraint.solver.SolverVariable r2 = r14.createObjectVariable(r2)
            android.support.constraint.solver.ArrayRow r3 = r45.createRow()
            android.support.constraint.solver.SolverVariable r4 = r45.createSlackVariable()
            r3.createRowGreaterThan(r2, r10, r4, r6)
            r14.addConstraint(r3)
        L_0x0148:
            android.support.constraint.solver.widgets.ConstraintWidget r2 = r15.mParent
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r2 = r2.getVerticalDimensionBehaviour()
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r3 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            if (r2 != r3) goto L_0x01d2
            if (r1 != 0) goto L_0x01d2
            android.support.constraint.solver.widgets.ConstraintAnchor r2 = r15.mTop
            android.support.constraint.solver.widgets.ConstraintAnchor r2 = r2.mTarget
            if (r2 == 0) goto L_0x017d
            android.support.constraint.solver.widgets.ConstraintAnchor r2 = r15.mTop
            android.support.constraint.solver.widgets.ConstraintAnchor r2 = r2.mTarget
            android.support.constraint.solver.widgets.ConstraintWidget r2 = r2.mOwner
            android.support.constraint.solver.widgets.ConstraintWidget r3 = r15.mParent
            if (r2 == r3) goto L_0x0165
            goto L_0x017d
        L_0x0165:
            android.support.constraint.solver.widgets.ConstraintAnchor r2 = r15.mTop
            android.support.constraint.solver.widgets.ConstraintAnchor r2 = r2.mTarget
            if (r2 == 0) goto L_0x0193
            android.support.constraint.solver.widgets.ConstraintAnchor r2 = r15.mTop
            android.support.constraint.solver.widgets.ConstraintAnchor r2 = r2.mTarget
            android.support.constraint.solver.widgets.ConstraintWidget r2 = r2.mOwner
            android.support.constraint.solver.widgets.ConstraintWidget r3 = r15.mParent
            if (r2 != r3) goto L_0x0193
            android.support.constraint.solver.widgets.ConstraintAnchor r2 = r15.mTop
            android.support.constraint.solver.widgets.ConstraintAnchor$ConnectionType r3 = android.support.constraint.solver.widgets.ConstraintAnchor.ConnectionType.STRICT
            r2.setConnectionType(r3)
            goto L_0x0193
        L_0x017d:
            android.support.constraint.solver.widgets.ConstraintWidget r2 = r15.mParent
            android.support.constraint.solver.widgets.ConstraintAnchor r2 = r2.mTop
            android.support.constraint.solver.SolverVariable r2 = r14.createObjectVariable(r2)
            android.support.constraint.solver.ArrayRow r3 = r45.createRow()
            android.support.constraint.solver.SolverVariable r4 = r45.createSlackVariable()
            r3.createRowGreaterThan(r9, r2, r4, r6)
            r14.addConstraint(r3)
        L_0x0193:
            android.support.constraint.solver.widgets.ConstraintAnchor r2 = r15.mBottom
            android.support.constraint.solver.widgets.ConstraintAnchor r2 = r2.mTarget
            if (r2 == 0) goto L_0x01bc
            android.support.constraint.solver.widgets.ConstraintAnchor r2 = r15.mBottom
            android.support.constraint.solver.widgets.ConstraintAnchor r2 = r2.mTarget
            android.support.constraint.solver.widgets.ConstraintWidget r2 = r2.mOwner
            android.support.constraint.solver.widgets.ConstraintWidget r3 = r15.mParent
            if (r2 == r3) goto L_0x01a4
            goto L_0x01bc
        L_0x01a4:
            android.support.constraint.solver.widgets.ConstraintAnchor r2 = r15.mBottom
            android.support.constraint.solver.widgets.ConstraintAnchor r2 = r2.mTarget
            if (r2 == 0) goto L_0x01d2
            android.support.constraint.solver.widgets.ConstraintAnchor r2 = r15.mBottom
            android.support.constraint.solver.widgets.ConstraintAnchor r2 = r2.mTarget
            android.support.constraint.solver.widgets.ConstraintWidget r2 = r2.mOwner
            android.support.constraint.solver.widgets.ConstraintWidget r3 = r15.mParent
            if (r2 != r3) goto L_0x01d2
            android.support.constraint.solver.widgets.ConstraintAnchor r2 = r15.mBottom
            android.support.constraint.solver.widgets.ConstraintAnchor$ConnectionType r3 = android.support.constraint.solver.widgets.ConstraintAnchor.ConnectionType.STRICT
            r2.setConnectionType(r3)
            goto L_0x01d2
        L_0x01bc:
            android.support.constraint.solver.widgets.ConstraintWidget r2 = r15.mParent
            android.support.constraint.solver.widgets.ConstraintAnchor r2 = r2.mBottom
            android.support.constraint.solver.SolverVariable r2 = r14.createObjectVariable(r2)
            android.support.constraint.solver.ArrayRow r3 = r45.createRow()
            android.support.constraint.solver.SolverVariable r4 = r45.createSlackVariable()
            r3.createRowGreaterThan(r2, r8, r4, r6)
            r14.addConstraint(r3)
        L_0x01d2:
            r16 = r0
            r17 = r1
            goto L_0x01db
        L_0x01d7:
            r16 = r6
            r17 = r16
        L_0x01db:
            int r0 = r15.mWidth
            int r1 = r15.mMinWidth
            if (r0 >= r1) goto L_0x01e3
            int r0 = r15.mMinWidth
        L_0x01e3:
            int r1 = r15.mHeight
            int r2 = r15.mMinHeight
            if (r1 >= r2) goto L_0x01eb
            int r1 = r15.mMinHeight
        L_0x01eb:
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r2 = r15.mHorizontalDimensionBehaviour
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r3 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r2 == r3) goto L_0x01f3
            r2 = r5
            goto L_0x01f4
        L_0x01f3:
            r2 = r6
        L_0x01f4:
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r3 = r15.mVerticalDimensionBehaviour
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r4 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r3 == r4) goto L_0x01fc
            r3 = r5
            goto L_0x01fd
        L_0x01fc:
            r3 = r6
        L_0x01fd:
            if (r2 != 0) goto L_0x0214
            android.support.constraint.solver.widgets.ConstraintAnchor r4 = r15.mLeft
            if (r4 == 0) goto L_0x0214
            android.support.constraint.solver.widgets.ConstraintAnchor r4 = r15.mRight
            if (r4 == 0) goto L_0x0214
            android.support.constraint.solver.widgets.ConstraintAnchor r4 = r15.mLeft
            android.support.constraint.solver.widgets.ConstraintAnchor r4 = r4.mTarget
            if (r4 == 0) goto L_0x0213
            android.support.constraint.solver.widgets.ConstraintAnchor r4 = r15.mRight
            android.support.constraint.solver.widgets.ConstraintAnchor r4 = r4.mTarget
            if (r4 != 0) goto L_0x0214
        L_0x0213:
            r2 = r5
        L_0x0214:
            if (r3 != 0) goto L_0x023f
            android.support.constraint.solver.widgets.ConstraintAnchor r4 = r15.mTop
            if (r4 == 0) goto L_0x023f
            android.support.constraint.solver.widgets.ConstraintAnchor r4 = r15.mBottom
            if (r4 == 0) goto L_0x023f
            android.support.constraint.solver.widgets.ConstraintAnchor r4 = r15.mTop
            android.support.constraint.solver.widgets.ConstraintAnchor r4 = r4.mTarget
            if (r4 == 0) goto L_0x022a
            android.support.constraint.solver.widgets.ConstraintAnchor r4 = r15.mBottom
            android.support.constraint.solver.widgets.ConstraintAnchor r4 = r4.mTarget
            if (r4 != 0) goto L_0x023f
        L_0x022a:
            int r4 = r15.mBaselineDistance
            if (r4 == 0) goto L_0x023e
            android.support.constraint.solver.widgets.ConstraintAnchor r4 = r15.mBaseline
            if (r4 == 0) goto L_0x023f
            android.support.constraint.solver.widgets.ConstraintAnchor r4 = r15.mTop
            android.support.constraint.solver.widgets.ConstraintAnchor r4 = r4.mTarget
            if (r4 == 0) goto L_0x023e
            android.support.constraint.solver.widgets.ConstraintAnchor r4 = r15.mBaseline
            android.support.constraint.solver.widgets.ConstraintAnchor r4 = r4.mTarget
            if (r4 != 0) goto L_0x023f
        L_0x023e:
            r3 = r5
        L_0x023f:
            int r4 = r15.mDimensionRatioSide
            float r5 = r15.mDimensionRatio
            float r6 = r15.mDimensionRatio
            r20 = 0
            int r6 = (r6 > r20 ? 1 : (r6 == r20 ? 0 : -1))
            r21 = r8
            r8 = -1
            if (r6 <= 0) goto L_0x02c9
            int r6 = r15.mVisibility
            r12 = 8
            if (r6 == r12) goto L_0x02c9
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r6 = r15.mHorizontalDimensionBehaviour
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r12 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            r20 = 1065353216(0x3f800000, float:1.0)
            if (r6 != r12) goto L_0x0295
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r6 = r15.mVerticalDimensionBehaviour
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r12 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r6 != r12) goto L_0x0295
            if (r2 == 0) goto L_0x026f
            if (r3 != 0) goto L_0x026f
            r23 = r0
            r6 = r1
            r24 = r3
            r25 = r5
            r12 = 0
            goto L_0x0292
        L_0x026f:
            if (r2 != 0) goto L_0x028a
            if (r3 == 0) goto L_0x028a
            int r4 = r15.mDimensionRatioSide
            if (r4 != r8) goto L_0x0281
            float r20 = r20 / r5
            r23 = r0
            r6 = r1
            r24 = r3
            r25 = r20
            goto L_0x0288
        L_0x0281:
            r23 = r0
            r6 = r1
            r24 = r3
            r25 = r5
        L_0x0288:
            r12 = 1
            goto L_0x0292
        L_0x028a:
            r23 = r0
            r6 = r1
            r24 = r3
            r12 = r4
            r25 = r5
        L_0x0292:
            r20 = 1
            goto L_0x02d3
        L_0x0295:
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r6 = r15.mHorizontalDimensionBehaviour
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r12 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r6 != r12) goto L_0x02ac
            int r0 = r15.mHeight
            float r0 = (float) r0
            float r0 = r0 * r5
            int r0 = (int) r0
            r23 = r0
            r6 = r1
            r24 = r3
            r25 = r5
            r3 = 1
            r12 = 0
            r20 = 0
            goto L_0x02d4
        L_0x02ac:
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r6 = r15.mVerticalDimensionBehaviour
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r12 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r6 != r12) goto L_0x02c9
            int r1 = r15.mDimensionRatioSide
            if (r1 != r8) goto L_0x02b8
            float r5 = r20 / r5
        L_0x02b8:
            int r1 = r15.mWidth
            float r1 = (float) r1
            float r1 = r1 * r5
            int r1 = (int) r1
            r23 = r0
            r6 = r1
            r3 = r2
            r25 = r5
            r12 = 1
            r20 = 0
            r24 = 1
            goto L_0x02d4
        L_0x02c9:
            r23 = r0
            r6 = r1
            r24 = r3
            r12 = r4
            r25 = r5
            r20 = 0
        L_0x02d3:
            r3 = r2
        L_0x02d4:
            if (r20 == 0) goto L_0x02dd
            if (r12 == 0) goto L_0x02da
            if (r12 != r8) goto L_0x02dd
        L_0x02da:
            r26 = 1
            goto L_0x02df
        L_0x02dd:
            r26 = 0
        L_0x02df:
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r0 = r15.mHorizontalDimensionBehaviour
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r1 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            if (r0 != r1) goto L_0x02eb
            boolean r0 = r15 instanceof android.support.constraint.solver.widgets.ConstraintWidgetContainer
            if (r0 == 0) goto L_0x02eb
            r2 = 1
            goto L_0x02ec
        L_0x02eb:
            r2 = 0
        L_0x02ec:
            int r0 = r15.mHorizontalResolution
            r5 = 2
            r4 = 3
            if (r0 == r5) goto L_0x03d7
            r1 = 2147483647(0x7fffffff, float:NaN)
            if (r13 == r1) goto L_0x0303
            android.support.constraint.solver.widgets.ConstraintAnchor r0 = r15.mLeft
            int r0 = r0.mGroup
            if (r0 != r13) goto L_0x03d7
            android.support.constraint.solver.widgets.ConstraintAnchor r0 = r15.mRight
            int r0 = r0.mGroup
            if (r0 != r13) goto L_0x03d7
        L_0x0303:
            if (r26 == 0) goto L_0x038d
            android.support.constraint.solver.widgets.ConstraintAnchor r0 = r15.mLeft
            android.support.constraint.solver.widgets.ConstraintAnchor r0 = r0.mTarget
            if (r0 == 0) goto L_0x038d
            android.support.constraint.solver.widgets.ConstraintAnchor r0 = r15.mRight
            android.support.constraint.solver.widgets.ConstraintAnchor r0 = r0.mTarget
            if (r0 == 0) goto L_0x038d
            android.support.constraint.solver.widgets.ConstraintAnchor r0 = r15.mLeft
            android.support.constraint.solver.SolverVariable r2 = r14.createObjectVariable(r0)
            android.support.constraint.solver.widgets.ConstraintAnchor r0 = r15.mRight
            android.support.constraint.solver.SolverVariable r3 = r14.createObjectVariable(r0)
            android.support.constraint.solver.widgets.ConstraintAnchor r0 = r15.mLeft
            android.support.constraint.solver.widgets.ConstraintAnchor r0 = r0.getTarget()
            android.support.constraint.solver.SolverVariable r0 = r14.createObjectVariable(r0)
            android.support.constraint.solver.widgets.ConstraintAnchor r1 = r15.mRight
            android.support.constraint.solver.widgets.ConstraintAnchor r1 = r1.getTarget()
            android.support.constraint.solver.SolverVariable r1 = r14.createObjectVariable(r1)
            android.support.constraint.solver.widgets.ConstraintAnchor r5 = r15.mLeft
            int r5 = r5.getMargin()
            r14.addGreaterThan(r2, r0, r5, r4)
            android.support.constraint.solver.widgets.ConstraintAnchor r5 = r15.mRight
            int r5 = r5.getMargin()
            int r5 = r5 * r8
            r14.addLowerThan(r3, r1, r5, r4)
            if (r16 != 0) goto L_0x0378
            android.support.constraint.solver.widgets.ConstraintAnchor r5 = r15.mLeft
            int r5 = r5.getMargin()
            float r4 = r15.mHorizontalBiasPercent
            android.support.constraint.solver.widgets.ConstraintAnchor r8 = r15.mRight
            int r8 = r8.getMargin()
            r16 = 4
            r22 = r0
            r0 = r14
            r23 = r1
            r27 = 2147483647(0x7fffffff, float:NaN)
            r1 = r2
            r2 = r22
            r22 = r3
            r3 = r5
            r5 = 3
            r5 = r23
            r28 = r6
            r18 = 0
            r6 = r22
            r29 = r7
            r7 = r8
            r30 = r21
            r8 = r16
            r0.addCentering(r1, r2, r3, r4, r5, r6, r7, r8)
            goto L_0x0383
        L_0x0378:
            r28 = r6
            r29 = r7
            r30 = r21
            r18 = 0
            r27 = 2147483647(0x7fffffff, float:NaN)
        L_0x0383:
            r32 = r9
            r33 = r10
            r34 = r11
            r35 = r12
            goto L_0x03e7
        L_0x038d:
            r27 = r1
            r28 = r6
            r29 = r7
            r30 = r21
            r18 = 0
            android.support.constraint.solver.widgets.ConstraintAnchor r4 = r15.mLeft
            android.support.constraint.solver.widgets.ConstraintAnchor r5 = r15.mRight
            int r6 = r15.mX
            int r0 = r15.mX
            int r7 = r0 + r23
            int r8 = r15.mMinWidth
            float r1 = r15.mHorizontalBiasPercent
            int r0 = r15.mMatchConstraintDefaultWidth
            int r13 = r15.mMatchConstraintMinWidth
            r31 = r13
            int r13 = r15.mMatchConstraintMaxWidth
            r19 = r0
            r0 = r15
            r21 = r1
            r1 = r14
            r22 = r8
            r8 = r23
            r32 = r9
            r9 = r22
            r33 = r10
            r10 = r21
            r34 = r11
            r11 = r26
            r35 = r12
            r12 = r16
            r21 = r13
            r16 = r31
            r13 = r19
            r14 = r16
            r15 = r21
            r0.applyConstraints(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15)
            r15 = r44
            goto L_0x03e7
        L_0x03d7:
            r28 = r6
            r29 = r7
            r32 = r9
            r33 = r10
            r34 = r11
            r35 = r12
            r30 = r21
            r18 = 0
        L_0x03e7:
            int r0 = r15.mVerticalResolution
            r1 = 2
            if (r0 != r1) goto L_0x03ed
            return
        L_0x03ed:
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r0 = r15.mVerticalDimensionBehaviour
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r1 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            if (r0 != r1) goto L_0x03f9
            boolean r0 = r15 instanceof android.support.constraint.solver.widgets.ConstraintWidgetContainer
            if (r0 == 0) goto L_0x03f9
            r2 = 1
            goto L_0x03fb
        L_0x03f9:
            r2 = r18
        L_0x03fb:
            if (r20 == 0) goto L_0x0409
            r14 = r35
            r13 = 1
            if (r14 == r13) goto L_0x0406
            r0 = -1
            if (r14 != r0) goto L_0x040d
            goto L_0x0407
        L_0x0406:
            r0 = -1
        L_0x0407:
            r11 = r13
            goto L_0x040f
        L_0x0409:
            r14 = r35
            r0 = -1
            r13 = 1
        L_0x040d:
            r11 = r18
        L_0x040f:
            int r1 = r15.mBaselineDistance
            if (r1 <= 0) goto L_0x0514
            android.support.constraint.solver.widgets.ConstraintAnchor r1 = r15.mBottom
            r12 = 5
            r9 = 2147483647(0x7fffffff, float:NaN)
            r10 = r46
            if (r10 == r9) goto L_0x042f
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r15.mBottom
            int r3 = r3.mGroup
            if (r3 != r10) goto L_0x042a
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r15.mBaseline
            int r3 = r3.mGroup
            if (r3 != r10) goto L_0x042a
            goto L_0x042f
        L_0x042a:
            r7 = r32
            r8 = r45
            goto L_0x043c
        L_0x042f:
            int r3 = r44.getBaselineDistance()
            r4 = r29
            r7 = r32
            r8 = r45
            r8.addEquality(r4, r7, r3, r12)
        L_0x043c:
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r15.mBaseline
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r3.mTarget
            if (r3 == 0) goto L_0x044a
            int r1 = r15.mBaselineDistance
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r15.mBaseline
            r16 = r1
            r5 = r3
            goto L_0x044d
        L_0x044a:
            r5 = r1
            r16 = r28
        L_0x044d:
            if (r10 == r9) goto L_0x0462
            android.support.constraint.solver.widgets.ConstraintAnchor r1 = r15.mTop
            int r1 = r1.mGroup
            if (r1 != r10) goto L_0x045a
            int r1 = r5.mGroup
            if (r1 != r10) goto L_0x045a
            goto L_0x0462
        L_0x045a:
            r15 = r8
            r39 = r14
            r13 = r30
            r14 = r7
            goto L_0x0534
        L_0x0462:
            if (r11 == 0) goto L_0x04cd
            android.support.constraint.solver.widgets.ConstraintAnchor r1 = r15.mTop
            android.support.constraint.solver.widgets.ConstraintAnchor r1 = r1.mTarget
            if (r1 == 0) goto L_0x04cd
            android.support.constraint.solver.widgets.ConstraintAnchor r1 = r15.mBottom
            android.support.constraint.solver.widgets.ConstraintAnchor r1 = r1.mTarget
            if (r1 == 0) goto L_0x04cd
            android.support.constraint.solver.widgets.ConstraintAnchor r1 = r15.mTop
            android.support.constraint.solver.SolverVariable r1 = r8.createObjectVariable(r1)
            android.support.constraint.solver.widgets.ConstraintAnchor r2 = r15.mBottom
            android.support.constraint.solver.SolverVariable r6 = r8.createObjectVariable(r2)
            android.support.constraint.solver.widgets.ConstraintAnchor r2 = r15.mTop
            android.support.constraint.solver.widgets.ConstraintAnchor r2 = r2.getTarget()
            android.support.constraint.solver.SolverVariable r2 = r8.createObjectVariable(r2)
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r15.mBottom
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r3.getTarget()
            android.support.constraint.solver.SolverVariable r5 = r8.createObjectVariable(r3)
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r15.mTop
            int r3 = r3.getMargin()
            r11 = 3
            r8.addGreaterThan(r1, r2, r3, r11)
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r15.mBottom
            int r3 = r3.getMargin()
            int r0 = r0 * r3
            r8.addLowerThan(r6, r5, r0, r11)
            if (r17 != 0) goto L_0x04c1
            android.support.constraint.solver.widgets.ConstraintAnchor r0 = r15.mTop
            int r3 = r0.getMargin()
            float r4 = r15.mVerticalBiasPercent
            android.support.constraint.solver.widgets.ConstraintAnchor r0 = r15.mBottom
            int r12 = r0.getMargin()
            r16 = 4
            r0 = r8
            r36 = r7
            r7 = r12
            r12 = r8
            r8 = r16
            r0.addCentering(r1, r2, r3, r4, r5, r6, r7, r8)
            goto L_0x04c4
        L_0x04c1:
            r36 = r7
            r12 = r8
        L_0x04c4:
            r15 = r12
            r39 = r14
            r13 = r30
            r14 = r36
            goto L_0x0534
        L_0x04cd:
            r36 = r7
            r7 = 3
            android.support.constraint.solver.widgets.ConstraintAnchor r4 = r15.mTop
            int r6 = r15.mY
            int r0 = r15.mY
            int r18 = r0 + r16
            int r3 = r15.mMinHeight
            float r1 = r15.mVerticalBiasPercent
            int r0 = r15.mMatchConstraintDefaultHeight
            r37 = r14
            int r14 = r15.mMatchConstraintMinHeight
            r38 = r14
            int r14 = r15.mMatchConstraintMaxHeight
            r19 = r0
            r0 = r15
            r21 = r1
            r1 = r8
            r22 = r3
            r3 = r24
            r7 = r18
            r8 = r16
            r9 = r22
            r10 = r21
            r12 = r17
            r13 = r19
            r16 = r14
            r39 = r37
            r14 = r38
            r15 = r16
            r0.applyConstraints(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15)
            r8 = r28
            r13 = r30
            r14 = r36
            r0 = 5
            r15 = r45
            r15.addEquality(r13, r14, r8, r0)
            goto L_0x0534
        L_0x0514:
            r39 = r14
            r8 = r28
            r13 = r30
            r14 = r32
            r10 = 2147483647(0x7fffffff, float:NaN)
            r12 = r46
            r15 = r45
            if (r12 == r10) goto L_0x053a
            r9 = r44
            android.support.constraint.solver.widgets.ConstraintAnchor r1 = r9.mTop
            int r1 = r1.mGroup
            if (r1 != r12) goto L_0x0534
            android.support.constraint.solver.widgets.ConstraintAnchor r1 = r9.mBottom
            int r1 = r1.mGroup
            if (r1 != r12) goto L_0x0534
            goto L_0x053c
        L_0x0534:
            r42 = r13
            r43 = r14
            goto L_0x05cf
        L_0x053a:
            r9 = r44
        L_0x053c:
            if (r11 == 0) goto L_0x0595
            android.support.constraint.solver.widgets.ConstraintAnchor r1 = r9.mTop
            android.support.constraint.solver.widgets.ConstraintAnchor r1 = r1.mTarget
            if (r1 == 0) goto L_0x0595
            android.support.constraint.solver.widgets.ConstraintAnchor r1 = r9.mBottom
            android.support.constraint.solver.widgets.ConstraintAnchor r1 = r1.mTarget
            if (r1 == 0) goto L_0x0595
            android.support.constraint.solver.widgets.ConstraintAnchor r1 = r9.mTop
            android.support.constraint.solver.SolverVariable r1 = r15.createObjectVariable(r1)
            android.support.constraint.solver.widgets.ConstraintAnchor r2 = r9.mBottom
            android.support.constraint.solver.SolverVariable r6 = r15.createObjectVariable(r2)
            android.support.constraint.solver.widgets.ConstraintAnchor r2 = r9.mTop
            android.support.constraint.solver.widgets.ConstraintAnchor r2 = r2.getTarget()
            android.support.constraint.solver.SolverVariable r2 = r15.createObjectVariable(r2)
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r9.mBottom
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r3.getTarget()
            android.support.constraint.solver.SolverVariable r5 = r15.createObjectVariable(r3)
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r9.mTop
            int r3 = r3.getMargin()
            r11 = 3
            r15.addGreaterThan(r1, r2, r3, r11)
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r9.mBottom
            int r3 = r3.getMargin()
            int r8 = r0 * r3
            r15.addLowerThan(r6, r5, r8, r11)
            if (r17 != 0) goto L_0x0534
            android.support.constraint.solver.widgets.ConstraintAnchor r0 = r9.mTop
            int r3 = r0.getMargin()
            float r4 = r9.mVerticalBiasPercent
            android.support.constraint.solver.widgets.ConstraintAnchor r0 = r9.mBottom
            int r7 = r0.getMargin()
            r8 = 4
            r0 = r15
            r0.addCentering(r1, r2, r3, r4, r5, r6, r7, r8)
            goto L_0x0534
        L_0x0595:
            r7 = 3
            android.support.constraint.solver.widgets.ConstraintAnchor r4 = r9.mTop
            android.support.constraint.solver.widgets.ConstraintAnchor r5 = r9.mBottom
            int r6 = r9.mY
            int r0 = r9.mY
            int r16 = r0 + r8
            int r3 = r9.mMinHeight
            float r1 = r9.mVerticalBiasPercent
            int r0 = r9.mMatchConstraintDefaultHeight
            r40 = r14
            int r14 = r9.mMatchConstraintMinHeight
            r41 = r14
            int r14 = r9.mMatchConstraintMaxHeight
            r18 = r0
            r0 = r9
            r19 = r1
            r1 = r15
            r21 = r3
            r3 = r24
            r7 = r16
            r9 = r21
            r10 = r19
            r12 = r17
            r42 = r13
            r13 = r18
            r16 = r14
            r43 = r40
            r14 = r41
            r15 = r16
            r0.applyConstraints(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15)
        L_0x05cf:
            if (r20 == 0) goto L_0x065f
            android.support.constraint.solver.ArrayRow r0 = r45.createRow()
            r1 = r46
            r2 = 2147483647(0x7fffffff, float:NaN)
            if (r1 == r2) goto L_0x05eb
            r8 = r44
            android.support.constraint.solver.widgets.ConstraintAnchor r2 = r8.mLeft
            int r2 = r2.mGroup
            if (r2 != r1) goto L_0x0661
            android.support.constraint.solver.widgets.ConstraintAnchor r2 = r8.mRight
            int r2 = r2.mGroup
            if (r2 != r1) goto L_0x0661
            goto L_0x05ed
        L_0x05eb:
            r8 = r44
        L_0x05ed:
            r4 = r39
            if (r4 != 0) goto L_0x0606
            r2 = r0
            r3 = r33
            r4 = r34
            r5 = r42
            r6 = r43
            r7 = r25
            android.support.constraint.solver.ArrayRow r0 = r2.createRowDimensionRatio(r3, r4, r5, r6, r7)
            r1 = r45
            r1.addConstraint(r0)
            goto L_0x0661
        L_0x0606:
            r1 = r45
            r2 = 1
            if (r4 != r2) goto L_0x061e
            r2 = r0
            r3 = r42
            r4 = r43
            r5 = r33
            r6 = r34
            r7 = r25
            android.support.constraint.solver.ArrayRow r0 = r2.createRowDimensionRatio(r3, r4, r5, r6, r7)
            r1.addConstraint(r0)
            goto L_0x0661
        L_0x061e:
            int r2 = r8.mMatchConstraintMinWidth
            if (r2 <= 0) goto L_0x062d
            int r2 = r8.mMatchConstraintMinWidth
            r3 = r33
            r4 = r34
            r5 = 3
            r1.addGreaterThan(r3, r4, r2, r5)
            goto L_0x0632
        L_0x062d:
            r3 = r33
            r4 = r34
            r5 = 3
        L_0x0632:
            int r2 = r8.mMatchConstraintMinHeight
            if (r2 <= 0) goto L_0x0640
            int r2 = r8.mMatchConstraintMinHeight
            r7 = r42
            r6 = r43
            r1.addGreaterThan(r7, r6, r2, r5)
            goto L_0x0644
        L_0x0640:
            r7 = r42
            r6 = r43
        L_0x0644:
            r9 = 4
            r2 = r0
            r5 = r7
            r7 = r25
            r2.createRowDimensionRatio(r3, r4, r5, r6, r7)
            android.support.constraint.solver.SolverVariable r2 = r45.createErrorVariable()
            android.support.constraint.solver.SolverVariable r3 = r45.createErrorVariable()
            r2.strength = r9
            r3.strength = r9
            r0.addError(r2, r3)
            r1.addConstraint(r0)
            goto L_0x0661
        L_0x065f:
            r8 = r44
        L_0x0661:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.constraint.solver.widgets.ConstraintWidget.addToSolver(android.support.constraint.solver.LinearSystem, int):void");
    }

    private void applyConstraints(LinearSystem linearSystem, boolean z, boolean z2, ConstraintAnchor constraintAnchor, ConstraintAnchor constraintAnchor2, int i, int i2, int i3, int i4, float f, boolean z3, boolean z4, int i5, int i6, int i7) {
        int i8;
        boolean z5;
        LinearSystem linearSystem2 = linearSystem;
        int i9 = i;
        int i10 = i2;
        int i11 = i4;
        int i12 = i6;
        int i13 = i7;
        SolverVariable createObjectVariable = linearSystem2.createObjectVariable(constraintAnchor);
        SolverVariable createObjectVariable2 = linearSystem2.createObjectVariable(constraintAnchor2);
        SolverVariable createObjectVariable3 = linearSystem2.createObjectVariable(constraintAnchor.getTarget());
        SolverVariable createObjectVariable4 = linearSystem2.createObjectVariable(constraintAnchor2.getTarget());
        int margin = constraintAnchor.getMargin();
        int margin2 = constraintAnchor2.getMargin();
        if (this.mVisibility == 8) {
            z5 = true;
            i8 = 0;
        } else {
            z5 = z2;
            i8 = i3;
        }
        if (createObjectVariable3 == null && createObjectVariable4 == null) {
            linearSystem2.addConstraint(linearSystem.createRow().createRowEquals(createObjectVariable, i9));
            if (z3) {
                return;
            }
            if (z) {
                linearSystem2.addConstraint(LinearSystem.createRowEquals(linearSystem2, createObjectVariable2, createObjectVariable, i11, true));
            } else if (z5) {
                linearSystem2.addConstraint(LinearSystem.createRowEquals(linearSystem2, createObjectVariable2, createObjectVariable, i8, false));
            } else {
                linearSystem2.addConstraint(linearSystem.createRow().createRowEquals(createObjectVariable2, i10));
            }
        } else if (createObjectVariable3 != null && createObjectVariable4 == null) {
            linearSystem2.addConstraint(linearSystem.createRow().createRowEquals(createObjectVariable, createObjectVariable3, margin));
            if (z) {
                linearSystem2.addConstraint(LinearSystem.createRowEquals(linearSystem2, createObjectVariable2, createObjectVariable, i11, true));
            } else if (z3) {
            } else {
                if (z5) {
                    linearSystem2.addConstraint(linearSystem.createRow().createRowEquals(createObjectVariable2, createObjectVariable, i8));
                } else {
                    linearSystem2.addConstraint(linearSystem.createRow().createRowEquals(createObjectVariable2, i10));
                }
            }
        } else if (createObjectVariable3 == null && createObjectVariable4 != null) {
            linearSystem2.addConstraint(linearSystem.createRow().createRowEquals(createObjectVariable2, createObjectVariable4, -1 * margin2));
            if (z) {
                linearSystem2.addConstraint(LinearSystem.createRowEquals(linearSystem2, createObjectVariable2, createObjectVariable, i11, true));
            } else if (z3) {
            } else {
                if (z5) {
                    linearSystem2.addConstraint(linearSystem.createRow().createRowEquals(createObjectVariable2, createObjectVariable, i8));
                } else {
                    linearSystem2.addConstraint(linearSystem.createRow().createRowEquals(createObjectVariable, i9));
                }
            }
        } else if (z5) {
            if (z) {
                linearSystem2.addConstraint(LinearSystem.createRowEquals(linearSystem2, createObjectVariable2, createObjectVariable, i11, true));
            } else {
                linearSystem2.addConstraint(linearSystem.createRow().createRowEquals(createObjectVariable2, createObjectVariable, i8));
            }
            if (constraintAnchor.getStrength() != constraintAnchor2.getStrength()) {
                if (constraintAnchor.getStrength() == ConstraintAnchor.Strength.STRONG) {
                    linearSystem2.addConstraint(linearSystem.createRow().createRowEquals(createObjectVariable, createObjectVariable3, margin));
                    SolverVariable createSlackVariable = linearSystem.createSlackVariable();
                    ArrayRow createRow = linearSystem.createRow();
                    createRow.createRowLowerThan(createObjectVariable2, createObjectVariable4, createSlackVariable, -1 * margin2);
                    linearSystem2.addConstraint(createRow);
                    return;
                }
                SolverVariable createSlackVariable2 = linearSystem.createSlackVariable();
                ArrayRow createRow2 = linearSystem.createRow();
                createRow2.createRowGreaterThan(createObjectVariable, createObjectVariable3, createSlackVariable2, margin);
                linearSystem2.addConstraint(createRow2);
                linearSystem2.addConstraint(linearSystem.createRow().createRowEquals(createObjectVariable2, createObjectVariable4, -1 * margin2));
            } else if (createObjectVariable3 == createObjectVariable4) {
                linearSystem2.addConstraint(LinearSystem.createRowCentering(linearSystem2, createObjectVariable, createObjectVariable3, 0, 0.5f, createObjectVariable4, createObjectVariable2, 0, true));
            } else if (!z4) {
                linearSystem2.addConstraint(LinearSystem.createRowGreaterThan(linearSystem2, createObjectVariable, createObjectVariable3, margin, constraintAnchor.getConnectionType() != ConstraintAnchor.ConnectionType.STRICT));
                linearSystem2.addConstraint(LinearSystem.createRowLowerThan(linearSystem2, createObjectVariable2, createObjectVariable4, -1 * margin2, constraintAnchor2.getConnectionType() != ConstraintAnchor.ConnectionType.STRICT));
                linearSystem2.addConstraint(LinearSystem.createRowCentering(linearSystem2, createObjectVariable, createObjectVariable3, margin, f, createObjectVariable4, createObjectVariable2, margin2, false));
            }
        } else if (z3) {
            linearSystem2.addGreaterThan(createObjectVariable, createObjectVariable3, margin, 3);
            linearSystem2.addLowerThan(createObjectVariable2, createObjectVariable4, -1 * margin2, 3);
            linearSystem2.addConstraint(LinearSystem.createRowCentering(linearSystem2, createObjectVariable, createObjectVariable3, margin, f, createObjectVariable4, createObjectVariable2, margin2, true));
        } else if (z4) {
        } else {
            if (i5 == 1) {
                int i14 = i6;
                if (i14 <= i8) {
                    i14 = i8;
                }
                int i15 = margin2;
                int i16 = i7;
                if (i16 > 0) {
                    if (i16 < i14) {
                        i14 = i16;
                    } else {
                        linearSystem2.addLowerThan(createObjectVariable2, createObjectVariable, i16, 3);
                    }
                }
                linearSystem2.addEquality(createObjectVariable2, createObjectVariable, i14, 3);
                linearSystem2.addGreaterThan(createObjectVariable, createObjectVariable3, margin, 2);
                linearSystem2.addLowerThan(createObjectVariable2, createObjectVariable4, -i15, 2);
                linearSystem2.addCentering(createObjectVariable, createObjectVariable3, margin, f, createObjectVariable4, createObjectVariable2, i15, 4);
                return;
            }
            int i17 = margin2;
            int i18 = i7;
            if (i6 == 0 && i18 == 0) {
                linearSystem2.addConstraint(linearSystem.createRow().createRowEquals(createObjectVariable, createObjectVariable3, margin));
                linearSystem2.addConstraint(linearSystem.createRow().createRowEquals(createObjectVariable2, createObjectVariable4, -1 * i17));
                return;
            }
            if (i18 > 0) {
                linearSystem2.addLowerThan(createObjectVariable2, createObjectVariable, i18, 3);
            }
            linearSystem2.addGreaterThan(createObjectVariable, createObjectVariable3, margin, 2);
            linearSystem2.addLowerThan(createObjectVariable2, createObjectVariable4, -i17, 2);
            linearSystem2.addCentering(createObjectVariable, createObjectVariable3, margin, f, createObjectVariable4, createObjectVariable2, i17, 4);
        }
    }

    public void updateFromSolver(LinearSystem linearSystem, int i) {
        if (i == Integer.MAX_VALUE) {
            setFrame(linearSystem.getObjectVariableValue(this.mLeft), linearSystem.getObjectVariableValue(this.mTop), linearSystem.getObjectVariableValue(this.mRight), linearSystem.getObjectVariableValue(this.mBottom));
        } else if (i == -2) {
            setFrame(this.mSolverLeft, this.mSolverTop, this.mSolverRight, this.mSolverBottom);
        } else {
            if (this.mLeft.mGroup == i) {
                this.mSolverLeft = linearSystem.getObjectVariableValue(this.mLeft);
            }
            if (this.mTop.mGroup == i) {
                this.mSolverTop = linearSystem.getObjectVariableValue(this.mTop);
            }
            if (this.mRight.mGroup == i) {
                this.mSolverRight = linearSystem.getObjectVariableValue(this.mRight);
            }
            if (this.mBottom.mGroup == i) {
                this.mSolverBottom = linearSystem.getObjectVariableValue(this.mBottom);
            }
        }
    }

    public void updateFromSolver(LinearSystem linearSystem) {
        updateFromSolver(linearSystem, Integer.MAX_VALUE);
    }
}
