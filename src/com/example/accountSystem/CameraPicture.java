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
	public static Bitmap imageBitmap;
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
}
