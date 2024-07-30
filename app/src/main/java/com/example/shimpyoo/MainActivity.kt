package com.example.shimpyoo

import android.content.ContentValues
import android.content.pm.PackageManager
import android.media.MediaPlayer
import android.media.MediaRecorder
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.io.File
import java.io.FileOutputStream

class MainActivity : AppCompatActivity() {
    // 녹음 파일의 저장 경로를 나타내는 변수
    private var output: String? = null

    // 오디오 녹음을 관리하는 MediaRecorder 객체를 나타냄
    private var mediaRecorder: MediaRecorder? = null

    // 오디오 재생을 관리하는 MediaPlayer 객체를 나타냄
    private var mediaPlayer: MediaPlayer? = null

    // 녹음 상태를 나타내는 변수. 녹음 중이면 true, 아니면 false
    private var state: Boolean = false

    // onCreate 메서드는 액티비티가 생성될 때 호출
    // UI 레이아웃을 설정 및 초기화
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // output 파일 경로 초기화
        // 기기의 Music 디렉토리에 녹음 파일을 저장
        val musicDir = getExternalFilesDir(Environment.DIRECTORY_MUSIC)
        val recordingFile = File(musicDir, "recording.wav")
        output = recordingFile.absolutePath

        // findViewById를 사용하여 레이아웃 파일에서
        // button_start, button_stop, button_play 버튼을 찾음
        val buttonStart: Button = findViewById(R.id.button_start)
        val buttonStop: Button = findViewById(R.id.button_stop)
        val buttonPlay: Button = findViewById(R.id.button_play)
        val buttonDownload: Button = findViewById(R.id.button_download)

        // buttonStart의 클릭 리스너를 설정
        // 클릭 시, 오디오 녹음 및 저장소 쓰기 권한이 있는지 확인
        buttonStart.setOnClickListener {
            // 권한이 없으면 ActivityCompat.requestPermissions를 사용하여 필요한 권한을 요청
            if (ContextCompat.checkSelfPermission(this,
                    android.Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this,
                    android.Manifest.permission.READ_MEDIA_AUDIO) != PackageManager.PERMISSION_GRANTED) {

                val permissions = arrayOf(android.Manifest.permission.RECORD_AUDIO,
                    android.Manifest.permission.READ_MEDIA_AUDIO)
                ActivityCompat.requestPermissions(this, permissions, 0)
            } else {
                // 권한이 있으면 startRecording 메서드를 호출하여 녹음을 시작
                startRecording()
            }
        }

        // buttonStop의 클릭 리스너를 설정
        // 클릭 시 stopRecording 메서드를 호출하여 녹음을 중지
        buttonStop.setOnClickListener {
            stopRecording()
        }

        // buttonPlay의 클릭 리스너를 설정
        // 클릭 시 startPlaying 메서드를 호출하여 녹음된 파일을 재생
        buttonPlay.setOnClickListener {
            startPlaying()
        }

        // buttonDownload의 클릭 리스너를 설정
        // 클릭 시 다운로드 메서드를 호출하여 녹음된 파일을 다운로드
        buttonDownload.setOnClickListener {
            downloadRecording()
        }
    }

    private fun startRecording() {
        // state 변수가 false일 때만 동작 -> 녹음 중이 아닐 때만
        if (!state) {
            // MediaRecorder 객체를 초기화하고,
            // 오디오 소스, 출력 포맷, 오디오 인코더, 출력 파일을 설정
            mediaRecorder = MediaRecorder().apply {
                setAudioSource(MediaRecorder.AudioSource.MIC)
                setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP)
                setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB)
                setOutputFile(output)
                setAudioEncodingBitRate(128000)
                setAudioSamplingRate(44100)
            }

            // 로그로 파일 경로 출력
            Log.d("Recording Path", "Recording file saved at: $output")

            try {
                // prepare와 start를 호출하여 녹음을 시작
                mediaRecorder?.apply {
                    prepare()
                    start()
                }
                // state를 true로 설정 (녹음 중)
                state = true
                Log.d("Recording", "Started recording successfully")
                // 토스트 메시지 표시
                Toast.makeText(this@MainActivity, "레코딩 시작", Toast.LENGTH_SHORT).show()
            } catch (e: Exception) {
                e.printStackTrace()
                Log.e("Recording", "Recording failed: ${e.message}")
                Toast.makeText(this@MainActivity, "레코딩 오류 발생: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this, "이미 레코딩 중입니다.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun stopRecording() {
        // state 변수가 true일 때만 동작(녹음 중일 때만)
        if (state) {
            try {
                // MediaRecorder 객체의 stop, reset, release를 호출하여
                // 녹음을 중지하고 리소스를 해제
                mediaRecorder?.apply {
                    stop()
                    reset()
                    release()
                }
                // state를 false로 설정
                state = false
                Log.d("Recording", "Stopped recording successfully")
                Toast.makeText(this, "중지 되었습니다.", Toast.LENGTH_SHORT).show()
            } catch (e: Exception) {
                e.printStackTrace()
                Log.e("Recording", "Stop recording failed: ${e.message}")
                Toast.makeText(this, "중지 오류 발생: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this, "레코딩 상태가 아닙니다.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun startPlaying() {
        mediaPlayer = MediaPlayer().apply {
            try {
                setDataSource(output)
                prepare()
                start()
                Toast.makeText(this@MainActivity, "재생 시작", Toast.LENGTH_SHORT).show()
            } catch (e: Exception) {
                e.printStackTrace()
                Log.e("Playback", "Playback failed: ${e.message}")
                Toast.makeText(this@MainActivity, "재생 오류 발생: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun downloadRecording() {
        val values = ContentValues().apply {
            put(MediaStore.Audio.Media.DISPLAY_NAME, "recording.wav")
            put(MediaStore.Audio.Media.MIME_TYPE, "audio/wav")
            put(MediaStore.Audio.Media.RELATIVE_PATH, Environment.DIRECTORY_MUSIC + "/MyRecordings")
            put(MediaStore.Audio.Media.IS_PENDING, true)
        }

        val resolver = contentResolver
        val collection = MediaStore.Audio.Media.getContentUri(MediaStore.VOLUME_EXTERNAL_PRIMARY)
        val item = resolver.insert(collection, values)

        try {
            item?.let { uri ->
                resolver.openFileDescriptor(uri, "w", null).use { pfd ->
                    FileOutputStream(pfd!!.fileDescriptor).use { out ->
                        File(output).inputStream().use { input ->
                            input.copyTo(out)
                        }
                    }
                }
                values.clear()
                values.put(MediaStore.Audio.Media.IS_PENDING, false)
                resolver.update(uri, values, null, null)
                Toast.makeText(this, "다운로드 완료: $uri", Toast.LENGTH_SHORT).show()
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e("Download", "Download failed: ${e.message}")
            Toast.makeText(this, "다운로드 오류 발생: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }
}