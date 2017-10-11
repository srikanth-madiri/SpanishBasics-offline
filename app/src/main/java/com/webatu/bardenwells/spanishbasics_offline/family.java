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

public class family extends AppCompatActivity {

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
        words.add(new Word("Father", "padre", R.drawable.number_one, R.raw.father));
        words.add(new Word("Mother", "madre", R.drawable.number_two, R.raw.mother));
        words.add(new Word("Daughter", "hija", R.drawable.number_four, R.raw.daughter));
        words.add(new Word("Husband", "esposo", R.drawable.number_five, R.raw.husband));
        words.add(new Word("Wife", "esposa", R.drawable.number_six, R.raw.wife));
        words.add(new Word("Brother", "hermano", R.drawable.number_seven, R.raw.brother));
        words.add(new Word("Sister", "hermana", R.drawable.number_eight, R.raw.sister));
        words.add(new Word("GrandFather", "abuelo", R.drawable.number_nine, R.raw.grandfather));
        words.add(new Word("GrandMother", "abuela", R.drawable.number_ten, R.raw.grandmother));
        words.add(new Word("GrandSon", "nieto", R.drawable.number_ten, R.raw.grandson));
        words.add(new Word("GrandDaughter", "nieta", R.drawable.number_ten, R.raw.granddaughter));
        words.add(new Word("Uncle", "tío", R.drawable.number_ten, R.raw.uncle));
        words.add(new Word("Aunt", "tía", R.drawable.number_ten, R.raw.aunt));
        words.add(new Word("Nephew", "sobrino", R.drawable.number_ten, R.raw.nephew));
        words.add(new Word("Niece", "sobrina", R.drawable.number_ten, R.raw.niece));
        words.add(new Word("Cousin", "primo", R.drawable.number_ten, R.raw.cousin));

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
                    mMediaPlayer = MediaPlayer.create(family.this, word.getAudioResourceId());
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


