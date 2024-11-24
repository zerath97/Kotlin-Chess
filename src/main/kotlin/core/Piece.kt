package core

abstract class Piece (private val type: PieceType, val color: Color) {
	private var _tile: Tile? = null

	var tile: Tile?
		get() = _tile
		set(value) {
			_tile?.occupant = null
			_tile = value
			_tile?.occupant = this
		}

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


	abstract fun possibleMoves(): List<Tile>

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
		return "${color.name} ${type.name}".lowercase()
	}


}