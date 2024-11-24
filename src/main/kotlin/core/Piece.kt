package core

abstract class Piece (val type: PieceType, val color: Color) {

	abstract val id: Int

	companion object {
		private val idCounters : MutableMap<String, Pair<Int, Int>> = mutableMapOf()
		fun resetCounter() {
			idCounters.clear()
		}
	}

	protected fun assignId(maxPieceType : Int): Int {
		val errMsg : String = "Cannot create more than $maxPieceType of ${this}s."
		val key : String = "$this"
		val (blackCount, whiteCount) = idCounters.getOrDefault(key, Pair(0, 0))

		val updatedCounters = when (color) {
			Color.BLACK -> {
				if (blackCount >= maxPieceType) error(errMsg)
				Pair(blackCount + 1, whiteCount)
			}
			Color.WHITE -> {
				if (whiteCount >= maxPieceType) error(errMsg)
				Pair(blackCount, whiteCount + 1)
			}
			else -> {
				error("Choose either BLACK or WHITE")
			}
		}


		idCounters[key] = updatedCounters

		return if (color == Color.BLACK) updatedCounters.first else updatedCounters.second
	}

	private var _tile: Tile? = null
	var tile: Tile?
		get() = _tile
		set(value) {
			_tile?.occupant = null
			_tile = value
			_tile?.occupant = this
		}

	abstract val offsetMoves : List<Tile>

	fun getRelativeMoves() : MutableList<Tile> {
		var newRow : Int
		var newCol : Int
		val relativeMoves = MutableList<Tile>(0) {i: Int ->
			newRow = this.tile?.row?.plus(offsetMoves[i].row) ?: 0
			newCol = this.tile?.row?.plus(offsetMoves[i].col) ?: 0
			Tile(newRow, newCol)
		}

		return relativeMoves
	}

	fun getOpenBoardMoves() : List<Tile> {
		val openBoardMoves : MutableList<Tile> = getRelativeMoves()

		for (move in openBoardMoves) {
			if (move.row > 8 || move.row < 1 || move.col > 8 || move.col < 1)
				openBoardMoves.remove(move)
		}
		return openBoardMoves
	}



	// Alternativ solution to getRelativeMoves():
//	val relativeMoves : List<Tile> by lazy {
//		offsetMoves.map {move ->
//			val newRow = this.tile?.row?.plus(move.row) ?: 0
//			val newCol = this.tile?.row?.plus(move.col) ?: 0
//			Tile(newRow, newCol)
//		}
//	}

	// TODO: Remove border moves from relativeMoves

	fun possibleMoves(): List<Tile> {
		TODO()
	}

	abstract fun move(newTile: Tile) : Unit

	fun emptyTiles(newTiles : MutableList<Tile>, occupiedTiles : MutableList<Tile>) {
		val tilesToRemove = mutableListOf<Tile>()

		for (newTile in newTiles) {
			if(!newTile.occupied) {
				tilesToRemove.add(newTile)
				occupiedTiles.add(newTile)
			}
		}

		newTiles.removeAll(tilesToRemove)
	}

	override fun toString(): String {
		return "${color.name} ${type.name} $id".lowercase()
	}


}