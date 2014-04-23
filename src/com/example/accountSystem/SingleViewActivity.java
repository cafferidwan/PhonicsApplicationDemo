package com.example.accountSystem;

import com.example.phonicsapp.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

public class SingleViewActivity extends Activity
{
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.singleview);

		 // Get intent data
        Intent i = getIntent();
 
        // Selected image id
        int position = i.getExtras().getInt("id");
        System.out.println("position:"+position);
//        ImageAdapter imageAdapter = new ImageAdapter(this);
// 
//        ImageView imageView = (ImageView) findViewById(R.id.SingleView);
//        imageView.setImageResource(imageAdapter.mThumbIds[position]);
	}
}
