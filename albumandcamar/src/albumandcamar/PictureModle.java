package albumandcamar;

import java.io.Serializable;

import android.net.Uri;

public class PictureModle  implements Serializable{
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private String   imageUri;

public String getImageUri() {
	return imageUri;
}

public void setImageUri(String imageUri) {
	this.imageUri = imageUri;
}




}
