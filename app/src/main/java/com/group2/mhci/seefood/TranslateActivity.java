package com.group2.mhci.seefood;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnKeyListener;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class TranslateActivity extends AppCompatActivity implements TextToSpeech.OnInitListener{

    private Toolbar mTopToolbar;
    private TextToSpeech textToSpeech;
    private TextView TransResult;
    private TextView TransSource;
    private Context context = TranslateActivity.this;
    private ListView translateList;
    private TranslateController translateController;
    private Locale loc;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        menu.findItem(R.id.action_search).setVisible(false);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_favorite) {
            Toast.makeText(context, "Function under implementation.", Toast.LENGTH_LONG).show();
            return true;
        }

        else if (id == R.id.action_search) {
            Toast.makeText(context, "Function under implementation.", Toast.LENGTH_LONG).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onInit(int status) {

        if (status == TextToSpeech.SUCCESS) {

            loc = new Locale("th");
            int result = textToSpeech.setLanguage(loc);

            if (result == TextToSpeech.LANG_MISSING_DATA
                    || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS", "This Language is not supported");
            } else {
                //speakOut("Welcome");
            }

        } else {
            Log.e("TTS", "Initilization Failed!");
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_translate);

        mTopToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        mTopToolbar.setTitle("Translate");
        setSupportActionBar(mTopToolbar);
        textToSpeech = new TextToSpeech(this, this);

        translateList = (ListView) findViewById(R.id.translateList);

        final String categoryID = getIntent().getStringExtra("CategoryID");
        final String itemID = getIntent().getStringExtra("ItemID");

        TransResult = (TextView) findViewById(R.id.TransResult);
        TransSource = (TextView) findViewById(R.id.TransSource);
        final ImageView foodImage = (ImageView) findViewById(R.id.translateFoodImage);

        translateController = new TranslateController(Integer.parseInt(categoryID),Integer.parseInt(itemID));

        TransResult.setText(food.foodList[Integer.parseInt(categoryID)][Integer.parseInt(itemID)].EN);
        foodImage.setImageResource(food.foodList[Integer.parseInt(categoryID)][Integer.parseInt(itemID)].images[0]);


        updateScreen();
        translateList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //updateScreen(transSentence.trans(input, "th"),input);
                if(position == translateController.getChoicesString().size()-1){
                    popUpTextPrompt();

                }
                else{
                    translateController.translate(position);
                    updateScreen();
                }
            }
        });

        /*
        EditText translateText = (EditText) findViewById(R.id.translateText);
        translateText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    String input = v.getText().toString();
                    updateScreen(transSentence.trans(input, "th"),input);
                    return false; // Pretend to not handle so event fall through and keyboard got hidden
                    //return true; // Handled
                }
                return false; // Not handled
            }
        });
        */
    }

    private void updateScreen(){
        this.TransResult.setText(translateController.lastTrans);
        this.TransSource.setText(translateController.lastTransSource);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, translateController.getChoicesString());
        translateList.setAdapter(adapter);

        if(translateController.enToTH) {
            loc = new Locale("th");
        }
        else {
            loc = new Locale("en");
        }
        textToSpeech.setLanguage(loc);

        speakOut(translateController.lastTrans);
    }

    private void popUpTextPrompt(){
        LayoutInflater layoutInflaterAndroid = LayoutInflater.from(context);
        View mView = layoutInflaterAndroid.inflate(R.layout.user_input_dialog_box, null);
        AlertDialog.Builder alertDialogBuilderUserInput = new AlertDialog.Builder(context);
        alertDialogBuilderUserInput.setView(mView);

        final EditText userInputDialogEditText = (EditText) mView.findViewById(R.id.userInputDialog);
        TextView dialogueTitle = (TextView) mView.findViewById(R.id.dialogTitle);
        if(translateController.enToTH) {
            dialogueTitle.setText("พิมพ์ประโยคที่ค้องการแปล");
            userInputDialogEditText.setHint("ข้อความ");
        }
        else {
            dialogueTitle.setText("Enter English text to translate");
            userInputDialogEditText.setHint("Message...");
        }
        alertDialogBuilderUserInput
                .setCancelable(false)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogBox, int id) {
                        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_IMPLICIT_ONLY);
                        String input = userInputDialogEditText.getText().toString();
                        translateController.translate(input);
                        updateScreen();
                    }
                })

                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogBox, int id) {
                                dialogBox.cancel();
                            }
                        });

        AlertDialog alertDialogAndroid = alertDialogBuilderUserInput.create();
        // Show soft keyboard
        alertDialogAndroid.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        alertDialogAndroid.show();
    }


    private void speakOut(String sayMe) {

        textToSpeech.speak(sayMe, TextToSpeech.QUEUE_FLUSH, null);
    }


}
