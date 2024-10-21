package kz.example.lesson_3.homeworks.hw2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.text.DateFormat;
import java.util.List;

import kz.example.lesson_3.R;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventViewHolder> {

    private List<Event> events;

    public EventAdapter(List<Event> eventList) {
        this.events = eventList;
    }

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_event, parent, false);
        return new EventViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder holder, int position) {
        Event event = events.get(position);
        holder.bind(event);
    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    static class EventViewHolder extends RecyclerView.ViewHolder {
        TextView _tvTitle, _tvDescription, _tvTime;

        public EventViewHolder(@NonNull View itemView) {
            super(itemView);
            _tvTitle = itemView.findViewById(R.id.eventTitle);
            _tvDescription = itemView.findViewById(R.id.eventDescription);
            _tvTime = itemView.findViewById(R.id.eventTime);
        }

        public void bind(Event event) {
            _tvTitle.setText(event.getTitle());
            _tvDescription.setText(event.getDescription());

            DateFormat timeFormat = DateFormat.getTimeInstance(DateFormat.SHORT);
            if (event.isAllDay()) {
                _tvTime.setText("All day");
            } else {
                String timeRange = timeFormat.format(event.getStartTime()) + " - " + timeFormat.format(event.getEndTime());
                _tvTime.setText(timeRange);
            }
        }
    }
}

