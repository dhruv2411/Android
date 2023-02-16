package com.itextpdf.text.pdf.codec;

import java.io.IOException;
import java.io.OutputStream;

public class LZWCompressor {
    BitFile bf_;
    int clearCode_ = (1 << this.codeSize_);
    int codeSize_;
    int endOfInfo_ = (this.clearCode_ + 1);
    int limit_ = ((1 << this.numBits_) - 1);
    LZWStringTable lzss_;
    int numBits_ = (this.codeSize_ + 1);
    short prefix_;
    boolean tiffFudge_;

    public LZWCompressor(OutputStream outputStream, int i, boolean z) throws IOException {
        this.bf_ = new BitFile(outputStream, !z);
        this.codeSize_ = i;
        this.tiffFudge_ = z;
        if (this.tiffFudge_) {
            this.limit_--;
        }
        this.prefix_ = -1;
        this.lzss_ = new LZWStringTable();
        this.lzss_.ClearTable(this.codeSize_);
        this.bf_.writeBits(this.clearCode_, this.numBits_);
    }

    public void compress(byte[] bArr, int i, int i2) throws IOException {
        int i3 = i2 + i;
        while (i < i3) {
            byte b = bArr[i];
            short FindCharString = this.lzss_.FindCharString(this.prefix_, b);
            if (FindCharString != -1) {
                this.prefix_ = FindCharString;
            } else {
                this.bf_.writeBits(this.prefix_, this.numBits_);
                if (this.lzss_.AddCharString(this.prefix_, b) > this.limit_) {
                    if (this.numBits_ == 12) {
                        this.bf_.writeBits(this.clearCode_, this.numBits_);
                        this.lzss_.ClearTable(this.codeSize_);
                        this.numBits_ = this.codeSize_ + 1;
                    } else {
                        this.numBits_++;
                    }
                    this.limit_ = (1 << this.numBits_) - 1;
                    if (this.tiffFudge_) {
                        this.limit_--;
                    }
                }
                this.prefix_ = (short) (((short) b) & 255);
            }
            i++;
        }
    }

    public void flush() throws IOException {
        if (this.prefix_ != -1) {
            this.bf_.writeBits(this.prefix_, this.numBits_);
        }
        this.bf_.writeBits(this.endOfInfo_, this.numBits_);
        this.bf_.flush();
    }
}
