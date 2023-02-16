package apps.shehryar.com.cricketscroingappPro.Utility_Classes;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.text.Html;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;
import apps.shehryar.com.cricketscroingapp.R;
import apps.shehryar.com.cricketscroingappPro.Batsman.Batsman;
import apps.shehryar.com.cricketscroingappPro.Overs.Over;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Matcher;

public class Formatter {
    public static String getSuperScriptStaric() {
        return String.valueOf(Html.fromHtml("<sup>*</sup>"));
    }

    public static String cutNameHalf(String str) {
        String[] strArr = new String[0];
        try {
            String[] split = str.split(" ");
            StringBuilder sb = new StringBuilder();
            if (split.length > 1) {
                for (int i = 0; i < split.length - 1; i++) {
                    sb.append(getFirstCharacterOfAString(split[i]));
                }
                sb.append(" ");
                sb.append(split[split.length - 1]);
            } else {
                sb.append(str);
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return " ";
        }
    }

    public static char getFirstCharacterOfAString(String str) {
        try {
            return str.toCharArray()[0];
        } catch (Exception e) {
            e.printStackTrace();
            return ' ';
        }
    }

    public static String convertPerBallScoreToString(ArrayList<Integer> arrayList) {
        StringBuffer stringBuffer = new StringBuffer();
        Iterator<Integer> it = arrayList.iterator();
        while (it.hasNext()) {
            int intValue = it.next().intValue();
            stringBuffer.append(intValue + "&");
        }
        return stringBuffer.toString();
    }

    public static ArrayList<Integer> convertStringToPerBallScore(String str) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (String valueOf : str.split("\\&")) {
            try {
                arrayList.add(Integer.valueOf(valueOf));
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return arrayList;
    }

    public static ArrayList<Integer> convertStringWithSpaceToPerBallScore(String str) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (String valueOf : str.split(" ")) {
            arrayList.add(Integer.valueOf(valueOf));
        }
        return arrayList;
    }

    public static String convertOversToString(ArrayList<Over> arrayList) {
        StringBuffer stringBuffer = new StringBuffer();
        Iterator<Over> it = arrayList.iterator();
        while (it.hasNext()) {
            Over next = it.next();
            if (!(next == null || next.getBowler() == null)) {
                stringBuffer.append(next.getOver_no() + "+" + next.getOverscore() + "+" + next.getWickets() + "+" + next.getBowler() + "+" + convertPerBallScoreToString(next.getPerballScore()) + ",");
            }
        }
        return stringBuffer.toString();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:45:0x00e4, code lost:
        if (r5.getWickets() == r4.getWickets()) goto L_0x00ec;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.util.ArrayList<apps.shehryar.com.cricketscroingappPro.Overs.Over> convertStringToOvers(java.lang.String r9) {
        /*
            java.lang.String r0 = ","
            java.lang.String[] r9 = r9.split(r0)
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            r1 = 0
            r2 = r9[r1]
            boolean r2 = r2.isEmpty()
            if (r2 != 0) goto L_0x002b
            r2 = r9[r1]
            java.lang.String r3 = ""
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x002b
            r2 = r9[r1]
            java.lang.String r3 = "null"
            boolean r2 = r2.equals(r3)
            if (r2 == 0) goto L_0x0029
            goto L_0x002b
        L_0x0029:
            int r2 = r9.length
            goto L_0x002c
        L_0x002b:
            r2 = r1
        L_0x002c:
            if (r9 == 0) goto L_0x00f5
            r3 = r1
        L_0x002f:
            if (r3 >= r2) goto L_0x00f5
            r4 = r9[r3]
            apps.shehryar.com.cricketscroingappPro.Overs.Over r5 = new apps.shehryar.com.cricketscroingappPro.Overs.Over
            r5.<init>()
            java.lang.String r6 = "\\+"
            java.lang.String[] r6 = r4.split(r6)     // Catch:{ NumberFormatException -> 0x0048 }
            r6 = r6[r1]     // Catch:{ NumberFormatException -> 0x0048 }
            int r6 = java.lang.Integer.parseInt(r6)     // Catch:{ NumberFormatException -> 0x0048 }
            r5.setOver_no(r6)     // Catch:{ NumberFormatException -> 0x0048 }
            goto L_0x004f
        L_0x0048:
            r6 = move-exception
            r6.printStackTrace()
            r5.setOver_no(r1)
        L_0x004f:
            r6 = 1
            java.lang.String r7 = "\\+"
            java.lang.String[] r7 = r4.split(r7)     // Catch:{ NumberFormatException -> 0x0060 }
            r7 = r7[r6]     // Catch:{ NumberFormatException -> 0x0060 }
            int r7 = java.lang.Integer.parseInt(r7)     // Catch:{ NumberFormatException -> 0x0060 }
            r5.setOverscore(r7)     // Catch:{ NumberFormatException -> 0x0060 }
            goto L_0x0067
        L_0x0060:
            r7 = move-exception
            r7.printStackTrace()
            r5.setOverscore(r1)
        L_0x0067:
            java.lang.String r7 = "\\+"
            java.lang.String[] r7 = r4.split(r7)     // Catch:{ NumberFormatException -> 0x0078 }
            r8 = 2
            r7 = r7[r8]     // Catch:{ NumberFormatException -> 0x0078 }
            int r7 = java.lang.Integer.parseInt(r7)     // Catch:{ NumberFormatException -> 0x0078 }
            r5.setWickets(r7)     // Catch:{ NumberFormatException -> 0x0078 }
            goto L_0x007f
        L_0x0078:
            r7 = move-exception
            r7.printStackTrace()
            r5.setWickets(r1)
        L_0x007f:
            java.lang.String r7 = "\\+"
            java.lang.String[] r7 = r4.split(r7)     // Catch:{ Exception -> 0x008c }
            r8 = 3
            r7 = r7[r8]     // Catch:{ Exception -> 0x008c }
            r5.setBowler(r7)     // Catch:{ Exception -> 0x008c }
            goto L_0x0095
        L_0x008c:
            r7 = move-exception
            r7.printStackTrace()
            java.lang.String r7 = " "
            r5.setBowler(r7)
        L_0x0095:
            java.lang.String r7 = "\\+"
            java.lang.String[] r4 = r4.split(r7)     // Catch:{ Exception -> 0x00a6 }
            r7 = 4
            r4 = r4[r7]     // Catch:{ Exception -> 0x00a6 }
            java.util.ArrayList r4 = convertStringToPerBallScore(r4)     // Catch:{ Exception -> 0x00a6 }
            r5.setPerballScore(r4)     // Catch:{ Exception -> 0x00a6 }
            goto L_0x00b2
        L_0x00a6:
            r4 = move-exception
            r4.printStackTrace()
            java.util.ArrayList r4 = new java.util.ArrayList
            r4.<init>()
            r5.setPerballScore(r4)
        L_0x00b2:
            r4 = 0
            int r7 = r0.size()
            if (r7 <= 0) goto L_0x00c4
            int r4 = r0.size()
            int r4 = r4 - r6
            java.lang.Object r4 = r0.get(r4)
            apps.shehryar.com.cricketscroingappPro.Overs.Over r4 = (apps.shehryar.com.cricketscroingappPro.Overs.Over) r4
        L_0x00c4:
            java.lang.String r7 = r5.getBowler()     // Catch:{ Exception -> 0x00e7 }
            java.lang.String r8 = r4.getBowler()     // Catch:{ Exception -> 0x00e7 }
            boolean r7 = r7.equals(r8)     // Catch:{ Exception -> 0x00e7 }
            if (r7 == 0) goto L_0x00eb
            int r7 = r5.getOverscore()     // Catch:{ Exception -> 0x00e7 }
            int r8 = r4.getOverscore()     // Catch:{ Exception -> 0x00e7 }
            if (r7 != r8) goto L_0x00eb
            int r7 = r5.getWickets()     // Catch:{ Exception -> 0x00e7 }
            int r4 = r4.getWickets()     // Catch:{ Exception -> 0x00e7 }
            if (r7 != r4) goto L_0x00eb
            goto L_0x00ec
        L_0x00e7:
            r4 = move-exception
            r4.printStackTrace()
        L_0x00eb:
            r6 = r1
        L_0x00ec:
            if (r6 != 0) goto L_0x00f1
            r0.add(r5)
        L_0x00f1:
            int r3 = r3 + 1
            goto L_0x002f
        L_0x00f5:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: apps.shehryar.com.cricketscroingappPro.Utility_Classes.Formatter.convertStringToOvers(java.lang.String):java.util.ArrayList");
    }

    public static String getActualPerBallScore(ArrayList<Integer> arrayList) {
        StringBuffer stringBuffer = new StringBuffer();
        Iterator<Integer> it = arrayList.iterator();
        while (it.hasNext()) {
            int intValue = it.next().intValue();
            if (intValue == 11) {
                stringBuffer.append("0nb ");
            } else if (intValue == 12) {
                stringBuffer.append("1nb ");
            } else if (intValue == 13) {
                stringBuffer.append("2nb ");
            } else if (intValue == 14) {
                stringBuffer.append("3nb ");
            } else if (intValue == 15) {
                stringBuffer.append("4nb ");
            } else if (intValue == 16) {
                stringBuffer.append("5nb ");
            } else if (intValue == 17) {
                stringBuffer.append("6nb ");
            } else if (intValue == 18) {
                stringBuffer.append("7nb ");
            } else if (intValue == 20) {
                stringBuffer.append("W ");
            } else if (intValue == 21) {
                stringBuffer.append("0wd ");
            } else if (intValue == 22) {
                stringBuffer.append("1wd ");
            } else if (intValue == 23) {
                stringBuffer.append("2wd ");
            } else if (intValue == 24) {
                stringBuffer.append("3wd ");
            } else if (intValue == 25) {
                stringBuffer.append("4wd ");
            } else if (intValue == 26) {
                stringBuffer.append("5wd ");
            } else if (intValue == 27) {
                stringBuffer.append("6wd ");
            } else if (intValue == 28) {
                stringBuffer.append("7wd ");
            } else if (intValue == 20) {
                stringBuffer.append("W ");
            } else if (intValue == 41) {
                stringBuffer.append("1b ");
            } else if (intValue == 42) {
                stringBuffer.append("2b ");
            } else if (intValue == 43) {
                stringBuffer.append("3b ");
            } else if (intValue == 44) {
                stringBuffer.append("4b ");
            } else if (intValue == 45) {
                stringBuffer.append("5b ");
            } else if (intValue == 46) {
                stringBuffer.append("6b ");
            } else if (intValue == 47) {
                stringBuffer.append("7b ");
            } else if (intValue == 51) {
                stringBuffer.append("1lb ");
            } else if (intValue == 52) {
                stringBuffer.append("2lb ");
            } else if (intValue == 53) {
                stringBuffer.append("3lb ");
            } else if (intValue == 54) {
                stringBuffer.append("4lb ");
            } else if (intValue == 55) {
                stringBuffer.append("5lb ");
            } else if (intValue == 56) {
                stringBuffer.append("6lb ");
            } else if (intValue == 57) {
                stringBuffer.append("7lb ");
            } else if (intValue == 60) {
                stringBuffer.append("W ");
            } else if (intValue == 61) {
                stringBuffer.append("W+wd ");
            } else if (intValue == 62) {
                stringBuffer.append("W+nb ");
            } else {
                stringBuffer.append(intValue + " ");
            }
        }
        return stringBuffer.toString();
    }

    public static void setFormattedBatsmenScores(TextView textView, Batsman batsman, Context context) throws Exception {
        int dimensionPixelSize = context.getResources().getDimensionPixelSize(R.dimen.batsman_score_size);
        int dimensionPixelSize2 = context.getResources().getDimensionPixelSize(R.dimen.batsman_balls_text_size);
        String str = batsman.getTotalScore() + "";
        SpannableString spannableString = new SpannableString(str);
        spannableString.setSpan(new AbsoluteSizeSpan(dimensionPixelSize), 0, str.length(), 18);
        spannableString.setSpan(new ForegroundColorSpan(ContextCompat.getColor(context, 17170444)), 0, str.length(), 18);
        String str2 = "(" + batsman.getBallsfaced() + ")";
        SpannableString spannableString2 = new SpannableString(str2);
        spannableString2.setSpan(new AbsoluteSizeSpan(dimensionPixelSize2), 0, str2.length(), 18);
        spannableString2.setSpan(new ForegroundColorSpan(ContextCompat.getColor(context, 17170439)), 0, str2.length(), 18);
        CharSequence charSequence = "";
        if (batsman.getTotalScore() < 100 && batsman.getBallsfaced() < 100) {
            charSequence = TextUtils.concat(new CharSequence[]{spannableString, "", spannableString2});
        } else if (batsman.getTotalScore() >= 100 || batsman.getBallsfaced() >= 100) {
            charSequence = TextUtils.concat(new CharSequence[]{spannableString, "\n", spannableString2});
        }
        textView.setText(charSequence, TextView.BufferType.SPANNABLE);
    }

    public static void setFormattedPartenershipScores(TextView textView, int i, int i2, Context context) throws Exception {
        int dimensionPixelSize = context.getResources().getDimensionPixelSize(R.dimen.batsman_score_size_partenership);
        int dimensionPixelSize2 = context.getResources().getDimensionPixelSize(R.dimen.batsman_balls_text_size_partenership);
        if (i < 0) {
            i = 0;
        }
        if (i2 < 0) {
            i2 = 0;
        }
        String str = i + "";
        SpannableString spannableString = new SpannableString(str);
        spannableString.setSpan(new AbsoluteSizeSpan(dimensionPixelSize), 0, str.length(), 18);
        spannableString.setSpan(new ForegroundColorSpan(ContextCompat.getColor(context, R.color.tealdark)), 0, str.length(), 18);
        String str2 = "(" + i2 + ")";
        SpannableString spannableString2 = new SpannableString(str2);
        spannableString2.setSpan(new AbsoluteSizeSpan(dimensionPixelSize2), 0, str2.length(), 18);
        spannableString2.setSpan(new ForegroundColorSpan(ContextCompat.getColor(context, R.color.teal)), 0, str2.length(), 18);
        CharSequence charSequence = "";
        if (i < 100 && i2 < 100) {
            charSequence = TextUtils.concat(new CharSequence[]{spannableString, "", spannableString2});
        } else if (i >= 100 || i2 >= 100) {
            charSequence = TextUtils.concat(new CharSequence[]{spannableString, "\n", spannableString2});
        }
        textView.setText(charSequence, TextView.BufferType.SPANNABLE);
    }

    public static String wrapStringWithDoubleQuotes(String str) {
        return "\"" + str + "\"";
    }

    public static String replaceForwardSlashWithBackSlash(String str) {
        return str.replaceAll("/", Matcher.quoteReplacement("\\"));
    }

    public static String replaceSingleQuoteWithTwoSingleQuotes(String str) {
        return str.replaceAll("'", "''");
    }

    public static String getFormattedName(String str) throws Exception {
        if (str == null) {
            return "";
        }
        String[] split = str.split(" ");
        if (split.length < 2 || str.length() <= 1) {
            return str;
        }
        return split[0].charAt(0) + " " + split[1];
    }
}
