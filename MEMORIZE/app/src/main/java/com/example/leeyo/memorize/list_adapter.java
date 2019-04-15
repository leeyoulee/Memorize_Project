package com.example.leeyo.memorize;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class list_adapter extends RecyclerView.Adapter<list_adapter.ViewHolder> {
    private ArrayList<list_data> list_data;
    private Context context;

    public list_adapter(Context context, ArrayList<list_data> list_data) {
        this.context = context;
        this.list_data = list_data;
    }

    @Override
    public list_adapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item, viewGroup, false);
        return new list_adapter.ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(final list_adapter.ViewHolder viewHolder, final int i) {
        viewHolder.diaryTitle.setText(list_data.get(i).getDiaryTitle());
        viewHolder.diaryDate.setText(list_data.get(i).getDiaryDate());
        viewHolder.diaryFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DiaryActivity.class);
                intent.putExtra("diaryNo", list_data.get(i).getDiaryNo());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list_data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView diaryTitle;
        TextView diaryDate;
        LinearLayout diaryFragment;

        public ViewHolder(View view) {
            super(view);
            diaryTitle = (TextView) view.findViewById(R.id.diaryTitle);
            diaryDate = (TextView) view.findViewById(R.id.diaryDate);
            diaryFragment = view.findViewById(R.id.diaryFragment);
        }
    }
}
