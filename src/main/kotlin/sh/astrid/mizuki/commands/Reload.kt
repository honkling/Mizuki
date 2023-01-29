package sh.astrid.mizuki.commands

import com.mojang.brigadier.builder.LiteralArgumentBuilder.literal
import com.mojang.brigadier.context.CommandContext
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback
import net.fabricmc.loader.api.FabricLoader
import net.minecraft.command.CommandSource
import net.minecraft.server.MinecraftServer
import net.minecraft.server.command.ServerCommandSource
import net.minecraft.text.Text
import sh.astrid.mizuki.Mizuki

class Reload {
    init {
        CommandRegistrationCallback.EVENT.register { dispatcher, _, _ ->
            dispatcher.register(literal<ServerCommandSource>("mizuki")
                .then(literal<ServerCommandSource>("reload")
                    .executes(::onCommand)))
        }
    }

    fun onCommand(ctx: CommandContext<ServerCommandSource>): Int {
        val sender = ctx.source
        val manager = sender.server.playerManager

        if (!sender.isExecutedByPlayer || manager.isOperator(sender.player!!.gameProfile)) return 0

        Mizuki.instance.reloadConfig()
        sender.sendMessage(Text.literal("[Mizuki] Successfully reloaded config."))

        return 1
    }
}