package com.example.natan.mysqllitedatabase;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.natan.mysqllitedatabase.Adapter.RecyclerViewAdapter;
import com.example.natan.mysqllitedatabase.Data.Contract;
import com.example.natan.mysqllitedatabase.Data.DbHelper;

import java.security.KeyStore;

public class MainActivity extends AppCompatActivity {

    private EditText edt;
    private Button btn;
    private RecyclerView mRecyclerView;
    private RecyclerViewAdapter mRecyclerViewAdapter;
    private SQLiteDatabase mDb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edt = findViewById(R.id.editText);
        btn =findViewById(R.id.button);
        mRecyclerView = findViewById(R.id.recyclerView);


        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        DbHelper dbHelper = new DbHelper(this);

        mDb = dbHelper.getWritableDatabase();

        Cursor cursor = getAllNames();

        mRecyclerViewAdapter=new RecyclerViewAdapter(this,cursor);

        mRecyclerView.setAdapter(mRecyclerViewAdapter);

    }

    private Cursor getAllNames() {

        return mDb.query(Contract.Entry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                Contract.Entry.COLUMN_NAME

        );

    }


    public void addGuest(View view)
    {
        addNewGuest(edt.getText().toString());
        mRecyclerViewAdapter.swapCursor(getAllNames());
        
        edt.getText().clear();

    }
    private long addNewGuest(String name)
    {
        ContentValues cv= new ContentValues();
        cv.put(Contract.Entry.COLUMN_NAME,name);

       return mDb.insert(Contract.Entry.TABLE_NAME,null,cv);
    }

}
