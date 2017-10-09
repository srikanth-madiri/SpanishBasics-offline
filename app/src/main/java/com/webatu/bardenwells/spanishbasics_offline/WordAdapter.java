package com.webatu.bardenwells.spanishbasics_offline;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by srikanth on 7/10/17.
 */
public class WordAdapter extends ArrayAdapter<Word> {
    //constructor for WordAdapter takes the context and data source and also colorId for an activity
    private int mColorResourceId;
    public WordAdapter(Activity context, ArrayList<Word> words, int colorId){
//calling the super(ArrayAdapter) constructor taking context resource id (which is not yet defined) and data source as arguments
        super(context, 0, words);
        mColorResourceId = colorId;
    }
    //overwrite the ArrayAdapter's getView() method to create custom ListView and return i
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //check if the existing view is being reused or inflate the view
        View listitemview = convertView;
        if (listitemview == null){
            listitemview = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }
//get the object located at this position located in the list
        Word currentword = getItem(position);
//find the textview mwiok in the list_item.xml
        TextView miwokTextView = (TextView) listitemview.findViewById(R.id.spanish_text_view);
//set the text of that textview to mMiwok from word class
        miwokTextView.setText(currentword.getSpanish());
//find the textview for default in the list_item.xml
        TextView defaultTextView = (TextView) listitemview.findViewById(R.id.default_text_view);
//set the text of the textview to the mdefault from word class
        defaultTextView.setText(currentword.getDefault());
//find the imageview from the list_item.xml
        ImageView iconView = (ImageView) listitemview.findViewById(R.id.image);
//check if the word has an image
        if (currentword.hasImage()) {
            //set the image of the imageView to mImageResourceId
            iconView.setVisibility(View.VISIBLE);
            iconView.setImageResource(currentword.getImageResourceId());
        }
        else {
            iconView.setVisibility(View.GONE);
        }
//get the container from word_list.xml for which the color has to be set
        View textContainer = listitemview.findViewById(R.id.text_container);
//find the color that the resource id maps to
        int color = ContextCompat.getColor(getContext(), mColorResourceId);
//set the background color
        textContainer.setBackgroundColor(color);
//returns a list containing one image and two textfields for every item in the ArrayList
        return listitemview;

    }
}
