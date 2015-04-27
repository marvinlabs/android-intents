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

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;

import java.io.File;

/**
 * Provides factory methods to create intents to view / open / ... medias
 *
 * @author Vincent Prat @ MarvinLabs
 */
public class MediaIntents {

    public static final String AUDIO_TYPE = "audio/*";
    public static final String VIDEO_TYPE = "video/*";
    public static final String IMAGE_TYPE = "image/*";

    /**
     * Open the media player to play the given media
     *
     * @param file The file path of the media to play.
     * @return the intent
     */
    public static Intent newPlayAudioFileIntent(File file) {
        return newPlayMediaFileIntent(file, AUDIO_TYPE);
    }

    /**
     * Open the media player to play the given media
     *
     * @param path The file path of the media to play.
     * @return the intent
     */
    public static Intent newPlayAudioFileIntent(String path) {
        return newPlayMediaFileIntent(path, AUDIO_TYPE);
    }

    /**
     * Open the media player to play the given media
     *
     * @param url The URL of the media to play.
     * @return the intent
     */
    public static Intent newPlayAudioIntent(String url) {
        return newPlayMediaIntent(url, AUDIO_TYPE);
    }

    /**
     * Open the media player to play the given media
     *
     * @param file The file path of the media to play.
     * @return the intent
     */
    public static Intent newPlayImageFileIntent(File file) {
        return newPlayMediaFileIntent(file, IMAGE_TYPE);
    }

    /**
     * Open the media player to play the given media
     *
     * @param path The file path of the media to play.
     * @return the intent
     */
    public static Intent newPlayImageFileIntent(String path) {
        return newPlayMediaFileIntent(path, IMAGE_TYPE);
    }

    /**
     * Open the media player to play the given media
     *
     * @param url The URL of the media to play.
     * @return the intent
     */
    public static Intent newPlayImageIntent(String url) {
        return newPlayMediaIntent(url, IMAGE_TYPE);
    }

    /**
     * Open the media player to play the given media
     *
     * @param file The file path of the media to play.
     * @return the intent
     */
    public static Intent newPlayVideoFileIntent(File file) {
        return newPlayMediaFileIntent(file, VIDEO_TYPE);
    }

    /**
     * Open the media player to play the given media
     *
     * @param path The file path of the media to play.
     * @return the intent
     */
    public static Intent newPlayVideoFileIntent(String path) {
        return newPlayMediaFileIntent(path, VIDEO_TYPE);
    }

    /**
     * Open the media player to play the given media
     *
     * @param url The URL of the media to play.
     * @return the intent
     */
    public static Intent newPlayVideoIntent(String url) {
        return newPlayMediaIntent(url, VIDEO_TYPE);
    }

    /**
     * Open a YouTube video. If the app is not installed, it opens it in the browser
     *
     * @param videoId The video ID
     *
     * @return the intent
     */
    public static Intent newPlayYouTubeVideoIntent(String videoId) {
        try {
            return new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + videoId));
        } catch (ActivityNotFoundException ex) {
            return new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/watch?v=" + videoId));
        }
    }

    /**
     * Open the media player to play the given media
     *
     * @param url  The URL of the media to play.
     * @param type The mime type
     * @return the intent
     */
    public static Intent newPlayMediaIntent(String url, String type) {
        return newPlayMediaIntent(Uri.parse(url), type);
    }

    /**
     * Open the media player to play the given media
     *
     * @param file The file path of the media to play.
     * @param type The mime type
     * @return the intent
     */
    public static Intent newPlayMediaFileIntent(File file, String type) {
        return newPlayMediaIntent(Uri.fromFile(file), type);
    }

    /**
     * Open the media player to play the given media
     *
     * @param path The file path of the media to play.
     * @param type The mime type
     * @return the intent
     */
    public static Intent newPlayMediaFileIntent(String path, String type) {
        return newPlayMediaIntent(Uri.fromFile(new File(path)), type);
    }

    /**
     * Open the media player to play the given media Uri
     *
     * @param uri  The Uri of the media to play.
     * @param type The mime type
     * @return the intent
     */
    public static Intent newPlayMediaIntent(Uri uri, String type) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(uri, type);
        return intent;
    }

    /**
     * Creates an intent that will launch a browser (most probably as other apps may handle specific URLs, e.g. YouTube)
     * to view the provided URL.
     *
     * @param url the URL to open
     * @return the intent
     */
    public static Intent newOpenWebBrowserIntent(String url) {
        if (!url.startsWith("https://") && !url.startsWith("http://")) {
            url = "http://" + url;
        }
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        return intent;
    }

    /**
     * Creates an intent that will launch the camera to take a picture that's saved to a temporary file so you can use
     * it directly without going through the gallery.
     *
     * @param tempFile the file that should be used to temporarily store the picture
     * @return the intent
     */
    public static Intent newTakePictureIntent(File tempFile) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(tempFile));
        return intent;
    }

    /**
     * Creates an intent that will launch the camera to take a picture that's saved to a temporary file so you can use
     * it directly without going through the gallery.
     *
     * @param tempFile the file that should be used to temporarily store the picture
     * @return the intent
     */
    public static Intent newTakePictureIntent(String tempFile) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(tempFile)));
        return intent;
    }

    /**
     * Creates an intent that will launch the phone's picture gallery to select a picture from it.
     *
     * @return the intent
     */
    public static Intent newSelectPictureIntent() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        return intent;
    }
}
