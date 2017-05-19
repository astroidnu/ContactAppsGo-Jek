package scoproject.com.contactsappgojek.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import scoproject.com.contactsappgojek.R;
import scoproject.com.contactsappgojek.databinding.ItemContactFavoriteListBinding;
import scoproject.com.contactsappgojek.databinding.ItemContactListBinding;
import scoproject.com.contactsappgojek.data.People;
import scoproject.com.contactsappgojek.viewmodel.contactlist.ContactListRowFavVM;
import scoproject.com.contactsappgojek.viewmodel.contactlist.ContactListRowVM;

/**
 * Created by ibnumuzzakkir on 5/19/17.
 */

public class ContactListAdapter extends RecyclerView.Adapter<ContactListAdapter.ViewHolder> {
    private Context mContext;
    private List<People> mPeopleFavoriteList;
    private List<People> mPeopleUnFavoriteList;

    public ContactListAdapter(Context context, List<People> peopleFavoriteList, List<People> peopleUnFavoriteList){
        mContext = context;
        Log.d(getClass().getName(), String.valueOf(peopleFavoriteList.size()) +" "+ String.valueOf(peopleUnFavoriteList.size()));
        mPeopleFavoriteList =  peopleFavoriteList;
        mPeopleUnFavoriteList = peopleUnFavoriteList;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewDataBinding viewDataBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),viewType, parent, false);
        return new ViewHolder(viewDataBinding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        switch (holder.getItemViewType()){
            case R.layout.item_contact_favorite_list:
                ContactListRowFavVM contactListRowFavVM = new ContactListRowFavVM(mPeopleFavoriteList.get(position),  ((ItemContactFavoriteListBinding) holder.getDataBinding()));
                contactListRowFavVM.takeContext(mContext);
                ((ItemContactFavoriteListBinding) holder.getDataBinding()).setVm(contactListRowFavVM);
                break;
            case R.layout.item_contact_list:
                ContactListRowVM contactListRowVM = new ContactListRowVM(mPeopleUnFavoriteList.get(position - mPeopleFavoriteList.size()),  ((ItemContactListBinding) holder.getDataBinding()));
                contactListRowVM.takeContext(mContext);
                ((ItemContactListBinding) holder.getDataBinding()).setVm(contactListRowVM);
                break;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if(position < mPeopleFavoriteList.size())
            return R.layout.item_contact_favorite_list;
        else
            return R.layout.item_contact_list;
    }

    @Override
    public int getItemCount() {
        return mPeopleFavoriteList.size() + mPeopleUnFavoriteList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ViewDataBinding mViewDataBinding;

        public ViewHolder(ViewDataBinding viewDataBinding){
            super(viewDataBinding.getRoot());
            mViewDataBinding = viewDataBinding;
            mViewDataBinding.executePendingBindings();
        }

        public ViewDataBinding getDataBinding(){
            return mViewDataBinding;
        }
    }

    public Context getContext(){
        return mContext;
    }
}
