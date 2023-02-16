package android.support.constraint.solver.widgets;

import android.support.constraint.solver.LinearSystem;
import android.support.constraint.solver.widgets.ConstraintAnchor;
import android.support.constraint.solver.widgets.ConstraintWidget;
import java.util.ArrayList;
import java.util.Arrays;

public class ConstraintWidgetContainer extends WidgetContainer {
    static boolean ALLOW_ROOT_GROUP = true;
    private static final int CHAIN_FIRST = 0;
    private static final int CHAIN_FIRST_VISIBLE = 2;
    private static final int CHAIN_LAST = 1;
    private static final int CHAIN_LAST_VISIBLE = 3;
    private static final boolean DEBUG = false;
    private static final boolean DEBUG_LAYOUT = false;
    private static final boolean DEBUG_OPTIMIZE = false;
    private static final int FLAG_CHAIN_DANGLING = 1;
    private static final int FLAG_CHAIN_OPTIMIZE = 0;
    private static final int FLAG_RECOMPUTE_BOUNDS = 2;
    private static final int MAX_ITERATIONS = 8;
    public static final int OPTIMIZATION_ALL = 2;
    public static final int OPTIMIZATION_BASIC = 4;
    public static final int OPTIMIZATION_CHAIN = 8;
    public static final int OPTIMIZATION_NONE = 1;
    private static final boolean USE_SNAPSHOT = true;
    private static final boolean USE_THREAD = false;
    private boolean[] flags = new boolean[3];
    protected LinearSystem mBackgroundSystem = null;
    private ConstraintWidget[] mChainEnds = new ConstraintWidget[4];
    private boolean mHeightMeasuredTooSmall = false;
    private ConstraintWidget[] mHorizontalChainsArray = new ConstraintWidget[4];
    private int mHorizontalChainsSize = 0;
    private ConstraintWidget[] mMatchConstraintsChainedWidgets = new ConstraintWidget[4];
    private int mOptimizationLevel = 2;
    int mPaddingBottom;
    int mPaddingLeft;
    int mPaddingRight;
    int mPaddingTop;
    private Snapshot mSnapshot;
    protected LinearSystem mSystem = new LinearSystem();
    private ConstraintWidget[] mVerticalChainsArray = new ConstraintWidget[4];
    private int mVerticalChainsSize = 0;
    private boolean mWidthMeasuredTooSmall = false;
    int mWrapHeight;
    int mWrapWidth;

    public String getType() {
        return "ConstraintLayout";
    }

    public boolean handlesInternalConstraints() {
        return false;
    }

    public ConstraintWidgetContainer() {
    }

    public ConstraintWidgetContainer(int i, int i2, int i3, int i4) {
        super(i, i2, i3, i4);
    }

    public ConstraintWidgetContainer(int i, int i2) {
        super(i, i2);
    }

    public void setOptimizationLevel(int i) {
        this.mOptimizationLevel = i;
    }

    public void reset() {
        this.mSystem.reset();
        this.mPaddingLeft = 0;
        this.mPaddingRight = 0;
        this.mPaddingTop = 0;
        this.mPaddingBottom = 0;
        super.reset();
    }

    public boolean isWidthMeasuredTooSmall() {
        return this.mWidthMeasuredTooSmall;
    }

    public boolean isHeightMeasuredTooSmall() {
        return this.mHeightMeasuredTooSmall;
    }

    public static ConstraintWidgetContainer createContainer(ConstraintWidgetContainer constraintWidgetContainer, String str, ArrayList<ConstraintWidget> arrayList, int i) {
        Rectangle bounds = getBounds(arrayList);
        if (bounds.width == 0 || bounds.height == 0) {
            return null;
        }
        if (i > 0) {
            int min = Math.min(bounds.x, bounds.y);
            if (i > min) {
                i = min;
            }
            bounds.grow(i, i);
        }
        constraintWidgetContainer.setOrigin(bounds.x, bounds.y);
        constraintWidgetContainer.setDimension(bounds.width, bounds.height);
        constraintWidgetContainer.setDebugName(str);
        ConstraintWidget parent = arrayList.get(0).getParent();
        int size = arrayList.size();
        for (int i2 = 0; i2 < size; i2++) {
            ConstraintWidget constraintWidget = arrayList.get(i2);
            if (constraintWidget.getParent() == parent) {
                constraintWidgetContainer.add(constraintWidget);
                constraintWidget.setX(constraintWidget.getX() - bounds.x);
                constraintWidget.setY(constraintWidget.getY() - bounds.y);
            }
        }
        return constraintWidgetContainer;
    }

    public boolean addChildrenToSolver(LinearSystem linearSystem, int i) {
        boolean z;
        addToSolver(linearSystem, i);
        int size = this.mChildren.size();
        if (this.mOptimizationLevel != 2 && this.mOptimizationLevel != 4) {
            z = true;
        } else if (optimize(linearSystem)) {
            return false;
        } else {
            z = false;
        }
        for (int i2 = 0; i2 < size; i2++) {
            ConstraintWidget constraintWidget = (ConstraintWidget) this.mChildren.get(i2);
            if (constraintWidget instanceof ConstraintWidgetContainer) {
                ConstraintWidget.DimensionBehaviour dimensionBehaviour = constraintWidget.mHorizontalDimensionBehaviour;
                ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = constraintWidget.mVerticalDimensionBehaviour;
                if (dimensionBehaviour == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
                    constraintWidget.setHorizontalDimensionBehaviour(ConstraintWidget.DimensionBehaviour.FIXED);
                }
                if (dimensionBehaviour2 == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
                    constraintWidget.setVerticalDimensionBehaviour(ConstraintWidget.DimensionBehaviour.FIXED);
                }
                constraintWidget.addToSolver(linearSystem, i);
                if (dimensionBehaviour == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
                    constraintWidget.setHorizontalDimensionBehaviour(dimensionBehaviour);
                }
                if (dimensionBehaviour2 == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
                    constraintWidget.setVerticalDimensionBehaviour(dimensionBehaviour2);
                }
            } else {
                if (z) {
                    Optimizer.checkMatchParent(this, linearSystem, constraintWidget);
                }
                constraintWidget.addToSolver(linearSystem, i);
            }
        }
        if (this.mHorizontalChainsSize > 0) {
            applyHorizontalChain(linearSystem);
        }
        if (this.mVerticalChainsSize > 0) {
            applyVerticalChain(linearSystem);
        }
        return true;
    }

    private boolean optimize(LinearSystem linearSystem) {
        int size = this.mChildren.size();
        for (int i = 0; i < size; i++) {
            ConstraintWidget constraintWidget = (ConstraintWidget) this.mChildren.get(i);
            constraintWidget.mHorizontalResolution = -1;
            constraintWidget.mVerticalResolution = -1;
            if (constraintWidget.mHorizontalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT || constraintWidget.mVerticalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                constraintWidget.mHorizontalResolution = 1;
                constraintWidget.mVerticalResolution = 1;
            }
        }
        boolean z = false;
        int i2 = 0;
        int i3 = 0;
        while (!z) {
            int i4 = 0;
            int i5 = 0;
            for (int i6 = 0; i6 < size; i6++) {
                ConstraintWidget constraintWidget2 = (ConstraintWidget) this.mChildren.get(i6);
                if (constraintWidget2.mHorizontalResolution == -1) {
                    if (this.mHorizontalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
                        constraintWidget2.mHorizontalResolution = 1;
                    } else {
                        Optimizer.checkHorizontalSimpleDependency(this, linearSystem, constraintWidget2);
                    }
                }
                if (constraintWidget2.mVerticalResolution == -1) {
                    if (this.mVerticalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
                        constraintWidget2.mVerticalResolution = 1;
                    } else {
                        Optimizer.checkVerticalSimpleDependency(this, linearSystem, constraintWidget2);
                    }
                }
                if (constraintWidget2.mVerticalResolution == -1) {
                    i4++;
                }
                if (constraintWidget2.mHorizontalResolution == -1) {
                    i5++;
                }
            }
            if ((i4 == 0 && i5 == 0) || (i2 == i4 && i3 == i5)) {
                z = true;
            }
            i2 = i4;
            i3 = i5;
        }
        int i7 = 0;
        int i8 = 0;
        for (int i9 = 0; i9 < size; i9++) {
            ConstraintWidget constraintWidget3 = (ConstraintWidget) this.mChildren.get(i9);
            if (constraintWidget3.mHorizontalResolution == 1 || constraintWidget3.mHorizontalResolution == -1) {
                i7++;
            }
            if (constraintWidget3.mVerticalResolution == 1 || constraintWidget3.mVerticalResolution == -1) {
                i8++;
            }
        }
        return i7 == 0 && i8 == 0;
    }

