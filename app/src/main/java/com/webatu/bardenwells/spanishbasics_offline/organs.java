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

public class organs extends AppCompatActivity {

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

        words.add(new Word("Head", "cabeza", R.drawable.number_ten, R.raw.head));
        words.add(new Word("Arm", "brazo", R.drawable.number_ten, R.raw.arm));
        words.add(new Word("Waist", "cintura", R.drawable.number_ten, R.raw.waist));
        words.add(new Word("Leg", "pierna", R.drawable.number_ten, R.raw.leg));
        words.add(new Word("Face", "cara", R.drawable.number_ten, R.raw.face));
        words.add(new Word("Chest", "pecho", R.drawable.number_ten, R.raw.chest));
        words.add(new Word("Stomach", "estómago", R.drawable.number_ten, R.raw.stomach));
        words.add(new Word("Foot", "pie", R.drawable.number_ten, R.raw.foot));
        words.add(new Word("Eye", "ojo", R.drawable.number_ten, R.raw.eye));
        words.add(new Word("Eyebrow", "ceja", R.drawable.number_ten, R.raw.eye_brow));
        words.add(new Word("Nose", "nariz", R.drawable.number_ten, R.raw.nose));
        words.add(new Word("Chin", "mentón", R.drawable.number_ten, R.raw.chin));
        words.add(new Word("Hair", "pelo", R.drawable.number_ten, R.raw.hair));
        words.add(new Word("Ear", "oreja", R.drawable.number_ten, R.raw.ear));
        words.add(new Word("Lip", "labio", R.drawable.number_ten, R.raw.lips));
        words.add(new Word("Neck", "cuello", R.drawable.number_ten, R.raw.enck));
        words.add(new Word("Nail", "clavo", R.drawable.number_ten, R.raw.nail));
        words.add(new Word("Finger", "dedo", R.drawable.number_ten, R.raw.finger));
        words.add(new Word("Wrist", "muñeca", R.drawable.number_ten, R.raw.wrist));
        words.add(new Word("Palm", "palma", R.drawable.number_ten, R.raw.palm));
        words.add(new Word("Elbow", "codo", R.drawable.number_ten, R.raw.elbow));
        words.add(new Word("Shoulder", "hombro", R.drawable.number_ten, R.raw.shoulder));
        words.add(new Word("Knee", "rodilla", R.drawable.number_ten, R.raw.knee));
        words.add(new Word("Thigh", "muslo", R.drawable.number_ten, R.raw.thigh));
        words.add(new Word("Forehead", "frente", R.drawable.number_two, R.raw.forehead));
        words.add(new Word("Cheek", "mejilla", R.drawable.number_two, R.raw.cheek));
        words.add(new Word("Tooth", "diente", R.drawable.number_three, R.raw.tooth));
        words.add(new Word("Brain", "cerebro", R.drawable.number_four, R.raw.brain));
        words.add(new Word("Throat", "garganta", R.drawable.number_five, R.raw.throat));
        words.add(new Word("Lung", "pulmón", R.drawable.number_six, R.raw.lung));
        words.add(new Word("heart", "corazón", R.drawable.number_seven, R.raw.heart));
        words.add(new Word("Kidney", "riñón", R.drawable.number_eight, R.raw.kidney));
        words.add(new Word("Skeleton", "esqueleto", R.drawable.number_nine, R.raw.skeleton));
        words.add(new Word("Skull", "cráneo", R.drawable.number_ten, R.raw.skull));
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
                    mMediaPlayer = MediaPlayer.create(organs.this, word.getAudioResourceId());
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


