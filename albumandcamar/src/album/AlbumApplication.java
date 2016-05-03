package album;

import com.example.albumandcamar.R;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.disc.naming.HashCodeFileNameGenerator;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;

import android.app.Application;

public class AlbumApplication extends Application {
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		inniteImageLoader();

	}

	private void inniteImageLoader() {

		DisplayImageOptions options = new DisplayImageOptions.Builder().showStubImage(R.drawable.ic_launcher) 
				.showImageForEmptyUri(R.drawable.ic_launcher) 
				.showImageOnFail(R.drawable.ic_launcher) 
				.cacheInMemory(true) 
				.cacheOnDisc(true) 
				.displayer(new RoundedBitmapDisplayer(20)) 
				.build();

		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext())
				.threadPriority(Thread.NORM_PRIORITY - 2)
				.threadPoolSize(3)
				.denyCacheImageMultipleSizesInMemory()
				.tasksProcessingOrder(QueueProcessingType.LIFO)
				.memoryCache(new WeakMemoryCache())
				.memoryCacheSizePercentage(60)
				.diskCacheFileNameGenerator(new HashCodeFileNameGenerator())
				.diskCacheFileNameGenerator(new Md5FileNameGenerator())
				.diskCacheSize(50 * 1024 * 1024) 
				.diskCacheFileCount(100) 
				.denyCacheImageMultipleSizesInMemory()
				.imageDownloader(new BaseImageDownloader(getApplicationContext(), 5 * 1000, 30 * 1000))
				.memoryCacheExtraOptions(480, 800)
//				.defaultDisplayImageOptions(options)
				.writeDebugLogs() 
				.build();

		ImageLoader.getInstance().init(config);

	}

	public static DisplayImageOptions setUpOption() {
		DisplayImageOptions options = new DisplayImageOptions.Builder().showStubImage(R.drawable.ic_pic) 
				.showImageForEmptyUri(R.drawable.ic_pic) 
				.showImageOnFail(R.drawable.ic_pic) 
				.cacheInMemory(true) 
				.cacheOnDisc(true) 
				.displayer(new RoundedBitmapDisplayer(0)) 
				.build();

		return options;
	}

}
