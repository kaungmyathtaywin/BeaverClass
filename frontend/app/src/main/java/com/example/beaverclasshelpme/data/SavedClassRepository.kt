package com.example.beaverclasshelpme.data

class SavedClassRepository(
    private val dao: SavedClassDao
) {
    suspend fun insertNewClass(myClass: SavedClass) = dao.insert(myClass)

    suspend fun deleteSavedCity(myClass: SavedClass) = dao.delete(myClass)

    fun getAllSavedClasses() = dao.getAllClasses()
}