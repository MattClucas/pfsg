package com.clucasprojects.sacredgeometry;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class SetRollsActivity extends ActionBarActivity {

    public final static String EXTRA_ROLLS = "com.clucasprojects.sacredgeometry.ROLLS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_rolls);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_set_rolls, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void addRoll(View view)
    {
        TextView textview = (TextView)findViewById(R.id.rollsLabel);
        if (textview.getText().toString().equals("Enter All Rolls")){
            textview.setText("");
        }
        Button b = (Button)view;
        String roll = b.getText().toString();
        textview.append(roll);
    }

    public void startCalculation(View view)
    {
        TextView textview = (TextView)findViewById(R.id.rollsLabel);
        if (!textview.getText().toString().equals("Enter All Rolls")) {
            Intent intent = new Intent(this, CalculateActivity.class);
            intent.putExtra(MainActivity.EXTRA_SPELL_LEVEL, getIntent().getStringExtra(MainActivity.EXTRA_SPELL_LEVEL));
            intent.putExtra(EXTRA_ROLLS, textview.getText().toString());
            startActivity(intent);
        }
    }
}
