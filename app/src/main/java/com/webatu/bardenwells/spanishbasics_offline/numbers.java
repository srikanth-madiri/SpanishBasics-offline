package com.webatu.bardenwells.spanishbasics_offline;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by srikanth on 28/9/17.
 */

public class numbers extends AppCompatActivity {

    private MediaPlayer mMediaPlayer;
    private MediaPlayer.OnCompletionListener mCompletionListener =  new MediaPlayer.OnCompletionListener(){
        @Override
        public void onCompletion(MediaPlayer mp){
            ReleaseMediaPlayer();
        }
    };
    //handles the audio focus
    private AudioManager mAudioManager;

    AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener =
            new AudioManager.OnAudioFocusChangeListener(){
                public void onAudioFocusChange(int focusChange){
                    if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                            focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK){
                        mMediaPlayer.start();
                        //start from beginning if lost
                        mMediaPlayer.seekTo(0);
                    }
                    else if (focusChange == AudioManager.AUDIOFOCUS_GAIN){
                        //resume playback
                        mMediaPlayer.start();
                    }
                    //when we permanently lost the AudioFocus
                    else if (focusChange == AudioManager.AUDIOFOCUS_LOSS){
                        //since we lost focus so,release the resources
                        ReleaseMediaPlayer();
                    }
                }
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);
        //create and setup the request for audio focus
        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
