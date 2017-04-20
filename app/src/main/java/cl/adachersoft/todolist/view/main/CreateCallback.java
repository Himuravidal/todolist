package cl.adachersoft.todolist.view.main;

import cl.adachersoft.todolist.models.Pending;

/**
 * Created by cristian on 27-10-2016.
 */

public interface CreateCallback {

    void success(Pending pending);

    void fail();



}
