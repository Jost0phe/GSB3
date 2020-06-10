/*package com.example.gsbandroidapp.ui;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.gsbandroidapp.R;

import org.w3c.dom.Text;

import java.util.List;

public class FicheList extends ArrayAdapter<Fiche> {
*//*    private Activity context;
    private List<Fiche>ficheList;

    public FicheList(Activity context, List<Fiche>ficheList){
        super(context, R.layout.list_layout, ficheList);
        this.context = context;
        this.ficheList = ficheList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.list_layout, null, true);

        TextView textViewValue1 = (TextView) listViewItem.findViewById(R.id.input_valeur1);
        TextView textViewValue2 = (TextView) listViewItem.findViewById(R.id.input_valeur2);

        Fiche fiche = ficheList.get(position);

        textViewValue1.setText(fiche.getValeur1());
        textViewValue2.setText(fiche.getValeur2());

        return listViewItem;
    }*//*
}*/
