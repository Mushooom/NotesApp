package controllers
import models.Note
import persistence.Serializer


// Controller class NoteAPI
class NoteAPI(serializerType:Serializer) {
    var notes = ArrayList<Note>()
    private var serializer: Serializer = serializerType

    @Throws(Exception::class)
    fun load(){
        notes = serializer.read() as ArrayList<Note>
    }

    @Throws(Exception::class)
    fun store(){
        serializer.write(notes)
        print("Saving notes\n")
    }

    // Function to add a note -- boolean
    fun add(note: Note): Boolean {
        return notes.add(note)
    }

    // Function listAllNotes -- if empty else listOfNotes
    fun listAllNotes(): String =
        if (notes.isEmpty()) "No notes stored"
        else notes.joinToString(separator = "\n") { note ->
            notes.indexOf(note).toString() + ": " + note.toString() }

    // Function to count notes in ArrayList
    fun numberOfNotes(): Int {
        return notes.size
    }

    // Function find a note by the index in ArrayList
    fun findNote(index: Int): Note? {
        return if (isValidListIndex(index, notes)) {
            notes[index]
        } else null
    }

    // Function to check if there are items in ArrayList
    fun isValidListIndex(index: Int, list: List<Any>): Boolean {
        return (index >= 0 && index < list.size)
    }

    // Function is valid index
    fun isValidIndex(index: Int): Boolean {
        return isValidListIndex(index, notes)
    }

    // Function to list active notes, return empty if no notes in array list
    fun listActiveNotes(): String =
        if (numberOfActiveNotes() == 0) "No active notes"
        else notes.filter { note -> !note.isNoteArchived}
            .joinToString(separator = "\n") { note ->
                notes.indexOf(note).toString() + ": " + note.toString() }

    // Function to get number of active notes = not archived
    fun numberOfActiveNotes(): Int =
        notes.count() { note: Note -> !note.isNoteArchived }

    // Function to list archived notes = not active
    fun listArchivedNotes(): String =
        if (numberOfArchivedNotes() == 0) "No archived notes"
        else notes.filter { note -> note.isNoteArchived}
            .joinToString(separator = "\n") { note ->
                notes.indexOf(note).toString() + ": " + note.toString() }

    // Function to show number of archived notes
    fun numberOfArchivedNotes(): Int =
        notes.count() { note: Note -> note.isNoteArchived }

    // Function list notes by selected priority --> from 1 to 5
    fun listNotesBySelectedPriority(priority: Int): String {
        return if (notes.isEmpty()) {
            "No notes stored"
        } else {
            var listOfNotes = ""
            for (i in notes.indices) {
                if (notes[i].notePriority == priority) {
                    listOfNotes +=
                        """$i: ${notes[i]}
                        """.trimIndent()
                }
            }
            if (listOfNotes.equals("")) {
                "No notes with priority: $priority"
            } else {
                "${numberOfNotesByPriority(priority)} notes with priority $priority: $listOfNotes"
            }
        }
    }

    // Function to show number(count) of notes by selected priority --> from 1 to 5
    fun numberOfNotesByPriority(priority: Int): Int  =
        notes.count() { note: Note -> note.notePriority == priority }


    // Function delete note by selected index
    fun deleteNote(indexToDelete: Int): Note? {
        return if (isValidListIndex(indexToDelete, notes)) {
            notes.removeAt(indexToDelete)
        } else null
    }

    // Function to update note by selected index
    fun updateNote(indexToUpdate: Int, note: Note?): Boolean {
        // Allocate the index of the note -> check if it exists
        val allocatedNote = findNote(indexToUpdate)
        if ((allocatedNote != null) && (note != null)) {
            allocatedNote.noteTitle = note.noteTitle
            allocatedNote.notePriority = note.notePriority
            allocatedNote.noteCategory = note.noteCategory
            return true
        } else return false
    }

    // Function to archive an active note
    fun archiveNote(indexToArchive: Int): Boolean {
        // Check if note is active
        if (isValidIndex(indexToArchive)) {
            val noteToArchive = notes[indexToArchive]
            if (!noteToArchive.isNoteArchived) {
                noteToArchive.isNoteArchived = true
                return true
            }
        }
        return false
    }

}
