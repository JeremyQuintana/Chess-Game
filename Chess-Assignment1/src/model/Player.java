package model;
import java.util.HashMap;
import java.util.Map;

public class Player {

	public Player(String name, String password)
	{
		this.name = name;
		this.password = password;
		// type set later
		score = 0;
		pieces = new HashMap<>();
		
		for (PieceType piecetype : PieceType.values())
			for (int i=1; i<=2; i++)
			{
				switch (piecetype)
				{
					case ROOK : 	add(new Rook("r" + i,   this));	break;
					case KNIGHT : 	add(new Knight("k" + i, this));	break;
					case BISHOP : 	add(new Bishop("b" + i, this));	break;
				}
			}
	}
	
	private String name;
	private String password;
	private PlayerType type;
	private int score;
	private Map<String, Piece> pieces;
	
	
	// internally, all pieces are uniquely defined
	// but can be accessed generally
	void remove(Piece piece)
	{
		if (piece != null)
			pieces.remove(piece.getKey());
	}
	
	void add(Piece piece)
	{
		if (piece != null)
			pieces.put(piece.getKey(),piece);
	}
	
	void addScore(int points)
	{
		score += points;
	}
	
	Map<String, Piece> getPieces()
	{
		return pieces;
	}
	
	public int getScore() 
	{
		return score;
	}
	
	public void setType(PlayerType type)
	{
		this.type = type;
	}
	
	public PlayerType getType()
	{
		return type;
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
