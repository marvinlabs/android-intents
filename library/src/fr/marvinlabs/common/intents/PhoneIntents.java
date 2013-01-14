package fr.marvinlabs.common.intents;

import android.content.Intent;
import android.net.Uri;

/**
 * Provides factory methods to create intents to send SMS, MMS and call phone numbers
 * 
 * @author Vincent Prat @ MarvinLabs
 * 
 * @todo MMS intents
 */
public class PhoneIntents {

	/**
	 * Creates an intent that will allow to send an SMS without specifying the phone number
	 * 
	 * @param body
	 *            The text to send
	 * 
	 * @return the intent
	 */
	public static Intent newSmsIntent(String body) {
		return newSmsIntent(null, body);
	}

	/**
	 * Creates an intent that will allow to send an SMS to a phone number
	 * 
	 * @param phoneNumber
	 *            The phone number to send the SMS to (or null if you don't want to specify it)
	 * @param body
	 *            The text to send
	 * 
	 * @return the intent
	 */
	public static Intent newSmsIntent(String phoneNumber, String body) {
		Intent sendIntent = phoneNumber == null || phoneNumber.trim().length() <= 0 ? new Intent(Intent.ACTION_VIEW)
				: new Intent(Intent.ACTION_VIEW, Uri.parse("sms:" + phoneNumber));
		sendIntent.putExtra("sms_body", body);
		sendIntent.setType("vnd.android-dir/mms-sms");
		return sendIntent;
	}

	/**
	 * Creates an intent that will open the phone app and enter the given number. Unlike
	 * {@link #newCallNumberIntent(String)}, this does not actually dispatch the call, so it gives the user a chance to
	 * review and edit the number.
	 * 
	 * @param phoneNumber
	 *            the number to dial
	 * @return the intent
	 */
	public static Intent newDialNumberIntent(String phoneNumber) {
		return new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNumber.replace(" ", "")));
	}

	/**
	 * Creates an intent that will immediately dispatch a call to the given number. NOTE that unlike
	 * {@link #newDialNumberIntent(String)}, this intent requires the {@link android.Manifest.permission#CALL_PHONE}
	 * permission to be set.
	 * 
	 * @param phoneNumber
	 *            the number to call
	 * @return the intent
	 */
	public static Intent newCallNumberIntent(String phoneNumber) {
		return new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneNumber.replace(" ", "")));
	}
}
