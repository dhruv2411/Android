package android.support.constraint.solver;

import java.io.PrintStream;
import java.util.Arrays;

public class ArrayLinkedVariables {
    private static final boolean DEBUG = false;
    private static final int NONE = -1;
    private int ROW_SIZE = 8;
    private SolverVariable candidate = null;
    int currentSize = 0;
    private int[] mArrayIndices = new int[this.ROW_SIZE];
    private int[] mArrayNextIndices = new int[this.ROW_SIZE];
    private float[] mArrayValues = new float[this.ROW_SIZE];
    private final Cache mCache;
    private boolean mDidFillOnce = false;
    private int mHead = -1;
    private int mLast = -1;
    private final ArrayRow mRow;

    ArrayLinkedVariables(ArrayRow arrayRow, Cache cache) {
        this.mRow = arrayRow;
        this.mCache = cache;
    }

    public final void put(SolverVariable solverVariable, float f) {
        if (f == 0.0f) {
            remove(solverVariable);
        } else if (this.mHead == -1) {
            this.mHead = 0;
            this.mArrayValues[this.mHead] = f;
            this.mArrayIndices[this.mHead] = solverVariable.id;
            this.mArrayNextIndices[this.mHead] = -1;
            this.currentSize++;
            if (!this.mDidFillOnce) {
                this.mLast++;
            }
        } else {
            int i = this.mHead;
            int i2 = 0;
            int i3 = -1;
            while (i != -1 && i2 < this.currentSize) {
                if (this.mArrayIndices[i] == solverVariable.id) {
                    this.mArrayValues[i] = f;
                    return;
                }
                if (this.mArrayIndices[i] < solverVariable.id) {
                    i3 = i;
                }
                i = this.mArrayNextIndices[i];
                i2++;
            }
            int i4 = this.mLast + 1;
            if (this.mDidFillOnce) {
                if (this.mArrayIndices[this.mLast] == -1) {
                    i4 = this.mLast;
                } else {
                    i4 = this.mArrayIndices.length;
                }
            }
            if (i4 >= this.mArrayIndices.length && this.currentSize < this.mArrayIndices.length) {
                int i5 = 0;
                while (true) {
                    if (i5 >= this.mArrayIndices.length) {
                        break;
                    } else if (this.mArrayIndices[i5] == -1) {
                        i4 = i5;
                        break;
                    } else {
                        i5++;
                    }
                }
            }
            if (i4 >= this.mArrayIndices.length) {
                i4 = this.mArrayIndices.length;
                this.ROW_SIZE *= 2;
                this.mDidFillOnce = false;
                this.mLast = i4 - 1;
                this.mArrayValues = Arrays.copyOf(this.mArrayValues, this.ROW_SIZE);
                this.mArrayIndices = Arrays.copyOf(this.mArrayIndices, this.ROW_SIZE);
                this.mArrayNextIndices = Arrays.copyOf(this.mArrayNextIndices, this.ROW_SIZE);
            }
            this.mArrayIndices[i4] = solverVariable.id;
            this.mArrayValues[i4] = f;
            if (i3 != -1) {
                this.mArrayNextIndices[i4] = this.mArrayNextIndices[i3];
                this.mArrayNextIndices[i3] = i4;
            } else {
                this.mArrayNextIndices[i4] = this.mHead;
                this.mHead = i4;
            }
            this.currentSize++;
            if (!this.mDidFillOnce) {
                this.mLast++;
            }
            if (this.currentSize >= this.mArrayIndices.length) {
                this.mDidFillOnce = true;
            }
        }
    }

