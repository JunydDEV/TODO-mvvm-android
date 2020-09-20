package com.reminder

import com.employeeapp.data.AppRepository
import com.reminder.data.Note
import com.reminder.utils.AppConstants
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import java.util.*

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class CreateNoteFragmentTest {

    @Mock
    private lateinit var appRepository: AppRepository

    @Test
    fun validateFakeNote() {
        var note = getFakeNote()
        assertTrue(validateNotes(note))
    }

    @Test
    fun givenFakeNote_whenInserted_shouldReturnPositiveLongValue() {
        var note = getFakeNote()
        assertTrue(appRepository.insertNotes(note) != AppConstants.OPERATION_FAILED)
    }

    @Test
    fun givenFakeNote_whenUpdated_shouldReturnPositiveLongValue() {
        var note = getFakeNote()
        assertTrue(appRepository.updateNote(note) != AppConstants.OPERATION_FAILED)
    }

    private fun getFakeNote(): Note {
        return Note(
            notesId = UUID.randomUUID().toString(),
            notesTitle = "Test NoteMockito",
            notesDescription = "Test Note Description",
            notesDate = "10/02/2020",
            isCompleted = false
        )
    }

    private fun validateNotes(notes: Note): Boolean {
        when {
            notes.notesTitle.isEmpty() -> {
                return false
            }
            notes.notesDescription.isEmpty() -> {
                return false
            }
            notes.notesDate.isEmpty() -> {
                return false
            }
        }
        return true
    }
}