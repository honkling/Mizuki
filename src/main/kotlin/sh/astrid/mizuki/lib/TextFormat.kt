package sh.astrid.mizuki.lib

import net.minecraft.util.Formatting

enum class TextFormat(val formatting: Formatting) {
	CODE_0(Formatting.BLACK),
	CODE_1(Formatting.DARK_BLUE),
	CODE_2(Formatting.DARK_GREEN),
	CODE_3(Formatting.DARK_AQUA),
	CODE_4(Formatting.DARK_RED),
	CODE_5(Formatting.DARK_PURPLE),
	CODE_6(Formatting.GOLD),
	CODE_7(Formatting.GRAY),
	CODE_8(Formatting.DARK_GRAY),
	CODE_9(Formatting.BLUE),
	CODE_A(Formatting.GREEN),
	CODE_B(Formatting.AQUA),
	CODE_C(Formatting.RED),
	CODE_D(Formatting.LIGHT_PURPLE),
	CODE_E(Formatting.YELLOW),
	CODE_F(Formatting.WHITE),

	CODE_K(Formatting.OBFUSCATED),
	CODE_L(Formatting.BOLD),
	CODE_M(Formatting.STRIKETHROUGH),
	CODE_N(Formatting.UNDERLINE),
	CODE_O(Formatting.ITALIC),
	CODE_R(Formatting.RESET)
}