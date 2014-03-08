
package com.mikeladze.musicdiscovery.util;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;

/**
 * The utilities for intents.
 * 
 * @author Tim Mikeladze
 */
public class IntentUtils {
	
	/**
	 * Checks if given intent is available on the system.
	 * 
	 * @param context the context
	 * @param intent the intent
	 * @return true, if is available
	 */
	public static boolean isAvailable(Context context, Intent intent) {
		PackageManager mgr = context.getPackageManager();
		List<ResolveInfo> list = mgr.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
		return list.size() > 0;
	}
}
