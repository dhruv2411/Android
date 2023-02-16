package com.itextpdf.text;

import java.io.Serializable;

public class AccessibleElementId implements Comparable<AccessibleElementId>, Serializable {
    private static int id_counter;
    private int id = 0;

    public AccessibleElementId() {
        int i = id_counter + 1;
        id_counter = i;
        this.id = i;
    }

    public String toString() {
        return Integer.toString(this.id);
    }

    public int hashCode() {
        return this.id;
    }

    public boolean equals(Object obj) {
        return (obj instanceof AccessibleElementId) && this.id == ((AccessibleElementId) obj).id;
    }

    public int compareTo(AccessibleElementId accessibleElementId) {
        if (this.id < accessibleElementId.id) {
            return -1;
        }
        return this.id > accessibleElementId.id ? 1 : 0;
    }
}
