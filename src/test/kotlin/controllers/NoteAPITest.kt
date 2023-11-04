package controllers
// Imports
import models.Note
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNull

/* Testing class for NoteAPI functions
*   - add()
*   - listAll()
*   - listActiveNotes()
*   - listArchivedNotes()
*   - listNotesBySelectedPriority()
*   - deleteNotes()
*   - updateNote()
* */

/*
class NoteAPITest {

    private var learnKotlin: Note? = null
    private var summerHoliday: Note? = null
    private var codeApp: Note? = null
    private var testApp: Note? = null
    private var swim: Note? = null
    private var populateNotes: NoteAPI? = NoteAPI()
    private var emptyNotes: NoteAPI? = NoteAPI()

    @BeforeEach  // Dummy Data -- set values and load into App ArrayList
    fun setup() {
        learnKotlin = Note("Learning Kotlin", 5, "College", false)
        summerHoliday = Note("Summer Holiday to France", 1, "Holiday", false)
        codeApp = Note("Code App", 4, "Work", false)
        testApp = Note("Test App", 4, "Work", true)
        swim = Note("Swim - Pool", 3, "Hobby", true)

        // Adding the notes to API
        populateNotes!!.add(learnKotlin!!)
        populateNotes!!.add(summerHoliday!!)
        populateNotes!!.add(codeApp!!)
        populateNotes!!.add(testApp!!)
        populateNotes!!.add(swim!!)
    }

    @AfterEach  // Deletes the values after each test -- clean the ArrayList
    fun tearDown() {
        learnKotlin = null
        summerHoliday = null
        codeApp = null
        testApp = null
        swim = null
        populateNotes = null
        emptyNotes = null
    }

    // Test class for adding notes
    @Nested
    inner class AddNotes {

        @Test  // Test to add new note into ArrayList that is not empty
        fun `adding a Note fo populated ArrayList`() {
            val newNote = Note("Study", 1, "College", false)
            assertEquals(5, populateNotes!!.numberOfNotes())         // 5 notes from dummy data
            assertTrue(/* condition = */ populateNotes!!.add(newNote))  // adding a note
            assertEquals(6, populateNotes!!.numberOfNotes())         // 6 notes in array list
            assertEquals(newNote, populateNotes!!.findNote(populateNotes!!.numberOfNotes() - 1))
        }

        @Test // Test to add new note into empty ArrayList
        fun `adding a Note to empty ArrayList`() {
            val newNote = Note("Lambdas", 2, "College", false)
            assertEquals(0, actual = emptyNotes!!.numberOfNotes())
            assertTrue(/* condition = */ emptyNotes!!.add(newNote))
            assertEquals(1, emptyNotes!!.numberOfNotes())
            assertEquals(newNote, emptyNotes!!.findNote(index = emptyNotes!!.numberOfNotes() - 1))
        }
    }

    // Test class for listing notes
    @Nested
    inner class ListNotes {

        // Test to list notes when ArrayList is empty --> NoteAPI function listAllNotes
        @Test
        fun `list all notes for empty ArrayList`() {
            assertEquals(0, emptyNotes!!.numberOfNotes())
            assertTrue(emptyNotes!!.listAllNotes().contains("No notes stored"))
        }

        // Test to list notes for populated ArrayList -- dummy data @BeforeEach
        @Test
        fun `list all notes from populated ArrayList`() {
            assertEquals(5, populateNotes!!.numberOfNotes())
            val noteString = populateNotes!!.listAllNotes()
            assertTrue(noteString.contains("Learning Kotlin"))
            assertTrue(noteString.contains("Summer Holiday"))
            assertTrue(noteString.contains("Code App"))
            assertTrue(noteString.contains("Test App"))
            assertTrue(noteString.contains("Swim"))
        }


        // Test to list Active notes when ArrayList is empty --> NoteAPI function listActiveNotes
        @Test
        fun `listActiveNotes for empty ArrayList`() {
            assertEquals(0, emptyNotes!!.numberOfNotes())
            assertTrue(
                emptyNotes!!.listActiveNotes().contains("No active notes")
            )
        }

        // Test to listActiveNotes from populated ArrayList --> NoteAPI function listActiveNotes
        @Test
        fun `listActiveNotes from populated ArrayList`() {
            assertEquals(3, populateNotes!!.numberOfActiveNotes())
            val activeNotesString = populateNotes!!.listActiveNotes()
            assertTrue(activeNotesString.contains("Learning Kotlin"))
            assertTrue(activeNotesString.contains("Code App"))
            assertTrue(activeNotesString.contains("Summer Holiday"))
            assertFalse(activeNotesString.contains("Test App"))
            assertFalse(activeNotesString.contains("Swim"))
        }

        // Test to listArchivedNotes from empty ArrayList --> NoteAPI function listArchivedNotes
        @Test
        fun `listArchivedNotes from empty ArrayList`() {
            assertEquals(0, emptyNotes!!.numberOfNotes())
            assertTrue(
                emptyNotes!!.listArchivedNotes().contains("No archived notes")
            )
        }

        // Test to listArchivedNotes from populated ArrayList --> NoteAPI function listArchivedNotes
        @Test
        fun `listArchivedNotes from populated ArrayList`() {
            assertEquals(2, populateNotes!!.numberOfArchivedNotes())
            val archivedNotesString = populateNotes!!.listArchivedNotes()
            assertFalse(archivedNotesString.contains("Learning Kotlin"))
            assertFalse(archivedNotesString.contains("Code App"))
            assertFalse(archivedNotesString.contains("Summer Holiday"))
            assertTrue(archivedNotesString.contains("Test App"))
            assertTrue(archivedNotesString.contains("Swim"))
        }

        // Test list notes by priority --> 1 to 5 from NoteAPI function listNotesBySelectedPriority
        @Test
        fun `listNotesBySelectedPriority from empty ArrayList`() {
            assertEquals(0, emptyNotes!!.numberOfNotes())
            assertTrue(
                emptyNotes!!.listNotesBySelectedPriority(1).contains("No notes")
            )
        }

        // Test list notes by priority from full ArrayList --> 1 to 5 from NoteAPI function listNotesBySelectedPriority
        @Test
        fun `listNotesBySelectedPriority from populated ArrayList with no notes of priority`() {
            //Priority notes from dummy data: 1 (1 note), 2 (none), 3 (1 note). 4 (2 notes), 5 (1 note)
            assertEquals(5, populateNotes!!.numberOfNotes())
            val priorityToString = populateNotes!!.listNotesBySelectedPriority(2)
            assertTrue(priorityToString.contains("No notes with priority"))
            assertTrue(priorityToString.contains("2"))
        }

        // Test list notes by priority to show all matching notes --> from noteAPI function listNotesBySelectedPriority
        @Test
        fun `listNotesBySelectedPriority shows notes with matching priority`() {
            //Priority 1 (1 note), 2 (none), 3 (1 note). 4 (2 notes), 5 (1 note)
            assertEquals(5, populateNotes!!.numberOfNotes())
            val priority1String = populateNotes!!.listNotesBySelectedPriority(1)
            assertTrue(priority1String.contains("1 notes with priority"))
            assertTrue(priority1String.contains("priority 1"))
            assertTrue(priority1String.contains("Summer Holiday"))
            assertFalse(priority1String.contains("Swim"))
            assertFalse(priority1String.contains("Learning Kotlin"))
            assertFalse(priority1String.contains("Code App"))
            assertFalse(priority1String.contains("Test App"))


            val priority4String = populateNotes!!.listNotesBySelectedPriority(4)
            assertTrue(priority4String.contains("2 notes with priority"))
            assertTrue(priority4String.contains("priority 4"))
            assertFalse(priority4String.contains("Swim"))
            assertTrue(priority4String.contains("Code App"))
            assertTrue(priority4String.contains("Test App"))
            assertFalse(priority4String.contains("Learning Kotlin"))
            assertFalse(priority4String.contains("Summer Holiday"))

        }


    }

    // Test class for deleting notes
    @Nested
    inner class DeleteNotes {

        // Delete notes from empty array list -> return null
        @Test
        fun `delete note from empty ArrayList`(){
            assertNull(emptyNotes!!.deleteNote(0))
            assertNull(populateNotes!!.deleteNote(-1))
            assertNull(populateNotes!!.deleteNote(5))
        }

        // Delete note that exists
        @Test
        fun `delete note that exists`(){
            assertEquals(5, populateNotes!!.numberOfNotes())
            assertEquals(swim, populateNotes!!.deleteNote(4))
            assertEquals(4,populateNotes!!.numberOfNotes())
            assertEquals(testApp, populateNotes!!.deleteNote(3))
            assertEquals(3, populateNotes!!.numberOfNotes())
        }
    }

    // Test class update notes
    @Nested
    inner class UpdateNotes{

        // Function to update non-existing note
        @Test
        fun `function to update notes from empty ArrayLIst`(){
            assertFalse(populateNotes!!.updateNote(6, Note("New Title", 1, "School", false)))
            assertFalse(populateNotes!!.updateNote(-1, Note("New Title", 2, "Coding", false)))
            assertFalse(emptyNotes!!.updateNote(0, Note("Updating note", 4, "Test Category", true)))
        }

        // Function to update note that does exist
        @Test
        fun `function to update existing note`(){
            // Check that note with index 2 exists and content is matching
            //  codeApp = Note("Code App", 4, "Work", false)
            assertEquals(codeApp, populateNotes!!.findNote(2))
            assertEquals("Code App", populateNotes!!.findNote(2)!!.noteTitle)
            assertEquals(4, populateNotes!!.findNote(2)!!.notePriority)
            assertEquals("Work", populateNotes!!.findNote(2)!!.noteCategory)
            // Update new information
            assertTrue(populateNotes!!.updateNote(2, Note("New Title", 2, "Coding", false)))
            assertEquals("New Title", populateNotes!!.findNote(2)!!.noteTitle)
            assertEquals(2, populateNotes!!.findNote(2)!!.notePriority)
            assertEquals("Coding", populateNotes!!.findNote(2)!!.noteCategory)
        }
    }
}

 */