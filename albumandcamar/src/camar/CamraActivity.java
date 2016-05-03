package camar;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.example.albumandcamar.R;

import album.ShowSinglePicsActivity;
import android.app.Activity;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;
import util.ScreenUtils;

public class CamraActivity extends Activity implements SurfaceHolder.Callback, PictureCallback, OnClickListener {
	private SurfaceView camraSurfaceView;
	private Button takePicBtn, savePicBtn;
	private Camera camera;
	private Camera.Parameters parameters = null;
	private byte[] picResult = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.camra_activity_layout);
		setUpView();
	}

	private void setUpView() {
		camraSurfaceView = (SurfaceView) findViewById(R.id.surfaceView1);
		camraSurfaceView.getHolder().addCallback(this);
		camraSurfaceView.getHolder().setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
		camraSurfaceView.getHolder().setKeepScreenOn(true);
		camraSurfaceView.getHolder().setFixedSize(ScreenUtils.getScreenWidth(getApplicationContext()),
				ScreenUtils.getStatusHeight(getApplicationContext()));
		takePicBtn = (Button) findViewById(R.id.button1);
		savePicBtn = (Button) findViewById(R.id.button2);
		takePicBtn.setOnClickListener(this);
		savePicBtn.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.button1:
			camera.takePicture(null, null, CamraActivity.this);
			break;
		case R.id.button2:
			if (picResult != null) {
				try {
					saveToSDCard(picResult);
					camera.startPreview(); // 拍完照后，重新开始预览
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					Toast.makeText(getApplicationContext(), "保存文件出错", Toast.LENGTH_SHORT).show();
					;
				}
			}

			break;

		default:
			break;
		}
	}

	public void saveToSDCard(byte[] data) throws IOException {
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss"); // 格式化时间
		String filename = format.format(date) + ".jpg";
		File fileFolder = new File(Environment.getExternalStorageDirectory() + "/finger/");
		if (!fileFolder.exists()) { // 如果目录不存在，则创建一个名为"finger"的目录
			fileFolder.mkdir();
		}
		File jpgFile = new File(fileFolder, filename);
		FileOutputStream outputStream = new FileOutputStream(jpgFile); // 文件输出流
		outputStream.write(data); // 写入sd卡中
		outputStream.close(); // 关闭输出流
		Toast.makeText(getApplicationContext(), "图片已保存", Toast.LENGTH_SHORT).show();
		Uri contentUri = Uri.fromFile(jpgFile);
		Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE,contentUri);
		sendBroadcast(mediaScanIntent);
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		try {
			camera = Camera.open(); // 打开摄像头
			camera.setPreviewDisplay(holder); // 设置用于显示拍照影像的SurfaceHolder对象
			camera.setDisplayOrientation(getPreviewDegree(CamraActivity.this));
			camera.startPreview(); // 开始预览
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
		// TODO Auto-generated method stub
		parameters = camera.getParameters(); // 获取各项参数
		parameters.setPictureFormat(PixelFormat.JPEG); // 设置图片格式
		parameters.setPreviewSize(ScreenUtils.getScreenHeight(getApplicationContext()),
				ScreenUtils.getScreenHeight(getApplicationContext())); // 设置预览大小
		parameters.setPreviewFrameRate(5); // 设置每秒显示4帧
		parameters.setPictureSize(ScreenUtils.getScreenWidth(getApplicationContext()),
				ScreenUtils.getScreenHeight(getApplicationContext())); // 设置保存的图片尺寸
		parameters.setJpegQuality(80); // 设置照片质量

	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		if (camera != null) {
			camera.release(); // 释放照相机
			camera = null;
		}

	}

	// 拍照返回
	@Override
	public void onPictureTaken(byte[] data, Camera camera) {
		// TODO Auto-generated method stub
		picResult = data;

	}

	// 提供一个静态方法，用于根据手机方向获得相机预览画面旋转的角度
	public static int getPreviewDegree(Activity activity) {
		// 获得手机的方向
		int rotation = activity.getWindowManager().getDefaultDisplay().getRotation();
		int degree = 0;
		// 根据手机的方向计算相机预览画面应该选择的角度
		switch (rotation) {
		case Surface.ROTATION_0:
			degree = 90;
			break;
		case Surface.ROTATION_90:
			degree = 0;
			break;
		case Surface.ROTATION_180:
			degree = 270;
			break;
		case Surface.ROTATION_270:
			degree = 180;
			break;
		}
		return degree;
	}

}
