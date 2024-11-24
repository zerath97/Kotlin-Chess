package pieces

import core.PieceType
import core.Color
import core.Piece
import core.Tile



class King(color : Color) : Piece(PieceType.KING, color){

	override val id: Int = assignId(1)

	override val offsetMoves: List<Tile> = listOf(
		Tile(1, 1), Tile(1, 0), Tile(1, -1), // Example moves
		Tile(0, 1),            Tile(0, -1),
		Tile(-1, 1), Tile(-1, 0), Tile(-1, -1)
	)







	override fun move(newTile: Tile) {
		TODO("Not yet implemented")
	}
}