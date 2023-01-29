package sh.astrid.mizuki.listeners

import net.minecraft.server.network.ServerPlayerEntity

data class PlayerQuitEvent(override val player: ServerPlayerEntity) : PlayerEvent(player)
