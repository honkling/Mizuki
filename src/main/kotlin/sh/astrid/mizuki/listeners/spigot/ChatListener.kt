package sh.astrid.mizuki.listeners.spigot

import net.fabricmc.fabric.api.message.v1.ServerMessageEvents
import net.minecraft.network.message.MessageType.Parameters
import net.minecraft.network.message.SignedMessage
import net.minecraft.server.network.ServerPlayerEntity
import sh.astrid.mizuki.lib.*
import sh.astrid.mizuki.listeners.AsyncChatEvent

class ChatListener {
    init {
        ServerMessageEvents.ALLOW_CHAT_MESSAGE.register(::onChat)
    }

    private fun onChat(message: SignedMessage, player: ServerPlayerEntity, params: Parameters): Boolean {
        sendWebhook(buildMsg(AsyncChatEvent(player, message.content.string), "chat"))
        return true
    }
}