    public final void add(SolverVariable solverVariable, float f) {
        if (f != 0.0f) {
            if (this.mHead == -1) {
                this.mHead = 0;
                this.mArrayValues[this.mHead] = f;
                this.mArrayIndices[this.mHead] = solverVariable.id;
                this.mArrayNextIndices[this.mHead] = -1;
                this.currentSize++;
                if (!this.mDidFillOnce) {
                    this.mLast++;
                    return;
                }
                return;
            }
            int i = this.mHead;
            int i2 = 0;
            int i3 = -1;
            while (i != -1 && i2 < this.currentSize) {
                int i4 = this.mArrayIndices[i];
                if (i4 == solverVariable.id) {
                    float[] fArr = this.mArrayValues;
                    fArr[i] = fArr[i] + f;
                    if (this.mArrayValues[i] == 0.0f) {
                        if (i == this.mHead) {
                            this.mHead = this.mArrayNextIndices[i];
                        } else {
                            this.mArrayNextIndices[i3] = this.mArrayNextIndices[i];
                        }
                        this.mCache.mIndexedVariables[i4].removeClientEquation(this.mRow);
                        if (this.mDidFillOnce) {
                            this.mLast = i;
                        }
                        this.currentSize--;
                        return;
                    }
                    return;
                }
                if (this.mArrayIndices[i] < solverVariable.id) {
                    i3 = i;
                }
                i = this.mArrayNextIndices[i];
                i2++;
            }
            int i5 = this.mLast + 1;
            if (this.mDidFillOnce) {
                if (this.mArrayIndices[this.mLast] == -1) {
                    i5 = this.mLast;
                } else {
                    i5 = this.mArrayIndices.length;
                }
            }
            if (i5 >= this.mArrayIndices.length && this.currentSize < this.mArrayIndices.length) {
                int i6 = 0;
                while (true) {
                    if (i6 >= this.mArrayIndices.length) {
                        break;
                    } else if (this.mArrayIndices[i6] == -1) {
                        i5 = i6;
                        break;
                    } else {
                        i6++;
                    }
                }
            }
            if (i5 >= this.mArrayIndices.length) {
                i5 = this.mArrayIndices.length;
                this.ROW_SIZE *= 2;
                this.mDidFillOnce = false;
                this.mLast = i5 - 1;
                this.mArrayValues = Arrays.copyOf(this.mArrayValues, this.ROW_SIZE);
                this.mArrayIndices = Arrays.copyOf(this.mArrayIndices, this.ROW_SIZE);
                this.mArrayNextIndices = Arrays.copyOf(this.mArrayNextIndices, this.ROW_SIZE);
            }
            this.mArrayIndices[i5] = solverVariable.id;
            this.mArrayValues[i5] = f;
            if (i3 != -1) {
                this.mArrayNextIndices[i5] = this.mArrayNextIndices[i3];
                this.mArrayNextIndices[i3] = i5;
            } else {
                this.mArrayNextIndices[i5] = this.mHead;
                this.mHead = i5;
            }
            this.currentSize++;
            if (!this.mDidFillOnce) {
                this.mLast++;
            }
            if (this.mLast >= this.mArrayIndices.length) {
                this.mDidFillOnce = true;
                this.mLast = this.mArrayIndices.length - 1;
            }
        }
    }

    public final float remove(SolverVariable solverVariable) {
        if (this.candidate == solverVariable) {
            this.candidate = null;
        }
        if (this.mHead == -1) {
            return 0.0f;
        }
        int i = this.mHead;
        int i2 = 0;
        int i3 = -1;
        while (i != -1 && i2 < this.currentSize) {
            int i4 = this.mArrayIndices[i];
            if (i4 == solverVariable.id) {
                if (i == this.mHead) {
                    this.mHead = this.mArrayNextIndices[i];
                } else {
                    this.mArrayNextIndices[i3] = this.mArrayNextIndices[i];
                }
                this.mCache.mIndexedVariables[i4].removeClientEquation(this.mRow);
                this.currentSize--;
                this.mArrayIndices[i] = -1;
                if (this.mDidFillOnce) {
                    this.mLast = i;
                }
                return this.mArrayValues[i];
            }
            i2++;
            i3 = i;
            i = this.mArrayNextIndices[i];
        }
        return 0.0f;
    }

    public final void clear() {
        this.mHead = -1;
        this.mLast = -1;
        this.mDidFillOnce = false;
        this.currentSize = 0;
    }

