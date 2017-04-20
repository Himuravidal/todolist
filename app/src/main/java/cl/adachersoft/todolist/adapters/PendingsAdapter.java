package cl.adachersoft.todolist.adapters;

import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import cl.adachersoft.todolist.R;
import cl.adachersoft.todolist.data.PendingsData;
import cl.adachersoft.todolist.models.Pending;
import cl.adachersoft.todolist.view.main.pendingList.PendingListListener;

/**
 * Created by cristian on 27-10-2016.
 */

public class PendingsAdapter extends RecyclerView.Adapter<PendingsAdapter.ViewHolder>{


    private List<Pending> pendings =new PendingsData().notDone();
    private PendingListListener listener;

    public PendingsAdapter(PendingListListener listener) {
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_pending, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final Pending pending = pendings.get(position);
        holder.name.setText(pending.getName());

        CheckBox checkbox = holder.status;
        checkbox.setChecked(pending.isDone());
        checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            pending.setDone(true);
                            pending.save();
                            pendings.remove(position);
                            notifyDataSetChanged();

                        }
                    }, 100);

                }
            }
        });

        holder.name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.click(pending.getId());

            }
        });

    }

    @Override
    public int getItemCount() {
        return pendings.size();
    }

    public void add(Pending pending){
        pendings.add(0,pending);
        notifyDataSetChanged();

    }

    public void reset(){
        pendings.clear();
        List<Pending> pendingList = new PendingsData().notDone();
        pendings.addAll(pendingList);
        notifyDataSetChanged();

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
       CheckBox status;
        //Same that above, create a subclass which you will use to access your views
        //But this time, the inflating is above and the finding is here

        ViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.nameTv);
            status = (CheckBox) view.findViewById(R.id.pendingCb);



        }

    }
}


