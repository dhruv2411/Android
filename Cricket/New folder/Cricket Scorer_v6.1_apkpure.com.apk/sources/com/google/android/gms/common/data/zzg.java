package com.google.android.gms.common.data;

import java.util.NoSuchElementException;

public class zzg<T> extends zzb<T> {
    private T zzaEe;

    public zzg(DataBuffer<T> dataBuffer) {
        super(dataBuffer);
    }

    public T next() {
        if (!hasNext()) {
            int i = this.zzaDJ;
            StringBuilder sb = new StringBuilder(46);
            sb.append("Cannot advance the iterator beyond ");
            sb.append(i);
            throw new NoSuchElementException(sb.toString());
        }
        this.zzaDJ++;
        if (this.zzaDJ == 0) {
            this.zzaEe = this.zzaDI.get(0);
            if (!(this.zzaEe instanceof zzc)) {
                String valueOf = String.valueOf(this.zzaEe.getClass());
                StringBuilder sb2 = new StringBuilder(44 + String.valueOf(valueOf).length());
                sb2.append("DataBuffer reference of type ");
                sb2.append(valueOf);
                sb2.append(" is not movable");
                throw new IllegalStateException(sb2.toString());
            }
        } else {
            ((zzc) this.zzaEe).zzcG(this.zzaDJ);
        }
        return this.zzaEe;
    }
}
