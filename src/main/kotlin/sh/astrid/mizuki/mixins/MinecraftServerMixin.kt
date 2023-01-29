package sh.astrid.mizuki.mixins

import net.minecraft.server.MinecraftServer
import org.spongepowered.asm.mixin.Mixin
import org.spongepowered.asm.mixin.injection.At
import org.spongepowered.asm.mixin.injection.Inject
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo
import sh.astrid.mizuki.Discord

@Mixin(MinecraftServer::class)
class MinecraftServerMixin {
	@Inject(at = [At("HEAD")], method = ["close"])
	fun close(ci: CallbackInfo) {
		if (!Discord.isConnected) return
		Discord.api.disconnect().join()
	}
}