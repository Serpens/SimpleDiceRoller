package pl.albertbogdanowicz.simplediceroller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
//import android.util.Log;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Spinner diceSpinner = (Spinner) findViewById(R.id.diceSpinner);
        diceSpinner.setSelection(2);
    }

    public int rollDice(int numberOfDice, int typeOfDice) {
        int result = 0;

        Random r = new Random();
        for (int i=0; i<numberOfDice; i++) {
            result += (r.nextInt(typeOfDice) + 1);
        }
        return result;
    }

    public void rollButtonClick (View view) {
        Spinner diceSpinner = (Spinner) findViewById(R.id.diceSpinner);
        Spinner numberSpinner = (Spinner) findViewById(R.id.numberSpinner);
        TextView resultView = (TextView) findViewById(R.id.resultTextView);
        int numberOfDice = 0;
        int typeOfDice = 0;
        numberOfDice = Integer.parseInt(numberSpinner.getSelectedItem().toString());
        typeOfDice = Integer.parseInt(diceSpinner.getSelectedItem().toString());
        //Log.d("Number", Integer.toString(numberOfDice));
        //Log.d("Type", Integer.toString(typeOfDice));
        int result = rollDice(numberOfDice, typeOfDice);
        resultView.setText(Integer.toString(result));
    }
}
