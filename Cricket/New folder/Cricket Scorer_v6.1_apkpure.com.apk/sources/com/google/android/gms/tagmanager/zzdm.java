package com.google.android.gms.tagmanager;

import android.content.Context;
import com.google.android.gms.analytics.Tracker;
import com.google.android.gms.analytics.ecommerce.Product;
import com.google.android.gms.analytics.ecommerce.ProductAction;
import com.google.android.gms.analytics.ecommerce.Promotion;
import com.google.android.gms.internal.zzah;
import com.google.android.gms.internal.zzai;
import com.google.android.gms.internal.zzak;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.itextpdf.text.pdf.PdfBoolean;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class zzdm extends zzdj {
    private static final String ID = zzah.UNIVERSAL_ANALYTICS.toString();
    private static final String zzbIL = zzai.ACCOUNT.toString();
    private static final String zzbIM = zzai.ANALYTICS_PASS_THROUGH.toString();
    private static final String zzbIN = zzai.ENABLE_ECOMMERCE.toString();
    private static final String zzbIO = zzai.ECOMMERCE_USE_DATA_LAYER.toString();
    private static final String zzbIP = zzai.ECOMMERCE_MACRO_DATA.toString();
    private static final String zzbIQ = zzai.ANALYTICS_FIELDS.toString();
    private static final String zzbIR = zzai.TRACK_TRANSACTION.toString();
    private static final String zzbIS = zzai.TRANSACTION_DATALAYER_MAP.toString();
    private static final String zzbIT = zzai.TRANSACTION_ITEM_DATALAYER_MAP.toString();
    private static final List<String> zzbIU = Arrays.asList(new String[]{ProductAction.ACTION_DETAIL, ProductAction.ACTION_CHECKOUT, ProductAction.ACTION_CHECKOUT_OPTION, "click", ProductAction.ACTION_ADD, ProductAction.ACTION_REMOVE, ProductAction.ACTION_PURCHASE, ProductAction.ACTION_REFUND});
    private static final Pattern zzbIV = Pattern.compile("dimension(\\d+)");
    private static final Pattern zzbIW = Pattern.compile("metric(\\d+)");
    private static Map<String, String> zzbIX;
    private static Map<String, String> zzbIY;
    private final DataLayer zzbEV;
    private final Set<String> zzbIZ;
    private final zzdi zzbJa;

    public zzdm(Context context, DataLayer dataLayer) {
        this(context, dataLayer, new zzdi(context));
    }

    zzdm(Context context, DataLayer dataLayer, zzdi zzdi) {
        super(ID, new String[0]);
        this.zzbEV = dataLayer;
        this.zzbJa = zzdi;
        this.zzbIZ = new HashSet();
        this.zzbIZ.add("");
        this.zzbIZ.add("0");
        this.zzbIZ.add(PdfBoolean.FALSE);
    }

    private Double zzV(Object obj) {
        if (obj instanceof String) {
            try {
                return Double.valueOf((String) obj);
            } catch (NumberFormatException e) {
                String valueOf = String.valueOf(e.getMessage());
                throw new RuntimeException(valueOf.length() != 0 ? "Cannot convert the object to Double: ".concat(valueOf) : new String("Cannot convert the object to Double: "));
            }
        } else if (obj instanceof Integer) {
            return Double.valueOf(((Integer) obj).doubleValue());
        } else {
            if (obj instanceof Double) {
                return (Double) obj;
            }
            String valueOf2 = String.valueOf(obj.toString());
            throw new RuntimeException(valueOf2.length() != 0 ? "Cannot convert the object to Double: ".concat(valueOf2) : new String("Cannot convert the object to Double: "));
        }
    }

    private Integer zzW(Object obj) {
        if (obj instanceof String) {
            try {
                return Integer.valueOf((String) obj);
            } catch (NumberFormatException e) {
                String valueOf = String.valueOf(e.getMessage());
                throw new RuntimeException(valueOf.length() != 0 ? "Cannot convert the object to Integer: ".concat(valueOf) : new String("Cannot convert the object to Integer: "));
            }
        } else if (obj instanceof Double) {
            return Integer.valueOf(((Double) obj).intValue());
        } else {
            if (obj instanceof Integer) {
                return (Integer) obj;
            }
            String valueOf2 = String.valueOf(obj.toString());
            throw new RuntimeException(valueOf2.length() != 0 ? "Cannot convert the object to Integer: ".concat(valueOf2) : new String("Cannot convert the object to Integer: "));
        }
    }

    private void zza(Tracker tracker, Map<String, zzak.zza> map) {
        String zzhB = zzhB("transactionId");
        if (zzhB == null) {
            zzbo.e("Cannot find transactionId in data layer.");
            return;
        }
        LinkedList<Map> linkedList = new LinkedList<>();
        try {
            Map<String, String> zzk = zzk(map.get(zzbIQ));
            zzk.put("&t", "transaction");
            for (Map.Entry next : zzal(map).entrySet()) {
                zze(zzk, (String) next.getValue(), zzhB((String) next.getKey()));
            }
            linkedList.add(zzk);
            List<Map<String, String>> zzhC = zzhC("transactionProducts");
            if (zzhC != null) {
                for (Map next2 : zzhC) {
                    if (next2.get("name") == null) {
                        zzbo.e("Unable to send transaction item hit due to missing 'name' field.");
                        return;
                    }
                    Map<String, String> zzk2 = zzk(map.get(zzbIQ));
                    zzk2.put("&t", "item");
                    zzk2.put("&ti", zzhB);
                    for (Map.Entry next3 : zzam(map).entrySet()) {
                        zze(zzk2, (String) next3.getValue(), (String) next2.get(next3.getKey()));
                    }
                    linkedList.add(zzk2);
                }
            }
            for (Map send : linkedList) {
                tracker.send(send);
            }
        } catch (IllegalArgumentException e) {
            zzbo.zzb("Unable to send transaction", e);
        }
    }

    private Promotion zzaj(Map<String, String> map) {
        Promotion promotion = new Promotion();
        String str = map.get(TtmlNode.ATTR_ID);
        if (str != null) {
            promotion.setId(String.valueOf(str));
        }
        String str2 = map.get("name");
        if (str2 != null) {
            promotion.setName(String.valueOf(str2));
        }
        String str3 = map.get("creative");
        if (str3 != null) {
            promotion.setCreative(String.valueOf(str3));
        }
        String str4 = map.get("position");
        if (str4 != null) {
            promotion.setPosition(String.valueOf(str4));
        }
        return promotion;
    }

    private Product zzak(Map<String, Object> map) {
        String str;
        String valueOf;
        String str2;
        Product product = new Product();
        Object obj = map.get(TtmlNode.ATTR_ID);
        if (obj != null) {
            product.setId(String.valueOf(obj));
        }
        Object obj2 = map.get("name");
        if (obj2 != null) {
            product.setName(String.valueOf(obj2));
        }
        Object obj3 = map.get("brand");
        if (obj3 != null) {
            product.setBrand(String.valueOf(obj3));
        }
        Object obj4 = map.get("category");
        if (obj4 != null) {
            product.setCategory(String.valueOf(obj4));
        }
        Object obj5 = map.get("variant");
        if (obj5 != null) {
            product.setVariant(String.valueOf(obj5));
        }
        Object obj6 = map.get(FirebaseAnalytics.Param.COUPON);
        if (obj6 != null) {
            product.setCouponCode(String.valueOf(obj6));
        }
        Object obj7 = map.get("position");
        if (obj7 != null) {
            product.setPosition(zzW(obj7).intValue());
        }
        Object obj8 = map.get(FirebaseAnalytics.Param.PRICE);
        if (obj8 != null) {
            product.setPrice(zzV(obj8).doubleValue());
        }
        Object obj9 = map.get(FirebaseAnalytics.Param.QUANTITY);
        if (obj9 != null) {
            product.setQuantity(zzW(obj9).intValue());
        }
        for (String next : map.keySet()) {
            Matcher matcher = zzbIV.matcher(next);
            if (matcher.matches()) {
                try {
                    product.setCustomDimension(Integer.parseInt(matcher.group(1)), String.valueOf(map.get(next)));
                } catch (NumberFormatException unused) {
                    str = "illegal number in custom dimension value: ";
                    valueOf = String.valueOf(next);
                    if (valueOf.length() == 0) {
                        str2 = new String(str);
                        zzbo.zzbh(str2);
                    }
                    str2 = str.concat(valueOf);
                    zzbo.zzbh(str2);
                }
            } else {
                Matcher matcher2 = zzbIW.matcher(next);
                if (matcher2.matches()) {
                    try {
                        product.setCustomMetric(Integer.parseInt(matcher2.group(1)), zzW(map.get(next)).intValue());
                    } catch (NumberFormatException unused2) {
                        str = "illegal number in custom metric value: ";
                        valueOf = String.valueOf(next);
                        if (valueOf.length() == 0) {
                            str2 = new String(str);
                            zzbo.zzbh(str2);
                        }
                        str2 = str.concat(valueOf);
                        zzbo.zzbh(str2);
                    }
                }
            }
        }
        return product;
    }

    private Map<String, String> zzal(Map<String, zzak.zza> map) {
        zzak.zza zza = map.get(zzbIS);
        if (zza != null) {
            return zzc(zza);
        }
        if (zzbIX == null) {
            HashMap hashMap = new HashMap();
            hashMap.put("transactionId", "&ti");
            hashMap.put("transactionAffiliation", "&ta");
            hashMap.put("transactionTax", "&tt");
            hashMap.put("transactionShipping", "&ts");
            hashMap.put("transactionTotal", "&tr");
            hashMap.put("transactionCurrency", "&cu");
            zzbIX = hashMap;
        }
        return zzbIX;
    }

    private Map<String, String> zzam(Map<String, zzak.zza> map) {
        zzak.zza zza = map.get(zzbIT);
        if (zza != null) {
            return zzc(zza);
        }
        if (zzbIY == null) {
            HashMap hashMap = new HashMap();
            hashMap.put("name", "&in");
            hashMap.put("sku", "&ic");
            hashMap.put("category", "&iv");
            hashMap.put(FirebaseAnalytics.Param.PRICE, "&ip");
            hashMap.put(FirebaseAnalytics.Param.QUANTITY, "&iq");
            hashMap.put(FirebaseAnalytics.Param.CURRENCY, "&cu");
            zzbIY = hashMap;
        }
        return zzbIY;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v31, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v24, resolved type: java.util.List} */
    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0027, code lost:
        if ((r8 instanceof java.util.Map) != false) goto L_0x003a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:5:0x0038, code lost:
        if ((r8 instanceof java.util.Map) != false) goto L_0x003a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x003d, code lost:
        r8 = null;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x00cc  */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x011d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void zzb(com.google.android.gms.analytics.Tracker r7, java.util.Map<java.lang.String, com.google.android.gms.internal.zzak.zza> r8) {
        /*
            r6 = this;
            com.google.android.gms.analytics.HitBuilders$ScreenViewBuilder r0 = new com.google.android.gms.analytics.HitBuilders$ScreenViewBuilder
            r0.<init>()
            java.lang.String r1 = zzbIQ
            java.lang.Object r1 = r8.get(r1)
            com.google.android.gms.internal.zzak$zza r1 = (com.google.android.gms.internal.zzak.zza) r1
            java.util.Map r1 = r6.zzk(r1)
            r0.setAll(r1)
            java.lang.String r2 = zzbIO
            boolean r2 = r6.zzi(r8, r2)
            r3 = 0
            if (r2 == 0) goto L_0x002a
            com.google.android.gms.tagmanager.DataLayer r8 = r6.zzbEV
            java.lang.String r2 = "ecommerce"
            java.lang.Object r8 = r8.get(r2)
            boolean r2 = r8 instanceof java.util.Map
            if (r2 == 0) goto L_0x003d
            goto L_0x003a
        L_0x002a:
            java.lang.String r2 = zzbIP
            java.lang.Object r8 = r8.get(r2)
            com.google.android.gms.internal.zzak$zza r8 = (com.google.android.gms.internal.zzak.zza) r8
            java.lang.Object r8 = com.google.android.gms.tagmanager.zzdl.zzj(r8)
            boolean r2 = r8 instanceof java.util.Map
            if (r2 == 0) goto L_0x003d
        L_0x003a:
            java.util.Map r8 = (java.util.Map) r8
            goto L_0x003e
        L_0x003d:
            r8 = r3
        L_0x003e:
            if (r8 == 0) goto L_0x01b8
            java.lang.String r2 = "&cu"
            java.lang.Object r1 = r1.get(r2)
            java.lang.String r1 = (java.lang.String) r1
            if (r1 != 0) goto L_0x0052
            java.lang.String r1 = "currencyCode"
            java.lang.Object r1 = r8.get(r1)
            java.lang.String r1 = (java.lang.String) r1
        L_0x0052:
            if (r1 == 0) goto L_0x0059
            java.lang.String r2 = "&cu"
            r0.set(r2, r1)
        L_0x0059:
            java.lang.String r1 = "impressions"
            java.lang.Object r1 = r8.get(r1)
            boolean r2 = r1 instanceof java.util.List
            if (r2 == 0) goto L_0x00a4
            java.util.List r1 = (java.util.List) r1
            java.util.Iterator r1 = r1.iterator()
        L_0x0069:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L_0x00a4
            java.lang.Object r2 = r1.next()
            java.util.Map r2 = (java.util.Map) r2
            com.google.android.gms.analytics.ecommerce.Product r4 = r6.zzak(r2)     // Catch:{ RuntimeException -> 0x0085 }
            java.lang.String r5 = "list"
            java.lang.Object r2 = r2.get(r5)     // Catch:{ RuntimeException -> 0x0085 }
            java.lang.String r2 = (java.lang.String) r2     // Catch:{ RuntimeException -> 0x0085 }
            r0.addImpression(r4, r2)     // Catch:{ RuntimeException -> 0x0085 }
            goto L_0x0069
        L_0x0085:
            r2 = move-exception
            java.lang.String r4 = "Failed to extract a product from DataLayer. "
            java.lang.String r2 = r2.getMessage()
            java.lang.String r2 = java.lang.String.valueOf(r2)
            int r5 = r2.length()
            if (r5 == 0) goto L_0x009b
            java.lang.String r2 = r4.concat(r2)
            goto L_0x00a0
        L_0x009b:
            java.lang.String r2 = new java.lang.String
            r2.<init>(r4)
        L_0x00a0:
            com.google.android.gms.tagmanager.zzbo.e(r2)
            goto L_0x0069
        L_0x00a4:
            java.lang.String r1 = "promoClick"
            boolean r1 = r8.containsKey(r1)
            if (r1 == 0) goto L_0x00be
            java.lang.String r1 = "promoClick"
        L_0x00ae:
            java.lang.Object r1 = r8.get(r1)
            java.util.Map r1 = (java.util.Map) r1
            java.lang.String r2 = "promotions"
            java.lang.Object r1 = r1.get(r2)
            r3 = r1
            java.util.List r3 = (java.util.List) r3
            goto L_0x00c9
        L_0x00be:
            java.lang.String r1 = "promoView"
            boolean r1 = r8.containsKey(r1)
            if (r1 == 0) goto L_0x00c9
            java.lang.String r1 = "promoView"
            goto L_0x00ae
        L_0x00c9:
            r1 = 1
            if (r3 == 0) goto L_0x011b
            java.util.Iterator r2 = r3.iterator()
        L_0x00d0:
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto L_0x0103
            java.lang.Object r3 = r2.next()
            java.util.Map r3 = (java.util.Map) r3
            com.google.android.gms.analytics.ecommerce.Promotion r3 = r6.zzaj(r3)     // Catch:{ RuntimeException -> 0x00e4 }
            r0.addPromotion(r3)     // Catch:{ RuntimeException -> 0x00e4 }
            goto L_0x00d0
        L_0x00e4:
            r3 = move-exception
            java.lang.String r4 = "Failed to extract a promotion from DataLayer. "
            java.lang.String r3 = r3.getMessage()
            java.lang.String r3 = java.lang.String.valueOf(r3)
            int r5 = r3.length()
            if (r5 == 0) goto L_0x00fa
            java.lang.String r3 = r4.concat(r3)
            goto L_0x00ff
        L_0x00fa:
            java.lang.String r3 = new java.lang.String
            r3.<init>(r4)
        L_0x00ff:
            com.google.android.gms.tagmanager.zzbo.e(r3)
            goto L_0x00d0
        L_0x0103:
            java.lang.String r2 = "promoClick"
            boolean r2 = r8.containsKey(r2)
            if (r2 == 0) goto L_0x0114
            java.lang.String r1 = "&promoa"
            java.lang.String r2 = "click"
            r0.set(r1, r2)
            r1 = 0
            goto L_0x011b
        L_0x0114:
            java.lang.String r2 = "&promoa"
            java.lang.String r3 = "view"
            r0.set(r2, r3)
        L_0x011b:
            if (r1 == 0) goto L_0x01b8
            java.util.List<java.lang.String> r1 = zzbIU
            java.util.Iterator r1 = r1.iterator()
        L_0x0123:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L_0x01b8
            java.lang.Object r2 = r1.next()
            java.lang.String r2 = (java.lang.String) r2
            boolean r3 = r8.containsKey(r2)
            if (r3 == 0) goto L_0x0123
            java.lang.Object r8 = r8.get(r2)
            java.util.Map r8 = (java.util.Map) r8
            java.lang.String r1 = "products"
            java.lang.Object r1 = r8.get(r1)
            java.util.List r1 = (java.util.List) r1
            if (r1 == 0) goto L_0x017c
            java.util.Iterator r1 = r1.iterator()
        L_0x0149:
            boolean r3 = r1.hasNext()
            if (r3 == 0) goto L_0x017c
            java.lang.Object r3 = r1.next()
            java.util.Map r3 = (java.util.Map) r3
            com.google.android.gms.analytics.ecommerce.Product r3 = r6.zzak(r3)     // Catch:{ RuntimeException -> 0x015d }
            r0.addProduct(r3)     // Catch:{ RuntimeException -> 0x015d }
            goto L_0x0149
        L_0x015d:
            r3 = move-exception
            java.lang.String r4 = "Failed to extract a product from DataLayer. "
            java.lang.String r3 = r3.getMessage()
            java.lang.String r3 = java.lang.String.valueOf(r3)
            int r5 = r3.length()
            if (r5 == 0) goto L_0x0173
            java.lang.String r3 = r4.concat(r3)
            goto L_0x0178
        L_0x0173:
            java.lang.String r3 = new java.lang.String
            r3.<init>(r4)
        L_0x0178:
            com.google.android.gms.tagmanager.zzbo.e(r3)
            goto L_0x0149
        L_0x017c:
            java.lang.String r1 = "actionField"
            boolean r1 = r8.containsKey(r1)     // Catch:{ RuntimeException -> 0x019a }
            if (r1 == 0) goto L_0x0191
            java.lang.String r1 = "actionField"
            java.lang.Object r8 = r8.get(r1)     // Catch:{ RuntimeException -> 0x019a }
            java.util.Map r8 = (java.util.Map) r8     // Catch:{ RuntimeException -> 0x019a }
            com.google.android.gms.analytics.ecommerce.ProductAction r8 = r6.zzh(r2, r8)     // Catch:{ RuntimeException -> 0x019a }
            goto L_0x0196
        L_0x0191:
            com.google.android.gms.analytics.ecommerce.ProductAction r8 = new com.google.android.gms.analytics.ecommerce.ProductAction     // Catch:{ RuntimeException -> 0x019a }
            r8.<init>(r2)     // Catch:{ RuntimeException -> 0x019a }
        L_0x0196:
            r0.setProductAction(r8)     // Catch:{ RuntimeException -> 0x019a }
            goto L_0x01b8
        L_0x019a:
            r8 = move-exception
            java.lang.String r1 = "Failed to extract a product action from DataLayer. "
            java.lang.String r8 = r8.getMessage()
            java.lang.String r8 = java.lang.String.valueOf(r8)
            int r2 = r8.length()
            if (r2 == 0) goto L_0x01b0
            java.lang.String r8 = r1.concat(r8)
            goto L_0x01b5
        L_0x01b0:
            java.lang.String r8 = new java.lang.String
            r8.<init>(r1)
        L_0x01b5:
            com.google.android.gms.tagmanager.zzbo.e(r8)
        L_0x01b8:
            java.util.Map r8 = r0.build()
            r7.send(r8)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.tagmanager.zzdm.zzb(com.google.android.gms.analytics.Tracker, java.util.Map):void");
    }

    private Map<String, String> zzc(zzak.zza zza) {
        Object zzj = zzdl.zzj(zza);
        if (!(zzj instanceof Map)) {
            return null;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Map.Entry entry : ((Map) zzj).entrySet()) {
            linkedHashMap.put(entry.getKey().toString(), entry.getValue().toString());
        }
        return linkedHashMap;
    }

    private void zze(Map<String, String> map, String str, String str2) {
        if (str2 != null) {
            map.put(str, str2);
        }
    }

    private ProductAction zzh(String str, Map<String, Object> map) {
        ProductAction productAction = new ProductAction(str);
        Object obj = map.get(TtmlNode.ATTR_ID);
        if (obj != null) {
            productAction.setTransactionId(String.valueOf(obj));
        }
        Object obj2 = map.get("affiliation");
        if (obj2 != null) {
            productAction.setTransactionAffiliation(String.valueOf(obj2));
        }
        Object obj3 = map.get(FirebaseAnalytics.Param.COUPON);
        if (obj3 != null) {
            productAction.setTransactionCouponCode(String.valueOf(obj3));
        }
        Object obj4 = map.get("list");
        if (obj4 != null) {
            productAction.setProductActionList(String.valueOf(obj4));
        }
        Object obj5 = map.get("option");
        if (obj5 != null) {
            productAction.setCheckoutOptions(String.valueOf(obj5));
        }
        Object obj6 = map.get("revenue");
        if (obj6 != null) {
            productAction.setTransactionRevenue(zzV(obj6).doubleValue());
        }
        Object obj7 = map.get(FirebaseAnalytics.Param.TAX);
        if (obj7 != null) {
            productAction.setTransactionTax(zzV(obj7).doubleValue());
        }
        Object obj8 = map.get(FirebaseAnalytics.Param.SHIPPING);
        if (obj8 != null) {
            productAction.setTransactionShipping(zzV(obj8).doubleValue());
        }
        Object obj9 = map.get("step");
        if (obj9 != null) {
            productAction.setCheckoutStep(zzW(obj9).intValue());
        }
        return productAction;
    }

    private String zzhB(String str) {
        Object obj = this.zzbEV.get(str);
        if (obj == null) {
            return null;
        }
        return obj.toString();
    }

    private List<Map<String, String>> zzhC(String str) {
        Object obj = this.zzbEV.get(str);
        if (obj == null) {
            return null;
        }
        if (!(obj instanceof List)) {
            throw new IllegalArgumentException("transactionProducts should be of type List.");
        }
        List<Map<String, String>> list = (List) obj;
        for (Map<String, String> map : list) {
            if (!(map instanceof Map)) {
                throw new IllegalArgumentException("Each element of transactionProducts should be of type Map.");
            }
        }
        return list;
    }

    private boolean zzi(Map<String, zzak.zza> map, String str) {
        zzak.zza zza = map.get(str);
        if (zza == null) {
            return false;
        }
        return zzdl.zzi(zza).booleanValue();
    }

    private Map<String, String> zzk(zzak.zza zza) {
        if (zza == null) {
            return new HashMap();
        }
        Map<String, String> zzc = zzc(zza);
        if (zzc == null) {
            return new HashMap();
        }
        String str = zzc.get("&aip");
        if (str != null && this.zzbIZ.contains(str.toLowerCase())) {
            zzc.remove("&aip");
        }
        return zzc;
    }

    public /* bridge */ /* synthetic */ String zzQL() {
        return super.zzQL();
    }

    public /* bridge */ /* synthetic */ Set zzQM() {
        return super.zzQM();
    }

    public /* bridge */ /* synthetic */ boolean zzQb() {
        return super.zzQb();
    }

    public /* bridge */ /* synthetic */ zzak.zza zzZ(Map map) {
        return super.zzZ(map);
    }

    public void zzab(Map<String, zzak.zza> map) {
        Tracker zzht = this.zzbJa.zzht("_GTM_DEFAULT_TRACKER_");
        zzht.enableAdvertisingIdCollection(zzi(map, "collect_adid"));
        if (zzi(map, zzbIN)) {
            zzb(zzht, map);
        } else if (zzi(map, zzbIM)) {
            zzht.send(zzk(map.get(zzbIQ)));
        } else if (zzi(map, zzbIR)) {
            zza(zzht, map);
        } else {
            zzbo.zzbh("Ignoring unknown tag.");
        }
    }
}
