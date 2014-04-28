package com.example.accountSystem;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.example.phonicsapp.R;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;

public class CameraPicture extends Activity 
{
	private Camera mCamera;
	public static Bitmap imageBitmap;
	private Bitmap bitmap;
	public static int counter;
	
	private static int TAKE_PICTURE = 1;
	private Uri outputFileUri;
	File f ;

	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_camera);
		
		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		
		File file = new File(Environment.getExternalStorageDirectory(), "/PhonicsApp/AccountPic");
		if (!file.exists())  
		{
		    file.mkdirs();
		    f = new File(Environment.getExternalStorageDirectory(),
					"/PhonicsApp/AccountPic/"+AccountDisplayPage.accountNumber+".jpg");
			outputFileUri = Uri.fromFile(f);
		}
		else if(file.exists())
		{
			f = new File(Environment.getExternalStorageDirectory(),
					"/PhonicsApp/AccountPic/"+AccountDisplayPage.accountNumber+".jpg");
			outputFileUri = Uri.fromFile(f);
		}
		intent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
		startActivityForResult(intent, TAKE_PICTURE);
		
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
//		if (requestCode == TAKE_PICTURE)
//		{
			finish();
			startActivity(new Intent(getBaseContext(), AccountDisplayPage.class));
//		}
 
	}
	
	@SuppressLint("SdCardPath")
	public static boolean writeFile()
	{
		File folder = new File(Environment.getExternalStorageDirectory() + "/PhonicsApp/AccountPic");
		boolean success = true;
		if (!folder.exists())
		{
		    success = folder.mkdirs();
		}
		if (success) 
		{
		    // Do something on success
			try 
			{
				FileOutputStream out = new FileOutputStream("/sdcard/PhonicsApp/AccountPic/"+AccountDisplayPage.accountNumber+".jpg");
				//imageBitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
				
				//Log.d(DEBUG_TAG, "camerafile: " + camerafile);
			}
			catch (FileNotFoundException e) 
			{
				Log.d("CAMERA", e.getMessage());
			} 
			catch (IOException e) 
			{
				Log.d("CAMERA", e.getMessage());
			}
		}
		else 
		{
		    // Do something else on failure 
			if(folder.exists()==true)
			{
				try 
				{
					FileOutputStream out = new FileOutputStream("/sdcard/PhonicsApp/AccountPic/1.jpg");
//					imageBitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
					
				}
				catch (FileNotFoundException e) 
				{
					Log.d("CAMERA", e.getMessage());
				} 
				catch (IOException e) 
				{
					Log.d("CAMERA", e.getMessage());
				}
			}
		}
		return success;
	}
}
