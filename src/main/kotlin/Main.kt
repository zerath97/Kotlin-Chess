package org.chess

import core.*
import org.jetbrains.annotations.TestOnly
import pieces.King
import pieces.Pawn

fun main() {

	val board1 : Board = Board()
	board1.setupPieces()

	val pieces = board1.pieces
	for ((key, piece) in pieces) {
		println(key)
	}


}