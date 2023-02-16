package com.itextpdf.text.io;

import java.io.IOException;

public class MapFailedException extends IOException {
    public MapFailedException(IOException iOException) {
        super(iOException.getMessage());
        initCause(iOException);
    }
}
