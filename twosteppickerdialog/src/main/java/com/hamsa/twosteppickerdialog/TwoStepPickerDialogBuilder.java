package com.hamsa.twosteppickerdialog;

import java.util.List;


interface TwoStepPickerDialogBuilder {
    TwoStepPickerDialogBuilder withDialogListener(OnStepPickListener listener);

    TwoStepPickerDialogBuilder withOkButton(String okButton);

    TwoStepPickerDialogBuilder withCancelButton(String cancelButton);

    TwoStepPickerDialogBuilder withBaseData(List<String> baseData);

    TwoStepPickerDialogBuilder withStepData(List<List<String>> stepData);

    TwoStepPickerDialogBuilder withBaseOnLeft(boolean b);

    TwoStepPickerDialogBuilder withOnStepDataRequested(OnStepDataRequestedListener onStepDataRequestedListener);

    TwoStepPickerDialogBuilder withInitialBaseSelected(int i);

    TwoStepPickerDialogBuilder withInitialStepSelected(int i);

    TwoStepPickerDialog build();
}
