package controllers
import models.Note

// Controller class NoteAPI
class NoteAPI {
    private var notes = ArrayList<Note>()

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
}