    /* JADX WARNING: Removed duplicated region for block: B:193:0x04b9  */
    /* JADX WARNING: Removed duplicated region for block: B:225:0x04bb A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void applyHorizontalChain(android.support.constraint.solver.LinearSystem r35) {
        /*
            r34 = this;
            r6 = r34
            r15 = r35
            r14 = 0
            r13 = r14
        L_0x0006:
            int r0 = r6.mHorizontalChainsSize
            if (r13 >= r0) goto L_0x051c
            android.support.constraint.solver.widgets.ConstraintWidget[] r0 = r6.mHorizontalChainsArray
            r12 = r0[r13]
            android.support.constraint.solver.widgets.ConstraintWidget[] r2 = r6.mChainEnds
            android.support.constraint.solver.widgets.ConstraintWidget[] r0 = r6.mHorizontalChainsArray
            r3 = r0[r13]
            r4 = 0
            boolean[] r5 = r6.flags
            r0 = r6
            r1 = r15
            int r0 = r0.countMatchConstraintsChainedWidgets(r1, r2, r3, r4, r5)
            android.support.constraint.solver.widgets.ConstraintWidget[] r1 = r6.mChainEnds
            r2 = 2
            r1 = r1[r2]
            if (r1 != 0) goto L_0x002a
        L_0x0024:
            r4 = r13
            r19 = r14
            r3 = r15
            goto L_0x0513
        L_0x002a:
            boolean[] r3 = r6.flags
            r4 = 1
            boolean r3 = r3[r4]
            if (r3 == 0) goto L_0x0055
            int r0 = r12.getDrawX()
        L_0x0035:
            if (r1 == 0) goto L_0x0024
            android.support.constraint.solver.widgets.ConstraintAnchor r2 = r1.mLeft
            android.support.constraint.solver.SolverVariable r2 = r2.mSolverVariable
            r15.addEquality(r2, r0)
            android.support.constraint.solver.widgets.ConstraintWidget r2 = r1.mHorizontalNextWidget
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r1.mLeft
            int r3 = r3.getMargin()
            int r4 = r1.getWidth()
            int r3 = r3 + r4
            android.support.constraint.solver.widgets.ConstraintAnchor r1 = r1.mRight
            int r1 = r1.getMargin()
            int r3 = r3 + r1
            int r0 = r0 + r3
            r1 = r2
            goto L_0x0035
        L_0x0055:
            int r3 = r12.mHorizontalChainStyle
            if (r3 != 0) goto L_0x005b
            r3 = r4
            goto L_0x005c
        L_0x005b:
            r3 = r14
        L_0x005c:
            int r5 = r12.mHorizontalChainStyle
            if (r5 != r2) goto L_0x0062
            r5 = r4
            goto L_0x0063
        L_0x0062:
            r5 = r14
        L_0x0063:
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r7 = r6.mHorizontalDimensionBehaviour
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r8 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            if (r7 != r8) goto L_0x006b
            r7 = r4
            goto L_0x006c
        L_0x006b:
            r7 = r14
        L_0x006c:
            int r8 = r6.mOptimizationLevel
            if (r8 == r2) goto L_0x0076
            int r8 = r6.mOptimizationLevel
            r9 = 8
            if (r8 != r9) goto L_0x008c
        L_0x0076:
            boolean[] r8 = r6.flags
            boolean r8 = r8[r14]
            if (r8 == 0) goto L_0x008c
            boolean r8 = r12.mHorizontalChainFixedPosition
            if (r8 == 0) goto L_0x008c
            if (r5 != 0) goto L_0x008c
            if (r7 != 0) goto L_0x008c
            int r7 = r12.mHorizontalChainStyle
            if (r7 != 0) goto L_0x008c
            android.support.constraint.solver.widgets.Optimizer.applyDirectResolutionHorizontalChain(r6, r15, r0, r12)
            goto L_0x0024
        L_0x008c:
            r11 = 3
            r16 = 0
            if (r0 == 0) goto L_0x0349
            if (r5 == 0) goto L_0x0095
            goto L_0x0349
        L_0x0095:
            r3 = 0
            r5 = r3
            r3 = r16
        L_0x0099:
            if (r1 == 0) goto L_0x015d
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r7 = r1.mHorizontalDimensionBehaviour
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r8 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r7 == r8) goto L_0x0117
            android.support.constraint.solver.widgets.ConstraintAnchor r7 = r1.mLeft
            int r7 = r7.getMargin()
            if (r3 == 0) goto L_0x00b0
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r3.mRight
            int r3 = r3.getMargin()
            int r7 = r7 + r3
        L_0x00b0:
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r1.mLeft
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r3.mTarget
            android.support.constraint.solver.widgets.ConstraintWidget r3 = r3.mOwner
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r3 = r3.mHorizontalDimensionBehaviour
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r8 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r3 != r8) goto L_0x00be
            r3 = r2
            goto L_0x00bf
        L_0x00be:
            r3 = r11
        L_0x00bf:
            android.support.constraint.solver.widgets.ConstraintAnchor r8 = r1.mLeft
            android.support.constraint.solver.SolverVariable r8 = r8.mSolverVariable
            android.support.constraint.solver.widgets.ConstraintAnchor r9 = r1.mLeft
            android.support.constraint.solver.widgets.ConstraintAnchor r9 = r9.mTarget
            android.support.constraint.solver.SolverVariable r9 = r9.mSolverVariable
            r15.addGreaterThan(r8, r9, r7, r3)
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r1.mRight
            int r3 = r3.getMargin()
            android.support.constraint.solver.widgets.ConstraintAnchor r7 = r1.mRight
            android.support.constraint.solver.widgets.ConstraintAnchor r7 = r7.mTarget
            android.support.constraint.solver.widgets.ConstraintWidget r7 = r7.mOwner
            android.support.constraint.solver.widgets.ConstraintAnchor r7 = r7.mLeft
            android.support.constraint.solver.widgets.ConstraintAnchor r7 = r7.mTarget
            if (r7 == 0) goto L_0x00f9
            android.support.constraint.solver.widgets.ConstraintAnchor r7 = r1.mRight
            android.support.constraint.solver.widgets.ConstraintAnchor r7 = r7.mTarget
            android.support.constraint.solver.widgets.ConstraintWidget r7 = r7.mOwner
            android.support.constraint.solver.widgets.ConstraintAnchor r7 = r7.mLeft
            android.support.constraint.solver.widgets.ConstraintAnchor r7 = r7.mTarget
            android.support.constraint.solver.widgets.ConstraintWidget r7 = r7.mOwner
            if (r7 != r1) goto L_0x00f9
            android.support.constraint.solver.widgets.ConstraintAnchor r7 = r1.mRight
            android.support.constraint.solver.widgets.ConstraintAnchor r7 = r7.mTarget
            android.support.constraint.solver.widgets.ConstraintWidget r7 = r7.mOwner
            android.support.constraint.solver.widgets.ConstraintAnchor r7 = r7.mLeft
            int r7 = r7.getMargin()
            int r3 = r3 + r7
        L_0x00f9:
            android.support.constraint.solver.widgets.ConstraintAnchor r7 = r1.mRight
            android.support.constraint.solver.widgets.ConstraintAnchor r7 = r7.mTarget
            android.support.constraint.solver.widgets.ConstraintWidget r7 = r7.mOwner
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r7 = r7.mHorizontalDimensionBehaviour
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r8 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r7 != r8) goto L_0x0107
            r7 = r2
            goto L_0x0108
        L_0x0107:
            r7 = r11
        L_0x0108:
            android.support.constraint.solver.widgets.ConstraintAnchor r8 = r1.mRight
            android.support.constraint.solver.SolverVariable r8 = r8.mSolverVariable
            android.support.constraint.solver.widgets.ConstraintAnchor r9 = r1.mRight
            android.support.constraint.solver.widgets.ConstraintAnchor r9 = r9.mTarget
            android.support.constraint.solver.SolverVariable r9 = r9.mSolverVariable
            int r3 = -r3
            r15.addLowerThan(r8, r9, r3, r7)
            goto L_0x0154
        L_0x0117:
            float r3 = r1.mHorizontalWeight
            float r5 = r5 + r3
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r1.mRight
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r3.mTarget
            if (r3 == 0) goto L_0x013a
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r1.mRight
            int r3 = r3.getMargin()
            android.support.constraint.solver.widgets.ConstraintWidget[] r7 = r6.mChainEnds
            r7 = r7[r11]
            if (r1 == r7) goto L_0x013b
            android.support.constraint.solver.widgets.ConstraintAnchor r7 = r1.mRight
            android.support.constraint.solver.widgets.ConstraintAnchor r7 = r7.mTarget
            android.support.constraint.solver.widgets.ConstraintWidget r7 = r7.mOwner
            android.support.constraint.solver.widgets.ConstraintAnchor r7 = r7.mLeft
            int r7 = r7.getMargin()
            int r3 = r3 + r7
            goto L_0x013b
        L_0x013a:
            r3 = r14
        L_0x013b:
            android.support.constraint.solver.widgets.ConstraintAnchor r7 = r1.mRight
            android.support.constraint.solver.SolverVariable r7 = r7.mSolverVariable
            android.support.constraint.solver.widgets.ConstraintAnchor r8 = r1.mLeft
            android.support.constraint.solver.SolverVariable r8 = r8.mSolverVariable
            r15.addGreaterThan(r7, r8, r14, r4)
            android.support.constraint.solver.widgets.ConstraintAnchor r7 = r1.mRight
            android.support.constraint.solver.SolverVariable r7 = r7.mSolverVariable
            android.support.constraint.solver.widgets.ConstraintAnchor r8 = r1.mRight
            android.support.constraint.solver.widgets.ConstraintAnchor r8 = r8.mTarget
            android.support.constraint.solver.SolverVariable r8 = r8.mSolverVariable
            int r3 = -r3
            r15.addLowerThan(r7, r8, r3, r4)
        L_0x0154:
            android.support.constraint.solver.widgets.ConstraintWidget r3 = r1.mHorizontalNextWidget
            r33 = r3
            r3 = r1
            r1 = r33
            goto L_0x0099
        L_0x015d:
            if (r0 != r4) goto L_0x01e4
            android.support.constraint.solver.widgets.ConstraintWidget[] r0 = r6.mMatchConstraintsChainedWidgets
            r0 = r0[r14]
            android.support.constraint.solver.widgets.ConstraintAnchor r1 = r0.mLeft
            int r1 = r1.getMargin()
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r0.mLeft
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r3.mTarget
            if (r3 == 0) goto L_0x0178
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r0.mLeft
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r3.mTarget
            int r3 = r3.getMargin()
            int r1 = r1 + r3
        L_0x0178:
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r0.mRight
            int r3 = r3.getMargin()
            android.support.constraint.solver.widgets.ConstraintAnchor r5 = r0.mRight
            android.support.constraint.solver.widgets.ConstraintAnchor r5 = r5.mTarget
            if (r5 == 0) goto L_0x018d
            android.support.constraint.solver.widgets.ConstraintAnchor r5 = r0.mRight
            android.support.constraint.solver.widgets.ConstraintAnchor r5 = r5.mTarget
            int r5 = r5.getMargin()
            int r3 = r3 + r5
        L_0x018d:
            android.support.constraint.solver.widgets.ConstraintAnchor r5 = r12.mRight
            android.support.constraint.solver.widgets.ConstraintAnchor r5 = r5.mTarget
            android.support.constraint.solver.SolverVariable r5 = r5.mSolverVariable
            android.support.constraint.solver.widgets.ConstraintWidget[] r7 = r6.mChainEnds
            r7 = r7[r11]
            if (r0 != r7) goto L_0x01a3
            android.support.constraint.solver.widgets.ConstraintWidget[] r5 = r6.mChainEnds
            r5 = r5[r4]
            android.support.constraint.solver.widgets.ConstraintAnchor r5 = r5.mRight
            android.support.constraint.solver.widgets.ConstraintAnchor r5 = r5.mTarget
            android.support.constraint.solver.SolverVariable r5 = r5.mSolverVariable
        L_0x01a3:
            int r7 = r0.mMatchConstraintDefaultWidth
            if (r7 != r4) goto L_0x01cd
            android.support.constraint.solver.widgets.ConstraintAnchor r0 = r12.mLeft
            android.support.constraint.solver.SolverVariable r0 = r0.mSolverVariable
            android.support.constraint.solver.widgets.ConstraintAnchor r7 = r12.mLeft
            android.support.constraint.solver.widgets.ConstraintAnchor r7 = r7.mTarget
            android.support.constraint.solver.SolverVariable r7 = r7.mSolverVariable
            r15.addGreaterThan(r0, r7, r1, r4)
            android.support.constraint.solver.widgets.ConstraintAnchor r0 = r12.mRight
            android.support.constraint.solver.SolverVariable r0 = r0.mSolverVariable
            int r1 = -r3
            r15.addLowerThan(r0, r5, r1, r4)
            android.support.constraint.solver.widgets.ConstraintAnchor r0 = r12.mRight
            android.support.constraint.solver.SolverVariable r0 = r0.mSolverVariable
            android.support.constraint.solver.widgets.ConstraintAnchor r1 = r12.mLeft
            android.support.constraint.solver.SolverVariable r1 = r1.mSolverVariable
            int r3 = r12.getWidth()
            r15.addEquality(r0, r1, r3, r2)
            goto L_0x0024
        L_0x01cd:
            android.support.constraint.solver.widgets.ConstraintAnchor r2 = r0.mLeft
            android.support.constraint.solver.SolverVariable r2 = r2.mSolverVariable
            android.support.constraint.solver.widgets.ConstraintAnchor r7 = r0.mLeft
            android.support.constraint.solver.widgets.ConstraintAnchor r7 = r7.mTarget
            android.support.constraint.solver.SolverVariable r7 = r7.mSolverVariable
            r15.addEquality(r2, r7, r1, r4)
            android.support.constraint.solver.widgets.ConstraintAnchor r0 = r0.mRight
            android.support.constraint.solver.SolverVariable r0 = r0.mSolverVariable
            int r1 = -r3
            r15.addEquality(r0, r5, r1, r4)
            goto L_0x0024
        L_0x01e4:
            r1 = r14
        L_0x01e5:
            int r3 = r0 + -1
            if (r1 >= r3) goto L_0x0024
            android.support.constraint.solver.widgets.ConstraintWidget[] r7 = r6.mMatchConstraintsChainedWidgets
            r7 = r7[r1]
            android.support.constraint.solver.widgets.ConstraintWidget[] r8 = r6.mMatchConstraintsChainedWidgets
            int r1 = r1 + 1
            r8 = r8[r1]
            android.support.constraint.solver.widgets.ConstraintAnchor r9 = r7.mLeft
            android.support.constraint.solver.SolverVariable r9 = r9.mSolverVariable
            android.support.constraint.solver.widgets.ConstraintAnchor r10 = r7.mRight
            android.support.constraint.solver.SolverVariable r10 = r10.mSolverVariable
            android.support.constraint.solver.widgets.ConstraintAnchor r14 = r8.mLeft
            android.support.constraint.solver.SolverVariable r14 = r14.mSolverVariable
            android.support.constraint.solver.widgets.ConstraintAnchor r2 = r8.mRight
            android.support.constraint.solver.SolverVariable r2 = r2.mSolverVariable
            android.support.constraint.solver.widgets.ConstraintWidget[] r4 = r6.mChainEnds
            r4 = r4[r11]
            if (r8 != r4) goto L_0x0212
            android.support.constraint.solver.widgets.ConstraintWidget[] r2 = r6.mChainEnds
            r4 = 1
            r2 = r2[r4]
            android.support.constraint.solver.widgets.ConstraintAnchor r2 = r2.mRight
            android.support.constraint.solver.SolverVariable r2 = r2.mSolverVariable
        L_0x0212:
            android.support.constraint.solver.widgets.ConstraintAnchor r4 = r7.mLeft
            int r4 = r4.getMargin()
            android.support.constraint.solver.widgets.ConstraintAnchor r11 = r7.mLeft
            android.support.constraint.solver.widgets.ConstraintAnchor r11 = r11.mTarget
            if (r11 == 0) goto L_0x0245
            android.support.constraint.solver.widgets.ConstraintAnchor r11 = r7.mLeft
            android.support.constraint.solver.widgets.ConstraintAnchor r11 = r11.mTarget
            android.support.constraint.solver.widgets.ConstraintWidget r11 = r11.mOwner
            android.support.constraint.solver.widgets.ConstraintAnchor r11 = r11.mRight
            android.support.constraint.solver.widgets.ConstraintAnchor r11 = r11.mTarget
            if (r11 == 0) goto L_0x0245
            android.support.constraint.solver.widgets.ConstraintAnchor r11 = r7.mLeft
            android.support.constraint.solver.widgets.ConstraintAnchor r11 = r11.mTarget
            android.support.constraint.solver.widgets.ConstraintWidget r11 = r11.mOwner
            android.support.constraint.solver.widgets.ConstraintAnchor r11 = r11.mRight
            android.support.constraint.solver.widgets.ConstraintAnchor r11 = r11.mTarget
            android.support.constraint.solver.widgets.ConstraintWidget r11 = r11.mOwner
            if (r11 != r7) goto L_0x0245
            android.support.constraint.solver.widgets.ConstraintAnchor r11 = r7.mLeft
            android.support.constraint.solver.widgets.ConstraintAnchor r11 = r11.mTarget
            android.support.constraint.solver.widgets.ConstraintWidget r11 = r11.mOwner
            android.support.constraint.solver.widgets.ConstraintAnchor r11 = r11.mRight
            int r11 = r11.getMargin()
            int r4 = r4 + r11
        L_0x0245:
            android.support.constraint.solver.widgets.ConstraintAnchor r11 = r7.mLeft
            android.support.constraint.solver.widgets.ConstraintAnchor r11 = r11.mTarget
            android.support.constraint.solver.SolverVariable r11 = r11.mSolverVariable
            r28 = r0
            r0 = 2
            r15.addGreaterThan(r9, r11, r4, r0)
            android.support.constraint.solver.widgets.ConstraintAnchor r0 = r7.mRight
            int r0 = r0.getMargin()
            android.support.constraint.solver.widgets.ConstraintAnchor r4 = r7.mRight
            android.support.constraint.solver.widgets.ConstraintAnchor r4 = r4.mTarget
            if (r4 == 0) goto L_0x0274
            android.support.constraint.solver.widgets.ConstraintWidget r4 = r7.mHorizontalNextWidget
            if (r4 == 0) goto L_0x0274
            android.support.constraint.solver.widgets.ConstraintWidget r4 = r7.mHorizontalNextWidget
            android.support.constraint.solver.widgets.ConstraintAnchor r4 = r4.mLeft
            android.support.constraint.solver.widgets.ConstraintAnchor r4 = r4.mTarget
            if (r4 == 0) goto L_0x0272
            android.support.constraint.solver.widgets.ConstraintWidget r4 = r7.mHorizontalNextWidget
            android.support.constraint.solver.widgets.ConstraintAnchor r4 = r4.mLeft
            int r4 = r4.getMargin()
            goto L_0x0273
        L_0x0272:
            r4 = 0
        L_0x0273:
            int r0 = r0 + r4
        L_0x0274:
            android.support.constraint.solver.widgets.ConstraintAnchor r4 = r7.mRight
            android.support.constraint.solver.widgets.ConstraintAnchor r4 = r4.mTarget
            android.support.constraint.solver.SolverVariable r4 = r4.mSolverVariable
            int r0 = -r0
            r11 = 2
            r15.addLowerThan(r10, r4, r0, r11)
            if (r1 != r3) goto L_0x0301
            android.support.constraint.solver.widgets.ConstraintAnchor r0 = r8.mLeft
            int r0 = r0.getMargin()
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r8.mLeft
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r3.mTarget
            if (r3 == 0) goto L_0x02b4
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r8.mLeft
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r3.mTarget
            android.support.constraint.solver.widgets.ConstraintWidget r3 = r3.mOwner
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r3.mRight
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r3.mTarget
            if (r3 == 0) goto L_0x02b4
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r8.mLeft
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r3.mTarget
            android.support.constraint.solver.widgets.ConstraintWidget r3 = r3.mOwner
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r3.mRight
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r3.mTarget
            android.support.constraint.solver.widgets.ConstraintWidget r3 = r3.mOwner
            if (r3 != r8) goto L_0x02b4
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r8.mLeft
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r3.mTarget
            android.support.constraint.solver.widgets.ConstraintWidget r3 = r3.mOwner
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r3.mRight
            int r3 = r3.getMargin()
            int r0 = r0 + r3
        L_0x02b4:
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r8.mLeft
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r3.mTarget
            android.support.constraint.solver.SolverVariable r3 = r3.mSolverVariable
            r4 = 2
            r15.addGreaterThan(r14, r3, r0, r4)
            android.support.constraint.solver.widgets.ConstraintAnchor r0 = r8.mRight
            android.support.constraint.solver.widgets.ConstraintWidget[] r3 = r6.mChainEnds
            r4 = 3
            r3 = r3[r4]
            if (r8 != r3) goto L_0x02ce
            android.support.constraint.solver.widgets.ConstraintWidget[] r0 = r6.mChainEnds
            r3 = 1
            r0 = r0[r3]
            android.support.constraint.solver.widgets.ConstraintAnchor r0 = r0.mRight
        L_0x02ce:
            int r3 = r0.getMargin()
            android.support.constraint.solver.widgets.ConstraintAnchor r4 = r0.mTarget
            if (r4 == 0) goto L_0x02f7
            android.support.constraint.solver.widgets.ConstraintAnchor r4 = r0.mTarget
            android.support.constraint.solver.widgets.ConstraintWidget r4 = r4.mOwner
            android.support.constraint.solver.widgets.ConstraintAnchor r4 = r4.mLeft
            android.support.constraint.solver.widgets.ConstraintAnchor r4 = r4.mTarget
            if (r4 == 0) goto L_0x02f7
            android.support.constraint.solver.widgets.ConstraintAnchor r4 = r0.mTarget
            android.support.constraint.solver.widgets.ConstraintWidget r4 = r4.mOwner
            android.support.constraint.solver.widgets.ConstraintAnchor r4 = r4.mLeft
            android.support.constraint.solver.widgets.ConstraintAnchor r4 = r4.mTarget
            android.support.constraint.solver.widgets.ConstraintWidget r4 = r4.mOwner
            if (r4 != r8) goto L_0x02f7
            android.support.constraint.solver.widgets.ConstraintAnchor r4 = r0.mTarget
            android.support.constraint.solver.widgets.ConstraintWidget r4 = r4.mOwner
            android.support.constraint.solver.widgets.ConstraintAnchor r4 = r4.mLeft
            int r4 = r4.getMargin()
            int r3 = r3 + r4
        L_0x02f7:
            android.support.constraint.solver.widgets.ConstraintAnchor r0 = r0.mTarget
            android.support.constraint.solver.SolverVariable r0 = r0.mSolverVariable
            int r3 = -r3
            r4 = 2
            r15.addLowerThan(r2, r0, r3, r4)
            goto L_0x0302
        L_0x0301:
            r4 = 2
        L_0x0302:
            int r0 = r12.mMatchConstraintMaxWidth
            if (r0 <= 0) goto L_0x030b
            int r0 = r12.mMatchConstraintMaxWidth
            r15.addLowerThan(r10, r9, r0, r4)
        L_0x030b:
            android.support.constraint.solver.ArrayRow r0 = r35.createRow()
            float r3 = r7.mHorizontalWeight
            float r11 = r8.mHorizontalWeight
            android.support.constraint.solver.widgets.ConstraintAnchor r4 = r7.mLeft
            int r21 = r4.getMargin()
            android.support.constraint.solver.widgets.ConstraintAnchor r4 = r7.mRight
            int r23 = r4.getMargin()
            android.support.constraint.solver.widgets.ConstraintAnchor r4 = r8.mLeft
            int r25 = r4.getMargin()
            android.support.constraint.solver.widgets.ConstraintAnchor r4 = r8.mRight
            int r27 = r4.getMargin()
            r16 = r0
            r17 = r3
            r18 = r5
            r19 = r11
            r20 = r9
            r22 = r10
            r24 = r14
            r26 = r2
            r16.createRowEqualDimension(r17, r18, r19, r20, r21, r22, r23, r24, r25, r26, r27)
            r15.addConstraint(r0)
            r0 = r28
            r2 = 2
            r4 = 1
            r11 = 3
            r14 = 0
            goto L_0x01e5
        L_0x0349:
            r0 = r1
            r2 = r16
            r4 = r2
            r7 = 0
        L_0x034e:
            if (r0 == 0) goto L_0x04ca
            android.support.constraint.solver.widgets.ConstraintWidget r8 = r0.mHorizontalNextWidget
            if (r8 != 0) goto L_0x035c
            android.support.constraint.solver.widgets.ConstraintWidget[] r2 = r6.mChainEnds
            r7 = 1
            r2 = r2[r7]
            r14 = r2
            r2 = 1
            goto L_0x035e
        L_0x035c:
            r14 = r2
            r2 = r7
        L_0x035e:
            if (r5 == 0) goto L_0x03b6
            android.support.constraint.solver.widgets.ConstraintAnchor r7 = r0.mLeft
            int r9 = r7.getMargin()
            if (r4 == 0) goto L_0x036f
            android.support.constraint.solver.widgets.ConstraintAnchor r4 = r4.mRight
            int r4 = r4.getMargin()
            int r9 = r9 + r4
        L_0x036f:
            if (r1 == r0) goto L_0x0373
            r4 = 3
            goto L_0x0374
        L_0x0373:
            r4 = 1
        L_0x0374:
            android.support.constraint.solver.SolverVariable r10 = r7.mSolverVariable
            android.support.constraint.solver.widgets.ConstraintAnchor r11 = r7.mTarget
            android.support.constraint.solver.SolverVariable r11 = r11.mSolverVariable
            r15.addGreaterThan(r10, r11, r9, r4)
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r4 = r0.mHorizontalDimensionBehaviour
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r9 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r4 != r9) goto L_0x03b4
            android.support.constraint.solver.widgets.ConstraintAnchor r4 = r0.mRight
            int r9 = r0.mMatchConstraintDefaultWidth
            r10 = 1
            if (r9 != r10) goto L_0x039e
            int r9 = r0.mMatchConstraintMinWidth
            int r10 = r0.getWidth()
            int r9 = java.lang.Math.max(r9, r10)
            android.support.constraint.solver.SolverVariable r4 = r4.mSolverVariable
            android.support.constraint.solver.SolverVariable r7 = r7.mSolverVariable
            r11 = 3
            r15.addEquality(r4, r7, r9, r11)
            goto L_0x0410
        L_0x039e:
            r11 = 3
            android.support.constraint.solver.SolverVariable r9 = r7.mSolverVariable
            android.support.constraint.solver.widgets.ConstraintAnchor r10 = r7.mTarget
            android.support.constraint.solver.SolverVariable r10 = r10.mSolverVariable
            int r6 = r7.mMargin
            r15.addGreaterThan(r9, r10, r6, r11)
            android.support.constraint.solver.SolverVariable r4 = r4.mSolverVariable
            android.support.constraint.solver.SolverVariable r6 = r7.mSolverVariable
            int r7 = r0.mMatchConstraintMinWidth
            r15.addLowerThan(r4, r6, r7, r11)
            goto L_0x0410
        L_0x03b4:
            r11 = 3
            goto L_0x0410
        L_0x03b6:
            r11 = 3
            r6 = 5
            if (r3 != 0) goto L_0x03e5
            if (r2 == 0) goto L_0x03e5
            if (r4 == 0) goto L_0x03e5
            android.support.constraint.solver.widgets.ConstraintAnchor r4 = r0.mRight
            android.support.constraint.solver.widgets.ConstraintAnchor r4 = r4.mTarget
            if (r4 != 0) goto L_0x03d0
            android.support.constraint.solver.widgets.ConstraintAnchor r4 = r0.mRight
            android.support.constraint.solver.SolverVariable r4 = r4.mSolverVariable
            int r6 = r0.getDrawRight()
            r15.addEquality(r4, r6)
            goto L_0x0410
        L_0x03d0:
            android.support.constraint.solver.widgets.ConstraintAnchor r4 = r0.mRight
            int r4 = r4.getMargin()
            android.support.constraint.solver.widgets.ConstraintAnchor r7 = r0.mRight
            android.support.constraint.solver.SolverVariable r7 = r7.mSolverVariable
            android.support.constraint.solver.widgets.ConstraintAnchor r9 = r14.mRight
            android.support.constraint.solver.widgets.ConstraintAnchor r9 = r9.mTarget
            android.support.constraint.solver.SolverVariable r9 = r9.mSolverVariable
            int r4 = -r4
            r15.addEquality(r7, r9, r4, r6)
            goto L_0x0410
        L_0x03e5:
            if (r3 != 0) goto L_0x041d
            if (r2 != 0) goto L_0x041d
            if (r4 != 0) goto L_0x041d
            android.support.constraint.solver.widgets.ConstraintAnchor r4 = r0.mLeft
            android.support.constraint.solver.widgets.ConstraintAnchor r4 = r4.mTarget
            if (r4 != 0) goto L_0x03fd
            android.support.constraint.solver.widgets.ConstraintAnchor r4 = r0.mLeft
            android.support.constraint.solver.SolverVariable r4 = r4.mSolverVariable
            int r6 = r0.getDrawX()
            r15.addEquality(r4, r6)
            goto L_0x0410
        L_0x03fd:
            android.support.constraint.solver.widgets.ConstraintAnchor r4 = r0.mLeft
            int r4 = r4.getMargin()
            android.support.constraint.solver.widgets.ConstraintAnchor r7 = r0.mLeft
            android.support.constraint.solver.SolverVariable r7 = r7.mSolverVariable
            android.support.constraint.solver.widgets.ConstraintAnchor r9 = r12.mLeft
            android.support.constraint.solver.widgets.ConstraintAnchor r9 = r9.mTarget
            android.support.constraint.solver.SolverVariable r9 = r9.mSolverVariable
            r15.addEquality(r7, r9, r4, r6)
        L_0x0410:
            r29 = r0
            r30 = r3
            r0 = r12
            r4 = r13
            r18 = r14
            r3 = r15
            r19 = 0
            goto L_0x04b7
        L_0x041d:
            android.support.constraint.solver.widgets.ConstraintAnchor r6 = r0.mLeft
            android.support.constraint.solver.widgets.ConstraintAnchor r7 = r0.mRight
            int r10 = r6.getMargin()
            int r9 = r7.getMargin()
            android.support.constraint.solver.SolverVariable r11 = r6.mSolverVariable
            r29 = r0
            android.support.constraint.solver.widgets.ConstraintAnchor r0 = r6.mTarget
            android.support.constraint.solver.SolverVariable r0 = r0.mSolverVariable
            r30 = r3
            r3 = 1
            r15.addGreaterThan(r11, r0, r10, r3)
            android.support.constraint.solver.SolverVariable r0 = r7.mSolverVariable
            android.support.constraint.solver.widgets.ConstraintAnchor r11 = r7.mTarget
            android.support.constraint.solver.SolverVariable r11 = r11.mSolverVariable
            r31 = r13
            int r13 = -r9
            r15.addLowerThan(r0, r11, r13, r3)
            android.support.constraint.solver.widgets.ConstraintAnchor r0 = r6.mTarget
            if (r0 == 0) goto L_0x044c
            android.support.constraint.solver.widgets.ConstraintAnchor r0 = r6.mTarget
            android.support.constraint.solver.SolverVariable r0 = r0.mSolverVariable
            goto L_0x044e
        L_0x044c:
            r0 = r16
        L_0x044e:
            if (r4 != 0) goto L_0x045f
            android.support.constraint.solver.widgets.ConstraintAnchor r0 = r12.mLeft
            android.support.constraint.solver.widgets.ConstraintAnchor r0 = r0.mTarget
            if (r0 == 0) goto L_0x045d
            android.support.constraint.solver.widgets.ConstraintAnchor r0 = r12.mLeft
            android.support.constraint.solver.widgets.ConstraintAnchor r0 = r0.mTarget
            android.support.constraint.solver.SolverVariable r0 = r0.mSolverVariable
            goto L_0x045f
        L_0x045d:
            r0 = r16
        L_0x045f:
            if (r8 != 0) goto L_0x0471
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r14.mRight
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r3.mTarget
            if (r3 == 0) goto L_0x046f
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r14.mRight
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r3.mTarget
            android.support.constraint.solver.widgets.ConstraintWidget r3 = r3.mOwner
            r8 = r3
            goto L_0x0471
        L_0x046f:
            r8 = r16
        L_0x0471:
            r3 = r8
            if (r3 == 0) goto L_0x04ab
            android.support.constraint.solver.widgets.ConstraintAnchor r4 = r3.mLeft
            android.support.constraint.solver.SolverVariable r4 = r4.mSolverVariable
            if (r2 == 0) goto L_0x0489
            android.support.constraint.solver.widgets.ConstraintAnchor r4 = r14.mRight
            android.support.constraint.solver.widgets.ConstraintAnchor r4 = r4.mTarget
            if (r4 == 0) goto L_0x0487
            android.support.constraint.solver.widgets.ConstraintAnchor r4 = r14.mRight
            android.support.constraint.solver.widgets.ConstraintAnchor r4 = r4.mTarget
            android.support.constraint.solver.SolverVariable r4 = r4.mSolverVariable
            goto L_0x0489
        L_0x0487:
            r4 = r16
        L_0x0489:
            if (r0 == 0) goto L_0x04ab
            if (r4 == 0) goto L_0x04ab
            android.support.constraint.solver.SolverVariable r8 = r6.mSolverVariable
            r11 = 1056964608(0x3f000000, float:0.5)
            android.support.constraint.solver.SolverVariable r13 = r7.mSolverVariable
            r6 = 4
            r7 = r15
            r17 = r9
            r9 = r0
            r0 = 3
            r0 = r12
            r12 = r4
            r4 = r31
            r18 = r14
            r19 = 0
            r14 = r17
            r32 = r3
            r3 = r15
            r15 = r6
            r7.addCentering(r8, r9, r10, r11, r12, r13, r14, r15)
            goto L_0x04b5
        L_0x04ab:
            r32 = r3
            r0 = r12
            r18 = r14
            r3 = r15
            r4 = r31
            r19 = 0
        L_0x04b5:
            r8 = r32
        L_0x04b7:
            if (r2 == 0) goto L_0x04bb
            r8 = r16
        L_0x04bb:
            r12 = r0
            r7 = r2
            r15 = r3
            r13 = r4
            r0 = r8
            r2 = r18
            r4 = r29
            r3 = r30
            r6 = r34
            goto L_0x034e
        L_0x04ca:
            r0 = r12
            r4 = r13
            r3 = r15
            r19 = 0
            if (r5 == 0) goto L_0x0513
            android.support.constraint.solver.widgets.ConstraintAnchor r1 = r1.mLeft
            android.support.constraint.solver.widgets.ConstraintAnchor r5 = r2.mRight
            int r10 = r1.getMargin()
            int r14 = r5.getMargin()
            android.support.constraint.solver.widgets.ConstraintAnchor r6 = r0.mLeft
            android.support.constraint.solver.widgets.ConstraintAnchor r6 = r6.mTarget
            if (r6 == 0) goto L_0x04eb
            android.support.constraint.solver.widgets.ConstraintAnchor r6 = r0.mLeft
            android.support.constraint.solver.widgets.ConstraintAnchor r6 = r6.mTarget
            android.support.constraint.solver.SolverVariable r6 = r6.mSolverVariable
            r9 = r6
            goto L_0x04ed
        L_0x04eb:
            r9 = r16
        L_0x04ed:
            android.support.constraint.solver.widgets.ConstraintAnchor r6 = r2.mRight
            android.support.constraint.solver.widgets.ConstraintAnchor r6 = r6.mTarget
            if (r6 == 0) goto L_0x04fb
            android.support.constraint.solver.widgets.ConstraintAnchor r2 = r2.mRight
            android.support.constraint.solver.widgets.ConstraintAnchor r2 = r2.mTarget
            android.support.constraint.solver.SolverVariable r2 = r2.mSolverVariable
            r12 = r2
            goto L_0x04fd
        L_0x04fb:
            r12 = r16
        L_0x04fd:
            if (r9 == 0) goto L_0x0513
            if (r12 == 0) goto L_0x0513
            android.support.constraint.solver.SolverVariable r2 = r5.mSolverVariable
            int r6 = -r14
            r7 = 1
            r3.addLowerThan(r2, r12, r6, r7)
            android.support.constraint.solver.SolverVariable r8 = r1.mSolverVariable
            float r11 = r0.mHorizontalBiasPercent
            android.support.constraint.solver.SolverVariable r13 = r5.mSolverVariable
            r15 = 4
            r7 = r3
            r7.addCentering(r8, r9, r10, r11, r12, r13, r14, r15)
        L_0x0513:
            int r13 = r4 + 1
            r15 = r3
            r14 = r19
            r6 = r34
            goto L_0x0006
        L_0x051c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.constraint.solver.widgets.ConstraintWidgetContainer.applyHorizontalChain(android.support.constraint.solver.LinearSystem):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:203:0x04dc  */
    /* JADX WARNING: Removed duplicated region for block: B:235:0x04de A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void applyVerticalChain(android.support.constraint.solver.LinearSystem r35) {
        /*
            r34 = this;
            r6 = r34
            r15 = r35
            r14 = 0
            r13 = r14
        L_0x0006:
            int r0 = r6.mVerticalChainsSize
            if (r13 >= r0) goto L_0x053f
            android.support.constraint.solver.widgets.ConstraintWidget[] r0 = r6.mVerticalChainsArray
            r12 = r0[r13]
            android.support.constraint.solver.widgets.ConstraintWidget[] r2 = r6.mChainEnds
            android.support.constraint.solver.widgets.ConstraintWidget[] r0 = r6.mVerticalChainsArray
            r3 = r0[r13]
            r4 = 1
            boolean[] r5 = r6.flags
            r0 = r6
            r1 = r15
            int r0 = r0.countMatchConstraintsChainedWidgets(r1, r2, r3, r4, r5)
            android.support.constraint.solver.widgets.ConstraintWidget[] r1 = r6.mChainEnds
            r2 = 2
            r1 = r1[r2]
            if (r1 != 0) goto L_0x002a
        L_0x0024:
            r4 = r13
            r19 = r14
            r3 = r15
            goto L_0x0536
        L_0x002a:
            boolean[] r3 = r6.flags
            r4 = 1
            boolean r3 = r3[r4]
            if (r3 == 0) goto L_0x0055
            int r0 = r12.getDrawY()
        L_0x0035:
            if (r1 == 0) goto L_0x0024
            android.support.constraint.solver.widgets.ConstraintAnchor r2 = r1.mTop
            android.support.constraint.solver.SolverVariable r2 = r2.mSolverVariable
            r15.addEquality(r2, r0)
            android.support.constraint.solver.widgets.ConstraintWidget r2 = r1.mVerticalNextWidget
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r1.mTop
            int r3 = r3.getMargin()
            int r4 = r1.getHeight()
            int r3 = r3 + r4
            android.support.constraint.solver.widgets.ConstraintAnchor r1 = r1.mBottom
            int r1 = r1.getMargin()
            int r3 = r3 + r1
            int r0 = r0 + r3
            r1 = r2
            goto L_0x0035
        L_0x0055:
            int r3 = r12.mVerticalChainStyle
            if (r3 != 0) goto L_0x005b
            r3 = r4
            goto L_0x005c
        L_0x005b:
            r3 = r14
        L_0x005c:
            int r5 = r12.mVerticalChainStyle
            if (r5 != r2) goto L_0x0062
            r5 = r4
            goto L_0x0063
        L_0x0062:
            r5 = r14
        L_0x0063:
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r7 = r6.mVerticalDimensionBehaviour
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r8 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            if (r7 != r8) goto L_0x006b
            r7 = r4
            goto L_0x006c
        L_0x006b:
            r7 = r14
        L_0x006c:
            int r8 = r6.mOptimizationLevel
            if (r8 == r2) goto L_0x0076
            int r8 = r6.mOptimizationLevel
            r9 = 8
            if (r8 != r9) goto L_0x008c
        L_0x0076:
            boolean[] r8 = r6.flags
            boolean r8 = r8[r14]
            if (r8 == 0) goto L_0x008c
            boolean r8 = r12.mVerticalChainFixedPosition
            if (r8 == 0) goto L_0x008c
            if (r5 != 0) goto L_0x008c
            if (r7 != 0) goto L_0x008c
            int r7 = r12.mVerticalChainStyle
            if (r7 != 0) goto L_0x008c
            android.support.constraint.solver.widgets.Optimizer.applyDirectResolutionVerticalChain(r6, r15, r0, r12)
            goto L_0x0024
        L_0x008c:
            r11 = 3
            r16 = 0
            if (r0 == 0) goto L_0x0349
            if (r5 == 0) goto L_0x0095
            goto L_0x0349
        L_0x0095:
            r3 = 0
            r5 = r3
            r3 = r16
        L_0x0099:
            if (r1 == 0) goto L_0x015d
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r7 = r1.mVerticalDimensionBehaviour
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r8 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r7 == r8) goto L_0x0117
            android.support.constraint.solver.widgets.ConstraintAnchor r7 = r1.mTop
            int r7 = r7.getMargin()
            if (r3 == 0) goto L_0x00b0
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r3.mBottom
            int r3 = r3.getMargin()
            int r7 = r7 + r3
        L_0x00b0:
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r1.mTop
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r3.mTarget
            android.support.constraint.solver.widgets.ConstraintWidget r3 = r3.mOwner
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r3 = r3.mVerticalDimensionBehaviour
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r8 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r3 != r8) goto L_0x00be
            r3 = r2
            goto L_0x00bf
        L_0x00be:
            r3 = r11
        L_0x00bf:
            android.support.constraint.solver.widgets.ConstraintAnchor r8 = r1.mTop
            android.support.constraint.solver.SolverVariable r8 = r8.mSolverVariable
            android.support.constraint.solver.widgets.ConstraintAnchor r9 = r1.mTop
            android.support.constraint.solver.widgets.ConstraintAnchor r9 = r9.mTarget
            android.support.constraint.solver.SolverVariable r9 = r9.mSolverVariable
            r15.addGreaterThan(r8, r9, r7, r3)
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r1.mBottom
            int r3 = r3.getMargin()
            android.support.constraint.solver.widgets.ConstraintAnchor r7 = r1.mBottom
            android.support.constraint.solver.widgets.ConstraintAnchor r7 = r7.mTarget
            android.support.constraint.solver.widgets.ConstraintWidget r7 = r7.mOwner
            android.support.constraint.solver.widgets.ConstraintAnchor r7 = r7.mTop
            android.support.constraint.solver.widgets.ConstraintAnchor r7 = r7.mTarget
            if (r7 == 0) goto L_0x00f9
            android.support.constraint.solver.widgets.ConstraintAnchor r7 = r1.mBottom
            android.support.constraint.solver.widgets.ConstraintAnchor r7 = r7.mTarget
            android.support.constraint.solver.widgets.ConstraintWidget r7 = r7.mOwner
            android.support.constraint.solver.widgets.ConstraintAnchor r7 = r7.mTop
            android.support.constraint.solver.widgets.ConstraintAnchor r7 = r7.mTarget
            android.support.constraint.solver.widgets.ConstraintWidget r7 = r7.mOwner
            if (r7 != r1) goto L_0x00f9
            android.support.constraint.solver.widgets.ConstraintAnchor r7 = r1.mBottom
            android.support.constraint.solver.widgets.ConstraintAnchor r7 = r7.mTarget
            android.support.constraint.solver.widgets.ConstraintWidget r7 = r7.mOwner
            android.support.constraint.solver.widgets.ConstraintAnchor r7 = r7.mTop
            int r7 = r7.getMargin()
            int r3 = r3 + r7
        L_0x00f9:
            android.support.constraint.solver.widgets.ConstraintAnchor r7 = r1.mBottom
            android.support.constraint.solver.widgets.ConstraintAnchor r7 = r7.mTarget
            android.support.constraint.solver.widgets.ConstraintWidget r7 = r7.mOwner
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r7 = r7.mVerticalDimensionBehaviour
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r8 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r7 != r8) goto L_0x0107
            r7 = r2
            goto L_0x0108
        L_0x0107:
            r7 = r11
        L_0x0108:
            android.support.constraint.solver.widgets.ConstraintAnchor r8 = r1.mBottom
            android.support.constraint.solver.SolverVariable r8 = r8.mSolverVariable
            android.support.constraint.solver.widgets.ConstraintAnchor r9 = r1.mBottom
            android.support.constraint.solver.widgets.ConstraintAnchor r9 = r9.mTarget
            android.support.constraint.solver.SolverVariable r9 = r9.mSolverVariable
            int r3 = -r3
            r15.addLowerThan(r8, r9, r3, r7)
            goto L_0x0154
        L_0x0117:
            float r3 = r1.mVerticalWeight
            float r5 = r5 + r3
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r1.mBottom
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r3.mTarget
            if (r3 == 0) goto L_0x013a
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r1.mBottom
            int r3 = r3.getMargin()
            android.support.constraint.solver.widgets.ConstraintWidget[] r7 = r6.mChainEnds
            r7 = r7[r11]
            if (r1 == r7) goto L_0x013b
            android.support.constraint.solver.widgets.ConstraintAnchor r7 = r1.mBottom
            android.support.constraint.solver.widgets.ConstraintAnchor r7 = r7.mTarget
            android.support.constraint.solver.widgets.ConstraintWidget r7 = r7.mOwner
            android.support.constraint.solver.widgets.ConstraintAnchor r7 = r7.mTop
            int r7 = r7.getMargin()
            int r3 = r3 + r7
            goto L_0x013b
        L_0x013a:
            r3 = r14
        L_0x013b:
            android.support.constraint.solver.widgets.ConstraintAnchor r7 = r1.mBottom
            android.support.constraint.solver.SolverVariable r7 = r7.mSolverVariable
            android.support.constraint.solver.widgets.ConstraintAnchor r8 = r1.mTop
            android.support.constraint.solver.SolverVariable r8 = r8.mSolverVariable
            r15.addGreaterThan(r7, r8, r14, r4)
            android.support.constraint.solver.widgets.ConstraintAnchor r7 = r1.mBottom
            android.support.constraint.solver.SolverVariable r7 = r7.mSolverVariable
            android.support.constraint.solver.widgets.ConstraintAnchor r8 = r1.mBottom
            android.support.constraint.solver.widgets.ConstraintAnchor r8 = r8.mTarget
            android.support.constraint.solver.SolverVariable r8 = r8.mSolverVariable
            int r3 = -r3
            r15.addLowerThan(r7, r8, r3, r4)
        L_0x0154:
            android.support.constraint.solver.widgets.ConstraintWidget r3 = r1.mVerticalNextWidget
            r33 = r3
            r3 = r1
            r1 = r33
            goto L_0x0099
        L_0x015d:
            if (r0 != r4) goto L_0x01e4
            android.support.constraint.solver.widgets.ConstraintWidget[] r0 = r6.mMatchConstraintsChainedWidgets
            r0 = r0[r14]
            android.support.constraint.solver.widgets.ConstraintAnchor r1 = r0.mTop
            int r1 = r1.getMargin()
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r0.mTop
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r3.mTarget
            if (r3 == 0) goto L_0x0178
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r0.mTop
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r3.mTarget
            int r3 = r3.getMargin()
            int r1 = r1 + r3
        L_0x0178:
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r0.mBottom
            int r3 = r3.getMargin()
            android.support.constraint.solver.widgets.ConstraintAnchor r5 = r0.mBottom
            android.support.constraint.solver.widgets.ConstraintAnchor r5 = r5.mTarget
            if (r5 == 0) goto L_0x018d
            android.support.constraint.solver.widgets.ConstraintAnchor r5 = r0.mBottom
            android.support.constraint.solver.widgets.ConstraintAnchor r5 = r5.mTarget
            int r5 = r5.getMargin()
            int r3 = r3 + r5
        L_0x018d:
            android.support.constraint.solver.widgets.ConstraintAnchor r5 = r12.mBottom
            android.support.constraint.solver.widgets.ConstraintAnchor r5 = r5.mTarget
            android.support.constraint.solver.SolverVariable r5 = r5.mSolverVariable
            android.support.constraint.solver.widgets.ConstraintWidget[] r7 = r6.mChainEnds
            r7 = r7[r11]
            if (r0 != r7) goto L_0x01a3
            android.support.constraint.solver.widgets.ConstraintWidget[] r5 = r6.mChainEnds
            r5 = r5[r4]
            android.support.constraint.solver.widgets.ConstraintAnchor r5 = r5.mBottom
            android.support.constraint.solver.widgets.ConstraintAnchor r5 = r5.mTarget
            android.support.constraint.solver.SolverVariable r5 = r5.mSolverVariable
        L_0x01a3:
            int r7 = r0.mMatchConstraintDefaultHeight
            if (r7 != r4) goto L_0x01cd
            android.support.constraint.solver.widgets.ConstraintAnchor r0 = r12.mTop
            android.support.constraint.solver.SolverVariable r0 = r0.mSolverVariable
            android.support.constraint.solver.widgets.ConstraintAnchor r7 = r12.mTop
            android.support.constraint.solver.widgets.ConstraintAnchor r7 = r7.mTarget
            android.support.constraint.solver.SolverVariable r7 = r7.mSolverVariable
            r15.addGreaterThan(r0, r7, r1, r4)
            android.support.constraint.solver.widgets.ConstraintAnchor r0 = r12.mBottom
            android.support.constraint.solver.SolverVariable r0 = r0.mSolverVariable
            int r1 = -r3
            r15.addLowerThan(r0, r5, r1, r4)
            android.support.constraint.solver.widgets.ConstraintAnchor r0 = r12.mBottom
            android.support.constraint.solver.SolverVariable r0 = r0.mSolverVariable
            android.support.constraint.solver.widgets.ConstraintAnchor r1 = r12.mTop
            android.support.constraint.solver.SolverVariable r1 = r1.mSolverVariable
            int r3 = r12.getHeight()
            r15.addEquality(r0, r1, r3, r2)
            goto L_0x0024
        L_0x01cd:
            android.support.constraint.solver.widgets.ConstraintAnchor r2 = r0.mTop
            android.support.constraint.solver.SolverVariable r2 = r2.mSolverVariable
            android.support.constraint.solver.widgets.ConstraintAnchor r7 = r0.mTop
            android.support.constraint.solver.widgets.ConstraintAnchor r7 = r7.mTarget
            android.support.constraint.solver.SolverVariable r7 = r7.mSolverVariable
            r15.addEquality(r2, r7, r1, r4)
            android.support.constraint.solver.widgets.ConstraintAnchor r0 = r0.mBottom
            android.support.constraint.solver.SolverVariable r0 = r0.mSolverVariable
            int r1 = -r3
            r15.addEquality(r0, r5, r1, r4)
            goto L_0x0024
        L_0x01e4:
            r1 = r14
        L_0x01e5:
            int r3 = r0 + -1
            if (r1 >= r3) goto L_0x0024
            android.support.constraint.solver.widgets.ConstraintWidget[] r7 = r6.mMatchConstraintsChainedWidgets
            r7 = r7[r1]
            android.support.constraint.solver.widgets.ConstraintWidget[] r8 = r6.mMatchConstraintsChainedWidgets
            int r1 = r1 + 1
            r8 = r8[r1]
            android.support.constraint.solver.widgets.ConstraintAnchor r9 = r7.mTop
            android.support.constraint.solver.SolverVariable r9 = r9.mSolverVariable
            android.support.constraint.solver.widgets.ConstraintAnchor r10 = r7.mBottom
            android.support.constraint.solver.SolverVariable r10 = r10.mSolverVariable
            android.support.constraint.solver.widgets.ConstraintAnchor r14 = r8.mTop
            android.support.constraint.solver.SolverVariable r14 = r14.mSolverVariable
            android.support.constraint.solver.widgets.ConstraintAnchor r2 = r8.mBottom
            android.support.constraint.solver.SolverVariable r2 = r2.mSolverVariable
            android.support.constraint.solver.widgets.ConstraintWidget[] r4 = r6.mChainEnds
            r4 = r4[r11]
            if (r8 != r4) goto L_0x0212
            android.support.constraint.solver.widgets.ConstraintWidget[] r2 = r6.mChainEnds
            r4 = 1
            r2 = r2[r4]
            android.support.constraint.solver.widgets.ConstraintAnchor r2 = r2.mBottom
            android.support.constraint.solver.SolverVariable r2 = r2.mSolverVariable
        L_0x0212:
            android.support.constraint.solver.widgets.ConstraintAnchor r4 = r7.mTop
            int r4 = r4.getMargin()
            android.support.constraint.solver.widgets.ConstraintAnchor r11 = r7.mTop
            android.support.constraint.solver.widgets.ConstraintAnchor r11 = r11.mTarget
            if (r11 == 0) goto L_0x0245
            android.support.constraint.solver.widgets.ConstraintAnchor r11 = r7.mTop
            android.support.constraint.solver.widgets.ConstraintAnchor r11 = r11.mTarget
            android.support.constraint.solver.widgets.ConstraintWidget r11 = r11.mOwner
            android.support.constraint.solver.widgets.ConstraintAnchor r11 = r11.mBottom
            android.support.constraint.solver.widgets.ConstraintAnchor r11 = r11.mTarget
            if (r11 == 0) goto L_0x0245
            android.support.constraint.solver.widgets.ConstraintAnchor r11 = r7.mTop
            android.support.constraint.solver.widgets.ConstraintAnchor r11 = r11.mTarget
            android.support.constraint.solver.widgets.ConstraintWidget r11 = r11.mOwner
            android.support.constraint.solver.widgets.ConstraintAnchor r11 = r11.mBottom
            android.support.constraint.solver.widgets.ConstraintAnchor r11 = r11.mTarget
            android.support.constraint.solver.widgets.ConstraintWidget r11 = r11.mOwner
            if (r11 != r7) goto L_0x0245
            android.support.constraint.solver.widgets.ConstraintAnchor r11 = r7.mTop
            android.support.constraint.solver.widgets.ConstraintAnchor r11 = r11.mTarget
            android.support.constraint.solver.widgets.ConstraintWidget r11 = r11.mOwner
            android.support.constraint.solver.widgets.ConstraintAnchor r11 = r11.mBottom
            int r11 = r11.getMargin()
            int r4 = r4 + r11
        L_0x0245:
            android.support.constraint.solver.widgets.ConstraintAnchor r11 = r7.mTop
            android.support.constraint.solver.widgets.ConstraintAnchor r11 = r11.mTarget
            android.support.constraint.solver.SolverVariable r11 = r11.mSolverVariable
            r28 = r0
            r0 = 2
            r15.addGreaterThan(r9, r11, r4, r0)
            android.support.constraint.solver.widgets.ConstraintAnchor r0 = r7.mBottom
            int r0 = r0.getMargin()
            android.support.constraint.solver.widgets.ConstraintAnchor r4 = r7.mBottom
            android.support.constraint.solver.widgets.ConstraintAnchor r4 = r4.mTarget
            if (r4 == 0) goto L_0x0274
            android.support.constraint.solver.widgets.ConstraintWidget r4 = r7.mVerticalNextWidget
            if (r4 == 0) goto L_0x0274
            android.support.constraint.solver.widgets.ConstraintWidget r4 = r7.mVerticalNextWidget
            android.support.constraint.solver.widgets.ConstraintAnchor r4 = r4.mTop
            android.support.constraint.solver.widgets.ConstraintAnchor r4 = r4.mTarget
            if (r4 == 0) goto L_0x0272
            android.support.constraint.solver.widgets.ConstraintWidget r4 = r7.mVerticalNextWidget
            android.support.constraint.solver.widgets.ConstraintAnchor r4 = r4.mTop
            int r4 = r4.getMargin()
            goto L_0x0273
        L_0x0272:
            r4 = 0
        L_0x0273:
            int r0 = r0 + r4
        L_0x0274:
            android.support.constraint.solver.widgets.ConstraintAnchor r4 = r7.mBottom
            android.support.constraint.solver.widgets.ConstraintAnchor r4 = r4.mTarget
            android.support.constraint.solver.SolverVariable r4 = r4.mSolverVariable
            int r0 = -r0
            r11 = 2
            r15.addLowerThan(r10, r4, r0, r11)
            if (r1 != r3) goto L_0x0301
            android.support.constraint.solver.widgets.ConstraintAnchor r0 = r8.mTop
            int r0 = r0.getMargin()
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r8.mTop
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r3.mTarget
            if (r3 == 0) goto L_0x02b4
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r8.mTop
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r3.mTarget
            android.support.constraint.solver.widgets.ConstraintWidget r3 = r3.mOwner
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r3.mBottom
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r3.mTarget
            if (r3 == 0) goto L_0x02b4
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r8.mTop
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r3.mTarget
            android.support.constraint.solver.widgets.ConstraintWidget r3 = r3.mOwner
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r3.mBottom
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r3.mTarget
            android.support.constraint.solver.widgets.ConstraintWidget r3 = r3.mOwner
            if (r3 != r8) goto L_0x02b4
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r8.mTop
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r3.mTarget
            android.support.constraint.solver.widgets.ConstraintWidget r3 = r3.mOwner
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r3.mBottom
            int r3 = r3.getMargin()
            int r0 = r0 + r3
        L_0x02b4:
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r8.mTop
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r3.mTarget
            android.support.constraint.solver.SolverVariable r3 = r3.mSolverVariable
            r4 = 2
            r15.addGreaterThan(r14, r3, r0, r4)
            android.support.constraint.solver.widgets.ConstraintAnchor r0 = r8.mBottom
            android.support.constraint.solver.widgets.ConstraintWidget[] r3 = r6.mChainEnds
            r4 = 3
            r3 = r3[r4]
            if (r8 != r3) goto L_0x02ce
            android.support.constraint.solver.widgets.ConstraintWidget[] r0 = r6.mChainEnds
            r3 = 1
            r0 = r0[r3]
            android.support.constraint.solver.widgets.ConstraintAnchor r0 = r0.mBottom
        L_0x02ce:
            int r3 = r0.getMargin()
            android.support.constraint.solver.widgets.ConstraintAnchor r4 = r0.mTarget
            if (r4 == 0) goto L_0x02f7
            android.support.constraint.solver.widgets.ConstraintAnchor r4 = r0.mTarget
            android.support.constraint.solver.widgets.ConstraintWidget r4 = r4.mOwner
            android.support.constraint.solver.widgets.ConstraintAnchor r4 = r4.mTop
            android.support.constraint.solver.widgets.ConstraintAnchor r4 = r4.mTarget
            if (r4 == 0) goto L_0x02f7
            android.support.constraint.solver.widgets.ConstraintAnchor r4 = r0.mTarget
            android.support.constraint.solver.widgets.ConstraintWidget r4 = r4.mOwner
            android.support.constraint.solver.widgets.ConstraintAnchor r4 = r4.mTop
            android.support.constraint.solver.widgets.ConstraintAnchor r4 = r4.mTarget
            android.support.constraint.solver.widgets.ConstraintWidget r4 = r4.mOwner
            if (r4 != r8) goto L_0x02f7
            android.support.constraint.solver.widgets.ConstraintAnchor r4 = r0.mTarget
            android.support.constraint.solver.widgets.ConstraintWidget r4 = r4.mOwner
            android.support.constraint.solver.widgets.ConstraintAnchor r4 = r4.mTop
            int r4 = r4.getMargin()
            int r3 = r3 + r4
        L_0x02f7:
            android.support.constraint.solver.widgets.ConstraintAnchor r0 = r0.mTarget
            android.support.constraint.solver.SolverVariable r0 = r0.mSolverVariable
            int r3 = -r3
            r4 = 2
            r15.addLowerThan(r2, r0, r3, r4)
            goto L_0x0302
        L_0x0301:
            r4 = 2
        L_0x0302:
            int r0 = r12.mMatchConstraintMaxHeight
            if (r0 <= 0) goto L_0x030b
            int r0 = r12.mMatchConstraintMaxHeight
            r15.addLowerThan(r10, r9, r0, r4)
        L_0x030b:
            android.support.constraint.solver.ArrayRow r0 = r35.createRow()
            float r3 = r7.mVerticalWeight
            float r11 = r8.mVerticalWeight
            android.support.constraint.solver.widgets.ConstraintAnchor r4 = r7.mTop
            int r21 = r4.getMargin()
            android.support.constraint.solver.widgets.ConstraintAnchor r4 = r7.mBottom
            int r23 = r4.getMargin()
            android.support.constraint.solver.widgets.ConstraintAnchor r4 = r8.mTop
            int r25 = r4.getMargin()
            android.support.constraint.solver.widgets.ConstraintAnchor r4 = r8.mBottom
            int r27 = r4.getMargin()
            r16 = r0
            r17 = r3
            r18 = r5
            r19 = r11
            r20 = r9
            r22 = r10
            r24 = r14
            r26 = r2
            r16.createRowEqualDimension(r17, r18, r19, r20, r21, r22, r23, r24, r25, r26, r27)
            r15.addConstraint(r0)
            r0 = r28
            r2 = 2
            r4 = 1
            r11 = 3
            r14 = 0
            goto L_0x01e5
        L_0x0349:
            r0 = r1
            r2 = r16
            r4 = r2
            r7 = 0
        L_0x034e:
            if (r0 == 0) goto L_0x04ed
            android.support.constraint.solver.widgets.ConstraintWidget r8 = r0.mVerticalNextWidget
            if (r8 != 0) goto L_0x035c
            android.support.constraint.solver.widgets.ConstraintWidget[] r2 = r6.mChainEnds
            r7 = 1
            r2 = r2[r7]
            r14 = r2
            r2 = 1
            goto L_0x035e
        L_0x035c:
            r14 = r2
            r2 = r7
        L_0x035e:
            if (r5 == 0) goto L_0x03d9
            android.support.constraint.solver.widgets.ConstraintAnchor r7 = r0.mTop
            int r9 = r7.getMargin()
            if (r4 == 0) goto L_0x036f
            android.support.constraint.solver.widgets.ConstraintAnchor r4 = r4.mBottom
            int r4 = r4.getMargin()
            int r9 = r9 + r4
        L_0x036f:
            if (r1 == r0) goto L_0x0373
            r4 = 3
            goto L_0x0374
        L_0x0373:
            r4 = 1
        L_0x0374:
            android.support.constraint.solver.widgets.ConstraintAnchor r10 = r7.mTarget
            if (r10 == 0) goto L_0x037f
            android.support.constraint.solver.SolverVariable r10 = r7.mSolverVariable
            android.support.constraint.solver.widgets.ConstraintAnchor r11 = r7.mTarget
            android.support.constraint.solver.SolverVariable r11 = r11.mSolverVariable
            goto L_0x0399
        L_0x037f:
            android.support.constraint.solver.widgets.ConstraintAnchor r10 = r0.mBaseline
            android.support.constraint.solver.widgets.ConstraintAnchor r10 = r10.mTarget
            if (r10 == 0) goto L_0x0396
            android.support.constraint.solver.widgets.ConstraintAnchor r10 = r0.mBaseline
            android.support.constraint.solver.SolverVariable r10 = r10.mSolverVariable
            android.support.constraint.solver.widgets.ConstraintAnchor r11 = r0.mBaseline
            android.support.constraint.solver.widgets.ConstraintAnchor r11 = r11.mTarget
            android.support.constraint.solver.SolverVariable r11 = r11.mSolverVariable
            int r17 = r7.getMargin()
            int r9 = r9 - r17
            goto L_0x0399
        L_0x0396:
            r10 = r16
            r11 = r10
        L_0x0399:
            if (r10 == 0) goto L_0x03a0
            if (r11 == 0) goto L_0x03a0
            r15.addGreaterThan(r10, r11, r9, r4)
        L_0x03a0:
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r4 = r0.mVerticalDimensionBehaviour
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r9 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r4 != r9) goto L_0x03d7
            android.support.constraint.solver.widgets.ConstraintAnchor r4 = r0.mBottom
            int r9 = r0.mMatchConstraintDefaultHeight
            r10 = 1
            if (r9 != r10) goto L_0x03c1
            int r9 = r0.mMatchConstraintMinHeight
            int r10 = r0.getHeight()
            int r9 = java.lang.Math.max(r9, r10)
            android.support.constraint.solver.SolverVariable r4 = r4.mSolverVariable
            android.support.constraint.solver.SolverVariable r7 = r7.mSolverVariable
            r11 = 3
            r15.addEquality(r4, r7, r9, r11)
            goto L_0x0433
        L_0x03c1:
            r11 = 3
            android.support.constraint.solver.SolverVariable r9 = r7.mSolverVariable
            android.support.constraint.solver.widgets.ConstraintAnchor r10 = r7.mTarget
            android.support.constraint.solver.SolverVariable r10 = r10.mSolverVariable
            int r6 = r7.mMargin
            r15.addGreaterThan(r9, r10, r6, r11)
            android.support.constraint.solver.SolverVariable r4 = r4.mSolverVariable
            android.support.constraint.solver.SolverVariable r6 = r7.mSolverVariable
            int r7 = r0.mMatchConstraintMinHeight
            r15.addLowerThan(r4, r6, r7, r11)
            goto L_0x0433
        L_0x03d7:
            r11 = 3
            goto L_0x0433
        L_0x03d9:
            r11 = 3
            r6 = 5
            if (r3 != 0) goto L_0x0408
            if (r2 == 0) goto L_0x0408
            if (r4 == 0) goto L_0x0408
            android.support.constraint.solver.widgets.ConstraintAnchor r4 = r0.mBottom
            android.support.constraint.solver.widgets.ConstraintAnchor r4 = r4.mTarget
            if (r4 != 0) goto L_0x03f3
            android.support.constraint.solver.widgets.ConstraintAnchor r4 = r0.mBottom
            android.support.constraint.solver.SolverVariable r4 = r4.mSolverVariable
            int r6 = r0.getDrawBottom()
            r15.addEquality(r4, r6)
            goto L_0x0433
        L_0x03f3:
            android.support.constraint.solver.widgets.ConstraintAnchor r4 = r0.mBottom
            int r4 = r4.getMargin()
            android.support.constraint.solver.widgets.ConstraintAnchor r7 = r0.mBottom
            android.support.constraint.solver.SolverVariable r7 = r7.mSolverVariable
            android.support.constraint.solver.widgets.ConstraintAnchor r9 = r14.mBottom
            android.support.constraint.solver.widgets.ConstraintAnchor r9 = r9.mTarget
            android.support.constraint.solver.SolverVariable r9 = r9.mSolverVariable
            int r4 = -r4
            r15.addEquality(r7, r9, r4, r6)
            goto L_0x0433
        L_0x0408:
            if (r3 != 0) goto L_0x0440
            if (r2 != 0) goto L_0x0440
            if (r4 != 0) goto L_0x0440
            android.support.constraint.solver.widgets.ConstraintAnchor r4 = r0.mTop
            android.support.constraint.solver.widgets.ConstraintAnchor r4 = r4.mTarget
            if (r4 != 0) goto L_0x0420
            android.support.constraint.solver.widgets.ConstraintAnchor r4 = r0.mTop
            android.support.constraint.solver.SolverVariable r4 = r4.mSolverVariable
            int r6 = r0.getDrawY()
            r15.addEquality(r4, r6)
            goto L_0x0433
        L_0x0420:
            android.support.constraint.solver.widgets.ConstraintAnchor r4 = r0.mTop
            int r4 = r4.getMargin()
            android.support.constraint.solver.widgets.ConstraintAnchor r7 = r0.mTop
            android.support.constraint.solver.SolverVariable r7 = r7.mSolverVariable
            android.support.constraint.solver.widgets.ConstraintAnchor r9 = r12.mTop
            android.support.constraint.solver.widgets.ConstraintAnchor r9 = r9.mTarget
            android.support.constraint.solver.SolverVariable r9 = r9.mSolverVariable
            r15.addEquality(r7, r9, r4, r6)
        L_0x0433:
            r29 = r0
            r30 = r3
            r0 = r12
            r4 = r13
            r18 = r14
            r3 = r15
            r19 = 0
            goto L_0x04da
        L_0x0440:
            android.support.constraint.solver.widgets.ConstraintAnchor r6 = r0.mTop
            android.support.constraint.solver.widgets.ConstraintAnchor r7 = r0.mBottom
            int r10 = r6.getMargin()
            int r9 = r7.getMargin()
            android.support.constraint.solver.SolverVariable r11 = r6.mSolverVariable
            r29 = r0
            android.support.constraint.solver.widgets.ConstraintAnchor r0 = r6.mTarget
            android.support.constraint.solver.SolverVariable r0 = r0.mSolverVariable
            r30 = r3
            r3 = 1
            r15.addGreaterThan(r11, r0, r10, r3)
            android.support.constraint.solver.SolverVariable r0 = r7.mSolverVariable
            android.support.constraint.solver.widgets.ConstraintAnchor r11 = r7.mTarget
            android.support.constraint.solver.SolverVariable r11 = r11.mSolverVariable
            r31 = r13
            int r13 = -r9
            r15.addLowerThan(r0, r11, r13, r3)
            android.support.constraint.solver.widgets.ConstraintAnchor r0 = r6.mTarget
            if (r0 == 0) goto L_0x046f
            android.support.constraint.solver.widgets.ConstraintAnchor r0 = r6.mTarget
            android.support.constraint.solver.SolverVariable r0 = r0.mSolverVariable
            goto L_0x0471
        L_0x046f:
            r0 = r16
        L_0x0471:
            if (r4 != 0) goto L_0x0482
            android.support.constraint.solver.widgets.ConstraintAnchor r0 = r12.mTop
            android.support.constraint.solver.widgets.ConstraintAnchor r0 = r0.mTarget
            if (r0 == 0) goto L_0x0480
            android.support.constraint.solver.widgets.ConstraintAnchor r0 = r12.mTop
            android.support.constraint.solver.widgets.ConstraintAnchor r0 = r0.mTarget
            android.support.constraint.solver.SolverVariable r0 = r0.mSolverVariable
            goto L_0x0482
        L_0x0480:
            r0 = r16
        L_0x0482:
            if (r8 != 0) goto L_0x0494
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r14.mBottom
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r3.mTarget
            if (r3 == 0) goto L_0x0492
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r14.mBottom
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r3.mTarget
            android.support.constraint.solver.widgets.ConstraintWidget r3 = r3.mOwner
            r8 = r3
            goto L_0x0494
        L_0x0492:
            r8 = r16
        L_0x0494:
            r3 = r8
            if (r3 == 0) goto L_0x04ce
            android.support.constraint.solver.widgets.ConstraintAnchor r4 = r3.mTop
            android.support.constraint.solver.SolverVariable r4 = r4.mSolverVariable
            if (r2 == 0) goto L_0x04ac
            android.support.constraint.solver.widgets.ConstraintAnchor r4 = r14.mBottom
            android.support.constraint.solver.widgets.ConstraintAnchor r4 = r4.mTarget
            if (r4 == 0) goto L_0x04aa
            android.support.constraint.solver.widgets.ConstraintAnchor r4 = r14.mBottom
            android.support.constraint.solver.widgets.ConstraintAnchor r4 = r4.mTarget
            android.support.constraint.solver.SolverVariable r4 = r4.mSolverVariable
            goto L_0x04ac
        L_0x04aa:
            r4 = r16
        L_0x04ac:
            if (r0 == 0) goto L_0x04ce
            if (r4 == 0) goto L_0x04ce
            android.support.constraint.solver.SolverVariable r8 = r6.mSolverVariable
            r11 = 1056964608(0x3f000000, float:0.5)
            android.support.constraint.solver.SolverVariable r13 = r7.mSolverVariable
            r6 = 4
            r7 = r15
            r17 = r9
            r9 = r0
            r0 = 3
            r0 = r12
            r12 = r4
            r4 = r31
            r18 = r14
            r19 = 0
            r14 = r17
            r32 = r3
            r3 = r15
            r15 = r6
            r7.addCentering(r8, r9, r10, r11, r12, r13, r14, r15)
            goto L_0x04d8
        L_0x04ce:
            r32 = r3
            r0 = r12
            r18 = r14
            r3 = r15
            r4 = r31
            r19 = 0
        L_0x04d8:
            r8 = r32
        L_0x04da:
            if (r2 == 0) goto L_0x04de
            r8 = r16
        L_0x04de:
            r12 = r0
            r7 = r2
            r15 = r3
            r13 = r4
            r0 = r8
            r2 = r18
            r4 = r29
            r3 = r30
            r6 = r34
            goto L_0x034e
        L_0x04ed:
            r0 = r12
            r4 = r13
            r3 = r15
            r19 = 0
            if (r5 == 0) goto L_0x0536
            android.support.constraint.solver.widgets.ConstraintAnchor r1 = r1.mTop
            android.support.constraint.solver.widgets.ConstraintAnchor r5 = r2.mBottom
            int r10 = r1.getMargin()
            int r14 = r5.getMargin()
            android.support.constraint.solver.widgets.ConstraintAnchor r6 = r0.mTop
            android.support.constraint.solver.widgets.ConstraintAnchor r6 = r6.mTarget
            if (r6 == 0) goto L_0x050e
            android.support.constraint.solver.widgets.ConstraintAnchor r6 = r0.mTop
            android.support.constraint.solver.widgets.ConstraintAnchor r6 = r6.mTarget
            android.support.constraint.solver.SolverVariable r6 = r6.mSolverVariable
            r9 = r6
            goto L_0x0510
        L_0x050e:
            r9 = r16
        L_0x0510:
            android.support.constraint.solver.widgets.ConstraintAnchor r6 = r2.mBottom
            android.support.constraint.solver.widgets.ConstraintAnchor r6 = r6.mTarget
            if (r6 == 0) goto L_0x051e
            android.support.constraint.solver.widgets.ConstraintAnchor r2 = r2.mBottom
            android.support.constraint.solver.widgets.ConstraintAnchor r2 = r2.mTarget
            android.support.constraint.solver.SolverVariable r2 = r2.mSolverVariable
            r12 = r2
            goto L_0x0520
        L_0x051e:
            r12 = r16
        L_0x0520:
            if (r9 == 0) goto L_0x0536
            if (r12 == 0) goto L_0x0536
            android.support.constraint.solver.SolverVariable r2 = r5.mSolverVariable
            int r6 = -r14
            r7 = 1
            r3.addLowerThan(r2, r12, r6, r7)
            android.support.constraint.solver.SolverVariable r8 = r1.mSolverVariable
            float r11 = r0.mVerticalBiasPercent
            android.support.constraint.solver.SolverVariable r13 = r5.mSolverVariable
            r15 = 4
            r7 = r3
            r7.addCentering(r8, r9, r10, r11, r12, r13, r14, r15)
        L_0x0536:
            int r13 = r4 + 1
            r15 = r3
            r14 = r19
            r6 = r34
            goto L_0x0006
        L_0x053f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.constraint.solver.widgets.ConstraintWidgetContainer.applyVerticalChain(android.support.constraint.solver.LinearSystem):void");
    }

    public void updateChildrenFromSolver(LinearSystem linearSystem, int i, boolean[] zArr) {
        zArr[2] = false;
        updateFromSolver(linearSystem, i);
        int size = this.mChildren.size();
        for (int i2 = 0; i2 < size; i2++) {
            ConstraintWidget constraintWidget = (ConstraintWidget) this.mChildren.get(i2);
            constraintWidget.updateFromSolver(linearSystem, i);
            if (constraintWidget.mHorizontalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && constraintWidget.getWidth() < constraintWidget.getWrapWidth()) {
                zArr[2] = true;
            }
            if (constraintWidget.mVerticalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && constraintWidget.getHeight() < constraintWidget.getWrapHeight()) {
                zArr[2] = true;
            }
        }
    }

    public void setPadding(int i, int i2, int i3, int i4) {
        this.mPaddingLeft = i;
        this.mPaddingTop = i2;
        this.mPaddingRight = i3;
        this.mPaddingBottom = i4;
    }

    /* JADX WARNING: Removed duplicated region for block: B:58:0x0105  */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x010e  */
    /* JADX WARNING: Removed duplicated region for block: B:77:0x015a  */
    /* JADX WARNING: Removed duplicated region for block: B:91:0x01b9  */
    /* JADX WARNING: Removed duplicated region for block: B:94:0x01cb  */
    /* JADX WARNING: Removed duplicated region for block: B:97:0x01e4  */
    /* JADX WARNING: Removed duplicated region for block: B:99:0x01ef  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void layout() {
        /*
            r18 = this;
            r1 = r18
            int r2 = r1.mX
            int r3 = r1.mY
            int r4 = r18.getWidth()
            r5 = 0
            int r4 = java.lang.Math.max(r5, r4)
            int r6 = r18.getHeight()
            int r6 = java.lang.Math.max(r5, r6)
            r1.mWidthMeasuredTooSmall = r5
            r1.mHeightMeasuredTooSmall = r5
            android.support.constraint.solver.widgets.ConstraintWidget r7 = r1.mParent
            if (r7 == 0) goto L_0x0046
            android.support.constraint.solver.widgets.Snapshot r7 = r1.mSnapshot
            if (r7 != 0) goto L_0x002a
            android.support.constraint.solver.widgets.Snapshot r7 = new android.support.constraint.solver.widgets.Snapshot
            r7.<init>(r1)
            r1.mSnapshot = r7
        L_0x002a:
            android.support.constraint.solver.widgets.Snapshot r7 = r1.mSnapshot
            r7.updateFrom(r1)
            int r7 = r1.mPaddingLeft
            r1.setX(r7)
            int r7 = r1.mPaddingTop
            r1.setY(r7)
            r18.resetAnchors()
            android.support.constraint.solver.LinearSystem r7 = r1.mSystem
            android.support.constraint.solver.Cache r7 = r7.getCache()
            r1.resetSolverVariables(r7)
            goto L_0x004a
        L_0x0046:
            r1.mX = r5
            r1.mY = r5
        L_0x004a:
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r7 = r1.mVerticalDimensionBehaviour
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r8 = r1.mHorizontalDimensionBehaviour
            int r9 = r1.mOptimizationLevel
            r10 = 2
            r11 = 1
            if (r9 != r10) goto L_0x00bd
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r9 = r1.mVerticalDimensionBehaviour
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r12 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            if (r9 == r12) goto L_0x0060
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r9 = r1.mHorizontalDimensionBehaviour
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r12 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            if (r9 != r12) goto L_0x00bd
        L_0x0060:
            java.util.ArrayList r9 = r1.mChildren
            boolean[] r12 = r1.flags
            r1.findWrapSize(r9, r12)
            boolean[] r9 = r1.flags
            boolean r9 = r9[r5]
            if (r4 <= 0) goto L_0x0078
            if (r6 <= 0) goto L_0x0078
            int r12 = r1.mWrapWidth
            if (r12 > r4) goto L_0x0077
            int r12 = r1.mWrapHeight
            if (r12 <= r6) goto L_0x0078
        L_0x0077:
            r9 = r5
        L_0x0078:
            if (r9 == 0) goto L_0x00be
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r12 = r1.mHorizontalDimensionBehaviour
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r13 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            if (r12 != r13) goto L_0x009b
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r12 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.FIXED
            r1.mHorizontalDimensionBehaviour = r12
            if (r4 <= 0) goto L_0x0090
            int r12 = r1.mWrapWidth
            if (r4 >= r12) goto L_0x0090
            r1.mWidthMeasuredTooSmall = r11
            r1.setWidth(r4)
            goto L_0x009b
        L_0x0090:
            int r12 = r1.mMinWidth
            int r13 = r1.mWrapWidth
            int r12 = java.lang.Math.max(r12, r13)
            r1.setWidth(r12)
        L_0x009b:
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r12 = r1.mVerticalDimensionBehaviour
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r13 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            if (r12 != r13) goto L_0x00be
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r12 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.FIXED
            r1.mVerticalDimensionBehaviour = r12
            if (r6 <= 0) goto L_0x00b1
            int r12 = r1.mWrapHeight
            if (r6 >= r12) goto L_0x00b1
            r1.mHeightMeasuredTooSmall = r11
            r1.setHeight(r6)
            goto L_0x00be
        L_0x00b1:
            int r12 = r1.mMinHeight
            int r13 = r1.mWrapHeight
            int r12 = java.lang.Math.max(r12, r13)
            r1.setHeight(r12)
            goto L_0x00be
        L_0x00bd:
            r9 = r5
        L_0x00be:
            r18.resetChains()
            java.util.ArrayList r12 = r1.mChildren
            int r12 = r12.size()
            r13 = r5
        L_0x00c8:
            if (r13 >= r12) goto L_0x00de
            java.util.ArrayList r14 = r1.mChildren
            java.lang.Object r14 = r14.get(r13)
            android.support.constraint.solver.widgets.ConstraintWidget r14 = (android.support.constraint.solver.widgets.ConstraintWidget) r14
            boolean r15 = r14 instanceof android.support.constraint.solver.widgets.WidgetContainer
            if (r15 == 0) goto L_0x00db
            android.support.constraint.solver.widgets.WidgetContainer r14 = (android.support.constraint.solver.widgets.WidgetContainer) r14
            r14.layout()
        L_0x00db:
            int r13 = r13 + 1
            goto L_0x00c8
        L_0x00de:
            r13 = r5
            r14 = r9
            r9 = r11
        L_0x00e1:
            if (r9 == 0) goto L_0x022c
            int r13 = r13 + r11
            r15 = 2147483647(0x7fffffff, float:NaN)
            android.support.constraint.solver.LinearSystem r5 = r1.mSystem     // Catch:{ Exception -> 0x00fd }
            r5.reset()     // Catch:{ Exception -> 0x00fd }
            android.support.constraint.solver.LinearSystem r5 = r1.mSystem     // Catch:{ Exception -> 0x00fd }
            boolean r5 = r1.addChildrenToSolver(r5, r15)     // Catch:{ Exception -> 0x00fd }
            if (r5 == 0) goto L_0x0103
            android.support.constraint.solver.LinearSystem r9 = r1.mSystem     // Catch:{ Exception -> 0x00fa }
            r9.minimize()     // Catch:{ Exception -> 0x00fa }
            goto L_0x0103
        L_0x00fa:
            r0 = move-exception
            r9 = r5
            goto L_0x00fe
        L_0x00fd:
            r0 = move-exception
        L_0x00fe:
            r5 = r0
            r5.printStackTrace()
            r5 = r9
        L_0x0103:
            if (r5 == 0) goto L_0x010e
            android.support.constraint.solver.LinearSystem r5 = r1.mSystem
            boolean[] r9 = r1.flags
            r1.updateChildrenFromSolver(r5, r15, r9)
        L_0x010c:
            r9 = r10
            goto L_0x0150
        L_0x010e:
            android.support.constraint.solver.LinearSystem r5 = r1.mSystem
            r1.updateFromSolver(r5, r15)
            r5 = 0
        L_0x0114:
            if (r5 >= r12) goto L_0x010c
            java.util.ArrayList r9 = r1.mChildren
            java.lang.Object r9 = r9.get(r5)
            android.support.constraint.solver.widgets.ConstraintWidget r9 = (android.support.constraint.solver.widgets.ConstraintWidget) r9
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r15 = r9.mHorizontalDimensionBehaviour
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r10 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r15 != r10) goto L_0x0134
            int r10 = r9.getWidth()
            int r15 = r9.getWrapWidth()
            if (r10 >= r15) goto L_0x0134
            boolean[] r5 = r1.flags
            r10 = 2
            r5[r10] = r11
            goto L_0x010c
        L_0x0134:
            r10 = 2
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r15 = r9.mVerticalDimensionBehaviour
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r10 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r15 != r10) goto L_0x014b
            int r10 = r9.getHeight()
            int r9 = r9.getWrapHeight()
            if (r10 >= r9) goto L_0x014b
            boolean[] r5 = r1.flags
            r9 = 2
            r5[r9] = r11
            goto L_0x0150
        L_0x014b:
            r9 = 2
            int r5 = r5 + 1
            r10 = r9
            goto L_0x0114
        L_0x0150:
            r5 = 8
            if (r13 >= r5) goto L_0x01b9
            boolean[] r5 = r1.flags
            boolean r5 = r5[r9]
            if (r5 == 0) goto L_0x01b9
            r5 = 0
            r10 = 0
            r15 = 0
        L_0x015d:
            if (r5 >= r12) goto L_0x0183
            java.util.ArrayList r9 = r1.mChildren
            java.lang.Object r9 = r9.get(r5)
            android.support.constraint.solver.widgets.ConstraintWidget r9 = (android.support.constraint.solver.widgets.ConstraintWidget) r9
            int r11 = r9.mX
            int r16 = r9.getWidth()
            int r11 = r11 + r16
            int r10 = java.lang.Math.max(r10, r11)
            int r11 = r9.mY
            int r9 = r9.getHeight()
            int r11 = r11 + r9
            int r15 = java.lang.Math.max(r15, r11)
            int r5 = r5 + 1
            r9 = 2
            r11 = 1
            goto L_0x015d
        L_0x0183:
            int r5 = r1.mMinWidth
            int r5 = java.lang.Math.max(r5, r10)
            int r9 = r1.mMinHeight
            int r9 = java.lang.Math.max(r9, r15)
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r10 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            if (r8 != r10) goto L_0x01a3
            int r10 = r18.getWidth()
            if (r10 >= r5) goto L_0x01a3
            r1.setWidth(r5)
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r5 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            r1.mHorizontalDimensionBehaviour = r5
            r5 = 1
            r11 = 1
            goto L_0x01a5
        L_0x01a3:
            r11 = r14
            r5 = 0
        L_0x01a5:
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r10 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            if (r7 != r10) goto L_0x01bb
            int r10 = r18.getHeight()
            if (r10 >= r9) goto L_0x01bb
            r1.setHeight(r9)
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r5 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            r1.mVerticalDimensionBehaviour = r5
            r5 = 1
            r11 = 1
            goto L_0x01bb
        L_0x01b9:
            r11 = r14
            r5 = 0
        L_0x01bb:
            int r9 = r1.mMinWidth
            int r10 = r18.getWidth()
            int r9 = java.lang.Math.max(r9, r10)
            int r10 = r18.getWidth()
            if (r9 <= r10) goto L_0x01d4
            r1.setWidth(r9)
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r5 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.FIXED
            r1.mHorizontalDimensionBehaviour = r5
            r5 = 1
            r11 = 1
        L_0x01d4:
            int r9 = r1.mMinHeight
            int r10 = r18.getHeight()
            int r9 = java.lang.Math.max(r9, r10)
            int r10 = r18.getHeight()
            if (r9 <= r10) goto L_0x01ed
            r1.setHeight(r9)
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r5 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.FIXED
            r1.mVerticalDimensionBehaviour = r5
            r5 = 1
            r11 = 1
        L_0x01ed:
            if (r11 != 0) goto L_0x0224
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r9 = r1.mHorizontalDimensionBehaviour
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r10 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            if (r9 != r10) goto L_0x0209
            if (r4 <= 0) goto L_0x0209
            int r9 = r18.getWidth()
            if (r9 <= r4) goto L_0x0209
            r9 = 1
            r1.mWidthMeasuredTooSmall = r9
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r5 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.FIXED
            r1.mHorizontalDimensionBehaviour = r5
            r1.setWidth(r4)
            r5 = 1
            r11 = 1
        L_0x0209:
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r9 = r1.mVerticalDimensionBehaviour
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r10 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            if (r9 != r10) goto L_0x0224
            if (r6 <= 0) goto L_0x0224
            int r9 = r18.getHeight()
            if (r9 <= r6) goto L_0x0224
            r9 = 1
            r1.mHeightMeasuredTooSmall = r9
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r5 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.FIXED
            r1.mVerticalDimensionBehaviour = r5
            r1.setHeight(r6)
            r5 = r9
            r14 = r5
            goto L_0x0226
        L_0x0224:
            r9 = 1
            r14 = r11
        L_0x0226:
            r11 = r9
            r10 = 2
            r9 = r5
            r5 = 0
            goto L_0x00e1
        L_0x022c:
            android.support.constraint.solver.widgets.ConstraintWidget r4 = r1.mParent
            if (r4 == 0) goto L_0x025c
            int r2 = r1.mMinWidth
            int r3 = r18.getWidth()
            int r2 = java.lang.Math.max(r2, r3)
            int r3 = r1.mMinHeight
            int r4 = r18.getHeight()
            int r3 = java.lang.Math.max(r3, r4)
            android.support.constraint.solver.widgets.Snapshot r4 = r1.mSnapshot
            r4.applyTo(r1)
            int r4 = r1.mPaddingLeft
            int r2 = r2 + r4
            int r4 = r1.mPaddingRight
            int r2 = r2 + r4
            r1.setWidth(r2)
            int r2 = r1.mPaddingTop
            int r3 = r3 + r2
            int r2 = r1.mPaddingBottom
            int r3 = r3 + r2
            r1.setHeight(r3)
            goto L_0x0260
        L_0x025c:
            r1.mX = r2
            r1.mY = r3
        L_0x0260:
            if (r14 == 0) goto L_0x0266
            r1.mHorizontalDimensionBehaviour = r8
            r1.mVerticalDimensionBehaviour = r7
        L_0x0266:
            android.support.constraint.solver.LinearSystem r2 = r1.mSystem
            android.support.constraint.solver.Cache r2 = r2.getCache()
            r1.resetSolverVariables(r2)
            android.support.constraint.solver.widgets.ConstraintWidgetContainer r2 = r18.getRootConstraintContainer()
            if (r1 != r2) goto L_0x0278
            r18.updateDrawPosition()
        L_0x0278:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.constraint.solver.widgets.ConstraintWidgetContainer.layout():void");
    }

    static int setGroup(ConstraintAnchor constraintAnchor, int i) {
        int i2 = constraintAnchor.mGroup;
        if (constraintAnchor.mOwner.getParent() == null) {
            return i;
        }
        if (i2 <= i) {
            return i2;
        }
        constraintAnchor.mGroup = i;
        ConstraintAnchor opposite = constraintAnchor.getOpposite();
        ConstraintAnchor constraintAnchor2 = constraintAnchor.mTarget;
        if (opposite != null) {
            i = setGroup(opposite, i);
        }
        if (constraintAnchor2 != null) {
            i = setGroup(constraintAnchor2, i);
        }
        if (opposite != null) {
            i = setGroup(opposite, i);
        }
        constraintAnchor.mGroup = i;
        return i;
    }

    public int layoutFindGroupsSimple() {
        int size = this.mChildren.size();
        for (int i = 0; i < size; i++) {
            ConstraintWidget constraintWidget = (ConstraintWidget) this.mChildren.get(i);
            constraintWidget.mLeft.mGroup = 0;
            constraintWidget.mRight.mGroup = 0;
            constraintWidget.mTop.mGroup = 1;
            constraintWidget.mBottom.mGroup = 1;
            constraintWidget.mBaseline.mGroup = 1;
        }
        return 2;
    }

    /* JADX WARNING: Removed duplicated region for block: B:117:0x01bb  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void findHorizontalWrapRecursive(android.support.constraint.solver.widgets.ConstraintWidget r8, boolean[] r9) {
        /*
            r7 = this;
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r0 = r8.mHorizontalDimensionBehaviour
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r1 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            r2 = 0
            r3 = 0
            if (r0 != r1) goto L_0x0017
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r0 = r8.mVerticalDimensionBehaviour
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r1 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r0 != r1) goto L_0x0017
            float r0 = r8.mDimensionRatio
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r0 <= 0) goto L_0x0017
            r9[r3] = r3
            return
        L_0x0017:
            int r0 = r8.getOptimizerWrapWidth()
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r1 = r8.mHorizontalDimensionBehaviour
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r4 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r1 != r4) goto L_0x0030
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r1 = r8.mVerticalDimensionBehaviour
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r4 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r1 == r4) goto L_0x0030
            float r1 = r8.mDimensionRatio
            int r1 = (r1 > r2 ? 1 : (r1 == r2 ? 0 : -1))
            if (r1 <= 0) goto L_0x0030
            r9[r3] = r3
            return
        L_0x0030:
            r1 = 1
            r8.mHorizontalWrapVisited = r1
            boolean r2 = r8 instanceof android.support.constraint.solver.widgets.Guideline
            if (r2 == 0) goto L_0x0060
            r9 = r8
            android.support.constraint.solver.widgets.Guideline r9 = (android.support.constraint.solver.widgets.Guideline) r9
            int r2 = r9.getOrientation()
            if (r2 != r1) goto L_0x005c
            int r0 = r9.getRelativeBegin()
            r1 = -1
            if (r0 == r1) goto L_0x004e
            int r9 = r9.getRelativeBegin()
            r0 = r3
            r3 = r9
            goto L_0x005d
        L_0x004e:
            int r0 = r9.getRelativeEnd()
            if (r0 == r1) goto L_0x005a
            int r9 = r9.getRelativeEnd()
            r0 = r9
            goto L_0x005d
        L_0x005a:
            r0 = r3
            goto L_0x005d
        L_0x005c:
            r3 = r0
        L_0x005d:
            r5 = r0
            goto L_0x01b3
        L_0x0060:
            android.support.constraint.solver.widgets.ConstraintAnchor r2 = r8.mRight
            boolean r2 = r2.isConnected()
            if (r2 != 0) goto L_0x0077
            android.support.constraint.solver.widgets.ConstraintAnchor r2 = r8.mLeft
            boolean r2 = r2.isConnected()
            if (r2 != 0) goto L_0x0077
            int r9 = r8.getX()
            int r3 = r0 + r9
            goto L_0x005d
        L_0x0077:
            android.support.constraint.solver.widgets.ConstraintAnchor r2 = r8.mRight
            android.support.constraint.solver.widgets.ConstraintAnchor r2 = r2.mTarget
            if (r2 == 0) goto L_0x00a8
            android.support.constraint.solver.widgets.ConstraintAnchor r2 = r8.mLeft
            android.support.constraint.solver.widgets.ConstraintAnchor r2 = r2.mTarget
            if (r2 == 0) goto L_0x00a8
            android.support.constraint.solver.widgets.ConstraintAnchor r2 = r8.mRight
            android.support.constraint.solver.widgets.ConstraintAnchor r2 = r2.mTarget
            android.support.constraint.solver.widgets.ConstraintAnchor r4 = r8.mLeft
            android.support.constraint.solver.widgets.ConstraintAnchor r4 = r4.mTarget
            if (r2 == r4) goto L_0x00a5
            android.support.constraint.solver.widgets.ConstraintAnchor r2 = r8.mRight
            android.support.constraint.solver.widgets.ConstraintAnchor r2 = r2.mTarget
            android.support.constraint.solver.widgets.ConstraintWidget r2 = r2.mOwner
            android.support.constraint.solver.widgets.ConstraintAnchor r4 = r8.mLeft
            android.support.constraint.solver.widgets.ConstraintAnchor r4 = r4.mTarget
            android.support.constraint.solver.widgets.ConstraintWidget r4 = r4.mOwner
            if (r2 != r4) goto L_0x00a8
            android.support.constraint.solver.widgets.ConstraintAnchor r2 = r8.mRight
            android.support.constraint.solver.widgets.ConstraintAnchor r2 = r2.mTarget
            android.support.constraint.solver.widgets.ConstraintWidget r2 = r2.mOwner
            android.support.constraint.solver.widgets.ConstraintWidget r4 = r8.mParent
            if (r2 == r4) goto L_0x00a8
        L_0x00a5:
            r9[r3] = r3
            return
        L_0x00a8:
            android.support.constraint.solver.widgets.ConstraintAnchor r2 = r8.mRight
            android.support.constraint.solver.widgets.ConstraintAnchor r2 = r2.mTarget
            r4 = 0
            if (r2 == 0) goto L_0x00ca
            android.support.constraint.solver.widgets.ConstraintAnchor r2 = r8.mRight
            android.support.constraint.solver.widgets.ConstraintAnchor r2 = r2.mTarget
            android.support.constraint.solver.widgets.ConstraintWidget r2 = r2.mOwner
            android.support.constraint.solver.widgets.ConstraintAnchor r5 = r8.mRight
            int r5 = r5.getMargin()
            int r5 = r5 + r0
            boolean r6 = r2.isRoot()
            if (r6 != 0) goto L_0x00cc
            boolean r6 = r2.mHorizontalWrapVisited
            if (r6 != 0) goto L_0x00cc
            r7.findHorizontalWrapRecursive(r2, r9)
            goto L_0x00cc
        L_0x00ca:
            r5 = r0
            r2 = r4
        L_0x00cc:
            android.support.constraint.solver.widgets.ConstraintAnchor r6 = r8.mLeft
            android.support.constraint.solver.widgets.ConstraintAnchor r6 = r6.mTarget
            if (r6 == 0) goto L_0x00ec
            android.support.constraint.solver.widgets.ConstraintAnchor r4 = r8.mLeft
            android.support.constraint.solver.widgets.ConstraintAnchor r4 = r4.mTarget
            android.support.constraint.solver.widgets.ConstraintWidget r4 = r4.mOwner
            android.support.constraint.solver.widgets.ConstraintAnchor r6 = r8.mLeft
            int r6 = r6.getMargin()
            int r0 = r0 + r6
            boolean r6 = r4.isRoot()
            if (r6 != 0) goto L_0x00ec
            boolean r6 = r4.mHorizontalWrapVisited
            if (r6 != 0) goto L_0x00ec
            r7.findHorizontalWrapRecursive(r4, r9)
        L_0x00ec:
            android.support.constraint.solver.widgets.ConstraintAnchor r9 = r8.mRight
            android.support.constraint.solver.widgets.ConstraintAnchor r9 = r9.mTarget
            if (r9 == 0) goto L_0x014e
            boolean r9 = r2.isRoot()
            if (r9 != 0) goto L_0x014e
            android.support.constraint.solver.widgets.ConstraintAnchor r9 = r8.mRight
            android.support.constraint.solver.widgets.ConstraintAnchor r9 = r9.mTarget
            android.support.constraint.solver.widgets.ConstraintAnchor$Type r9 = r9.mType
            android.support.constraint.solver.widgets.ConstraintAnchor$Type r6 = android.support.constraint.solver.widgets.ConstraintAnchor.Type.RIGHT
            if (r9 != r6) goto L_0x010b
            int r9 = r2.mDistToRight
            int r6 = r2.getOptimizerWrapWidth()
            int r9 = r9 - r6
            int r5 = r5 + r9
            goto L_0x011a
        L_0x010b:
            android.support.constraint.solver.widgets.ConstraintAnchor r9 = r8.mRight
            android.support.constraint.solver.widgets.ConstraintAnchor r9 = r9.mTarget
            android.support.constraint.solver.widgets.ConstraintAnchor$Type r9 = r9.getType()
            android.support.constraint.solver.widgets.ConstraintAnchor$Type r6 = android.support.constraint.solver.widgets.ConstraintAnchor.Type.LEFT
            if (r9 != r6) goto L_0x011a
            int r9 = r2.mDistToRight
            int r5 = r5 + r9
        L_0x011a:
            boolean r9 = r2.mRightHasCentered
            if (r9 != 0) goto L_0x0133
            android.support.constraint.solver.widgets.ConstraintAnchor r9 = r2.mLeft
            android.support.constraint.solver.widgets.ConstraintAnchor r9 = r9.mTarget
            if (r9 == 0) goto L_0x0131
            android.support.constraint.solver.widgets.ConstraintAnchor r9 = r2.mRight
            android.support.constraint.solver.widgets.ConstraintAnchor r9 = r9.mTarget
            if (r9 == 0) goto L_0x0131
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r9 = r2.mHorizontalDimensionBehaviour
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r6 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r9 == r6) goto L_0x0131
            goto L_0x0133
        L_0x0131:
            r9 = r3
            goto L_0x0134
        L_0x0133:
            r9 = r1
        L_0x0134:
            r8.mRightHasCentered = r9
            boolean r9 = r8.mRightHasCentered
            if (r9 == 0) goto L_0x014e
            android.support.constraint.solver.widgets.ConstraintAnchor r9 = r2.mLeft
            android.support.constraint.solver.widgets.ConstraintAnchor r9 = r9.mTarget
            if (r9 != 0) goto L_0x0141
            goto L_0x0149
        L_0x0141:
            android.support.constraint.solver.widgets.ConstraintAnchor r9 = r2.mLeft
            android.support.constraint.solver.widgets.ConstraintAnchor r9 = r9.mTarget
            android.support.constraint.solver.widgets.ConstraintWidget r9 = r9.mOwner
            if (r9 == r8) goto L_0x014e
        L_0x0149:
            int r9 = r2.mDistToRight
            int r9 = r5 - r9
            int r5 = r5 + r9
        L_0x014e:
            android.support.constraint.solver.widgets.ConstraintAnchor r9 = r8.mLeft
            android.support.constraint.solver.widgets.ConstraintAnchor r9 = r9.mTarget
            if (r9 == 0) goto L_0x01b2
            boolean r9 = r4.isRoot()
            if (r9 != 0) goto L_0x01b2
            android.support.constraint.solver.widgets.ConstraintAnchor r9 = r8.mLeft
            android.support.constraint.solver.widgets.ConstraintAnchor r9 = r9.mTarget
            android.support.constraint.solver.widgets.ConstraintAnchor$Type r9 = r9.getType()
            android.support.constraint.solver.widgets.ConstraintAnchor$Type r2 = android.support.constraint.solver.widgets.ConstraintAnchor.Type.LEFT
            if (r9 != r2) goto L_0x016f
            int r9 = r4.mDistToLeft
            int r2 = r4.getOptimizerWrapWidth()
            int r9 = r9 - r2
            int r0 = r0 + r9
            goto L_0x017e
        L_0x016f:
            android.support.constraint.solver.widgets.ConstraintAnchor r9 = r8.mLeft
            android.support.constraint.solver.widgets.ConstraintAnchor r9 = r9.mTarget
            android.support.constraint.solver.widgets.ConstraintAnchor$Type r9 = r9.getType()
            android.support.constraint.solver.widgets.ConstraintAnchor$Type r2 = android.support.constraint.solver.widgets.ConstraintAnchor.Type.RIGHT
            if (r9 != r2) goto L_0x017e
            int r9 = r4.mDistToLeft
            int r0 = r0 + r9
        L_0x017e:
            boolean r9 = r4.mLeftHasCentered
            if (r9 != 0) goto L_0x0196
            android.support.constraint.solver.widgets.ConstraintAnchor r9 = r4.mLeft
            android.support.constraint.solver.widgets.ConstraintAnchor r9 = r9.mTarget
            if (r9 == 0) goto L_0x0195
            android.support.constraint.solver.widgets.ConstraintAnchor r9 = r4.mRight
            android.support.constraint.solver.widgets.ConstraintAnchor r9 = r9.mTarget
            if (r9 == 0) goto L_0x0195
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r9 = r4.mHorizontalDimensionBehaviour
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r2 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r9 == r2) goto L_0x0195
            goto L_0x0196
        L_0x0195:
            r1 = r3
        L_0x0196:
            r8.mLeftHasCentered = r1
            boolean r9 = r8.mLeftHasCentered
            if (r9 == 0) goto L_0x01b2
            android.support.constraint.solver.widgets.ConstraintAnchor r9 = r4.mRight
            android.support.constraint.solver.widgets.ConstraintAnchor r9 = r9.mTarget
            if (r9 != 0) goto L_0x01a3
            goto L_0x01ab
        L_0x01a3:
            android.support.constraint.solver.widgets.ConstraintAnchor r9 = r4.mRight
            android.support.constraint.solver.widgets.ConstraintAnchor r9 = r9.mTarget
            android.support.constraint.solver.widgets.ConstraintWidget r9 = r9.mOwner
            if (r9 == r8) goto L_0x01b2
        L_0x01ab:
            int r9 = r4.mDistToLeft
            int r9 = r0 - r9
            int r3 = r0 + r9
            goto L_0x01b3
        L_0x01b2:
            r3 = r0
        L_0x01b3:
            int r9 = r8.getVisibility()
            r0 = 8
            if (r9 != r0) goto L_0x01c1
            int r9 = r8.mWidth
            int r3 = r3 - r9
            int r9 = r8.mWidth
            int r5 = r5 - r9
        L_0x01c1:
            r8.mDistToLeft = r3
            r8.mDistToRight = r5
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.constraint.solver.widgets.ConstraintWidgetContainer.findHorizontalWrapRecursive(android.support.constraint.solver.widgets.ConstraintWidget, boolean[]):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:128:0x020b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void findVerticalWrapRecursive(android.support.constraint.solver.widgets.ConstraintWidget r9, boolean[] r10) {
        /*
            r8 = this;
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r0 = r9.mVerticalDimensionBehaviour
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r1 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            r2 = 0
            if (r0 != r1) goto L_0x0017
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r0 = r9.mHorizontalDimensionBehaviour
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r1 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r0 == r1) goto L_0x0017
            float r0 = r9.mDimensionRatio
            r1 = 0
            int r0 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1))
            if (r0 <= 0) goto L_0x0017
            r10[r2] = r2
            return
        L_0x0017:
            int r0 = r9.getOptimizerWrapHeight()
            r1 = 1
            r9.mVerticalWrapVisited = r1
            boolean r3 = r9 instanceof android.support.constraint.solver.widgets.Guideline
            r4 = 8
            if (r3 == 0) goto L_0x004d
            r10 = r9
            android.support.constraint.solver.widgets.Guideline r10 = (android.support.constraint.solver.widgets.Guideline) r10
            int r1 = r10.getOrientation()
            if (r1 != 0) goto L_0x0049
            int r0 = r10.getRelativeBegin()
            r1 = -1
            if (r0 == r1) goto L_0x003b
            int r10 = r10.getRelativeBegin()
            r0 = r2
            r2 = r10
            goto L_0x004a
        L_0x003b:
            int r0 = r10.getRelativeEnd()
            if (r0 == r1) goto L_0x0047
            int r10 = r10.getRelativeEnd()
            r0 = r10
            goto L_0x004a
        L_0x0047:
            r0 = r2
            goto L_0x004a
        L_0x0049:
            r2 = r0
        L_0x004a:
            r6 = r2
            goto L_0x0205
        L_0x004d:
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r9.mBaseline
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r3.mTarget
            if (r3 != 0) goto L_0x0066
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r9.mTop
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r3.mTarget
            if (r3 != 0) goto L_0x0066
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r9.mBottom
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r3.mTarget
            if (r3 != 0) goto L_0x0066
            int r10 = r9.getY()
            int r2 = r0 + r10
            goto L_0x004a
        L_0x0066:
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r9.mBottom
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r3.mTarget
            if (r3 == 0) goto L_0x0097
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r9.mTop
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r3.mTarget
            if (r3 == 0) goto L_0x0097
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r9.mBottom
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r3.mTarget
            android.support.constraint.solver.widgets.ConstraintAnchor r5 = r9.mTop
            android.support.constraint.solver.widgets.ConstraintAnchor r5 = r5.mTarget
            if (r3 == r5) goto L_0x0094
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r9.mBottom
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r3.mTarget
            android.support.constraint.solver.widgets.ConstraintWidget r3 = r3.mOwner
            android.support.constraint.solver.widgets.ConstraintAnchor r5 = r9.mTop
            android.support.constraint.solver.widgets.ConstraintAnchor r5 = r5.mTarget
            android.support.constraint.solver.widgets.ConstraintWidget r5 = r5.mOwner
            if (r3 != r5) goto L_0x0097
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r9.mBottom
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r3.mTarget
            android.support.constraint.solver.widgets.ConstraintWidget r3 = r3.mOwner
            android.support.constraint.solver.widgets.ConstraintWidget r5 = r9.mParent
            if (r3 == r5) goto L_0x0097
        L_0x0094:
            r10[r2] = r2
            return
        L_0x0097:
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r9.mBaseline
            boolean r3 = r3.isConnected()
            if (r3 == 0) goto L_0x00d3
            android.support.constraint.solver.widgets.ConstraintAnchor r1 = r9.mBaseline
            android.support.constraint.solver.widgets.ConstraintAnchor r1 = r1.mTarget
            android.support.constraint.solver.widgets.ConstraintWidget r1 = r1.getOwner()
            boolean r2 = r1.mVerticalWrapVisited
            if (r2 != 0) goto L_0x00ae
            r8.findVerticalWrapRecursive(r1, r10)
        L_0x00ae:
            int r10 = r1.mDistToTop
            int r2 = r1.mHeight
            int r10 = r10 - r2
            int r10 = r10 + r0
            int r10 = java.lang.Math.max(r10, r0)
            int r2 = r1.mDistToBottom
            int r1 = r1.mHeight
            int r2 = r2 - r1
            int r2 = r2 + r0
            int r0 = java.lang.Math.max(r2, r0)
            int r1 = r9.getVisibility()
            if (r1 != r4) goto L_0x00ce
            int r1 = r9.mHeight
            int r10 = r10 - r1
            int r1 = r9.mHeight
            int r0 = r0 - r1
        L_0x00ce:
            r9.mDistToTop = r10
            r9.mDistToBottom = r0
            return
        L_0x00d3:
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r9.mTop
            boolean r3 = r3.isConnected()
            r5 = 0
            if (r3 == 0) goto L_0x00f9
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r9.mTop
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r3.mTarget
            android.support.constraint.solver.widgets.ConstraintWidget r3 = r3.getOwner()
            android.support.constraint.solver.widgets.ConstraintAnchor r6 = r9.mTop
            int r6 = r6.getMargin()
            int r6 = r6 + r0
            boolean r7 = r3.isRoot()
            if (r7 != 0) goto L_0x00fb
            boolean r7 = r3.mVerticalWrapVisited
            if (r7 != 0) goto L_0x00fb
            r8.findVerticalWrapRecursive(r3, r10)
            goto L_0x00fb
        L_0x00f9:
            r6 = r0
            r3 = r5
        L_0x00fb:
            android.support.constraint.solver.widgets.ConstraintAnchor r7 = r9.mBottom
            boolean r7 = r7.isConnected()
            if (r7 == 0) goto L_0x011f
            android.support.constraint.solver.widgets.ConstraintAnchor r5 = r9.mBottom
            android.support.constraint.solver.widgets.ConstraintAnchor r5 = r5.mTarget
            android.support.constraint.solver.widgets.ConstraintWidget r5 = r5.getOwner()
            android.support.constraint.solver.widgets.ConstraintAnchor r7 = r9.mBottom
            int r7 = r7.getMargin()
            int r0 = r0 + r7
            boolean r7 = r5.isRoot()
            if (r7 != 0) goto L_0x011f
            boolean r7 = r5.mVerticalWrapVisited
            if (r7 != 0) goto L_0x011f
            r8.findVerticalWrapRecursive(r5, r10)
        L_0x011f:
            android.support.constraint.solver.widgets.ConstraintAnchor r10 = r9.mTop
            android.support.constraint.solver.widgets.ConstraintAnchor r10 = r10.mTarget
            if (r10 == 0) goto L_0x0193
            boolean r10 = r3.isRoot()
            if (r10 != 0) goto L_0x0193
            android.support.constraint.solver.widgets.ConstraintAnchor r10 = r9.mTop
            android.support.constraint.solver.widgets.ConstraintAnchor r10 = r10.mTarget
            android.support.constraint.solver.widgets.ConstraintAnchor$Type r10 = r10.getType()
            android.support.constraint.solver.widgets.ConstraintAnchor$Type r7 = android.support.constraint.solver.widgets.ConstraintAnchor.Type.TOP
            if (r10 != r7) goto L_0x0140
            int r10 = r3.mDistToTop
            int r7 = r3.getOptimizerWrapHeight()
            int r10 = r10 - r7
            int r6 = r6 + r10
            goto L_0x014f
        L_0x0140:
            android.support.constraint.solver.widgets.ConstraintAnchor r10 = r9.mTop
            android.support.constraint.solver.widgets.ConstraintAnchor r10 = r10.mTarget
            android.support.constraint.solver.widgets.ConstraintAnchor$Type r10 = r10.getType()
            android.support.constraint.solver.widgets.ConstraintAnchor$Type r7 = android.support.constraint.solver.widgets.ConstraintAnchor.Type.BOTTOM
            if (r10 != r7) goto L_0x014f
            int r10 = r3.mDistToTop
            int r6 = r6 + r10
        L_0x014f:
            boolean r10 = r3.mTopHasCentered
            if (r10 != 0) goto L_0x0178
            android.support.constraint.solver.widgets.ConstraintAnchor r10 = r3.mTop
            android.support.constraint.solver.widgets.ConstraintAnchor r10 = r10.mTarget
            if (r10 == 0) goto L_0x0176
            android.support.constraint.solver.widgets.ConstraintAnchor r10 = r3.mTop
            android.support.constraint.solver.widgets.ConstraintAnchor r10 = r10.mTarget
            android.support.constraint.solver.widgets.ConstraintWidget r10 = r10.mOwner
            if (r10 == r9) goto L_0x0176
            android.support.constraint.solver.widgets.ConstraintAnchor r10 = r3.mBottom
            android.support.constraint.solver.widgets.ConstraintAnchor r10 = r10.mTarget
            if (r10 == 0) goto L_0x0176
            android.support.constraint.solver.widgets.ConstraintAnchor r10 = r3.mBottom
            android.support.constraint.solver.widgets.ConstraintAnchor r10 = r10.mTarget
            android.support.constraint.solver.widgets.ConstraintWidget r10 = r10.mOwner
            if (r10 == r9) goto L_0x0176
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r10 = r3.mVerticalDimensionBehaviour
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r7 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r10 == r7) goto L_0x0176
            goto L_0x0178
        L_0x0176:
            r10 = r2
            goto L_0x0179
        L_0x0178:
            r10 = r1
        L_0x0179:
            r9.mTopHasCentered = r10
            boolean r10 = r9.mTopHasCentered
            if (r10 == 0) goto L_0x0193
            android.support.constraint.solver.widgets.ConstraintAnchor r10 = r3.mBottom
            android.support.constraint.solver.widgets.ConstraintAnchor r10 = r10.mTarget
            if (r10 != 0) goto L_0x0186
            goto L_0x018e
        L_0x0186:
            android.support.constraint.solver.widgets.ConstraintAnchor r10 = r3.mBottom
            android.support.constraint.solver.widgets.ConstraintAnchor r10 = r10.mTarget
            android.support.constraint.solver.widgets.ConstraintWidget r10 = r10.mOwner
            if (r10 == r9) goto L_0x0193
        L_0x018e:
            int r10 = r3.mDistToTop
            int r10 = r6 - r10
            int r6 = r6 + r10
        L_0x0193:
            android.support.constraint.solver.widgets.ConstraintAnchor r10 = r9.mBottom
            android.support.constraint.solver.widgets.ConstraintAnchor r10 = r10.mTarget
            if (r10 == 0) goto L_0x0205
            boolean r10 = r5.isRoot()
            if (r10 != 0) goto L_0x0205
            android.support.constraint.solver.widgets.ConstraintAnchor r10 = r9.mBottom
            android.support.constraint.solver.widgets.ConstraintAnchor r10 = r10.mTarget
            android.support.constraint.solver.widgets.ConstraintAnchor$Type r10 = r10.getType()
            android.support.constraint.solver.widgets.ConstraintAnchor$Type r3 = android.support.constraint.solver.widgets.ConstraintAnchor.Type.BOTTOM
            if (r10 != r3) goto L_0x01b4
            int r10 = r5.mDistToBottom
            int r3 = r5.getOptimizerWrapHeight()
            int r10 = r10 - r3
            int r0 = r0 + r10
            goto L_0x01c3
        L_0x01b4:
            android.support.constraint.solver.widgets.ConstraintAnchor r10 = r9.mBottom
            android.support.constraint.solver.widgets.ConstraintAnchor r10 = r10.mTarget
            android.support.constraint.solver.widgets.ConstraintAnchor$Type r10 = r10.getType()
            android.support.constraint.solver.widgets.ConstraintAnchor$Type r3 = android.support.constraint.solver.widgets.ConstraintAnchor.Type.TOP
            if (r10 != r3) goto L_0x01c3
            int r10 = r5.mDistToBottom
            int r0 = r0 + r10
        L_0x01c3:
            boolean r10 = r5.mBottomHasCentered
            if (r10 != 0) goto L_0x01eb
            android.support.constraint.solver.widgets.ConstraintAnchor r10 = r5.mTop
            android.support.constraint.solver.widgets.ConstraintAnchor r10 = r10.mTarget
            if (r10 == 0) goto L_0x01ea
            android.support.constraint.solver.widgets.ConstraintAnchor r10 = r5.mTop
            android.support.constraint.solver.widgets.ConstraintAnchor r10 = r10.mTarget
            android.support.constraint.solver.widgets.ConstraintWidget r10 = r10.mOwner
            if (r10 == r9) goto L_0x01ea
            android.support.constraint.solver.widgets.ConstraintAnchor r10 = r5.mBottom
            android.support.constraint.solver.widgets.ConstraintAnchor r10 = r10.mTarget
            if (r10 == 0) goto L_0x01ea
            android.support.constraint.solver.widgets.ConstraintAnchor r10 = r5.mBottom
            android.support.constraint.solver.widgets.ConstraintAnchor r10 = r10.mTarget
            android.support.constraint.solver.widgets.ConstraintWidget r10 = r10.mOwner
            if (r10 == r9) goto L_0x01ea
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r10 = r5.mVerticalDimensionBehaviour
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r3 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r10 == r3) goto L_0x01ea
            goto L_0x01eb
        L_0x01ea:
            r1 = r2
        L_0x01eb:
            r9.mBottomHasCentered = r1
            boolean r10 = r9.mBottomHasCentered
            if (r10 == 0) goto L_0x0205
            android.support.constraint.solver.widgets.ConstraintAnchor r10 = r5.mTop
            android.support.constraint.solver.widgets.ConstraintAnchor r10 = r10.mTarget
            if (r10 != 0) goto L_0x01f8
            goto L_0x0200
        L_0x01f8:
            android.support.constraint.solver.widgets.ConstraintAnchor r10 = r5.mTop
            android.support.constraint.solver.widgets.ConstraintAnchor r10 = r10.mTarget
            android.support.constraint.solver.widgets.ConstraintWidget r10 = r10.mOwner
            if (r10 == r9) goto L_0x0205
        L_0x0200:
            int r10 = r5.mDistToBottom
            int r10 = r0 - r10
            int r0 = r0 + r10
        L_0x0205:
            int r10 = r9.getVisibility()
            if (r10 != r4) goto L_0x0211
            int r10 = r9.mHeight
            int r6 = r6 - r10
            int r10 = r9.mHeight
            int r0 = r0 - r10
        L_0x0211:
            r9.mDistToTop = r6
            r9.mDistToBottom = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.constraint.solver.widgets.ConstraintWidgetContainer.findVerticalWrapRecursive(android.support.constraint.solver.widgets.ConstraintWidget, boolean[]):void");
    }

    public void findWrapSize(ArrayList<ConstraintWidget> arrayList, boolean[] zArr) {
        ArrayList<ConstraintWidget> arrayList2 = arrayList;
        boolean[] zArr2 = zArr;
        int size = arrayList.size();
        char c = 0;
        zArr2[0] = true;
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        int i7 = 0;
        while (i < size) {
            ConstraintWidget constraintWidget = arrayList2.get(i);
            if (!constraintWidget.isRoot()) {
                if (!constraintWidget.mHorizontalWrapVisited) {
                    findHorizontalWrapRecursive(constraintWidget, zArr2);
                }
                if (!constraintWidget.mVerticalWrapVisited) {
                    findVerticalWrapRecursive(constraintWidget, zArr2);
                }
                if (zArr2[c]) {
                    int width = (constraintWidget.mDistToLeft + constraintWidget.mDistToRight) - constraintWidget.getWidth();
                    int height = (constraintWidget.mDistToTop + constraintWidget.mDistToBottom) - constraintWidget.getHeight();
                    int width2 = constraintWidget.mHorizontalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_PARENT ? constraintWidget.getWidth() + constraintWidget.mLeft.mMargin + constraintWidget.mRight.mMargin : width;
                    int height2 = constraintWidget.mVerticalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_PARENT ? constraintWidget.getHeight() + constraintWidget.mTop.mMargin + constraintWidget.mBottom.mMargin : height;
                    if (constraintWidget.getVisibility() == 8) {
                        width2 = 0;
                        height2 = 0;
                    }
                    i2 = Math.max(i2, constraintWidget.mDistToLeft);
                    i3 = Math.max(i3, constraintWidget.mDistToRight);
                    i6 = Math.max(i6, constraintWidget.mDistToBottom);
                    i5 = Math.max(i5, constraintWidget.mDistToTop);
                    int max = Math.max(i4, width2);
                    i7 = Math.max(i7, height2);
                    i4 = max;
                } else {
                    return;
                }
            }
            i++;
            c = 0;
        }
        this.mWrapWidth = Math.max(this.mMinWidth, Math.max(Math.max(i2, i3), i4));
        this.mWrapHeight = Math.max(this.mMinHeight, Math.max(Math.max(i5, i6), i7));
        for (int i8 = 0; i8 < size; i8++) {
            ConstraintWidget constraintWidget2 = arrayList2.get(i8);
            constraintWidget2.mHorizontalWrapVisited = false;
            constraintWidget2.mVerticalWrapVisited = false;
            constraintWidget2.mLeftHasCentered = false;
            constraintWidget2.mRightHasCentered = false;
            constraintWidget2.mTopHasCentered = false;
            constraintWidget2.mBottomHasCentered = false;
        }
    }

    public int layoutFindGroups() {
        ConstraintAnchor.Type[] typeArr = {ConstraintAnchor.Type.LEFT, ConstraintAnchor.Type.RIGHT, ConstraintAnchor.Type.TOP, ConstraintAnchor.Type.BASELINE, ConstraintAnchor.Type.BOTTOM};
        int size = this.mChildren.size();
        int i = 1;
        for (int i2 = 0; i2 < size; i2++) {
            ConstraintWidget constraintWidget = (ConstraintWidget) this.mChildren.get(i2);
            ConstraintAnchor constraintAnchor = constraintWidget.mLeft;
            if (constraintAnchor.mTarget == null) {
                constraintAnchor.mGroup = Integer.MAX_VALUE;
            } else if (setGroup(constraintAnchor, i) == i) {
                i++;
            }
            ConstraintAnchor constraintAnchor2 = constraintWidget.mTop;
            if (constraintAnchor2.mTarget == null) {
                constraintAnchor2.mGroup = Integer.MAX_VALUE;
            } else if (setGroup(constraintAnchor2, i) == i) {
                i++;
            }
            ConstraintAnchor constraintAnchor3 = constraintWidget.mRight;
            if (constraintAnchor3.mTarget == null) {
                constraintAnchor3.mGroup = Integer.MAX_VALUE;
            } else if (setGroup(constraintAnchor3, i) == i) {
                i++;
            }
            ConstraintAnchor constraintAnchor4 = constraintWidget.mBottom;
            if (constraintAnchor4.mTarget == null) {
                constraintAnchor4.mGroup = Integer.MAX_VALUE;
            } else if (setGroup(constraintAnchor4, i) == i) {
                i++;
            }
            ConstraintAnchor constraintAnchor5 = constraintWidget.mBaseline;
            if (constraintAnchor5.mTarget == null) {
                constraintAnchor5.mGroup = Integer.MAX_VALUE;
            } else if (setGroup(constraintAnchor5, i) == i) {
                i++;
            }
        }
        boolean z = true;
        while (z) {
            int i3 = 0;
            boolean z2 = false;
            while (i3 < size) {
                ConstraintWidget constraintWidget2 = (ConstraintWidget) this.mChildren.get(i3);
                boolean z3 = z2;
                for (ConstraintAnchor.Type type : typeArr) {
                    ConstraintAnchor constraintAnchor6 = null;
                    switch (type) {
                        case LEFT:
                            constraintAnchor6 = constraintWidget2.mLeft;
                            break;
                        case TOP:
                            constraintAnchor6 = constraintWidget2.mTop;
                            break;
                        case RIGHT:
                            constraintAnchor6 = constraintWidget2.mRight;
                            break;
                        case BOTTOM:
                            constraintAnchor6 = constraintWidget2.mBottom;
                            break;
                        case BASELINE:
                            constraintAnchor6 = constraintWidget2.mBaseline;
                            break;
                    }
                    ConstraintAnchor constraintAnchor7 = constraintAnchor6.mTarget;
                    if (constraintAnchor7 != null) {
                        if (!(constraintAnchor7.mOwner.getParent() == null || constraintAnchor7.mGroup == constraintAnchor6.mGroup)) {
                            int i4 = constraintAnchor6.mGroup > constraintAnchor7.mGroup ? constraintAnchor7.mGroup : constraintAnchor6.mGroup;
                            constraintAnchor6.mGroup = i4;
                            constraintAnchor7.mGroup = i4;
                            z3 = true;
                        }
                        ConstraintAnchor opposite = constraintAnchor7.getOpposite();
                        if (!(opposite == null || opposite.mGroup == constraintAnchor6.mGroup)) {
                            int i5 = constraintAnchor6.mGroup > opposite.mGroup ? opposite.mGroup : constraintAnchor6.mGroup;
                            constraintAnchor6.mGroup = i5;
                            opposite.mGroup = i5;
                            z3 = true;
                        }
                    }
                }
                i3++;
                z2 = z3;
            }
            z = z2;
        }
        int[] iArr = new int[((this.mChildren.size() * typeArr.length) + 1)];
        Arrays.fill(iArr, -1);
        int i6 = 0;
        for (int i7 = 0; i7 < size; i7++) {
            ConstraintWidget constraintWidget3 = (ConstraintWidget) this.mChildren.get(i7);
            ConstraintAnchor constraintAnchor8 = constraintWidget3.mLeft;
            if (constraintAnchor8.mGroup != Integer.MAX_VALUE) {
                int i8 = constraintAnchor8.mGroup;
                if (iArr[i8] == -1) {
                    iArr[i8] = i6;
                    i6++;
                }
                constraintAnchor8.mGroup = iArr[i8];
            }
            ConstraintAnchor constraintAnchor9 = constraintWidget3.mTop;
            if (constraintAnchor9.mGroup != Integer.MAX_VALUE) {
                int i9 = constraintAnchor9.mGroup;
                if (iArr[i9] == -1) {
                    iArr[i9] = i6;
                    i6++;
                }
                constraintAnchor9.mGroup = iArr[i9];
            }
            ConstraintAnchor constraintAnchor10 = constraintWidget3.mRight;
            if (constraintAnchor10.mGroup != Integer.MAX_VALUE) {
                int i10 = constraintAnchor10.mGroup;
                if (iArr[i10] == -1) {
                    iArr[i10] = i6;
                    i6++;
                }
                constraintAnchor10.mGroup = iArr[i10];
            }
            ConstraintAnchor constraintAnchor11 = constraintWidget3.mBottom;
            if (constraintAnchor11.mGroup != Integer.MAX_VALUE) {
                int i11 = constraintAnchor11.mGroup;
                if (iArr[i11] == -1) {
                    iArr[i11] = i6;
                    i6++;
                }
                constraintAnchor11.mGroup = iArr[i11];
            }
            ConstraintAnchor constraintAnchor12 = constraintWidget3.mBaseline;
            if (constraintAnchor12.mGroup != Integer.MAX_VALUE) {
                int i12 = constraintAnchor12.mGroup;
                if (iArr[i12] == -1) {
                    iArr[i12] = i6;
                    i6++;
                }
                constraintAnchor12.mGroup = iArr[i12];
            }
        }
        return i6;
    }

    public void layoutWithGroup(int i) {
        int i2 = this.mX;
        int i3 = this.mY;
        if (this.mParent != null) {
            if (this.mSnapshot == null) {
                this.mSnapshot = new Snapshot(this);
            }
            this.mSnapshot.updateFrom(this);
            this.mX = 0;
            this.mY = 0;
            resetAnchors();
            resetSolverVariables(this.mSystem.getCache());
        } else {
            this.mX = 0;
            this.mY = 0;
        }
        int size = this.mChildren.size();
        for (int i4 = 0; i4 < size; i4++) {
            ConstraintWidget constraintWidget = (ConstraintWidget) this.mChildren.get(i4);
            if (constraintWidget instanceof WidgetContainer) {
                ((WidgetContainer) constraintWidget).layout();
            }
        }
        this.mLeft.mGroup = 0;
        this.mRight.mGroup = 0;
        this.mTop.mGroup = 1;
        this.mBottom.mGroup = 1;
        this.mSystem.reset();
        for (int i5 = 0; i5 < i; i5++) {
            try {
                addToSolver(this.mSystem, i5);
                this.mSystem.minimize();
                updateFromSolver(this.mSystem, i5);
            } catch (Exception e) {
                e.printStackTrace();
            }
            updateFromSolver(this.mSystem, -2);
        }
        if (this.mParent != null) {
            int width = getWidth();
            int height = getHeight();
            this.mSnapshot.applyTo(this);
            setWidth(width);
            setHeight(height);
        } else {
            this.mX = i2;
            this.mY = i3;
        }
        if (this == getRootConstraintContainer()) {
            updateDrawPosition();
        }
    }

    public ArrayList<Guideline> getVerticalGuidelines() {
        ArrayList<Guideline> arrayList = new ArrayList<>();
        int size = this.mChildren.size();
        for (int i = 0; i < size; i++) {
            ConstraintWidget constraintWidget = (ConstraintWidget) this.mChildren.get(i);
            if (constraintWidget instanceof Guideline) {
                Guideline guideline = (Guideline) constraintWidget;
                if (guideline.getOrientation() == 1) {
                    arrayList.add(guideline);
                }
            }
        }
        return arrayList;
    }

    public ArrayList<Guideline> getHorizontalGuidelines() {
        ArrayList<Guideline> arrayList = new ArrayList<>();
        int size = this.mChildren.size();
        for (int i = 0; i < size; i++) {
            ConstraintWidget constraintWidget = (ConstraintWidget) this.mChildren.get(i);
            if (constraintWidget instanceof Guideline) {
                Guideline guideline = (Guideline) constraintWidget;
                if (guideline.getOrientation() == 0) {
                    arrayList.add(guideline);
                }
            }
        }
        return arrayList;
    }

    public LinearSystem getSystem() {
        return this.mSystem;
    }

    private void resetChains() {
        this.mHorizontalChainsSize = 0;
        this.mVerticalChainsSize = 0;
    }

    /* access modifiers changed from: package-private */
    public void addChain(ConstraintWidget constraintWidget, int i) {
        if (i == 0) {
            while (constraintWidget.mLeft.mTarget != null && constraintWidget.mLeft.mTarget.mOwner.mRight.mTarget != null && constraintWidget.mLeft.mTarget.mOwner.mRight.mTarget == constraintWidget.mLeft && constraintWidget.mLeft.mTarget.mOwner != constraintWidget) {
                constraintWidget = constraintWidget.mLeft.mTarget.mOwner;
            }
            addHorizontalChain(constraintWidget);
        } else if (i == 1) {
            while (constraintWidget.mTop.mTarget != null && constraintWidget.mTop.mTarget.mOwner.mBottom.mTarget != null && constraintWidget.mTop.mTarget.mOwner.mBottom.mTarget == constraintWidget.mTop && constraintWidget.mTop.mTarget.mOwner != constraintWidget) {
                constraintWidget = constraintWidget.mTop.mTarget.mOwner;
            }
            addVerticalChain(constraintWidget);
        }
    }

