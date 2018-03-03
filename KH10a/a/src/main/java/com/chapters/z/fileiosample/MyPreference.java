package com.chapters.z.fileiosample;


import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.preference.Preference;
import android.util.AttributeSet;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;


public class MyPreference extends Preference {
    static final String LEFT="图片";
    static final String CENTER="普通文本";
    static final String RIGHT="缓存文件";
    RadioButton radioButtonLeft,radioButtonCenter,radioButtonRight;
    TextView textViewSummary;
    String mCurrentValue;
    public MyPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
        setLayoutResource(R.layout.preference_my);
        mCurrentValue="普通文本";
    }

    @Override
    protected void onBindView(View view) {
        super.onBindView(view);
        radioButtonLeft=view.findViewById(R.id.radioButton_left);
        radioButtonLeft.setOnCheckedChangeListener(new RadioOnCheckedChangeListener() );
        radioButtonCenter=view.findViewById(R.id.radioButton_center);
        radioButtonCenter.setOnCheckedChangeListener(new RadioOnCheckedChangeListener() );
        radioButtonRight=view.findViewById(R.id.radioButton_right);
        radioButtonRight.setOnCheckedChangeListener(new RadioOnCheckedChangeListener() );
        textViewSummary=view.findViewById(R.id.tvsummary);

        textViewSummary.setText(mCurrentValue);
        switch (mCurrentValue){
            case LEFT:
                radioButtonLeft.toggle();
                break;
            case CENTER:
                radioButtonCenter.toggle();
                break;
            case RIGHT:
                radioButtonRight.toggle();
                break;
        }
    }

    class RadioOnCheckedChangeListener implements CompoundButton.OnCheckedChangeListener {

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            // Check which checkbox was clicked
            switch (buttonView.getId()) {
                case R.id.radioButton_left:
                    if (isChecked)
                        mCurrentValue=LEFT;
                    break;
                case R.id.radioButton_center:
                    if (isChecked)
                        mCurrentValue=CENTER;
                    break;
                case R.id.radioButton_right:
                    if (isChecked)
                        mCurrentValue=RIGHT;
                    break;
                // TODO: Veggie sandwich
            }
            persistString(mCurrentValue);
            textViewSummary.setText(mCurrentValue);
        }
    }




    @Override
    protected void onSetInitialValue(boolean restorePersistedValue, Object defaultValue) {
        if (restorePersistedValue) {
            // Restore existing state
            mCurrentValue = this.getPersistedString(LEFT);
        } else {
            // Set default state from the XML attribute
            mCurrentValue = (String) defaultValue;
            persistString(mCurrentValue);
        }


    }


    @Override
    protected Parcelable onSaveInstanceState() {
        final Parcelable superState = super.onSaveInstanceState();
        // Check whether this Preference is persistent (continually saved)
        if (isPersistent()) {
            // No need to save instance state since it's persistent,
            // use superclass state
            return superState;
        }

        // Create instance of custom BaseSavedState
        final SavedState myState = new SavedState(superState);
        // Set the state's value with the class member that holds current
        // setting value
        myState.value = mCurrentValue;
        return myState;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        // Check whether we saved the state in onSaveInstanceState
        if (state == null || !state.getClass().equals(SavedState.class)) {
            // Didn't save the state, so call superclass
            super.onRestoreInstanceState(state);
            return;
        }

        // Cast state to custom BaseSavedState and pass to superclass
        SavedState myState = (SavedState) state;
        super.onRestoreInstanceState(myState.getSuperState());

        // Set this Preference's widget to reflect the restored state
       switch (myState.value){
           case LEFT:
               radioButtonLeft.isChecked();
               break;
           case CENTER:
               radioButtonCenter.isChecked();
               break;
           case RIGHT:
               radioButtonRight.isChecked();
               break;
       }
    }



    private static class SavedState extends BaseSavedState {
        // Member that holds the setting's value
        // Change this data type to match the type saved by your Preference
        String value;

        public SavedState(Parcelable superState) {
            super(superState);
        }

        public SavedState(Parcel source) {
            super(source);
            // Get the current preference's value
            value = source.readString();  // Change this to read the appropriate data type
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            super.writeToParcel(dest, flags);
            // Write the preference's value
            dest.writeString(value);  // Change this to write the appropriate data type
        }

        // Standard creator object using an instance of this class
        public static final Creator<SavedState> CREATOR =
                new Creator<SavedState>() {

                    public MyPreference.SavedState createFromParcel(Parcel in) {
                        return new MyPreference.SavedState(in);
                    }

                    public MyPreference.SavedState[] newArray(int size) {
                        return new MyPreference.SavedState[size];
                    }
                };
    }

}
