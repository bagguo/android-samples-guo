package com.example.guo.startup

import android.content.ContentProvider
import android.content.ContentValues
import android.database.Cursor
import android.net.Uri
import android.os.SystemClock

/**
 * Case 4: ContentProvider-style early init (runs before Application.onCreate).
 * Declared only in the debug manifest so release never loads it.
 */
class StartupDemoContentProvider : ContentProvider() {

    override fun onCreate(): Boolean {
        if (!StartupDemoConfig.ENABLE_EARLY_CONTENT_PROVIDER) {
            StartupTiming.log("SKIP EarlyContentProvider (still instantiated; work disabled)")
            return true
        }

        val startedAt = SystemClock.elapsedRealtime()
        StartupTiming.measure("EarlyContentProvider.onCreate") {
            // Typical anti-pattern: SDK static init / disk read from a provider.
            val ctx = context
            if (ctx != null) {
                val prefs = ctx.getSharedPreferences("startup_demo_provider", 0)
                prefs.edit().putLong("provider_boot_at", startedAt).commit()
            }
            Thread.sleep(80)
            StartupTiming.log(
                "EarlyContentProvider ran BEFORE Application.onCreate " +
                    "(elapsedSinceBootApprox=${SystemClock.elapsedRealtime()}ms uptime)"
            )
        }
        return true
    }

    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? = null

    override fun getType(uri: Uri): String? = null

    override fun insert(uri: Uri, values: ContentValues?): Uri? = null

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int = 0

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int = 0
}
