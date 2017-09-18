
public class BitMask {
	static int bitMask31_26(int hex) {
		return hex & 0xFC000000;

	}

	static int bitMask25_21(int hex) {
		return hex & 0x03E00000;

	}

	static int bitMask20_16(int hex) {
		return hex & 0x001F0000;

	}

	static int bitMask15_11(int hex) {
		return hex & 0x0000F800;

	}

	static int bitMask5_0(int hex) {
		return hex & 0x3F;

	}

	static int bitMask15_0(int hex) {
		return hex & 0xFFFF;
	}

}
