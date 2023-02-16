package com.itextpdf.text.pdf.codec;

import java.io.IOException;
import java.io.OutputStream;

public class BitFile {
    int bitsLeft_;
    boolean blocks_ = false;
    byte[] buffer_;
    int index_;
    OutputStream output_;

    public BitFile(OutputStream outputStream, boolean z) {
        this.output_ = outputStream;
        this.blocks_ = z;
        this.buffer_ = new byte[256];
        this.index_ = 0;
        this.bitsLeft_ = 8;
    }

    public void flush() throws IOException {
        int i = this.index_ + (this.bitsLeft_ == 8 ? 0 : 1);
        if (i > 0) {
            if (this.blocks_) {
                this.output_.write(i);
            }
            this.output_.write(this.buffer_, 0, i);
            this.buffer_[0] = 0;
            this.index_ = 0;
            this.bitsLeft_ = 8;
        }
    }

    public void writeBits(int i, int i2) throws IOException {
        do {
            if ((this.index_ == 254 && this.bitsLeft_ == 0) || this.index_ > 254) {
                if (this.blocks_) {
                    this.output_.write(255);
                }
                this.output_.write(this.buffer_, 0, 255);
                this.buffer_[0] = 0;
                this.index_ = 0;
                this.bitsLeft_ = 8;
            }
            if (i2 <= this.bitsLeft_) {
                if (this.blocks_) {
                    byte[] bArr = this.buffer_;
                    int i3 = this.index_;
                    bArr[i3] = (byte) (((i & ((1 << i2) - 1)) << (8 - this.bitsLeft_)) | bArr[i3]);
                    this.bitsLeft_ -= i2;
                } else {
                    byte[] bArr2 = this.buffer_;
                    int i4 = this.index_;
                    bArr2[i4] = (byte) (((i & ((1 << i2) - 1)) << (this.bitsLeft_ - i2)) | bArr2[i4]);
                    this.bitsLeft_ -= i2;
                }
                i2 = 0;
                continue;
            } else if (this.blocks_) {
                byte[] bArr3 = this.buffer_;
                int i5 = this.index_;
                bArr3[i5] = (byte) (bArr3[i5] | ((((1 << this.bitsLeft_) - 1) & i) << (8 - this.bitsLeft_)));
                int i6 = this.bitsLeft_;
                i >>= this.bitsLeft_;
                i2 -= this.bitsLeft_;
                byte[] bArr4 = this.buffer_;
                int i7 = this.index_ + 1;
                this.index_ = i7;
                bArr4[i7] = 0;
                this.bitsLeft_ = 8;
                continue;
            } else {
                byte b = (i >>> (i2 - this.bitsLeft_)) & ((1 << this.bitsLeft_) - 1);
                byte[] bArr5 = this.buffer_;
                int i8 = this.index_;
                bArr5[i8] = (byte) (b | bArr5[i8]);
                i2 -= this.bitsLeft_;
                int i9 = this.bitsLeft_;
                byte[] bArr6 = this.buffer_;
                int i10 = this.index_ + 1;
                this.index_ = i10;
                bArr6[i10] = 0;
                this.bitsLeft_ = 8;
                continue;
            }
        } while (i2 != 0);
    }
}
