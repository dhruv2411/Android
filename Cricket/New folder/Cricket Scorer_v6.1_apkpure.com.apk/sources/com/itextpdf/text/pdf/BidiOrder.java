package com.itextpdf.text.pdf;

import com.itextpdf.text.error_messages.MessageLocalization;
import com.itextpdf.text.pdf.languages.DevanagariLigaturizer;
import com.itextpdf.text.pdf.languages.GujaratiLigaturizer;

public final class BidiOrder {
    public static final byte AL = 4;
    public static final byte AN = 11;
    public static final byte B = 15;
    public static final byte BN = 14;
    public static final byte CS = 12;
    public static final byte EN = 8;
    public static final byte ES = 9;
    public static final byte ET = 10;
    public static final byte L = 0;
    public static final byte LRE = 1;
    public static final byte LRO = 2;
    public static final byte NSM = 13;
    public static final byte ON = 18;
    public static final byte PDF = 7;
    public static final byte R = 3;
    public static final byte RLE = 5;
    public static final byte RLO = 6;
    public static final byte S = 16;
    public static final byte TYPE_MAX = 18;
    public static final byte TYPE_MIN = 0;
    public static final byte WS = 17;
    private static char[] baseTypes = {0, 8, 14, 9, 9, 16, 10, 10, 15, 11, 11, 16, 12, 12, 17, 13, 13, 15, 14, 27, 14, 28, 30, 15, 31, 31, 16, ' ', ' ', 17, '!', '\"', 18, '#', '%', 10, '&', '*', 18, '+', '+', 10, ',', ',', 12, '-', '-', 10, '.', '.', 12, '/', '/', 9, '0', '9', 8, ':', ':', 12, ';', '@', 18, 'A', 'Z', 0, '[', '`', 18, 'a', 'z', 0, '{', '~', 18, 127, 132, 14, 133, 133, 15, 134, 159, 14, 160, 160, 12, 161, 161, 18, 162, 165, 10, 166, 169, 18, 170, 170, 0, 171, 175, 18, 176, 177, 10, 178, 179, 8, 180, 180, 18, 181, 181, 0, 182, 184, 18, 185, 185, 8, 186, 186, 0, 187, 191, 18, 192, 214, 0, 215, 215, 18, 216, 246, 0, 247, 247, 18, 248, 696, 0, 697, 698, 18, 699, 705, 0, 706, 719, 18, 720, 721, 0, 722, 735, 18, 736, 740, 0, 741, 749, 18, 750, 750, 0, 751, 767, 18, 768, 855, 13, 856, 860, 0, 861, 879, 13, 880, 883, 0, 884, 885, 18, 886, 893, 0, 894, 894, 18, 895, 899, 0, 900, 901, 18, 902, 902, 0, 903, 903, 18, 904, 1013, 0, 1014, 1014, 18, 1015, 1154, 0, 1155, 1158, 13, 1159, 1159, 0, 1160, 1161, 13, 1162, 1417, 0, 1418, 1418, 18, 1419, 1424, 0, 1425, 1441, 13, 1442, 1442, 0, 1443, 1465, 13, 1466, 1466, 0, 1467, 1469, 13, 1470, 1470, 3, 1471, 1471, 13, 1472, 1472, 3, 1473, 1474, 13, 1475, 1475, 3, 1476, 1476, 13, 1477, 1487, 0, 1488, 1514, 3, 1515, 1519, 0, 1520, 1524, 3, 1525, 1535, 0, 1536, 1539, 4, 1540, 1547, 0, 1548, 1548, 12, 1549, 1549, 4, 1550, 1551, 18, 1552, 1557, 13, 1558, 1562, 0, 1563, 1563, 4, 1564, 1566, 0, 1567, 1567, 4, 1568, 1568, 0, 1569, 1594, 4, 1595, 1599, 0, 1600, 1610, 4, 1611, 1624, 13, 1625, 1631, 0, 1632, 1641, 11, 1642, 1642, 10, 1643, 1644, 11, 1645, 1647, 4, 1648, 1648, 13, 1649, 1749, 4, 1750, 1756, 13, 1757, 1757, 4, 1758, 1764, 13, 1765, 1766, 4, 1767, 1768, 13, 1769, 1769, 18, 1770, 1773, 13, 1774, 1775, 4, 1776, 1785, 8, 1786, 1805, 4, 1806, 1806, 0, 1807, 1807, 14, 1808, 1808, 4, 1809, 1809, 13, 1810, 1839, 4, 1840, 1866, 13, 1867, 1868, 0, 1869, 1871, 4, 1872, 1919, 0, 1920, 1957, 4, 1958, 1968, 13, 1969, 1969, 4, 1970, 2304, 0, 2305, 2306, 13, 2307, 2363, 0, 2364, 2364, 13, 2365, 2368, 0, 2369, DevanagariLigaturizer.DEVA_MATRA_AI, 13, 2377, 2380, 0, DevanagariLigaturizer.DEVA_HALANTA, DevanagariLigaturizer.DEVA_HALANTA, 13, 2382, 2384, 0, 2385, 2388, 13, 2389, 2401, 0, DevanagariLigaturizer.DEVA_MATRA_HLR, DevanagariLigaturizer.DEVA_MATRA_HLRR, 13, 2404, 2432, 0, 2433, 2433, 13, 2434, 2491, 0, 2492, 2492, 13, 2493, 2496, 0, 2497, 2500, 13, 2501, 2508, 0, 2509, 2509, 13, 2510, 2529, 0, 2530, 2531, 13, 2532, 2545, 0, 2546, 2547, 10, 2548, 2560, 0, 2561, 2562, 13, 2563, 2619, 0, 2620, 2620, 13, 2621, 2624, 0, 2625, 2626, 13, 2627, 2630, 0, 2631, 2632, 13, 2633, 2634, 0, 2635, 2637, 13, 2638, 2671, 0, 2672, 2673, 13, 2674, 2688, 0, 2689, 2690, 13, 2691, 2747, 0, 2748, 2748, 13, 2749, 2752, 0, 2753, 2757, 13, 2758, 2758, 0, GujaratiLigaturizer.GUJR_MATRA_E, GujaratiLigaturizer.GUJR_MATRA_AI, 13, 2761, 2764, 0, GujaratiLigaturizer.GUJR_HALANTA, GujaratiLigaturizer.GUJR_HALANTA, 13, 2766, 2785, 0, GujaratiLigaturizer.GUJR_MATRA_HLR, GujaratiLigaturizer.GUJR_MATRA_HLRR, 13, 2788, 2800, 0, 2801, 2801, 10, 2802, 2816, 0, 2817, 2817, 13, 2818, 2875, 0, 2876, 2876, 13, 2877, 2878, 0, 2879, 2879, 13, 2880, 2880, 0, 2881, 2883, 13, 2884, 2892, 0, 2893, 2893, 13, 2894, 2901, 0, 2902, 2902, 13, 2903, 2945, 0, 2946, 2946, 13, 2947, 3007, 0, 3008, 3008, 13, 3009, 3020, 0, 3021, 3021, 13, 3022, 3058, 0, 3059, 3064, 18, 3065, 3065, 10, 3066, 3066, 18, 3067, 3133, 0, 3134, 3136, 13, 3137, 3141, 0, 3142, 3144, 13, 3145, 3145, 0, 3146, 3149, 13, 3150, 3156, 0, 3157, 3158, 13, 3159, 3259, 0, 3260, 3260, 13, 3261, 3275, 0, 3276, 3277, 13, 3278, 3392, 0, 3393, 3395, 13, 3396, 3404, 0, 3405, 3405, 13, 3406, 3529, 0, 3530, 3530, 13, 3531, 3537, 0, 3538, 3540, 13, 3541, 3541, 0, 3542, 3542, 13, 3543, 3632, 0, 3633, 3633, 13, 3634, 3635, 0, 3636, 3642, 13, 3643, 3646, 0, 3647, 3647, 10, 3648, 3654, 0, 3655, 3662, 13, 3663, 3760, 0, 3761, 3761, 13, 3762, 3763, 0, 3764, 3769, 13, 3770, 3770, 0, 3771, 3772, 13, 3773, 3783, 0, 3784, 3789, 13, 3790, 3863, 0, 3864, 3865, 13, 3866, 3892, 0, 3893, 3893, 13, 3894, 3894, 0, 3895, 3895, 13, 3896, 3896, 0, 3897, 3897, 13, 3898, 3901, 18, 3902, 3952, 0, 3953, 3966, 13, 3967, 3967, 0, 3968, 3972, 13, 3973, 3973, 0, 3974, 3975, 13, 3976, 3983, 0, 3984, 3991, 13, 3992, 3992, 0, 3993, 4028, 13, 4029, 4037, 0, 4038, 4038, 13, 4039, 4140, 0, 4141, 4144, 13, 4145, 4145, 0, 4146, 4146, 13, 4147, 4149, 0, 4150, 4151, 13, 4152, 4152, 0, 4153, 4153, 13, 4154, 4183, 0, 4184, 4185, 13, 4186, 5759, 0, 5760, 5760, 17, 5761, 5786, 0, 5787, 5788, 18, 5789, 5905, 0, 5906, 5908, 13, 5909, 5937, 0, 5938, 5940, 13, 5941, 5969, 0, 5970, 5971, 13, 5972, 6001, 0, 6002, 6003, 13, 6004, 6070, 0, 6071, 6077, 13, 6078, 6085, 0, 6086, 6086, 13, 6087, 6088, 0, 6089, 6099, 13, 6100, 6106, 0, 6107, 6107, 10, 6108, 6108, 0, 6109, 6109, 13, 6110, 6127, 0, 6128, 6137, 18, 6138, 6143, 0, 6144, 6154, 18, 6155, 6157, 13, 6158, 6158, 17, 6159, 6312, 0, 6313, 6313, 13, 6314, 6431, 0, 6432, 6434, 13, 6435, 6438, 0, 6439, 6443, 13, 6444, 6449, 0, 6450, 6450, 13, 6451, 6456, 0, 6457, 6459, 13, 6460, 6463, 0, 6464, 6464, 18, 6465, 6467, 0, 6468, 6469, 18, 6470, 6623, 0, 6624, 6655, 18, 6656, 8124, 0, 8125, 8125, 18, 8126, 8126, 0, 8127, 8129, 18, 8130, 8140, 0, 8141, 8143, 18, 8144, 8156, 0, 8157, 8159, 18, 8160, 8172, 0, 8173, 8175, 18, 8176, 8188, 0, 8189, 8190, 18, 8191, 8191, 0, 8192, 8202, 17, 8203, 8205, 14, 8206, 8206, 0, 8207, 8207, 3, 8208, 8231, 18, 8232, 8232, 17, BaseFont.PARAGRAPH_SEPARATOR, BaseFont.PARAGRAPH_SEPARATOR, 15, 8234, 8234, 1, 8235, 8235, 5, 8236, 8236, 7, 8237, 8237, 2, 8238, 8238, 6, 8239, 8239, 17, 8240, 8244, 10, 8245, 8276, 18, 8277, 8278, 0, 8279, 8279, 18, 8280, 8286, 0, 8287, 8287, 17, 8288, 8291, 14, 8292, 8297, 0, 8298, 8303, 14, 8304, 8304, 8, 8305, 8307, 0, 8308, 8313, 8, 8314, 8315, 10, 8316, 8318, 18, 8319, 8319, 0, 8320, 8329, 8, 8330, 8331, 10, 8332, 8334, 18, 8335, 8351, 0, 8352, 8369, 10, 8370, 8399, 0, 8400, 8426, 13, 8427, 8447, 0, 8448, 8449, 18, 8450, 8450, 0, 8451, 8454, 18, 8455, 8455, 0, 8456, 8457, 18, 8458, 8467, 0, 8468, 8468, 18, 8469, 8469, 0, 8470, 8472, 18, 8473, 8477, 0, 8478, 8483, 18, 8484, 8484, 0, 8485, 8485, 18, 8486, 8486, 0, 8487, 8487, 18, 8488, 8488, 0, 8489, 8489, 18, 8490, 8493, 0, 8494, 8494, 10, 8495, 8497, 0, 8498, 8498, 18, 8499, 8505, 0, 8506, 8507, 18, 8508, 8511, 0, 8512, 8516, 18, 8517, 8521, 0, 8522, 8523, 18, 8524, 8530, 0, 8531, 8543, 18, 8544, 8591, 0, 8592, 8721, 18, 8722, 8723, 10, 8724, 9013, 18, 9014, 9082, 0, 9083, 9108, 18, 9109, 9109, 0, 9110, 9168, 18, 9169, 9215, 0, 9216, 9254, 18, 9255, 9279, 0, 9280, 9290, 18, 9291, 9311, 0, 9312, 9371, 8, 9372, 9449, 0, 9450, 9450, 8, 9451, 9751, 18, 9752, 9752, 0, 9753, 9853, 18, 9854, 9855, 0, 9856, 9873, 18, 9874, 9887, 0, 9888, 9889, 18, 9890, 9984, 0, 9985, 9988, 18, 9989, 9989, 0, 9990, 9993, 18, 9994, 9995, 0, 9996, 10023, 18, 10024, 10024, 0, 10025, 10059, 18, 10060, 10060, 0, 10061, 10061, 18, 10062, 10062, 0, 10063, 10066, 18, 10067, 10069, 0, 10070, 10070, 18, 10071, 10071, 0, 10072, 10078, 18, 10079, 10080, 0, 10081, 10132, 18, 10133, 10135, 0, 10136, 10159, 18, 10160, 10160, 0, 10161, 10174, 18, 10175, 10191, 0, 10192, 10219, 18, 10220, 10223, 0, 10224, 11021, 18, 11022, 11903, 0, 11904, 11929, 18, 11930, 11930, 0, 11931, 12019, 18, 12020, 12031, 0, 12032, 12245, 18, 12246, 12271, 0, 12272, 12283, 18, 12284, 12287, 0, 12288, 12288, 17, 12289, 12292, 18, 12293, 12295, 0, 12296, 12320, 18, 12321, 12329, 0, 12330, 12335, 13, 12336, 12336, 18, 12337, 12341, 0, 12342, 12343, 18, 12344, 12348, 0, 12349, 12351, 18, 12352, 12440, 0, 12441, 12442, 13, 12443, 12444, 18, 12445, 12447, 0, 12448, 12448, 18, 12449, 12538, 0, 12539, 12539, 18, 12540, 12828, 0, 12829, 12830, 18, 12831, 12879, 0, 12880, 12895, 18, 12896, 12923, 0, 12924, 12925, 18, 12926, 12976, 0, 12977, 12991, 18, 12992, 13003, 0, 13004, 13007, 18, 13008, 13174, 0, 13175, 13178, 18, 13179, 13277, 0, 13278, 13279, 18, 13280, 13310, 0, 13311, 13311, 18, 13312, 19903, 0, 19904, 19967, 18, 19968, 42127, 0, 42128, 42182, 18, 42183, 64284, 0, 64285, 64285, 3, 64286, 64286, 13, 64287, 64296, 3, 64297, 64297, 10, 64298, 64310, 3, 64311, 64311, 0, 64312, 64316, 3, 64317, 64317, 0, 64318, 64318, 3, 64319, 64319, 0, 64320, 64321, 3, 64322, 64322, 0, 64323, 64324, 3, 64325, 64325, 0, 64326, 64335, 3, 64336, 64433, 4, 64434, 64466, 0, 64467, 64829, 4, 64830, 64831, 18, 64832, 64847, 0, 64848, 64911, 4, 64912, 64913, 0, 64914, 64967, 4, 64968, 65007, 0, 65008, 65020, 4, 65021, 65021, 18, 65022, 65023, 0, 65024, 65039, 13, 65040, 65055, 0, 65056, 65059, 13, 65060, 65071, 0, 65072, 65103, 18, 65104, 65104, 12, 65105, 65105, 18, 65106, 65106, 12, 65107, 65107, 0, 65108, 65108, 18, 65109, 65109, 12, 65110, 65118, 18, 65119, 65119, 10, 65120, 65121, 18, 65122, 65123, 10, 65124, 65126, 18, 65127, 65127, 0, 65128, 65128, 18, 65129, 65130, 10, 65131, 65131, 18, 65132, 65135, 0, 65136, 65140, 4, 65141, 65141, 0, 65142, 65276, 4, 65277, 65278, 0, 65279, 65279, 14, 65280, 65280, 0, 65281, 65282, 18, 65283, 65285, 10, 65286, 65290, 18, 65291, 65291, 10, 65292, 65292, 12, 65293, 65293, 10, 65294, 65294, 12, 65295, 65295, 9, 65296, 65305, 8, 65306, 65306, 12, 65307, 65312, 18, 65313, 65338, 0, 65339, 65344, 18, 65345, 65370, 0, 65371, 65381, 18, 65382, 65503, 0, 65504, 65505, 10, 65506, 65508, 18, 65509, 65510, 10, 65511, 65511, 0, 65512, 65518, 18, 65519, 65528, 0, 65529, 65531, 14, 65532, 65533, 18, 65534, 65535, 0};
    private static final byte[] rtypes = new byte[65536];
    private byte[] embeddings;
    private byte[] initialTypes;
    private byte paragraphEmbeddingLevel = -1;
    private byte[] resultLevels;
    private byte[] resultTypes;
    private int textLength;

