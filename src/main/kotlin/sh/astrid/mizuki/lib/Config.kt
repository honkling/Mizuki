package sh.astrid.mizuki.lib

import com.moandjiezana.toml.Toml
import sh.astrid.mizuki.Mizuki
import java.io.File

fun getConfig(key: String): String {
	val path = File(Mizuki.instance.dataFolder, "messages.toml")
	val toml: Toml = Toml().read(path)
	return toml.getString(key) ?: ""
}

fun Toml.set(key: String, value: Any) {
	val field = Toml::class.java.getDeclaredField("values")
	field.isAccessible = true
	val values = field.get(this) as HashMap<String, Any>

	values[key] = value
	field.set(this, values)
}