/*
 * Name: I.K
 * 
 * CS472 - Computer Architecture
 * MIPS Disassembler
 * Project 1
 * 
 */


public class ProjectOne {

	public static void main(String[] args) {
		
		
		/*opcode (B31-26)
		 * Destination register  rd (B25_21)
		 * 2nd source register  rt (B20_16)
		 * Source register  rs (B15_11)
		 * Immediate  (B15_0)
		 */
		
		int val, rs = 0, rt = 0, rd = 0, opcode = 0;
		short immediate = 0;
		
		
		
		//  Machine instructions that will be disassemble
		int hex_code[] = { 0x022DA822, 0x8EF30018, 0x12A70004, 0x02689820, 0xAD930018, 0x02697824, 0xAD8FFFF4,
				0x018C6020, 0x02A4A825, 0x158FFFF6, 0x8E59FFF0 };

		
		// first instruction begins at address hex 0X7A060
		int start_address = 0x7A060;
        System.out.println("\nAddress: ");
        
        
		for (int hex : hex_code) {

			System.out.printf("0x%02x", start_address);

			opcode = BitMask.bitMask31_26(hex) >>> 26;

			if (opcode == 0) {

				rs = BitMask.bitMask25_21(hex);
				rt = BitMask.bitMask20_16(hex);
				rd = BitMask.bitMask15_11(hex);

				int function = BitMask.bitMask5_0(hex);

				switch (function) {
				case 32:

					System.out.printf(" add ");
					break;
				case 34:

					System.out.printf(" sub ");
					break;
				case 36:

					System.out.printf(" and ");
					break;
				case 37:

					System.out.printf(" or  ");
					break;
				case 42:

					System.out.printf(" slt ");
					break;
				default:

					System.out.println("No data found");
				}
				rd = (rd >>> 11);
				rs = (rs >>> 21);
				rt = (rt >>> 16);

				System.out.printf("$%d, $%d, $%d", rd, rs, rt);

			}

			else {
				rs = BitMask.bitMask25_21(hex);
				rt = BitMask.bitMask20_16(hex);
				immediate = (short) BitMask.bitMask15_0(hex);

				if (opcode == 4) {
					System.out.printf(" beq ");
				} else if (opcode == 5) {
					System.out.printf(" bne ");
				} else if (opcode == 35) {
					System.out.printf(" lw ");
				} else if (opcode == 43) {
					System.out.printf(" sw ");

				} else {
					System.out.printf("No data found");
				}

				// lw and sw
				if (opcode == 35 || opcode == 43) {
					rt = (rt >>> 16);
					rs = (rs >>> 21);

					System.out.printf(" $%d, %d ($%d)", rt, immediate, rs);

				}
				// bne and beq
				else if (opcode == 4 || opcode == 5) {
					rs = (rs >> 21);
					rt = (rt >> 16);
					val = (start_address + 4 * immediate + 4);
					
					System.out.printf("$%d, $%d,", rs, rt);
					System.out.printf("address 0x%02x   ", val);
					
				} else

					System.out.println("No data found");

			}
			System.out.println();
			start_address += 4;
		}

	}

	
}

