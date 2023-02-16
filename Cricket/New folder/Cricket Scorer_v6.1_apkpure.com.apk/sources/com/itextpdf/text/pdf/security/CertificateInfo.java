package com.itextpdf.text.pdf.security;

import com.itextpdf.text.ExceptionConverter;
import com.itextpdf.text.error_messages.MessageLocalization;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.spongycastle.asn1.ASN1InputStream;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.ASN1Set;
import org.spongycastle.asn1.ASN1TaggedObject;

public class CertificateInfo {

    public static class X500Name {
        public static final ASN1ObjectIdentifier C = new ASN1ObjectIdentifier("2.5.4.6");
        public static final ASN1ObjectIdentifier CN = new ASN1ObjectIdentifier("2.5.4.3");
        public static final ASN1ObjectIdentifier DC = new ASN1ObjectIdentifier("0.9.2342.19200300.100.1.25");
        public static final Map<ASN1ObjectIdentifier, String> DefaultSymbols = new HashMap();
        public static final ASN1ObjectIdentifier E = EmailAddress;
        public static final ASN1ObjectIdentifier EmailAddress = new ASN1ObjectIdentifier("1.2.840.113549.1.9.1");
        public static final ASN1ObjectIdentifier GENERATION = new ASN1ObjectIdentifier("2.5.4.44");
        public static final ASN1ObjectIdentifier GIVENNAME = new ASN1ObjectIdentifier("2.5.4.42");
        public static final ASN1ObjectIdentifier INITIALS = new ASN1ObjectIdentifier("2.5.4.43");
        public static final ASN1ObjectIdentifier L = new ASN1ObjectIdentifier("2.5.4.7");
        public static final ASN1ObjectIdentifier O = new ASN1ObjectIdentifier("2.5.4.10");
        public static final ASN1ObjectIdentifier OU = new ASN1ObjectIdentifier("2.5.4.11");
        public static final ASN1ObjectIdentifier SN = new ASN1ObjectIdentifier("2.5.4.5");
        public static final ASN1ObjectIdentifier ST = new ASN1ObjectIdentifier("2.5.4.8");
        public static final ASN1ObjectIdentifier SURNAME = new ASN1ObjectIdentifier("2.5.4.4");
        public static final ASN1ObjectIdentifier T = new ASN1ObjectIdentifier("2.5.4.12");
        public static final ASN1ObjectIdentifier UID = new ASN1ObjectIdentifier("0.9.2342.19200300.100.1.1");
        public static final ASN1ObjectIdentifier UNIQUE_IDENTIFIER = new ASN1ObjectIdentifier("2.5.4.45");
        public Map<String, ArrayList<String>> values = new HashMap();

        static {
            DefaultSymbols.put(C, "C");
            DefaultSymbols.put(O, "O");
            DefaultSymbols.put(T, "T");
            DefaultSymbols.put(OU, "OU");
            DefaultSymbols.put(CN, "CN");
            DefaultSymbols.put(L, "L");
            DefaultSymbols.put(ST, "ST");
            DefaultSymbols.put(SN, "SN");
            DefaultSymbols.put(EmailAddress, "E");
            DefaultSymbols.put(DC, "DC");
            DefaultSymbols.put(UID, "UID");
            DefaultSymbols.put(SURNAME, "SURNAME");
            DefaultSymbols.put(GIVENNAME, "GIVENNAME");
            DefaultSymbols.put(INITIALS, "INITIALS");
            DefaultSymbols.put(GENERATION, "GENERATION");
        }

        public X500Name(ASN1Sequence aSN1Sequence) {
            Enumeration objects = aSN1Sequence.getObjects();
            while (objects.hasMoreElements()) {
                ASN1Set aSN1Set = (ASN1Set) objects.nextElement();
                for (int i = 0; i < aSN1Set.size(); i++) {
                    ASN1Sequence objectAt = aSN1Set.getObjectAt(i);
                    String str = DefaultSymbols.get(objectAt.getObjectAt(0));
                    if (str != null) {
                        ArrayList arrayList = this.values.get(str);
                        if (arrayList == null) {
                            arrayList = new ArrayList();
                            this.values.put(str, arrayList);
                        }
                        arrayList.add(objectAt.getObjectAt(1).getString());
                    }
                }
            }
        }

