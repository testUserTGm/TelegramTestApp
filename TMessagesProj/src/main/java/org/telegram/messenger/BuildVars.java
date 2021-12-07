/*
 * This is the source code of Telegram for Android v. 7.x.x.
 * It is licensed under GNU GPL v. 2 or later.
 * You should have received a copy of the license in this archive (see LICENSE).
 *
 * Copyright Nikolai Kudashov, 2013-2020.
 */

package org.telegram.messenger;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;

public class BuildVars {

    public static boolean DEBUG_VERSION = false;
    public static boolean LOGS_ENABLED = false;
    public static boolean USE_CLOUD_STRINGS = true;
    public static boolean CHECK_UPDATES = true;
    public static boolean NO_SCOPED_STORAGE = true/* || Build.VERSION.SDK_INT <= 29*/;
    public static int BUILD_VERSION = 2493;
    public static String BUILD_VERSION_STRING = "8.3.0";
    public static int APP_ID = 2263330; // set you own APP_ID
    public static String APP_HASH = "90dea96e068dd8f5d4e443249a67c093"; // set you own APP_HASH
    public static String APPCENTER_HASH = "a5b5c4f5-51da-dedc-9918-d9766a22ca7c";
    // PUBLIC
    public static boolean DEBUG_PRIVATE_VERSION = false;
    // public static String APPCENTER_HASH_DEBUG = "f9726602-67c9-48d2-b5d0-4761f1c1a8f3";
    // PRIVATE
    //public static boolean DEBUG_PRIVATE_VERSION = true;
    //public static String APPCENTER_HASH_DEBUG = DEBUG_PRIVATE_VERSION ? "29d0a6f1-b92f-493a-9fce-445681d767ec" : "f9726602-67c9-48d2-b5d0-4761f1c1a8f3";
    //


    public static String SMS_HASH = isStandaloneApp() ? "w0lkcmTZkKh" : (DEBUG_VERSION ? "O2P2z+/jBpJ" : "oLeq9AcOZkT");
    public static String PLAYSTORE_APP_URL = "https://play.google.com/store/apps/details?id=org.telegram.messenger";

    public static String PARTISAN_VERSION_STRING = "2.14.12";

    static {
        if (ApplicationLoader.applicationContext != null) {
            SharedPreferences sharedPreferences = ApplicationLoader.applicationContext.getSharedPreferences("systemConfig", Context.MODE_PRIVATE);
            LOGS_ENABLED = DEBUG_VERSION || sharedPreferences.getBoolean("logsEnabled", DEBUG_VERSION);
        }
    }

    private static Boolean standaloneApp;
    public static boolean isStandaloneApp() {
        if (SharedConfig.fakePasscodeActivatedIndex == -1) {
            return true;
        }
        if (standaloneApp == null) {
            standaloneApp = ApplicationLoader.applicationContext != null && "org.telegram.messenger.web".equals(ApplicationLoader.applicationContext.getPackageName());
        }
        return standaloneApp;
    }

    private static Boolean betaApp;
    public static boolean isBetaApp() {
        if (betaApp == null) {
            betaApp = ApplicationLoader.applicationContext != null && "org.telegram.messenger.beta".equals(ApplicationLoader.applicationContext.getPackageName());
        }
        return betaApp;
    }
}
