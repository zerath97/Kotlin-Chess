package pieces

import core.PieceType
import core.Color
import core.Piece
import core.Tile


class Pawn(color : Color) : Piece(PieceType.PAWN, color){


	override val id: Int = assignId(8)

	override fun possibleMoves(): List<Tile> {
		val row = this.tile?.row ?: 0u
		val col = this.tile?.col ?: 0u

		val regularMoves : MutableList<Tile> = mutableListOf()
		val attackMoves : MutableList<Tile> = mutableListOf()
		if (color == Color.BLACK) {
			regularMoves.add(Tile(row + 1u, col))
			regularMoves.add(Tile(row + 2u, col))
			attackMoves.add(Tile(row + 1u, col - 1u))
			attackMoves.add(Tile(row + 1u, col + 1u))

		} else {
			attackMoves.add(Tile(row - 1u, col - 1u))
			attackMoves.add(Tile(row - 1u, col + 1u))
		}


		return regularMoves /*, attackMoves*/
	}

	override fun move(newTile: Tile) {
		TODO("Not yet implemented")
	}

}