        public X500Name(String str) {
            X509NameTokenizer x509NameTokenizer = new X509NameTokenizer(str);
            while (x509NameTokenizer.hasMoreTokens()) {
                String nextToken = x509NameTokenizer.nextToken();
                int indexOf = nextToken.indexOf(61);
                if (indexOf == -1) {
                    throw new IllegalArgumentException(MessageLocalization.getComposedMessage("badly.formated.directory.string", new Object[0]));
                }
                String upperCase = nextToken.substring(0, indexOf).toUpperCase();
                String substring = nextToken.substring(indexOf + 1);
                ArrayList arrayList = this.values.get(upperCase);
                if (arrayList == null) {
                    arrayList = new ArrayList();
                    this.values.put(upperCase, arrayList);
                }
                arrayList.add(substring);
            }
        }

        public String getField(String str) {
            List list = this.values.get(str);
            if (list == null) {
                return null;
            }
            return (String) list.get(0);
        }

        public List<String> getFieldArray(String str) {
            return this.values.get(str);
        }

        public Map<String, ArrayList<String>> getFields() {
            return this.values;
        }

        public String toString() {
            return this.values.toString();
        }
    }

    public static class X509NameTokenizer {
        private StringBuffer buf = new StringBuffer();
        private int index;
        private String oid;

        public X509NameTokenizer(String str) {
            this.oid = str;
            this.index = -1;
        }

        public boolean hasMoreTokens() {
            return this.index != this.oid.length();
        }

        public String nextToken() {
            if (this.index == this.oid.length()) {
                return null;
            }
            int i = this.index + 1;
            this.buf.setLength(0);
            boolean z = false;
            boolean z2 = false;
            while (i != this.oid.length()) {
                char charAt = this.oid.charAt(i);
                if (charAt == '\"') {
                    if (!z) {
                        z2 = !z2;
                    } else {
                        this.buf.append(charAt);
                    }
                } else if (z || z2) {
                    this.buf.append(charAt);
                } else {
                    if (charAt == '\\') {
                        z = true;
                    } else if (charAt == ',') {
                        break;
                    } else {
                        this.buf.append(charAt);
                    }
                    i++;
                }
                z = false;
                i++;
            }
            this.index = i;
            return this.buf.toString().trim();
        }
    }

    public static X500Name getIssuerFields(X509Certificate x509Certificate) {
        try {
            return new X500Name(getIssuer(x509Certificate.getTBSCertificate()));
        } catch (Exception e) {
            throw new ExceptionConverter(e);
        }
    }

    public static ASN1Primitive getIssuer(byte[] bArr) {
        try {
            ASN1Sequence readObject = new ASN1InputStream(new ByteArrayInputStream(bArr)).readObject();
            return readObject.getObjectAt(readObject.getObjectAt(0) instanceof ASN1TaggedObject ? 3 : 2);
        } catch (IOException e) {
            throw new ExceptionConverter(e);
        }
    }

    public static X500Name getSubjectFields(X509Certificate x509Certificate) {
        if (x509Certificate == null) {
            return null;
        }
        try {
            return new X500Name(getSubject(x509Certificate.getTBSCertificate()));
        } catch (Exception e) {
            throw new ExceptionConverter(e);
        }
    }

    public static ASN1Primitive getSubject(byte[] bArr) {
        try {
            ASN1Sequence readObject = new ASN1InputStream(new ByteArrayInputStream(bArr)).readObject();
            return readObject.getObjectAt(readObject.getObjectAt(0) instanceof ASN1TaggedObject ? 5 : 4);
        } catch (IOException e) {
            throw new ExceptionConverter(e);
        }
    }
}
