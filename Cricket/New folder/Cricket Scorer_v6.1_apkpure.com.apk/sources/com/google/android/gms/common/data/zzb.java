package com.google.android.gms.common.data;

import com.google.android.gms.common.internal.zzac;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class zzb<T> implements Iterator<T> {
    protected final DataBuffer<T> zzaDI;
    protected int zzaDJ = -1;

    public zzb(DataBuffer<T> dataBuffer) {
        this.zzaDI = (DataBuffer) zzac.zzw(dataBuffer);
    }

    public boolean hasNext() {
        return this.zzaDJ < this.zzaDI.getCount() - 1;
    }

    public T next() {
        if (!hasNext()) {
            int i = this.zzaDJ;
            StringBuilder sb = new StringBuilder(46);
            sb.append("Cannot advance the iterator beyond ");
            sb.append(i);
            throw new NoSuchElementException(sb.toString());
        }
        DataBuffer<T> dataBuffer = this.zzaDI;
        int i2 = this.zzaDJ + 1;
        this.zzaDJ = i2;
        return dataBuffer.get(i2);
    }

    public void remove() {
        throw new UnsupportedOperationException("Cannot remove elements from a DataBufferIterator");
    }
}
