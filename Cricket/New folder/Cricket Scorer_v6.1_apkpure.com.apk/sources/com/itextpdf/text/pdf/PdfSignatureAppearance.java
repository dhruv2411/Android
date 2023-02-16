package com.itextpdf.text.pdf;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.Version;
import com.itextpdf.text.error_messages.MessageLocalization;
import com.itextpdf.text.io.RASInputStream;
import com.itextpdf.text.io.RandomAccessSource;
import com.itextpdf.text.io.RandomAccessSourceFactory;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.security.CertificateInfo;
import com.itextpdf.text.pdf.security.SecurityConstants;
import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;

public class PdfSignatureAppearance {
    public static final int CERTIFIED_FORM_FILLING = 2;
    public static final int CERTIFIED_FORM_FILLING_AND_ANNOTATIONS = 3;
    public static final int CERTIFIED_NO_CHANGES_ALLOWED = 1;
    private static final float MARGIN = 2.0f;
    public static final int NOT_CERTIFIED = 0;
    private static final float TOP_SECTION = 0.3f;
    public static final String questionMark = "% DSUnknown\nq\n1 G\n1 g\n0.1 0 0 0.1 9 0 cm\n0 J 0 j 4 M []0 d\n1 i \n0 g\n313 292 m\n313 404 325 453 432 529 c\n478 561 504 597 504 645 c\n504 736 440 760 391 760 c\n286 760 271 681 265 626 c\n265 625 l\n100 625 l\n100 828 253 898 381 898 c\n451 898 679 878 679 650 c\n679 555 628 499 538 435 c\n488 399 467 376 467 292 c\n313 292 l\nh\n308 214 170 -164 re\nf\n0.44 G\n1.2 w\n1 1 0.4 rg\n287 318 m\n287 430 299 479 406 555 c\n451 587 478 623 478 671 c\n478 762 414 786 365 786 c\n260 786 245 707 239 652 c\n239 651 l\n74 651 l\n74 854 227 924 355 924 c\n425 924 653 904 653 676 c\n653 581 602 525 512 461 c\n462 425 441 402 441 318 c\n287 318 l\nh\n282 240 170 -164 re\nB\nQ\n";
    private boolean acro6Layers = true;
    private PdfTemplate[] app = new PdfTemplate[5];
    private byte[] bout;
    private int boutLen;
    private int certificationLevel = 0;
    private String contact;
    private PdfDictionary cryptoDictionary;
    private HashMap<PdfName, PdfLiteral> exclusionLocations;
    private PdfSigLockDictionary fieldLock;
    private String fieldName;
    private PdfTemplate frm;
    private Image image;
    private float imageScale;
    private Font layer2Font;
    private String layer2Text;
    private String layer4Text;
    private String location;
    private String locationCaption = "Location: ";
    private OutputStream originalout;
    private int page = 1;
    private Rectangle pageRect;
    private boolean preClosed = false;
    private RandomAccessFile raf;
    private long[] range;
    private String reason;
    private String reasonCaption = "Reason: ";
    private Rectangle rect;
    private RenderingMode renderingMode = RenderingMode.DESCRIPTION;
    private boolean reuseAppearance = false;
    private int runDirection = 1;
    private Certificate signCertificate;
    private Calendar signDate;
    private String signatureCreator;
    private SignatureEvent signatureEvent;
    private Image signatureGraphic = null;
    private ByteBuffer sigout;
    private PdfStamper stamper;
    private File tempFile;
    private PdfStamperImp writer;

    public enum RenderingMode {
        DESCRIPTION,
        NAME_AND_DESCRIPTION,
        GRAPHIC_AND_DESCRIPTION,
        GRAPHIC
    }

    public interface SignatureEvent {
        void getSignatureDictionary(PdfDictionary pdfDictionary);
    }

    PdfSignatureAppearance(PdfStamperImp pdfStamperImp) {
        this.writer = pdfStamperImp;
        this.signDate = new GregorianCalendar();
        this.fieldName = getNewSigName();
        this.signatureCreator = Version.getInstance().getVersion();
    }

    public void setCertificationLevel(int i) {
        this.certificationLevel = i;
    }

    public int getCertificationLevel() {
        return this.certificationLevel;
    }

    public String getReason() {
        return this.reason;
    }

    public void setReason(String str) {
        this.reason = str;
    }

    public void setReasonCaption(String str) {
        this.reasonCaption = str;
    }

    public String getLocation() {
        return this.location;
    }

    public void setLocation(String str) {
        this.location = str;
    }

    public void setLocationCaption(String str) {
        this.locationCaption = str;
    }

    public String getSignatureCreator() {
        return this.signatureCreator;
    }

    public void setSignatureCreator(String str) {
        this.signatureCreator = str;
    }

    public String getContact() {
        return this.contact;
    }

    public void setContact(String str) {
        this.contact = str;
    }

    public Calendar getSignDate() {
        return this.signDate;
    }

    public void setSignDate(Calendar calendar) {
        this.signDate = calendar;
    }

    public InputStream getRangeStream() throws IOException {
        return new RASInputStream(new RandomAccessSourceFactory().createRanged(getUnderlyingSource(), this.range));
    }

    private RandomAccessSource getUnderlyingSource() throws IOException {
        RandomAccessSourceFactory randomAccessSourceFactory = new RandomAccessSourceFactory();
        return this.raf == null ? randomAccessSourceFactory.createSource(this.bout) : randomAccessSourceFactory.createSource(this.raf);
    }

    public void addDeveloperExtension(PdfDeveloperExtension pdfDeveloperExtension) {
        this.writer.addDeveloperExtension(pdfDeveloperExtension);
    }

    public PdfDictionary getCryptoDictionary() {
        return this.cryptoDictionary;
    }

    public void setCryptoDictionary(PdfDictionary pdfDictionary) {
        this.cryptoDictionary = pdfDictionary;
    }

    public void setCertificate(Certificate certificate) {
        this.signCertificate = certificate;
    }

    public Certificate getCertificate() {
        return this.signCertificate;
    }

    public SignatureEvent getSignatureEvent() {
        return this.signatureEvent;
    }

    public void setSignatureEvent(SignatureEvent signatureEvent2) {
        this.signatureEvent = signatureEvent2;
    }

    public String getFieldName() {
        return this.fieldName;
    }

    public String getNewSigName() {
        AcroFields acroFields = this.writer.getAcroFields();
        boolean z = false;
        int i = 0;
        while (!z) {
            i++;
            String str = SecurityConstants.Signature + i;
            if (acroFields.getFieldItem(str) == null) {
                String str2 = str + ".";
                Iterator<String> it = acroFields.getFields().keySet().iterator();
                while (true) {
                    if (it.hasNext()) {
                        if (it.next().startsWith(str2)) {
                            z = false;
                            break;
                        }
                    } else {
                        z = true;
                        break;
                    }
                }
            }
        }
        return SecurityConstants.Signature + i;
    }

    public int getPage() {
        return this.page;
    }

