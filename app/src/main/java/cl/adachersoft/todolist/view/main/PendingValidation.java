package cl.adachersoft.todolist.view.main;

import cl.adachersoft.todolist.models.Pending;

/**
 * Created by cristian on 27-10-2016.
 */

public class PendingValidation {

private CreateCallback callback;

    public PendingValidation(CreateCallback callback) {
        this.callback = callback;
    }

    public void init(String name){
        if (name.trim().length() > 0){
            Pending pending = new Pending();
            pending.setName(name);
            pending.setDone(false);
            pending.save();
            callback.success(pending);

        }else
        {
            callback.fail();

        }

    }





}

