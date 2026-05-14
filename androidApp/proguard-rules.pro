# VITO ProGuard Rules

# Keep serialization classes
-keep class com.vito.app.data.model.** { *; }
-keepattributes *Annotation*

# Don't warn about missing SLF4J
-dontwarn org.slf4j.impl.**
-dontwarn org.slf4j.**

# Kotlin Serialization
-keepattributes *Annotation*, InnerClasses
-dontnote kotlinx.serialization.Annotations
-keepclassmembers class kotlinx.serialization.json.** {
    *** Companion;
}
-keepclasseswithmembers class kotlinx.serialization.json.** {
    kotlinx.serialization.KSerializer serializer(...);
}

# Supabase
-keep class io.supabase.** { *; }
-keep class net.supabase.** { *; }

# Stripe SDK
-keep class com.stripe.android.** { *; }
-keep class com.stripe.** { *; }

# Google Maps
-keep class com.google.android.gms.maps.** { *; }
-keep class com.google.android.gms.location.** { *; }

# Koin DI
-keep class org.koin.** { *; }
-keep class com.vito.app.di.** { *; }

# Keep Application class
-keep class com.vito.app.di.VitoApplication { *; }
-keep class com.vito.app.VitoApplication { *; }

# Keep MainActivity
-keep class com.vito.app.MainActivity { *; }

# Keep Service
-keep class com.vito.app.service.DriverLocationService { *; }

# Keep ViewModels
-keep class com.vito.app.viewmodel.** { *; }

# Coroutines
-keepnames class kotlinx.coroutines.internal.MainDispatcherFactory {}
-keepnames class kotlinx.coroutines.CoroutineExceptionHandler {}
-keepclassmembernames class kotlinx.** {
    volatile <fields>;
}

# Keep Compose
-keep class androidx.compose.** { *; }

# Prevent R8 from stripping interface information
-keep,allowobfuscation,allowoptimization interface * {
    <methods>;
}

# Keep enum classes
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}