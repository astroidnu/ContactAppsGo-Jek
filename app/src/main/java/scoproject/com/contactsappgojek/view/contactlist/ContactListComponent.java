package scoproject.com.contactsappgojek.view.contactlist;

import dagger.Component;
import scoproject.com.contactsappgojek.di.component.AppComponent;
import scoproject.com.contactsappgojek.viewmodel.contactlist.ContactListRowFavVM;
import scoproject.com.contactsappgojek.viewmodel.contactlist.ContactListRowVM;
import scoproject.com.contactsappgojek.viewmodel.contactlist.ContactListVM;

/**
 * Created by ibnumuzzakkir on 18/05/2017.
 * Android Developer
 * Garena Indonesia
 */


@ContactListScope
@Component(dependencies = {AppComponent.class})
public interface ContactListComponent {
    void inject(ContactListActivity contactListActivity);
    void inject(ContactListVM contactListVM);
    void inject(ContactListRowVM contactListRowVM);
    void inject(ContactListRowFavVM contactListRowFavVM);
}