    public Rectangle getRect() {
        return this.rect;
    }

    public Rectangle getPageRect() {
        return this.pageRect;
    }

    public boolean isInvisible() {
        return this.rect == null || this.rect.getWidth() == 0.0f || this.rect.getHeight() == 0.0f;
    }

    public void setVisibleSignature(Rectangle rectangle, int i, String str) {
        if (str != null) {
            if (str.indexOf(46) >= 0) {
                throw new IllegalArgumentException(MessageLocalization.getComposedMessage("field.names.cannot.contain.a.dot", new Object[0]));
            } else if (this.writer.getAcroFields().getFieldItem(str) != null) {
                throw new IllegalArgumentException(MessageLocalization.getComposedMessage("the.field.1.already.exists", str));
            } else {
                this.fieldName = str;
            }
        }
        if (i < 1 || i > this.writer.reader.getNumberOfPages()) {
            throw new IllegalArgumentException(MessageLocalization.getComposedMessage("invalid.page.number.1", i));
        }
        this.pageRect = new Rectangle(rectangle);
        this.pageRect.normalize();
        this.rect = new Rectangle(this.pageRect.getWidth(), this.pageRect.getHeight());
        this.page = i;
    }

    public void setVisibleSignature(String str) {
        AcroFields.Item fieldItem = this.writer.getAcroFields().getFieldItem(str);
        if (fieldItem == null) {
            throw new IllegalArgumentException(MessageLocalization.getComposedMessage("the.field.1.does.not.exist", str));
        }
        PdfDictionary merged = fieldItem.getMerged(0);
        if (!PdfName.SIG.equals(PdfReader.getPdfObject(merged.get(PdfName.FT)))) {
            throw new IllegalArgumentException(MessageLocalization.getComposedMessage("the.field.1.is.not.a.signature.field", str));
        }
        this.fieldName = str;
        PdfArray asArray = merged.getAsArray(PdfName.RECT);
        this.pageRect = new Rectangle(asArray.getAsNumber(0).floatValue(), asArray.getAsNumber(1).floatValue(), asArray.getAsNumber(2).floatValue(), asArray.getAsNumber(3).floatValue());
        this.pageRect.normalize();
        this.page = fieldItem.getPage(0).intValue();
        int pageRotation = this.writer.reader.getPageRotation(this.page);
        Rectangle pageSizeWithRotation = this.writer.reader.getPageSizeWithRotation(this.page);
        if (pageRotation == 90) {
            this.pageRect = new Rectangle(this.pageRect.getBottom(), pageSizeWithRotation.getTop() - this.pageRect.getLeft(), this.pageRect.getTop(), pageSizeWithRotation.getTop() - this.pageRect.getRight());
        } else if (pageRotation == 180) {
            this.pageRect = new Rectangle(pageSizeWithRotation.getRight() - this.pageRect.getLeft(), pageSizeWithRotation.getTop() - this.pageRect.getBottom(), pageSizeWithRotation.getRight() - this.pageRect.getRight(), pageSizeWithRotation.getTop() - this.pageRect.getTop());
        } else if (pageRotation == 270) {
            this.pageRect = new Rectangle(pageSizeWithRotation.getRight() - this.pageRect.getBottom(), this.pageRect.getLeft(), pageSizeWithRotation.getRight() - this.pageRect.getTop(), this.pageRect.getRight());
        }
        if (pageRotation != 0) {
            this.pageRect.normalize();
        }
        this.rect = new Rectangle(this.pageRect.getWidth(), this.pageRect.getHeight());
    }

    public RenderingMode getRenderingMode() {
        return this.renderingMode;
    }

    public void setRenderingMode(RenderingMode renderingMode2) {
        this.renderingMode = renderingMode2;
    }

    public Image getSignatureGraphic() {
        return this.signatureGraphic;
    }

    public void setSignatureGraphic(Image image2) {
        this.signatureGraphic = image2;
    }

    public boolean isAcro6Layers() {
        return this.acro6Layers;
    }

    public void setAcro6Layers(boolean z) {
        this.acro6Layers = z;
    }

    public PdfTemplate getLayer(int i) {
        if (i < 0 || i >= this.app.length) {
            return null;
        }
        PdfTemplate pdfTemplate = this.app[i];
        if (pdfTemplate != null) {
            return pdfTemplate;
        }
        PdfTemplate[] pdfTemplateArr = this.app;
        PdfTemplate pdfTemplate2 = new PdfTemplate(this.writer);
        pdfTemplateArr[i] = pdfTemplate2;
        pdfTemplate2.setBoundingBox(this.rect);
        PdfStamperImp pdfStamperImp = this.writer;
        pdfStamperImp.addDirectTemplateSimple(pdfTemplate2, new PdfName("n" + i));
        return pdfTemplate2;
    }

    public void setReuseAppearance(boolean z) {
        this.reuseAppearance = z;
    }

    public Image getImage() {
        return this.image;
    }

    public void setImage(Image image2) {
        this.image = image2;
    }

    public float getImageScale() {
        return this.imageScale;
    }

    public void setImageScale(float f) {
        this.imageScale = f;
    }

    public void setLayer2Text(String str) {
        this.layer2Text = str;
    }

    public String getLayer2Text() {
        return this.layer2Text;
    }

    public Font getLayer2Font() {
        return this.layer2Font;
    }

    public void setLayer2Font(Font font) {
        this.layer2Font = font;
    }

    public void setRunDirection(int i) {
        if (i < 0 || i > 3) {
            throw new RuntimeException(MessageLocalization.getComposedMessage("invalid.run.direction.1", i));
        }
        this.runDirection = i;
    }

    public int getRunDirection() {
        return this.runDirection;
    }

    public void setLayer4Text(String str) {
        this.layer4Text = str;
    }

    public String getLayer4Text() {
        return this.layer4Text;
    }

    public PdfTemplate getTopLayer() {
        if (this.frm == null) {
            this.frm = new PdfTemplate(this.writer);
            this.frm.setBoundingBox(this.rect);
            this.writer.addDirectTemplateSimple(this.frm, new PdfName("FRM"));
        }
        return this.frm;
    }

