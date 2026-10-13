package com.example.guo.startup

/**
 * Toggle individual startup anti-pattern demos.
 * Flip a flag to `false` to skip that case on the next cold start.
 */
object StartupDemoConfig {
    /** Main-thread SharedPreferences.commit() + file write. */
    const val ENABLE_MAIN_THREAD_DISK_IO = true

    /** Simulated third-party SDK that blocks the main thread during init. */
    const val ENABLE_BLOCKING_SDK_INIT = true

    /** Extra CPU / parsing work before first frame (Application + first Activity). */
    const val ENABLE_PRE_FIRST_FRAME_WORK = true

    /**
     * ContentProvider early init (runs before Application.onCreate).
     * Disable requires a clean install or uninstall if you also remove the provider
     * from the debug manifest; the class still no-ops when this is false.
     */
    const val ENABLE_EARLY_CONTENT_PROVIDER = true

    /** Artificial sleeps / payload sizes — keep short enough to avoid soft ANRs in casual demos. */
    const val BLOCKING_SDK_SLEEP_MS = 250L
    const val DISK_IO_PREF_WRITES = 40
    const val DISK_IO_FILE_BYTES = 128 * 1024
    const val PRE_FRAME_HASH_ROUNDS = 8_000
}
