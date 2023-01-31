package shouman.iss.bored.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import shouman.iss.bored.ui.activity.viewModel.HomePageViewModel
import shouman.iss.bored.ui.theme.BoredTheme
import androidx.lifecycle.viewmodel.compose.viewModel
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment
import shouman.iss.bored.data.entity.BoredDataEntity

class HomePageActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            BoredTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    BoredRow()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BoredRow(label: String = "label", data: String = "data") {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
    ) {
        Text(text = label,color= Color.Red,  modifier = Modifier
            .padding(6.dp))
        Text(text = data,color= Color.Blue, modifier = Modifier
            .padding(6.dp))
    }
}


@Composable
fun BoredCard(entity: BoredDataEntity, viewModel: HomePageViewModel){
    Column() {
        BoredRow(label = "Activity", entity.activity)
        BoredRow(label = "Type", entity.type)
        BoredRow(label = "Key", entity.key)
        Row(horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
            ) {
            BoredButton(viewModel = viewModel)
        }

    }
}

@Composable
fun BoredButton(viewModel: HomePageViewModel){
    val uiState by viewModel.uiState.collectAsState()
    Button(onClick = {
        viewModel.getBoredEntity()
    }) {
        Text(text = "I am bored")
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    val viewModel: HomePageViewModel = viewModel()
    val uiState by viewModel.uiState.collectAsState()
    BoredTheme {
        if(uiState.initialState && !uiState.isLoading){
            Column(modifier = Modifier.fillMaxWidth()) {
                BoredButton(viewModel)
            }
        }
        if(uiState.boredDataEntity != null && !uiState.isLoading){
            BoredCard(
                viewModel = viewModel,
                entity = uiState.boredDataEntity!!)
        }
        if (uiState.isLoading) {
            Column(
                verticalArrangement = Arrangement.Center, modifier = Modifier
                    .fillMaxHeight()
            ) {
                Row(
                    horizontalArrangement = Arrangement.Center, modifier = Modifier
                        .fillMaxWidth()
                ) {
                    CircularProgressIndicator()
                }
            }


        }

    }
}