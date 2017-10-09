package com.webatu.bardenwells.spanishbasics_offline;

/**
 * Created by srikanth on 7/10/17.
 */
public class Word {

    private String Default;
    private String spanish;
    //variable to check if image is there for a particular word
    private int NO_IMAGE_PROVIDED = -1;
    //variabe to hold audio resourse id
    private int mAudioResourceId;
    //image resource resource id for word
    private int mImageResourceId = NO_IMAGE_PROVIDED;
    //Constructor for all other numbers,colors,family Activity
    Word(String defaultTrans, String spanishTrans, int audioResourceId) {
        Default = defaultTrans;
        spanish = spanishTrans;
        mAudioResourceId = audioResourceId;
    }
    //constructor for Phrases and daily Phrases Activity
    Word(String defaultTrans, String spanishTrans, int Image, int audioResourceId) {
        Default = defaultTrans;
        spanish = spanishTrans;
        mImageResourceId = Image;
        mAudioResourceId = audioResourceId;
    }

    //gets the english translation
    public String getDefault(){
        return Default;
    }
    //returns miwok trnslation
    public String getSpanish(){
        return spanish;
    }
    //returns image resource id(int)
    public int getImageResourceId(){
        return mImageResourceId;
    }
    //to check if the word has an image (returns ture if it has)
    public boolean hasImage(){
        return mImageResourceId != NO_IMAGE_PROVIDED;
    }
    //method to get the audio resource id
    public int getAudioResourceId(){
        return mAudioResourceId;
    }
}
