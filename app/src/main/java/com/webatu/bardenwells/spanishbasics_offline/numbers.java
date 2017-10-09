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
        words.add(new Word("One", "Ondhu", R.drawable.number_one, R.raw.number_one));
        words.add(new Word("Two", "Eradu", R.drawable.number_two, R.raw.number_one));
        words.add(new Word("Three", "Mooru", R.drawable.number_three, R.raw.number_one));
        words.add(new Word("Four", "Naalugu", R.drawable.number_four, R.raw.number_one));
        words.add(new Word("Five", "aaa", R.drawable.number_five, R.raw.number_one));
        words.add(new Word("Six", "sss", R.drawable.number_six, R.raw.number_one));
        words.add(new Word("Seven", "ddd", R.drawable.number_seven, R.raw.number_one));
        words.add(new Word("Eight", "fff", R.drawable.number_eight, R.raw.number_one));
        words.add(new Word("Nine", "ggg", R.drawable.number_nine, R.raw.number_one));
        words.add(new Word("Ten", "hhh", R.drawable.number_ten, R.raw.number_one));
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

                    //create a media player handler
                    mMediaPlayer = MediaPlayer.create(numbers.this, word.getAudioResourceId());
                    mMediaPlayer.start();
                    //adding onCompletionListener to release the resources used
                    mMediaPlayer.setOnCompletionListener(mCompletionListener);
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


