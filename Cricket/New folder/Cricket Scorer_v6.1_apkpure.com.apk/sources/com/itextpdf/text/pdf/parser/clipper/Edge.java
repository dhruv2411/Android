package com.itextpdf.text.pdf.parser.clipper;

import com.itextpdf.text.pdf.parser.clipper.Clipper;
import com.itextpdf.text.pdf.parser.clipper.Point;
import java.math.BigInteger;
import java.util.logging.Logger;

class Edge {
    protected static final double HORIZONTAL = -3.4E38d;
    private static final Logger LOGGER = Logger.getLogger(Edge.class.getName());
    protected static final int SKIP = -2;
    protected static final int UNASSIGNED = -1;
    private final Point.LongPoint bot = new Point.LongPoint();
    private final Point.LongPoint current = new Point.LongPoint();
    private final Point.LongPoint delta = new Point.LongPoint();
    double deltaX;
    Edge next;
    Edge nextInAEL;
    Edge nextInLML;
    Edge nextInSEL;
    int outIdx;
    Clipper.PolyType polyTyp;
    Edge prev;
    Edge prevInAEL;
    Edge prevInSEL;
    Side side;
    private final Point.LongPoint top = new Point.LongPoint();
    int windCnt;
    int windCnt2;
    int windDelta;

    enum Side {
        LEFT,
        RIGHT
    }

    static boolean doesE2InsertBeforeE1(Edge edge, Edge edge2) {
        if (edge2.current.getX() == edge.current.getX()) {
            if (edge2.top.getY() > edge.top.getY()) {
                if (edge2.top.getX() < topX(edge, edge2.top.getY())) {
                    return true;
                }
                return false;
            } else if (edge.top.getX() > topX(edge2, edge.top.getY())) {
                return true;
            } else {
                return false;
            }
        } else if (edge2.current.getX() < edge.current.getX()) {
            return true;
        } else {
            return false;
        }
    }

    static boolean slopesEqual(Edge edge, Edge edge2, boolean z) {
        if (z) {
            return BigInteger.valueOf(edge.getDelta().getY()).multiply(BigInteger.valueOf(edge2.getDelta().getX())).equals(BigInteger.valueOf(edge.getDelta().getX()).multiply(BigInteger.valueOf(edge2.getDelta().getY())));
        }
        return edge.getDelta().getY() * edge2.getDelta().getX() == edge.getDelta().getX() * edge2.getDelta().getY();
    }

    static void swapPolyIndexes(Edge edge, Edge edge2) {
        int i = edge.outIdx;
        edge.outIdx = edge2.outIdx;
        edge2.outIdx = i;
    }

    static void swapSides(Edge edge, Edge edge2) {
        Side side2 = edge.side;
        edge.side = edge2.side;
        edge2.side = side2;
    }

    static long topX(Edge edge, long j) {
        if (j == edge.getTop().getY()) {
            return edge.getTop().getX();
        }
        return edge.getBot().getX() + Math.round(edge.deltaX * ((double) (j - edge.getBot().getY())));
    }

    public Edge findNextLocMin() {
        Edge edge = this;
        while (true) {
            if (!edge.bot.equals(edge.prev.bot) || edge.current.equals(edge.top)) {
                edge = edge.next;
            } else if (edge.deltaX != HORIZONTAL && edge.prev.deltaX != HORIZONTAL) {
                return edge;
            } else {
                while (edge.prev.deltaX == HORIZONTAL) {
                    edge = edge.prev;
                }
                Edge edge2 = edge;
                while (edge2.deltaX == HORIZONTAL) {
                    edge2 = edge2.next;
                }
                if (edge2.top.getY() != edge2.prev.bot.getY()) {
                    return edge.prev.bot.getX() < edge2.bot.getX() ? edge : edge2;
                }
                edge = edge2;
            }
        }
    }

    public Point.LongPoint getBot() {
        return this.bot;
    }

    public Point.LongPoint getCurrent() {
        return this.current;
    }

    public Point.LongPoint getDelta() {
        return this.delta;
    }

    public Edge getMaximaPair() {
        Edge edge;
        if (!this.next.top.equals(this.top) || this.next.nextInLML != null) {
            edge = (!this.prev.top.equals(this.top) || this.prev.nextInLML != null) ? null : this.prev;
        } else {
            edge = this.next;
        }
        if (edge == null || (edge.outIdx != -2 && (edge.nextInAEL != edge.prevInAEL || edge.isHorizontal()))) {
            return edge;
        }
        return null;
    }

    public Edge getNextInAEL(Clipper.Direction direction) {
        return direction == Clipper.Direction.LEFT_TO_RIGHT ? this.nextInAEL : this.prevInAEL;
    }

    public Point.LongPoint getTop() {
        return this.top;
    }

