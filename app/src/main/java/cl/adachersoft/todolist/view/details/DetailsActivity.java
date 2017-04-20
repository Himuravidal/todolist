package cl.adachersoft.todolist.view.details;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import cl.adachersoft.todolist.R;
import cl.adachersoft.todolist.models.Pending;
import cl.adachersoft.todolist.view.main.pendingList.PendingListFragment;

public class DetailsActivity extends AppCompatActivity {

    private Pending pending;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);


        long pendingID = getIntent().getLongExtra(PendingListFragment.PENDING_ID,0);
        pending = Pending.findById(Pending.class,pendingID);

        Toast.makeText(this,pending.getName(), Toast.LENGTH_SHORT).show();
    }
}
