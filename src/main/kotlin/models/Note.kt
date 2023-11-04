package models


// Data class Note --as specified in Git-hub issue
// noteTitle: String, notePriority: Int(1..5), noteCategory: String, isNoteArchived: boolean
data class Note (
    var noteTitle: String,
    var notePriority: Int,
    var noteCategory: String,
    var isNoteArchived: Boolean
)