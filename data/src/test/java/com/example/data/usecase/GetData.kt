import com.example.data.goods.usecase.GetData
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetDataTest {
    @Test
    fun testGetData(){
        runBlocking {
            val getData = GetData()
            getData.getData()
        }


    }
}