    private void addHorizontalChain(ConstraintWidget constraintWidget) {
        int i = 0;
        while (i < this.mHorizontalChainsSize) {
            if (this.mHorizontalChainsArray[i] != constraintWidget) {
                i++;
            } else {
                return;
            }
        }
        if (this.mHorizontalChainsSize + 1 >= this.mHorizontalChainsArray.length) {
            this.mHorizontalChainsArray = (ConstraintWidget[]) Arrays.copyOf(this.mHorizontalChainsArray, this.mHorizontalChainsArray.length * 2);
        }
        this.mHorizontalChainsArray[this.mHorizontalChainsSize] = constraintWidget;
        this.mHorizontalChainsSize++;
    }

    private void addVerticalChain(ConstraintWidget constraintWidget) {
        int i = 0;
        while (i < this.mVerticalChainsSize) {
            if (this.mVerticalChainsArray[i] != constraintWidget) {
                i++;
            } else {
                return;
            }
        }
        if (this.mVerticalChainsSize + 1 >= this.mVerticalChainsArray.length) {
            this.mVerticalChainsArray = (ConstraintWidget[]) Arrays.copyOf(this.mVerticalChainsArray, this.mVerticalChainsArray.length * 2);
        }
        this.mVerticalChainsArray[this.mVerticalChainsSize] = constraintWidget;
        this.mVerticalChainsSize++;
    }

