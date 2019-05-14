package model;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Player {

	public Player(String id, String password, PlayerType type)
	{
		this.id = id;
		this.password = password;
		this.type = type;
		score = 0;
		pieces = new HashMap<>();
		
		for (PieceType piecetype : PieceType.values())
			for (int i=1; i<=2; i++)
			{
				switch (piecetype)
				{
					case ROOK : 	add(new Rook("r" + i,   type));	break;
					case KNIGHT : 	add(new Knight("k" + i, type));	break;
					case BISHOP : 	add(new Bishop("b" + i, type));	break;
				}
			}
	}
	
	private String id;
	private String password;
	private PlayerType type;
	private int score;
	private Map<String, Piece> pieces;
	
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
	
	public PlayerType getType()
	{
		return type;
	}
	
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
