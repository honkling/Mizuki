package sh.astrid.mizuki.listeners

import net.minecraft.server.network.ServerPlayerEntity

data class AsyncChatEvent(override val player: ServerPlayerEntity, val message: String) : PlayerEvent(player)