    private int countMatchConstraintsChainedWidgets(LinearSystem linearSystem, ConstraintWidget[] constraintWidgetArr, ConstraintWidget constraintWidget, int i, boolean[] zArr) {
        int i2;
        char c;
        char c2;
        LinearSystem linearSystem2 = linearSystem;
        ConstraintWidget constraintWidget2 = constraintWidget;
        zArr[0] = true;
        zArr[1] = false;
        ConstraintWidget constraintWidget3 = null;
        constraintWidgetArr[0] = null;
        constraintWidgetArr[2] = null;
        constraintWidgetArr[1] = null;
        constraintWidgetArr[3] = null;
        float f = 0.0f;
        int i3 = 5;
        int i4 = 8;
        if (i == 0) {
            boolean z = constraintWidget2.mLeft.mTarget == null || constraintWidget2.mLeft.mTarget.mOwner == this;
            constraintWidget2.mHorizontalNextWidget = null;
            i2 = 0;
            ConstraintWidget constraintWidget4 = null;
            ConstraintWidget constraintWidget5 = constraintWidget.getVisibility() != 8 ? constraintWidget2 : null;
            ConstraintWidget constraintWidget6 = constraintWidget5;
            ConstraintWidget constraintWidget7 = constraintWidget2;
            while (constraintWidget7.mRight.mTarget != null) {
                constraintWidget7.mHorizontalNextWidget = constraintWidget3;
                if (constraintWidget7.getVisibility() != 8) {
                    if (constraintWidget6 == null) {
                        constraintWidget6 = constraintWidget7;
                    }
                    if (!(constraintWidget5 == null || constraintWidget5 == constraintWidget7)) {
                        constraintWidget5.mHorizontalNextWidget = constraintWidget7;
                    }
                    constraintWidget5 = constraintWidget7;
                } else {
                    linearSystem2.addEquality(constraintWidget7.mLeft.mSolverVariable, constraintWidget7.mLeft.mTarget.mSolverVariable, 0, 5);
                    linearSystem2.addEquality(constraintWidget7.mRight.mSolverVariable, constraintWidget7.mLeft.mSolverVariable, 0, 5);
                }
                if (constraintWidget7.getVisibility() != 8 && constraintWidget7.mHorizontalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                    if (constraintWidget7.mVerticalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                        zArr[0] = false;
                    }
                    if (constraintWidget7.mDimensionRatio <= f) {
                        zArr[0] = false;
                        int i5 = i2 + 1;
                        if (i5 >= this.mMatchConstraintsChainedWidgets.length) {
                            this.mMatchConstraintsChainedWidgets = (ConstraintWidget[]) Arrays.copyOf(this.mMatchConstraintsChainedWidgets, this.mMatchConstraintsChainedWidgets.length * 2);
                        }
                        this.mMatchConstraintsChainedWidgets[i2] = constraintWidget7;
                        i2 = i5;
                    }
                }
                if (constraintWidget7.mRight.mTarget.mOwner.mLeft.mTarget == null || constraintWidget7.mRight.mTarget.mOwner.mLeft.mTarget.mOwner != constraintWidget7 || constraintWidget7.mRight.mTarget.mOwner == constraintWidget7) {
                    break;
                }
                constraintWidget4 = constraintWidget7.mRight.mTarget.mOwner;
                constraintWidget7 = constraintWidget4;
                constraintWidget3 = null;
                f = 0.0f;
            }
            if (!(constraintWidget7.mRight.mTarget == null || constraintWidget7.mRight.mTarget.mOwner == this)) {
                z = false;
            }
            if (constraintWidget2.mLeft.mTarget == null || constraintWidget4.mRight.mTarget == null) {
                c2 = 1;
                zArr[1] = true;
            } else {
                c2 = 1;
            }
            constraintWidget2.mHorizontalChainFixedPosition = z;
            constraintWidget4.mHorizontalNextWidget = null;
            constraintWidgetArr[0] = constraintWidget2;
            constraintWidgetArr[2] = constraintWidget6;
            constraintWidgetArr[c2] = constraintWidget4;
            constraintWidgetArr[3] = constraintWidget5;
        } else {
            boolean z2 = constraintWidget2.mTop.mTarget == null || constraintWidget2.mTop.mTarget.mOwner == this;
            ConstraintWidget constraintWidget8 = null;
            constraintWidget2.mVerticalNextWidget = null;
            int i6 = 0;
            ConstraintWidget constraintWidget9 = null;
            ConstraintWidget constraintWidget10 = constraintWidget.getVisibility() != 8 ? constraintWidget2 : null;
            ConstraintWidget constraintWidget11 = constraintWidget10;
            ConstraintWidget constraintWidget12 = constraintWidget2;
            while (constraintWidget12.mBottom.mTarget != null) {
                constraintWidget12.mVerticalNextWidget = constraintWidget8;
                if (constraintWidget12.getVisibility() != i4) {
                    if (constraintWidget10 == null) {
                        constraintWidget10 = constraintWidget12;
                    }
                    if (!(constraintWidget11 == null || constraintWidget11 == constraintWidget12)) {
                        constraintWidget11.mVerticalNextWidget = constraintWidget12;
                    }
                    constraintWidget11 = constraintWidget12;
                } else {
                    linearSystem2.addEquality(constraintWidget12.mTop.mSolverVariable, constraintWidget12.mTop.mTarget.mSolverVariable, 0, i3);
                    linearSystem2.addEquality(constraintWidget12.mBottom.mSolverVariable, constraintWidget12.mTop.mSolverVariable, 0, i3);
                }
                if (constraintWidget12.getVisibility() != i4 && constraintWidget12.mVerticalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                    if (constraintWidget12.mHorizontalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                        zArr[0] = false;
                    }
                    if (constraintWidget12.mDimensionRatio <= 0.0f) {
                        zArr[0] = false;
                        int i7 = i6 + 1;
                        if (i7 >= this.mMatchConstraintsChainedWidgets.length) {
                            this.mMatchConstraintsChainedWidgets = (ConstraintWidget[]) Arrays.copyOf(this.mMatchConstraintsChainedWidgets, this.mMatchConstraintsChainedWidgets.length * 2);
                        }
                        this.mMatchConstraintsChainedWidgets[i6] = constraintWidget12;
                        i6 = i7;
                    }
                }
                if (constraintWidget12.mBottom.mTarget.mOwner.mTop.mTarget == null || constraintWidget12.mBottom.mTarget.mOwner.mTop.mTarget.mOwner != constraintWidget12 || constraintWidget12.mBottom.mTarget.mOwner == constraintWidget12) {
                    break;
                }
                constraintWidget9 = constraintWidget12.mBottom.mTarget.mOwner;
                constraintWidget12 = constraintWidget9;
                constraintWidget8 = null;
                i3 = 5;
                i4 = 8;
            }
            i2 = i6;
            if (!(constraintWidget12.mBottom.mTarget == null || constraintWidget12.mBottom.mTarget.mOwner == this)) {
                z2 = false;
            }
            if (constraintWidget2.mTop.mTarget == null || constraintWidget9.mBottom.mTarget == null) {
                c = 1;
                zArr[1] = true;
            } else {
                c = 1;
            }
            constraintWidget2.mVerticalChainFixedPosition = z2;
            constraintWidget9.mVerticalNextWidget = null;
            constraintWidgetArr[0] = constraintWidget2;
            constraintWidgetArr[2] = constraintWidget10;
            constraintWidgetArr[c] = constraintWidget9;
            constraintWidgetArr[3] = constraintWidget11;
        }
        return i2;
    }
}
