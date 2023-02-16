package apps.shehryar.com.cricketscroingappPro.Utility_Classes;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import apps.shehryar.com.cricketscroingappPro.DBHelper;
import apps.shehryar.com.cricketscroingappPro.Match.Match;
import apps.shehryar.com.cricketscroingappPro.Team.Team;
import apps.shehryar.com.cricketscroingappPro.Team.TeamDetailsDialog;

public class AlertDialogBuilder {
    public static void showAlertDialog(Dialog dialog) {
        new AlertDialog.Builder(dialog.getContext()).setTitle(dialog.getTitle()).setMessage(dialog.getMsg()).setPositiveButton(dialog.getYesBtn(), dialog.getClickListener()).setNegativeButton(dialog.getNoBtn(), (DialogInterface.OnClickListener) null).show();
    }

    public static void showTipDialog(Context context) {
        if (context.getSharedPreferences(DBHelper.TABLE_TEAM, 0).getBoolean("showTipDialogmain2", true)) {
            new AlertDialog.Builder(context).setTitle("Tips").setMessage("1. Tap Batsman score to see batsman details like dots, singles, doubles etc\n\n2. Tap the Batting team name or batting team scores to see Batting team details including dots, singles, doubles etc and extras including no of byes, legbyes, wides and nos.").setPositiveButton("OK", (DialogInterface.OnClickListener) null).show();
            SharedPreferences.Editor edit = context.getSharedPreferences(DBHelper.TABLE_TEAM, 0).edit();
            edit.putBoolean("showTipDialogmain2", false);
            edit.commit();
        }
    }

    public static void showTipDialog2(Context context) {
        if (context.getSharedPreferences(DBHelper.TABLE_TEAM, 0).getBoolean("showTipDialoghistory2", true)) {
            new AlertDialog.Builder(context).setTitle("Tip").setMessage("1. Tap any Batsman to see batsman details like dots, singles, doubles etc\n\n2. Tap the Batting team name or batting team scores to see Batting team details including dots, singles, doubles etc and extras including no of byes, legbyes, wides and nos.").setPositiveButton("OK", (DialogInterface.OnClickListener) null).show();
            SharedPreferences.Editor edit = context.getSharedPreferences(DBHelper.TABLE_TEAM, 0).edit();
            edit.putBoolean("showTipDialoghistory2", false);
            edit.commit();
        }
    }

    public static void showTeamDetailsDialog(Match match, Team team, Activity activity) {
        TeamDetailsDialog teamDetailsDialog = new TeamDetailsDialog();
        team.setMatchTotalOvers(team.getMatchTotalOvers());
        Bundle bundle = new Bundle();
        bundle.putSerializable(DBHelper.TABLE_TEAM, team);
        bundle.putSerializable(DBHelper.TABLE_MATCH, match);
        teamDetailsDialog.setArguments(bundle);
        teamDetailsDialog.show(activity.getFragmentManager(), DBHelper.TABLE_TEAM);
    }

    public static void showParteneshipDisclaimerDialog(Context context) {
        if (context.getSharedPreferences(DBHelper.TABLE_TEAM, 0).getBoolean("showDisclaimerDialog", true)) {
            new AlertDialog.Builder(context).setTitle("Disclaimer").setMessage("In partenerships, batsman individual score might not be correct.").setPositiveButton("OK", (DialogInterface.OnClickListener) null).show();
            SharedPreferences.Editor edit = context.getSharedPreferences(DBHelper.TABLE_TEAM, 0).edit();
            edit.putBoolean("showDisclaimerDialog", false);
            edit.commit();
        }
    }

    public static void showWicketInfoDialog(Context context, String str) {
        showAlertDialog(new Dialog(context, "Wicket+" + str, "To add wicket on " + str + " Just tap 'W' and choose wicket type. You will get the option to choose " + str, "Ok", "", (DialogInterface.OnClickListener) null));
    }
}
