package model;
import java.util.HashMap;
import java.util.Map;

public class Player {

	public Player(PlayerType playerType)
	{
		type = playerType;
		score = 0;
		
		pieces = new HashMap<>();
//		for (PieceType piece : PieceType.values())
//		{
//			String key = piece.getKey();
//			pieces.put(key, 		new Piece(piece, this, key)); 
//			pieces.put(key + "2", 	new Piece(piece, this, key + "2"));
//		}
//		
		// if piece subclasses implemented, ^^^ method becomes
		for (PieceType piece : PieceType.values())
		{
			for (int i=1; i<=2; i++)
			{
				String key = piece.getKey() + i;
				switch (piece)
				{
					case ROOK : 	pieces.put(key, new Rook(this, key));	break;
					case KNIGHT : 	pieces.put(key, new Knight(this, key));	break;
					case BISHOP : 	pieces.put(key, new Bishop(this, key));	break;
				}
			}
		}
		
	}
	
	private String name;
	private String password;
	private PlayerType type;
	private int score;
	private Map<String, Piece> pieces;
	
	void removePiece(String pieceKey)
	{
		pieces.remove(pieceKey);
		// do something with score?
	}
	
	void addScore(int points)
	{
		score += points;
	}
	
	Map<String, Piece> getPieces()
	{
		return pieces;
	}
	
	PlayerType getType()
	{
		return type;
	}
	
	int getScore() {
		return score;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	public void setPassword(String password)
	{
		this.password = password;
	}
	
	public String getName()
	{
		return name;
	}
	
	public String getPassword()
	{
		return password;
	}
	
	public String toString()
	{
		return type.name();
	}
}
