
package com.example.parshva.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class Sample extends AppCompatActivity {
    MYVIEW_1810 myView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);
        myView=findViewById(R.id.my_view);

    }

    public void clear(View view) {
        Log.v("ClearCalls","");
        myView.clear();
    }

    public void Undo(View view) {
        myView.undo();
    }

    public void Redo(View view) {
        myView.Redo();
    }
}
