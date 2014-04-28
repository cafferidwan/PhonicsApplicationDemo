package com.example.accountSystem;

import java.io.File;
import java.util.ArrayList;

import com.example.phonicsapp.R;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class AccountDisplayPage extends Activity 
{
	
	public static String TAG = AccountDisplayPage.class.getSimpleName();

	public GridView gridView;
	public ArrayList<Item> gridArray = new ArrayList<Item>();
	public CustomGridViewAdapter customGridAdapter;
	
	public static int accountNumber=66;
	public Bitmap[] accountPic = new Bitmap[10];
	
	public AccountDisplayPage instance;
	public File[] imgFile= new File[10];
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		instance=this;
		
		//loading account images
		loadingAccountImage();
	
		
		//loading text view labels 
		loadTextView();
		
		gridView = (GridView) findViewById(R.id.gridview);
		gridView.setOnItemClickListener(new OnItemClickListener() 
		{
	          public void onItemClick(AdapterView<?> parent, View v, int position, long id) 
	          {
	        	  //if the image file does not exists, then take a snap shot
	        	  onClickSnapShotCheck(position);
	          }
	      });
		customGridAdapter = new CustomGridViewAdapter(this, R.layout.row_grid,
				gridArray);
		gridView.setAdapter(customGridAdapter);
		
	}
	
	public void loadTextView()
	{ 
		//adding textview icons
		gridArray.add(new Item(accountPic[0], "0"));
		gridArray.add(new Item(accountPic[1], "1"));
		gridArray.add(new Item(accountPic[2], "2"));
		gridArray.add(new Item(accountPic[3], "3"));
		gridArray.add(new Item(accountPic[4], "4"));
		gridArray.add(new Item(accountPic[5], "5"));
	} 
	
	public void loadingAccountImage()
	{
		for(int i=0;i<6;i++)
		{
			imgFile[i] = new  File("/sdcard/PhonicsApp/AccountPic/"+i+".jpg");
			//if the camera snap shot image file exists, then load the snap shot
			if(imgFile[i].exists())
			{
				
				Bitmap scaled = BitmapFactory.decodeFile(imgFile[i].getAbsolutePath());
				accountPic[i] =  Bitmap.createScaledBitmap(scaled, 226,
						223, false);
//				accountPic[i] = ShrinkBitmap(imgFile[i].getAbsolutePath(),226,223);
				
			}
			//else load the default image   
			else
			{
				Bitmap b=BitmapFactory.decodeResource(instance.getResources(),
						R.drawable.images);
				accountPic[i] = Bitmap.createScaledBitmap(b, 226,
						223, false);
			}
		}

	}
	
	public Bitmap ShrinkBitmap(String file, int width, int height) {
        Log.d("ShrinkBitmap", "Trying to shrink " + file + " to " + width + "x" + height);
        BitmapFactory.Options bmpFactoryOptions = new BitmapFactory.Options();
        bmpFactoryOptions.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeFile(file, bmpFactoryOptions);
        Log.d("ShrinkBitmap", "Original size: " + bmpFactoryOptions.outWidth + "x" + bmpFactoryOptions.outHeight);
 
        int heightRatio = (int) Math.ceil(bmpFactoryOptions.outHeight / (float) height);
        int widthRatio = (int) Math.ceil(bmpFactoryOptions.outWidth / (float) width);
        if (heightRatio > 1 || widthRatio > 1) {
            if (heightRatio > widthRatio) {
                bmpFactoryOptions.inSampleSize = heightRatio;
                Log.d("ShrinkBitmap", "Shrink ratio: " + heightRatio);
            } else {
                bmpFactoryOptions.inSampleSize = widthRatio;
                Log.d("ShrinkBitmap", "Shrink ratio: " + widthRatio);
            }
        }
        bmpFactoryOptions.inJustDecodeBounds = false;
        bitmap = BitmapFactory.decodeFile(file, bmpFactoryOptions);
        return bitmap;
    }
	
	private void setPic(String imagePath, ImageView destination) 
	{
	    int targetW = 226;//destination.getWidth();
	    int targetH = 223;//destination.getHeight();
	    // Get the dimensions of the bitmap
	    BitmapFactory.Options bmOptions = new BitmapFactory.Options();
	    
	    bmOptions.inJustDecodeBounds = true;
	    
	    BitmapFactory.decodeFile(imagePath, bmOptions);
	    
	    int photoW = bmOptions.outWidth;
	    int photoH = bmOptions.outHeight;

	    // Determine how much to scale down the image
	    int scaleFactor = Math.min(photoW/targetW, photoH/targetH);

	    // Decode the image file into a Bitmap sized to fill the View
	    bmOptions.inJustDecodeBounds = false;
	    bmOptions.inSampleSize = scaleFactor;
	    bmOptions.inPurgeable = true;

	    Bitmap bitmap = BitmapFactory.decodeFile(imagePath, bmOptions);
	    destination.setImageBitmap(bitmap);
	}
	
	public void onClickSnapShotCheck(int position)
	{
		 File imgFile = new  File("/sdcard/PhonicsApp/AccountPic/"+position+".jpg");
 		 if(!imgFile.exists())
 		 {
 			 accountNumber = position;
 			 instance.finish();
 			 instance.startActivity(new Intent(instance.getBaseContext(), CameraPicture.class));
 	     }
 		 
	}
}