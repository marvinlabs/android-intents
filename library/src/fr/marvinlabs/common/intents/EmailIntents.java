package fr.marvinlabs.common.intents;

import android.content.Intent;
import android.net.Uri;

/**
 * Provides factory methods to create intents to send emails
 * 
 * @author Vincent Prat @ MarvinLabs
 */
public class EmailIntents {

	/**
	 * Create an intent to send an email to a single recipient
	 * 
	 * @param address
	 *            The recipient address (or null if not specified)
	 * @param subject
	 *            The subject of the email (or null if not specified)
	 * @param body
	 *            The body of the email (or null if not specified)
	 * 
	 * @return the intent
	 */
	public static Intent newEmailIntent(String address, String subject, String body) {
		return newEmailIntent(address, subject, body, null);
	}

	/**
	 * Create an intent to send an email with an attachment to a single recipient
	 * 
	 * @param address
	 *            The recipient address (or null if not specified)
	 * @param subject
	 *            The subject of the email (or null if not specified)
	 * @param body
	 *            The body of the email (or null if not specified)
	 * @param attachment
	 *            The URI of a file to attach to the email. Note that the URI must point to a location the email
	 *            application is allowed to read and has permissions to access.
	 * 
	 * @return the intent
	 */
	public static Intent newEmailIntent(String address, String subject, String body, Uri attachment) {
		return newEmailIntent(address == null ? null : new String[] { address }, subject, body, attachment);
	}

	/**
	 * Create an intent to send an email with an attachment
	 * 
	 * @param addresses
	 *            The recipients addresses (or null if not specified)
	 * @param subject
	 *            The subject of the email (or null if not specified)
	 * @param body
	 *            The body of the email (or null if not specified)
	 * @param attachment
	 *            The URI of a file to attach to the email. Note that the URI must point to a location the email
	 *            application is allowed to read and has permissions to access.
	 * 
	 * @return the intent
	 */
	public static Intent newEmailIntent(String[] addresses, String subject, String body, Uri attachment) {
		Intent intent = new Intent(Intent.ACTION_SEND);
		if (addresses != null) intent.putExtra(Intent.EXTRA_EMAIL, addresses);
		if (body != null) intent.putExtra(Intent.EXTRA_TEXT, body);
		if (subject != null) intent.putExtra(Intent.EXTRA_SUBJECT, subject);
		if (attachment != null) intent.putExtra(Intent.EXTRA_STREAM, attachment);
		intent.setType(MIME_TYPE_EMAIL);

		return intent;
	}

	private static final String MIME_TYPE_EMAIL = "message/rfc822";
}
