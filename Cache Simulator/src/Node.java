
public class Node {
	public short minValue = 0x0;
	public final short SIZE = 16;
	public short slot,tag = -1;
	public boolean valid = false;
	public short[] data = new short[SIZE];

	public void displayCache() {
		short valid = this.valid ? (short) 1 : (short) 0;
		System.out.printf("  %X    %d    %X         ", this.slot, valid, this.tag);
		for (short i : this.data) {
			System.out.printf("%X ", i);
		}
		System.out.println();

	}

	public Node(short slot) {
		this.slot = slot;
		this.valid = false;
		this.tag = minValue;
		for (short i = 0; i < 16; i++) {
			this.data[i] = minValue;
		}
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

	

}
