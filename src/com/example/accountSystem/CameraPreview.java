package com.example.accountSystem;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.hardware.Camera;
import android.os.Environment;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class CameraPreview extends SurfaceView implements SurfaceHolder.Callback
{
	private SurfaceHolder mHolder;
	private Camera mCamera;

	@SuppressWarnings("deprecation")
	public CameraPreview(Context context, Camera camera)
	{
		super(context);
		mCamera = camera;

		mHolder = getHolder();
		mHolder.addCallback(this);
		mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
	}

	public void surfaceCreated(SurfaceHolder holder)
	{
		try 
		{
			mCamera.setPreviewDisplay(holder);
			mCamera.startPreview();
		} 
		catch (IOException e) 
		{
			mCamera.release();
			mCamera = null;
		}
	}

	public void surfaceDestroyed(SurfaceHolder holder)
	{
		mCamera.release();
	}

	public void surfaceChanged(SurfaceHolder holder, int format, int w, int h)
	{
		mCamera.setDisplayOrientation(180);
		if (mHolder.getSurface() == null)
		{
			return;
		}

		try
		{
			mCamera.stopPreview();
		}
		catch (Exception e)
		{
			
		}

		try 
		{
			mCamera.setPreviewDisplay(mHolder);
			mCamera.startPreview();
		} 
		catch (Exception e) 
		{

		}
	}

}