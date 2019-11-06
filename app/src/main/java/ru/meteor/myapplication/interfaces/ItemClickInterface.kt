package ru.meteor.myapplication.interfaces

// Интерфейс обработки нажатия
interface ItemClickInterface {
    fun onClick()

    fun onClick(position: Int)
}