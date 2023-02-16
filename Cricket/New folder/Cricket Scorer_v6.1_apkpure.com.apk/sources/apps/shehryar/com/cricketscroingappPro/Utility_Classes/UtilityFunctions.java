package apps.shehryar.com.cricketscroingappPro.Utility_Classes;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.FileProvider;
import android.text.Spannable;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.MimeTypeMap;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;
import apps.shehryar.com.CheckBoxListItem;
import apps.shehryar.com.cricketscroingapp.R;
import apps.shehryar.com.cricketscroingappPro.Batsman.Batsman;
import apps.shehryar.com.cricketscroingappPro.Bowler.Bowler;
import apps.shehryar.com.cricketscroingappPro.DBHelper;
import apps.shehryar.com.cricketscroingappPro.Match.Match;
import apps.shehryar.com.cricketscroingappPro.Model.GeneralOptions;
import apps.shehryar.com.cricketscroingappPro.Model.NavigationItem;
import apps.shehryar.com.cricketscroingappPro.Overs.Over;
import apps.shehryar.com.cricketscroingappPro.PermissionAsker;
import apps.shehryar.com.cricketscroingappPro.Player.Player;
import apps.shehryar.com.cricketscroingappPro.SuggestionDialog;
import apps.shehryar.com.cricketscroingappPro.Team.Team;
import apps.shehryar.com.cricketscroingappPro.UpgradeToProDialog;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;

public class UtilityFunctions {
    public static int convertOverstoBalls(int i, int i2, int i3) {
        return (i * i3) + i2;
    }

    public static int getNumberOfBallsFromOvers(int i, int i2, int i3) {
        return (i2 * i) + i3;
    }

    public static ArrayList<String> getStringArrayListFromPlayersList(ArrayList<Player> arrayList) {
        ArrayList<String> arrayList2 = new ArrayList<>();
        Iterator<Player> it = arrayList.iterator();
        while (it.hasNext()) {
            arrayList2.add(it.next().getName());
        }
        return arrayList2;
    }

    public static boolean checkBatsman(ArrayList<Batsman> arrayList, Batsman batsman) {
        Iterator<Batsman> it = arrayList.iterator();
        while (it.hasNext()) {
            if (it.next().getName().equals(batsman.getName())) {
                return true;
            }
        }
        return false;
    }

    public static boolean checkBowler(ArrayList<Bowler> arrayList, Bowler bowler) {
        Iterator<Bowler> it = arrayList.iterator();
        while (it.hasNext()) {
            if (it.next().getName().equals(bowler.getName())) {
                return true;
            }
        }
        return false;
    }

    public static void setMatchStatusAsFollowedOn(Context context, Match match) {
        long team_Yours_id2 = match.getTeam_Yours_id2();
        match.setTeam_Yours_id2(match.getTeam_opp_id2());
        match.setTeam_opp_id2(team_Yours_id2);
        new DBHelper(context).updateMatch(match);
    }

