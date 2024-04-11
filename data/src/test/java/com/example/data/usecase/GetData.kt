import com.example.data.goods.usecase.GetData
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetDataTest {
    @Test
    fun testGetData() {
        val getData = GetData()
        getData.getData()

    }
}
