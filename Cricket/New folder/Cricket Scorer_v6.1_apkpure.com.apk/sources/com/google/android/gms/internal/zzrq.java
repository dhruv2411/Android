package com.google.android.gms.internal;

import com.google.android.gms.analytics.ecommerce.Product;
import com.google.android.gms.analytics.ecommerce.ProductAction;
import com.google.android.gms.analytics.ecommerce.Promotion;
import com.google.android.gms.analytics.zzf;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class zzrq extends zzf<zzrq> {
    private ProductAction zzabO;
    private final Map<String, List<Product>> zzabP = new HashMap();
    private final List<Promotion> zzabQ = new ArrayList();
    private final List<Product> zzabR = new ArrayList();

    public String toString() {
        HashMap hashMap = new HashMap();
        if (!this.zzabR.isEmpty()) {
            hashMap.put("products", this.zzabR);
        }
        if (!this.zzabQ.isEmpty()) {
            hashMap.put("promotions", this.zzabQ);
        }
        if (!this.zzabP.isEmpty()) {
            hashMap.put("impressions", this.zzabP);
        }
        hashMap.put("productAction", this.zzabO);
        return zzj(hashMap);
    }

    public void zza(Product product, String str) {
        if (product != null) {
            if (str == null) {
                str = "";
            }
            if (!this.zzabP.containsKey(str)) {
                this.zzabP.put(str, new ArrayList());
            }
            this.zzabP.get(str).add(product);
        }
    }

    /* renamed from: zza */
    public void zzb(zzrq zzrq) {
        zzrq.zzabR.addAll(this.zzabR);
        zzrq.zzabQ.addAll(this.zzabQ);
        for (Map.Entry next : this.zzabP.entrySet()) {
            String str = (String) next.getKey();
            for (Product zza : (List) next.getValue()) {
                zzrq.zza(zza, str);
            }
        }
        if (this.zzabO != null) {
            zzrq.zzabO = this.zzabO;
        }
    }

    public ProductAction zznp() {
        return this.zzabO;
    }

    public List<Product> zznq() {
        return Collections.unmodifiableList(this.zzabR);
    }

    public Map<String, List<Product>> zznr() {
        return this.zzabP;
    }

    public List<Promotion> zzns() {
        return Collections.unmodifiableList(this.zzabQ);
    }
}
