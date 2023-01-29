package sh.astrid.mizuki.listeners.spigot

import net.fabricmc.fabric.api.networking.v1.PacketSender
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents
import net.minecraft.server.MinecraftServer
import net.minecraft.server.network.ServerPlayNetworkHandler
import sh.astrid.mizuki.lib.*
import sh.astrid.mizuki.listeners.PlayerEvent

class PlayerListener {

    init {
        ServerPlayConnectionEvents.JOIN.register(::onJoin)
        ServerPlayConnectionEvents.DISCONNECT.register(::onLeave)
    }

    private fun onJoin(handler: ServerPlayNetworkHandler, _sender: PacketSender, _server: MinecraftServer) {
        sendWebhook(buildMsg(PlayerEvent(handler.player), "join"))
    }

    private fun onLeave(handler: ServerPlayNetworkHandler, _server: MinecraftServer) {
        sendWebhook(buildMsg(PlayerEvent(handler.player), "leave"))
    }
}
