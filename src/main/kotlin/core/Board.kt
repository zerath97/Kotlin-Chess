package core

import core.Piece
import core.Tile
import pieces.*

class Board {

	var id : Int = 0

	companion object {
		var counter : Int = 0
	}

	init {
		Piece.resetCounter()
		counter++
		id = counter
	}

	val tiles: List<Tile> = List(64) { index ->
		val row : Int = (1 + (index / 8))
		val col : Int = (1 + (index % 8))
		Tile(row, col)
	}

	val pieces = mutableMapOf<String, Piece?>().apply {
		for (index in 0 .. 31) {
			val piece = when (index) {
				0, 7 -> Rook(Color.BLACK)
				1, 6 -> Knight(Color.BLACK)
				2, 5 -> Bishop(Color.BLACK)
				3 -> Queen(Color.BLACK)
				4 -> King(Color.BLACK)
				in 8..15 -> Pawn(Color.BLACK)

				in 16..23 -> Pawn(Color.WHITE)
				28 -> King(Color.WHITE)
				27 -> Queen(Color.WHITE)
				24, 31 -> Rook(Color.WHITE)
				25, 30 -> Knight(Color.WHITE)
				26, 29 -> Bishop(Color.WHITE)

				else -> null
			}

			piece?.let {
				val key = "${it.color} ${it.type} ${it.id}"
				put(key, it)
			}
		}
	}

//	val pieces = MutableList<Piece?>(32) { index ->
//
//		when(index) {
//			0, 7 -> Rook(Color.BLACK)
//			1, 6 -> Knight(Color.BLACK)
//			2, 5 -> Bishop(Color.BLACK)
//			3 -> Queen(Color.BLACK)
//			4 -> King(Color.BLACK)
//			in 8..15 -> Pawn(Color.BLACK)
//
//			in 16..23 -> Pawn(Color.WHITE)
//			28 -> King(Color.WHITE)
//			29 -> Queen(Color.WHITE)
//			24, 31 -> Rook(Color.WHITE)
//			25, 30 -> Knight(Color.WHITE)
//			26, 27 -> Bishop(Color.WHITE)
//
//			else -> null
//		}
//	}

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
		var count : Int = 0
		for((_, piece) in pieces) {
			piece?.tile = if (count < pieces.size/2) tiles[count] else tiles[count+32]
			count++
		}

		this.print()
	}

	fun getTile(row: Int, col: Int) : Tile {
		val index = ((row - 1) * 8 + (col - 1)).toInt()
		return tiles[index]
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
		var count : Int = 0
		for ((key, piece) in pieces) {
			if (count % 8 == 0 && count  > 0) println("")
			print("- $piece id: ${pieces[key]} ")
			count++
		}
		println()
	}

}