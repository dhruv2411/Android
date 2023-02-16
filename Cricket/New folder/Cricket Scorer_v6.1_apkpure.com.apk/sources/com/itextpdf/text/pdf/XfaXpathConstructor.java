package com.itextpdf.text.pdf;

import com.itextpdf.text.pdf.security.XpathConstructor;
import com.itextpdf.text.xml.xmp.PdfSchema;
import com.itextpdf.xmp.XMPConst;

public class XfaXpathConstructor implements XpathConstructor {
    private final String CONFIG;
    private final String CONNECTIONSET;
    private final String DATASETS;
    private final String LOCALESET;
    private final String PDF;
    private final String SOURCESET;
    private final String STYLESHEET;
    private final String TEMPLATE;
    private final String XDC;
    private final String XFDF;
    private final String XMPMETA;
    private String xpathExpression;

    public enum XdpPackage {
        Config,
        ConnectionSet,
        Datasets,
        LocaleSet,
        Pdf,
        SourceSet,
        Stylesheet,
        Template,
        Xdc,
        Xfdf,
        Xmpmeta
    }

    public XfaXpathConstructor() {
        this.CONFIG = "config";
        this.CONNECTIONSET = "connectionSet";
        this.DATASETS = "datasets";
        this.LOCALESET = "localeSet";
        this.PDF = PdfSchema.DEFAULT_XPATH_ID;
        this.SOURCESET = "sourceSet";
        this.STYLESHEET = "stylesheet";
        this.TEMPLATE = "template";
        this.XDC = "xdc";
        this.XFDF = "xfdf";
        this.XMPMETA = XMPConst.TAG_XMPMETA;
        this.xpathExpression = "";
    }

    public XfaXpathConstructor(XdpPackage xdpPackage) {
        String str;
        this.CONFIG = "config";
        this.CONNECTIONSET = "connectionSet";
        this.DATASETS = "datasets";
        this.LOCALESET = "localeSet";
        this.PDF = PdfSchema.DEFAULT_XPATH_ID;
        this.SOURCESET = "sourceSet";
        this.STYLESHEET = "stylesheet";
        this.TEMPLATE = "template";
        this.XDC = "xdc";
        this.XFDF = "xfdf";
        this.XMPMETA = XMPConst.TAG_XMPMETA;
        switch (xdpPackage) {
            case Config:
                str = "config";
                break;
            case ConnectionSet:
                str = "connectionSet";
                break;
            case Datasets:
                str = "datasets";
                break;
            case LocaleSet:
                str = "localeSet";
                break;
            case Pdf:
                str = PdfSchema.DEFAULT_XPATH_ID;
                break;
            case SourceSet:
                str = "sourceSet";
                break;
            case Stylesheet:
                str = "stylesheet";
                break;
            case Template:
                str = "template";
                break;
            case Xdc:
                str = "xdc";
                break;
            case Xfdf:
                str = "xfdf";
                break;
            case Xmpmeta:
                str = XMPConst.TAG_XMPMETA;
                break;
            default:
                this.xpathExpression = "";
                return;
        }
        this.xpathExpression = "/xdp:xdp/*[local-name()='" + str + "']";
    }

    public String getXpathExpression() {
        return this.xpathExpression;
    }
}
