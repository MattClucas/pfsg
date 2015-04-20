package com.clucasprojects.sacredgeometry;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;



public class CalculateActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate);

        // Get the message from the intent
        Intent intent = getIntent();
        String spellLevel = intent.getStringExtra(MainActivity.EXTRA_SPELL_LEVEL);
        String rolls = intent.getStringExtra(SetRollsActivity.EXTRA_ROLLS);

        // Calculate result and equation
        PrimeCalculator myCalc = new PrimeCalculator();
        myCalc.calculate(spellLevel, rolls);

        //Set the text view
        if (myCalc.getResult()!=0)
        {
            setResults("Result is " + myCalc.getResult(), myCalc.getEquation());
        }
        else
        {
            int[] needs = myCalc.getPrimeConstants();
            setResults("Sorry, no result found. \n You need " + needs[0] + ", " + needs[1] + ", or " + needs[2] + ".", "Remember that I don't do parenthesis.");
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_calculate, menu);
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

    public void gotoMainActivity(View view)
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void setResults(String resultText, String equationText)
    {
        TextView calcValue = (TextView)findViewById(R.id.calcValue);
        calcValue.setText(resultText);

        TextView calcString = (TextView)findViewById(R.id.calcString);
        calcString.setText(equationText);
    }
}
