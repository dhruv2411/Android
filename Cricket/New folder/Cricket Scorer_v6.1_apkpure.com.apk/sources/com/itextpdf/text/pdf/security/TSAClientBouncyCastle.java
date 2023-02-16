package com.itextpdf.text.pdf.security;

import com.itextpdf.text.error_messages.MessageLocalization;
import com.itextpdf.text.log.Logger;
import com.itextpdf.text.log.LoggerFactory;
import com.itextpdf.text.pdf.codec.Base64;
import io.fabric.sdk.android.services.network.HttpRequest;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.net.URL;
import java.net.URLConnection;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.cmp.PKIFailureInfo;
import org.spongycastle.tsp.TSPException;
import org.spongycastle.tsp.TimeStampRequest;
import org.spongycastle.tsp.TimeStampRequestGenerator;
import org.spongycastle.tsp.TimeStampResponse;
import org.spongycastle.tsp.TimeStampToken;
import org.spongycastle.tsp.TimeStampTokenInfo;

public class TSAClientBouncyCastle implements TSAClient {
    public static final String DEFAULTHASHALGORITHM = "SHA-256";
    public static final int DEFAULTTOKENSIZE = 4096;
    private static final Logger LOGGER = LoggerFactory.getLogger((Class<?>) TSAClientBouncyCastle.class);
    protected String digestAlgorithm;
    protected int tokenSizeEstimate;
    protected TSAInfoBouncyCastle tsaInfo;
    protected String tsaPassword;
    protected String tsaURL;
    protected String tsaUsername;

    public TSAClientBouncyCastle(String str) {
        this(str, (String) null, (String) null, 4096, "SHA-256");
    }

    public TSAClientBouncyCastle(String str, String str2, String str3) {
        this(str, str2, str3, 4096, "SHA-256");
    }

    public TSAClientBouncyCastle(String str, String str2, String str3, int i, String str4) {
        this.tsaURL = str;
        this.tsaUsername = str2;
        this.tsaPassword = str3;
        this.tokenSizeEstimate = i;
        this.digestAlgorithm = str4;
    }

    public void setTSAInfo(TSAInfoBouncyCastle tSAInfoBouncyCastle) {
        this.tsaInfo = tSAInfoBouncyCastle;
    }

    public int getTokenSizeEstimate() {
        return this.tokenSizeEstimate;
    }

    public MessageDigest getMessageDigest() throws GeneralSecurityException {
        return new BouncyCastleDigest().getMessageDigest(this.digestAlgorithm);
    }

    public byte[] getTimeStampToken(byte[] bArr) throws IOException, TSPException {
        int i;
        TimeStampRequestGenerator timeStampRequestGenerator = new TimeStampRequestGenerator();
        timeStampRequestGenerator.setCertReq(true);
        TimeStampRequest generate = timeStampRequestGenerator.generate(new ASN1ObjectIdentifier(DigestAlgorithms.getAllowedDigests(this.digestAlgorithm)), bArr, BigInteger.valueOf(System.currentTimeMillis()));
        TimeStampResponse timeStampResponse = new TimeStampResponse(getTSAResponse(generate.getEncoded()));
        timeStampResponse.validate(generate);
        PKIFailureInfo failInfo = timeStampResponse.getFailInfo();
        if (failInfo == null) {
            i = 0;
        } else {
            i = failInfo.intValue();
        }
        if (i != 0) {
            throw new IOException(MessageLocalization.getComposedMessage("invalid.tsa.1.response.code.2", this.tsaURL, String.valueOf(i)));
        }
        TimeStampToken timeStampToken = timeStampResponse.getTimeStampToken();
        if (timeStampToken == null) {
            throw new IOException(MessageLocalization.getComposedMessage("tsa.1.failed.to.return.time.stamp.token.2", this.tsaURL, timeStampResponse.getStatusString()));
        }
        TimeStampTokenInfo timeStampInfo = timeStampToken.getTimeStampInfo();
        byte[] encoded = timeStampToken.getEncoded();
        LOGGER.info("Timestamp generated: " + timeStampInfo.getGenTime());
        if (this.tsaInfo != null) {
            this.tsaInfo.inspectTimeStampTokenInfo(timeStampInfo);
        }
        this.tokenSizeEstimate = encoded.length + 32;
        return encoded;
    }

    /* access modifiers changed from: protected */
    public byte[] getTSAResponse(byte[] bArr) throws IOException {
        try {
            URLConnection openConnection = new URL(this.tsaURL).openConnection();
            openConnection.setDoInput(true);
            openConnection.setDoOutput(true);
            openConnection.setUseCaches(false);
            openConnection.setRequestProperty(HttpRequest.HEADER_CONTENT_TYPE, "application/timestamp-query");
            openConnection.setRequestProperty("Content-Transfer-Encoding", "binary");
            if (this.tsaUsername != null && !this.tsaUsername.equals("")) {
                String str = this.tsaUsername + ":" + this.tsaPassword;
                openConnection.setRequestProperty(HttpRequest.HEADER_AUTHORIZATION, "Basic " + Base64.encodeBytes(str.getBytes(), 8));
            }
            OutputStream outputStream = openConnection.getOutputStream();
            outputStream.write(bArr);
            outputStream.close();
            InputStream inputStream = openConnection.getInputStream();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] bArr2 = new byte[1024];
            while (true) {
                int read = inputStream.read(bArr2, 0, bArr2.length);
                if (read < 0) {
                    break;
                }
                byteArrayOutputStream.write(bArr2, 0, read);
            }
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            String contentEncoding = openConnection.getContentEncoding();
            return (contentEncoding == null || !contentEncoding.equalsIgnoreCase("base64")) ? byteArray : Base64.decode(new String(byteArray));
        } catch (IOException unused) {
            throw new IOException(MessageLocalization.getComposedMessage("failed.to.get.tsa.response.from.1", this.tsaURL));
        }
    }
}
