package blip.chat.sdk.android

import android.os.Bundle
import blip.chat.sdk.android.models.BlipChatModel
import blip.chat.sdk.android.models.BlipChatStyleModel
import blip.chat.sdk.android.models.TYPE
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine

open class BlipChatActivity : FlutterActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)

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

        NativeMethodChannel.onInit(blip.toString())
    }

    override fun configureFlutterEngine(flutterEngine: FlutterEngine) {
        super.configureFlutterEngine(flutterEngine)
        NativeMethodChannel.configureChannel(flutterEngine)
    }
}