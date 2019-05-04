package org.my.alias;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class WordsAdapter extends ArrayAdapter<Pair> {
    private final AppCompatActivity context;
    ArrayList<Pair> words;

    public WordsAdapter(AppCompatActivity context, ArrayList<Pair> words) {
        super(context, R.layout.result_element, words);
        this.context = context;
        this.words = words;
    }

    static class ViewHolder {
        public ImageView imageView;
        public TextView textView;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Pair word = words.get(position);
        final ViewHolder holder;
        View rowView = convertView;
        if (rowView == null) {
            LayoutInflater inflater = context.getLayoutInflater();
            rowView = inflater.inflate(R.layout.result_element, null, true);
            holder = new ViewHolder();
            holder.textView = rowView.findViewById(R.id.title);

            holder.textView.setTypeface(getTypeface(context));
            holder.imageView = rowView.findViewById(R.id.image);
            rowView.setTag(holder);
        } else {
            holder = (ViewHolder) rowView.getTag();
        }

        holder.textView.setText(word.getWord());

        if (word.isGuess()){
            holder.imageView.setImageResource(R.mipmap.ok);
        } else {
            holder.imageView.setImageResource(R.mipmap.cancel);
        }
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (word.isGuess()){
                    word.setGuess(false);
                    holder.imageView.setImageResource(R.mipmap.cancel);
                }else{
                    word.setGuess(true);
                    holder.imageView.setImageResource(R.mipmap.ok);
                }
            }
        });

        return rowView;
    }

    private Typeface getTypeface(Context context) {
        return Typeface.createFromAsset(context.getAssets(), "a_stamper.ttf");
    }
}
