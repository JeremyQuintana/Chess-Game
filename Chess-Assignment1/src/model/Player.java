package model;
import java.util.HashMap;
import java.util.Map;

public class Player {

	public Player(String id, String password)
	{
		this.id = id;
		this.password = password;
		score = 0;
		
		pieces = new HashMap<>();
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
	
	private String id;
	private String password;
	private PlayerType type;
	private int score;
	private Map<String, Piece> pieces;
	
	void removePiece(String pieceKey)
	{
		pieces.remove(pieceKey);
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
	
	void setType(PlayerType type)
	{
		this.type = type;
	}
	
	int getScore() 
	{
		return score;
	}
	
//	public void setid(String id)
//	{
//		this.id = id;
//	}
//	public void setPassword(String password)
//	{
//		this.password = password;
//	}
	
	public String getid()
	{
		return id;
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
