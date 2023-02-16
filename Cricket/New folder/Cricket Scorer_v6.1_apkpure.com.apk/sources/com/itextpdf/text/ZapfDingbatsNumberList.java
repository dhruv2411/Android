package com.itextpdf.text;

import com.itextpdf.xmp.XMPError;

public class ZapfDingbatsNumberList extends List {
    protected int type;

    public ZapfDingbatsNumberList(int i) {
        super(true);
        this.type = i;
        this.symbol.setFont(FontFactory.getFont("ZapfDingbats", this.symbol.getFont().getSize(), 0));
        this.postSymbol = " ";
    }

    public ZapfDingbatsNumberList(int i, int i2) {
        super(true, (float) i2);
        this.type = i;
        this.symbol.setFont(FontFactory.getFont("ZapfDingbats", this.symbol.getFont().getSize(), 0));
        this.postSymbol = " ";
    }

    public void setType(int i) {
        this.type = i;
    }

    public int getType() {
        return this.type;
    }

    public boolean add(Element element) {
        if (element instanceof ListItem) {
            ListItem listItem = (ListItem) element;
            Chunk chunk = new Chunk(this.preSymbol, this.symbol.getFont());
            chunk.setAttributes(this.symbol.getAttributes());
            switch (this.type) {
                case 0:
                    chunk.append(String.valueOf((char) (this.first + this.list.size() + 171)));
                    break;
                case 1:
                    chunk.append(String.valueOf((char) (this.first + this.list.size() + 181)));
                    break;
                case 2:
                    chunk.append(String.valueOf((char) (this.first + this.list.size() + 191)));
                    break;
                default:
                    chunk.append(String.valueOf((char) (this.first + this.list.size() + XMPError.BADXML)));
                    break;
            }
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
        ZapfDingbatsNumberList zapfDingbatsNumberList = new ZapfDingbatsNumberList(this.type);
        populateProperties(zapfDingbatsNumberList);
        return zapfDingbatsNumberList;
    }
}
