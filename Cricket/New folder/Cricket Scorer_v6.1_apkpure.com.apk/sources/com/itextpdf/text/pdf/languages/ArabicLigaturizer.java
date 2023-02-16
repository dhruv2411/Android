package com.itextpdf.text.pdf.languages;

import com.itextpdf.text.pdf.BidiLine;
import com.itextpdf.text.pdf.BidiOrder;
import java.util.HashMap;

public class ArabicLigaturizer implements LanguageProcessor {
    private static final char ALEF = 'ا';
    private static final char ALEFHAMZA = 'أ';
    private static final char ALEFHAMZABELOW = 'إ';
    private static final char ALEFMADDA = 'آ';
    private static final char ALEFMAKSURA = 'ى';
    private static final char DAMMA = 'ُ';
    public static final int DIGITS_AN2EN = 64;
    public static final int DIGITS_EN2AN = 32;
    public static final int DIGITS_EN2AN_INIT_AL = 128;
    public static final int DIGITS_EN2AN_INIT_LR = 96;
    public static final int DIGITS_MASK = 224;
    private static final int DIGITS_RESERVED = 160;
    public static final int DIGIT_TYPE_AN = 0;
    public static final int DIGIT_TYPE_AN_EXTENDED = 256;
    public static final int DIGIT_TYPE_MASK = 256;
    private static final char FARSIYEH = 'ی';
    private static final char FATHA = 'َ';
    private static final char HAMZA = 'ء';
    private static final char HAMZAABOVE = 'ٔ';
    private static final char HAMZABELOW = 'ٕ';
    private static final char KASRA = 'ِ';
    private static final char LAM = 'ل';
    private static final char LAM_ALEF = 'ﻻ';
    private static final char LAM_ALEFHAMZA = 'ﻷ';
    private static final char LAM_ALEFHAMZABELOW = 'ﻹ';
    private static final char LAM_ALEFMADDA = 'ﻵ';
    private static final char MADDA = 'ٓ';
    private static final char SHADDA = 'ّ';
    private static final char TATWEEL = 'ـ';
    private static final char WAW = 'و';
    private static final char WAWHAMZA = 'ؤ';
    private static final char YEH = 'ي';
    private static final char YEHHAMZA = 'ئ';
    private static final char ZWJ = '‍';
    public static final int ar_composedtashkeel = 4;
    public static final int ar_lig = 8;
    public static final int ar_nothing = 0;
    public static final int ar_novowel = 1;
    private static final char[][] chartable = {new char[]{HAMZA, 65152}, new char[]{ALEFMADDA, 65153, 65154}, new char[]{ALEFHAMZA, 65155, 65156}, new char[]{WAWHAMZA, 65157, 65158}, new char[]{ALEFHAMZABELOW, 65159, 65160}, new char[]{YEHHAMZA, 65161, 65162, 65163, 65164}, new char[]{ALEF, 65165, 65166}, new char[]{1576, 65167, 65168, 65169, 65170}, new char[]{1577, 65171, 65172}, new char[]{1578, 65173, 65174, 65175, 65176}, new char[]{1579, 65177, 65178, 65179, 65180}, new char[]{1580, 65181, 65182, 65183, 65184}, new char[]{1581, 65185, 65186, 65187, 65188}, new char[]{1582, 65189, 65190, 65191, 65192}, new char[]{1583, 65193, 65194}, new char[]{1584, 65195, 65196}, new char[]{1585, 65197, 65198}, new char[]{1586, 65199, 65200}, new char[]{1587, 65201, 65202, 65203, 65204}, new char[]{1588, 65205, 65206, 65207, 65208}, new char[]{1589, 65209, 65210, 65211, 65212}, new char[]{1590, 65213, 65214, 65215, 65216}, new char[]{1591, 65217, 65218, 65219, 65220}, new char[]{1592, 65221, 65222, 65223, 65224}, new char[]{1593, 65225, 65226, 65227, 65228}, new char[]{1594, 65229, 65230, 65231, 65232}, new char[]{TATWEEL, TATWEEL, TATWEEL, TATWEEL, TATWEEL}, new char[]{1601, 65233, 65234, 65235, 65236}, new char[]{1602, 65237, 65238, 65239, 65240}, new char[]{1603, 65241, 65242, 65243, 65244}, new char[]{LAM, 65245, 65246, 65247, 65248}, new char[]{1605, 65249, 65250, 65251, 65252}, new char[]{1606, 65253, 65254, 65255, 65256}, new char[]{1607, 65257, 65258, 65259, 65260}, new char[]{WAW, 65261, 65262}, new char[]{ALEFMAKSURA, 65263, 65264, 64488, 64489}, new char[]{YEH, 65265, 65266, 65267, 65268}, new char[]{1649, 64336, 64337}, new char[]{1657, 64358, 64359, 64360, 64361}, new char[]{1658, 64350, 64351, 64352, 64353}, new char[]{1659, 64338, 64339, 64340, 64341}, new char[]{1662, 64342, 64343, 64344, 64345}, new char[]{1663, 64354, 64355, 64356, 64357}, new char[]{1664, 64346, 64347, 64348, 64349}, new char[]{1667, 64374, 64375, 64376, 64377}, new char[]{1668, 64370, 64371, 64372, 64373}, new char[]{1670, 64378, 64379, 64380, 64381}, new char[]{1671, 64382, 64383, 64384, 64385}, new char[]{1672, 64392, 64393}, new char[]{1676, 64388, 64389}, new char[]{1677, 64386, 64387}, new char[]{1678, 64390, 64391}, new char[]{1681, 64396, 64397}, new char[]{1688, 64394, 64395}, new char[]{1700, 64362, 64363, 64364, 64365}, new char[]{1702, 64366, 64367, 64368, 64369}, new char[]{1705, 64398, 64399, 64400, 64401}, new char[]{1709, 64467, 64468, 64469, 64470}, new char[]{1711, 64402, 64403, 64404, 64405}, new char[]{1713, 64410, 64411, 64412, 64413}, new char[]{1715, 64406, 64407, 64408, 64409}, new char[]{1722, 64414, 64415}, new char[]{1723, 64416, 64417, 64418, 64419}, new char[]{1726, 64426, 64427, 64428, 64429}, new char[]{1728, 64420, 64421}, new char[]{1729, 64422, 64423, 64424, 64425}, new char[]{1733, 64480, 64481}, new char[]{1734, 64473, 64474}, new char[]{1735, 64471, 64472}, new char[]{1736, 64475, 64476}, new char[]{1737, 64482, 64483}, new char[]{1739, 64478, 64479}, new char[]{FARSIYEH, 64508, 64509, 64510, 64511}, new char[]{1744, 64484, 64485, 64486, 64487}, new char[]{1746, 64430, 64431}, new char[]{1747, 64432, 64433}};
    private static final HashMap<Character, char[]> maptable = new HashMap<>();
    private static final HashMap<Character, Character> reverseLigatureMapTable = new HashMap<>();
    protected int options = 0;
    protected int runDirection = 3;

