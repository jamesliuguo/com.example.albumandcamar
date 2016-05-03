package albumandcamar;

import java.security.KeyStore.PrivateKeyEntry;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Set;

import com.example.albumandcamar.R;

import albumandcamar.AlbumaAsycTask.AlbumDataCallBack;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class SelectAblumActivity extends Activity {
	private ListView listView;
	private SelectAlbulmAdapter selectAlbulmAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listview_layout);
		setUpView();
		addOnCliclLisener();
		getLocalPictures();

	}

	private void addOnCliclLisener() {
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Intent intent = new Intent(getApplicationContext(), GrideViewActivity.class);
				intent.putExtra(getResources().getString(R.string.select_album_pic_list),
						selectAlbulmAdapter.getItem(position).getList());
				intent.putExtra(getResources().getString(R.string.select_album_pic_list_title),
						selectAlbulmAdapter.getItem(position).getTitle());
				startActivity(intent);

			}
		});

	}

	private void setUpView() {
		getActionBar().setDisplayShowHomeEnabled(false);
		listView = (ListView) findViewById(R.id.select_abulm_listview);
		selectAlbulmAdapter = new SelectAlbulmAdapter(getApplicationContext(), 0, 0);

	}

	// 数据分类
	private void doCategoryAblum(HashMap<String, ArrayList<PictureModle>> result) {

		ArrayList<CategoryModle> cmlist = new ArrayList<CategoryModle>();
		if (result != null) {
			Set<String> keys = result.keySet();
			for (String s : keys) {
				CategoryModle categoryModle = new CategoryModle();
				categoryModle.setTitle(s);
				categoryModle.setList(result.get(s));
				cmlist.add(categoryModle);

			}
		}
		sortPicList(cmlist);
		selectAlbulmAdapter.addAll(cmlist);
		setUpListView();
	}

	// 图片列表按图片多少排序
	private void sortPicList(ArrayList<CategoryModle> cmlist) {
		Collections.sort(cmlist, new Comparator<CategoryModle>() {

			@Override
			public int compare(CategoryModle lhs, CategoryModle rhs) {
				return rhs.getList().size() - lhs.getList().size();
			}
		});

	}

	private void setUpListView() {
		listView.setAdapter(selectAlbulmAdapter);
	}

	private void getLocalPictures() {
		AlbumaAsycTask asycTask = new AlbumaAsycTask(getApplicationContext());
		asycTask.setBack(new AlbumDataCallBack() {

			@Override
			public void testString(String str) {
				// TODO Auto-generated method stub

			}

			@Override
			public void preExecute() {

			}

			@Override
			public void fail(String smg) {

			}

			@Override
			public void success(HashMap<String, ArrayList<PictureModle>> result) {
				// TODO Auto-generated method stub

				doCategoryAblum(result);

			}

		});
		asycTask.execute("");
	}

}
