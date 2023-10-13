package controllers
// Imports
import models.Note
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

/* Testing class for NoteAPI functions
*   - add()
*   - listAll()*/
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
        testApp = Note("Test App", 4, "Work", false)
        swim = Note("Swim - Pool", 3, "Hobby", false)

        // Adding the notes to api
        populateNotes!!.add(learnKotlin!!)
        populateNotes!!.add(summerHoliday!!)
        populateNotes!!.add(codeApp!!)
        populateNotes!!.add(testApp!!)
        populateNotes!!.add(swim!!)
    }

    @AfterEach  // Deletes the values after each test -- clean the ArrayList
    fun tearDown(){
        learnKotlin = null
        summerHoliday = null
        codeApp = null
        testApp = null
        swim = null
        populateNotes = null
        emptyNotes = null
    }

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

    @Nested
    inner class ListNotes {

        // Test to list notes when ArrayListt is empty --> NoteAPI function listAllNotes
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
    }

}