package org.i_smartway.myvg;

/**
 * Created by vwx on 08/22/17.
 */

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class VacationAdapter extends RecyclerView.Adapter<VacationAdapter.MyViewHolder> {

    private List<Vacation> vacationList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, year, genre;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            genre = (TextView) view.findViewById(R.id.genre);
            year = (TextView) view.findViewById(R.id.year);
        }
    }


    public VacationAdapter(List<Vacation> vacationList) {
        this.vacationList = vacationList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.vacation_aceh_list_wisata, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Vacation vacation = vacationList.get(position);
        holder.title.setText(vacation.getTitle());
        holder.genre.setText(vacation.getGenre());
        holder.year.setText(vacation.getYear());
    }

    @Override
    public int getItemCount() {
        return vacationList.size();
    }
}