    public PdfTemplate getAppearance() throws DocumentException {
        Font font;
        String str;
        PdfTemplate pdfTemplate;
        Font font2;
        Rectangle rectangle;
        Rectangle rectangle2;
        PdfTemplate pdfTemplate2;
        String str2;
        if (isInvisible()) {
            PdfTemplate pdfTemplate3 = new PdfTemplate(this.writer);
            pdfTemplate3.setBoundingBox(new Rectangle(0.0f, 0.0f));
            this.writer.addDirectTemplateSimple(pdfTemplate3, (PdfName) null);
            return pdfTemplate3;
        }
        if (this.app[0] == null && !this.reuseAppearance) {
            createBlankN0();
        }
        if (this.app[1] == null && !this.acro6Layers) {
            PdfTemplate[] pdfTemplateArr = this.app;
            PdfTemplate pdfTemplate4 = new PdfTemplate(this.writer);
            pdfTemplateArr[1] = pdfTemplate4;
            pdfTemplate4.setBoundingBox(new Rectangle(100.0f, 100.0f));
            this.writer.addDirectTemplateSimple(pdfTemplate4, new PdfName("n1"));
            pdfTemplate4.setLiteral(questionMark);
        }
        if (this.app[2] == null) {
            if (this.layer2Text == null) {
                StringBuilder sb = new StringBuilder();
                sb.append("Digitally signed by ");
                CertificateInfo.X500Name subjectFields = CertificateInfo.getSubjectFields((X509Certificate) this.signCertificate);
                if (subjectFields != null) {
                    String field = subjectFields.getField("CN");
                    str2 = field == null ? subjectFields.getField("E") : field;
                } else {
                    str2 = null;
                }
                if (str2 == null) {
                    str2 = "";
                }
                sb.append(str2);
                sb.append(10);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss z");
                sb.append("Date: ");
                sb.append(simpleDateFormat.format(this.signDate.getTime()));
                if (this.reason != null) {
                    sb.append(10);
                    sb.append(this.reasonCaption);
                    sb.append(this.reason);
                }
                if (this.location != null) {
                    sb.append(10);
                    sb.append(this.locationCaption);
                    sb.append(this.location);
                }
                str = sb.toString();
            } else {
                str = this.layer2Text;
            }
            PdfTemplate[] pdfTemplateArr2 = this.app;
            PdfTemplate pdfTemplate5 = new PdfTemplate(this.writer);
            pdfTemplateArr2[2] = pdfTemplate5;
            pdfTemplate5.setBoundingBox(this.rect);
            this.writer.addDirectTemplateSimple(pdfTemplate5, new PdfName("n2"));
            if (this.image == null) {
                pdfTemplate = pdfTemplate5;
            } else if (this.imageScale == 0.0f) {
                pdfTemplate = pdfTemplate5;
                pdfTemplate5.addImage(this.image, this.rect.getWidth(), 0.0f, 0.0f, this.rect.getHeight(), 0.0f, 0.0f);
            } else {
                pdfTemplate = pdfTemplate5;
                float f = this.imageScale;
                if (this.imageScale < 0.0f) {
                    f = Math.min(this.rect.getWidth() / this.image.getWidth(), this.rect.getHeight() / this.image.getHeight());
                }
                float width = this.image.getWidth() * f;
                float height = this.image.getHeight() * f;
                pdfTemplate.addImage(this.image, width, 0.0f, 0.0f, height, (this.rect.getWidth() - width) / 2.0f, (this.rect.getHeight() - height) / 2.0f);
            }
            if (this.layer2Font == null) {
                font2 = new Font();
            } else {
                font2 = new Font(this.layer2Font);
            }
            float size = font2.getSize();
            if (this.renderingMode == RenderingMode.NAME_AND_DESCRIPTION || (this.renderingMode == RenderingMode.GRAPHIC_AND_DESCRIPTION && this.signatureGraphic != null)) {
                rectangle2 = new Rectangle(2.0f, 2.0f, (this.rect.getWidth() / 2.0f) - 2.0f, this.rect.getHeight() - 2.0f);
                rectangle = new Rectangle((this.rect.getWidth() / 2.0f) + 1.0f, 2.0f, this.rect.getWidth() - 1.0f, this.rect.getHeight() - 2.0f);
                if (this.rect.getHeight() > this.rect.getWidth()) {
                    rectangle2 = new Rectangle(2.0f, this.rect.getHeight() / 2.0f, this.rect.getWidth() - 2.0f, this.rect.getHeight());
                    rectangle = new Rectangle(2.0f, 2.0f, this.rect.getWidth() - 2.0f, (this.rect.getHeight() / 2.0f) - 2.0f);
                }
            } else if (this.renderingMode != RenderingMode.GRAPHIC) {
                rectangle = new Rectangle(2.0f, 2.0f, this.rect.getWidth() - 2.0f, (this.rect.getHeight() * 0.7f) - 2.0f);
                rectangle2 = null;
            } else if (this.signatureGraphic == null) {
                throw new IllegalStateException(MessageLocalization.getComposedMessage("a.signature.image.should.be.present.when.rendering.mode.is.graphic.only", new Object[0]));
            } else {
                rectangle2 = new Rectangle(2.0f, 2.0f, this.rect.getWidth() - 2.0f, this.rect.getHeight() - 2.0f);
                rectangle = null;
            }
            switch (this.renderingMode) {
                case NAME_AND_DESCRIPTION:
                    pdfTemplate2 = pdfTemplate;
                    String field2 = CertificateInfo.getSubjectFields((X509Certificate) this.signCertificate).getField("CN");
                    if (field2 == null) {
                        field2 = CertificateInfo.getSubjectFields((X509Certificate) this.signCertificate).getField("E");
                    }
                    if (field2 == null) {
                        field2 = "";
                    }
                    float fitText = ColumnText.fitText(font2, field2, new Rectangle(rectangle2.getWidth() - 2.0f, rectangle2.getHeight() - 2.0f), -1.0f, this.runDirection);
                    ColumnText columnText = new ColumnText(pdfTemplate2);
                    columnText.setRunDirection(this.runDirection);
                    columnText.setSimpleColumn(new Phrase(field2, font2), rectangle2.getLeft(), rectangle2.getBottom(), rectangle2.getRight(), rectangle2.getTop(), fitText, 0);
                    columnText.go();
                    break;
                case GRAPHIC_AND_DESCRIPTION:
                    pdfTemplate2 = pdfTemplate;
                    if (this.signatureGraphic != null) {
                        ColumnText columnText2 = new ColumnText(pdfTemplate2);
                        columnText2.setRunDirection(this.runDirection);
                        columnText2.setSimpleColumn(rectangle2.getLeft(), rectangle2.getBottom(), rectangle2.getRight(), rectangle2.getTop(), 0.0f, 2);
                        Image instance = Image.getInstance(this.signatureGraphic);
                        instance.scaleToFit(rectangle2.getWidth(), rectangle2.getHeight());
                        Paragraph paragraph = new Paragraph();
                        paragraph.add((Element) new Chunk(instance, ((rectangle2.getWidth() - instance.getScaledWidth()) / 2.0f) + 0.0f + ((rectangle2.getWidth() - instance.getScaledWidth()) / 2.0f), ((-instance.getScaledHeight()) + 15.0f) - ((rectangle2.getHeight() - instance.getScaledHeight()) / 2.0f), false));
                        columnText2.addElement(paragraph);
                        columnText2.go();
                        break;
                    } else {
                        throw new IllegalStateException(MessageLocalization.getComposedMessage("a.signature.image.should.be.present.when.rendering.mode.is.graphic.and.description", new Object[0]));
                    }
                case GRAPHIC:
                    pdfTemplate2 = pdfTemplate;
                    ColumnText columnText3 = new ColumnText(pdfTemplate2);
                    columnText3.setRunDirection(this.runDirection);
                    columnText3.setSimpleColumn(rectangle2.getLeft(), rectangle2.getBottom(), rectangle2.getRight(), rectangle2.getTop(), 0.0f, 2);
                    Image instance2 = Image.getInstance(this.signatureGraphic);
                    instance2.scaleToFit(rectangle2.getWidth(), rectangle2.getHeight());
                    Paragraph paragraph2 = new Paragraph(rectangle2.getHeight());
                    paragraph2.add((Element) new Chunk(instance2, (rectangle2.getWidth() - instance2.getScaledWidth()) / 2.0f, (rectangle2.getHeight() - instance2.getScaledHeight()) / 2.0f, false));
                    columnText3.addElement(paragraph2);
                    columnText3.go();
                    break;
                default:
                    pdfTemplate2 = pdfTemplate;
                    break;
            }
            if (this.renderingMode != RenderingMode.GRAPHIC) {
                if (size <= 0.0f) {
                    size = ColumnText.fitText(font2, str, new Rectangle(rectangle.getWidth(), rectangle.getHeight()), 12.0f, this.runDirection);
                }
                ColumnText columnText4 = new ColumnText(pdfTemplate2);
                columnText4.setRunDirection(this.runDirection);
                columnText4.setSimpleColumn(new Phrase(str, font2), rectangle.getLeft(), rectangle.getBottom(), rectangle.getRight(), rectangle.getTop(), size, 0);
                columnText4.go();
            }
        }
        if (this.app[3] == null && !this.acro6Layers) {
            PdfTemplate[] pdfTemplateArr3 = this.app;
            PdfTemplate pdfTemplate6 = new PdfTemplate(this.writer);
            pdfTemplateArr3[3] = pdfTemplate6;
            pdfTemplate6.setBoundingBox(new Rectangle(100.0f, 100.0f));
            this.writer.addDirectTemplateSimple(pdfTemplate6, new PdfName("n3"));
            pdfTemplate6.setLiteral("% DSBlank\n");
        }
        if (this.app[4] == null && !this.acro6Layers) {
            PdfTemplate[] pdfTemplateArr4 = this.app;
            PdfTemplate pdfTemplate7 = new PdfTemplate(this.writer);
            pdfTemplateArr4[4] = pdfTemplate7;
            pdfTemplate7.setBoundingBox(new Rectangle(0.0f, this.rect.getHeight() * 0.7f, this.rect.getRight(), this.rect.getTop()));
            this.writer.addDirectTemplateSimple(pdfTemplate7, new PdfName("n4"));
            if (this.layer2Font == null) {
                font = new Font();
            } else {
                font = new Font(this.layer2Font);
            }
            String str3 = "Signature Not Verified";
            if (this.layer4Text != null) {
                str3 = this.layer4Text;
            }
            float fitText2 = ColumnText.fitText(font, str3, new Rectangle(this.rect.getWidth() - 4.0f, (this.rect.getHeight() * TOP_SECTION) - 4.0f), 15.0f, this.runDirection);
            ColumnText columnText5 = new ColumnText(pdfTemplate7);
            columnText5.setRunDirection(this.runDirection);
            columnText5.setSimpleColumn(new Phrase(str3, font), 2.0f, 0.0f, this.rect.getWidth() - 2.0f, this.rect.getHeight() - 2.0f, fitText2, 0);
            columnText5.go();
        }
        int pageRotation = this.writer.reader.getPageRotation(this.page);
        Rectangle rectangle3 = new Rectangle(this.rect);
        for (int i = pageRotation; i > 0; i -= 90) {
            rectangle3 = rectangle3.rotate();
        }
        if (this.frm == null) {
            this.frm = new PdfTemplate(this.writer);
            this.frm.setBoundingBox(rectangle3);
            this.writer.addDirectTemplateSimple(this.frm, new PdfName("FRM"));
            float min = Math.min(this.rect.getWidth(), this.rect.getHeight()) * 0.9f;
            float width2 = (this.rect.getWidth() - min) / 2.0f;
            float height2 = (this.rect.getHeight() - min) / 2.0f;
            float f2 = min / 100.0f;
            if (pageRotation == 90) {
                this.frm.concatCTM(0.0f, 1.0f, -1.0f, 0.0f, this.rect.getHeight(), 0.0f);
            } else if (pageRotation == 180) {
                this.frm.concatCTM(-1.0f, 0.0f, 0.0f, -1.0f, this.rect.getWidth(), this.rect.getHeight());
            } else if (pageRotation == 270) {
                this.frm.concatCTM(0.0f, -1.0f, 1.0f, 0.0f, 0.0f, this.rect.getWidth());
            }
            if (this.reuseAppearance) {
                PdfIndirectReference normalAppearance = this.writer.getAcroFields().getNormalAppearance(getFieldName());
                if (normalAppearance != null) {
                    this.frm.addTemplateReference(normalAppearance, new PdfName("n0"), 1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f);
                } else {
                    this.reuseAppearance = false;
                    if (this.app[0] == null) {
                        createBlankN0();
                    }
                }
            }
            if (!this.reuseAppearance) {
                this.frm.addTemplate(this.app[0], 0.0f, 0.0f);
            }
            if (!this.acro6Layers) {
                this.frm.addTemplate(this.app[1], f2, 0.0f, 0.0f, f2, width2, height2);
            }
            this.frm.addTemplate(this.app[2], 0.0f, 0.0f);
            if (!this.acro6Layers) {
                this.frm.addTemplate(this.app[3], f2, 0.0f, 0.0f, f2, width2, height2);
                this.frm.addTemplate(this.app[4], 0.0f, 0.0f);
            }
        }
        PdfTemplate pdfTemplate8 = new PdfTemplate(this.writer);
        pdfTemplate8.setBoundingBox(rectangle3);
        this.writer.addDirectTemplateSimple(pdfTemplate8, (PdfName) null);
        pdfTemplate8.addTemplate(this.frm, 0.0f, 0.0f);
        return pdfTemplate8;
    }

