package blip.chat.sdk.android

import android.content.Context
import android.util.Log
import blip.chat.sdk.android.models.BlipChatModel
import blip.chat.sdk.android.models.BlipChatStyleModel
import blip.chat.sdk.android.models.TYPE
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.embedding.engine.FlutterEngineCache
import io.flutter.embedding.engine.dart.DartExecutor
import io.flutter.plugin.common.MethodChannel

open class BlipChat(var context: Context) {

    private lateinit var methodChannel: MethodChannel
    private val CHANNEL_NAME = "blip.sdk.chat.native/helper"
    private lateinit var flutterEngine: FlutterEngine
    
    fun open() {

        // Instantiate a FlutterEngine.
        flutterEngine = FlutterEngine(context)

        // Start executing Dart code to pre-warm the FlutterEngine.
        flutterEngine.dartExecutor.executeDartEntrypoint(
            DartExecutor.DartEntrypoint.createDefault()
        )

        // Cache the FlutterEngine to be used by FlutterActivity.
        FlutterEngineCache
            .getInstance()
            .put("my_engine_id", flutterEngine)

        methodChannel = MethodChannel(flutterEngine.dartExecutor.binaryMessenger, CHANNEL_NAME)
        methodChannel.setMethodCallHandler { call, result ->
            Log.i("RETORNO", result.toString())
        }

        var blip = BlipChatModel()
        blip.key = "Ym9yaXNha2l0YToyOTQwYTY5YS1hMTBjLTRjZDEtOTgwMi1lOGU1ZGFiOTMyYmE="
        blip.type = TYPE.PLAIN
        blip.hostName = "akita-mtls.ws.0mn.io"
        blip.useMtls = true
        var style = BlipChatStyleModel()
        style.primary = "c9c8cc"
        style.sentBubble = "a442f5"
        style.receivedBubble = "706f73"
        style.background = "7b42f5"
        style.showOwnerAvatar = true
        style.overrideOwnerColors = true
        style.showUserAvatar = true
        blip.style = style

        methodChannel.invokeMethod("onInit", blip.toString())

        context.startActivity(
            FlutterActivity.withCachedEngine("my_engine_id").build(context)
        )
    }

}