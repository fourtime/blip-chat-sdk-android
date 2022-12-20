package blip.chat.sdk.android.test

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import blip.chat.sdk.android.BlipChat
import blip.chat.sdk.android.BlipChatActivity
import blip.chat.sdk.android.models.BlipChatModel
import blip.chat.sdk.android.models.BlipChatStyleModel
import blip.chat.sdk.android.models.TYPE

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var button = findViewById<Button>(R.id.button)

        var model = BlipChatModel()
        model.key = "Ym9yaXNha2l0YToyOTQwYTY5YS1hMTBjLTRjZDEtOTgwMi1lOGU1ZGFiOTMyYmE="
        model.type = TYPE.PLAIN
        model.hostName = "akita-mtls.ws.0mn.io"
        model.useMtls = true
        var style = BlipChatStyleModel()
        style.primary = "c9c8cc"
        style.sentBubble = "a442f5"
        style.receivedBubble = "706f73"
        style.background = "7b42f5"
        style.showOwnerAvatar = true
        style.overrideOwnerColors = true
        style.showUserAvatar = true
        model.style = style

        var blip = BlipChat(this, model)

        button.setOnClickListener {
            startActivity(
                BlipChatActivity.withEngineDefault().build(blip, this)
            )
        }

    }
}