    private void createBlankN0() {
        PdfTemplate[] pdfTemplateArr = this.app;
        PdfTemplate pdfTemplate = new PdfTemplate(this.writer);
        pdfTemplateArr[0] = pdfTemplate;
        pdfTemplate.setBoundingBox(new Rectangle(100.0f, 100.0f));
        this.writer.addDirectTemplateSimple(pdfTemplate, new PdfName("n0"));
        pdfTemplate.setLiteral("% DSBlank\n");
    }

    public PdfStamper getStamper() {
        return this.stamper;
    }

    /* access modifiers changed from: package-private */
    public void setStamper(PdfStamper pdfStamper) {
        this.stamper = pdfStamper;
    }

    /* access modifiers changed from: package-private */
    public ByteBuffer getSigout() {
        return this.sigout;
    }

    /* access modifiers changed from: package-private */
    public void setSigout(ByteBuffer byteBuffer) {
        this.sigout = byteBuffer;
    }

    /* access modifiers changed from: package-private */
    public OutputStream getOriginalout() {
        return this.originalout;
    }

    /* access modifiers changed from: package-private */
    public void setOriginalout(OutputStream outputStream) {
        this.originalout = outputStream;
    }

    public File getTempFile() {
        return this.tempFile;
    }

    /* access modifiers changed from: package-private */
    public void setTempFile(File file) {
        this.tempFile = file;
    }

