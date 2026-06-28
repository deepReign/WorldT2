package com.flipkart.machinecoding.worldt2.util


object OversFormatter {

    fun format(balls: Int): String {

        return "${balls / 6}.${balls % 6}"
    }
}