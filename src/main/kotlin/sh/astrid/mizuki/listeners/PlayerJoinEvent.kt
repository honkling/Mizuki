package sh.astrid.mizuki.listeners

import net.minecraft.server.network.ServerPlayerEntity

data class PlayerJoinEvent(override val player: ServerPlayerEntity) : PlayerEvent(player)