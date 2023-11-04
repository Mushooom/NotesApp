import controllers.NoteAPI
import models.Note
import mu.KotlinLogging
import persistence.JSONSerializer
import persistence.XMLSerializer
import utils.ScannerInput
import utils.ScannerInput.ScannerInput.readNextInt
import utils.ScannerInput.ScannerInput.readNextLine
import java.io.File
import kotlin.system.exitProcess

// Alexander Novakovsky Notes APP -- v2.0

// Logger variable
var logger = KotlinLogging.logger{}

// NoteAPI variable
  // private val noteAPI = NoteAPI(XMLSerializer(File("notes.xml")))
private val noteAPI = NoteAPI(JSONSerializer(File("notes.json")))

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
        │  NOTE MENU:              │┌──────────────────────────┐
        │  1 -> Add a note         ││  10  > Archive note      │
        │  2 -> List all notes     ││  15 -> Active number     │
        │  3 -> Update a note      ││  16 -> Archived number   │
        │  4 -> Delete a note      ││  20 -> Save notes        │
        │  5 -> --------------     ││  21 -> Load notes        │
        │  6 -> --------------     ││  **  > --------------    │
        │  7 -> By priority notes  ││  **  > --------------    │
        │  8 -> Number by priority ││  **  > --------------    │
        │  9 -> --                 ││  99  > Dummy data        │
        ├──────────────────────────┤└──────────────────────────┘
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
            10 -> archiveNote()
            15 -> numberOfActiveNotes()
            16 -> numberOfArchivedNotes()
            20 -> saveNotes()
            21 -> loadNotes()
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
   if (noteAPI.numberOfNotes() > 0){
       val option = readNextInt(
       """
          > -----------------------
          > 1-> View all notes
          > 2-> View active notes
          > 3-> View archived notes
          > -----------------------
          > Enter option:
       """.trimMargin(">"))

       when (option) {
           1 -> listAllNotes()
           2 -> listActiveNotes()
           3 -> listArchivedNotes()
           else -> println("Invalid option: $option")
       }
   } else {
       println("Notes list empty")
   }
}

// Function to list all notes
fun listAllNotes(){
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

// Function to show count of archived notes
fun numberOfArchivedNotes(){
    println("Archived notes: " + noteAPI.numberOfArchivedNotes())
}

// Function updateNote -- updating a note by selected ID
fun updateNote(){
    logger.info { "Update note function activated" }
    // List notes to choose index of note to be updated
    listNotes()
    if (noteAPI.numberOfNotes() > 0) {
        val indexToUpdate = readNextInt("Enter index of note to update: ")
        if (noteAPI.isValidIndex(indexToUpdate)) {
            val noteTitle = readNextLine("Enter title: ")
            val notePriority = readNextInt(" Priority from 1 to 5: ")
            val noteCategory = readNextLine("Enter category: ")
            // pass the index and new data to NoteAPI
            if (noteAPI.updateNote(indexToUpdate, Note(noteTitle, notePriority, noteCategory, false))){
                println("Successful update")
            }
            else println("Update failed")
        }
        else println("No notes with such index number")
    }
}

// Function deleteNote -- note delete by entering ID
fun deleteNote(){
    logger.info { "Delete function activated" }
    // List notes -> check if populated -> then prompt for index number to delete
    listNotes()
    if (noteAPI.numberOfNotes() > 0) {
        val indexToDelete = readNextInt("Enter note index to delete: ")
        // Pass the index number to function deleteNote delete the note by specified index
        val noteToDelete = noteAPI.deleteNote(indexToDelete)
            if (noteToDelete != null) {
                println("Delete success. Deleted note: ${noteToDelete.noteTitle}")
            } else {
                println("Delete fail")
            }

    }
}

// Function to archive a note
fun archiveNote(){
    // Check if there are active notes in ArrayList
    listActiveNotes()
    if (noteAPI.numberOfActiveNotes() > 0){
        val indexToArchive = readNextInt("Enter index of the note to be archived: ")
        // Pass the index of the note to be archived
        if (noteAPI.archiveNote(indexToArchive)) {
            println("Note archived")
        } else {
            println("Failed to archive note")
        }
    }
}


// Function save -> save notes to file notes.xml
fun saveNotes(){
    try {
        noteAPI.store()
    } catch (e: Exception){
        System.err.println("Error saving to file: $e")
    }
}

// Function load saved notes from file
fun loadNotes(){
    try {
        noteAPI.load()
    } catch (e: Exception){
        System.err.println("Error loading form file: $e")
    }
}

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