package com.itextpdf.text;

public class ZapfDingbatsList extends List {
    protected int zn;

    public ZapfDingbatsList(int i) {
        super(true);
        this.zn = i;
        this.symbol.setFont(FontFactory.getFont("ZapfDingbats", this.symbol.getFont().getSize(), 0));
        this.postSymbol = " ";
    }

    public ZapfDingbatsList(int i, int i2) {
        super(true, (float) i2);
        this.zn = i;
        this.symbol.setFont(FontFactory.getFont("ZapfDingbats", this.symbol.getFont().getSize(), 0));
        this.postSymbol = " ";
    }

    public ZapfDingbatsList(int i, int i2, BaseColor baseColor) {
        super(true, (float) i2);
        this.zn = i;
        this.symbol.setFont(FontFactory.getFont("ZapfDingbats", this.symbol.getFont().getSize(), 0, baseColor));
        this.postSymbol = " ";
    }

    public void setDingbatColor(BaseColor baseColor) {
        this.symbol.setFont(FontFactory.getFont("ZapfDingbats", this.symbol.getFont().getSize(), 0, baseColor));
    }

    public void setCharNumber(int i) {
        this.zn = i;
    }

    public int getCharNumber() {
        return this.zn;
    }

    public boolean add(Element element) {
        if (element instanceof ListItem) {
            ListItem listItem = (ListItem) element;
            Chunk chunk = new Chunk(this.preSymbol, this.symbol.getFont());
            chunk.setAttributes(this.symbol.getAttributes());
            chunk.append(String.valueOf((char) this.zn));
            chunk.append(this.postSymbol);
            listItem.setListSymbol(chunk);
            listItem.setIndentationLeft(this.symbolIndent, this.autoindent);
            listItem.setIndentationRight(0.0f);
            this.list.add(listItem);
            return false;
        } else if (!(element instanceof List)) {
            return false;
        } else {
            List list = (List) element;
            list.setIndentationLeft(list.getIndentationLeft() + this.symbolIndent);
            this.first--;
            return this.list.add(list);
        }
    }

    public List cloneShallow() {
        ZapfDingbatsList zapfDingbatsList = new ZapfDingbatsList(this.zn);
        populateProperties(zapfDingbatsList);
        return zapfDingbatsList;
    }
}
