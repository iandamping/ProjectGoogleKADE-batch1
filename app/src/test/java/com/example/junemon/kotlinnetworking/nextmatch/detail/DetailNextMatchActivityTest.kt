import com.example.junemon.kotlinnetworking.BuildConfig
import com.example.junemon.kotlinnetworking.databases.DatabaseModel
import com.example.junemon.kotlinnetworking.databases.DatabaseOpenHelper
import com.example.junemon.kotlinnetworking.feature.nextmatch.detail.DetailNextMatchPresenter
import com.example.junemon.kotlinnetworking.feature.nextmatch.detail.DetailNextMatchView
import com.example.junemon.kotlinnetworking.model.MainModelNextMatch
import org.jetbrains.anko.db.*
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(constants = BuildConfig::class)
class DetailNextMatchActivityTest {
    @Mock
    lateinit var mView: DetailNextMatchView
    @Mock
    lateinit var database: DatabaseOpenHelper
    @Mock
    lateinit var presenter: DetailNextMatchPresenter
    @Mock
    lateinit var data: MainModelNextMatch.Event


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = DetailNextMatchPresenter(mView)
        database = DatabaseOpenHelper(RuntimeEnvironment.application)
        data = MainModelNextMatch.Event()

    }

    @Test
    fun createDB() {
        database.use {
            createTable(DatabaseModel.TABLE_FAVORITE, true,
                    DatabaseModel.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                    DatabaseModel.AWAY_DEFENSE to TEXT,
                    DatabaseModel.AWAY_FORWARD to TEXT,
                    DatabaseModel.AWAY_GOALKEPEER to TEXT,
                    DatabaseModel.AWAY_GOAL_DETAILS to TEXT,
                    DatabaseModel.AWAY_MIDFIELD to TEXT,
                    DatabaseModel.DATE_EVENT to TEXT,
                    DatabaseModel.HOME_DEFENSE to TEXT,
                    DatabaseModel.HOME_FORWARD to TEXT,
                    DatabaseModel.HOME_GOALKEPEER to TEXT,
                    DatabaseModel.HOME_GOAL_DETAILS to TEXT,
                    DatabaseModel.HOME_MIDFIELD to TEXT,
                    DatabaseModel.TEAM_HOME_ID to TEXT + UNIQUE,
                    DatabaseModel.TEAM_HOME_NAME to TEXT,
                    DatabaseModel.TEAM_HOME_SCORE to TEXT,
                    DatabaseModel.TEAM_AWAY_ID to TEXT + UNIQUE,
                    DatabaseModel.TEAM_AWAY_NAME to TEXT,
                    DatabaseModel.TEAM_AWAY_SCORE to TEXT,
                    DatabaseModel.TEAM_AWAY_BADGE to TEXT,
                    DatabaseModel.TEAM_HOME_BADGE to TEXT)
        }
    }

    @Test
    fun addToFav() {

        database.use {
            insert(DatabaseModel.TABLE_FAVORITE,
                    DatabaseModel.TEAM_HOME_ID to data.idHomeTeam,
                    DatabaseModel.TEAM_AWAY_ID to data.idAwayTeam,
                    DatabaseModel.TEAM_HOME_NAME to data.strHomeTeam,
                    DatabaseModel.TEAM_AWAY_NAME to data.strAwayTeam,
                    DatabaseModel.TEAM_HOME_SCORE to data.intHomeScore,
                    DatabaseModel.TEAM_AWAY_SCORE to data.intAwayScore,
                    DatabaseModel.HOME_GOAL_DETAILS to data.strHomeGoalDetails,
                    DatabaseModel.AWAY_GOAL_DETAILS to data.strAwayGoalDetails,
                    DatabaseModel.HOME_GOALKEPEER to data.strHomeLineupGoalkeeper,
                    DatabaseModel.AWAY_GOALKEPEER to data.strAwayLineupGoalkeeper,
                    DatabaseModel.HOME_DEFENSE to data.strHomeLineupDefense,
                    DatabaseModel.AWAY_DEFENSE to data.strAwayLineupDefense,
                    DatabaseModel.HOME_MIDFIELD to data.strHomeLineupMidfield,
                    DatabaseModel.AWAY_MIDFIELD to data.strAwayLineupMidfield,
                    DatabaseModel.HOME_FORWARD to data.strHomeLineupForward,
                    DatabaseModel.AWAY_FORWARD to data.strAwayLineupForward,
                    DatabaseModel.DATE_EVENT to data.dateEvent
            )


        }
    }

    @After
    @Test
    fun getData() {
        presenter.acceptData(data)
    }
}