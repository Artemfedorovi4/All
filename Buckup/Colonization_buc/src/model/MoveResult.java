package model;

public class MoveResult {

	private MoveType type;

	private Hexagon hex;

	public Hexagon getHex() {
		return hex;
	}

	public MoveResult(MoveType type) {
		this(type, null);
	}

	public MoveResult(MoveType type, Hexagon hex) {
		this.type = type;
		this.hex = hex;
	}

	public MoveType getType() {
		return type;
	}
}
