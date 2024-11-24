package pieces

import core.PieceType
import core.Color
import core.Piece
import core.Tile



class King(color : Color) : Piece(PieceType.KING, color){

	override val id: Int = assignId(1)

	override fun possibleMoves(): List<Tile> {
		TODO("Not yet implemented")
	}

	override fun move(newTile: Tile) {
		TODO("Not yet implemented")
	}
}