package br.com.haline.desafio_icasei.br.com.haline.desafio_icasei.feature.presentation.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import br.com.haline.desafio_icasei.R

@Composable
fun CreatePlaylistDialog(
    playlists: List<String>,
    onDismiss: () -> Unit,
    onConfirm: (String) -> Unit
) {
    var playlistName by remember { mutableStateOf("") }
    var showPlaylistSelector by remember { mutableStateOf(false) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(stringResource(R.string.playlist_create)) },
        text = {
            Column {
                OutlinedTextField(
                    value = playlistName,
                    onValueChange = { playlistName = it },
                    label = { Text(stringResource(R.string.playlist_name)) }
                )

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { showPlaylistSelector = true }) {

                    Text(stringResource(R.string.playlist_chose))
                }
            }
        },
        confirmButton = {
            TextButton(onClick = {
                onConfirm(playlistName)
                onDismiss()
            }) {
                Text(stringResource(R.string.playlist_save))
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(stringResource(R.string.playlist_cancel))
            }
        }
    )

    if (showPlaylistSelector) {
        SelectPlaylistDialog(
            playlists = playlists,
            onDismiss = { showPlaylistSelector = false },
            onSelect = { selectedPlaylist ->
                playlistName = selectedPlaylist
                showPlaylistSelector = false
            }
        )
    }
}

