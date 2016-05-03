package album;

import com.example.albumandcamar.R;
import com.nostra13.universalimageloader.core.ImageLoader;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

public class PicturesAdapter extends ArrayAdapter<PictureModle> {
	private ImageLoader imageLoader;
	public PicturesAdapter(Context context, int resource) {
		super(context, resource);
		// TODO Auto-generated constructor stub
		imageLoader = ImageLoader.getInstance();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHodler hodler = null;
		if (convertView == null) {
			convertView = View.inflate(getContext(), R.layout.grideview_item, null);
			hodler = new ViewHodler(convertView);
			convertView.setTag(hodler);
		} else {
			hodler = (ViewHodler) convertView.getTag();
		}
		imageLoader.displayImage(getItem(position).getImageUri(), 
				hodler.getImageView());
		return convertView;

	}

	class ViewHodler {
		private ImageView imageView;
		private  View convertView;
		public ViewHodler(View convertView) {
			// TODO Auto-generated constructor stub
			this.convertView=convertView;
		}
		public ImageView getImageView() {
			if(imageView==null){
				imageView=(ImageView) convertView.findViewById(R.id.imageView1);
			}
			return imageView;
		}

		

	}

}
