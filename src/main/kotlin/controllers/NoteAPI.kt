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
}