package org.i_smartway.myvg;

/**
 * Created by vwx on 08/22/17.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class VacationMainActivity extends AppCompatActivity {
    private List<Vacation> vacationList = new ArrayList<>();
    private RecyclerView recyclerView;
    private VacationAdapter mAdapter;

//    private RatingBar Rating;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vacation_aceh_activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        Rating = (RatingBar) findViewById(R.id.Rating);
//        Rating.setRating(3);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mAdapter = new VacationAdapter(vacationList);

        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new VacationDividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        recyclerView.addOnItemTouchListener(new VacationRecyclerTouchListener(getApplicationContext(), recyclerView, new VacationRecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Vacation vacation = vacationList.get(position);
                Toast.makeText(getApplicationContext(), vacation.getTitle() + " is selected!", Toast.LENGTH_SHORT).show();

                //pindah layer per menu
                if (position == 0)
                {
                    Intent intent = new Intent(VacationMainActivity.this,VacationCounterPesanan.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));


        prepareMovieData();
    }

    private void prepareMovieData() {
        Vacation vacation = new Vacation("Lembang", "Rp.50000", " ");
        vacationList.add(vacation);

        vacation = new Vacation("Kebun Raya Bogor", "Rp.5000", " ");
        vacationList.add(vacation);

        vacation = new Vacation("Kawah Putih", "Rp.Gratis", " ");
        vacationList.add(vacation);

        vacation = new Vacation("Tangkuban Perahu", "Rp.Gratis", " ");
        vacationList.add(vacation);

        vacation = new Vacation("Candi Borobudur", "Rp.Gratis", " ");
        vacationList.add(vacation);

        vacation = new Vacation("Candi Prambanan", "Rp.Gratis", " ");
        vacationList.add(vacation);

        vacation = new Vacation("Mahameru", "Rp.Gratis", " ");
        vacationList.add(vacation);

        vacation = new Vacation("Monas", "Rp.Gratis", " ");
        vacationList.add(vacation);

        vacation = new Vacation("Pulau Seribu", "Rp.Gratis", " ");
        vacationList.add(vacation);

        vacation = new Vacation("Ancol", "Rp.Gratis", " ");
        vacationList.add(vacation);

        vacation = new Vacation("Dufan", "Rp.Gratis", " ");
        vacationList.add(vacation);

        vacation = new Vacation("TMII", "Rp.Gratis", " ");
        vacationList.add(vacation);
//
//        vacation = new Vacation("Back to the Future", "Rp.Gratis", " ");
//        movieList.add(vacation);
//
//        vacation = new Vacation("Raiders of the Lost Ark", "Rp.Gratis", "1981");
//        movieList.add(vacation);
//
//        vacation = new Vacation("Goldfinger", "Rp.Gratis", "1965");
//        movieList.add(vacation);
//
//        vacation = new Vacation("Guardians of the Galaxy", "Rp.Gratis", "2014");
//        movieList.add(vacation);

        mAdapter.notifyDataSetChanged();
    }

}