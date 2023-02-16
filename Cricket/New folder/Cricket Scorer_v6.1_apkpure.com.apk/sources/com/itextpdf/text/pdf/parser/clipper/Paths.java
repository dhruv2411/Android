package com.itextpdf.text.pdf.parser.clipper;

import com.itextpdf.text.pdf.parser.clipper.Point;
import com.itextpdf.text.pdf.parser.clipper.PolyNode;
import java.util.ArrayList;
import java.util.Iterator;

public class Paths extends ArrayList<Path> {
    private static final long serialVersionUID = 1910552127810480852L;

    public static Paths closedPathsFromPolyTree(PolyTree polyTree) {
        Paths paths = new Paths();
        paths.addPolyNode(polyTree, PolyNode.NodeType.CLOSED);
        return paths;
    }

    public static Paths makePolyTreeToPaths(PolyTree polyTree) {
        Paths paths = new Paths();
        paths.addPolyNode(polyTree, PolyNode.NodeType.ANY);
        return paths;
    }

    public static Paths openPathsFromPolyTree(PolyTree polyTree) {
        Paths paths = new Paths();
        for (PolyNode next : polyTree.getChilds()) {
            if (next.isOpen()) {
                paths.add(next.getPolygon());
            }
        }
        return paths;
    }

    public Paths() {
    }

    public Paths(int i) {
        super(i);
    }

    public void addPolyNode(PolyNode polyNode, PolyNode.NodeType nodeType) {
        boolean z = true;
        switch (nodeType) {
            case OPEN:
                return;
            case CLOSED:
                z = true ^ polyNode.isOpen();
                break;
        }
        if (polyNode.getPolygon().size() > 0 && z) {
            add(polyNode.getPolygon());
        }
        for (PolyNode addPolyNode : polyNode.getChilds()) {
            addPolyNode(addPolyNode, nodeType);
        }
    }

    public Paths cleanPolygons() {
        return cleanPolygons(1.415d);
    }

    public Paths cleanPolygons(double d) {
        Paths paths = new Paths(size());
        for (int i = 0; i < size(); i++) {
            paths.add(((Path) get(i)).cleanPolygon(d));
        }
        return paths;
    }

    public LongRect getBounds() {
        int size = size();
        LongRect longRect = new LongRect();
        int i = 0;
        while (i < size && ((Path) get(i)).isEmpty()) {
            i++;
        }
        if (i == size) {
            return longRect;
        }
        longRect.left = ((Point.LongPoint) ((Path) get(i)).get(0)).getX();
        longRect.right = longRect.left;
        longRect.top = ((Point.LongPoint) ((Path) get(i)).get(0)).getY();
        longRect.bottom = longRect.top;
        while (i < size) {
            for (int i2 = 0; i2 < ((Path) get(i)).size(); i2++) {
                if (((Point.LongPoint) ((Path) get(i)).get(i2)).getX() < longRect.left) {
                    longRect.left = ((Point.LongPoint) ((Path) get(i)).get(i2)).getX();
                } else if (((Point.LongPoint) ((Path) get(i)).get(i2)).getX() > longRect.right) {
                    longRect.right = ((Point.LongPoint) ((Path) get(i)).get(i2)).getX();
                }
                if (((Point.LongPoint) ((Path) get(i)).get(i2)).getY() < longRect.top) {
                    longRect.top = ((Point.LongPoint) ((Path) get(i)).get(i2)).getY();
                } else if (((Point.LongPoint) ((Path) get(i)).get(i2)).getY() > longRect.bottom) {
                    longRect.bottom = ((Point.LongPoint) ((Path) get(i)).get(i2)).getY();
                }
            }
            i++;
        }
        return longRect;
    }

    public void reversePaths() {
        Iterator it = iterator();
        while (it.hasNext()) {
            ((Path) it.next()).reverse();
        }
    }
}