    static boolean isVowel(char c) {
        return (c >= 1611 && c <= 1621) || c == 1648;
    }

    public boolean isRTL() {
        return true;
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x02ff  */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0321 A[SYNTHETIC] */
    static {
        /*
            java.util.HashMap r0 = new java.util.HashMap
            r0.<init>()
            maptable = r0
            java.util.HashMap r0 = new java.util.HashMap
            r0.<init>()
            reverseLigatureMapTable = r0
            r0 = 76
            char[][] r0 = new char[r0][]
            r1 = 2
            char[] r2 = new char[r1]
            r2 = {1569, -384} // fill-array
            r3 = 0
            r0[r3] = r2
            r2 = 3
            char[] r4 = new char[r2]
            r4 = {1570, -383, -382} // fill-array
            r5 = 1
            r0[r5] = r4
            char[] r4 = new char[r2]
            r4 = {1571, -381, -380} // fill-array
            r0[r1] = r4
            char[] r4 = new char[r2]
            r4 = {1572, -379, -378} // fill-array
            r0[r2] = r4
            char[] r4 = new char[r2]
            r4 = {1573, -377, -376} // fill-array
            r6 = 4
            r0[r6] = r4
            r4 = 5
            char[] r7 = new char[r4]
            r7 = {1574, -375, -374, -373, -372} // fill-array
            r0[r4] = r7
            char[] r7 = new char[r2]
            r7 = {1575, -371, -370} // fill-array
            r8 = 6
            r0[r8] = r7
            char[] r7 = new char[r4]
            r7 = {1576, -369, -368, -367, -366} // fill-array
            r8 = 7
            r0[r8] = r7
            char[] r7 = new char[r2]
            r7 = {1577, -365, -364} // fill-array
            r8 = 8
            r0[r8] = r7
            char[] r7 = new char[r4]
            r7 = {1578, -363, -362, -361, -360} // fill-array
            r8 = 9
            r0[r8] = r7
            char[] r7 = new char[r4]
            r7 = {1579, -359, -358, -357, -356} // fill-array
            r8 = 10
            r0[r8] = r7
            char[] r7 = new char[r4]
            r7 = {1580, -355, -354, -353, -352} // fill-array
            r8 = 11
            r0[r8] = r7
            char[] r7 = new char[r4]
            r7 = {1581, -351, -350, -349, -348} // fill-array
            r8 = 12
            r0[r8] = r7
            char[] r7 = new char[r4]
            r7 = {1582, -347, -346, -345, -344} // fill-array
            r8 = 13
            r0[r8] = r7
            char[] r7 = new char[r2]
            r7 = {1583, -343, -342} // fill-array
            r8 = 14
            r0[r8] = r7
            char[] r7 = new char[r2]
            r7 = {1584, -341, -340} // fill-array
            r8 = 15
            r0[r8] = r7
            char[] r7 = new char[r2]
            r7 = {1585, -339, -338} // fill-array
            r8 = 16
            r0[r8] = r7
            char[] r7 = new char[r2]
            r7 = {1586, -337, -336} // fill-array
            r8 = 17
            r0[r8] = r7
            char[] r7 = new char[r4]
            r7 = {1587, -335, -334, -333, -332} // fill-array
            r8 = 18
            r0[r8] = r7
            char[] r7 = new char[r4]
            r7 = {1588, -331, -330, -329, -328} // fill-array
            r8 = 19
            r0[r8] = r7
            char[] r7 = new char[r4]
            r7 = {1589, -327, -326, -325, -324} // fill-array
            r8 = 20
            r0[r8] = r7
            char[] r7 = new char[r4]
            r7 = {1590, -323, -322, -321, -320} // fill-array
            r8 = 21
            r0[r8] = r7
            char[] r7 = new char[r4]
            r7 = {1591, -319, -318, -317, -316} // fill-array
            r8 = 22
            r0[r8] = r7
            char[] r7 = new char[r4]
            r7 = {1592, -315, -314, -313, -312} // fill-array
            r8 = 23
            r0[r8] = r7
            char[] r7 = new char[r4]
            r7 = {1593, -311, -310, -309, -308} // fill-array
            r8 = 24
            r0[r8] = r7
            char[] r7 = new char[r4]
            r7 = {1594, -307, -306, -305, -304} // fill-array
            r8 = 25
            r0[r8] = r7
            char[] r7 = new char[r4]
            r7 = {1600, 1600, 1600, 1600, 1600} // fill-array
            r8 = 26
            r0[r8] = r7
            char[] r7 = new char[r4]
            r7 = {1601, -303, -302, -301, -300} // fill-array
            r8 = 27
            r0[r8] = r7
            char[] r7 = new char[r4]
            r7 = {1602, -299, -298, -297, -296} // fill-array
            r8 = 28
            r0[r8] = r7
            char[] r7 = new char[r4]
            r7 = {1603, -295, -294, -293, -292} // fill-array
            r8 = 29
            r0[r8] = r7
            char[] r7 = new char[r4]
            r7 = {1604, -291, -290, -289, -288} // fill-array
            r8 = 30
            r0[r8] = r7
            char[] r7 = new char[r4]
            r7 = {1605, -287, -286, -285, -284} // fill-array
            r8 = 31
            r0[r8] = r7
            char[] r7 = new char[r4]
            r7 = {1606, -283, -282, -281, -280} // fill-array
            r8 = 32
            r0[r8] = r7
            char[] r7 = new char[r4]
            r7 = {1607, -279, -278, -277, -276} // fill-array
            r8 = 33
            r0[r8] = r7
            char[] r7 = new char[r2]
            r7 = {1608, -275, -274} // fill-array
            r8 = 34
            r0[r8] = r7
            char[] r7 = new char[r4]
            r7 = {1609, -273, -272, -1048, -1047} // fill-array
            r8 = 35
            r0[r8] = r7
            char[] r7 = new char[r4]
            r7 = {1610, -271, -270, -269, -268} // fill-array
            r8 = 36
            r0[r8] = r7
            char[] r7 = new char[r2]
            r7 = {1649, -1200, -1199} // fill-array
            r8 = 37
            r0[r8] = r7
            char[] r7 = new char[r4]
            r7 = {1657, -1178, -1177, -1176, -1175} // fill-array
            r8 = 38
            r0[r8] = r7
            char[] r7 = new char[r4]
            r7 = {1658, -1186, -1185, -1184, -1183} // fill-array
            r8 = 39
            r0[r8] = r7
            char[] r7 = new char[r4]
            r7 = {1659, -1198, -1197, -1196, -1195} // fill-array
            r8 = 40
            r0[r8] = r7
            char[] r7 = new char[r4]
            r7 = {1662, -1194, -1193, -1192, -1191} // fill-array
            r8 = 41
            r0[r8] = r7
            char[] r7 = new char[r4]
            r7 = {1663, -1182, -1181, -1180, -1179} // fill-array
            r8 = 42
            r0[r8] = r7
            char[] r7 = new char[r4]
            r7 = {1664, -1190, -1189, -1188, -1187} // fill-array
            r8 = 43
            r0[r8] = r7
            char[] r7 = new char[r4]
            r7 = {1667, -1162, -1161, -1160, -1159} // fill-array
            r8 = 44
            r0[r8] = r7
            char[] r7 = new char[r4]
            r7 = {1668, -1166, -1165, -1164, -1163} // fill-array
            r8 = 45
            r0[r8] = r7
            char[] r7 = new char[r4]
            r7 = {1670, -1158, -1157, -1156, -1155} // fill-array
            r8 = 46
            r0[r8] = r7
            char[] r7 = new char[r4]
            r7 = {1671, -1154, -1153, -1152, -1151} // fill-array
            r8 = 47
            r0[r8] = r7
            char[] r7 = new char[r2]
            r7 = {1672, -1144, -1143} // fill-array
            r8 = 48
            r0[r8] = r7
            char[] r7 = new char[r2]
            r7 = {1676, -1148, -1147} // fill-array
            r8 = 49
            r0[r8] = r7
            char[] r7 = new char[r2]
            r7 = {1677, -1150, -1149} // fill-array
            r8 = 50
            r0[r8] = r7
            char[] r7 = new char[r2]
            r7 = {1678, -1146, -1145} // fill-array
            r8 = 51
            r0[r8] = r7
            char[] r7 = new char[r2]
            r7 = {1681, -1140, -1139} // fill-array
            r8 = 52
            r0[r8] = r7
            char[] r7 = new char[r2]
            r7 = {1688, -1142, -1141} // fill-array
            r8 = 53
            r0[r8] = r7
            char[] r7 = new char[r4]
            r7 = {1700, -1174, -1173, -1172, -1171} // fill-array
            r8 = 54
            r0[r8] = r7
            char[] r7 = new char[r4]
            r7 = {1702, -1170, -1169, -1168, -1167} // fill-array
            r8 = 55
            r0[r8] = r7
            char[] r7 = new char[r4]
            r7 = {1705, -1138, -1137, -1136, -1135} // fill-array
            r8 = 56
            r0[r8] = r7
            char[] r7 = new char[r4]
            r7 = {1709, -1069, -1068, -1067, -1066} // fill-array
            r8 = 57
            r0[r8] = r7
            char[] r7 = new char[r4]
            r7 = {1711, -1134, -1133, -1132, -1131} // fill-array
            r8 = 58
            r0[r8] = r7
            char[] r7 = new char[r4]
            r7 = {1713, -1126, -1125, -1124, -1123} // fill-array
            r8 = 59
            r0[r8] = r7
            char[] r7 = new char[r4]
            r7 = {1715, -1130, -1129, -1128, -1127} // fill-array
            r8 = 60
            r0[r8] = r7
            char[] r7 = new char[r2]
            r7 = {1722, -1122, -1121} // fill-array
            r8 = 61
            r0[r8] = r7
            char[] r7 = new char[r4]
            r7 = {1723, -1120, -1119, -1118, -1117} // fill-array
            r8 = 62
            r0[r8] = r7
            char[] r7 = new char[r4]
            r7 = {1726, -1110, -1109, -1108, -1107} // fill-array
            r8 = 63
            r0[r8] = r7
            char[] r7 = new char[r2]
            r7 = {1728, -1116, -1115} // fill-array
            r8 = 64
            r0[r8] = r7
            char[] r7 = new char[r4]
            r7 = {1729, -1114, -1113, -1112, -1111} // fill-array
            r8 = 65
            r0[r8] = r7
            char[] r7 = new char[r2]
            r7 = {1733, -1056, -1055} // fill-array
            r8 = 66
            r0[r8] = r7
            char[] r7 = new char[r2]
            r7 = {1734, -1063, -1062} // fill-array
            r8 = 67
            r0[r8] = r7
            char[] r7 = new char[r2]
            r7 = {1735, -1065, -1064} // fill-array
            r8 = 68
            r0[r8] = r7
            char[] r7 = new char[r2]
            r7 = {1736, -1061, -1060} // fill-array
            r8 = 69
            r0[r8] = r7
            char[] r7 = new char[r2]
            r7 = {1737, -1054, -1053} // fill-array
            r8 = 70
            r0[r8] = r7
            char[] r7 = new char[r2]
            r7 = {1739, -1058, -1057} // fill-array
            r8 = 71
            r0[r8] = r7
            char[] r7 = new char[r4]
            r7 = {1740, -1028, -1027, -1026, -1025} // fill-array
            r8 = 72
            r0[r8] = r7
            char[] r7 = new char[r4]
            r7 = {1744, -1052, -1051, -1050, -1049} // fill-array
            r8 = 73
            r0[r8] = r7
            char[] r7 = new char[r2]
            r7 = {1746, -1106, -1105} // fill-array
            r8 = 74
            r0[r8] = r7
            char[] r7 = new char[r2]
            r7 = {1747, -1104, -1103} // fill-array
            r8 = 75
            r0[r8] = r7
            chartable = r0
            char[][] r0 = chartable
            int r7 = r0.length
            r8 = r3
        L_0x02bc:
            if (r8 >= r7) goto L_0x0324
            r9 = r0[r8]
            java.util.HashMap<java.lang.Character, char[]> r10 = maptable
            char r11 = r9[r3]
            java.lang.Character r11 = java.lang.Character.valueOf(r11)
            r10.put(r11, r9)
            int r10 = r9.length
            if (r10 == r2) goto L_0x02e2
            if (r10 == r4) goto L_0x02d1
            goto L_0x02f3
        L_0x02d1:
            java.util.HashMap<java.lang.Character, java.lang.Character> r10 = reverseLigatureMapTable
            char r11 = r9[r6]
            java.lang.Character r11 = java.lang.Character.valueOf(r11)
            char r12 = r9[r2]
            java.lang.Character r12 = java.lang.Character.valueOf(r12)
            r10.put(r11, r12)
        L_0x02e2:
            java.util.HashMap<java.lang.Character, java.lang.Character> r10 = reverseLigatureMapTable
            char r11 = r9[r1]
            java.lang.Character r11 = java.lang.Character.valueOf(r11)
            char r12 = r9[r5]
            java.lang.Character r12 = java.lang.Character.valueOf(r12)
            r10.put(r11, r12)
        L_0x02f3:
            char r10 = r9[r3]
            r11 = 1591(0x637, float:2.23E-42)
            if (r10 == r11) goto L_0x02ff
            char r10 = r9[r3]
            r11 = 1592(0x638, float:2.231E-42)
            if (r10 != r11) goto L_0x0321
        L_0x02ff:
            java.util.HashMap<java.lang.Character, java.lang.Character> r10 = reverseLigatureMapTable
            char r11 = r9[r6]
            java.lang.Character r11 = java.lang.Character.valueOf(r11)
            char r12 = r9[r5]
            java.lang.Character r12 = java.lang.Character.valueOf(r12)
            r10.put(r11, r12)
            java.util.HashMap<java.lang.Character, java.lang.Character> r10 = reverseLigatureMapTable
            char r11 = r9[r2]
            java.lang.Character r11 = java.lang.Character.valueOf(r11)
            char r9 = r9[r5]
            java.lang.Character r9 = java.lang.Character.valueOf(r9)
            r10.put(r11, r9)
        L_0x0321:
            int r8 = r8 + 1
            goto L_0x02bc
        L_0x0324:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.itextpdf.text.pdf.languages.ArabicLigaturizer.<clinit>():void");
    }

    static char charshape(char c, int i) {
        if (c >= 1569 && c <= 1747) {
            char[] cArr = maptable.get(Character.valueOf(c));
            if (cArr != null) {
                return cArr[i + 1];
            }
        } else if (c >= 65269 && c <= 65275) {
            return (char) (c + i);
        }
        return c;
    }

    static int shapecount(char c) {
        if (c >= 1569 && c <= 1747 && !isVowel(c)) {
            char[] cArr = maptable.get(Character.valueOf(c));
            if (cArr != null) {
                return cArr.length - 1;
            }
        } else if (c == 8205) {
            return 4;
        }
        return 1;
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static int ligature(char r9, com.itextpdf.text.pdf.languages.ArabicLigaturizer.charstruct r10) {
        /*
            char r0 = r10.basechar
            r1 = 0
            if (r0 != 0) goto L_0x0006
            return r1
        L_0x0006:
            boolean r0 = isVowel(r9)
            r2 = 65271(0xfef7, float:9.1464E-41)
            r3 = 65273(0xfef9, float:9.1467E-41)
            r4 = 65275(0xfefb, float:9.147E-41)
            r5 = 1
            r6 = 2
            if (r0 == 0) goto L_0x007c
            char r0 = r10.vowel
            r7 = 1617(0x651, float:2.266E-42)
            if (r0 == 0) goto L_0x0021
            if (r9 == r7) goto L_0x0021
            r0 = r6
            goto L_0x0022
        L_0x0021:
            r0 = r5
        L_0x0022:
            r8 = 1575(0x627, float:2.207E-42)
            switch(r9) {
                case 1617: goto L_0x006b;
                case 1618: goto L_0x0027;
                case 1619: goto L_0x0061;
                case 1620: goto L_0x003d;
                case 1621: goto L_0x002a;
                default: goto L_0x0027;
            }
        L_0x0027:
            r10.vowel = r9
            goto L_0x0073
        L_0x002a:
            char r9 = r10.basechar
            if (r9 == r8) goto L_0x0038
            if (r9 == r4) goto L_0x0035
            r9 = 1621(0x655, float:2.272E-42)
            r10.mark1 = r9
            goto L_0x0073
        L_0x0035:
            r10.basechar = r3
            goto L_0x0074
        L_0x0038:
            r9 = 1573(0x625, float:2.204E-42)
            r10.basechar = r9
            goto L_0x0074
        L_0x003d:
            char r9 = r10.basechar
            if (r9 == r8) goto L_0x005c
            r1 = 1740(0x6cc, float:2.438E-42)
            if (r9 == r1) goto L_0x0057
            if (r9 == r4) goto L_0x0054
            switch(r9) {
                case 1608: goto L_0x004f;
                case 1609: goto L_0x0057;
                case 1610: goto L_0x0057;
                default: goto L_0x004a;
            }
        L_0x004a:
            r9 = 1620(0x654, float:2.27E-42)
            r10.mark1 = r9
            goto L_0x0073
        L_0x004f:
            r9 = 1572(0x624, float:2.203E-42)
            r10.basechar = r9
            goto L_0x0074
        L_0x0054:
            r10.basechar = r2
            goto L_0x0074
        L_0x0057:
            r9 = 1574(0x626, float:2.206E-42)
            r10.basechar = r9
            goto L_0x0074
        L_0x005c:
            r9 = 1571(0x623, float:2.201E-42)
            r10.basechar = r9
            goto L_0x0074
        L_0x0061:
            char r9 = r10.basechar
            if (r9 == r8) goto L_0x0066
            goto L_0x0073
        L_0x0066:
            r9 = 1570(0x622, float:2.2E-42)
            r10.basechar = r9
            goto L_0x0074
        L_0x006b:
            char r9 = r10.mark1
            if (r9 != 0) goto L_0x0072
            r10.mark1 = r7
            goto L_0x0073
        L_0x0072:
            return r1
        L_0x0073:
            r6 = r0
        L_0x0074:
            if (r6 != r5) goto L_0x007b
            int r9 = r10.lignum
            int r9 = r9 + r5
            r10.lignum = r9
        L_0x007b:
            return r6
        L_0x007c:
            char r0 = r10.vowel
            if (r0 == 0) goto L_0x0081
            return r1
        L_0x0081:
            char r0 = r10.basechar
            if (r0 == 0) goto L_0x00a7
            r5 = 1604(0x644, float:2.248E-42)
            if (r0 == r5) goto L_0x008a
            goto L_0x00b0
        L_0x008a:
            r0 = 3
            switch(r9) {
                case 1570: goto L_0x009e;
                case 1571: goto L_0x0099;
                case 1572: goto L_0x008e;
                case 1573: goto L_0x0094;
                case 1574: goto L_0x008e;
                case 1575: goto L_0x008f;
                default: goto L_0x008e;
            }
        L_0x008e:
            goto L_0x00b0
        L_0x008f:
            r10.basechar = r4
            r10.numshapes = r6
            goto L_0x00a5
        L_0x0094:
            r10.basechar = r3
            r10.numshapes = r6
            goto L_0x00a5
        L_0x0099:
            r10.basechar = r2
            r10.numshapes = r6
            goto L_0x00a5
        L_0x009e:
            r9 = 65269(0xfef5, float:9.1461E-41)
            r10.basechar = r9
            r10.numshapes = r6
        L_0x00a5:
            r1 = r0
            goto L_0x00b0
        L_0x00a7:
            r10.basechar = r9
            int r9 = shapecount(r9)
            r10.numshapes = r9
            r1 = r5
        L_0x00b0:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.itextpdf.text.pdf.languages.ArabicLigaturizer.ligature(char, com.itextpdf.text.pdf.languages.ArabicLigaturizer$charstruct):int");
    }

    static void copycstostring(StringBuffer stringBuffer, charstruct charstruct2, int i) {
        if (charstruct2.basechar != 0) {
            stringBuffer.append(charstruct2.basechar);
            charstruct2.lignum--;
            if (charstruct2.mark1 != 0) {
                if ((i & 1) == 0) {
                    stringBuffer.append(charstruct2.mark1);
                    charstruct2.lignum--;
                } else {
                    charstruct2.lignum--;
                }
            }
            if (charstruct2.vowel == 0) {
                return;
            }
            if ((i & 1) == 0) {
                stringBuffer.append(charstruct2.vowel);
                charstruct2.lignum--;
                return;
            }
            charstruct2.lignum--;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0035, code lost:
        if (r10.charAt(r2) == 1617) goto L_0x0037;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0037, code lost:
        r5 = 64610;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x003d, code lost:
        if (r10.charAt(r2) == 1617) goto L_0x003f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x003f, code lost:
        r5 = 64609;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0045, code lost:
        if (r10.charAt(r2) == 1617) goto L_0x0047;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0047, code lost:
        r5 = 64608;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static void doublelig(java.lang.StringBuffer r10, int r11) {
        /*
            int r0 = r10.length()
            r1 = 0
            r2 = 1
            r3 = r0
            r4 = r1
        L_0x0008:
            if (r2 >= r0) goto L_0x0137
            r5 = r11 & 4
            r6 = 64608(0xfc60, float:9.0535E-41)
            r7 = 64609(0xfc61, float:9.0536E-41)
            r8 = 64610(0xfc62, float:9.0538E-41)
            if (r5 == 0) goto L_0x0049
            char r5 = r10.charAt(r4)
            r9 = 1617(0x651, float:2.266E-42)
            switch(r5) {
                case 1614: goto L_0x0041;
                case 1615: goto L_0x0039;
                case 1616: goto L_0x0031;
                case 1617: goto L_0x0021;
                default: goto L_0x0020;
            }
        L_0x0020:
            goto L_0x0049
        L_0x0021:
            char r5 = r10.charAt(r2)
            switch(r5) {
                case 1612: goto L_0x002d;
                case 1613: goto L_0x0029;
                case 1614: goto L_0x0047;
                case 1615: goto L_0x003f;
                case 1616: goto L_0x0037;
                default: goto L_0x0028;
            }
        L_0x0028:
            goto L_0x0049
        L_0x0029:
            r5 = 64607(0xfc5f, float:9.0534E-41)
            goto L_0x004a
        L_0x002d:
            r5 = 64606(0xfc5e, float:9.0532E-41)
            goto L_0x004a
        L_0x0031:
            char r5 = r10.charAt(r2)
            if (r5 != r9) goto L_0x0049
        L_0x0037:
            r5 = r8
            goto L_0x004a
        L_0x0039:
            char r5 = r10.charAt(r2)
            if (r5 != r9) goto L_0x0049
        L_0x003f:
            r5 = r7
            goto L_0x004a
        L_0x0041:
            char r5 = r10.charAt(r2)
            if (r5 != r9) goto L_0x0049
        L_0x0047:
            r5 = r6
            goto L_0x004a
        L_0x0049:
            r5 = r1
        L_0x004a:
            r6 = r11 & 8
            if (r6 == 0) goto L_0x011f
            char r6 = r10.charAt(r4)
            r7 = 65192(0xfea8, float:9.1353E-41)
            r8 = 65188(0xfea4, float:9.1348E-41)
            r9 = 65184(0xfea0, float:9.1342E-41)
            switch(r6) {
                case 65169: goto L_0x0109;
                case 65175: goto L_0x00f2;
                case 65235: goto L_0x00e4;
                case 65247: goto L_0x00ba;
                case 65251: goto L_0x0095;
                case 65255: goto L_0x007a;
                case 65256: goto L_0x0060;
                default: goto L_0x005e;
            }
        L_0x005e:
            goto L_0x011f
        L_0x0060:
            char r6 = r10.charAt(r2)
            r7 = 65198(0xfeae, float:9.1362E-41)
            if (r6 == r7) goto L_0x0075
            r7 = 65200(0xfeb0, float:9.1365E-41)
            if (r6 == r7) goto L_0x0070
            goto L_0x011f
        L_0x0070:
            r5 = 64651(0xfc8b, float:9.0595E-41)
            goto L_0x011f
        L_0x0075:
            r5 = 64650(0xfc8a, float:9.0594E-41)
            goto L_0x011f
        L_0x007a:
            char r6 = r10.charAt(r2)
            if (r6 == r9) goto L_0x0090
            if (r6 == r8) goto L_0x008b
            if (r6 == r7) goto L_0x0086
            goto L_0x011f
        L_0x0086:
            r5 = 64724(0xfcd4, float:9.0698E-41)
            goto L_0x011f
        L_0x008b:
            r5 = 64723(0xfcd3, float:9.0696E-41)
            goto L_0x011f
        L_0x0090:
            r5 = 64722(0xfcd2, float:9.0695E-41)
            goto L_0x011f
        L_0x0095:
            char r6 = r10.charAt(r2)
            if (r6 == r9) goto L_0x00b5
            if (r6 == r8) goto L_0x00b0
            if (r6 == r7) goto L_0x00ab
            r7 = 65252(0xfee4, float:9.1438E-41)
            if (r6 == r7) goto L_0x00a6
            goto L_0x011f
        L_0x00a6:
            r5 = 64721(0xfcd1, float:9.0693E-41)
            goto L_0x011f
        L_0x00ab:
            r5 = 64720(0xfcd0, float:9.0692E-41)
            goto L_0x011f
        L_0x00b0:
            r5 = 64719(0xfccf, float:9.069E-41)
            goto L_0x011f
        L_0x00b5:
            r5 = 64718(0xfcce, float:9.0689E-41)
            goto L_0x011f
        L_0x00ba:
            char r6 = r10.charAt(r2)
            switch(r6) {
                case 65182: goto L_0x00e0;
                case 65184: goto L_0x00dc;
                case 65186: goto L_0x00d8;
                case 65188: goto L_0x00d4;
                case 65190: goto L_0x00d0;
                case 65192: goto L_0x00cc;
                case 65250: goto L_0x00c8;
                case 65252: goto L_0x00c3;
                default: goto L_0x00c1;
            }
        L_0x00c1:
            goto L_0x011f
        L_0x00c3:
            r5 = 64716(0xfccc, float:9.0686E-41)
            goto L_0x011f
        L_0x00c8:
            r5 = 64578(0xfc42, float:9.0493E-41)
            goto L_0x011f
        L_0x00cc:
            r5 = 64715(0xfccb, float:9.0685E-41)
            goto L_0x011f
        L_0x00d0:
            r5 = 64577(0xfc41, float:9.0492E-41)
            goto L_0x011f
        L_0x00d4:
            r5 = 64714(0xfcca, float:9.0684E-41)
            goto L_0x011f
        L_0x00d8:
            r5 = 64576(0xfc40, float:9.049E-41)
            goto L_0x011f
        L_0x00dc:
            r5 = 64713(0xfcc9, float:9.0682E-41)
            goto L_0x011f
        L_0x00e0:
            r5 = 64575(0xfc3f, float:9.0489E-41)
            goto L_0x011f
        L_0x00e4:
            char r6 = r10.charAt(r2)
            r7 = 65266(0xfef2, float:9.1457E-41)
            if (r6 == r7) goto L_0x00ee
            goto L_0x011f
        L_0x00ee:
            r5 = 64562(0xfc32, float:9.047E-41)
            goto L_0x011f
        L_0x00f2:
            char r6 = r10.charAt(r2)
            if (r6 == r9) goto L_0x0105
            if (r6 == r8) goto L_0x0101
            if (r6 == r7) goto L_0x00fd
            goto L_0x011f
        L_0x00fd:
            r5 = 64675(0xfca3, float:9.0629E-41)
            goto L_0x011f
        L_0x0101:
            r5 = 64674(0xfca2, float:9.0628E-41)
            goto L_0x011f
        L_0x0105:
            r5 = 64673(0xfca1, float:9.0626E-41)
            goto L_0x011f
        L_0x0109:
            char r6 = r10.charAt(r2)
            if (r6 == r9) goto L_0x011c
            if (r6 == r8) goto L_0x0118
            if (r6 == r7) goto L_0x0114
            goto L_0x011f
        L_0x0114:
            r5 = 64670(0xfc9e, float:9.0622E-41)
            goto L_0x011f
        L_0x0118:
            r5 = 64669(0xfc9d, float:9.062E-41)
            goto L_0x011f
        L_0x011c:
            r5 = 64668(0xfc9c, float:9.0619E-41)
        L_0x011f:
            if (r5 == 0) goto L_0x012a
            r10.setCharAt(r4, r5)
            int r3 = r3 + -1
            int r2 = r2 + 1
            goto L_0x0008
        L_0x012a:
            int r4 = r4 + 1
            char r5 = r10.charAt(r2)
            r10.setCharAt(r4, r5)
            int r2 = r2 + 1
            goto L_0x0008
        L_0x0137:
            r10.setLength(r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.itextpdf.text.pdf.languages.ArabicLigaturizer.doublelig(java.lang.StringBuffer, int):void");
    }

    static boolean connects_to_left(charstruct charstruct2) {
        return charstruct2.numshapes > 2;
    }

    static void shape(char[] cArr, StringBuffer stringBuffer, int i) {
        charstruct charstruct2 = new charstruct();
        charstruct charstruct3 = new charstruct();
        charstruct charstruct4 = charstruct2;
        int i2 = 0;
        while (i2 < cArr.length) {
            int i3 = i2 + 1;
            char c = cArr[i2];
            if (ligature(c, charstruct3) == 0) {
                int shapecount = shapecount(c);
                int i4 = shapecount == 1 ? 0 : 2;
                if (connects_to_left(charstruct4)) {
                    i4++;
                }
                charstruct3.basechar = charshape(charstruct3.basechar, i4 % charstruct3.numshapes);
                copycstostring(stringBuffer, charstruct4, i);
                charstruct charstruct5 = new charstruct();
                charstruct5.basechar = c;
                charstruct5.numshapes = shapecount;
                charstruct5.lignum++;
                i2 = i3;
                charstruct charstruct6 = charstruct5;
                charstruct4 = charstruct3;
                charstruct3 = charstruct6;
            } else {
                i2 = i3;
            }
        }
        charstruct3.basechar = charshape(charstruct3.basechar, (connects_to_left(charstruct4) ? 1 : 0) % charstruct3.numshapes);
        copycstostring(stringBuffer, charstruct4, i);
        copycstostring(stringBuffer, charstruct3, i);
    }

    public static int arabic_shape(char[] cArr, int i, int i2, char[] cArr2, int i3, int i4, int i5) {
        char[] cArr3 = new char[i2];
        for (int i6 = (i2 + i) - 1; i6 >= i; i6--) {
            cArr3[i6 - i] = cArr[i6];
        }
        StringBuffer stringBuffer = new StringBuffer(i2);
        shape(cArr3, stringBuffer, i5);
        if ((i5 & 12) != 0) {
            doublelig(stringBuffer, i5);
        }
        System.arraycopy(stringBuffer.toString().toCharArray(), 0, cArr2, i3, stringBuffer.length());
        return stringBuffer.length();
    }

    public static void processNumbers(char[] cArr, int i, int i2, int i3) {
        int i4 = i + i2;
        int i5 = i3 & 224;
        if (i5 != 0) {
            int i6 = i3 & 256;
            char c = i6 != 0 ? i6 != 256 ? '0' : 1776 : 1632;
            if (i5 == 32) {
                int i7 = c - '0';
                while (i < i4) {
                    char c2 = cArr[i];
                    if (c2 <= '9' && c2 >= '0') {
                        cArr[i] = (char) (cArr[i] + i7);
                    }
                    i++;
                }
            } else if (i5 == 64) {
                char c3 = (char) (c + 9);
                int i8 = '0' - c;
                while (i < i4) {
                    char c4 = cArr[i];
                    if (c4 <= c3 && c4 >= c) {
                        cArr[i] = (char) (cArr[i] + i8);
                    }
                    i++;
                }
            } else if (i5 == 96) {
                shapeToArabicDigitsWithContext(cArr, 0, i2, c, false);
            } else if (i5 == 128) {
                shapeToArabicDigitsWithContext(cArr, 0, i2, c, true);
            }
        }
    }

    static void shapeToArabicDigitsWithContext(char[] cArr, int i, int i2, char c, boolean z) {
        char c2 = (char) (c - '0');
        int i3 = i2 + i;
        while (i < i3) {
            char c3 = cArr[i];
            byte direction = BidiOrder.getDirection(c3);
            if (direction != 0) {
                if (direction != 8) {
                    switch (direction) {
                        case 3:
                            break;
                        case 4:
                            z = true;
                            continue;
                    }
                } else if (z && c3 <= '9') {
                    cArr[i] = (char) (c3 + c2);
                }
                i++;
            }
            z = false;
            i++;
        }
    }

    public static Character getReverseMapping(char c) {
        return reverseLigatureMapTable.get(Character.valueOf(c));
    }

    static class charstruct {
        char basechar;
        int lignum;
        char mark1;
        int numshapes = 1;
        char vowel;

        charstruct() {
        }
    }

    public ArabicLigaturizer() {
    }

    public ArabicLigaturizer(int i, int i2) {
        this.runDirection = i;
        this.options = i2;
    }

    public String process(String str) {
        return BidiLine.processLTR(str, this.runDirection, this.options);
    }
}
