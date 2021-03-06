package scoproject.com.contactsappgojek.networking.contactlist;

import android.util.Log;

import java.util.List;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import scoproject.com.contactsappgojek.data.People;
import scoproject.com.contactsappgojek.networking.NetworkService;

/**
 * Created by ibnumuzzakkir on 18/05/2017.
 * Android Developer
 * Garena Indonesia
 */

public class GetContactListAPIService {
    private NetworkService mNetworkService;

    public GetContactListAPIService(NetworkService networkService){
        mNetworkService = networkService;
    }

    public Flowable<List<People>> getContactList() {
        return mNetworkService.getPeopleList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError(this::handleError)
                .toFlowable(BackpressureStrategy.BUFFER);
    }

    private void handleError(Throwable throwable) {
        Log.e(getClass().getName(), throwable.getMessage());
    }
}