//creating an arrayList for numbers
        final ArrayList<Word> words = new ArrayList<Word>();

        words.add(new Word("zero", "cero", R.drawable.number_one, R.raw.zero));
        words.add(new Word("One", "un", R.drawable.number_one, R.raw.one));
        words.add(new Word("Two", "dos", R.drawable.number_two, R.raw.two));
        words.add(new Word("Three", "tres", R.drawable.number_three, R.raw.three));
        words.add(new Word("Four", "cuatro", R.drawable.number_four, R.raw.four));
        words.add(new Word("Five", "cinco", R.drawable.number_five, R.raw.five));
        words.add(new Word("Six", "seis", R.drawable.number_six, R.raw.six));
        words.add(new Word("Seven", "siete", R.drawable.number_seven, R.raw.seven));
        words.add(new Word("Eight", "ocho", R.drawable.number_eight, R.raw.eight));
        words.add(new Word("Nine", "nueve", R.drawable.number_nine, R.raw.nine));
        words.add(new Word("Ten", "diez", R.drawable.number_ten, R.raw.ten));
        words.add(new Word("Eleven", "once", R.drawable.number_one, R.raw.eleven));
        words.add(new Word("Twelve", "doce", R.drawable.number_one, R.raw.twelve));
        words.add(new Word("Thirteen", "trece", R.drawable.number_one, R.raw.thirteen));
        words.add(new Word("Fourteen", "catorce", R.drawable.number_one, R.raw.fourteen));
        words.add(new Word("Fifteen", "quince", R.drawable.number_one, R.raw.fifteen));
        words.add(new Word("Sixteen", "dieciséis", R.drawable.number_one, R.raw.sixteen));
        words.add(new Word("Seventeen", "diecisiete", R.drawable.number_one, R.raw.seventeen));
        words.add(new Word("Eighteen", "dieciocho", R.drawable.number_one, R.raw.eighteen));
        words.add(new Word("Nineteen", "diecinueve", R.drawable.number_one, R.raw.nineteen));
        words.add(new Word("Twenty", "veinte", R.drawable.number_one, R.raw.twenty));
        words.add(new Word("Twenty-One", "veintiuno", R.drawable.number_one, R.raw.twenty_one));
        words.add(new Word("Twenty-Two", "veintidós", R.drawable.number_one, R.raw.twenty_two));
        words.add(new Word("Twenty-Three", "veintitrés", R.drawable.number_one, R.raw.twenty_three));
        words.add(new Word("Twenty-Four", "veinticuatro", R.drawable.number_one, R.raw.twenty_four));
        words.add(new Word("Twenty-Five", "veinticinco", R.drawable.number_one, R.raw.twenty_five));
        words.add(new Word("Twenty-Six", "veintiséis", R.drawable.number_one, R.raw.twenty_six));
        words.add(new Word("Twenty-Seven", "veintisiete", R.drawable.number_one, R.raw.twenty_seven));
        words.add(new Word("Twenty-Eight", "veintiocho", R.drawable.number_one, R.raw.twenty_eight));
        words.add(new Word("Twenty-Nine", "veintinueve", R.drawable.number_one, R.raw.twenty_nine));
        words.add(new Word("Thirty", "treinta", R.drawable.number_one, R.raw.thirty));
        words.add(new Word("Thirty-One", "treinta y uno", R.drawable.number_one, R.raw.thirtyone));
        words.add(new Word("Thirty-Two", "treinta y dos", R.drawable.number_one, R.raw.thirty_two));
        words.add(new Word("Thirty-Three", "treinta y tres", R.drawable.number_one, R.raw.thirty_three));
        words.add(new Word("Thirty-Four", "treinta y cuatro", R.drawable.number_one, R.raw.thirty_four));
        words.add(new Word("Thirty-Five", "treinta y cinco", R.drawable.number_one, R.raw.thirty_five));
        words.add(new Word("Thirty-Six", "treinta y seis", R.drawable.number_one, R.raw.thirty_six));
        words.add(new Word("Thirty-Seven", "treinta y siete", R.drawable.number_one, R.raw.thirty_seven));
        words.add(new Word("Thirty-Eight", "treinta y ocho", R.drawable.number_one, R.raw.thirty_eight));
        words.add(new Word("Thirty-Nine", "treinta y nueve", R.drawable.number_one, R.raw.thirty_nine));
        words.add(new Word("Forty", "cuarenta", R.drawable.number_one, R.raw.forty));
        words.add(new Word("Forty-One", "cuarenta y uno", R.drawable.number_one, R.raw.forty_one));
        words.add(new Word("Forty-Two", "cuarenta y dos", R.drawable.number_one, R.raw.forty_two));
        words.add(new Word("Forty-Three", "cuarenta y tres", R.drawable.number_one, R.raw.forty_three));
        words.add(new Word("Forty-Four", "cuarenta y cuatro", R.drawable.number_one, R.raw.forty_four));
        words.add(new Word("Forty-Five", "cuarenta y cinco", R.drawable.number_one, R.raw.forty_five));
        words.add(new Word("Forty-Six", "cuarenta y seis", R.drawable.number_one, R.raw.forty_six));
        words.add(new Word("Forty-Seven", "cuarenta y siete", R.drawable.number_one, R.raw.forty_seven));
        words.add(new Word("Forty-Eight", "cuarenta y ocho", R.drawable.number_one, R.raw.forty_eight));
        words.add(new Word("Forty-Nine", "cuarenta y nueve", R.drawable.number_one, R.raw.forty_nine));
        words.add(new Word("Fifty", "cincuenta", R.drawable.number_one, R.raw.fifty));
        words.add(new Word("Fifty-One", "cincuenta y uno", R.drawable.number_one, R.raw.fifty_one));
        words.add(new Word("Fifty-Two", "cincuenta y dos", R.drawable.number_one, R.raw.fifty_two));
        words.add(new Word("Fifty-Three", "cincuenta y tres", R.drawable.number_one, R.raw.fifty_three));
        words.add(new Word("Fifty-Four", "cincuenta y cuatro", R.drawable.number_one, R.raw.fifty_four));
        words.add(new Word("Fifty-Five", "cincuenta y cinco", R.drawable.number_one, R.raw.fifty_five));
        words.add(new Word("Fifty-Six", "cincuenta y seis", R.drawable.number_one, R.raw.fifty_six));
        words.add(new Word("Fifty-Seven", "cincuenta y siete", R.drawable.number_one, R.raw.fifty_seven));
        words.add(new Word("Fifty-Eight", "cincuenta y ocho", R.drawable.number_one, R.raw.fifty_eight));
        words.add(new Word("Fifty-Nine", "cincuenta y nueve", R.drawable.number_one, R.raw.fifty_nine));
        words.add(new Word("Sixty", "sesenta", R.drawable.number_one, R.raw.sixty));
        words.add(new Word("Sixty-One", "sesenta y uno", R.drawable.number_one, R.raw.sixty_one));
        words.add(new Word("Sixty-Two", "sesenta y dos", R.drawable.number_one, R.raw.sixty_two));
        words.add(new Word("Sixty-Three", "sesenta y tres", R.drawable.number_one, R.raw.sixty_three));
        words.add(new Word("Sixty-Four", "sesenta y cuatro", R.drawable.number_one, R.raw.sixty_for));
        words.add(new Word("Sixty-Five", "sesenta y cinco", R.drawable.number_one, R.raw.sixty_five));
        words.add(new Word("Sixty-Six", "sesenta y seis", R.drawable.number_one, R.raw.sixty_six));
        words.add(new Word("Sixty-Seven", "sesenta y siete", R.drawable.number_one, R.raw.sixty_seven));
        words.add(new Word("Sixty-Eight", "sesenta y ocho", R.drawable.number_one, R.raw.sixty_eight));
        words.add(new Word("Sixty-Nine", "sesenta y nueve", R.drawable.number_one, R.raw.sixty_nine));
        words.add(new Word("Seventy-", "setenta", R.drawable.number_one, R.raw.seventy));
        words.add(new Word("Seventy-One", "setenta y uno", R.drawable.number_one, R.raw.seventy_one));
        words.add(new Word("Seventy-Two", "setenta y dos", R.drawable.number_one, R.raw.seventy_two));
        words.add(new Word("Seventy-Three", "setenta y tres", R.drawable.number_one, R.raw.seventy_three));
        words.add(new Word("Seventy-Four", "setenta y cuatro", R.drawable.number_one, R.raw.seventy_four));
        words.add(new Word("Seventy-Five", "setenta y cinco", R.drawable.number_one, R.raw.seventy_five));
        words.add(new Word("Seventy-Six", "setenta y seis", R.drawable.number_one, R.raw.seventy_six));
        words.add(new Word("Seventy-Seven", "setenta y siete", R.drawable.number_one, R.raw.seventy_seven));
        words.add(new Word("Seventy-Eight", "setenta y ocho", R.drawable.number_one, R.raw.seventy_eight));
        words.add(new Word("Seventy-Nine", "setenta y nueve", R.drawable.number_one, R.raw.seventy_nine));
        words.add(new Word("Eighty", "ochenta", R.drawable.number_one, R.raw.eighty));
        words.add(new Word("Eighty-One", "ochenta y uno", R.drawable.number_one, R.raw.eighty_one));
        words.add(new Word("Eighty-Two", "ochenta y dos", R.drawable.number_one, R.raw.eighty_two));
        words.add(new Word("Eighty-Three", "ochenta y tres", R.drawable.number_one, R.raw.eighty_three));
        words.add(new Word("Eighty-Four", "ochenta y cuatro", R.drawable.number_one, R.raw.eighty_four));
        words.add(new Word("Eighty-Five", "ochenta y cinco", R.drawable.number_one, R.raw.eighty_five));
        words.add(new Word("Eighty-Six", "ochenta y seis", R.drawable.number_one, R.raw.eighty_six));
        words.add(new Word("Eighty-Seven", "ochenta y siete", R.drawable.number_one, R.raw.eighty_seven));
        words.add(new Word("Eighty-Eight", "ochenta y ocho", R.drawable.number_one, R.raw.eighty_eight));
        words.add(new Word("Eighty-Nine", "ochenta y nueve", R.drawable.number_one, R.raw.eighty_nine));
        words.add(new Word("Ninety", "noventa", R.drawable.number_one, R.raw.ninety));
        words.add(new Word("Ninety-One", "noventa y uno", R.drawable.number_one, R.raw.ninety_one));
        words.add(new Word("Ninety-Two", "noventa y dos", R.drawable.number_one, R.raw.ninety_two));
        words.add(new Word("Ninety-Three", "noventa y tres", R.drawable.number_one, R.raw.ninety_three));
        words.add(new Word("Ninety-Four", "noventa y cuatro", R.drawable.number_one, R.raw.ninety_four));
        words.add(new Word("Ninety-Five", "noventa y cinco", R.drawable.number_one, R.raw.ninety_five));
        words.add(new Word("Ninety-Six", "noventa y seis", R.drawable.number_one, R.raw.ninety_six));
        words.add(new Word("Ninety-Seven", "noventa y siete", R.drawable.number_one, R.raw.ninety_seven));
        words.add(new Word("Ninety-Eight", "noventa y ocho", R.drawable.number_one, R.raw.ninty_eight));
        words.add(new Word("Ninety-Nine", "noventa y nueve", R.drawable.number_one, R.raw.ninty_nine));
        words.add(new Word("Hundred", "cien", R.drawable.number_one, R.raw.hundred));

