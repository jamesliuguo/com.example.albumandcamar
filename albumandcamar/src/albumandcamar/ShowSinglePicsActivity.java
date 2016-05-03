package albumandcamar;

import java.util.ArrayList;

import com.example.albumandcamar.R;
import com.viewpagerindicator.CirclePageIndicator;

import albumandcamar.PicsViewPagerAdapter.OnItemOnCliclkLisener;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.PopupWindow;
import android.widget.Toast;
import android.view.Window;

public class ShowSinglePicsActivity extends Activity {
	private ViewPager showPicsViewPager;
	private PicsViewPagerAdapter pagerAdapter;
	private CirclePageIndicator indicator;
	private int pos;
	private ArrayList<PictureModle> pics;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.show_single_pic_layout);
		getIntentData();
		setUpView();
		// ceshi
		addOnChangeLisener();
	}

	private void getIntentData() {
		pics = (ArrayList<PictureModle>) getIntent()
				.getSerializableExtra(getResources().getString(R.string.select_album_pic_list));
		pos = getIntent().getIntExtra(getResources().getString(R.string.select_album_pic_list_pos), 0);

	}

	private void setUpView() {
		// getActionBar().setDisplayShowHomeEnabled(false);
		// getActionBar().setDisplayShowTitleEnabled(false);
		showPicsViewPager = (ViewPager) findViewById(R.id.vPager);
		pagerAdapter = new PicsViewPagerAdapter(getApplicationContext());
		pagerAdapter.setPics(pics);
		showPicsViewPager.setAdapter(pagerAdapter);
		indicator = (CirclePageIndicator) findViewById(R.id.indicator);
		// showPicsViewPager.setCurrentItem(pos);
		indicator.setViewPager(showPicsViewPager, pos);
		// indicator.setCurrentItem(pos);
		pagerAdapter.setOnItemOnCliclkLisener(new OnItemOnCliclkLisener() {

			@Override
			public void itemOnClick(int pos, PictureModle pictureModle) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), 
						"" + pictureModle.getImageUri(), 
						Toast.LENGTH_SHORT).show();
				;
				

			}
		});

	}
	
	
	
	

	private void addOnChangeLisener() {

	}

}
