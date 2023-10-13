import mu.KotlinLogging
import utils.ScannerInput
import utils.ScannerInput.ScannerInput.readNextInt
import utils.ScannerInput.ScannerInput.readNextLine
import kotlin.system.exitProcess

// Alexander Novakovsky Notes APP
// Testing new branch

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


// Function updateNote -- updating a note by selected ID
fun updateNote(){
    logger.info { "Update note function activated" }
}


// Function deleteNote -- note delete by entering ID
fun deleteNote(){
    logger.info { "Delete function activated" }
}


// Function exit -- application exit on selecting 0 from menu
fun exitApp(){
    println("App exiting")
    logger.info { "App terminated" }
    exitProcess(0)
}