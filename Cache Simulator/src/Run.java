
import java.util.Scanner;

public class Run {
	private String input;
	public final short SIZE = 16;
	private short slot,tag = -1;
	private boolean valid = false;
	private short[] data = new short[SIZE];
	public static final short MAIN_MEMORY = 2048;
	public static final short MAX_BYTE = 0xff;
	private short memory[] = new short[MAIN_MEMORY];
	public Node[] node = new Node[SIZE];

	static Scanner keyboard = new Scanner(System.in);

	public static void main(String args[]) {

		Run me = new Run();
		me.initializeCache();
		me.doIt();
	}

	public void cacheZero() {
		//
	}

	public void doIt() {

		System.out.println("\n(R)ead,(W)rite, or (D)isplay Cache");

		input = keyboard.next();

		if (input.equalsIgnoreCase("r")) {

			System.out.println("What address would you like to read?");
			short value = (short) keyboard.nextShort(16);
			read(value);
			doIt();

		} else if (input.equalsIgnoreCase("w")) {

			System.out.println("What address would you like to write?");
			short address = keyboard.nextShort(16);
			System.out.println("What data would you like to write at that address?");
			short data = keyboard.nextShort(16);
			write(address, data);
			doIt();

		} else if (input.equalsIgnoreCase("d")) {

			display();
			doIt();

		} else {

			System.out.println("Wrong input..Try again");
			doIt();

		}

	}
	public void initializeCache() {
		for (short s = 0; s < MAIN_MEMORY; s++) {
			this.memory[s] = (short) (s & 0Xff);
		}

		for (byte b = 0; b < SIZE; b++) {
			this.node[b] = new Node(b);
		}
	}

	public boolean read(short address) {
		short tag = (short) ((address & 0xF00) >>> 8);
		short slot = (short) ((address & 0x0F0) >>> 4);
		short offset = (short) (address & 0x00F);

		if (tag == this.node[slot].getTag()) {

			if (this.node[slot].getValid()) {
				System.out.printf("At that byte there is the value  %x (Cache Hit)\n",
						this.node[slot].getData(offset));
				return true;
			}

			else {

				this.data(address);
				System.out.printf("At that byte there is the value %x (Cache Miss)\n",
						this.node[slot].getData(offset));
				return true;
			}
		} else {

			if (!this.node[slot].getValid()) {

				this.data(address);
				System.out.printf("At that byte there is the value %x (Cache Miss)\n",
						this.node[slot].getData(offset));
				return true;

			} else {

				this.data(address);
				System.out.printf("At that byte there is the value %x  (Cache Miss)\n",
						this.node[slot].getData(offset));
				return true;
			}
		}
	}

	
	private void data(int address) {
		short tag = (short) ((address & 0xF00) >>> 8);
		short slot = (short) ((address & 0x0F0) >>> 4);
		short start = (short) (address & 0xFF0);
		short last = (short) (start + SIZE);short value = 0;
		for (short i = start; i < last; i++) {
			this.node[slot].setData(value++, this.memory[i]);
		}
		this.node[slot].setTag(tag);
		this.node[slot].setValid(true);
	}
	public boolean write(short address, short value) {
		short tag = (short) ((address & 0xF00) >>> 8);
		short slot = (short) ((address & 0x0F0) >>> 4);
		short offset = (short) (address & 0x00F);

		if (tag == this.node[slot].getTag()) {

			if (this.node[slot].getValid()) {

				this.node[slot].setData(offset, value);
				System.out.printf("Value %x has been written to address %x (Cache Hit)\n",
				this.node[slot].getData(offset), address);
				return true;

			} else {

				this.data(address);
				this.node[slot].setData(offset, value);
				System.out.printf("Value %x has been written to address %x (Cache Miss)\n",
						this.node[slot].getData(offset), address);
				return true;

			}
		} else {

			this.data(address);
			this.node[slot].setData(offset, value);
			System.out.printf("Value %X has been written to address %X (Cache Miss)\n",
					this.node[slot].getData(offset), address);
			return true;
		}
	}

	public void display() {
		System.out.println("Slot Valid Tag       Data");
		for (byte b = 0; b < SIZE; b++) {
			this.node[b].displayCache();

		}
		System.out.println();
	}

	public short getData(short data) {
		return this.data[data];
	}

	public void setData(short data, short count) {
		this.data[data] = count;
	}

	public boolean getValid() {
		return this.valid;
		
	}
	public void setValid(boolean valid) {
		this.valid = valid;
	}

	public void setTag(short tag) {
		this.tag = tag;
	}

	public short getTag() {
		return this.tag;
	}


	public void displayCache() {
		short valid = this.valid ? (short) 1 : (short) 0;
		System.out.printf("  %x    %d    %x         ", this.slot, valid, this.tag);
		for (short i : this.data) {
			System.out.printf("%x ", i);
		}
		System.out.println();

	}
}
