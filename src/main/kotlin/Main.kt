import java.util.Scanner
import java.lang.System.exit
import java.util.*
import kotlin.system.exitProcess

// Alexander Novakovsky Notes APP


//Global variables
val scanner = Scanner(System.`in`)

// Main function
fun main(args: Array<String>) {
    runMenu()
}


// Main menu function -- (alt + 0151) -- return Int value entered
fun mainMenu(): Int {
    println("Note Keeper App")
    println("———————————————")
    println("NOTE MENU:")
    println("———————————————")
    println(" 1 -> Add a note")
    println(" 2 -> List all notes")
    println(" 3 -> Update a note")
    println(" 4 -> Delete a note")
    println("———————————————")
    println(" 0 -> Exit app")
    println("———————————————")
    println("Enter option: ")
    return scanner.nextInt()
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
    println("Adding a note")
}


// Function listNotes -- listing all the notes
fun listNotes(){
    println("Listing all the notes")
}


// Function updateNote -- updating a note by selected ID
fun updateNote(){
    println("Updating a note")
}


// Function deleteNote -- note delete by entering ID
fun deleteNote(){
    println("Note has been deleted")
}


// Function exit -- application exit on selecting 0 from menu
fun exitApp(){
    println("App exiting")
    exitProcess(0)
}