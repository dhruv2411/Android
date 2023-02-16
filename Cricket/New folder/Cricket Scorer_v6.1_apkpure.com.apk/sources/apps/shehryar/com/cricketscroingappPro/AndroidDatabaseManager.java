package apps.shehryar.com.cricketscroingappPro;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.internal.view.SupportMenu;
import android.support.v4.view.ViewCompat;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.actions.SearchIntents;
import com.itextpdf.text.pdf.codec.wmf.MetaDo;
import io.fabric.sdk.android.services.settings.SettingsJsonConstants;
import java.util.ArrayList;
import java.util.LinkedList;

public class AndroidDatabaseManager extends Activity implements AdapterView.OnItemClickListener {
    DBHelper dbm;
    HorizontalScrollView hsv;
    indexInfo info = new indexInfo();
    LinearLayout mainLayout;
    ScrollView mainscrollview;
    Button next;
    Button previous;
    Spinner select_table;
    TableLayout tableLayout;
    TableRow.LayoutParams tableRowParams;
    TextView tv;
    TextView tvmessage;

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
    }

    static class indexInfo {
        public static int currentpage = 0;
        public static int cursorpostion = 0;
        public static ArrayList<String> emptytablecolumnnames = null;
        public static int index = 10;
        public static boolean isCustomQuery = false;
        public static boolean isEmpty = false;
        public static Cursor maincursor = null;
        public static int numberofpages = 0;
        public static String table_name = "";
        public static ArrayList<String> tableheadernames;
        public static ArrayList<String> value_string;

        indexInfo() {
        }
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.dbm = new DBHelper(this);
        this.mainscrollview = new ScrollView(this);
        this.mainLayout = new LinearLayout(this);
        this.mainLayout.setOrientation(1);
        this.mainLayout.setBackgroundColor(-1);
        this.mainLayout.setScrollContainer(true);
        this.mainscrollview.addView(this.mainLayout);
        setContentView(this.mainscrollview);
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setPadding(0, 10, 0, 20);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(0, 150);
        layoutParams.weight = 1.0f;
        TextView textView = new TextView(this);
        textView.setText("Select Table");
        textView.setTextSize(22.0f);
        textView.setLayoutParams(layoutParams);
        this.select_table = new Spinner(this);
        this.select_table.setLayoutParams(layoutParams);
        linearLayout.addView(textView);
        linearLayout.addView(this.select_table);
        this.mainLayout.addView(linearLayout);
        this.hsv = new HorizontalScrollView(this);
        this.tableLayout = new TableLayout(this);
        this.tableLayout.setHorizontalScrollBarEnabled(true);
        this.hsv.addView(this.tableLayout);
        LinearLayout linearLayout2 = new LinearLayout(this);
        linearLayout2.setPadding(0, 20, 0, 10);
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-1, -2);
        layoutParams2.weight = 1.0f;
        TextView textView2 = new TextView(this);
        textView2.setText("No. Of Records : ");
        textView2.setTextSize(20.0f);
        textView2.setLayoutParams(layoutParams2);
        this.tv = new TextView(this);
        this.tv.setTextSize(20.0f);
        this.tv.setLayoutParams(layoutParams2);
        linearLayout2.addView(textView2);
        linearLayout2.addView(this.tv);
        this.mainLayout.addView(linearLayout2);
        final EditText editText = new EditText(this);
        editText.setVisibility(8);
        editText.setHint("Enter Your Query here and Click on Submit Query Button .Results will be displayed below");
        this.mainLayout.addView(editText);
        Button button = new Button(this);
        button.setVisibility(8);
        button.setText("Submit Query");
        button.setBackgroundColor(Color.parseColor("#BAE7F6"));
        this.mainLayout.addView(button);
        TextView textView3 = new TextView(this);
        textView3.setText("Click on the row below to update values or delete the tuple");
        textView3.setPadding(0, 5, 0, 5);
        Spinner spinner = new Spinner(this);
        this.mainLayout.addView(spinner);
        this.mainLayout.addView(textView3);
        this.hsv.setPadding(0, 10, 0, 10);
        this.hsv.setScrollbarFadingEnabled(false);
        this.hsv.setScrollBarStyle(50331648);
        this.mainLayout.addView(this.hsv);
        LinearLayout linearLayout3 = new LinearLayout(this);
        this.previous = new Button(this);
        this.previous.setText("Previous");
        this.previous.setBackgroundColor(Color.parseColor("#BAE7F6"));
        this.previous.setLayoutParams(layoutParams2);
        this.next = new Button(this);
        this.next.setText("Next");
        this.next.setBackgroundColor(Color.parseColor("#BAE7F6"));
        this.next.setLayoutParams(layoutParams2);
        TextView textView4 = new TextView(this);
        textView4.setLayoutParams(layoutParams2);
        linearLayout3.setPadding(0, 10, 0, 10);
        linearLayout3.addView(this.previous);
        linearLayout3.addView(textView4);
        linearLayout3.addView(this.next);
        this.mainLayout.addView(linearLayout3);
        this.tvmessage = new TextView(this);
        this.tvmessage.setText("Error Messages will be displayed here");
        this.tvmessage.setTextSize(18.0f);
        this.mainLayout.addView(this.tvmessage);
        Button button2 = new Button(this);
        button2.setText("Custom Query");
        button2.setBackgroundColor(Color.parseColor("#BAE7F6"));
        this.mainLayout.addView(button2);
        final LinearLayout linearLayout4 = linearLayout2;
        AnonymousClass1 r8 = r0;
        final Spinner spinner2 = spinner;
        Button button3 = button2;
        final TextView textView5 = textView3;
        final EditText editText2 = editText;
        LinearLayout linearLayout5 = linearLayout3;
        final Button button4 = button;
        Spinner spinner3 = spinner;
        final Button button5 = button3;
        AnonymousClass1 r0 = new View.OnClickListener() {
            public void onClick(View view) {
                indexInfo.isCustomQuery = true;
                linearLayout4.setVisibility(8);
                spinner2.setVisibility(8);
                textView5.setVisibility(8);
                editText2.setVisibility(0);
                button4.setVisibility(0);
                AndroidDatabaseManager.this.select_table.setSelection(0);
                button5.setVisibility(8);
            }
        };
        button5.setOnClickListener(r8);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                AndroidDatabaseManager.this.tableLayout.removeAllViews();
                button5.setVisibility(8);
                String obj = editText.getText().toString();
                Log.d(SearchIntents.EXTRA_QUERY, obj);
                ArrayList<Cursor> data = AndroidDatabaseManager.this.dbm.getData(obj);
                Cursor cursor = data.get(0);
                Cursor cursor2 = data.get(1);
                cursor2.moveToLast();
                if (cursor2.getString(0).equalsIgnoreCase("Success")) {
                    AndroidDatabaseManager.this.tvmessage.setBackgroundColor(Color.parseColor("#2ecc71"));
                    if (cursor != null) {
                        TextView textView = AndroidDatabaseManager.this.tvmessage;
                        textView.setText("Queru Executed successfully.Number of rows returned :" + cursor.getCount());
                        if (cursor.getCount() > 0) {
                            indexInfo.maincursor = cursor;
                            AndroidDatabaseManager.this.refreshTable(1);
                            return;
                        }
                        return;
                    }
                    AndroidDatabaseManager.this.tvmessage.setText("Queru Executed successfully");
                    AndroidDatabaseManager.this.refreshTable(1);
                    return;
                }
                AndroidDatabaseManager.this.tvmessage.setBackgroundColor(Color.parseColor("#e74c3c"));
                TextView textView2 = AndroidDatabaseManager.this.tvmessage;
                textView2.setText("Error:" + cursor2.getString(0));
            }
        });
        this.tableRowParams = new TableRow.LayoutParams(-2, -2);
        this.tableRowParams.setMargins(0, 0, 2, 0);
        ArrayList<Cursor> data = this.dbm.getData("SELECT name _id FROM sqlite_master WHERE type ='table'");
        Cursor cursor = data.get(0);
        Cursor cursor2 = data.get(1);
        cursor2.moveToLast();
        Log.d("Message from sql = ", cursor2.getString(0));
        ArrayList arrayList = new ArrayList();
        if (cursor != null) {
            cursor.moveToFirst();
            arrayList.add("click here");
            do {
                arrayList.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }
        AnonymousClass3 r1 = new ArrayAdapter<String>(this, 17367048, arrayList) {
            public View getView(int i, View view, ViewGroup viewGroup) {
                View view2 = super.getView(i, view, viewGroup);
                view2.setBackgroundColor(-1);
                TextView textView = (TextView) view2;
                textView.setTextSize(20.0f);
                return textView;
            }

            public View getDropDownView(int i, View view, ViewGroup viewGroup) {
                View dropDownView = super.getDropDownView(i, view, viewGroup);
                dropDownView.setBackgroundColor(-1);
                return dropDownView;
            }
        };
        r1.setDropDownViewResource(17367049);
        if (r1 != null) {
            this.select_table.setAdapter(r1);
        }
        final LinearLayout linearLayout6 = linearLayout2;
        final LinearLayout linearLayout7 = linearLayout5;
        final Spinner spinner4 = spinner3;
        final TextView textView6 = textView3;
        final EditText editText3 = editText;
        Button button6 = button5;
        final Button button7 = button;
        AnonymousClass4 r13 = r0;
        final Button button8 = button6;
        Spinner spinner5 = this.select_table;
        final Cursor cursor3 = cursor;
        AnonymousClass4 r02 = new AdapterView.OnItemSelectedListener() {
            public void onNothingSelected(AdapterView<?> adapterView) {
            }

            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
                if (i == 0 && !indexInfo.isCustomQuery) {
                    linearLayout6.setVisibility(8);
                    AndroidDatabaseManager.this.hsv.setVisibility(8);
                    linearLayout7.setVisibility(8);
                    spinner4.setVisibility(8);
                    textView6.setVisibility(8);
                    AndroidDatabaseManager.this.tvmessage.setVisibility(8);
                    editText3.setVisibility(8);
                    button7.setVisibility(8);
                    button8.setVisibility(8);
                }
                if (i != 0) {
                    linearLayout6.setVisibility(0);
                    spinner4.setVisibility(0);
                    textView6.setVisibility(0);
                    editText3.setVisibility(8);
                    button7.setVisibility(8);
                    button8.setVisibility(0);
                    AndroidDatabaseManager.this.hsv.setVisibility(0);
                    AndroidDatabaseManager.this.tvmessage.setVisibility(0);
                    linearLayout7.setVisibility(0);
                    int i2 = i - 1;
                    cursor3.moveToPosition(i2);
                    indexInfo.cursorpostion = i2;
                    Log.d("selected table name is", "" + cursor3.getString(0));
                    indexInfo.table_name = cursor3.getString(0);
                    AndroidDatabaseManager.this.tvmessage.setText("Error Messages will be displayed here");
                    AndroidDatabaseManager.this.tvmessage.setBackgroundColor(-1);
                    AndroidDatabaseManager.this.tableLayout.removeAllViews();
                    ArrayList arrayList = new ArrayList();
                    arrayList.add("Click here to change this table");
                    arrayList.add("Add row to this table");
                    arrayList.add("Delete this table");
                    arrayList.add("Drop this table");
                    new ArrayAdapter(AndroidDatabaseManager.this.getApplicationContext(), 17367049, arrayList).setDropDownViewResource(17367048);
                    AnonymousClass1 r11 = new ArrayAdapter<String>(AndroidDatabaseManager.this, 17367048, arrayList) {
                        public View getView(int i, View view, ViewGroup viewGroup) {
                            View view2 = super.getView(i, view, viewGroup);
                            view2.setBackgroundColor(-1);
                            TextView textView = (TextView) view2;
                            textView.setTextSize(20.0f);
                            return textView;
                        }

                        public View getDropDownView(int i, View view, ViewGroup viewGroup) {
                            View dropDownView = super.getDropDownView(i, view, viewGroup);
                            dropDownView.setBackgroundColor(-1);
                            return dropDownView;
                        }
                    };
                    r11.setDropDownViewResource(17367049);
                    spinner4.setAdapter(r11);
                    String str = "select * from " + cursor3.getString(0);
                    Log.d("", "" + str);
                    Cursor cursor = AndroidDatabaseManager.this.dbm.getData(str).get(0);
                    indexInfo.maincursor = cursor;
                    if (cursor != null) {
                        int count = cursor.getCount();
                        indexInfo.isEmpty = false;
                        Log.d("counts", "" + count);
                        AndroidDatabaseManager.this.tv.setText("" + count);
                        spinner4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            public void onNothingSelected(AdapterView<?> adapterView) {
                            }

                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
                                ((TextView) adapterView.getChildAt(0)).setTextColor(Color.rgb(0, 0, 0));
                                if (spinner4.getSelectedItem().toString().equals("Drop this table")) {
                                    AndroidDatabaseManager.this.runOnUiThread(new Runnable() {
                                        public void run() {
                                            if (!AndroidDatabaseManager.this.isFinishing()) {
                                                AlertDialog.Builder title = new AlertDialog.Builder(AndroidDatabaseManager.this).setTitle("Are you sure ?");
                                                title.setMessage("Pressing yes will remove " + indexInfo.table_name + " table from database").setPositiveButton("yes", new DialogInterface.OnClickListener() {
                                                    public void onClick(DialogInterface dialogInterface, int i) {
                                                        Cursor cursor = AndroidDatabaseManager.this.dbm.getData("Drop table " + indexInfo.table_name).get(1);
                                                        cursor.moveToLast();
                                                        Log.d("Drop table Mesage", cursor.getString(0));
                                                        if (cursor.getString(0).equalsIgnoreCase("Success")) {
                                                            AndroidDatabaseManager.this.tvmessage.setBackgroundColor(Color.parseColor("#2ecc71"));
                                                            TextView textView = AndroidDatabaseManager.this.tvmessage;
                                                            textView.setText(indexInfo.table_name + "Dropped successfully");
                                                            AndroidDatabaseManager.this.refreshactivity();
                                                            return;
                                                        }
                                                        AndroidDatabaseManager.this.tvmessage.setBackgroundColor(Color.parseColor("#e74c3c"));
                                                        TextView textView2 = AndroidDatabaseManager.this.tvmessage;
                                                        textView2.setText("Error:" + cursor.getString(0));
                                                        spinner4.setSelection(0);
                                                    }
                                                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                                                    public void onClick(DialogInterface dialogInterface, int i) {
                                                        spinner4.setSelection(0);
                                                    }
                                                }).create().show();
                                            }
                                        }
                                    });
                                }
                                if (spinner4.getSelectedItem().toString().equals("Delete this table")) {
                                    AndroidDatabaseManager.this.runOnUiThread(new Runnable() {
                                        public void run() {
                                            if (!AndroidDatabaseManager.this.isFinishing()) {
                                                AlertDialog.Builder title = new AlertDialog.Builder(AndroidDatabaseManager.this).setTitle("Are you sure?");
                                                title.setMessage("Clicking on yes will delete all the contents of " + indexInfo.table_name + " table from database").setPositiveButton("yes", new DialogInterface.OnClickListener() {
                                                    public void onClick(DialogInterface dialogInterface, int i) {
                                                        String str = "Delete  from " + indexInfo.table_name;
                                                        Log.d("delete table query", str);
                                                        Cursor cursor = AndroidDatabaseManager.this.dbm.getData(str).get(1);
                                                        cursor.moveToLast();
                                                        Log.d("Delete table Mesage", cursor.getString(0));
                                                        if (cursor.getString(0).equalsIgnoreCase("Success")) {
                                                            AndroidDatabaseManager.this.tvmessage.setBackgroundColor(Color.parseColor("#2ecc71"));
                                                            AndroidDatabaseManager.this.tvmessage.setText(indexInfo.table_name + " table content deleted successfully");
                                                            indexInfo.isEmpty = true;
                                                            AndroidDatabaseManager.this.refreshTable(0);
                                                            return;
                                                        }
                                                        AndroidDatabaseManager.this.tvmessage.setBackgroundColor(Color.parseColor("#e74c3c"));
                                                        AndroidDatabaseManager.this.tvmessage.setText("Error:" + cursor.getString(0));
                                                        spinner4.setSelection(0);
                                                    }
                                                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                                                    public void onClick(DialogInterface dialogInterface, int i) {
                                                        spinner4.setSelection(0);
                                                    }
                                                }).create().show();
                                            }
                                        }
                                    });
                                }
                                if (spinner4.getSelectedItem().toString().equals("Add row to this table")) {
                                    final LinkedList linkedList = new LinkedList();
                                    final LinkedList linkedList2 = new LinkedList();
                                    final ScrollView scrollView = new ScrollView(AndroidDatabaseManager.this);
                                    Cursor cursor = indexInfo.maincursor;
                                    if (indexInfo.isEmpty) {
                                        AndroidDatabaseManager.this.getcolumnnames();
                                        for (int i2 = 0; i2 < indexInfo.emptytablecolumnnames.size(); i2++) {
                                            TextView textView = new TextView(AndroidDatabaseManager.this.getApplicationContext());
                                            textView.setText(indexInfo.emptytablecolumnnames.get(i2));
                                            linkedList.add(textView);
                                        }
                                        for (int i3 = 0; i3 < linkedList.size(); i3++) {
                                            linkedList2.add(new EditText(AndroidDatabaseManager.this.getApplicationContext()));
                                        }
                                    } else {
                                        for (int i4 = 0; i4 < cursor.getColumnCount(); i4++) {
                                            String columnName = cursor.getColumnName(i4);
                                            TextView textView2 = new TextView(AndroidDatabaseManager.this.getApplicationContext());
                                            textView2.setText(columnName);
                                            linkedList.add(textView2);
                                        }
                                        for (int i5 = 0; i5 < linkedList.size(); i5++) {
                                            linkedList2.add(new EditText(AndroidDatabaseManager.this.getApplicationContext()));
                                        }
                                    }
                                    RelativeLayout relativeLayout = new RelativeLayout(AndroidDatabaseManager.this);
                                    new RelativeLayout.LayoutParams(-2, -2).addRule(10);
                                    for (int i6 = 0; i6 < linkedList.size(); i6++) {
                                        TextView textView3 = (TextView) linkedList.get(i6);
                                        EditText editText = (EditText) linkedList2.get(i6);
                                        int i7 = i6 + SettingsJsonConstants.ANALYTICS_FLUSH_INTERVAL_SECS_DEFAULT;
                                        textView3.setId(i6 + 400);
                                        textView3.setTextColor(Color.parseColor("#000000"));
                                        editText.setBackgroundColor(Color.parseColor("#F2F2F2"));
                                        editText.setTextColor(Color.parseColor("#000000"));
                                        editText.setId(i6 + 500);
                                        LinearLayout linearLayout = new LinearLayout(AndroidDatabaseManager.this);
                                        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(0, 100);
                                        layoutParams.weight = 1.0f;
                                        linearLayout.addView(textView3, layoutParams);
                                        linearLayout.addView(editText, layoutParams);
                                        linearLayout.setId(i7);
                                        Log.d("Edit Text Value", "" + editText.getText().toString());
                                        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-1, -2);
                                        layoutParams2.addRule(3, linearLayout.getId() + -1);
                                        layoutParams2.setMargins(0, 20, 0, 0);
                                        relativeLayout.addView(linearLayout, layoutParams2);
                                    }
                                    relativeLayout.setBackgroundColor(-1);
                                    scrollView.addView(relativeLayout);
                                    Log.d("Button Clicked", "");
                                    AndroidDatabaseManager.this.runOnUiThread(new Runnable() {
                                        public void run() {
                                            if (!AndroidDatabaseManager.this.isFinishing()) {
                                                new AlertDialog.Builder(AndroidDatabaseManager.this).setTitle("values").setCancelable(false).setView(scrollView).setPositiveButton("Add", new DialogInterface.OnClickListener() {
                                                    public void onClick(DialogInterface dialogInterface, int i) {
                                                        indexInfo.index = 10;
                                                        String str = "Insert into " + indexInfo.table_name + " (";
                                                        for (int i2 = 0; i2 < linkedList.size(); i2++) {
                                                            TextView textView = (TextView) linkedList.get(i2);
                                                            textView.getText().toString();
                                                            if (i2 == linkedList.size() - 1) {
                                                                str = str + textView.getText().toString();
                                                            } else {
                                                                str = str + textView.getText().toString() + ", ";
                                                            }
                                                        }
                                                        String str2 = str + " ) VALUES ( ";
                                                        for (int i3 = 0; i3 < linkedList.size(); i3++) {
                                                            EditText editText = (EditText) linkedList2.get(i3);
                                                            editText.getText().toString();
                                                            if (i3 == linkedList.size() - 1) {
                                                                str2 = str2 + "'" + editText.getText().toString() + "' ) ";
                                                            } else {
                                                                str2 = str2 + "'" + editText.getText().toString() + "' , ";
                                                            }
                                                        }
                                                        Log.d("Insert Query", str2);
                                                        Cursor cursor = AndroidDatabaseManager.this.dbm.getData(str2).get(1);
                                                        cursor.moveToLast();
                                                        Log.d("Add New Row", cursor.getString(0));
                                                        if (cursor.getString(0).equalsIgnoreCase("Success")) {
                                                            AndroidDatabaseManager.this.tvmessage.setBackgroundColor(Color.parseColor("#2ecc71"));
                                                            AndroidDatabaseManager.this.tvmessage.setText("New Row added succesfully to " + indexInfo.table_name);
                                                            AndroidDatabaseManager.this.refreshTable(0);
                                                            return;
                                                        }
                                                        AndroidDatabaseManager.this.tvmessage.setBackgroundColor(Color.parseColor("#e74c3c"));
                                                        AndroidDatabaseManager.this.tvmessage.setText("Error:" + cursor.getString(0));
                                                        spinner4.setSelection(0);
                                                    }
                                                }).setNegativeButton("close", new DialogInterface.OnClickListener() {
                                                    public void onClick(DialogInterface dialogInterface, int i) {
                                                        spinner4.setSelection(0);
                                                    }
                                                }).create().show();
                                            }
                                        }
                                    });
                                }
                            }
                        });
                        TableRow tableRow = new TableRow(AndroidDatabaseManager.this.getApplicationContext());
                        tableRow.setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
                        tableRow.setPadding(0, 2, 0, 2);
                        for (int i3 = 0; i3 < cursor.getColumnCount(); i3++) {
                            LinearLayout linearLayout = new LinearLayout(AndroidDatabaseManager.this);
                            linearLayout.setBackgroundColor(-1);
                            linearLayout.setLayoutParams(AndroidDatabaseManager.this.tableRowParams);
                            TextView textView = new TextView(AndroidDatabaseManager.this.getApplicationContext());
                            textView.setPadding(0, 0, 4, 3);
                            textView.setText("" + cursor.getColumnName(i3));
                            textView.setTextColor(Color.parseColor("#000000"));
                            linearLayout.addView(textView);
                            tableRow.addView(linearLayout);
                        }
                        AndroidDatabaseManager.this.tableLayout.addView(tableRow);
                        cursor.moveToFirst();
                        AndroidDatabaseManager.this.paginatetable(cursor.getCount());
                        return;
                    }
                    textView6.setVisibility(8);
                    AndroidDatabaseManager.this.tableLayout.removeAllViews();
                    AndroidDatabaseManager.this.getcolumnnames();
                    TableRow tableRow2 = new TableRow(AndroidDatabaseManager.this.getApplicationContext());
                    tableRow2.setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
                    tableRow2.setPadding(0, 2, 0, 2);
                    LinearLayout linearLayout2 = new LinearLayout(AndroidDatabaseManager.this);
                    linearLayout2.setBackgroundColor(-1);
                    linearLayout2.setLayoutParams(AndroidDatabaseManager.this.tableRowParams);
                    TextView textView2 = new TextView(AndroidDatabaseManager.this.getApplicationContext());
                    textView2.setPadding(0, 0, 4, 3);
                    textView2.setText("   Table   Is   Empty   ");
                    textView2.setTextSize(30.0f);
                    textView2.setTextColor(SupportMenu.CATEGORY_MASK);
                    linearLayout2.addView(textView2);
                    tableRow2.addView(linearLayout2);
                    AndroidDatabaseManager.this.tableLayout.addView(tableRow2);
                    AndroidDatabaseManager.this.tv.setText("0");
                }
            }
        };
        spinner5.setOnItemSelectedListener(r13);
    }

    public void getcolumnnames() {
        DBHelper dBHelper = this.dbm;
        Cursor cursor = dBHelper.getData("PRAGMA table_info(" + indexInfo.table_name + ")").get(0);
        indexInfo.isEmpty = true;
        if (cursor != null) {
            indexInfo.isEmpty = true;
            ArrayList<String> arrayList = new ArrayList<>();
            cursor.moveToFirst();
            do {
                arrayList.add(cursor.getString(1));
            } while (cursor.moveToNext());
            indexInfo.emptytablecolumnnames = arrayList;
        }
    }

    public void updateDeletePopup(int i) {
        Cursor cursor = indexInfo.maincursor;
        ArrayList arrayList = new ArrayList();
        arrayList.add("Click Here to Change this row");
        arrayList.add("Update this row");
        arrayList.add("Delete this row");
        final ArrayList<String> arrayList2 = indexInfo.value_string;
        final LinkedList linkedList = new LinkedList();
        final LinkedList linkedList2 = new LinkedList();
        for (int i2 = 0; i2 < cursor.getColumnCount(); i2++) {
            String columnName = cursor.getColumnName(i2);
            TextView textView = new TextView(getApplicationContext());
            textView.setText(columnName);
            linkedList.add(textView);
        }
        for (int i3 = 0; i3 < linkedList.size(); i3++) {
            String str = arrayList2.get(i3);
            EditText editText = new EditText(getApplicationContext());
            arrayList2.add(str);
            editText.setText(str);
            linkedList2.add(editText);
        }
        RelativeLayout relativeLayout = new RelativeLayout(this);
        relativeLayout.setBackgroundColor(-1);
        new RelativeLayout.LayoutParams(-2, -2).addRule(10);
        ScrollView scrollView = new ScrollView(this);
        LinearLayout linearLayout = new LinearLayout(this);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        layoutParams.setMargins(0, 20, 0, 0);
        Spinner spinner = new Spinner(getApplicationContext());
        AnonymousClass5 r14 = new ArrayAdapter<String>(this, 17367048, arrayList) {
            public View getView(int i, View view, ViewGroup viewGroup) {
                View view2 = super.getView(i, view, viewGroup);
                view2.setBackgroundColor(-1);
                TextView textView = (TextView) view2;
                textView.setTextSize(20.0f);
                return textView;
            }

            public View getDropDownView(int i, View view, ViewGroup viewGroup) {
                View dropDownView = super.getDropDownView(i, view, viewGroup);
                dropDownView.setBackgroundColor(-1);
                return dropDownView;
            }
        };
        r14.setDropDownViewResource(17367049);
        spinner.setAdapter(r14);
        linearLayout.setId(MetaDo.META_PAINTREGION);
        linearLayout.addView(spinner, layoutParams);
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams2.addRule(3, 0);
        relativeLayout.addView(linearLayout, layoutParams2);
        for (int i4 = 0; i4 < linkedList.size(); i4++) {
            TextView textView2 = (TextView) linkedList.get(i4);
            EditText editText2 = (EditText) linkedList2.get(i4);
            textView2.setId(i4 + 100);
            textView2.setTextColor(Color.parseColor("#000000"));
            editText2.setBackgroundColor(Color.parseColor("#F2F2F2"));
            editText2.setTextColor(Color.parseColor("#000000"));
            editText2.setId(i4 + 200);
            Log.d("text View Value", "" + textView2.getText().toString());
            LinearLayout linearLayout2 = new LinearLayout(this);
            linearLayout2.setBackgroundColor(Color.parseColor("#FFFFFF"));
            linearLayout2.setId(i4 + 300);
            LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(0, 100);
            layoutParams3.weight = 1.0f;
            textView2.setLayoutParams(layoutParams3);
            editText2.setLayoutParams(layoutParams3);
            linearLayout2.addView(textView2);
            linearLayout2.addView(editText2);
            Log.d("Edit Text Value", "" + editText2.getText().toString());
            RelativeLayout.LayoutParams layoutParams4 = new RelativeLayout.LayoutParams(-1, -2);
            layoutParams4.addRule(3, linearLayout2.getId() + -1);
            layoutParams4.setMargins(0, 20, 0, 0);
            linearLayout2.getId();
            relativeLayout.addView(linearLayout2, layoutParams4);
        }
        scrollView.addView(relativeLayout);
        final ScrollView scrollView2 = scrollView;
        final Spinner spinner2 = spinner;
        runOnUiThread(new Runnable() {
            public void run() {
                if (!AndroidDatabaseManager.this.isFinishing()) {
                    new AlertDialog.Builder(AndroidDatabaseManager.this).setTitle("values").setView(scrollView2).setCancelable(false).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogInterface, int i) {
                            String obj = spinner2.getSelectedItem().toString();
                            if (obj.equalsIgnoreCase("Update this row")) {
                                indexInfo.index = 10;
                                String str = "UPDATE " + indexInfo.table_name + " SET ";
                                for (int i2 = 0; i2 < linkedList.size(); i2++) {
                                    TextView textView = (TextView) linkedList.get(i2);
                                    EditText editText = (EditText) linkedList2.get(i2);
                                    if (!editText.getText().toString().equals("null")) {
                                        String str2 = str + textView.getText().toString() + " = ";
                                        if (i2 == linkedList.size() - 1) {
                                            str = str2 + "'" + editText.getText().toString() + "'";
                                        } else {
                                            str = str2 + "'" + editText.getText().toString() + "' , ";
                                        }
                                    }
                                }
                                String str3 = str + " where ";
                                for (int i3 = 0; i3 < linkedList.size(); i3++) {
                                    TextView textView2 = (TextView) linkedList.get(i3);
                                    if (!((String) arrayList2.get(i3)).equals("null")) {
                                        String str4 = str3 + textView2.getText().toString() + " = ";
                                        if (i3 == linkedList.size() - 1) {
                                            str3 = str4 + "'" + ((String) arrayList2.get(i3)) + "' ";
                                        } else {
                                            str3 = str4 + "'" + ((String) arrayList2.get(i3)) + "' and ";
                                        }
                                    }
                                }
                                Log.d("Update Query", str3);
                                Cursor cursor = AndroidDatabaseManager.this.dbm.getData(str3).get(1);
                                cursor.moveToLast();
                                Log.d("Update Mesage", cursor.getString(0));
                                if (cursor.getString(0).equalsIgnoreCase("Success")) {
                                    AndroidDatabaseManager.this.tvmessage.setBackgroundColor(Color.parseColor("#2ecc71"));
                                    AndroidDatabaseManager.this.tvmessage.setText(indexInfo.table_name + " table Updated Successfully");
                                    AndroidDatabaseManager.this.refreshTable(0);
                                } else {
                                    AndroidDatabaseManager.this.tvmessage.setBackgroundColor(Color.parseColor("#e74c3c"));
                                    AndroidDatabaseManager.this.tvmessage.setText("Error:" + cursor.getString(0));
                                }
                            }
                            if (obj.equalsIgnoreCase("Delete this row")) {
                                indexInfo.index = 10;
                                String str5 = "DELETE FROM " + indexInfo.table_name + " WHERE ";
                                for (int i4 = 0; i4 < linkedList.size(); i4++) {
                                    TextView textView3 = (TextView) linkedList.get(i4);
                                    if (!((String) arrayList2.get(i4)).equals("null")) {
                                        String str6 = str5 + textView3.getText().toString() + " = ";
                                        if (i4 == linkedList.size() - 1) {
                                            str5 = str6 + "'" + ((String) arrayList2.get(i4)) + "' ";
                                        } else {
                                            str5 = str6 + "'" + ((String) arrayList2.get(i4)) + "' and ";
                                        }
                                    }
                                }
                                Log.d("Delete Query", str5);
                                AndroidDatabaseManager.this.dbm.getData(str5);
                                Cursor cursor2 = AndroidDatabaseManager.this.dbm.getData(str5).get(1);
                                cursor2.moveToLast();
                                Log.d("Update Mesage", cursor2.getString(0));
                                if (cursor2.getString(0).equalsIgnoreCase("Success")) {
                                    AndroidDatabaseManager.this.tvmessage.setBackgroundColor(Color.parseColor("#2ecc71"));
                                    AndroidDatabaseManager.this.tvmessage.setText("Row deleted from " + indexInfo.table_name + " table");
                                    AndroidDatabaseManager.this.refreshTable(0);
                                    return;
                                }
                                AndroidDatabaseManager.this.tvmessage.setBackgroundColor(Color.parseColor("#e74c3c"));
                                AndroidDatabaseManager.this.tvmessage.setText("Error:" + cursor2.getString(0));
                            }
                        }
                    }).setNegativeButton("close", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogInterface, int i) {
                        }
                    }).create().show();
                }
            }
        });
    }

    public void refreshactivity() {
        finish();
        startActivity(getIntent());
    }

    public void refreshTable(int i) {
        Cursor cursor;
        this.tableLayout.removeAllViews();
        if (i == 0) {
            cursor = this.dbm.getData("select * from " + indexInfo.table_name).get(0);
            indexInfo.maincursor = cursor;
        } else {
            cursor = null;
        }
        if (i == 1) {
            cursor = indexInfo.maincursor;
        }
        if (cursor != null) {
            int count = cursor.getCount();
            Log.d("counts", "" + count);
            this.tv.setText("" + count);
            TableRow tableRow = new TableRow(getApplicationContext());
            tableRow.setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
            tableRow.setPadding(0, 2, 0, 2);
            for (int i2 = 0; i2 < cursor.getColumnCount(); i2++) {
                LinearLayout linearLayout = new LinearLayout(this);
                linearLayout.setBackgroundColor(-1);
                linearLayout.setLayoutParams(this.tableRowParams);
                TextView textView = new TextView(getApplicationContext());
                textView.setPadding(0, 0, 4, 3);
                textView.setText("" + cursor.getColumnName(i2));
                textView.setTextColor(Color.parseColor("#000000"));
                linearLayout.addView(textView);
                tableRow.addView(linearLayout);
            }
            this.tableLayout.addView(tableRow);
            cursor.moveToFirst();
            paginatetable(cursor.getCount());
            return;
        }
        TableRow tableRow2 = new TableRow(getApplicationContext());
        tableRow2.setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
        tableRow2.setPadding(0, 2, 0, 2);
        LinearLayout linearLayout2 = new LinearLayout(this);
        linearLayout2.setBackgroundColor(-1);
        linearLayout2.setLayoutParams(this.tableRowParams);
        TextView textView2 = new TextView(getApplicationContext());
        textView2.setPadding(0, 0, 4, 3);
        textView2.setText("   Table   Is   Empty   ");
        textView2.setTextSize(30.0f);
        textView2.setTextColor(SupportMenu.CATEGORY_MASK);
        linearLayout2.addView(textView2);
        tableRow2.addView(linearLayout2);
        this.tableLayout.addView(tableRow2);
        this.tv.setText("0");
    }

    public void paginatetable(int i) {
        final Cursor cursor = indexInfo.maincursor;
        indexInfo.numberofpages = (cursor.getCount() / 10) + 1;
        indexInfo.currentpage = 1;
        cursor.moveToFirst();
        int i2 = 0;
        do {
            final TableRow tableRow = new TableRow(getApplicationContext());
            tableRow.setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
            tableRow.setPadding(0, 2, 0, 2);
            for (int i3 = 0; i3 < cursor.getColumnCount(); i3++) {
                LinearLayout linearLayout = new LinearLayout(this);
                linearLayout.setBackgroundColor(-1);
                linearLayout.setLayoutParams(this.tableRowParams);
                TextView textView = new TextView(getApplicationContext());
                String str = "";
                try {
                    str = cursor.getString(i3);
                } catch (Exception unused) {
                }
                textView.setText(str);
                textView.setTextColor(Color.parseColor("#000000"));
                textView.setPadding(0, 0, 4, 3);
                linearLayout.addView(textView);
                tableRow.addView(linearLayout);
            }
            tableRow.setVisibility(0);
            i2++;
            tableRow.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    ArrayList<String> arrayList = new ArrayList<>();
                    for (int i = 0; i < cursor.getColumnCount(); i++) {
                        arrayList.add(((TextView) ((LinearLayout) tableRow.getChildAt(i)).getChildAt(0)).getText().toString());
                    }
                    indexInfo.value_string = arrayList;
                    AndroidDatabaseManager.this.updateDeletePopup(0);
                }
            });
            this.tableLayout.addView(tableRow);
            if (!cursor.moveToNext()) {
                break;
            }
        } while (i2 < 10);
        indexInfo.index = i2;
        this.previous.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                int i = (indexInfo.currentpage - 2) * 10;
                if (indexInfo.currentpage == 1) {
                    Toast.makeText(AndroidDatabaseManager.this.getApplicationContext(), "This is the first page", 1).show();
                    return;
                }
                indexInfo.currentpage--;
                cursor.moveToPosition(i);
                boolean z = true;
                for (int i2 = 1; i2 < AndroidDatabaseManager.this.tableLayout.getChildCount(); i2++) {
                    TableRow tableRow = (TableRow) AndroidDatabaseManager.this.tableLayout.getChildAt(i2);
                    if (z) {
                        tableRow.setVisibility(0);
                        for (int i3 = 0; i3 < tableRow.getChildCount(); i3++) {
                            ((TextView) ((LinearLayout) tableRow.getChildAt(i3)).getChildAt(0)).setText("" + cursor.getString(i3));
                        }
                        z = !cursor.isLast();
                        if (!cursor.isLast()) {
                            cursor.moveToNext();
                        }
                    } else {
                        tableRow.setVisibility(8);
                    }
                }
                indexInfo.index = i;
                Log.d("index =", "" + indexInfo.index);
            }
        });
        this.next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (indexInfo.currentpage >= indexInfo.numberofpages) {
                    Toast.makeText(AndroidDatabaseManager.this.getApplicationContext(), "This is the last page", 1).show();
                    return;
                }
                indexInfo.currentpage++;
                boolean z = true;
                for (int i = 1; i < AndroidDatabaseManager.this.tableLayout.getChildCount(); i++) {
                    TableRow tableRow = (TableRow) AndroidDatabaseManager.this.tableLayout.getChildAt(i);
                    if (z) {
                        tableRow.setVisibility(0);
                        for (int i2 = 0; i2 < tableRow.getChildCount(); i2++) {
                            ((TextView) ((LinearLayout) tableRow.getChildAt(i2)).getChildAt(0)).setText("" + cursor.getString(i2));
                        }
                        z = !cursor.isLast();
                        if (!cursor.isLast()) {
                            cursor.moveToNext();
                        }
                    } else {
                        tableRow.setVisibility(8);
                    }
                }
            }
        });
    }
}
