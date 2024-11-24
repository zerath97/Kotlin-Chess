package pieces

import core.PieceType
import core.Color
import core.Piece
import core.Tile


class Rook(color : Color) : Piece(PieceType.ROOK, color){

	override val id: Int = assignId(2)

	override val offsetMoves: List<Tile> = listOf(
		Tile(1, 1), Tile(1, 0), Tile(1, -1), // Example moves
		Tile(0, 1),            Tile(0, -1),
		Tile(-1, 1), Tile(-1, 0), Tile(-1, -1)
	)

	override fun move(newTile: Tile) {
		TODO("Not yet implemented")
	}
}