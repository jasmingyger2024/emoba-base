package fhnw.emoba.modules.module05.squad.data

import org.junit.Assert.assertEquals
import org.junit.Test
import androidx.test.platform.app.InstrumentationRegistry

internal class RepositoryTest {

    @Test
    fun testLoadData() {
        // given
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext

        //when
        val squad = SquadRepository.loadSquad(appContext)

        //then
        assertEquals("Super hero squad", squad.squadName)
        assertEquals(2016,               squad.formed)
        assertEquals(4,                  squad.members.size)
        assertEquals("Molecule Man",     squad.members[0].name)
    }

}