//creating a custom Adapter for ListView
        WordAdapter adapter = new WordAdapter(this, words, R.color.list_background_color);
//getting the ListView to which the Adapter has to be added
        ListView listview = (ListView) findViewById(R.id.list);
//setting the Adapter
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                //getting the respective audio resource id
                Word word = words.get(position);
                //release the media player resources if it exists
                ReleaseMediaPlayer();
                //request audio focus for playback
                int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,
                        //use the music Stream
                        AudioManager.STREAM_MUSIC,
                        //request permanent focus
                        AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    // mAudioManager.registerMediaButtonEventReceiver();
                    try {
                        //create a media player handler
                        mMediaPlayer = MediaPlayer.create(numbers.this, word.getAudioResourceId());
                        mMediaPlayer.start();
                        //adding onCompletionListener to release the resources used
                        mMediaPlayer.setOnCompletionListener(mCompletionListener);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
    //releasing the resources when the app is stopped or user goes to other intent
    @Override
    public void onStop(){
        super.onStop();
        //when the activity is stopped releasethe resources
        ReleaseMediaPlayer();
    }
    //clean up the resourses before playing another song
    private void ReleaseMediaPlayer(){
        //if player is not null then it is plying some file
        if (mMediaPlayer != null){
            //irrespective of the current state release the media player
            mMediaPlayer.release();
            //set the media player back to null
            mMediaPlayer = null;
            //abandon audio focus when playback is done
            //unregister's audiofocuschange listener
            mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
        }
    }
}


