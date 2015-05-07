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

package com.marvinlabs.intents;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Contacts;
import android.provider.ContactsContract;
import android.provider.Telephony;
import android.text.TextUtils;

/**
 * Provides factory methods to create intents to send SMS, MMS and call phone numbers
 *
 * @author Vincent Prat @ MarvinLabs
 * @todo MMS intents
 */
public class PhoneIntents {

    /**
     * Creates an intent that will allow to send an SMS without specifying the phone number
     *
     * @return the intent
     */
    public static Intent newEmptySmsIntent(Context context) {
        return newSmsIntent(context, null, (String[]) null);
    }

    /**
     * Creates an intent that will allow to send an SMS without specifying the phone number
     *
     * @param phoneNumber The phone number to send the SMS to
     * @return the intent
     */
    public static Intent newEmptySmsIntent(Context context, String phoneNumber) {
        return newSmsIntent(context, null, new String[]{phoneNumber});
    }

    /**
     * Creates an intent that will allow to send an SMS without specifying the phone number
     *
     * @param phoneNumbers The phone numbers to send the SMS to
     * @return the intent
     */
    public static Intent newEmptySmsIntent(Context context, String[] phoneNumbers) {
        return newSmsIntent(context, null, phoneNumbers);
    }

    /**
     * Creates an intent that will allow to send an SMS without specifying the phone number
     *
     * @param body The text to send
     * @return the intent
     */
    public static Intent newSmsIntent(Context context, String body) {
        return newSmsIntent(context, body, (String[]) null);
    }

    /**
     * Creates an intent that will allow to send an SMS without specifying the phone number
     *
     * @param body The text to send
     * @param phoneNumber The phone number to send the SMS to
     * @return the intent
     */
    public static Intent newSmsIntent(Context context, String body, String phoneNumber) {
        return newSmsIntent(context, body, new String[]{phoneNumber});
    }


    /**
     * Creates an intent that will allow to send an SMS to a phone number
     *
     * @param body         The text to send
     * @param phoneNumbers The phone numbers to send the SMS to (or null if you don't want to specify it)
     * @return the intent
     */
    public static Intent newSmsIntent(Context context, String body, String[] phoneNumbers) {
        Uri smsUri;
        if (phoneNumbers == null || phoneNumbers.length==0) {
            smsUri = Uri.parse("smsto:");
        } else {
            smsUri = Uri.parse("smsto:" + Uri.encode(TextUtils.join(",", phoneNumbers)));
        }

        Intent intent;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            intent = new Intent(Intent.ACTION_SENDTO, smsUri);
            intent.setPackage(Telephony.Sms.getDefaultSmsPackage(context));
        } else {
            intent = new Intent(Intent.ACTION_VIEW, smsUri);
        }

        if (body!=null) {
            intent.putExtra("sms_body", body);
        }

        return intent;
    }

    /**
     * Creates an intent that will open the phone app and enter the given number. Unlike
     * {@link #newCallNumberIntent(String)}, this does not actually dispatch the call, so it gives the user a chance to
     * review and edit the number.
     *
     * @param phoneNumber the number to dial
     * @return the intent
     */
    public static Intent newDialNumberIntent(String phoneNumber) {
        final Intent intent;
        if (phoneNumber == null || phoneNumber.trim().length() <= 0) {
            intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"));
        } else {
            intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNumber.replace(" ", "")));
        }
        return intent;
    }

    /**
     * Creates an intent that will immediately dispatch a call to the given number. NOTE that unlike
     * {@link #newDialNumberIntent(String)}, this intent requires the {@link android.Manifest.permission#CALL_PHONE}
     * permission to be set.
     *
     * @param phoneNumber the number to call
     * @return the intent
     */
    public static Intent newCallNumberIntent(String phoneNumber) {
        final Intent intent;
        if (phoneNumber == null || phoneNumber.trim().length() <= 0) {
            intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"));
        } else {
            intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneNumber.replace(" ", "")));
        }
        return intent;
    }

    /**
     * Pick contact from phone book
     */
    public static Intent newPickContactIntent() {
        return newPickContactIntent(null);
    }

    /**
     * Pick contact from phone book
     * <p/>
     * Examples:
     * <p/>
     * <code><pre>
     *     // Select only from users with emails
     *     IntentUtils.pickContact(ContactsContract.CommonDataKinds.Email.CONTENT_TYPE);
     * <p/>
     *     // Select only from users with phone numbers on pre Eclair devices
     *     IntentUtils.pickContact(Contacts.Phones.CONTENT_TYPE);
     * <p/>
     *     // Select only from users with phone numbers on devices with Eclair and higher
     *     IntentUtils.pickContact(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);
     * </pre></code>
     *
     * @param scope You can restrict selection by passing required content type.
     */
    @SuppressWarnings("deprecation")
    public static Intent newPickContactIntent(String scope) {
        Intent intent;
        if (isContacts2ApiSupported()) {
            intent = new Intent(Intent.ACTION_PICK, Uri.parse("content://com.android.contacts/contacts"));
        } else {
            intent = new Intent(Intent.ACTION_PICK, Contacts.People.CONTENT_URI);
        }

        if (!TextUtils.isEmpty(scope)) {
            intent.setType(scope);
        }

        return intent;
    }

    /**
     * Pick contact only from contacts with telephone numbers
     */
    @SuppressWarnings("deprecation")
    public static Intent newPickContactWithPhoneIntent() {
        Intent intent;
        if (isContacts2ApiSupported()) {
            intent = newPickContactIntent(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);
        } else {
            // pre Eclair, use old contacts API
            intent = newPickContactIntent(Contacts.Phones.CONTENT_TYPE);
        }
        return intent;
    }

    /**
     * Does the current device support the Post eclair contacts API?
     */
    private static boolean isContacts2ApiSupported() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.ECLAIR;
    }
}
