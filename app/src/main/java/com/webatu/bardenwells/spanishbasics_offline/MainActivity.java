package com.webatu.bardenwells.spanishbasics_offline;

import android.animation.Animator;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        // Set the content of the activity to use the activity_main.xml layout file
        setContentView(R.layout.activity_main);

        // Find the View that shows the why spanish? category
        final TextView why = (TextView) findViewById(R.id.why_spanish);

        // Set a click listener on that View
        why.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the why spanish? category is clicked on.
            @Override
            public void onClick(View view) {
                // Create a new intent to open the {@link why spanish}
                Intent whyIntent = new Intent(MainActivity.this, why_spanish.class);
               // Start the new activity
                startActivity(whyIntent);
            }
        });


        // Find the View that shows the alphabets category
        TextView alphabets = (TextView) findViewById(R.id.alphabets);

        // Set a click listener on that View
        alphabets.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the alphabets category is clicked on.
            @Override
            public void onClick(View view) {
                // Create a new intent to open the {@link alphabets}
                Intent alphaIntent = new Intent(MainActivity.this, alphabets.class);

                // Start the new activity
                startActivity(alphaIntent);
            }
        });


        // Find the View that shows the numbers category
        TextView numbers = (TextView) findViewById(R.id.numbers);

        // Set a click listener on that View
        numbers.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the numbers category is clicked on.
            @Override
            public void onClick(View view) {
                // Create a new intent to open the {@link numbers}
                Intent numbersIntent = new Intent(MainActivity.this, numbers.class);

                // Start the new activity
                startActivity(numbersIntent);
            }
        });


        // Find the View that shows colors category
        TextView colors = (TextView) findViewById(R.id.colors);

        // Set a click listener on that View
        colors.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the colors category is clicked on.
            @Override
            public void onClick(View view) {
                // Create a new intent to open the {@link colors}
                Intent colorsIntent = new Intent(MainActivity.this, colors.class);

                // Start the new activity
                startActivity(colorsIntent);
            }
        });


        // Find the View that shows the family category
        TextView family = (TextView) findViewById(R.id.family);

        // Set a click listener on that View
        family.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the family category is clicked on.
            @Override
            public void onClick(View view) {
                // Create a new intent to open the {@link family}
                Intent familyIntent = new Intent(MainActivity.this, family.class);

                // Start the new activity
                startActivity(familyIntent);
            }
        });


        // Find the View that shows the organs category
        TextView organs = (TextView) findViewById(R.id.organs);

        // Set a click listener on that View
        organs.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the organs category is clicked on.
            @Override
            public void onClick(View view) {
                // Create a new intent to open the {@link organs}
                Intent oragnsIntent = new Intent(MainActivity.this, organs.class);

                // Start the new activity
                startActivity(oragnsIntent);
            }
        });


        // Find the View that shows the animals category
        TextView animals = (TextView) findViewById(R.id.animals);

        // Set a click listener on that View
        animals.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the animals category is clicked on.
            @Override
            public void onClick(View view) {
                // Create a new intent to open the {@link animals}
                Intent animalsIntent = new Intent(MainActivity.this, animals.class);

                // Start the new activity
                startActivity(animalsIntent);
            }
        });


        // Find the View that shows the phrases category
        TextView phrases = (TextView) findViewById(R.id.phrases);

        // Set a click listener on that View
        phrases.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the phrases category is clicked on.
            @Override
            public void onClick(View view) {
                // Create a new intent to open the {@link phrases}
                Intent phrasesIntent = new Intent(MainActivity.this, phrases.class);

                // Start the new activity
                startActivity(phrasesIntent);
            }
        });


        // Find the View that shows the daily category
        TextView daily = (TextView) findViewById(R.id.daily);

        // Set a click listener on that View
        daily.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the daily category is clicked on.
            @Override
            public void onClick(View view) {
                // Create a new intent to open the {@link why spanish}
                Intent dailyIntent = new Intent(MainActivity.this, daily.class);

                // Start the new activity
                startActivity(dailyIntent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // Handle item selection
        switch (item.getItemId()) {

            case R.id.night_icon:

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setCancelable(true);
                builder.setTitle("Night Mode");
                builder.setMessage("Switch Between Normal And Night View");
                builder.setPositiveButton("NightMode",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                View v1 = findViewById(R.id.why_spanish);
                                v1.setBackgroundColor(getResources().getColor(R.color.black));

                                View v2 = findViewById(R.id.alphabets);
                                TextView tv2 = (TextView) findViewById(R.id.alphabets);
                                v2.setBackgroundColor(getResources().getColor(R.color.white));
                                tv2.setTextColor(getResources().getColor(R.color.black));

                                View v3 = findViewById(R.id.numbers);
                                TextView tv3 = (TextView) findViewById(R.id.numbers);
                                v3.setBackgroundColor(getResources().getColor(R.color.white));
                                tv3.setTextColor(getResources().getColor(R.color.black));

                                View v4 = findViewById(R.id.colors);
                                v4.setBackgroundColor(getResources().getColor(R.color.black));

                                View v5 = findViewById(R.id.family);
                                v5.setBackgroundColor(getResources().getColor(R.color.black));

                                View v6 = findViewById(R.id.organs);
                                TextView tv6 = (TextView) findViewById(R.id.organs);
                                v6.setBackgroundColor(getResources().getColor(R.color.white));
                                tv6.setTextColor(getResources().getColor(R.color.black));

                                View v7 = findViewById(R.id.animals);
                                TextView tv7 = (TextView) findViewById(R.id.animals);
                                v7.setBackgroundColor(getResources().getColor(R.color.white));
                                tv7.setTextColor(getResources().getColor(R.color.black));

                                View v8 = findViewById(R.id.phrases);
                                v8.setBackgroundColor(getResources().getColor(R.color.black));

                            }
                        });


                builder.setNegativeButton("Normal", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                        View v1 = findViewById(R.id.why_spanish);
                        v1.setBackgroundColor(getResources().getColor(R.color.why_spanish));

                        View v2 = findViewById(R.id.alphabets);
                        TextView tv2 = (TextView) findViewById(R.id.alphabets);
                        v2.setBackgroundColor(getResources().getColor(R.color.alphabets));
                        tv2.setTextColor(getResources().getColor(R.color.white));

                        View v3 = findViewById(R.id.numbers);
                        TextView tv3 = (TextView) findViewById(R.id.numbers);
                        v3.setBackgroundColor(getResources().getColor(R.color.numbers));
                        tv3.setTextColor(getResources().getColor(R.color.white));

                        View v4 = findViewById(R.id.colors);
                        v4.setBackgroundColor(getResources().getColor(R.color.colors));

                        View v5 = findViewById(R.id.family);
                        v5.setBackgroundColor(getResources().getColor(R.color.family));

                        View v6 = findViewById(R.id.organs);
                        TextView tv6 = (TextView) findViewById(R.id.organs);
                        v6.setBackgroundColor(getResources().getColor(R.color.organs));
                        tv6.setTextColor(getResources().getColor(R.color.white));

                        View v7 = findViewById(R.id.animals);
                        TextView tv7 = (TextView) findViewById(R.id.animals);
                        v7.setBackgroundColor(getResources().getColor(R.color.animals));
                        tv7.setTextColor(getResources().getColor(R.color.white));

                        View v8 = findViewById(R.id.phrases);
                        v8.setBackgroundColor(getResources().getColor(R.color.phrases));


                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
                return true;

            case R.id.Developer:

                // Create a new intent to open the Developer Page
                Intent developerIntent = new Intent(MainActivity.this, Developer.class);

                // Start the new activity
                startActivity(developerIntent);

                return true;

            case R.id.Rate_Us:

                return true;

            case R.id.More_Apps:

                AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
                builder1.setCancelable(true);
                builder1.setTitle("Stay Tuned!!!");
                builder1.setMessage("COMING SOOOOOON.....");
                builder1.setPositiveButton("ok",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });


                builder1.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                AlertDialog dialog1 = builder1.create();
                dialog1.show();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }
}