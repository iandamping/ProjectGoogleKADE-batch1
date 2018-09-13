

import com.example.junemon.kotlinnetworking.MainApplication
import com.example.junemon.kotlinnetworking.feature.nextmatch.NextMatchFragmentView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class NextMatchFragmentPresenterTest {
    @Mock
    private lateinit var composite: CompositeDisposable
    @Mock
    private lateinit var mView: NextMatchFragmentView

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        composite = CompositeDisposable()

    }

    @Test
    fun getData() {
        composite.add(MainApplication.getFootballEvent.getNextEventData()
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result -> mView.onSuccess(result.events) },
                        { error -> mView.onFailed(error.localizedMessage) }))
    }

}