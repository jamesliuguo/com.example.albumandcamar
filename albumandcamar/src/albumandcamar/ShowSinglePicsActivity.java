package albumandcamar;

import java.util.ArrayList;

import com.example.albumandcamar.R;
import com.viewpagerindicator.CirclePageIndicator;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;

public class ShowSinglePicsActivity extends Activity {
	private  ViewPager  showPicsViewPager;
	private  PicsViewPagerAdapter  pagerAdapter;
	private  CirclePageIndicator indicator;
	private  int  pos;
	private  ArrayList<PictureModle>  pics;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.show_single_pic_layout);
		getIntentData();
		setUpView();
	}

	private void getIntentData() {
		pics=(ArrayList<PictureModle>) getIntent().
				getSerializableExtra(getResources().getString(R.string.select_album_pic_list));
		pos=getIntent().getIntExtra(getResources().getString(R.string.select_album_pic_list_pos), 
				0);
				
	}

	private void setUpView() {
		getActionBar().setDisplayShowHomeEnabled(false);
		getActionBar().setDisplayShowTitleEnabled(false);
		showPicsViewPager=(ViewPager) findViewById(R.id.vPager);
		pagerAdapter=new PicsViewPagerAdapter(getApplicationContext());
		pagerAdapter.setPics(pics);
		showPicsViewPager.setAdapter(pagerAdapter);
		indicator=(CirclePageIndicator) findViewById(R.id.indicator);
//		showPicsViewPager.setCurrentItem(pos);
		indicator.setViewPager(showPicsViewPager);
		indicator.setCurrentItem(pos);
		
	}

}
