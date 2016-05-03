package albumandcamar;

import java.io.Serializable;
import java.util.ArrayList;

import android.os.Parcel;
import android.os.Parcelable;

public class CategoryModle implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private  String  title;
	private  ArrayList<PictureModle>  list;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public ArrayList<PictureModle> getList() {
		return list;
	}
	public void setList(ArrayList<PictureModle> list) {
		this.list = list;
	}
	
	
	

}
