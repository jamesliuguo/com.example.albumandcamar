package album;

import java.util.ArrayList;

import com.example.albumandcamar.R;
import com.nostra13.universalimageloader.core.ImageLoader;

import android.R.bool;
import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.ValueAnimator;
import android.content.Context;
import android.os.CountDownTimer;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

public class PicsViewPagerAdapter extends PagerAdapter {
	private ArrayList<PictureModle> pics = new ArrayList<PictureModle>();
	private ArrayList<View> views = new ArrayList<View>();
	private Context mContext;
	private ImageLoader imageLoader;
	boolean isClicked = Boolean.FALSE;
	private OnItemOnCliclkLisener onItemOnCliclkLisener;

	public OnItemOnCliclkLisener getOnItemOnCliclkLisener() {
		return onItemOnCliclkLisener;
	}

	public void setOnItemOnCliclkLisener(OnItemOnCliclkLisener onItemOnCliclkLisener) {
		this.onItemOnCliclkLisener = onItemOnCliclkLisener;
	}

	public ArrayList<PictureModle> getPics() {
		return pics;
	}

	public void setPics(ArrayList<PictureModle> pics) {
		this.pics = pics;
	}

	public PicsViewPagerAdapter(Context mContext) {
		// TODO Auto-generated constructor stub
		this.mContext = mContext;
		imageLoader = ImageLoader.getInstance();
	}

	// 获取要滑动的控件的数量，在这里我们以滑动的广告栏为例，那么这里就应该是展示的广告图片的ImageView数量
	// 来判断显示的是否是同一张图片，这里我们将两个参数相比较返回即可
	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0 == arg1;
	}

	// PagerAdapter只缓存三张要显示的图片，如果滑动的图片超出了缓存的范围，就会调用这个方法，将图片销毁
	@Override
	public void destroyItem(ViewGroup view, int position, Object object) {
		// view.removeView(views.get(position));
	}

	// 当要显示的图片可以进行缓存的时候，会调用这个方法进行显示图片的初始化，我们将要显示的ImageView加入到ViewGroup中，然后作为返回值返回即可
	@Override
	public Object instantiateItem(ViewGroup view, final int position) {
		final View chidrenView = View.inflate(mContext, R.layout.show_pager_adapter_item, null);
		ImageView imageView = (ImageView) chidrenView.findViewById(R.id.pager_image_view);
		view.addView(chidrenView);
		views.add(chidrenView);
		String url = pics.get(position).getImageUri();
		imageLoader.displayImage(url, imageView);

		chidrenView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				showEditeImageDialog(chidrenView);
			}
		});
		return chidrenView;
	}

	// 编辑框显示show_bianji_layout
	private void showEditeImageDialog(final View chidrenView) {
		final View view = chidrenView.findViewById(R.id.show_bianji_layout);
		view.setVisibility(View.VISIBLE);
		chidrenView.setTag(true);
		showChildrenViews(view);
		CountDownTimer downTimer = new CountDownTimer(3000, 1000) {

			@Override
			public void onTick(long millisUntilFinished) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onFinish() {
				// TODO Auto-generated method stub
				
				dissmissChildrenViews(view);
				// view.setVisibility(View.INVISIBLE);

			}
		};
		downTimer.start();

	}

	// 退出
	private void dissmissChildrenViews(final View view) {
		LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.show_bianji);
		int count = linearLayout.getChildCount();
		for (int i = 0; i < count; i++) {
			linearLayout.getChildAt(i).animate().translationX(0).translationY(100).alpha(0f).setStartDelay(50 * i + 50)
					.start();

		}
		//

	}

	// 进入
	private void showChildrenViews(View view) {

		LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.show_bianji);
		int count = linearLayout.getChildCount();
		for (int i = 0; i < count; i++) {
			linearLayout.getChildAt(i).animate().translationX(0).
			translationY(0).alpha(1f).setStartDelay(50 * i + 50)
					.start();
			;
		}

	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return pics.size();
	}

	@Override
	public int getItemPosition(Object object) {
		// TODO Auto-generated method stub
		return super.getItemPosition(object);
	}

	@Override
	public void destroyItem(View container, int position, Object object) {
		// TODO Auto-generated method stub
		super.destroyItem(container, position, object);

	}

	public interface OnItemOnCliclkLisener {
		public void itemOnClick(int pos, PictureModle pictureModle);
	}

}
