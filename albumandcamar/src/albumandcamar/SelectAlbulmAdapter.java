package albumandcamar;

import com.example.albumandcamar.R;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.LoadedFrom;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import view.HKImageView;

public class SelectAlbulmAdapter extends ArrayAdapter<CategoryModle> {
	private ImageLoader loader;

	public SelectAlbulmAdapter(Context context, int resource, int textViewResourceId) {
		super(context, resource, textViewResourceId);
		// TODO Auto-generated constructor stub
		loader = ImageLoader.getInstance();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHodler viewHodler = null;
		if (convertView == null) {
			convertView = View.inflate(getContext(), R.layout.listview_item, null);
			viewHodler = new ViewHodler(convertView);
			convertView.setTag(viewHodler);
		} else {
			viewHodler = (ViewHodler) convertView.getTag();
		}
		CategoryModle modle = getItem(position);
		String titleName = modle.getTitle();
		String imageUrl = modle.getList().get(0).getImageUri();
		int count = modle.getList().size();
		viewHodler.getPicCountTextView()
				.setText(getContext().getResources().getString(R.string.select_album_pic_list_count, count));
		if (imageUrl != null && !"".equals(imageUrl)) {

			loader.displayImage(imageUrl, viewHodler.getImageView());
		}
		if (titleName != null && !"".equals(titleName)) {
			viewHodler.getTitle().setText(titleName);
		}
		return convertView;
	}

	// 优化列表
	class ViewHodler {
		private TextView title;
		private TextView picCountTextView;
		private HKImageView imageView;
		private ImageView arrawback;
		private View convertView;

		public ViewHodler(View convertView) {
			this.convertView = convertView;

		}

		public TextView getTitle() {
			if (title == null) {
				title = (TextView) convertView.findViewById(R.id.textView1);
			}
			return title;
		}

		public TextView getPicCountTextView() {
			if (picCountTextView == null) {
				picCountTextView = (TextView) convertView.findViewById(R.id.textView2);
			}
			return picCountTextView;
		}

		public HKImageView getImageView() {
			if (imageView == null) {
				imageView = (HKImageView) convertView.findViewById(R.id.imageView1);
			}
			return imageView;
		}

		public ImageView getArrawback() {
			if (arrawback == null) {
				arrawback = (ImageView) convertView.findViewById(R.id.imageView2);
			}
			return arrawback;
		}

	}

}
