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

/**
 * Provides factory methods to create intents to share stuff
 *
 * @author Vincent Prat @ MarvinLabs
 */
public class ShareIntents {

    /**
     * Creates a chooser to share some data.
     *
     * @param subject            The subject to share (might be discarded, for instance if the user picks an SMS app)
     * @param message            The message to share
     * @param chooserDialogTitle The title for the chooser dialog
     * @return the intent
     */
    public static Intent newShareTextIntent(String subject, String message, String chooserDialogTitle) {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_TEXT, message);
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
        shareIntent.setType(MIME_TYPE_TEXT);
        return Intent.createChooser(shareIntent, chooserDialogTitle);
    }

    private static final String MIME_TYPE_TEXT = "text/*";
}