    private static boolean isWhitespace(byte b) {
        if (b == 14 || b == 17) {
            return true;
        }
        switch (b) {
            case 1:
            case 2:
                return true;
            default:
                switch (b) {
                    case 5:
                    case 6:
                    case 7:
                        return true;
                    default:
                        return false;
                }
        }
    }

    private static byte typeForLevel(int i) {
        return (i & 1) == 0 ? (byte) 0 : 3;
    }

    public BidiOrder(byte[] bArr) {
        validateTypes(bArr);
        this.initialTypes = (byte[]) bArr.clone();
        runAlgorithm();
    }

    public BidiOrder(byte[] bArr, byte b) {
        validateTypes(bArr);
        validateParagraphEmbeddingLevel(b);
        this.initialTypes = (byte[]) bArr.clone();
        this.paragraphEmbeddingLevel = b;
        runAlgorithm();
    }

    public BidiOrder(char[] cArr, int i, int i2, byte b) {
        this.initialTypes = new byte[i2];
        for (int i3 = 0; i3 < i2; i3++) {
            this.initialTypes[i3] = rtypes[cArr[i + i3]];
        }
        validateParagraphEmbeddingLevel(b);
        this.paragraphEmbeddingLevel = b;
        runAlgorithm();
    }

    public static final byte getDirection(char c) {
        return rtypes[c];
    }

