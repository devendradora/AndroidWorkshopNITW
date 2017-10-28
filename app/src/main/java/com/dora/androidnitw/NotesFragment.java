package com.dora.androidnitw;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class NotesFragment extends Fragment {

    DatabaseHelper dbhelper_obj;
    SQLiteDatabase db_obj;
    Cursor cursor;
    ListView notesList;
    ImageButton addnotesbtn;

    public NotesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbhelper_obj = new DatabaseHelper(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView= inflater.inflate(R.layout.fragment_notes, container, false);
        addnotesbtn = (ImageButton) rootView.findViewById(R.id.Courses_addbutton);
//        final FragmentManager fragManager = getFragmentManager();
//        addnotesbtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                AddNotesFragment addFrag = AddNotesFragment.newInstance("course1", "DS");
//
//                fragManager.beginTransaction().replace(R.id.container, addFrag,null).addToBackStack(null).commit();
//            }
//        });

        notesList = (ListView)rootView.findViewById(R.id.lv_notes);
        String[] from= {"TITLE","DESC"};
        int[] to={R.id.tv_title,R.id.tv_desc};
        cursor = dbhelper_obj.getAllNotes();
        SimpleCursorAdapter curAdapter = new SimpleCursorAdapter(getActivity(),R.layout.fragment_notes_row,cursor,from,to);
        notesList.setAdapter(curAdapter);
        dbhelper_obj.addNotes("Dora", "ISTE CLUB TESTING");
        Log.d("VALUE", String.valueOf(notesList.getItemAtPosition(0)));
        notesList.invalidate();

        Toast.makeText(NotesFragment.this.getActivity(),"Notes list loaded", Toast.LENGTH_SHORT).show();
        return rootView ;
    }

}
