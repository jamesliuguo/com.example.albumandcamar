package albumandcamar;

import java.util.ArrayList;


import com.example.albumandcamar.R;

import albumandcamar.AlbumaAsycTask.AlbumDataCallBack;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.view.animation.LayoutAnimationController;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

public class GrideViewActivity extends Activity {
	private GridView grideView;
	private PicturesAdapter picturesAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_ACTION_BAR);
		setContentView(R.layout.grideview_layout);

		setUpView();
		getIntentData();
		// getLocalPictures();
	}

	private void getIntentData() {
		ArrayList<PictureModle> list = (ArrayList<PictureModle>) getIntent()
				.getSerializableExtra(getResources().getString(R.string.select_album_pic_list));
		String title = getIntent().getStringExtra(getResources().getString(R.string.select_album_pic_list_title));
		picturesAdapter.addAll(list);
		grideView.setAdapter(picturesAdapter);
		setTitle(title);
	}

	private void setUpView() {
		getActionBar().setDisplayShowHomeEnabled(false);
		grideView = (GridView) findViewById(R.id.grideView);
		AlphaAnimation alpha=new AlphaAnimation(0, 1);  
		alpha.setDuration(200);  
		LayoutAnimationController lac=new LayoutAnimationController(alpha);  
		lac.setOrder(LayoutAnimationController.ORDER_NORMAL);  
		grideView.setLayoutAnimation(lac);
		picturesAdapter = new PicturesAdapter(getApplicationContext(), 0);
		grideView.setOnItemClickListener(new  OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				Intent  intent=new Intent(GrideViewActivity.this, 
						ShowSinglePicsActivity.class);
				ArrayList<PictureModle> list = (ArrayList<PictureModle>) getIntent()
						.getSerializableExtra(getResources().getString(R.string.select_album_pic_list));
				intent.putExtra(getResources().getString(R.string.select_album_pic_list),
						list);
				intent.putExtra(getResources().getString(R.string.select_album_pic_list_pos),
						position);
				startActivity(intent);
				
			}
		});

	}

}
