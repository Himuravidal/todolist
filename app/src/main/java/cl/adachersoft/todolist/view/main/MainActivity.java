package cl.adachersoft.todolist.view.main;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import cl.adachersoft.todolist.R;
import cl.adachersoft.todolist.models.Pending;
import cl.adachersoft.todolist.view.main.pendingList.PendingListFragment;

public class MainActivity extends AppCompatActivity implements CreateCallback {
    private Dialog dialog;

    private PendingListFragment pendingListFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        pendingListFragment = (PendingListFragment)getSupportFragmentManager().findFragmentById(R.id.pendingListFragment);

        setDialog();


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText pendingInput =(EditText)dialog.findViewById(R.id.pendingET);
                pendingInput.setText("");
                dialog.show();
                InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.showSoftInput(getCurrentFocus(), InputMethodManager.SHOW_FORCED);


            }
        });
    }

    private void setDialog() {
        dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_create_pending);
        dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);

        final EditText pendingInput = (EditText) dialog.findViewById(R.id.pendingET);
        ImageButton saveBtn = (ImageButton) dialog.findViewById(R.id.saveBtn);


        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String pendingName = pendingInput.getText().toString();

                createdPending(pendingName);


            }
        });

        pendingInput.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_NEXT || actionId == EditorInfo.IME_ACTION_DONE) {
                    String pendigName = pendingInput.getText().toString();
                    createdPending(pendigName);

                    return true;
                }
                return false;

            }
        });


    }

    private void createdPending(String name) {

        PendingValidation pendingValidation = new PendingValidation(MainActivity.this);
        pendingValidation.init(name);
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, 0);
        dialog.dismiss();


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void success(Pending pending) {

        pendingListFragment.addPending(pending);

    }

    @Override
    public void fail() {

        Toast.makeText(this, "Un nonbre por Favor", Toast.LENGTH_SHORT).show();


    }
}