    private void runAlgorithm() {
        int i;
        this.textLength = this.initialTypes.length;
        this.resultTypes = (byte[]) this.initialTypes.clone();
        if (this.paragraphEmbeddingLevel == -1) {
            determineParagraphEmbeddingLevel();
        }
        this.resultLevels = new byte[this.textLength];
        setLevels(0, this.textLength, this.paragraphEmbeddingLevel);
        determineExplicitEmbeddingLevels();
        this.textLength = removeExplicitCodes();
        byte b = this.paragraphEmbeddingLevel;
        for (int i2 = 0; i2 < this.textLength; i2 = i) {
            byte b2 = this.resultLevels[i2];
            byte typeForLevel = typeForLevel(Math.max(b, b2));
            i = i2 + 1;
            while (i < this.textLength && this.resultLevels[i] == b2) {
                i++;
            }
            int i3 = i2;
            int i4 = i;
            byte b3 = b2;
            byte b4 = typeForLevel;
            byte typeForLevel2 = typeForLevel(Math.max(i < this.textLength ? this.resultLevels[i] : this.paragraphEmbeddingLevel, b2));
            resolveWeakTypes(i3, i4, b3, b4, typeForLevel2);
            resolveNeutralTypes(i3, i4, b3, b4, typeForLevel2);
            resolveImplicitLevels(i3, i4, b3, b4, typeForLevel2);
            b = b2;
        }
        this.textLength = reinsertExplicitCodes(this.textLength);
    }

    private void determineParagraphEmbeddingLevel() {
        byte b;
        int i = 0;
        while (true) {
            if (i >= this.textLength) {
                b = -1;
                break;
            }
            b = this.resultTypes[i];
            if (b == 0 || b == 4 || b == 3) {
                break;
            }
            i++;
        }
        if (b == -1) {
            this.paragraphEmbeddingLevel = 0;
        } else if (b == 0) {
            this.paragraphEmbeddingLevel = 0;
        } else {
            this.paragraphEmbeddingLevel = 1;
        }
    }

