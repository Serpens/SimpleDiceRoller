package pl.albertbogdanowicz.simplediceroller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

    public int[] rollDice(int numberOfDice, int typeOfDice) {
        int[] result = new int[numberOfDice];

        Random r = new Random();
        for (int i=0; i<numberOfDice; i++) {
            result[i] = (r.nextInt(typeOfDice) + 1);
        }
        return result;
    }

    public void rollButtonClick (View view) {
        Spinner diceSpinner = (Spinner) findViewById(R.id.diceSpinner);
        Spinner numberSpinner = (Spinner) findViewById(R.id.numberSpinner);
        TextView resultsView = (TextView) findViewById(R.id.resultsTextView);
        TextView resultSumView = (TextView) findViewById(R.id.resultSumTextView);
        int numberOfDice;
        int typeOfDice;
        numberOfDice = Integer.parseInt(numberSpinner.getSelectedItem().toString());
        typeOfDice = Integer.parseInt(diceSpinner.getSelectedItem().toString());
        int result[] = rollDice(numberOfDice, typeOfDice);
        int sum = 0;
        String resultsStr = "";
        for (int i=0; i<result.length; i++){
            sum += result[i];
            resultsStr += Integer.toString(result[i]);
            resultsStr += ", ";
        }
        resultsStr = resultsStr.substring(0, resultsStr.length()-2); // remove last ", "
        resultsView.setText(resultsStr);
        resultSumView.setText(Integer.toString(sum));
    }
}
