/*
Copyright 2013 Vincent Mimoun-Prat

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/

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
