package albumandcamar;

import java.util.ArrayList;

import com.example.albumandcamar.R;

import albumandcamar.AlbumaAsycTask.AlbumDataCallBack;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
	private TextView textview;
	private Button picsButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mian_layout);
		setUpView();
		addOnCliclLisnner();

	}
	
	private  void  testArthmotic(){
		
		
	}

	private void setUpView() {
		// TODO Auto-generated method stub
		textview = (TextView) findViewById(R.id.textView1);
		picsButton = (Button) findViewById(R.id.album_button);

	}

	private void addOnCliclLisnner() {
		picsButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, SelectAblumActivity.class);
				startActivity(intent);

			}
		});
	}

}
