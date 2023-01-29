package sh.astrid.mizuki

import com.moandjiezana.toml.Toml
import net.fabricmc.api.DedicatedServerModInitializer
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents
import net.fabricmc.loader.api.FabricLoader
import net.minecraft.server.MinecraftServer
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import sh.astrid.mizuki.commands.*
import sh.astrid.mizuki.listeners.spigot.*
import java.io.File
import java.util.logging.Level

class Mizuki : DedicatedServerModInitializer {
    val dataFolder = File("./config/Mizuki")
    val logger: Logger = LogManager.getLogger("Mizuki")
    lateinit var server: MinecraftServer
    lateinit var config: Toml

    init {
        instance = this

        // Configuration
        dataFolder.parentFile.mkdir()
        dataFolder.mkdir()
        saveResource("config.toml", false)
        saveResource("messages.toml", false)
        reloadConfig()
    }

    override fun onInitializeServer() {
        ServerLifecycleEvents.SERVER_STARTING.register { server ->
            this.server = server
        }

        Discord.load()

        // Register commands
        Reload()

        // Register Fabric events
        PlayerListener()
        ChatListener()

        val version = FabricLoader.getInstance().getModContainer("mizuki").get().metadata.version.friendlyString
        instance.logger.info("Mizuki v$version has loaded successfully.")

        // todo: add config option for "rich webhooks"
        //  - changes the webhook avatar to whatever they configure
        //  - changes the name to whatever they configure

    }

    fun reloadConfig() {
        config = Toml().read(File(dataFolder, "config.toml"))
    }

    fun saveConfig() {
        val content = StringBuilder()

        config.toMap().entries.forEach { (key, value) ->
            content.append("$key = ")

            content.append(
                    (if (value is String) "\"" + value
                    .replace("\"", "\\\"")
                    .replace("\\", "\\\\") + "\""
                else value))

            content.append("\n")
        }

        val config = File(dataFolder, "config.toml")

        config.createNewFile()

        config.writeText(content.toString())
    }

    private fun saveResource(path: String, override: Boolean) {
        val file = dataFolder.resolve(path)
        val resource = Mizuki::class.java.getResource("/$path")
        val content = resource.readText()

        if (override || !file.exists()) {
            file.createNewFile()
            file.writeText(content)
        }
    }

    companion object {
        @JvmStatic
        lateinit var instance: Mizuki
    }
}