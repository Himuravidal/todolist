package cl.adachersoft.todolist.view.main.pendingList;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cl.adachersoft.todolist.R;
import cl.adachersoft.todolist.adapters.PendingsAdapter;
import cl.adachersoft.todolist.models.Pending;
import cl.adachersoft.todolist.view.details.DetailsActivity;

/**
 * A placeholder fragment containing a simple view.
 */
public class PendingListFragment extends Fragment implements PendingListListener {

    public static final String PENDING_ID = "PENDING_ID";

    private PendingsAdapter pendingsAdapter;

    public PendingListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_pending_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = (RecyclerView) view;

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

         pendingsAdapter = new PendingsAdapter(this);
        recyclerView.setAdapter(pendingsAdapter);


    }

    public void addPending(Pending pending){
        pendingsAdapter.add(pending);

    }

    @Override
    public void click(long id) {
        Intent intent= new Intent(getActivity(), DetailsActivity.class);
        intent.putExtra(PENDING_ID, id);
        startActivity(intent);
    }

}
