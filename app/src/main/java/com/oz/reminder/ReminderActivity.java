package com.oz.reminder;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ReminderActivity extends AppCompatActivity {

    public static final String RESULT_EXTRA_REMINDER = "RESULT_EXTRA_REMINDER";

    private EditText titleEditText;
    private EditText messageEditText;

    public static void startForResult(Activity activity, int requestCode) {
        Intent intent = new Intent(activity, ReminderActivity.class);
        activity.startActivityForResult(intent, requestCode);
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder);

        titleEditText = (EditText) findViewById(R.id.titleEditText);
        messageEditText = (EditText) findViewById(R.id.messageEditText);
        Button saveButton = (Button) findViewById(R.id.saveButton);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveReminder();
            }
        });
    }

    private void saveReminder() {
        Reminder reminder = createReminder();

        if (isValid(reminder)) {
            notifyUser();
            setResultAndFinish(reminder);
        }
    }

    private void setResultAndFinish(Reminder reminder) {
        Intent data = new Intent();
        data.putExtra(RESULT_EXTRA_REMINDER, reminder);
        setResult(RESULT_OK, data);
        finish();
    }

    private Reminder createReminder() {
        String title = titleEditText.getText().toString();
        String message = messageEditText.getText().toString();
        return new Reminder(title, message);
    }

    private boolean isValid(Reminder reminder) {
        boolean isValid = true;

        if (reminder.getTitle().length() == 0) {
            titleEditText.requestFocus();
            titleEditText.setError("Field cannot be empty");
            isValid = false;
        }

        if (reminder.getMessage().length() == 0) {
            messageEditText.requestFocus();
            messageEditText.setError("Field cannot be empty");
            isValid = false;
        }

        return isValid;
    }

    private void notifyUser() {
        Toast.makeText(ReminderActivity.this, "Reminder Successful", Toast.LENGTH_LONG).show();
    }
}
