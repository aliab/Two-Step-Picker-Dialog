package info.abdolahi.twosteppicker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.hamsa.twosteppickerdialog.OnStepDataRequestedListener;
import com.hamsa.twosteppickerdialog.OnStepPickListener;
import com.hamsa.twosteppickerdialog.TwoStepPickerDialog;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button pickerButton = (Button) findViewById(R.id.pickerButton);

        final List<String> baseData = new ArrayList<>();
        baseData.add("Step1");
        baseData.add("Step2");
        baseData.add("Step3");


        final TwoStepPickerDialog pickThing = new TwoStepPickerDialog
                .Builder(this)
                .withBaseData(baseData)
                .withOkButton("Get Me")
                .withCancelButton("Cancel It")
                .withBaseOnLeft(true) // if you want it RTL like, set it to false
                .withInitialBaseSelected(2)
                .withInitialStepSelected(1)
                .withOnStepDataRequested(new OnStepDataRequestedListener() {
                    @Override
                    public List<String> onStepDataRequest(int baseDataPos) {
                        return getRandomList("Step " + (baseDataPos + 1) + " - ");
                    }
                })
                .withDialogListener(new OnStepPickListener() {
                    @Override
                    public void onStepPicked(int step, int pos) {
                        Toast.makeText(MainActivity.this, baseData.get(step) + " -> " + pos, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onDismissed() {
                        Toast.makeText(MainActivity.this, "Dismised!", Toast.LENGTH_SHORT).show();
                    }
                })
                .build();


        pickerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pickThing.show();
            }
        });
    }

    public List<String> getRandomList(String perFix) {
        List<String> randomData = new ArrayList<>();
        Random r = new Random();
        for (int i = 1; i < r.nextInt(100); i++) {
            randomData.add(perFix + i);
        }
        return randomData;
    }
}
