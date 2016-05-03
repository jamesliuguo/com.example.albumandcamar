package album;

import java.util.ArrayList;

import com.example.albumandcamar.R;

import album.AlbumaAsycTask.AlbumDataCallBack;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import camar.CamraActivity;

public class MainActivity extends Activity implements OnClickListener {
	private TextView textview;
	private Button picsButton, camraButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mian_layout);
		setUpView();
		addOnCliclLisnner();

	}

	private void testArthmotic() {

	}

	private void setUpView() {
		// TODO Auto-generated method stub
		textview = (TextView) findViewById(R.id.textView1);
		picsButton = (Button) findViewById(R.id.album_button);
		camraButton = (Button) findViewById(R.id.camra_button);

	}

	private void addOnCliclLisnner() {
		picsButton.setOnClickListener(this);
		camraButton.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		Intent intent = new Intent();
		switch (v.getId()) {
		case R.id.album_button:
			// 相册
			intent.setClass(MainActivity.this, SelectAblumActivity.class);
			startActivity(intent);

			break;
		case R.id.camra_button:
			//拍照
			intent.setClass(getApplicationContext(), CamraActivity.class);
			startActivity(intent);
			break;

		default:
			break;
		}

	}

}
