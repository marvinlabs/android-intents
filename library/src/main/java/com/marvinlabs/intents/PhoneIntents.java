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

import android.content.Intent;
import android.net.Uri;

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
     * @param body The text to send
     * @return the intent
     */
    public static Intent newSmsIntent(String body) {
        return newSmsIntent(null, body);
    }

    /**
     * Creates an intent that will allow to send an SMS to a phone number
     *
     * @param phoneNumber The phone number to send the SMS to (or null if you don't want to specify it)
     * @param body        The text to send
     * @return the intent
     */
    public static Intent newSmsIntent(String phoneNumber, String body) {
        final Intent intent;
        if (phoneNumber == null || phoneNumber.trim().length() <= 0) {
            intent = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:"));
        } else {
            intent = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:" + phoneNumber));
        }
        intent.putExtra("sms_body", body);
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
}
