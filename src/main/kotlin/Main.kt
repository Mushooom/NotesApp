import models.Note
import mu.KotlinLogging
import utils.ScannerInput
import utils.ScannerInput.ScannerInput.readNextInt
import utils.ScannerInput.ScannerInput.readNextLine
import kotlin.system.exitProcess


// Alexander Novakovsky Notes APP -- v2.0

// Logger variable
var logger = KotlinLogging.logger{}

// NoteAPI variable
private val noteAPI = controllers.NoteAPI()

// Main function
fun main(args: Array<String>) {
    logger.info { "NOTE APP starting" }
    runMenu()
}


// Main menu function -- (alt + 179, 180, 196 ...) -- return Int value entered
// Web source for alt codes https://www.alt-codes.net/
fun mainMenu(): Int {
    return ScannerInput.ScannerInput.readNextInt("""
        ┌──────────────────────────┐
        │     NOTE KEEPER APP      │
        ├──────────────────────────┤
        │  NOTE MENU:              │
        │  1 -> Add a note         │
        │  2 -> List all notes     │
        │  3 -> Update a note      │
        │  4 -> Delete a note      │
        │  5 -> Active notes       │
        │  6 -> Archived notes     │
        │  7 -> By priority notes  │
        │  8 -> Number by priority │
        │  9 -> --                 │
        │  10  > --------------    │
        │  15 -> Active number     │
        │  16 -> Archived number   │        
        │  99 -> Dummy data        │
        ├──────────────────────────┤
        │  0 -> Exit app           │
        └──────────────────────────┘
           Enter option:    
    """.trimIndent())
}


// RunMenu function -- background code for menu
fun runMenu() {
    do {
        when (val option: Int = mainMenu()) {
            1 -> addNote()
            2 -> listNotes()
            3 -> updateNote()
            4 -> deleteNote()
            5 -> listActiveNotes()
            6 -> listArchivedNotes()
            15 -> numberOfActiveNotes()
            16 -> numberOfArchivedNotes()
            99 -> dummyData()
            0 -> exitApp()
            else -> println("Invalid option entered: $option")
        }
    } while (true)
}


// Function addNote -- adding a note -> noteAPI array list -> models Note
fun addNote(){
    //logger.info { "addNote function invoked" }
    val noteTitle = readNextLine("Enter a tile of the note: ")
    val notePriority = readNextInt("Enter a priority from 1 to 5: ")
    val noteCategory = readNextLine("Enter a category for the note: ")
    val isAdded = noteAPI.add(models.Note(noteTitle, notePriority, noteCategory, false))

    if (isAdded) {
        println("Added Successfully")
    } else {
        println("Add Failed")
    }
}


// Function listNotes -- listing all the notes called from noteAPI
fun listNotes(){
    //logger.info { "List notes function activated" }
    println(noteAPI.listAllNotes())
}

// Function list all active notes
fun listActiveNotes(){
    println(noteAPI.listActiveNotes())
}

// Function list all archived notes
fun listArchivedNotes(){
    println(noteAPI.listArchivedNotes())
}

// Function to show count of active notes
fun numberOfActiveNotes(){
    println("Active notes: " + noteAPI.numberOfActiveNotes())
}

// Function to show count of active notes
fun numberOfArchivedNotes(){
    println("Archived notes: " + noteAPI.numberOfArchivedNotes())
}

// Function updateNote -- updating a note by selected ID
fun updateNote(){
    logger.info { "Update note function activated" }
}


// Function deleteNote -- note delete by entering ID
fun deleteNote(){
    logger.info { "Delete function activated" }
    // List notes -> check if populated -> then prompt for index number to delete
    listNotes()
    if (noteAPI.numberOfNotes() > 0) {
        val indexToDelete = readNextInt("Enter note index to delete: ")
        // Pass the index number to function deleteNOte delete the note by specified index
        val noteToDelete = noteAPI.deleteNote(indexToDelete)
            if (noteToDelete != null) {
                println("Delete success. Deleted note: ${noteToDelete.noteTitle}")
            } else {
                println("Delete fail")
            }

    }
}


// Function listNotesByPriority -> noteAPI listNotesBySelectedPriority



// Function exit -- application exit on selecting 0 from menu
fun exitApp(){
    println("App exiting")
    logger.info { "App terminated" }
    exitProcess(0)
}

// Dummy data
fun dummyData(){
    noteAPI.add(Note("Title", 1, "College", false))
    noteAPI.add(Note("Missing", 2, "Work", true))
    noteAPI.add(Note("Heroes of Might and Magic", 3, "Games", false))
    noteAPI.add(Note("Vikings", 5, "Netflix", false))
    noteAPI.add(Note("Hobbies", 4, "Work", true))
}