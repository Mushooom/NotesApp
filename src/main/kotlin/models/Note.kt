package models


// Data class Note --as specified in Git-hub issue
// noteTitle: String, notePriority: Int(1..5), noteCategory: String, isNoteArchived: boolean
data class Note (
    val noteTitle: String,
    val notePriority: Int,
    val noteCategory: String,
    val isNoteArchived: Boolean
)