package org.i_smartway.myvg;

/**
 * Created by vwx on 08/21/17.
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.Toast;

import org.i_smartway.myvg.Indonesia.Aceh;
import org.i_smartway.myvg.Indonesia.BangkaBelitung;
import org.i_smartway.myvg.Indonesia.Bengkulu;
import org.i_smartway.myvg.Indonesia.Jambi;
import org.i_smartway.myvg.Indonesia.KepulauanRiau;
import org.i_smartway.myvg.Indonesia.Riau;

import org.i_smartway.myvg.Indonesia.SumateraSelatan;
//import i_smartway.myvg.Indonesia.SumateraUtara;
//import i_smartway.myvg.Indonesia.SumateraBarat;

public class ActivityVgVacation extends Activity {

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vg_vacation);

        // get the listview
        expListView = (ExpandableListView) findViewById(R.id.lvExp);

        // preparing list data
        prepareListData();

        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);

        // Listview Group click listener
        expListView.setOnGroupClickListener(new OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {
                // Toast.makeText(getApplicationContext(),
                // "Group Clicked " + listDataHeader.get(groupPosition),
                // Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        // Listview Group expanded listener
        expListView.setOnGroupExpandListener(new OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                Toast.makeText(getApplicationContext(),
                        listDataHeader.get(groupPosition) + " Expanded",
                        Toast.LENGTH_SHORT).show();
            }
        });

        // Listview Group collasped listener
        expListView.setOnGroupCollapseListener(new OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
                Toast.makeText(getApplicationContext(),
                        listDataHeader.get(groupPosition) + " Collapsed",
                        Toast.LENGTH_SHORT).show();

            }
        });

        // Listview on child click listener
        expListView.setOnChildClickListener(new OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {

                if (groupPosition==0 && childPosition==0){
                    Intent intent = new Intent(ActivityVgVacation.this, VacationMainActivity.class);
                    startActivity(intent);
                }
                else if (groupPosition==0 && childPosition==1){
                    Intent intent = new Intent(ActivityVgVacation.this,org.i_smartway.myvg.SumateraUtara.class);
                    startActivity(intent);
                }
                else if (groupPosition==0 && childPosition==2){
                    Intent intent = new Intent(ActivityVgVacation.this,org.i_smartway.myvg.SumateraBarat.class);
                    startActivity(intent);
                }
                else if (groupPosition==0 && childPosition==3){
                    Intent intent = new Intent(ActivityVgVacation.this,Riau.class);
                    startActivity(intent);
                }
                else if (groupPosition==0 && childPosition==4){
                    Intent intent = new Intent(ActivityVgVacation.this,Jambi.class);
                    startActivity(intent);
                }
                else if (groupPosition==0 && childPosition==5){
                    Intent intent = new Intent(ActivityVgVacation.this,Bengkulu.class);
                    startActivity(intent);
                }
                else if (groupPosition==0 && childPosition==6){
                    Intent intent = new Intent(ActivityVgVacation.this,KepulauanRiau.class);
                    startActivity(intent);
                }
                else if (groupPosition==0 && childPosition==7){
                    Intent intent = new Intent(ActivityVgVacation.this,BangkaBelitung.class);
                    startActivity(intent);
                }
                else if (groupPosition==0 && childPosition==8){
                    Intent intent = new Intent(ActivityVgVacation.this,SumateraSelatan.class);
                    startActivity(intent);
                }

                // TODO Auto-generated method stub
//                Toast.makeText(
//                        getApplicationContext(),
//                        listDataHeader.get(groupPosition)
//                                + " : "
//                                + listDataChild.get(
//                                listDataHeader.get(groupPosition)).get(
//                                childPosition), Toast.LENGTH_SHORT)
//                        .show();
                return false;
            }
        });
    }

    /*
     * Preparing the list data
     */
    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding child data
//        listDataHeader.add("Top 250");
//        listDataHeader.add("Now Showing");
//        listDataHeader.add("Coming Soon..");
        listDataHeader.add("INDONESIA");
        listDataHeader.add("JEPANG");
        listDataHeader.add("KOREA");
        listDataHeader.add("AMERIKA");



        // Adding child data
        List<String> indonesia = new ArrayList<String>();
        indonesia.add("Aceh");
        indonesia.add("Sumater Utara");
        indonesia.add("Sumatera Barat");
        indonesia.add("Riau");
        indonesia.add("Jambi");
        indonesia.add("Bengkulu");
        indonesia.add("Kepulauan Riau");
        indonesia.add("Bangka Belitung");
        indonesia.add("Sumatera Selatan");

        List<String> jepang = new ArrayList<String>();
        jepang.add("Hiroshima");
        jepang.add("Nagasaki");

        List<String> korea = new ArrayList<String>();
        korea.add("2NE1");
        korea.add("SNSD");

        List<String> amerika = new ArrayList<String>();
        amerika.add("New York");
        amerika.add("Texas");


//        List<String> top250 = new ArrayList<String>();
//        top250.add("The Shawshank Redemption");
//        top250.add("The Godfather");
//        top250.add("The Godfather: Part II");
//        top250.add("Pulp Fiction");
//        top250.add("The Good, the Bad and the Ugly");
//        top250.add("The Dark Knight");
//        top250.add("12 Angry Men");
//
//        List<String> nowShowing = new ArrayList<String>();
//        nowShowing.add("The Conjuring");
//        nowShowing.add("Despicable Me 2");
//        nowShowing.add("Turbo");
//        nowShowing.add("Grown Ups 2");
//        nowShowing.add("Red 2");
//        nowShowing.add("The Wolverine");
//
//        List<String> comingSoon = new ArrayList<String>();
//        comingSoon.add("2 Guns");
//        comingSoon.add("The Smurfs 2");
//        comingSoon.add("The Spectacular Now");
//        comingSoon.add("The Canyons");
//        comingSoon.add("Europa Report");

        // Header, Child data
//        listDataChild.put(listDataHeader.get(0), top250);
//        listDataChild.put(listDataHeader.get(1), nowShowing);
//        listDataChild.put(listDataHeader.get(2), comingSoon);
        listDataChild.put(listDataHeader.get(0), indonesia);
        listDataChild.put(listDataHeader.get(1), jepang);
        listDataChild.put(listDataHeader.get(2), korea);
        listDataChild.put(listDataHeader.get(3), amerika);

    }
}