    public PdfSigLockDictionary getFieldLockDict() {
        return this.fieldLock;
    }

    public void setFieldLockDict(PdfSigLockDictionary pdfSigLockDictionary) {
        this.fieldLock = pdfSigLockDictionary;
    }

    public boolean isPreClosed() {
        return this.preClosed;
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(7:67|68|69|70|71|72|73) */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:71:0x02eb */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void preClose(java.util.HashMap<com.itextpdf.text.pdf.PdfName, java.lang.Integer> r19) throws java.io.IOException, com.itextpdf.text.DocumentException {
        /*
            r18 = this;
            r1 = r18
            boolean r2 = r1.preClosed
            r3 = 0
            if (r2 == 0) goto L_0x0015
            com.itextpdf.text.DocumentException r2 = new com.itextpdf.text.DocumentException
            java.lang.String r4 = "document.already.pre.closed"
            java.lang.Object[] r3 = new java.lang.Object[r3]
            java.lang.String r3 = com.itextpdf.text.error_messages.MessageLocalization.getComposedMessage((java.lang.String) r4, (java.lang.Object[]) r3)
            r2.<init>((java.lang.String) r3)
            throw r2
        L_0x0015:
            com.itextpdf.text.pdf.PdfStamper r2 = r1.stamper
            r2.mergeVerification()
            r2 = 1
            r1.preClosed = r2
            com.itextpdf.text.pdf.PdfStamperImp r4 = r1.writer
            com.itextpdf.text.pdf.AcroFields r4 = r4.getAcroFields()
            java.lang.String r5 = r18.getFieldName()
            boolean r6 = r4.doesSignatureFieldExist(r5)
            com.itextpdf.text.pdf.PdfStamperImp r7 = r1.writer
            com.itextpdf.text.pdf.PdfIndirectReference r7 = r7.getPdfIndirectReference()
            com.itextpdf.text.pdf.PdfStamperImp r8 = r1.writer
            r9 = 3
            r8.setSigFlags(r9)
            if (r6 == 0) goto L_0x00b7
            com.itextpdf.text.pdf.AcroFields$Item r4 = r4.getFieldItem(r5)
            com.itextpdf.text.pdf.PdfDictionary r4 = r4.getWidget(r3)
            com.itextpdf.text.pdf.PdfStamperImp r5 = r1.writer
            r5.markUsed((com.itextpdf.text.pdf.PdfObject) r4)
            com.itextpdf.text.pdf.PdfName r5 = com.itextpdf.text.pdf.PdfName.LOCK
            com.itextpdf.text.pdf.PdfDictionary r5 = r4.getAsDict(r5)
            if (r5 != 0) goto L_0x0065
            com.itextpdf.text.pdf.PdfSigLockDictionary r6 = r1.fieldLock
            if (r6 == 0) goto L_0x0065
            com.itextpdf.text.pdf.PdfName r5 = com.itextpdf.text.pdf.PdfName.LOCK
            com.itextpdf.text.pdf.PdfStamperImp r6 = r1.writer
            com.itextpdf.text.pdf.PdfSigLockDictionary r8 = r1.fieldLock
            com.itextpdf.text.pdf.PdfIndirectObject r6 = r6.addToBody(r8)
            com.itextpdf.text.pdf.PdfIndirectReference r6 = r6.getIndirectReference()
            r4.put(r5, r6)
            com.itextpdf.text.pdf.PdfSigLockDictionary r5 = r1.fieldLock
        L_0x0065:
            com.itextpdf.text.pdf.PdfName r6 = com.itextpdf.text.pdf.PdfName.P
            com.itextpdf.text.pdf.PdfStamperImp r8 = r1.writer
            int r10 = r18.getPage()
            com.itextpdf.text.pdf.PdfIndirectReference r8 = r8.getPageReference(r10)
            r4.put(r6, r8)
            com.itextpdf.text.pdf.PdfName r6 = com.itextpdf.text.pdf.PdfName.V
            r4.put(r6, r7)
            com.itextpdf.text.pdf.PdfName r6 = com.itextpdf.text.pdf.PdfName.F
            com.itextpdf.text.pdf.PdfObject r6 = r4.get(r6)
            com.itextpdf.text.pdf.PdfObject r6 = com.itextpdf.text.pdf.PdfReader.getPdfObjectRelease((com.itextpdf.text.pdf.PdfObject) r6)
            if (r6 == 0) goto L_0x0092
            boolean r8 = r6.isNumber()
            if (r8 == 0) goto L_0x0092
            com.itextpdf.text.pdf.PdfNumber r6 = (com.itextpdf.text.pdf.PdfNumber) r6
            int r6 = r6.intValue()
            goto L_0x0093
        L_0x0092:
            r6 = r3
        L_0x0093:
            r6 = r6 | 128(0x80, float:1.794E-43)
            com.itextpdf.text.pdf.PdfName r8 = com.itextpdf.text.pdf.PdfName.F
            com.itextpdf.text.pdf.PdfNumber r10 = new com.itextpdf.text.pdf.PdfNumber
            r10.<init>((int) r6)
            r4.put(r8, r10)
            com.itextpdf.text.pdf.PdfDictionary r6 = new com.itextpdf.text.pdf.PdfDictionary
            r6.<init>()
            com.itextpdf.text.pdf.PdfName r8 = com.itextpdf.text.pdf.PdfName.N
            com.itextpdf.text.pdf.PdfTemplate r10 = r18.getAppearance()
            com.itextpdf.text.pdf.PdfIndirectReference r10 = r10.getIndirectReference()
            r6.put(r8, r10)
            com.itextpdf.text.pdf.PdfName r8 = com.itextpdf.text.pdf.PdfName.AP
            r4.put(r8, r6)
            goto L_0x0110
        L_0x00b7:
            com.itextpdf.text.pdf.PdfStamperImp r4 = r1.writer
            com.itextpdf.text.pdf.PdfFormField r4 = com.itextpdf.text.pdf.PdfFormField.createSignature(r4)
            r4.setFieldName(r5)
            com.itextpdf.text.pdf.PdfName r5 = com.itextpdf.text.pdf.PdfName.V
            r4.put(r5, r7)
            r5 = 132(0x84, float:1.85E-43)
            r4.setFlags(r5)
            com.itextpdf.text.pdf.PdfSigLockDictionary r5 = r1.fieldLock
            r6 = 0
            if (r5 == 0) goto L_0x00e3
            com.itextpdf.text.pdf.PdfName r5 = com.itextpdf.text.pdf.PdfName.LOCK
            com.itextpdf.text.pdf.PdfStamperImp r8 = r1.writer
            com.itextpdf.text.pdf.PdfSigLockDictionary r10 = r1.fieldLock
            com.itextpdf.text.pdf.PdfIndirectObject r8 = r8.addToBody(r10)
            com.itextpdf.text.pdf.PdfIndirectReference r8 = r8.getIndirectReference()
            r4.put(r5, r8)
            com.itextpdf.text.pdf.PdfSigLockDictionary r5 = r1.fieldLock
            goto L_0x00e4
        L_0x00e3:
            r5 = r6
        L_0x00e4:
            int r8 = r18.getPage()
            boolean r10 = r18.isInvisible()
            if (r10 != 0) goto L_0x00f6
            com.itextpdf.text.Rectangle r10 = r18.getPageRect()
            r4.setWidget(r10, r6)
            goto L_0x00ff
        L_0x00f6:
            com.itextpdf.text.Rectangle r10 = new com.itextpdf.text.Rectangle
            r11 = 0
            r10.<init>(r11, r11)
            r4.setWidget(r10, r6)
        L_0x00ff:
            com.itextpdf.text.pdf.PdfName r6 = com.itextpdf.text.pdf.PdfAnnotation.APPEARANCE_NORMAL
            com.itextpdf.text.pdf.PdfTemplate r10 = r18.getAppearance()
            r4.setAppearance(r6, r10)
            r4.setPage(r8)
            com.itextpdf.text.pdf.PdfStamperImp r6 = r1.writer
            r6.addAnnotation((com.itextpdf.text.pdf.PdfAnnotation) r4, (int) r8)
        L_0x0110:
            java.util.HashMap r4 = new java.util.HashMap
            r4.<init>()
            r1.exclusionLocations = r4
            com.itextpdf.text.pdf.PdfDictionary r4 = r1.cryptoDictionary
            if (r4 != 0) goto L_0x0123
            com.itextpdf.text.DocumentException r2 = new com.itextpdf.text.DocumentException
            java.lang.String r3 = "No crypto dictionary defined."
            r2.<init>((java.lang.String) r3)
            throw r2
        L_0x0123:
            com.itextpdf.text.pdf.PdfLiteral r4 = new com.itextpdf.text.pdf.PdfLiteral
            r6 = 80
            r4.<init>((int) r6)
            java.util.HashMap<com.itextpdf.text.pdf.PdfName, com.itextpdf.text.pdf.PdfLiteral> r6 = r1.exclusionLocations
            com.itextpdf.text.pdf.PdfName r8 = com.itextpdf.text.pdf.PdfName.BYTERANGE
            r6.put(r8, r4)
            com.itextpdf.text.pdf.PdfDictionary r6 = r1.cryptoDictionary
            com.itextpdf.text.pdf.PdfName r8 = com.itextpdf.text.pdf.PdfName.BYTERANGE
            r6.put(r8, r4)
            java.util.Set r4 = r19.entrySet()
            java.util.Iterator r4 = r4.iterator()
        L_0x0140:
            boolean r6 = r4.hasNext()
            if (r6 == 0) goto L_0x016c
            java.lang.Object r6 = r4.next()
            java.util.Map$Entry r6 = (java.util.Map.Entry) r6
            java.lang.Object r8 = r6.getKey()
            com.itextpdf.text.pdf.PdfName r8 = (com.itextpdf.text.pdf.PdfName) r8
            java.lang.Object r6 = r6.getValue()
            java.lang.Integer r6 = (java.lang.Integer) r6
            com.itextpdf.text.pdf.PdfLiteral r10 = new com.itextpdf.text.pdf.PdfLiteral
            int r6 = r6.intValue()
            r10.<init>((int) r6)
            java.util.HashMap<com.itextpdf.text.pdf.PdfName, com.itextpdf.text.pdf.PdfLiteral> r6 = r1.exclusionLocations
            r6.put(r8, r10)
            com.itextpdf.text.pdf.PdfDictionary r6 = r1.cryptoDictionary
            r6.put(r8, r10)
            goto L_0x0140
        L_0x016c:
            int r4 = r1.certificationLevel
            if (r4 <= 0) goto L_0x0175
            com.itextpdf.text.pdf.PdfDictionary r4 = r1.cryptoDictionary
            r1.addDocMDP(r4)
        L_0x0175:
            if (r5 == 0) goto L_0x017c
            com.itextpdf.text.pdf.PdfDictionary r4 = r1.cryptoDictionary
            r1.addFieldMDP(r4, r5)
        L_0x017c:
            com.itextpdf.text.pdf.PdfSignatureAppearance$SignatureEvent r4 = r1.signatureEvent
            if (r4 == 0) goto L_0x0187
            com.itextpdf.text.pdf.PdfSignatureAppearance$SignatureEvent r4 = r1.signatureEvent
            com.itextpdf.text.pdf.PdfDictionary r5 = r1.cryptoDictionary
            r4.getSignatureDictionary(r5)
        L_0x0187:
            com.itextpdf.text.pdf.PdfStamperImp r4 = r1.writer
            com.itextpdf.text.pdf.PdfDictionary r5 = r1.cryptoDictionary
            r4.addToBody((com.itextpdf.text.pdf.PdfObject) r5, (com.itextpdf.text.pdf.PdfIndirectReference) r7, (boolean) r3)
            int r4 = r1.certificationLevel
            if (r4 <= 0) goto L_0x01b3
            com.itextpdf.text.pdf.PdfDictionary r4 = new com.itextpdf.text.pdf.PdfDictionary
            r4.<init>()
            com.itextpdf.text.pdf.PdfName r5 = new com.itextpdf.text.pdf.PdfName
            java.lang.String r6 = "DocMDP"
            r5.<init>((java.lang.String) r6)
            r4.put(r5, r7)
            com.itextpdf.text.pdf.PdfStamperImp r5 = r1.writer
            com.itextpdf.text.pdf.PdfReader r5 = r5.reader
            com.itextpdf.text.pdf.PdfDictionary r5 = r5.getCatalog()
            com.itextpdf.text.pdf.PdfName r6 = new com.itextpdf.text.pdf.PdfName
            java.lang.String r7 = "Perms"
            r6.<init>((java.lang.String) r7)
            r5.put(r6, r4)
        L_0x01b3:
            com.itextpdf.text.pdf.PdfStamperImp r4 = r1.writer
            com.itextpdf.text.pdf.PdfStamper r5 = r1.stamper
            java.util.Map r5 = r5.getMoreInfo()
            r4.close(r5)
            java.util.HashMap<com.itextpdf.text.pdf.PdfName, com.itextpdf.text.pdf.PdfLiteral> r4 = r1.exclusionLocations
            int r4 = r4.size()
            int r4 = r4 * 2
            long[] r4 = new long[r4]
            r1.range = r4
            java.util.HashMap<com.itextpdf.text.pdf.PdfName, com.itextpdf.text.pdf.PdfLiteral> r4 = r1.exclusionLocations
            com.itextpdf.text.pdf.PdfName r5 = com.itextpdf.text.pdf.PdfName.BYTERANGE
            java.lang.Object r4 = r4.get(r5)
            com.itextpdf.text.pdf.PdfLiteral r4 = (com.itextpdf.text.pdf.PdfLiteral) r4
            long r4 = r4.getPosition()
            java.util.HashMap<com.itextpdf.text.pdf.PdfName, com.itextpdf.text.pdf.PdfLiteral> r6 = r1.exclusionLocations
            com.itextpdf.text.pdf.PdfName r7 = com.itextpdf.text.pdf.PdfName.BYTERANGE
            r6.remove(r7)
            java.util.HashMap<com.itextpdf.text.pdf.PdfName, com.itextpdf.text.pdf.PdfLiteral> r6 = r1.exclusionLocations
            java.util.Collection r6 = r6.values()
            java.util.Iterator r6 = r6.iterator()
            r7 = r2
        L_0x01ea:
            boolean r8 = r6.hasNext()
            if (r8 == 0) goto L_0x020f
            java.lang.Object r8 = r6.next()
            com.itextpdf.text.pdf.PdfLiteral r8 = (com.itextpdf.text.pdf.PdfLiteral) r8
            long r10 = r8.getPosition()
            long[] r12 = r1.range
            int r13 = r7 + 1
            r12[r7] = r10
            long[] r7 = r1.range
            int r12 = r13 + 1
            int r8 = r8.getPosLength()
            long r14 = (long) r8
            long r16 = r14 + r10
            r7[r13] = r16
            r7 = r12
            goto L_0x01ea
        L_0x020f:
            long[] r6 = r1.range
            long[] r7 = r1.range
            int r7 = r7.length
            int r7 = r7 - r2
            java.util.Arrays.sort(r6, r2, r7)
        L_0x0218:
            long[] r6 = r1.range
            int r6 = r6.length
            int r6 = r6 + -2
            if (r9 >= r6) goto L_0x0230
            long[] r6 = r1.range
            r7 = r6[r9]
            long[] r10 = r1.range
            int r11 = r9 + -1
            r11 = r10[r11]
            long r13 = r7 - r11
            r6[r9] = r13
            int r9 = r9 + 2
            goto L_0x0218
        L_0x0230:
            java.io.File r6 = r1.tempFile
            r7 = 93
            r8 = 32
            r9 = 91
            if (r6 != 0) goto L_0x028e
            com.itextpdf.text.pdf.ByteBuffer r6 = r1.sigout
            byte[] r6 = r6.getBuffer()
            r1.bout = r6
            com.itextpdf.text.pdf.ByteBuffer r6 = r1.sigout
            int r6 = r6.size()
            r1.boutLen = r6
            long[] r6 = r1.range
            long[] r10 = r1.range
            int r10 = r10.length
            int r10 = r10 - r2
            int r2 = r1.boutLen
            long r11 = (long) r2
            long[] r2 = r1.range
            long[] r13 = r1.range
            int r13 = r13.length
            int r13 = r13 + -2
            r13 = r2[r13]
            long r15 = r11 - r13
            r6[r10] = r15
            com.itextpdf.text.pdf.ByteBuffer r2 = new com.itextpdf.text.pdf.ByteBuffer
            r2.<init>()
            r2.append((char) r9)
            r6 = r3
        L_0x0269:
            long[] r9 = r1.range
            int r9 = r9.length
            if (r6 >= r9) goto L_0x027c
            long[] r9 = r1.range
            r10 = r9[r6]
            com.itextpdf.text.pdf.ByteBuffer r9 = r2.append((long) r10)
            r9.append((char) r8)
            int r6 = r6 + 1
            goto L_0x0269
        L_0x027c:
            r2.append((char) r7)
            byte[] r6 = r2.getBuffer()
            byte[] r7 = r1.bout
            int r4 = (int) r4
            int r2 = r2.size()
            java.lang.System.arraycopy(r6, r3, r7, r4, r2)
            goto L_0x02e3
        L_0x028e:
            java.io.RandomAccessFile r6 = new java.io.RandomAccessFile     // Catch:{ IOException -> 0x02e4 }
            java.io.File r10 = r1.tempFile     // Catch:{ IOException -> 0x02e4 }
            java.lang.String r11 = "rw"
            r6.<init>(r10, r11)     // Catch:{ IOException -> 0x02e4 }
            r1.raf = r6     // Catch:{ IOException -> 0x02e4 }
            java.io.RandomAccessFile r6 = r1.raf     // Catch:{ IOException -> 0x02e4 }
            long r10 = r6.length()     // Catch:{ IOException -> 0x02e4 }
            long[] r6 = r1.range     // Catch:{ IOException -> 0x02e4 }
            long[] r12 = r1.range     // Catch:{ IOException -> 0x02e4 }
            int r12 = r12.length     // Catch:{ IOException -> 0x02e4 }
            int r12 = r12 - r2
            long[] r2 = r1.range     // Catch:{ IOException -> 0x02e4 }
            long[] r13 = r1.range     // Catch:{ IOException -> 0x02e4 }
            int r13 = r13.length     // Catch:{ IOException -> 0x02e4 }
            int r13 = r13 + -2
            r13 = r2[r13]     // Catch:{ IOException -> 0x02e4 }
            long r15 = r10 - r13
            r6[r12] = r15     // Catch:{ IOException -> 0x02e4 }
            com.itextpdf.text.pdf.ByteBuffer r2 = new com.itextpdf.text.pdf.ByteBuffer     // Catch:{ IOException -> 0x02e4 }
            r2.<init>()     // Catch:{ IOException -> 0x02e4 }
            r2.append((char) r9)     // Catch:{ IOException -> 0x02e4 }
            r6 = r3
        L_0x02bb:
            long[] r9 = r1.range     // Catch:{ IOException -> 0x02e4 }
            int r9 = r9.length     // Catch:{ IOException -> 0x02e4 }
            if (r6 >= r9) goto L_0x02ce
            long[] r9 = r1.range     // Catch:{ IOException -> 0x02e4 }
            r10 = r9[r6]     // Catch:{ IOException -> 0x02e4 }
            com.itextpdf.text.pdf.ByteBuffer r9 = r2.append((long) r10)     // Catch:{ IOException -> 0x02e4 }
            r9.append((char) r8)     // Catch:{ IOException -> 0x02e4 }
            int r6 = r6 + 1
            goto L_0x02bb
        L_0x02ce:
            r2.append((char) r7)     // Catch:{ IOException -> 0x02e4 }
            java.io.RandomAccessFile r6 = r1.raf     // Catch:{ IOException -> 0x02e4 }
            r6.seek(r4)     // Catch:{ IOException -> 0x02e4 }
            java.io.RandomAccessFile r4 = r1.raf     // Catch:{ IOException -> 0x02e4 }
            byte[] r5 = r2.getBuffer()     // Catch:{ IOException -> 0x02e4 }
            int r2 = r2.size()     // Catch:{ IOException -> 0x02e4 }
            r4.write(r5, r3, r2)     // Catch:{ IOException -> 0x02e4 }
        L_0x02e3:
            return
        L_0x02e4:
            r0 = move-exception
            r2 = r0
            java.io.RandomAccessFile r3 = r1.raf     // Catch:{ Exception -> 0x02eb }
            r3.close()     // Catch:{ Exception -> 0x02eb }
        L_0x02eb:
            java.io.File r3 = r1.tempFile     // Catch:{ Exception -> 0x02f0 }
            r3.delete()     // Catch:{ Exception -> 0x02f0 }
        L_0x02f0:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.itextpdf.text.pdf.PdfSignatureAppearance.preClose(java.util.HashMap):void");
    }

    private void addDocMDP(PdfDictionary pdfDictionary) {
        PdfDictionary pdfDictionary2 = new PdfDictionary();
        PdfDictionary pdfDictionary3 = new PdfDictionary();
        pdfDictionary3.put(PdfName.P, new PdfNumber(this.certificationLevel));
        pdfDictionary3.put(PdfName.V, new PdfName("1.2"));
        pdfDictionary3.put(PdfName.TYPE, PdfName.TRANSFORMPARAMS);
        pdfDictionary2.put(PdfName.TRANSFORMMETHOD, PdfName.DOCMDP);
        pdfDictionary2.put(PdfName.TYPE, PdfName.SIGREF);
        pdfDictionary2.put(PdfName.TRANSFORMPARAMS, pdfDictionary3);
        if (this.writer.getPdfVersion().getVersion() < '6') {
            pdfDictionary2.put(new PdfName(SecurityConstants.DigestValue), new PdfString("aa"));
            PdfArray pdfArray = new PdfArray();
            pdfArray.add((PdfObject) new PdfNumber(0));
            pdfArray.add((PdfObject) new PdfNumber(0));
            pdfDictionary2.put(new PdfName("DigestLocation"), pdfArray);
            pdfDictionary2.put(new PdfName(SecurityConstants.DigestMethod), new PdfName("MD5"));
        }
        pdfDictionary2.put(PdfName.DATA, this.writer.reader.getTrailer().get(PdfName.ROOT));
        PdfArray pdfArray2 = new PdfArray();
        pdfArray2.add((PdfObject) pdfDictionary2);
        pdfDictionary.put(PdfName.REFERENCE, pdfArray2);
    }

    private void addFieldMDP(PdfDictionary pdfDictionary, PdfDictionary pdfDictionary2) {
        PdfDictionary pdfDictionary3 = new PdfDictionary();
        PdfDictionary pdfDictionary4 = new PdfDictionary();
        pdfDictionary4.putAll(pdfDictionary2);
        pdfDictionary4.put(PdfName.TYPE, PdfName.TRANSFORMPARAMS);
        pdfDictionary4.put(PdfName.V, new PdfName("1.2"));
        pdfDictionary3.put(PdfName.TRANSFORMMETHOD, PdfName.FIELDMDP);
        pdfDictionary3.put(PdfName.TYPE, PdfName.SIGREF);
        pdfDictionary3.put(PdfName.TRANSFORMPARAMS, pdfDictionary4);
        pdfDictionary3.put(new PdfName(SecurityConstants.DigestValue), new PdfString("aa"));
        PdfArray pdfArray = new PdfArray();
        pdfArray.add((PdfObject) new PdfNumber(0));
        pdfArray.add((PdfObject) new PdfNumber(0));
        pdfDictionary3.put(new PdfName("DigestLocation"), pdfArray);
        pdfDictionary3.put(new PdfName(SecurityConstants.DigestMethod), new PdfName("MD5"));
        pdfDictionary3.put(PdfName.DATA, this.writer.reader.getTrailer().get(PdfName.ROOT));
        PdfArray asArray = pdfDictionary.getAsArray(PdfName.REFERENCE);
        if (asArray == null) {
            asArray = new PdfArray();
        }
        asArray.add((PdfObject) pdfDictionary3);
        pdfDictionary.put(PdfName.REFERENCE, asArray);
    }

    public void close(PdfDictionary pdfDictionary) throws IOException, DocumentException {
        try {
            if (!this.preClosed) {
                throw new DocumentException(MessageLocalization.getComposedMessage("preclose.must.be.called.first", new Object[0]));
            }
            ByteBuffer byteBuffer = new ByteBuffer();
            for (PdfName next : pdfDictionary.getKeys()) {
                PdfObject pdfObject = pdfDictionary.get(next);
                PdfLiteral pdfLiteral = this.exclusionLocations.get(next);
                if (pdfLiteral == null) {
                    throw new IllegalArgumentException(MessageLocalization.getComposedMessage("the.key.1.didn.t.reserve.space.in.preclose", next.toString()));
                }
                byteBuffer.reset();
                pdfObject.toPdf((PdfWriter) null, byteBuffer);
                if (byteBuffer.size() > pdfLiteral.getPosLength()) {
                    throw new IllegalArgumentException(MessageLocalization.getComposedMessage("the.key.1.is.too.big.is.2.reserved.3", next.toString(), String.valueOf(byteBuffer.size()), String.valueOf(pdfLiteral.getPosLength())));
                } else if (this.tempFile == null) {
                    System.arraycopy(byteBuffer.getBuffer(), 0, this.bout, (int) pdfLiteral.getPosition(), byteBuffer.size());
                } else {
                    this.raf.seek(pdfLiteral.getPosition());
                    this.raf.write(byteBuffer.getBuffer(), 0, byteBuffer.size());
                }
            }
            if (pdfDictionary.size() != this.exclusionLocations.size()) {
                throw new IllegalArgumentException(MessageLocalization.getComposedMessage("the.update.dictionary.has.less.keys.than.required", new Object[0]));
            }
            if (this.tempFile == null) {
                this.originalout.write(this.bout, 0, this.boutLen);
            } else if (this.originalout != null) {
                this.raf.seek(0);
                long length = this.raf.length();
                byte[] bArr = new byte[8192];
                while (length > 0) {
                    int read = this.raf.read(bArr, 0, (int) Math.min((long) bArr.length, length));
                    if (read < 0) {
                        throw new EOFException(MessageLocalization.getComposedMessage("unexpected.eof", new Object[0]));
                    }
                    this.originalout.write(bArr, 0, read);
                    length -= (long) read;
                }
            }
        } finally {
            this.writer.reader.close();
            if (this.tempFile != null) {
                try {
                    this.raf.close();
                } catch (Exception unused) {
                }
                if (this.originalout != null) {
                    try {
                        this.tempFile.delete();
                    } catch (Exception unused2) {
                    }
                }
            }
            if (this.originalout != null) {
                try {
                    this.originalout.close();
                } catch (Exception unused3) {
                }
            }
        }
    }
}
