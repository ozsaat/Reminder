package com.oz.reminder;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class RemindersAdapter extends RecyclerView.Adapter<RemindersAdapter.ViewHolder> {

    private final List<Reminder> reminders = new ArrayList<>();

    @Override
    public int getItemCount() {
        return reminders.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.reminder_list_card, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Reminder reminder = reminders.get(position);
        holder.titleTextView.setText(reminder.getTitle());
        holder.messageTextView.setText(reminder.getMessage());
    }

    public void addReminder(Reminder reminder) {
        reminders.add(0, reminder);
        notifyItemInserted(0);
    }

    public List<Reminder> getReminders() {
        return reminders;
    }

    public void setReminders(List<Reminder> reminders) {
        this.reminders.clear();
        this.reminders.addAll(reminders);
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView titleTextView;
        private final TextView messageTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            titleTextView = (TextView) itemView.findViewById(R.id.titleTextView);
            messageTextView = (TextView) itemView.findViewById(R.id.messageTextView);
        }
    }
}
