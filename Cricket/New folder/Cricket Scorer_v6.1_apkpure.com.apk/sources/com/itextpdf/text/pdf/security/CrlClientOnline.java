package com.itextpdf.text.pdf.security;

import com.itextpdf.text.error_messages.MessageLocalization;
import com.itextpdf.text.log.Logger;
import com.itextpdf.text.log.LoggerFactory;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.cert.Certificate;
import java.security.cert.CertificateParsingException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CrlClientOnline implements CrlClient {
    private static final Logger LOGGER = LoggerFactory.getLogger((Class<?>) CrlClientOnline.class);
    protected List<URL> urls = new ArrayList();

    public CrlClientOnline() {
    }

    public CrlClientOnline(String... strArr) {
        for (String addUrl : strArr) {
            addUrl(addUrl);
        }
    }

    public CrlClientOnline(URL... urlArr) {
        for (URL addUrl : this.urls) {
            addUrl(addUrl);
        }
    }

    public CrlClientOnline(Certificate[] certificateArr) {
        for (X509Certificate x509Certificate : certificateArr) {
            LOGGER.info("Checking certificate: " + x509Certificate.getSubjectDN());
            try {
                addUrl(CertificateUtil.getCRLURL(x509Certificate));
            } catch (CertificateParsingException unused) {
                LOGGER.info("Skipped CRL url (certificate could not be parsed)");
            }
        }
    }

    /* access modifiers changed from: protected */
    public void addUrl(String str) {
        try {
            addUrl(new URL(str));
        } catch (MalformedURLException unused) {
            Logger logger = LOGGER;
            logger.info("Skipped CRL url (malformed): " + str);
        }
    }

    /* access modifiers changed from: protected */
    public void addUrl(URL url) {
        if (this.urls.contains(url)) {
            Logger logger = LOGGER;
            logger.info("Skipped CRL url (duplicate): " + url);
            return;
        }
        this.urls.add(url);
        Logger logger2 = LOGGER;
        logger2.info("Added CRL url: " + url);
    }

    public Collection<byte[]> getEncoded(X509Certificate x509Certificate, String str) {
        if (x509Certificate == null) {
            return null;
        }
        ArrayList<URL> arrayList = new ArrayList<>(this.urls);
        if (arrayList.size() == 0) {
            Logger logger = LOGGER;
            logger.info("Looking for CRL for certificate " + x509Certificate.getSubjectDN());
            if (str == null) {
                try {
                    str = CertificateUtil.getCRLURL(x509Certificate);
                } catch (Exception e) {
                    Logger logger2 = LOGGER;
                    logger2.info("Skipped CRL url: " + e.getMessage());
                }
            }
            if (str == null) {
                throw new NullPointerException();
            }
            arrayList.add(new URL(str));
            Logger logger3 = LOGGER;
            logger3.info("Found CRL url: " + str);
        }
        ArrayList arrayList2 = new ArrayList();
        for (URL url : arrayList) {
            try {
                Logger logger4 = LOGGER;
                logger4.info("Checking CRL: " + url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                if (httpURLConnection.getResponseCode() / 100 != 2) {
                    throw new IOException(MessageLocalization.getComposedMessage("invalid.http.response.1", httpURLConnection.getResponseCode()));
                }
                InputStream inputStream = (InputStream) httpURLConnection.getContent();
                byte[] bArr = new byte[1024];
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                while (true) {
                    int read = inputStream.read(bArr, 0, bArr.length);
                    if (read <= 0) {
                        break;
                    }
                    byteArrayOutputStream.write(bArr, 0, read);
                }
                inputStream.close();
                arrayList2.add(byteArrayOutputStream.toByteArray());
                Logger logger5 = LOGGER;
                logger5.info("Added CRL found at: " + url);
            } catch (Exception e2) {
                Logger logger6 = LOGGER;
                logger6.info("Skipped CRL: " + e2.getMessage() + " for " + url);
            }
        }
        return arrayList2;
    }
}