    public static void setTeamExtras(Team team, ArrayList<Over> arrayList) {
        team.clearByes();
        team.clearNos();
        team.clearWides();
        team.clearLegByes();
        team.clearDots();
        team.clearSingles();
        team.clearDoubles();
        team.clearTriples();
        team.clearFours();
        team.clearFives();
        team.clearSixes();
        Log.i("team dots", team.getDots() + "");
        Log.i("team singles", team.getSingles() + "");
        Log.i("team doubles", team.getDoubles() + "");
        Log.i("team threes", team.getTriples() + "");
        Log.i("team fours", team.getFours() + "");
        Log.i("team sixes", team.getSixes() + "");
        Iterator<Over> it = arrayList.iterator();
        while (it.hasNext()) {
            Iterator<Integer> it2 = it.next().perballScore.iterator();
            while (it2.hasNext()) {
                int intValue = it2.next().intValue();
                if (intValue < 11 || intValue > 18) {
                    if (intValue < 21 || intValue > 28) {
                        if (intValue < 41 || intValue > 47) {
                            if (intValue >= 51 && intValue <= 57) {
                                switch (intValue) {
                                    case 51:
                                        team.setLegbyes(1);
                                        break;
                                    case 52:
                                        team.setLegbyes(2);
                                        break;
                                    case 53:
                                        team.setLegbyes(3);
                                        break;
                                    case 54:
                                        team.setLegbyes(4);
                                        break;
                                    case 55:
                                        team.setLegbyes(5);
                                        break;
                                    case 56:
                                        team.setLegbyes(6);
                                        break;
                                    case 57:
                                        team.setLegbyes(7);
                                        break;
                                    case 58:
                                        team.setLegbyes(8);
                                        break;
                                }
                            } else if (intValue == 0) {
                                team.setDots(1);
                            } else if (intValue == 1) {
                                team.setSingles(1);
                            } else if (intValue == 2) {
                                team.setDoubles(1);
                            } else if (intValue == 3) {
                                team.setTriples(1);
                            } else if (intValue == 4) {
                                team.setFours(1);
                            } else if (intValue == 5) {
                                team.setFives(1);
                            } else if (intValue == 6) {
                                team.setSixes(1);
                            }
                        } else {
                            switch (intValue) {
                                case 41:
                                    team.setByes(1);
                                    break;
                                case 42:
                                    team.setByes(2);
                                    break;
                                case 43:
                                    team.setByes(3);
                                    break;
                                case 44:
                                    team.setByes(4);
                                    break;
                                case 45:
                                    team.setByes(5);
                                    break;
                                case 46:
                                    team.setByes(6);
                                    break;
                                case 47:
                                    team.setByes(7);
                                    break;
                                case 48:
                                    team.setByes(8);
                                    break;
                            }
                        }
                    } else {
                        switch (intValue) {
                            case 21:
                                team.setWides(1);
                                break;
                            case 22:
                                team.setWides(2);
                                break;
                            case 23:
                                team.setWides(3);
                                break;
                            case 24:
                                team.setWides(4);
                                break;
                            case 25:
                                team.setFives(5);
                                break;
                            case 26:
                                team.setWides(6);
                                break;
                            case 27:
                                team.setWides(7);
                                break;
                            case 28:
                                team.setWides(8);
                                break;
                        }
                    }
                } else {
                    switch (intValue) {
                        case 11:
                            team.setNos(1);
                            break;
                        case 12:
                            team.setNos(2);
                            break;
                        case 13:
                            team.setNos(3);
                            break;
                        case 14:
                            team.setNos(4);
                            break;
                        case 15:
                            team.setNos(5);
                            break;
                        case 16:
                            team.setNos(6);
                            break;
                        case 17:
                            team.setNos(7);
                            break;
                        case 18:
                            team.setNos(8);
                            break;
                    }
                }
            }
        }
    }

    public static void setSpanColor(TextView textView, String str, String str2, int i) {
        textView.setText(str, TextView.BufferType.SPANNABLE);
        int indexOf = str.indexOf(str2);
        ((Spannable) textView.getText()).setSpan(new ForegroundColorSpan(i), indexOf, str2.length() + indexOf, 33);
    }

    public static String getCurrentScoreString(Match match, Team team, Team team2, Batsman batsman, Batsman batsman2, Bowler bowler) {
        return match.getYourteam() + " VS " + match.getOpponent() + "\nVenue: " + match.getVenue() + "\n\n" + team.getName() + ": " + team.getScore() + "/" + team.getWickets() + "(" + team.getOversPlayed() + "." + team.getOverballs() + ")\n" + team2.getName() + ": " + team2.getScore() + "/" + team2.getWickets() + "(" + team2.getOversPlayed() + "." + team2.getOverballs() + ")\n\nCurrent Batsmen\n\n" + batsman.getName() + ":" + batsman.getTotalScore() + "(" + batsman.getBallsfaced() + ")\n" + batsman2.getName() + ":" + batsman2.getTotalScore() + "(" + batsman2.getBallsfaced() + ")\n\nCurrent Bowler\n\n" + bowler.getName() + ":" + bowler.getTotalscore() + "/" + bowler.getWickets();
    }

    public static void showKeyBoard(Context context, EditText editText) {
        ((InputMethodManager) context.getSystemService("input_method")).showSoftInput(editText, 2);
    }

    public static void hideKeyBoard(Context context, EditText editText) {
        ((InputMethodManager) context.getSystemService("input_method")).hideSoftInputFromWindow(editText.getWindowToken(), 0);
    }

