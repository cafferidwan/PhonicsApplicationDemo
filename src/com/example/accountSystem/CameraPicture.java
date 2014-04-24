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
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

public class CameraPicture extends Activity 
{
	private Camera mCamera;
	private CameraPreview mPreview;
	public static Bitmap imageBitmap;
	// a bitmap to display the captured image
	private Bitmap bitmap;
	public static int counter;

	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_camera);
		counter=0;
		mCamera = getCameraInstance();

		mPreview = new CameraPreview(this, mCamera);
		FrameLayout preview = (FrameLayout) findViewById(R.id.fl);
		preview.addView(mPreview);
		
		ImageView im = (ImageView) findViewById(R.id.imageView1);
		im.setOnClickListener(new View.OnClickListener() 
		{
			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub
				counter++;
				if(counter==1)
				{
					takePicture();
				}
			}
		});
		
	}

	@SuppressLint("NewApi")
	public static Camera getCameraInstance()
	{
		Camera c = null;
		try
		{
			c = Camera.open(1);
		}
		catch (Exception e) 
		{
			
		}
		return c;
	}
	
	public void takePicture()
	{
		Camera.PictureCallback mCall = new Camera.PictureCallback()
		{

			public void onPictureTaken(byte[] data, Camera camera)
			{
				// decode the data obtained by the camera into a Bitmap
				bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);

				Matrix matrix = new Matrix();

				matrix.postRotate(180);
//				 float[] mirrorY = { 1, 0, 0, 0, 1, 0, 0, 0, -1};
//		         Matrix matrixMirrorY = new Matrix();
//		         matrixMirrorY.setValues(mirrorY);
//
//		        matrix.postConcat(matrixMirrorY);
		        		
				Bitmap scaledBitmap = Bitmap.createScaledBitmap(bitmap, 226,
						223, true);
				imageBitmap = Bitmap.createBitmap(scaledBitmap, 0, 0,
						scaledBitmap.getWidth(), scaledBitmap.getHeight(), matrix, true);
				
				writeFile();
				// closing the activity after taking picture
				finish();
				startActivity(new Intent(getBaseContext(), AccountDisplayPage.class));

			}
		};
		mCamera.takePicture(null, null, mCall);
		
	}
	
	@SuppressLint("SdCardPath")
	public static void writeFile()
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
				imageBitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
				
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
					imageBitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
					
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
		}
		//counter=0;
	}
}
