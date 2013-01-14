package fr.marvinlabs.common.intents;

import java.util.Locale;

import android.content.Intent;
import android.net.Uri;

/**
 * Provides factory methods to create intents to work with geographical data (search locations for instance)
 * 
 * @author Vincent Prat @ MarvinLabs
 */
public class GeoIntents {

	/**
	 * Intent that should allow opening a map showing the given address (if it exists)
	 * 
	 * @param address
	 *            The address to search
	 * @param placeTitle
	 *            The title to show on the marker
	 * 
	 * @return the intent
	 */
	public static Intent newMapsIntent(String address, String placeTitle) {
		StringBuilder sb = new StringBuilder();
		sb.append("geo:0,0?q=");

		String addressEncoded = Uri.encode(address);
		sb.append(addressEncoded);
		
		// pass text for the info window
		String titleEncoded = Uri.encode("(" + placeTitle + ")");
		sb.append(titleEncoded);
		
		// set locale; probably not required for the maps app?
		sb.append("&hl=" + Locale.getDefault().getLanguage());

		return new Intent(Intent.ACTION_VIEW, Uri.parse(sb.toString()));
	}
}
