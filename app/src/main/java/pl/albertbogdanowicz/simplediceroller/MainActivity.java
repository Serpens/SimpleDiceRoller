package pl.albertbogdanowicz.simplediceroller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Spinner diceSpinner = (Spinner) findViewById(R.id.diceSpinner);
        diceSpinner.setSelection(2);
        diceSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                setThresholdPossibileValues();
            }

            public void onNothingSelected(AdapterView<?> adapterView) {
                return;
            }
        });
    }

    public int[] rollDice(int numberOfDice, int typeOfDice) {
        int[] result = new int[numberOfDice];

        Random r = new Random();
        for (int i=0; i<numberOfDice; i++) {
            result[i] = (r.nextInt(typeOfDice) + 1);
        }
        return result;
    }

    public void setThresholdPossibileValues() {
        Spinner diceSpinner = (Spinner) findViewById(R.id.diceSpinner);
        int maxValue = Integer.parseInt(diceSpinner.getSelectedItem().toString());
        ArrayList<String> entries = new ArrayList<String>();
        entries.add("-");
        for (int i=2; i<maxValue; i++)
            entries.add(Integer.toString(i));
        Spinner thresholdSpinner = (Spinner) findViewById(R.id.thresholdSpinner);
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, entries);
        thresholdSpinner.setAdapter(spinnerArrayAdapter);
    }

    public void rollButtonClick (View view) {
        Spinner diceSpinner = (Spinner) findViewById(R.id.diceSpinner);
        Spinner numberSpinner = (Spinner) findViewById(R.id.numberSpinner);
        Spinner thresholdSpinner = (Spinner) findViewById(R.id.thresholdSpinner);
        TextView resultsView = (TextView) findViewById(R.id.resultsTextView);
        TextView resultSumView = (TextView) findViewById(R.id.resultSumTextView);
        int numberOfDice;
        int typeOfDice;
        int threshold;
        numberOfDice = Integer.parseInt(numberSpinner.getSelectedItem().toString());
        typeOfDice = Integer.parseInt(diceSpinner.getSelectedItem().toString());
        if (thresholdSpinner.getSelectedItem().toString() != "-")
            threshold = Integer.parseInt(thresholdSpinner.getSelectedItem().toString());
        else
            threshold = 0;
        int result[] = rollDice(numberOfDice, typeOfDice);
        int sum = 0;
        String resultsStr = "";
        if (threshold == 0) {
            for (int i = 0; i < result.length; i++) {
                sum += result[i];
                resultsStr += Integer.toString(result[i]);
                resultsStr += ", ";
            }
        } else{
            for (int i = 0; i < result.length; i++) {
                sum += result[i];
                if (result[i] > threshold)
                    resultsStr += "<b><font color=\"red\">";
                resultsStr += Integer.toString(result[i]);
                if (result[i] > threshold)
                    resultsStr += "</font></b>";
                resultsStr += ", ";
            }
        }
        resultsStr = resultsStr.substring(0, resultsStr.length()-2); // remove last ", "
        resultsView.setText(Html.fromHtml(resultsStr));
        resultSumView.setText(Integer.toString(sum));
    }
}
