package com.example.registerform;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{
    EditText edit_text_first_name, edit_text_last_name, edit_text_birthday, edit_text_address, edit_text_email;

    Button button_birthday_select, button_register;

    RadioGroup radio_group_gender;

    CheckBox checkBox;

    TextView text_view_register_status;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edit_text_first_name = findViewById(R.id.first_name);
        edit_text_last_name = findViewById(R.id.last_name);
        edit_text_birthday = findViewById(R.id.birthday);
        edit_text_address = findViewById(R.id.address);
        edit_text_email = findViewById(R.id.email);

        button_birthday_select = findViewById(R.id.birthday_select);
        button_register = findViewById(R.id.register);

        radio_group_gender = findViewById(R.id.radio_group_gender);

        checkBox = findViewById(R.id.checkbox);

        text_view_register_status = findViewById(R.id.register_status);
    }

    @Override
    public void onClick(View view)
    {
        System.out.println(view.getId());
        switch (view.getId())
        {
            case R.id.register:
                System.out.println("register");
                ArrayList<String> unfilled_EditText_list = new ArrayList<>();

//                Get all EditText in current layout
                ViewGroup container = (ViewGroup) findViewById(R.id.container);

                for (int i = 0; i < container.getChildCount(); i++)
                {
                    View child_view = container.getChildAt(i);

//                    Filter out all EditText type
                    if (child_view instanceof EditText)
                    {
                        unfilled_EditText_list.add((String) ((EditText) child_view).getHint());
                    }
                }
//                Check if there is any unfilled EditText
                if (!unfilled_EditText_list.isEmpty())
                {
                    String missing_fields = Arrays.toString(unfilled_EditText_list.toArray());
                    text_view_register_status.setText(
                            String.format("Missing fields: %s", missing_fields)
                    );
                }

                break;

            case R.id.birthday_select:
                System.out.println("birthday select button");
                break;
        }
    }
}