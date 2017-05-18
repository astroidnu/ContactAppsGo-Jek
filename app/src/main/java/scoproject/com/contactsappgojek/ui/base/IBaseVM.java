package scoproject.com.contactsappgojek.ui.base;


import android.databinding.Observable;
import android.os.Bundle;
import android.support.annotation.NonNull;

import scoproject.com.contactsappgojek.ui.base.view.ViewVM;

/**
 * Created by ibnumuzzakkir on 5/18/17.
 */

public interface IBaseVM<V extends ViewVM> extends Observable {
    void attachView(V view, Bundle savedInstanceState);
    void detachView();
    void saveInstanceState(@NonNull Bundle outState);
}
