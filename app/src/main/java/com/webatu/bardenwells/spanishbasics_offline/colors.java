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
public class colors extends AppCompatActivity {

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
        words.add(new Word("White", "blanco", R.drawable.number_two, R.raw.white));
        words.add(new Word("Yellow", "amarillo", R.drawable.number_two, R.raw.yellow));
        words.add(new Word("Red", "rojo", R.drawable.number_four, R.raw.red));
        words.add(new Word("Gray", "gris", R.drawable.number_six, R.raw.gray));
        words.add(new Word("Purple", "morado", R.drawable.number_seven, R.raw.purple));
        words.add(new Word("Green", "verde", R.drawable.number_ten, R.raw.green));
        words.add(new Word("Blue", "azul", R.drawable.number_ten, R.raw.blue));
        words.add(new Word("Black", "negro", R.drawable.number_ten, R.raw.black));
        words.add(new Word("Fuchisa", "fucsia", R.drawable.number_three, R.raw.fuchsia));
        words.add(new Word("Silver", "plata", R.drawable.number_five, R.raw.silver));
        words.add(new Word("Maroon", "granate", R.drawable.number_eight, R.raw.maroon));
        words.add(new Word("Lime", "lima", R.drawable.number_nine, R.raw.lime));
        words.add(new Word("Olive", "aceituna", R.drawable.number_ten, R.raw.olive));
        words.add(new Word("Navy", "marina", R.drawable.number_ten, R.raw.navy));

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
                    mMediaPlayer = MediaPlayer.create(colors.this, word.getAudioResourceId());
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

