package albumandcamar;

import java.io.File;
import java.net.URI;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.provider.MediaStore.Images.Media;
import android.util.Log;

public class AlbumaAsycTask extends AsyncTask<String, String, HashMap<String, ArrayList<PictureModle>>> {
	private Context mContext;
	private StringBuffer stringBuffer = null;
	private AlbumDataCallBack back;

	public AlbumDataCallBack getBack() {
		return back;
	}

	public void setBack(AlbumDataCallBack back) {
		this.back = back;
	}

	public AlbumaAsycTask(Context mContext) {
		this.mContext = mContext;
		stringBuffer = new StringBuffer();
	}

	@Override
	protected HashMap<String, ArrayList<PictureModle>> doInBackground(String... params) {
		// TODO Auto-generated method stub
		return getImageFiles(mContext);
	}
	
	@Override
	protected void onPostExecute(HashMap<String, ArrayList<PictureModle>> result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		back.success(result);
	}

	public HashMap<String, ArrayList<PictureModle>> getImageFiles(Context context) {
		ContentResolver resolver = context.getContentResolver();
		ArrayList<PictureModle> modles = new ArrayList<PictureModle>();
		HashMap<String, ArrayList<PictureModle>> pics = new HashMap<String, ArrayList<PictureModle>>();
		String[] IMAGE_COLUMN = { MediaStore.Images.Media.DATA, MediaStore.Images.Media.SIZE };
		String[] AUDIO_COLUMN = { MediaStore.Audio.Media.DATA, MediaStore.Audio.Media.SIZE,
				MediaStore.Audio.Media.DURATION, MediaStore.Audio.Media._ID, MediaStore.Audio.Media.ALBUM_ID };
		Cursor cursor = resolver.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, IMAGE_COLUMN, null, null, null);
		String tp = "image}";
		if (cursor != null) {
			if (cursor.moveToFirst()) {
				String path = cursor.getString(cursor.getColumnIndex(IMAGE_COLUMN[0]));
				String size = cursor.getString(cursor.getColumnIndex(IMAGE_COLUMN[1]));
				while (cursor.moveToNext()) {
					path = cursor.getString(cursor.getColumnIndex(AUDIO_COLUMN[0]));
					size = cursor.getString(cursor.getColumnIndex(AUDIO_COLUMN[1]));
					stringBuffer.append(path + "\n");
					PictureModle modle = new PictureModle();
					modle.setImageUri("file://" + path);
					modles.add(modle);
					Log.i("stringBufferpic", "" + path);
					File file = new File(path);
					;
					String nameStr = file.getParent();
					File  pFile=new File(nameStr);
					String  name=pFile.getName();
                     //将图片分门别类
					if (pics.containsKey(name)) {
						ArrayList<PictureModle> temp = pics.get(name);
						temp.add(modle);
					} else {
						ArrayList<PictureModle> tempArrayList = new ArrayList<PictureModle>();
						tempArrayList.add(modle);
						pics.put(name, tempArrayList);
					}
				}
			}
		}
		Log.i("picshasmap", "" + pics.toString());
		return pics;
	}
	public interface AlbumDataCallBack {
		public void fail(String smg);
		public void preExecute();
		public void success(HashMap<String, ArrayList<PictureModle>> result);
		public void testString(String str);
	}

}