    public boolean isContributing(Clipper.PolyFillType polyFillType, Clipper.PolyFillType polyFillType2, Clipper.ClipType clipType) {
        LOGGER.entering(Edge.class.getName(), "isContributing");
        if (this.polyTyp == Clipper.PolyType.SUBJECT) {
            Clipper.PolyFillType polyFillType3 = polyFillType2;
            polyFillType2 = polyFillType;
            polyFillType = polyFillType3;
        }
        switch (polyFillType) {
            case EVEN_ODD:
                if (this.windDelta == 0 && this.windCnt != 1) {
                    return false;
                }
            case NON_ZERO:
                if (Math.abs(this.windCnt) != 1) {
                    return false;
                }
                break;
            case POSITIVE:
                if (this.windCnt != 1) {
                    return false;
                }
                break;
            default:
                if (this.windCnt != -1) {
                    return false;
                }
                break;
        }
        switch (clipType) {
            case INTERSECTION:
                switch (polyFillType2) {
                    case EVEN_ODD:
                    case NON_ZERO:
                        if (this.windCnt2 != 0) {
                            return true;
                        }
                        return false;
                    case POSITIVE:
                        if (this.windCnt2 > 0) {
                            return true;
                        }
                        return false;
                    default:
                        if (this.windCnt2 < 0) {
                            return true;
                        }
                        return false;
                }
            case UNION:
                switch (polyFillType2) {
                    case EVEN_ODD:
                    case NON_ZERO:
                        if (this.windCnt2 == 0) {
                            return true;
                        }
                        return false;
                    case POSITIVE:
                        if (this.windCnt2 <= 0) {
                            return true;
                        }
                        return false;
                    default:
                        if (this.windCnt2 >= 0) {
                            return true;
                        }
                        return false;
                }
            case DIFFERENCE:
                if (this.polyTyp == Clipper.PolyType.SUBJECT) {
                    switch (polyFillType2) {
                        case EVEN_ODD:
                        case NON_ZERO:
                            if (this.windCnt2 == 0) {
                                return true;
                            }
                            return false;
                        case POSITIVE:
                            if (this.windCnt2 <= 0) {
                                return true;
                            }
                            return false;
                        default:
                            if (this.windCnt2 >= 0) {
                                return true;
                            }
                            return false;
                    }
                } else {
                    switch (polyFillType2) {
                        case EVEN_ODD:
                        case NON_ZERO:
                            if (this.windCnt2 != 0) {
                                return true;
                            }
                            return false;
                        case POSITIVE:
                            if (this.windCnt2 > 0) {
                                return true;
                            }
                            return false;
                        default:
                            if (this.windCnt2 < 0) {
                                return true;
                            }
                            return false;
                    }
                }
            case XOR:
                if (this.windDelta != 0) {
                    return true;
                }
                switch (polyFillType2) {
                    case EVEN_ODD:
                    case NON_ZERO:
                        if (this.windCnt2 == 0) {
                            return true;
                        }
                        return false;
                    case POSITIVE:
                        if (this.windCnt2 <= 0) {
                            return true;
                        }
                        return false;
                    default:
                        if (this.windCnt2 >= 0) {
                            return true;
                        }
                        return false;
                }
            default:
                return true;
        }
    }

    public boolean isEvenOddAltFillType(Clipper.PolyFillType polyFillType, Clipper.PolyFillType polyFillType2) {
        if (this.polyTyp == Clipper.PolyType.SUBJECT) {
            if (polyFillType == Clipper.PolyFillType.EVEN_ODD) {
                return true;
            }
            return false;
        } else if (polyFillType2 == Clipper.PolyFillType.EVEN_ODD) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isEvenOddFillType(Clipper.PolyFillType polyFillType, Clipper.PolyFillType polyFillType2) {
        if (this.polyTyp == Clipper.PolyType.SUBJECT) {
            if (polyFillType2 == Clipper.PolyFillType.EVEN_ODD) {
                return true;
            }
            return false;
        } else if (polyFillType == Clipper.PolyFillType.EVEN_ODD) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isHorizontal() {
        return this.delta.getY() == 0;
    }

    public boolean isIntermediate(double d) {
        return ((double) this.top.getY()) == d && this.nextInLML != null;
    }

    public boolean isMaxima(double d) {
        return ((double) this.top.getY()) == d && this.nextInLML == null;
    }

    public void reverseHorizontal() {
        long x = this.top.getX();
        this.top.setX(Long.valueOf(this.bot.getX()));
        this.bot.setX(Long.valueOf(x));
        long z = this.top.getZ();
        this.top.setZ(Long.valueOf(this.bot.getZ()));
        this.bot.setZ(Long.valueOf(z));
    }

    public void setBot(Point.LongPoint longPoint) {
        this.bot.set(longPoint);
    }

    public void setCurrent(Point.LongPoint longPoint) {
        this.current.set(longPoint);
    }

    public void setTop(Point.LongPoint longPoint) {
        this.top.set(longPoint);
    }

    public String toString() {
        return "TEdge [Bot=" + this.bot + ", Curr=" + this.current + ", Top=" + this.top + ", Delta=" + this.delta + ", Dx=" + this.deltaX + ", PolyTyp=" + this.polyTyp + ", Side=" + this.side + ", WindDelta=" + this.windDelta + ", WindCnt=" + this.windCnt + ", WindCnt2=" + this.windCnt2 + ", OutIdx=" + this.outIdx + ", Next=" + this.next + ", Prev=" + this.prev + ", NextInLML=" + this.nextInLML + ", NextInAEL=" + this.nextInAEL + ", PrevInAEL=" + this.prevInAEL + ", NextInSEL=" + this.nextInSEL + ", PrevInSEL=" + this.prevInSEL + "]";
    }

    public void updateDeltaX() {
        this.delta.setX(Long.valueOf(this.top.getX() - this.bot.getX()));
        this.delta.setY(Long.valueOf(this.top.getY() - this.bot.getY()));
        if (this.delta.getY() == 0) {
            this.deltaX = HORIZONTAL;
        } else {
            this.deltaX = ((double) this.delta.getX()) / ((double) this.delta.getY());
        }
    }
}