    public static void hideKeyBoardOnNextAction(final Context context, AutoCompleteTextView autoCompleteTextView) {
        autoCompleteTextView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i != 5) {
                    return false;
                }
                ((InputMethodManager) context.getSystemService("input_method")).hideSoftInputFromWindow(textView.getWindowToken(), 0);
                return true;
            }
        });
    }

    public static void showDropDownOntouchingEditText(final AutoCompleteTextView autoCompleteTextView) {
        autoCompleteTextView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                try {
                    autoCompleteTextView.showDropDown();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public static void showDropDownOnEditTextFocus(final AutoCompleteTextView autoCompleteTextView) {
        autoCompleteTextView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View view, boolean z) {
                if (z) {
                    try {
                        if (autoCompleteTextView.getAdapter() != null && autoCompleteTextView != null) {
                            autoCompleteTextView.showDropDown();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    public static ArrayList<Batsman> sortBatsmen(ArrayList<Batsman> arrayList) {
        Collections.sort(arrayList, new Comparator<Batsman>() {
            public int compare(Batsman batsman, Batsman batsman2) {
                return batsman2.getTotalScore() - batsman.getTotalScore();
            }
        });
        return arrayList;
    }

    public static ArrayList<Bowler> sortBowler(ArrayList<Bowler> arrayList) {
        Collections.sort(arrayList, new Comparator<Bowler>() {
            public int compare(Bowler bowler, Bowler bowler2) {
                return bowler2.getWickets() - bowler.getWickets();
            }
        });
        return arrayList;
    }

    public static void openSuggestionsDialog(final Activity activity, AutoCompleteTextView autoCompleteTextView, ArrayList<String> arrayList, boolean z, boolean z2) {
        if (Build.VERSION.SDK_INT >= 21 && arrayList.size() > 0) {
            autoCompleteTextView.setShowSoftInputOnFocus(false);
        }
        AnonymousClass6 r0 = new SuggestionDialog.SuggestionsClickCallBack() {
            public void onSuggestionClicked(EditText editText, String str) {
                editText.setText(str);
            }

            public void onTypeYourOwnClick(EditText editText) {
                editText.setText("");
                editText.setFocusable(true);
                editText.requestFocus();
                activity.getWindow().setSoftInputMode(5);
            }
        };
        if (arrayList.size() > 0) {
            SuggestionDialog suggestionDialog = new SuggestionDialog(activity, autoCompleteTextView, arrayList, r0);
            Bundle bundle = new Bundle();
            bundle.putBoolean("matchType", z2);
            bundle.putBoolean("toss", z);
            suggestionDialog.setArguments(bundle);
            try {
                suggestionDialog.show(activity.getFragmentManager(), "suggestion Dialog");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void openSuggestionsDialog(Activity activity, AutoCompleteTextView autoCompleteTextView, ArrayList<String> arrayList, boolean z, boolean z2, SuggestionDialog.SuggestionsClickCallBack suggestionsClickCallBack) {
        if (Build.VERSION.SDK_INT >= 21 && arrayList.size() > 0) {
            autoCompleteTextView.setShowSoftInputOnFocus(false);
        }
        if (arrayList.size() > 0) {
            SuggestionDialog suggestionDialog = new SuggestionDialog(activity, autoCompleteTextView, arrayList, suggestionsClickCallBack);
            Bundle bundle = new Bundle();
            bundle.putBoolean("matchType", z2);
            bundle.putBoolean("toss", z);
            suggestionDialog.setArguments(bundle);
            try {
                suggestionDialog.show(activity.getFragmentManager(), "suggestion Dialog");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void openSuggestionsDialogInDialogFragment(final DialogFragment dialogFragment, AutoCompleteTextView autoCompleteTextView, ArrayList<String> arrayList, boolean z, boolean z2) {
        if (Build.VERSION.SDK_INT >= 21 && arrayList.size() > 0) {
            autoCompleteTextView.setShowSoftInputOnFocus(false);
        }
        AnonymousClass7 r0 = new SuggestionDialog.SuggestionsClickCallBack() {
            public void onSuggestionClicked(EditText editText, String str) {
                editText.setText(str);
            }

            public void onTypeYourOwnClick(EditText editText) {
                editText.setText("");
                editText.setFocusable(true);
                editText.requestFocus();
                dialogFragment.getDialog().getWindow().setSoftInputMode(5);
            }
        };
        if (arrayList.size() > 0) {
            SuggestionDialog suggestionDialog = new SuggestionDialog(dialogFragment.getActivity(), autoCompleteTextView, arrayList, r0);
            Bundle bundle = new Bundle();
            bundle.putBoolean("matchType", z2);
            bundle.putBoolean("toss", z);
            suggestionDialog.setArguments(bundle);
            try {
                suggestionDialog.show(dialogFragment.getActivity().getFragmentManager(), "suggestion Dialog");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void disableSoftInputFromAppearing(EditText editText) {
        if (Build.VERSION.SDK_INT >= 11) {
            editText.setRawInputType(1);
            editText.setTextIsSelectable(true);
            return;
        }
        editText.setRawInputType(0);
        editText.setFocusable(true);
    }

    public static ArrayList<String> getNotOutBatsmen(ArrayList<String> arrayList, ArrayList<Batsman> arrayList2) {
        ArrayList<String> arrayList3 = new ArrayList<>(arrayList);
        Iterator<Batsman> it = arrayList2.iterator();
        while (it.hasNext()) {
            Batsman next = it.next();
            for (int i = 0; i < arrayList3.size(); i++) {
                if (next.getName().equals(arrayList3.get(i)) && !next.isOut().equals("Ret")) {
                    arrayList3.remove(i);
                }
            }
        }
        return arrayList3;
    }

    public static ArrayList<String> getAllTeamsNames(ArrayList<Match> arrayList) {
        ArrayList<String> arrayList2 = new ArrayList<>();
        Iterator<Match> it = arrayList.iterator();
        while (it.hasNext()) {
            Match next = it.next();
            if (!arrayList2.contains(next.getYourteam())) {
                arrayList2.add(next.getYourteam());
            }
            if (!arrayList2.contains(next.getOpponent())) {
                arrayList2.add(next.getOpponent());
            }
        }
        return arrayList2;
    }

    public static void shareDialogImage(DialogFragment dialogFragment, Activity activity) {
        if (!SharedPrefsHelper.isPro(activity)) {
            showUpgradeToProDialog(dialogFragment, activity);
        } else if (!PermissionAsker.checkAPIVersion()) {
            ImageSharer.shareDialogImage(dialogFragment, activity);
        } else if (PermissionAsker.checkIfAlreadyhavePermission(activity)) {
            ImageSharer.shareDialogImage(dialogFragment, activity);
        } else {
            PermissionAsker.requestForSpecificPermission(105, activity);
        }
    }

    public static void showUpgradeToProDialog(final DialogFragment dialogFragment, final Activity activity) {
        AnonymousClass8 r0 = new UpgradeToProDialog.UpgradeToProCallBack() {
            public void onPurchaseFailed() {
            }

            public void onPurchaseSuccessfull() {
                if (dialogFragment != null) {
                    UtilityFunctions.shareDialogImage(dialogFragment, activity);
                } else {
                    ImageSharer.shareImage(activity);
                }
            }
        };
        UpgradeToProDialog upgradeToProDialog = new UpgradeToProDialog();
        upgradeToProDialog.setListener(r0);
        upgradeToProDialog.show(activity.getFragmentManager(), "upgrade to PRO dialog");
    }

    public static String convertDoubleToTwoValuesAfterPoint(double d) {
        return String.format("%.2f", new Object[]{Double.valueOf(d)});
    }

    public static String getCurrentTime() {
        return new SimpleDateFormat("hh:mm a").format(new Date());
    }

    public static int getTotalBallsFromOvers(Team team, Match match) {
        return (team.getOversPlayed() * 6) + team.getOverballs();
    }

    public static int getNoOfPlayersWithPlayingXI(Team team) throws Exception {
        int i = 0;
        if (!(team == null || team.getPlayers() == null)) {
            Iterator<Player> it = team.getPlayers().iterator();
            while (it.hasNext()) {
                if (it.next().isInPlayingXI()) {
                    i++;
                }
            }
        }
        return i;
    }

    public static String getStringFromStringArrayList(ArrayList<String> arrayList) throws Exception {
        StringBuffer stringBuffer = new StringBuffer();
        Iterator<String> it = arrayList.iterator();
        while (it.hasNext()) {
            stringBuffer.append(it.next() + ":");
        }
        return stringBuffer.toString();
    }

    public static ArrayList<String> getStringArrayListFromStrings(String str) throws Exception {
        String[] split = str.split(":");
        ArrayList<String> arrayList = new ArrayList<>();
        for (int i = 0; i < split.length; i++) {
            if (split[i] != null && !split[i].isEmpty()) {
                arrayList.add(split[i]);
            }
        }
        return arrayList;
    }

    public static void copyOversFromSharedPrefsIntoDB(Context context) {
        if (DBHelper.db_Upgraded) {
            SharedPrefsHelper.checkOversCopied(context);
        }
    }

    public static ArrayList<NavigationItem> getNavigationMenuItems() {
        ArrayList<NavigationItem> arrayList = new ArrayList<>();
        arrayList.add(new NavigationItem(R.id.viewbatsmanhistory, R.drawable.ic_bats_man, "Batting Scorecard"));
        arrayList.add(new NavigationItem(R.id.viewbowlershistory, R.drawable.ic_bats_man, "Bowling Scorecard"));
        arrayList.add(new NavigationItem(R.id.viewovershistory, R.drawable.ic_bats_man, "Overs Scorecard"));
        arrayList.add(new NavigationItem(R.id.viewfallofwickets, R.drawable.ic_bats_man, "Fall of Wickets"));
        arrayList.add(new NavigationItem(R.id.viewPartenerships, R.drawable.ic_bats_man, "Partnerships"));
        arrayList.add(new NavigationItem(R.id.finishInnings, R.drawable.ic_bats_man, "Finish/Declare Innings"));
        arrayList.add(new NavigationItem(R.id.viewMatchDetails, R.drawable.ic_bats_man, "Match Details"));
        return arrayList;
    }

    public static ArrayList<Match> getSpecificTeamMatches(String str, ArrayList<Match> arrayList) {
        ArrayList<Match> arrayList2 = new ArrayList<>();
        Iterator<Match> it = arrayList.iterator();
        while (it.hasNext()) {
            Match next = it.next();
            if (next.getYourteam().equalsIgnoreCase(str) || next.getOpponent().equalsIgnoreCase(str)) {
                arrayList2.add(next);
            }
        }
        return arrayList2;
    }

    public static ArrayList<Long> getTeamIds(ArrayList<Match> arrayList, int i) throws Exception {
        ArrayList<Long> arrayList2 = new ArrayList<>();
        ArrayList arrayList3 = new ArrayList();
        Iterator<Match> it = arrayList.iterator();
        while (it.hasNext()) {
            Match next = it.next();
            if (i == 1 && next.isTestMatch) {
                arrayList3.add(next);
            } else if (i == 2 && !next.isTestMatch) {
                arrayList3.add(next);
            } else if (i == 3) {
                arrayList3.add(next);
            }
        }
        Iterator it2 = arrayList3.iterator();
        while (it2.hasNext()) {
            Match match = (Match) it2.next();
            arrayList2.add(Long.valueOf(match.getTeam_Yours_id()));
            arrayList2.add(Long.valueOf(match.getTeam_opp_id()));
            if (match.isTestMatch) {
                arrayList2.add(Long.valueOf(match.getTeam_Yours_id2()));
                arrayList2.add(Long.valueOf(match.getTeam_opp_id2()));
            }
        }
        return arrayList2;
    }

    public static void showAlertDialog(Context context, String str, String str2, String str3, String str4, DialogInterface.OnClickListener onClickListener) {
        new AlertDialog.Builder(context).setTitle(str).setMessage(str2).setPositiveButton(str3, onClickListener).setNegativeButton(str4, (DialogInterface.OnClickListener) null).create().show();
    }

    public static void openDocument(Context context, File file) {
        Intent intent = new Intent("android.intent.action.VIEW");
        String fileExtensionFromUrl = MimeTypeMap.getFileExtensionFromUrl(Uri.fromFile(file).toString());
        String mimeTypeFromExtension = MimeTypeMap.getSingleton().getMimeTypeFromExtension(fileExtensionFromUrl);
        if (fileExtensionFromUrl.equalsIgnoreCase("") || mimeTypeFromExtension == null) {
            intent.setDataAndType(FileProvider.getUriForFile(context, context.getResources().getString(R.string.file_provide_authority), file), "text/*");
        } else {
            intent.setDataAndType(FileProvider.getUriForFile(context, context.getResources().getString(R.string.file_provide_authority), file), mimeTypeFromExtension);
        }
        intent.addFlags(1);
        context.startActivity(Intent.createChooser(intent, "Choose an Application:"));
    }

    public static ArrayList<CheckBoxListItem> getCheckBoxListItemsFromStrings(ArrayList<String> arrayList, String str) {
        ArrayList<CheckBoxListItem> arrayList2 = new ArrayList<>();
        Iterator<String> it = arrayList.iterator();
        while (it.hasNext()) {
            String next = it.next();
            arrayList2.add(new GeneralOptions(next, str != null ? str.contains(next) : false));
        }
        return arrayList2;
    }
}
