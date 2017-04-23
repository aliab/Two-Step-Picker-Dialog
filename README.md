# Two Step Picker Dialog For Android
[![](https://jitpack.io/v/aliab/Two-Step-Picker-Dialog.svg)](https://jitpack.io/#aliab/Two-Step-Picker-Dialog)

![Hero Image](https://raw.githubusercontent.com/aliab/Two-Step-Picker-Dialog/master/screenshot/heroimage.jpg)

## Description

Two step picker dialog for Android that helps you easily pick nested data on android.

## Usage

To use two step picker dialog you must add it as a dependency in your Gradle build:

Step 1. Add the JitPack repository to your build file
Add it in your root build.gradle at the end of repositories:

```groovy
allprojects {
		repositories {
			...
			maven { url "https://jitpack.io" }
		}
	}
```
Step 2. Add the dependency
```groovy
dependencies {
    compile 'com.github.aliab:Two-Step-Picker-Dialog:V1.1.0'
}
```

Then add the this to your java code:

```java
    new TwoStepPickerDialog
                    .Builder(this)
                    .withBaseData(baseData)
                    .withStepData(stepData) // if you want to pass step data dynamically, don't pass it in builder
                    .withOkButton("Get Me")
                    .withCancelButton("Cancel It")
                    .withBaseOnLeft(true) // if you want it RTL like, set it to false
                    .withInitialBaseSelected(0)
                    .withInitialStepSelected(0)
                    .withOnStepDataRequested(new OnStepDataRequestedListener() {
                        @Override
                        public List<String> onStepDataRequest(int baseDataPos) {
                            ... // return list of string base on your database/webservice ...
                        }
                    })
                    .withDialogListener(new OnStepPickListener() {
                        @Override
                        public void onStepPicked(int step, int pos) {
                            // get position of picked data
                        }

                        @Override
                        public void onDismissed() {
                            // know that user dismissed dialog
                        }
                    })
                    .build();
```


## Public Builder Methods

| Name | Description |
|:----:|:----:|
|withDialogListener(OnStepPickListener listener)| Get dialog actions |
|withOkButton(String okButton)| Set dialog positive button string|
|withCancelButton(String cancelButton)| Set dialog negative button string |
|withBaseData(List<String> baseData)| Set Base data of picker|
|withStepData(List<List<String>> stepData)| Set static data for steps - don't pass it if you implement OnStepDataRequestedListener|
|withStepData(withBaseOnLeft(boolean b))| If you want to show view RTL like, pass true|
|withOnStepDataRequested(OnStepDataRequestedListener onStepDataRequestedListener)| Set in step picker listener for return dynamic data|
|withInitialBaseSelected(int i);| Set selected base data item when dialog appears|
|withInitialStepSelected(int i)| Set selected step data item when dialog appears|

## Changelog

### v1.1.0

 * Ability to choose initial value

### v1.0.1

 * clean up manifest

### v1.0.0

 * Initial release

## License
```
   
The MIT License (MIT)

Copyright (c) 2017 Ali Abdolahi

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.

```
