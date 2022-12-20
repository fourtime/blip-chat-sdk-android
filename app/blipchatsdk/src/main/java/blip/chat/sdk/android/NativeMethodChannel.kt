package blip.chat.sdk.android

import android.util.Log
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodChannel

object NativeMethodChannel {
    private const val CHANNEL_NAME = "blip.sdk.chat.native/helper"
    private lateinit var methodChannel: MethodChannel

    fun configureChannel(flutterEngine: FlutterEngine) {
        methodChannel = MethodChannel(flutterEngine.dartExecutor.binaryMessenger, CHANNEL_NAME)
        methodChannel.setMethodCallHandler { call, result ->
            Log.i("RETORNO", result.toString())
        }
    }

    fun onInit(values: String) {
        methodChannel.invokeMethod("onInit", values)
    }
}