    /* access modifiers changed from: package-private */
    public final boolean containsKey(SolverVariable solverVariable) {
        if (this.mHead == -1) {
            return false;
        }
        int i = this.mHead;
        int i2 = 0;
        while (i != -1 && i2 < this.currentSize) {
            if (this.mArrayIndices[i] == solverVariable.id) {
                return true;
            }
            i = this.mArrayNextIndices[i];
            i2++;
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public boolean hasAtLeastOnePositiveVariable() {
        int i = this.mHead;
        int i2 = 0;
        while (i != -1 && i2 < this.currentSize) {
            if (this.mArrayValues[i] > 0.0f) {
                return true;
            }
            i = this.mArrayNextIndices[i];
            i2++;
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public void invert() {
        int i = this.mHead;
        int i2 = 0;
        while (i != -1 && i2 < this.currentSize) {
            float[] fArr = this.mArrayValues;
            fArr[i] = fArr[i] * -1.0f;
            i = this.mArrayNextIndices[i];
            i2++;
        }
    }

    /* access modifiers changed from: package-private */
    public void divideByAmount(float f) {
        int i = this.mHead;
        int i2 = 0;
        while (i != -1 && i2 < this.currentSize) {
            float[] fArr = this.mArrayValues;
            fArr[i] = fArr[i] / f;
            i = this.mArrayNextIndices[i];
            i2++;
        }
    }

    /* access modifiers changed from: package-private */
    public void updateClientEquations(ArrayRow arrayRow) {
        int i = this.mHead;
        int i2 = 0;
        while (i != -1 && i2 < this.currentSize) {
            this.mCache.mIndexedVariables[this.mArrayIndices[i]].addClientEquation(arrayRow);
            i = this.mArrayNextIndices[i];
            i2++;
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0032  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x0058 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.support.constraint.solver.SolverVariable pickPivotCandidate() {
        /*
            r9 = this;
            int r0 = r9.mHead
            r1 = 0
            r2 = 0
            r3 = r1
        L_0x0005:
            r4 = -1
            if (r0 == r4) goto L_0x005f
            int r4 = r9.currentSize
            if (r2 >= r4) goto L_0x005f
            float[] r4 = r9.mArrayValues
            r4 = r4[r0]
            r5 = 981668463(0x3a83126f, float:0.001)
            r6 = 0
            int r7 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r7 >= 0) goto L_0x0025
            r5 = -1165815185(0xffffffffba83126f, float:-0.001)
            int r5 = (r4 > r5 ? 1 : (r4 == r5 ? 0 : -1))
            if (r5 <= 0) goto L_0x002e
            float[] r4 = r9.mArrayValues
            r4[r0] = r6
        L_0x0023:
            r4 = r6
            goto L_0x002e
        L_0x0025:
            int r5 = (r4 > r5 ? 1 : (r4 == r5 ? 0 : -1))
            if (r5 >= 0) goto L_0x002e
            float[] r4 = r9.mArrayValues
            r4[r0] = r6
            goto L_0x0023
        L_0x002e:
            int r5 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r5 == 0) goto L_0x0058
            android.support.constraint.solver.Cache r5 = r9.mCache
            android.support.constraint.solver.SolverVariable[] r5 = r5.mIndexedVariables
            int[] r7 = r9.mArrayIndices
            r7 = r7[r0]
            r5 = r5[r7]
            android.support.constraint.solver.SolverVariable$Type r7 = r5.mType
            android.support.constraint.solver.SolverVariable$Type r8 = android.support.constraint.solver.SolverVariable.Type.UNRESTRICTED
            if (r7 != r8) goto L_0x004b
            int r4 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r4 >= 0) goto L_0x0047
            return r5
        L_0x0047:
            if (r1 != 0) goto L_0x0058
            r1 = r5
            goto L_0x0058
        L_0x004b:
            int r4 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r4 >= 0) goto L_0x0058
            if (r3 == 0) goto L_0x0057
            int r4 = r5.strength
            int r6 = r3.strength
            if (r4 >= r6) goto L_0x0058
        L_0x0057:
            r3 = r5
        L_0x0058:
            int[] r4 = r9.mArrayNextIndices
            r0 = r4[r0]
            int r2 = r2 + 1
            goto L_0x0005
        L_0x005f:
            if (r1 == 0) goto L_0x0062
            return r1
        L_0x0062:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.constraint.solver.ArrayLinkedVariables.pickPivotCandidate():android.support.constraint.solver.SolverVariable");
    }

    /* access modifiers changed from: package-private */
    public void updateFromRow(ArrayRow arrayRow, ArrayRow arrayRow2) {
        int i = this.mHead;
        while (true) {
            int i2 = 0;
            while (i != -1 && i2 < this.currentSize) {
                if (this.mArrayIndices[i] == arrayRow2.variable.id) {
                    float f = this.mArrayValues[i];
                    remove(arrayRow2.variable);
                    ArrayLinkedVariables arrayLinkedVariables = arrayRow2.variables;
                    int i3 = arrayLinkedVariables.mHead;
                    int i4 = 0;
                    while (i3 != -1 && i4 < arrayLinkedVariables.currentSize) {
                        add(this.mCache.mIndexedVariables[arrayLinkedVariables.mArrayIndices[i3]], arrayLinkedVariables.mArrayValues[i3] * f);
                        i3 = arrayLinkedVariables.mArrayNextIndices[i3];
                        i4++;
                    }
                    arrayRow.constantValue += arrayRow2.constantValue * f;
                    arrayRow2.variable.removeClientEquation(arrayRow);
                    i = this.mHead;
                } else {
                    i = this.mArrayNextIndices[i];
                    i2++;
                }
            }
            return;
        }
    }

    /* access modifiers changed from: package-private */
    public void updateFromSystem(ArrayRow arrayRow, ArrayRow[] arrayRowArr) {
        int i = this.mHead;
        while (true) {
            int i2 = 0;
            while (i != -1 && i2 < this.currentSize) {
                SolverVariable solverVariable = this.mCache.mIndexedVariables[this.mArrayIndices[i]];
                if (solverVariable.definitionId != -1) {
                    float f = this.mArrayValues[i];
                    remove(solverVariable);
                    ArrayRow arrayRow2 = arrayRowArr[solverVariable.definitionId];
                    if (!arrayRow2.isSimpleDefinition) {
                        ArrayLinkedVariables arrayLinkedVariables = arrayRow2.variables;
                        int i3 = arrayLinkedVariables.mHead;
                        int i4 = 0;
                        while (i3 != -1 && i4 < arrayLinkedVariables.currentSize) {
                            add(this.mCache.mIndexedVariables[arrayLinkedVariables.mArrayIndices[i3]], arrayLinkedVariables.mArrayValues[i3] * f);
                            i3 = arrayLinkedVariables.mArrayNextIndices[i3];
                            i4++;
                        }
                    }
                    arrayRow.constantValue += arrayRow2.constantValue * f;
                    arrayRow2.variable.removeClientEquation(arrayRow);
                    i = this.mHead;
                } else {
                    i = this.mArrayNextIndices[i];
                    i2++;
                }
            }
            return;
        }
    }

    /* access modifiers changed from: package-private */
    public SolverVariable getPivotCandidate() {
        if (this.candidate != null) {
            return this.candidate;
        }
        int i = this.mHead;
        int i2 = 0;
        SolverVariable solverVariable = null;
        while (i != -1 && i2 < this.currentSize) {
            if (this.mArrayValues[i] < 0.0f) {
                SolverVariable solverVariable2 = this.mCache.mIndexedVariables[this.mArrayIndices[i]];
                if (solverVariable == null || solverVariable.strength < solverVariable2.strength) {
                    solverVariable = solverVariable2;
                }
            }
            i = this.mArrayNextIndices[i];
            i2++;
        }
        return solverVariable;
    }

    /* access modifiers changed from: package-private */
    public final SolverVariable getVariable(int i) {
        int i2 = this.mHead;
        int i3 = 0;
        while (i2 != -1 && i3 < this.currentSize) {
            if (i3 == i) {
                return this.mCache.mIndexedVariables[this.mArrayIndices[i2]];
            }
            i2 = this.mArrayNextIndices[i2];
            i3++;
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public final float getVariableValue(int i) {
        int i2 = this.mHead;
        int i3 = 0;
        while (i2 != -1 && i3 < this.currentSize) {
            if (i3 == i) {
                return this.mArrayValues[i2];
            }
            i2 = this.mArrayNextIndices[i2];
            i3++;
        }
        return 0.0f;
    }

    public final float get(SolverVariable solverVariable) {
        int i = this.mHead;
        int i2 = 0;
        while (i != -1 && i2 < this.currentSize) {
            if (this.mArrayIndices[i] == solverVariable.id) {
                return this.mArrayValues[i];
            }
            i = this.mArrayNextIndices[i];
            i2++;
        }
        return 0.0f;
    }

    /* access modifiers changed from: package-private */
    public int sizeInBytes() {
        return 0 + (3 * this.mArrayIndices.length * 4) + 36;
    }

    public void display() {
        int i = this.currentSize;
        System.out.print("{ ");
        for (int i2 = 0; i2 < i; i2++) {
            SolverVariable variable = getVariable(i2);
            if (variable != null) {
                PrintStream printStream = System.out;
                printStream.print(variable + " = " + getVariableValue(i2) + " ");
            }
        }
        System.out.println(" }");
    }

    public String toString() {
        String str = "";
        int i = this.mHead;
        int i2 = 0;
        while (i != -1 && i2 < this.currentSize) {
            str = ((str + " -> ") + this.mArrayValues[i] + " : ") + this.mCache.mIndexedVariables[this.mArrayIndices[i]];
            i = this.mArrayNextIndices[i];
            i2++;
        }
        return str;
    }
}
