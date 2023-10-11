import mu.KotlinLogging
import utils.ScannerInput
import kotlin.system.exitProcess

// Alexander Novakovsky Notes APP


// Logger variable
var logger = KotlinLogging.logger{}

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


// Function addNote -- adding a note
fun addNote(){
    logger.info { "Add note function activated" }
}


// Function listNotes -- listing all the notes
fun listNotes(){
    logger.info { "List notes function activated" }
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