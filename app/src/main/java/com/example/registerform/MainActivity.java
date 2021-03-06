package com.example.registerform;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{
    EditText EditText_first_name, EditText_last_name, EditText_birthday, EditText_address, EditText_email;
    Button button_birthday_select, button_register;
    RadioGroup RadioGroup_gender;
    CheckBox checkBox;
    TextView text_view_register_status;
    RadioButton RadioButton_male, RadioButton_female;
    EditText[] EditText_list;

    String respondStr;

    final Calendar calendar = Calendar.getInstance();
    int day, month, year;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText_first_name = findViewById(R.id.first_name);
        EditText_last_name = findViewById(R.id.last_name);
        EditText_birthday = findViewById(R.id.birthday);
        EditText_address = findViewById(R.id.address);
        EditText_email = findViewById(R.id.email);

        EditText_list = new EditText[]{
                EditText_first_name,
                EditText_last_name,
                EditText_birthday,
                EditText_address,
                EditText_email
        };

        button_birthday_select = findViewById(R.id.birthday_select);
        button_birthday_select.setOnClickListener(this);
        button_register = findViewById(R.id.register);
        button_register.setOnClickListener(this);


        RadioGroup_gender = findViewById(R.id.radio_group_gender);
        RadioButton_female = findViewById(R.id.radio_btn_female);
        RadioButton_male = findViewById(R.id.radio_btn_male);

        checkBox = findViewById(R.id.checkbox);

        text_view_register_status = findViewById(R.id.register_status);


        day = calendar.get(Calendar.DAY_OF_MONTH);
        month = calendar.get(Calendar.MONTH);
        year = calendar.get(Calendar.YEAR);
    }

    @Override
    public void onClick(View view)
    {

        switch (view.getId())
        {
            case R.id.register:
                String respond = "Missing: ";
                String field_value;

                if (!checkBox.isChecked())
                {
                    respond = "Please agree to our Terms of Use.";
                }
                else if (RadioGroup_gender.getCheckedRadioButtonId() == -1)
                {
                    respond = "Please select your gender.";
                }
                else
                {
                    for (EditText current_EditText : EditText_list)
                    {
                        field_value = current_EditText.getText().toString();
                        if (field_value.isEmpty())
                        {
                            respond += String.format("%s, ", current_EditText.getHint());
                        }
                    }
//                    Remove the last comma + space
                    if ((respond != null) && (respond.length() > 0))
                    {
                        respond = respond.substring(0, respond.length() - 2);
                    }
                }

//                Change style for successful respond
                if (respond.equals("Missing")) //Mean there's no unfilled filed
                {
                    text_view_register_status.setTextColor(Color.parseColor("#60d394"));
                    text_view_register_status.setTextSize(20);
                    respond = "Successfully register!";
                }
                else //Reset to default style
                {
                    text_view_register_status.setTextColor(Color.parseColor("#ee6055"));
                    text_view_register_status.setTextSize(17);
                }

                text_view_register_status.setText(respond);
                break;


            case R.id.birthday_select:
                // date picker dialog
                DatePickerDialog datePicker = new DatePickerDialog(MainActivity.this,
                        new DatePickerDialog.OnDateSetListener()
                        {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth)
                            {
                                EditText_birthday.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                            }
                        }, year, month, day);
                datePicker.show();
                break;
        }
    }
}