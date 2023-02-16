package com.example.cricketscore;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.List;


public class TeamArrayAdapter extends ArrayAdapter<TeamModel> {
    private final Activity context;
    private final List<TeamModel> names;

    static class ViewHolder {
        public TextView text;
        public CheckBox checkBox;
    }

    public TeamArrayAdapter(Activity context, List<TeamModel> names) {
        super(context , R.layout.rowlayout, names);
        this.context = context;
        this.names = names;
    }

    public View getView(final int position, final View convertView, final ViewGroup parent) {
        View view = null;
        if(convertView == null) {
            LayoutInflater inflater = context.getLayoutInflater();
            view = inflater.inflate(R.layout.rowlayout, null);
            final ViewHolder viewHolder = new ViewHolder();
            viewHolder.text = (TextView) view.findViewById(R.id.TextView01);
            viewHolder.checkBox = (CheckBox) view.findViewById(R.id.CheckBox01);
            final View view2 = view;
           viewHolder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
               @Override
               public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                   TeamModel element = (TeamModel) viewHolder.checkBox.getTag();
                   element.setSelected(compoundButton.isChecked());
               }
           });

            view.setTag(viewHolder);
            viewHolder.checkBox.setTag(names.get(position));
        } else {
            view = convertView;
            ((ViewHolder) view.getTag()).checkBox.setTag(names.get(position));
        }

        ViewHolder holder = (ViewHolder) view.getTag();
        holder.text.setText(names.get(position).getName());

        holder.checkBox.setChecked(names.get(position).isSelected());

        return view;



    }
}
