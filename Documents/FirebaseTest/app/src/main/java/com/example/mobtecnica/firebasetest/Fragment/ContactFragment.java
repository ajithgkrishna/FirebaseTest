package com.example.mobtecnica.firebasetest.Fragment;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.mobtecnica.firebasetest.Adapter.ContactListAdapter;
import com.example.mobtecnica.firebasetest.Model.ContactList;
import com.example.mobtecnica.firebasetest.R;

import java.util.ArrayList;


public class ContactFragment extends Fragment {

    ArrayList<ContactList> contactList;
    Cursor cursor;
    int counter;
    Button btnsync;
    private RecyclerView recyclerView;
    private ProgressDialog pDialog;
    private Handler updateBarHandler;

    public ContactFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_contacts, container, false);

        pDialog = new ProgressDialog(getActivity());
        pDialog.setMessage("Reading contacts...");
        pDialog.setCancelable(false);
        pDialog.show();
        updateBarHandler = new Handler();
        btnsync = (Button) view.findViewById(R.id.btnSync);
        recyclerView = (RecyclerView) view.findViewById(R.id.lst_contacts);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);


        updateBarHandler = new Handler();
        new Thread(new Runnable() {
            @Override
            public void run() {
                getContacts();
            }
        }).start();
        /*ContactListAdapter listAdapter = new ContactListAdapter(getActivity(), contactList, getContext());
        recyclerView.setAdapter(listAdapter);

        if (listAdapter != null) {
            listAdapter.setOnItemClickListener(new ContactListAdapter.ContactListClickListener() {
                @Override
                public void onClicked(int position, View v) {
                    switch (v.getId()) {
                        case R.id.txtname:
                            Toast.makeText(getActivity(), contactList.get(position).getName(), Toast.LENGTH_SHORT).show();
                            break;
                        default:
                            break;
                    }
                }
            });
        }*/

        // Set onclicklistener to the list item.
        /*recyclerView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                //TODO Do whatever you want with the list data
                Toast.makeText(getApplicationContext(), "item clicked : \n"+contactList.get(position), Toast.LENGTH_SHORT).show();
            }
        });*/
        return view;
    }

    public void getContacts() {
        contactList = new ArrayList<ContactList>();
        String phoneNumber = null;
        String email = null;
        System.out.println("ContactFragment.getContacts");
        Uri CONTENT_URI = ContactsContract.Contacts.CONTENT_URI;
        String _ID = ContactsContract.Contacts._ID;
        String DISPLAY_NAME = ContactsContract.Contacts.DISPLAY_NAME;
        String HAS_PHONE_NUMBER = ContactsContract.Contacts.HAS_PHONE_NUMBER;
        Uri PhoneCONTENT_URI = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        String Phone_CONTACT_ID = ContactsContract.CommonDataKinds.Phone.CONTACT_ID;
        String NUMBER = ContactsContract.CommonDataKinds.Phone.NUMBER;
        Uri EmailCONTENT_URI = ContactsContract.CommonDataKinds.Email.CONTENT_URI;
        String EmailCONTACT_ID = ContactsContract.CommonDataKinds.Email.CONTACT_ID;
        String DATA = ContactsContract.CommonDataKinds.Email.DATA;
        StringBuffer output;
        ContentResolver contentResolver = getActivity().getApplicationContext().getContentResolver();
        cursor = contentResolver.query(CONTENT_URI, null, null, null, null);
        // Iterate every contact in the phone
        int i = 0;
        if (cursor.getCount() > 0) {
            counter = 0;
            while (cursor.moveToNext()) {
                output = new StringBuffer();
                ContactList contactList1 = new ContactList();
                // Update the progress message
                updateBarHandler.post(new Runnable() {
                    public void run() {
                        pDialog.setMessage("Reading contacts : " + counter++ + "/" + cursor.getCount());
                    }
                });
                String contact_id = cursor.getString(cursor.getColumnIndex(_ID));
                String name = cursor.getString(cursor.getColumnIndex(DISPLAY_NAME));
                System.out.println("ContactFragment.getContacts " + name);
                contactList1.setName(name);
                int hasPhoneNumber = Integer.parseInt(cursor.getString(cursor.getColumnIndex(HAS_PHONE_NUMBER)));
                if (hasPhoneNumber > 0) {
                    output.append("\n First Name:" + name);
                    //This is to read multiple phone numbers associated with the same contact
                    Cursor phoneCursor = contentResolver.query(PhoneCONTENT_URI, null, Phone_CONTACT_ID + " = ?", new String[]{contact_id}, null);
                    while (phoneCursor.moveToNext()) {
                        phoneNumber = phoneCursor.getString(phoneCursor.getColumnIndex(NUMBER));
                        output.append("\n Phone number:" + phoneNumber);
                        System.out.println("ContactFragment.getContacts " + phoneNumber);
                        contactList1.setPhone(phoneNumber);
                    }
                    phoneCursor.close();
                    // Read every email id associated with the contact
                    Cursor emailCursor = contentResolver.query(EmailCONTENT_URI, null, EmailCONTACT_ID + " = ?", new String[]{contact_id}, null);
                    while (emailCursor.moveToNext()) {
                        email = emailCursor.getString(emailCursor.getColumnIndex(DATA));
                        output.append("\n Email:" + email);
                    }
                    emailCursor.close();
                }
                contactList.add(contactList1);
                System.out.println("ContactFragment.getContacts Name " + contactList1.getName());
                System.out.println("ContactFragment.getContacts Phone " + contactList1.getPhone());
            }
            // ListView has to be updated using a ui thread
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    ContactListAdapter listAdapter = new ContactListAdapter(getActivity(), contactList, getContext());
                    recyclerView.setAdapter(listAdapter);
                }
            });

            // Dismiss the progressbar after 500 millisecondds
            updateBarHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    pDialog.cancel();
                }
            }, 500);

        }
    }
}
