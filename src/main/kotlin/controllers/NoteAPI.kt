package controllers
import models.Note

// Controller class NoteAPI
class NoteAPI {
    var notes = ArrayList<Note>()

    // Function to add a note -- boolean
    fun add(note: Note): Boolean {
        return notes.add(note)
    }

    // Function listAllNotes -- if empty else listOfNotes
    fun listAllNotes(): String{
        return if (notes.isEmpty()) {
            "No notes stored"
        }
        else {
            var listOfNotes = ""
            for (i in notes.indices){
                listOfNotes += "${i}: ${notes[i]} \n"
            }
            listOfNotes
        }
    }

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


    // Function to list active notes, return empty if no notes in array list
    fun listActiveNotes(): String {
        return if (numberOfActiveNotes() == 0) {
            "No active notes"
        } else {
            var listOfActiveNotes = ""
            for (note in notes) {
                if (!note.isNoteArchived) {
                    listOfActiveNotes += "${notes.indexOf(note)}: $note \n"
                }
            }
            listOfActiveNotes
        }
    }

    // Function to get number of active notes = not archived
    fun numberOfActiveNotes(): Int {
        var counter = 0
        for (note in notes) {
            if (!note.isNoteArchived) {
                counter++
            }
        }
        return counter
    }

    // Function to list archived notes = not active
    fun listArchivedNotes(): String {
        return if (numberOfArchivedNotes() == 0) {
            "No archived notes"
        } else {
            var listOfAchivedNotes = ""
            for (note in notes) {
                if (note.isNoteArchived) {
                    listOfAchivedNotes += "${notes.indexOf(note)}: $note \n"
                }
            }
            listOfAchivedNotes
        }
    }

    // Function to show number of archived notes
    fun numberOfArchivedNotes(): Int {
        var counter = 0
        for (note in notes) {
            if (note.isNoteArchived) {
                counter++
            }
        }
        return counter
    }

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
    fun numberOfNotesByPriority(priority: Int): Int {
        var counter = 0
        for (note in notes) {
            if (note.notePriority == priority) {
                counter++
            }
        }
        return counter
    }

}