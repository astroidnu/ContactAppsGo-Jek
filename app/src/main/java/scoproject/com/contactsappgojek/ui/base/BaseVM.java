package scoproject.com.contactsappgojek.ui.base;

import android.databinding.BaseObservable;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import scoproject.com.contactsappgojek.ui.base.view.ViewVM;

/**
 * Created by ibnumuzzakkir on 5/18/17.
 */

public abstract class BaseVM <V extends ViewVM> extends BaseObservable implements IBaseVM<V>{
    private V mvvmView;

    @Override
    @CallSuper
    public void attachView(V mvvmView, @Nullable Bundle savedInstanceState) {
        this.mvvmView = mvvmView;
        if(savedInstanceState != null) { restoreInstanceState(savedInstanceState); }
    }

    @Override
    @CallSuper
    public void detachView() {
        mvvmView = null;
    }

    protected void restoreInstanceState(@NonNull Bundle savedInstanceState) { }

    public void saveInstanceState(Bundle outState) { }

    public final boolean isViewAttached() {
        return mvvmView != null;
    }

    public final V getView() {
        return mvvmView;
    }

    public final void checkViewAttached() {
        if (!isViewAttached()) throw new NullPointerException("Please call ViewModel.attachView(MvvmView) before requesting data to the ViewModel");
    }
}