    private void determineExplicitEmbeddingLevels() {
        this.embeddings = processEmbeddings(this.resultTypes, this.paragraphEmbeddingLevel);
        for (int i = 0; i < this.textLength; i++) {
            byte b = this.embeddings[i];
            if ((b & 128) != 0) {
                b = (byte) (b & Byte.MAX_VALUE);
                this.resultTypes[i] = typeForLevel(b);
            }
            this.resultLevels[i] = b;
        }
    }

    private int removeExplicitCodes() {
        int i = 0;
        for (int i2 = 0; i2 < this.textLength; i2++) {
            byte b = this.initialTypes[i2];
            if (!(b == 1 || b == 5 || b == 2 || b == 6 || b == 7 || b == 14)) {
                this.embeddings[i] = this.embeddings[i2];
                this.resultTypes[i] = this.resultTypes[i2];
                this.resultLevels[i] = this.resultLevels[i2];
                i++;
            }
        }
        return i;
    }

    private int reinsertExplicitCodes(int i) {
        int i2;
        int length = this.initialTypes.length;
        while (true) {
            length--;
            if (length < 0) {
                break;
            }
            byte b = this.initialTypes[length];
            if (b == 1 || b == 5 || b == 2 || b == 6 || b == 7 || b == 14) {
                this.embeddings[length] = 0;
                this.resultTypes[length] = b;
                this.resultLevels[length] = -1;
            } else {
                i--;
                this.embeddings[length] = this.embeddings[i];
                this.resultTypes[length] = this.resultTypes[i];
                this.resultLevels[length] = this.resultLevels[i];
            }
        }
        if (this.resultLevels[0] == -1) {
            this.resultLevels[0] = this.paragraphEmbeddingLevel;
        }
        for (i2 = 1; i2 < this.initialTypes.length; i2++) {
            if (this.resultLevels[i2] == -1) {
                this.resultLevels[i2] = this.resultLevels[i2 - 1];
            }
        }
        return this.initialTypes.length;
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x003a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static byte[] processEmbeddings(byte[] r14, byte r15) {
        /*
            r0 = 62
            r1 = 0
            int r2 = r14.length
            byte[] r3 = new byte[r2]
            byte[] r4 = new byte[r0]
            r6 = r15
            r9 = r6
            r5 = r1
            r7 = r5
            r8 = r7
            r10 = r8
        L_0x000e:
            if (r5 >= r2) goto L_0x0075
            r3[r5] = r6
            byte r11 = r14[r5]
            r12 = 15
            if (r11 == r12) goto L_0x006b
            switch(r11) {
                case 1: goto L_0x0038;
                case 2: goto L_0x0038;
                default: goto L_0x001b;
            }
        L_0x001b:
            switch(r11) {
                case 5: goto L_0x0038;
                case 6: goto L_0x0038;
                case 7: goto L_0x0020;
                default: goto L_0x001e;
            }
        L_0x001e:
            goto L_0x0072
        L_0x0020:
            if (r7 <= 0) goto L_0x0025
            int r7 = r7 + -1
            goto L_0x0072
        L_0x0025:
            if (r8 <= 0) goto L_0x002e
            r11 = 61
            if (r9 == r11) goto L_0x002e
            int r8 = r8 + -1
            goto L_0x0072
        L_0x002e:
            if (r10 <= 0) goto L_0x0072
            int r10 = r10 + -1
            byte r6 = r4[r10]
            r9 = r6 & 127(0x7f, float:1.78E-43)
            byte r9 = (byte) r9
            goto L_0x0072
        L_0x0038:
            if (r7 != 0) goto L_0x0068
            r12 = 5
            r13 = 6
            if (r11 == r12) goto L_0x0047
            if (r11 != r13) goto L_0x0041
            goto L_0x0047
        L_0x0041:
            int r12 = r9 + 2
            r12 = r12 & -2
            byte r12 = (byte) r12
            goto L_0x004c
        L_0x0047:
            int r12 = r9 + 1
            r12 = r12 | 1
            byte r12 = (byte) r12
        L_0x004c:
            if (r12 >= r0) goto L_0x0061
            r4[r10] = r6
            int r10 = r10 + 1
            r6 = 2
            if (r11 == r6) goto L_0x005a
            if (r11 != r13) goto L_0x0058
            goto L_0x005a
        L_0x0058:
            r6 = r12
            goto L_0x005d
        L_0x005a:
            r6 = r12 | 128(0x80, float:1.794E-43)
            byte r6 = (byte) r6
        L_0x005d:
            r3[r5] = r6
            r9 = r12
            goto L_0x0072
        L_0x0061:
            r11 = 60
            if (r9 != r11) goto L_0x0068
            int r8 = r8 + 1
            goto L_0x0072
        L_0x0068:
            int r7 = r7 + 1
            goto L_0x0072
        L_0x006b:
            r3[r5] = r15
            r6 = r15
            r9 = r6
            r7 = r1
            r8 = r7
            r10 = r8
        L_0x0072:
            int r5 = r5 + 1
            goto L_0x000e
        L_0x0075:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.itextpdf.text.pdf.BidiOrder.processEmbeddings(byte[], byte):byte[]");
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x0038  */
    /* JADX WARNING: Removed duplicated region for block: B:92:0x003c A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void resolveWeakTypes(int r9, int r10, byte r11, byte r12, byte r13) {
        /*
            r8 = this;
            r11 = r9
            r0 = r12
        L_0x0002:
            if (r11 >= r10) goto L_0x0015
            byte[] r1 = r8.resultTypes
            byte r1 = r1[r11]
            r2 = 13
            if (r1 != r2) goto L_0x0011
            byte[] r1 = r8.resultTypes
            r1[r11] = r0
            goto L_0x0012
        L_0x0011:
            r0 = r1
        L_0x0012:
            int r11 = r11 + 1
            goto L_0x0002
        L_0x0015:
            r11 = r9
        L_0x0016:
            r0 = 4
            r1 = 3
            r2 = 11
            r3 = 8
            if (r11 >= r10) goto L_0x003f
            byte[] r4 = r8.resultTypes
            byte r4 = r4[r11]
            if (r4 != r3) goto L_0x003c
            int r3 = r11 + -1
        L_0x0026:
            if (r3 < r9) goto L_0x003c
            byte[] r4 = r8.resultTypes
            byte r4 = r4[r3]
            if (r4 == 0) goto L_0x0036
            if (r4 == r1) goto L_0x0036
            if (r4 != r0) goto L_0x0033
            goto L_0x0036
        L_0x0033:
            int r3 = r3 + -1
            goto L_0x0026
        L_0x0036:
            if (r4 != r0) goto L_0x003c
            byte[] r0 = r8.resultTypes
            r0[r11] = r2
        L_0x003c:
            int r11 = r11 + 1
            goto L_0x0016
        L_0x003f:
            r11 = r9
        L_0x0040:
            if (r11 >= r10) goto L_0x004f
            byte[] r4 = r8.resultTypes
            byte r4 = r4[r11]
            if (r4 != r0) goto L_0x004c
            byte[] r4 = r8.resultTypes
            r4[r11] = r1
        L_0x004c:
            int r11 = r11 + 1
            goto L_0x0040
        L_0x004f:
            int r11 = r9 + 1
        L_0x0051:
            int r0 = r10 + -1
            r4 = 9
            r5 = 12
            if (r11 >= r0) goto L_0x008b
            byte[] r0 = r8.resultTypes
            byte r0 = r0[r11]
            if (r0 == r4) goto L_0x0065
            byte[] r0 = r8.resultTypes
            byte r0 = r0[r11]
            if (r0 != r5) goto L_0x0088
        L_0x0065:
            byte[] r0 = r8.resultTypes
            int r4 = r11 + -1
            byte r0 = r0[r4]
            byte[] r4 = r8.resultTypes
            int r6 = r11 + 1
            byte r4 = r4[r6]
            if (r0 != r3) goto L_0x007a
            if (r4 != r3) goto L_0x007a
            byte[] r0 = r8.resultTypes
            r0[r11] = r3
            goto L_0x0088
        L_0x007a:
            byte[] r6 = r8.resultTypes
            byte r6 = r6[r11]
            if (r6 != r5) goto L_0x0088
            if (r0 != r2) goto L_0x0088
            if (r4 != r2) goto L_0x0088
            byte[] r0 = r8.resultTypes
            r0[r11] = r2
        L_0x0088:
            int r11 = r11 + 1
            goto L_0x0051
        L_0x008b:
            r11 = r9
        L_0x008c:
            r0 = 0
            r2 = 10
            if (r11 >= r10) goto L_0x00bc
            byte[] r6 = r8.resultTypes
            byte r6 = r6[r11]
            r7 = 1
            if (r6 != r2) goto L_0x00ba
            byte[] r6 = new byte[r7]
            r6[r0] = r2
            int r0 = r8.findRunLimit(r11, r10, r6)
            if (r11 != r9) goto L_0x00a4
            r2 = r12
            goto L_0x00aa
        L_0x00a4:
            byte[] r2 = r8.resultTypes
            int r6 = r11 + -1
            byte r2 = r2[r6]
        L_0x00aa:
            if (r2 == r3) goto L_0x00b4
            if (r0 != r10) goto L_0x00b0
            r2 = r13
            goto L_0x00b4
        L_0x00b0:
            byte[] r2 = r8.resultTypes
            byte r2 = r2[r0]
        L_0x00b4:
            if (r2 != r3) goto L_0x00b9
            r8.setTypes(r11, r0, r3)
        L_0x00b9:
            r11 = r0
        L_0x00ba:
            int r11 = r11 + r7
            goto L_0x008c
        L_0x00bc:
            r11 = r9
        L_0x00bd:
            if (r11 >= r10) goto L_0x00d2
            byte[] r13 = r8.resultTypes
            byte r13 = r13[r11]
            if (r13 == r4) goto L_0x00c9
            if (r13 == r2) goto L_0x00c9
            if (r13 != r5) goto L_0x00cf
        L_0x00c9:
            byte[] r13 = r8.resultTypes
            r6 = 18
            r13[r11] = r6
        L_0x00cf:
            int r11 = r11 + 1
            goto L_0x00bd
        L_0x00d2:
            r11 = r9
        L_0x00d3:
            if (r11 >= r10) goto L_0x00f5
            byte[] r13 = r8.resultTypes
            byte r13 = r13[r11]
            if (r13 != r3) goto L_0x00f2
            int r13 = r11 + -1
        L_0x00dd:
            if (r13 < r9) goto L_0x00eb
            byte[] r2 = r8.resultTypes
            byte r2 = r2[r13]
            if (r2 == 0) goto L_0x00ec
            if (r2 != r1) goto L_0x00e8
            goto L_0x00ec
        L_0x00e8:
            int r13 = r13 + -1
            goto L_0x00dd
        L_0x00eb:
            r2 = r12
        L_0x00ec:
            if (r2 != 0) goto L_0x00f2
            byte[] r13 = r8.resultTypes
            r13[r11] = r0
        L_0x00f2:
            int r11 = r11 + 1
            goto L_0x00d3
        L_0x00f5:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.itextpdf.text.pdf.BidiOrder.resolveWeakTypes(int, int, byte, byte, byte):void");
    }

    private void resolveNeutralTypes(int i, int i2, byte b, byte b2, byte b3) {
        byte b4;
        int i3 = i;
        while (i3 < i2) {
            byte b5 = this.resultTypes[i3];
            if (b5 == 17 || b5 == 18 || b5 == 15 || b5 == 16) {
                int findRunLimit = findRunLimit(i3, i2, new byte[]{B, S, WS, 18});
                byte b6 = 3;
                if (i3 == i) {
                    b4 = b2;
                } else {
                    b4 = this.resultTypes[i3 - 1];
                    if (!(b4 == 0 || b4 == 3 || (b4 != 11 && b4 != 8))) {
                        b4 = 3;
                    }
                }
                if (findRunLimit == i2) {
                    b6 = b3;
                } else {
                    byte b7 = this.resultTypes[findRunLimit];
                    if (b7 == 0 || b7 == 3 || !(b7 == 11 || b7 == 8)) {
                        b6 = b7;
                    }
                }
                if (b4 != b6) {
                    b4 = typeForLevel(b);
                }
                setTypes(i3, findRunLimit, b4);
                i3 = findRunLimit;
            }
            i3++;
        }
    }

    private void resolveImplicitLevels(int i, int i2, byte b, byte b2, byte b3) {
        if ((b & 1) == 0) {
            while (i < i2) {
                byte b4 = this.resultTypes[i];
                if (b4 != 0) {
                    if (b4 == 3) {
                        byte[] bArr = this.resultLevels;
                        bArr[i] = (byte) (bArr[i] + 1);
                    } else {
                        byte[] bArr2 = this.resultLevels;
                        bArr2[i] = (byte) (bArr2[i] + 2);
                    }
                }
                i++;
            }
            return;
        }
        while (i < i2) {
            if (this.resultTypes[i] != 3) {
                byte[] bArr3 = this.resultLevels;
                bArr3[i] = (byte) (bArr3[i] + 1);
            }
            i++;
        }
    }

    public byte[] getLevels() {
        return getLevels(new int[]{this.textLength});
    }

    public byte[] getLevels(int[] iArr) {
        validateLineBreaks(iArr, this.textLength);
        byte[] bArr = (byte[]) this.resultLevels.clone();
        int i = 0;
        for (int i2 = 0; i2 < bArr.length; i2++) {
            byte b = this.initialTypes[i2];
            if (b == 15 || b == 16) {
                bArr[i2] = this.paragraphEmbeddingLevel;
                int i3 = i2 - 1;
                while (i3 >= 0 && isWhitespace(this.initialTypes[i3])) {
                    bArr[i3] = this.paragraphEmbeddingLevel;
                    i3--;
                }
            }
        }
        int i4 = 0;
        while (i < iArr.length) {
            int i5 = iArr[i];
            int i6 = i5 - 1;
            while (i6 >= i4 && isWhitespace(this.initialTypes[i6])) {
                bArr[i6] = this.paragraphEmbeddingLevel;
                i6--;
            }
            i++;
            i4 = i5;
        }
        return bArr;
    }

    public int[] getReordering(int[] iArr) {
        validateLineBreaks(iArr, this.textLength);
        return computeMultilineReordering(getLevels(iArr), iArr);
    }

    private static int[] computeMultilineReordering(byte[] bArr, int[] iArr) {
        int[] iArr2 = new int[bArr.length];
        int i = 0;
        int i2 = 0;
        while (i < iArr.length) {
            int i3 = iArr[i];
            byte[] bArr2 = new byte[(i3 - i2)];
            System.arraycopy(bArr, i2, bArr2, 0, bArr2.length);
            int[] computeReordering = computeReordering(bArr2);
            for (int i4 = 0; i4 < computeReordering.length; i4++) {
                iArr2[i2 + i4] = computeReordering[i4] + i2;
            }
            i++;
            i2 = i3;
        }
        return iArr2;
    }

    private static int[] computeReordering(byte[] bArr) {
        int[] iArr = new int[r1];
        for (int i = 0; i < r1; i++) {
            iArr[i] = i;
        }
        int i2 = 0;
        byte b = 63;
        for (byte b2 : bArr) {
            if (b2 > i2) {
                i2 = b2;
            }
            if ((b2 & 1) != 0 && b2 < b) {
                b = b2;
            }
        }
        while (i2 >= b) {
            int i3 = 0;
            while (i3 < r1) {
                if (bArr[i3] >= i2) {
                    int i4 = i3 + 1;
                    while (i4 < r1 && bArr[i4] >= i2) {
                        i4++;
                    }
                    for (int i5 = i4 - 1; i3 < i5; i5--) {
                        int i6 = iArr[i3];
                        iArr[i3] = iArr[i5];
                        iArr[i5] = i6;
                        i3++;
                    }
                    i3 = i4;
                }
                i3++;
            }
            i2--;
        }
        return iArr;
    }

    public byte getBaseLevel() {
        return this.paragraphEmbeddingLevel;
    }

    private int findRunLimit(int i, int i2, byte[] bArr) {
        int i3 = i - 1;
        while (true) {
            i3++;
            if (i3 >= i2) {
                return i2;
            }
            byte b = this.resultTypes[i3];
            int i4 = 0;
            while (true) {
                if (i4 >= bArr.length) {
                    return i3;
                }
                if (b != bArr[i4]) {
                    i4++;
                }
            }
        }
    }

    private int findRunStart(int i, byte[] bArr) {
        while (true) {
            i--;
            int i2 = 0;
            if (i < 0) {
                return 0;
            }
            byte b = this.resultTypes[i];
            while (true) {
                if (i2 >= bArr.length) {
                    return i + 1;
                }
                if (b != bArr[i2]) {
                    i2++;
                }
            }
        }
    }

    private void setTypes(int i, int i2, byte b) {
        while (i < i2) {
            this.resultTypes[i] = b;
            i++;
        }
    }

    private void setLevels(int i, int i2, byte b) {
        while (i < i2) {
            this.resultLevels[i] = b;
            i++;
        }
    }

    private static void validateTypes(byte[] bArr) {
        if (bArr == null) {
            throw new IllegalArgumentException(MessageLocalization.getComposedMessage("types.is.null", new Object[0]));
        }
        for (int i = 0; i < bArr.length; i++) {
            if (bArr[i] < 0 || bArr[i] > 18) {
                throw new IllegalArgumentException(MessageLocalization.getComposedMessage("illegal.type.value.at.1.2", String.valueOf(i), String.valueOf(bArr[i])));
            }
        }
        for (int i2 = 0; i2 < bArr.length - 1; i2++) {
            if (bArr[i2] == 15) {
                throw new IllegalArgumentException(MessageLocalization.getComposedMessage("b.type.before.end.of.paragraph.at.index.1", i2));
            }
        }
    }

    private static void validateParagraphEmbeddingLevel(byte b) {
        if (b != -1 && b != 0 && b != 1) {
            throw new IllegalArgumentException(MessageLocalization.getComposedMessage("illegal.paragraph.embedding.level.1", (int) b));
        }
    }

    private static void validateLineBreaks(int[] iArr, int i) {
        int i2 = 0;
        int i3 = 0;
        while (i2 < iArr.length) {
            int i4 = iArr[i2];
            if (i4 <= i3) {
                throw new IllegalArgumentException(MessageLocalization.getComposedMessage("bad.linebreak.1.at.index.2", String.valueOf(i4), String.valueOf(i2)));
            } else {
                i2++;
                i3 = i4;
            }
        }
        if (i3 != i) {
            throw new IllegalArgumentException(MessageLocalization.getComposedMessage("last.linebreak.must.be.at.1", i));
        }
    }

    /* JADX WARNING: type inference failed for: r5v0, types: [int] */
    /* JADX WARNING: Multi-variable type inference failed */
    static {
        /*
            r0 = 65536(0x10000, float:9.18355E-41)
            byte[] r0 = new byte[r0]
            rtypes = r0
            r0 = 1725(0x6bd, float:2.417E-42)
            char[] r0 = new char[r0]
            r0 = {0, 8, 14, 9, 9, 16, 10, 10, 15, 11, 11, 16, 12, 12, 17, 13, 13, 15, 14, 27, 14, 28, 30, 15, 31, 31, 16, 32, 32, 17, 33, 34, 18, 35, 37, 10, 38, 42, 18, 43, 43, 10, 44, 44, 12, 45, 45, 10, 46, 46, 12, 47, 47, 9, 48, 57, 8, 58, 58, 12, 59, 64, 18, 65, 90, 0, 91, 96, 18, 97, 122, 0, 123, 126, 18, 127, 132, 14, 133, 133, 15, 134, 159, 14, 160, 160, 12, 161, 161, 18, 162, 165, 10, 166, 169, 18, 170, 170, 0, 171, 175, 18, 176, 177, 10, 178, 179, 8, 180, 180, 18, 181, 181, 0, 182, 184, 18, 185, 185, 8, 186, 186, 0, 187, 191, 18, 192, 214, 0, 215, 215, 18, 216, 246, 0, 247, 247, 18, 248, 696, 0, 697, 698, 18, 699, 705, 0, 706, 719, 18, 720, 721, 0, 722, 735, 18, 736, 740, 0, 741, 749, 18, 750, 750, 0, 751, 767, 18, 768, 855, 13, 856, 860, 0, 861, 879, 13, 880, 883, 0, 884, 885, 18, 886, 893, 0, 894, 894, 18, 895, 899, 0, 900, 901, 18, 902, 902, 0, 903, 903, 18, 904, 1013, 0, 1014, 1014, 18, 1015, 1154, 0, 1155, 1158, 13, 1159, 1159, 0, 1160, 1161, 13, 1162, 1417, 0, 1418, 1418, 18, 1419, 1424, 0, 1425, 1441, 13, 1442, 1442, 0, 1443, 1465, 13, 1466, 1466, 0, 1467, 1469, 13, 1470, 1470, 3, 1471, 1471, 13, 1472, 1472, 3, 1473, 1474, 13, 1475, 1475, 3, 1476, 1476, 13, 1477, 1487, 0, 1488, 1514, 3, 1515, 1519, 0, 1520, 1524, 3, 1525, 1535, 0, 1536, 1539, 4, 1540, 1547, 0, 1548, 1548, 12, 1549, 1549, 4, 1550, 1551, 18, 1552, 1557, 13, 1558, 1562, 0, 1563, 1563, 4, 1564, 1566, 0, 1567, 1567, 4, 1568, 1568, 0, 1569, 1594, 4, 1595, 1599, 0, 1600, 1610, 4, 1611, 1624, 13, 1625, 1631, 0, 1632, 1641, 11, 1642, 1642, 10, 1643, 1644, 11, 1645, 1647, 4, 1648, 1648, 13, 1649, 1749, 4, 1750, 1756, 13, 1757, 1757, 4, 1758, 1764, 13, 1765, 1766, 4, 1767, 1768, 13, 1769, 1769, 18, 1770, 1773, 13, 1774, 1775, 4, 1776, 1785, 8, 1786, 1805, 4, 1806, 1806, 0, 1807, 1807, 14, 1808, 1808, 4, 1809, 1809, 13, 1810, 1839, 4, 1840, 1866, 13, 1867, 1868, 0, 1869, 1871, 4, 1872, 1919, 0, 1920, 1957, 4, 1958, 1968, 13, 1969, 1969, 4, 1970, 2304, 0, 2305, 2306, 13, 2307, 2363, 0, 2364, 2364, 13, 2365, 2368, 0, 2369, 2376, 13, 2377, 2380, 0, 2381, 2381, 13, 2382, 2384, 0, 2385, 2388, 13, 2389, 2401, 0, 2402, 2403, 13, 2404, 2432, 0, 2433, 2433, 13, 2434, 2491, 0, 2492, 2492, 13, 2493, 2496, 0, 2497, 2500, 13, 2501, 2508, 0, 2509, 2509, 13, 2510, 2529, 0, 2530, 2531, 13, 2532, 2545, 0, 2546, 2547, 10, 2548, 2560, 0, 2561, 2562, 13, 2563, 2619, 0, 2620, 2620, 13, 2621, 2624, 0, 2625, 2626, 13, 2627, 2630, 0, 2631, 2632, 13, 2633, 2634, 0, 2635, 2637, 13, 2638, 2671, 0, 2672, 2673, 13, 2674, 2688, 0, 2689, 2690, 13, 2691, 2747, 0, 2748, 2748, 13, 2749, 2752, 0, 2753, 2757, 13, 2758, 2758, 0, 2759, 2760, 13, 2761, 2764, 0, 2765, 2765, 13, 2766, 2785, 0, 2786, 2787, 13, 2788, 2800, 0, 2801, 2801, 10, 2802, 2816, 0, 2817, 2817, 13, 2818, 2875, 0, 2876, 2876, 13, 2877, 2878, 0, 2879, 2879, 13, 2880, 2880, 0, 2881, 2883, 13, 2884, 2892, 0, 2893, 2893, 13, 2894, 2901, 0, 2902, 2902, 13, 2903, 2945, 0, 2946, 2946, 13, 2947, 3007, 0, 3008, 3008, 13, 3009, 3020, 0, 3021, 3021, 13, 3022, 3058, 0, 3059, 3064, 18, 3065, 3065, 10, 3066, 3066, 18, 3067, 3133, 0, 3134, 3136, 13, 3137, 3141, 0, 3142, 3144, 13, 3145, 3145, 0, 3146, 3149, 13, 3150, 3156, 0, 3157, 3158, 13, 3159, 3259, 0, 3260, 3260, 13, 3261, 3275, 0, 3276, 3277, 13, 3278, 3392, 0, 3393, 3395, 13, 3396, 3404, 0, 3405, 3405, 13, 3406, 3529, 0, 3530, 3530, 13, 3531, 3537, 0, 3538, 3540, 13, 3541, 3541, 0, 3542, 3542, 13, 3543, 3632, 0, 3633, 3633, 13, 3634, 3635, 0, 3636, 3642, 13, 3643, 3646, 0, 3647, 3647, 10, 3648, 3654, 0, 3655, 3662, 13, 3663, 3760, 0, 3761, 3761, 13, 3762, 3763, 0, 3764, 3769, 13, 3770, 3770, 0, 3771, 3772, 13, 3773, 3783, 0, 3784, 3789, 13, 3790, 3863, 0, 3864, 3865, 13, 3866, 3892, 0, 3893, 3893, 13, 3894, 3894, 0, 3895, 3895, 13, 3896, 3896, 0, 3897, 3897, 13, 3898, 3901, 18, 3902, 3952, 0, 3953, 3966, 13, 3967, 3967, 0, 3968, 3972, 13, 3973, 3973, 0, 3974, 3975, 13, 3976, 3983, 0, 3984, 3991, 13, 3992, 3992, 0, 3993, 4028, 13, 4029, 4037, 0, 4038, 4038, 13, 4039, 4140, 0, 4141, 4144, 13, 4145, 4145, 0, 4146, 4146, 13, 4147, 4149, 0, 4150, 4151, 13, 4152, 4152, 0, 4153, 4153, 13, 4154, 4183, 0, 4184, 4185, 13, 4186, 5759, 0, 5760, 5760, 17, 5761, 5786, 0, 5787, 5788, 18, 5789, 5905, 0, 5906, 5908, 13, 5909, 5937, 0, 5938, 5940, 13, 5941, 5969, 0, 5970, 5971, 13, 5972, 6001, 0, 6002, 6003, 13, 6004, 6070, 0, 6071, 6077, 13, 6078, 6085, 0, 6086, 6086, 13, 6087, 6088, 0, 6089, 6099, 13, 6100, 6106, 0, 6107, 6107, 10, 6108, 6108, 0, 6109, 6109, 13, 6110, 6127, 0, 6128, 6137, 18, 6138, 6143, 0, 6144, 6154, 18, 6155, 6157, 13, 6158, 6158, 17, 6159, 6312, 0, 6313, 6313, 13, 6314, 6431, 0, 6432, 6434, 13, 6435, 6438, 0, 6439, 6443, 13, 6444, 6449, 0, 6450, 6450, 13, 6451, 6456, 0, 6457, 6459, 13, 6460, 6463, 0, 6464, 6464, 18, 6465, 6467, 0, 6468, 6469, 18, 6470, 6623, 0, 6624, 6655, 18, 6656, 8124, 0, 8125, 8125, 18, 8126, 8126, 0, 8127, 8129, 18, 8130, 8140, 0, 8141, 8143, 18, 8144, 8156, 0, 8157, 8159, 18, 8160, 8172, 0, 8173, 8175, 18, 8176, 8188, 0, 8189, 8190, 18, 8191, 8191, 0, 8192, 8202, 17, 8203, 8205, 14, 8206, 8206, 0, 8207, 8207, 3, 8208, 8231, 18, 8232, 8232, 17, 8233, 8233, 15, 8234, 8234, 1, 8235, 8235, 5, 8236, 8236, 7, 8237, 8237, 2, 8238, 8238, 6, 8239, 8239, 17, 8240, 8244, 10, 8245, 8276, 18, 8277, 8278, 0, 8279, 8279, 18, 8280, 8286, 0, 8287, 8287, 17, 8288, 8291, 14, 8292, 8297, 0, 8298, 8303, 14, 8304, 8304, 8, 8305, 8307, 0, 8308, 8313, 8, 8314, 8315, 10, 8316, 8318, 18, 8319, 8319, 0, 8320, 8329, 8, 8330, 8331, 10, 8332, 8334, 18, 8335, 8351, 0, 8352, 8369, 10, 8370, 8399, 0, 8400, 8426, 13, 8427, 8447, 0, 8448, 8449, 18, 8450, 8450, 0, 8451, 8454, 18, 8455, 8455, 0, 8456, 8457, 18, 8458, 8467, 0, 8468, 8468, 18, 8469, 8469, 0, 8470, 8472, 18, 8473, 8477, 0, 8478, 8483, 18, 8484, 8484, 0, 8485, 8485, 18, 8486, 8486, 0, 8487, 8487, 18, 8488, 8488, 0, 8489, 8489, 18, 8490, 8493, 0, 8494, 8494, 10, 8495, 8497, 0, 8498, 8498, 18, 8499, 8505, 0, 8506, 8507, 18, 8508, 8511, 0, 8512, 8516, 18, 8517, 8521, 0, 8522, 8523, 18, 8524, 8530, 0, 8531, 8543, 18, 8544, 8591, 0, 8592, 8721, 18, 8722, 8723, 10, 8724, 9013, 18, 9014, 9082, 0, 9083, 9108, 18, 9109, 9109, 0, 9110, 9168, 18, 9169, 9215, 0, 9216, 9254, 18, 9255, 9279, 0, 9280, 9290, 18, 9291, 9311, 0, 9312, 9371, 8, 9372, 9449, 0, 9450, 9450, 8, 9451, 9751, 18, 9752, 9752, 0, 9753, 9853, 18, 9854, 9855, 0, 9856, 9873, 18, 9874, 9887, 0, 9888, 9889, 18, 9890, 9984, 0, 9985, 9988, 18, 9989, 9989, 0, 9990, 9993, 18, 9994, 9995, 0, 9996, 10023, 18, 10024, 10024, 0, 10025, 10059, 18, 10060, 10060, 0, 10061, 10061, 18, 10062, 10062, 0, 10063, 10066, 18, 10067, 10069, 0, 10070, 10070, 18, 10071, 10071, 0, 10072, 10078, 18, 10079, 10080, 0, 10081, 10132, 18, 10133, 10135, 0, 10136, 10159, 18, 10160, 10160, 0, 10161, 10174, 18, 10175, 10191, 0, 10192, 10219, 18, 10220, 10223, 0, 10224, 11021, 18, 11022, 11903, 0, 11904, 11929, 18, 11930, 11930, 0, 11931, 12019, 18, 12020, 12031, 0, 12032, 12245, 18, 12246, 12271, 0, 12272, 12283, 18, 12284, 12287, 0, 12288, 12288, 17, 12289, 12292, 18, 12293, 12295, 0, 12296, 12320, 18, 12321, 12329, 0, 12330, 12335, 13, 12336, 12336, 18, 12337, 12341, 0, 12342, 12343, 18, 12344, 12348, 0, 12349, 12351, 18, 12352, 12440, 0, 12441, 12442, 13, 12443, 12444, 18, 12445, 12447, 0, 12448, 12448, 18, 12449, 12538, 0, 12539, 12539, 18, 12540, 12828, 0, 12829, 12830, 18, 12831, 12879, 0, 12880, 12895, 18, 12896, 12923, 0, 12924, 12925, 18, 12926, 12976, 0, 12977, 12991, 18, 12992, 13003, 0, 13004, 13007, 18, 13008, 13174, 0, 13175, 13178, 18, 13179, 13277, 0, 13278, 13279, 18, 13280, 13310, 0, 13311, 13311, 18, 13312, 19903, 0, 19904, 19967, 18, 19968, -23409, 0, -23408, -23354, 18, -23353, -1252, 0, -1251, -1251, 3, -1250, -1250, 13, -1249, -1240, 3, -1239, -1239, 10, -1238, -1226, 3, -1225, -1225, 0, -1224, -1220, 3, -1219, -1219, 0, -1218, -1218, 3, -1217, -1217, 0, -1216, -1215, 3, -1214, -1214, 0, -1213, -1212, 3, -1211, -1211, 0, -1210, -1201, 3, -1200, -1103, 4, -1102, -1070, 0, -1069, -707, 4, -706, -705, 18, -704, -689, 0, -688, -625, 4, -624, -623, 0, -622, -569, 4, -568, -529, 0, -528, -516, 4, -515, -515, 18, -514, -513, 0, -512, -497, 13, -496, -481, 0, -480, -477, 13, -476, -465, 0, -464, -433, 18, -432, -432, 12, -431, -431, 18, -430, -430, 12, -429, -429, 0, -428, -428, 18, -427, -427, 12, -426, -418, 18, -417, -417, 10, -416, -415, 18, -414, -413, 10, -412, -410, 18, -409, -409, 0, -408, -408, 18, -407, -406, 10, -405, -405, 18, -404, -401, 0, -400, -396, 4, -395, -395, 0, -394, -260, 4, -259, -258, 0, -257, -257, 14, -256, -256, 0, -255, -254, 18, -253, -251, 10, -250, -246, 18, -245, -245, 10, -244, -244, 12, -243, -243, 10, -242, -242, 12, -241, -241, 9, -240, -231, 8, -230, -230, 12, -229, -224, 18, -223, -198, 0, -197, -192, 18, -191, -166, 0, -165, -155, 18, -154, -33, 0, -32, -31, 10, -30, -28, 18, -27, -26, 10, -25, -25, 0, -24, -18, 18, -17, -8, 0, -7, -5, 14, -4, -3, 18, -2, -1, 0} // fill-array
            baseTypes = r0
            r0 = 0
        L_0x0010:
            char[] r1 = baseTypes
            int r1 = r1.length
            if (r0 >= r1) goto L_0x0033
            char[] r1 = baseTypes
            char r1 = r1[r0]
            char[] r2 = baseTypes
            int r0 = r0 + 1
            char r2 = r2[r0]
            char[] r3 = baseTypes
            int r0 = r0 + 1
            char r3 = r3[r0]
            byte r3 = (byte) r3
        L_0x0026:
            if (r1 > r2) goto L_0x0030
            byte[] r4 = rtypes
            int r5 = r1 + 1
            r4[r1] = r3
            r1 = r5
            goto L_0x0026
        L_0x0030:
            int r0 = r0 + 1
            goto L_0x0010
        L_0x0033:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.itextpdf.text.pdf.BidiOrder.<clinit>():void");
    }
}
