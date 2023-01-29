package sh.astrid.mizuki.lib

import net.minecraft.text.Text
import net.minecraft.text.TextColor

val CODE_PATTERN = Regex("(#[0-9a-fA-F]{6}|&[0-9a-f])?([^&]|&[klmnor])*")
val FORMAT_PATTERN = Regex("^(#[0-9a-fA-F]{6}|&[0-9a-fkl-or])")

fun String.toText(): Text {
    val components = CODE_PATTERN.findAll(this)
    val text = Text.empty()

    components.forEachIndexed { index, component ->
        // We skip the last match because our regex always provides null as the latter match
        if (index == components.count() - 1)
            return@forEachIndexed

        val match = component.value
        val formatted = FORMAT_PATTERN.containsMatchIn(match)
        val isCode = match.startsWith("&")
        val value = if (formatted) match.substring(if (isCode) 2 else 7) else match

        val appendage = Text.literal(value)

        if (formatted) {
            val formatMatch = FORMAT_PATTERN.find(match)!!.value.uppercase()

            if (isCode)
                appendage.formatted(TextFormat.valueOf("CODE_${formatMatch.substring(1)}").formatting)
            else {
                val newStyle = appendage.style.withColor(Integer.decode(formatMatch.replace("#", "0x")))
                appendage.style = newStyle
            }
        }

        text.append(appendage)
    }

    return text
}