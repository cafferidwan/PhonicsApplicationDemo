package com.example.accountSystem;

import java.io.File;
import java.util.ArrayList;

import com.example.phonicsapp.R;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

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
	public static int counter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		instance=this;
		counter=0;
		
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
	
	public void onClickSnapShotCheck(int position)
	{
		 File imgFile = new  File("/sdcard/PhonicsApp/AccountPic/"+position+".jpg");
 		 if(!imgFile.exists())
 		 {
 			 accountNumber = position;
 			 
 			 counter++;
 			 if(counter==1)
 			 {
 				 instance.finish();
 				 instance.startActivity(new Intent(instance.getBaseContext(), CameraPicture.class));
 			 } 
 	     }
 		 
	}
}