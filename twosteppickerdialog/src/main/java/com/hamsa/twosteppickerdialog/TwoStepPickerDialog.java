package com.hamsa.twosteppickerdialog;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.NumberPicker;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aliabdolahi on 2/12/17.
 */

public class TwoStepPickerDialog {

    private Context context;
    private OnStepPickListener listener;
    private String okButtonString = "Ok";
    private String dismissButtonString = "Cancel";
    private List<String> baseData;
    private List<List<String>> stepData;
    private boolean baseOnLeft = true;
    private OnStepDataRequestedListener stepDataRequestedListener;

    private TwoStepPickerDialog(Context context) {
        this.context = context;
    }

    public void setStepDataRequestedListener(OnStepDataRequestedListener stepDataRequestedListener) {
        this.stepDataRequestedListener = stepDataRequestedListener;
    }

    private void setBaseOnLeft(boolean baseOnLeft) {
        this.baseOnLeft = baseOnLeft;
    }

    private void setBaseData(List<String> baseData) {
        this.baseData = baseData;
    }

    private void setStepData(List<List<String>> stepData) {
        this.stepData = stepData;
    }

    private void setDismissButtonString(String dismissButtonString) {
        this.dismissButtonString = dismissButtonString;
    }

    private void setOkButtonString(String okButtonString) {
        this.okButtonString = okButtonString;
    }

    private void setListener(OnStepPickListener listener) {
        this.listener = listener;
    }

    @SuppressLint("InflateParams")
    public void show() {

        final AlertDialog.Builder dialog = new AlertDialog.Builder(context);

        // setup custom view
        View v;
        if (baseOnLeft) {
            v = LayoutInflater.from(context).inflate(R.layout.twostep_dialog, null, false);
        } else {
            v = LayoutInflater.from(context).inflate(R.layout.twostep_dialog_rtl, null, false);
        }

        // get view
        final NumberPicker numberPickerBase = (NumberPicker) v.findViewById(R.id.left_picker);
        final NumberPicker numberPickerStep = (NumberPicker) v.findViewById(R.id.right_picker);

        // setup base data
        String[] baseDataStrinArray = new String[baseData.size()];
        baseDataStrinArray = baseData.toArray(baseDataStrinArray);

        numberPickerBase.setWrapSelectorWheel(false);
        numberPickerStep.setWrapSelectorWheel(false);

        // disable keyboard input
        numberPickerBase.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        numberPickerStep.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);

        // set value
        numberPickerBase.setMinValue(0);
        numberPickerBase.setMaxValue(baseData.size() - 1);
        numberPickerBase.setDisplayedValues(baseDataStrinArray);

        // listen to picket change
        numberPickerBase.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int oldVal, int newValue) {
                updateStepPicker(numberPickerBase, numberPickerStep);
            }
        });

        // update for current step
        updateStepPicker(numberPickerBase, numberPickerStep);

        // listen for ok button
        dialog.setPositiveButton(okButtonString, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (listener != null) {
                    listener.onStepPicked(numberPickerBase.getValue(), numberPickerStep.getValue());
                }
                dialogInterface.dismiss();
            }
        });

        // listen for cancel button
        dialog.setNegativeButton(dismissButtonString, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                if (listener != null) {
                    listener.onDismissed();
                }

                dialogInterface.dismiss();
            }
        });

        // set dialog view
        dialog.setView(v);

        // show it to user
        dialog.show();
    }

    private void updateStepPicker(final NumberPicker numberPickerBase, NumberPicker numberPickerStep) {

        ArrayList<String> data = new ArrayList<>();

        if (stepDataRequestedListener != null) {
            data = (ArrayList<String>) stepDataRequestedListener.onStepDataRequest(numberPickerBase.getValue());
        } else if (stepData != null && stepData.get(numberPickerBase.getValue()) != null) {
            data = (ArrayList<String>) stepData.get(numberPickerBase.getValue());
        }

        // Prevent ArrayOutOfBoundExceptions by setting
        // values array to null so its not checked
        numberPickerStep.setDisplayedValues(null);

        // make string array
        String[] stepDataStrinArray = new String[data.size()];
        stepDataStrinArray = data.toArray(stepDataStrinArray);

        //  update data
        numberPickerStep.setMinValue(0);
        numberPickerStep.setMaxValue(stepDataStrinArray.length - 1);
        numberPickerStep.setDisplayedValues(stepDataStrinArray);

        numberPickerStep.setValue(1);
    }


    /**
     * Base builder class
     */
    public static class Builder implements TwoStepPickerDialogBuilder {

        TwoStepPickerDialog twoStepPickerDialog;

        public Builder(Context context) {
            this.twoStepPickerDialog = new TwoStepPickerDialog(context);
        }

        @Override
        public Builder withDialogListener(OnStepPickListener listener) {
            twoStepPickerDialog.setListener(listener);
            return this;
        }

        @Override
        public Builder withOkButton(String okButton) {
            twoStepPickerDialog.setOkButtonString(okButton);
            return this;
        }

        @Override
        public Builder withCancelButton(String cancelButton) {
            twoStepPickerDialog.setDismissButtonString(cancelButton);
            return this;
        }

        @Override
        public Builder withBaseData(List<String> baseData) {
            twoStepPickerDialog.setBaseData(baseData);
            return this;
        }

        @Override
        public Builder withStepData(List<List<String>> stepData) {
            twoStepPickerDialog.setStepData(stepData);
            return this;
        }

        @Override
        public Builder withBaseOnLeft(boolean b) {
            twoStepPickerDialog.setBaseOnLeft(b);
            return this;
        }

        @Override
        public Builder withOnStepDataRequested(OnStepDataRequestedListener onStepDataRequestedListener) {
            twoStepPickerDialog.setStepDataRequestedListener(onStepDataRequestedListener);
            return this;
        }

        @Override
        public TwoStepPickerDialog build() {
            return twoStepPickerDialog;
        }
    }

}
