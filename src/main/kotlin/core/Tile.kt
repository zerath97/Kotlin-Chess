package core

import core.Color

class Tile (val row: Int = 0, val col: Int = 0) {

	val color: Color = if((row + col).toInt() % 2 == 0) Color.BLACK else Color.WHITE
	var occupied : Boolean = false
	var occupant : Piece? = null
		set(value) {
			field = value
			occupied = value != null
		}

	fun occupiedSelf(row : Int, col : Int) {

	}

	fun occupiedEnemy(row : Int, col : Int) {

	}


	fun printTile() : Unit {
		println("Tile: row ($row), column ($col), occupied ($occupied), color ($color)")
	}

	override fun toString(): String {
		return "($row,$col)"
	}
}