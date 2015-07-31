package com.mwongela.gcmdemo.utility;

import android.content.Context;
import android.content.Intent;

/**
 * Created by Moses Mwongela
 * on 7/16/15
 * moses1889@gmail.com
 *
 * Reference: http://www.androidhive.info/2012/10/android-push-notifications-using-google-cloud-messaging-gcm-php-and-mysql/
 */

public final class CommonUtilities {
	
	// give your server registration url here
    public static final String SERVER_URL = "http://psv.net16.net/gcm/register.php";

    // Google project id
    public static final String SENDER_ID = "794333997054";

    /**
     * Tag used on log messages.
     */
    public static final String TAG = "Mwongela's GCM Demo";

    public static final String EXTRA_MESSAGE = "message";

}
