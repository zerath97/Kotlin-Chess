package core

import core.Piece
import core.Tile
import pieces.*

class Board {
	val tiles: List<Tile> = List(64) { index ->
		val row : UInt = (1 + (index / 8)).toUInt()
		val col : UInt = (1 + (index % 8)).toUInt()
		Tile(row, col)
	}

	val pieces = MutableList<Piece?>(32) { index ->

		when(index) {
			28 -> King(Color.WHITE)
			4 -> King(Color.BLACK)
			in 16..23 -> Pawn(Color.WHITE)
			in 8..15 -> Pawn(Color.BLACK)

			else -> null
		}
	}

	var id : Int = 0

	companion object {
		var counter : Int = 0
	}

	init {
		Piece.resetCounter()
		counter++
		id = counter
	}

	fun getTile(row: UInt, col: UInt) : Tile {
		val index = ((row - 1u) * 8u + (col - 1u)).toInt()
		return tiles[index]
	}


	fun setupPieces() {
		println("New setup of the board is starting...")
		this.print()
		println("Resetting board...")
		print("State of each tile:")
		for (tile in tiles) {
			tile.occupant = null
			print(" {${tile.occupied}}")
		}
		println()
		this.print()
		println("Putting pieces into their starting positions...")
		for(i in 0..< pieces.size) {

			 pieces[i]?.tile = if (i < pieces.size/2) tiles[i] else tiles[i+32]
		}

		this.print()
	}

	fun print(): Unit {
		println("/||\\ Current state of Board $id /||\\")
		for (tile in tiles) {
			if ((tiles.indexOf(tile)) % 8 == 0 && tiles.indexOf(tile)  > 0) println()
			print("- $tile [${tile.occupant}] ")
		}
		println()
	}

	fun printPieces(): Unit {
		println("/||\\ All pieces existing in Board $id /||\\")
		for ((i, piece) in pieces.withIndex()) {
			if (i % 8 == 0 && i  > 0) println("")
			print("- $piece id: ${pieces.indexOf(piece)} ")
		}
		println()
	}

}