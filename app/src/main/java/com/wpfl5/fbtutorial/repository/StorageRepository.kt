package com.wpfl5.fbtutorial.repository

import android.net.Uri
import com.wpfl5.fbtutorial.model.FbResponse
import com.wpfl5.fbtutorial.model.StorageResponse
import kotlinx.coroutines.flow.Flow

/*
    Error Message : StorageException
        ERROR_UNKNOWN	알 수 없는 오류가 발생했습니다.
        ERROR_OBJECT_NOT_FOUND	해당 참조에 객체가 없습니다.
        ERROR_BUCKET_NOT_FOUND	Cloud Storage에 구성된 버킷이 없습니다.
        ERROR_PROJECT_NOT_FOUND	Cloud Storage에 구성된 프로젝트가 없습니다.
        ERROR_QUOTA_EXCEEDED	Cloud Storage 버킷의 할당량이 초과되었습니다. 무료 등급을 사용하는 경우 유료 요금제로 업그레이드하세요. 유료 요금제를 사용하는 경우 Firebase 지원팀에 문의하세요.
        ERROR_NOT_AUTHENTICATED	사용자가 인증되지 않았습니다. 인증한 후 다시 시도해 보세요.
        ERROR_NOT_AUTHORIZED	사용자에게 해당 작업을 할 수 있는 권한이 없습니다. 규칙이 올바른지 확인해 보세요.
        ERROR_RETRY_LIMIT_EXCEEDED	작업(업로드, 다운로드, 삭제 등)의 최대 제한 시간이 초과되었습니다. 다시 시도하세요.
        ERROR_INVALID_CHECKSUM	클라이언트의 파일과 서버에서 수신한 파일의 체크섬이 일치하지 않습니다. 다시 업로드해 보세요.
        ERROR_CANCELED	사용자가 작업을 취소했습니다.
 */

interface StorageRepository {
    suspend fun saveImgFile(filePath: Uri) : Flow<FbResponse<Boolean>>
    suspend fun getStorageList() : Flow<FbResponse<List<StorageResponse>>>
    suspend fun deleteFile(filePath: String) : Flow<FbResponse<Boolean>>
    suspend fun downloadFile(filePath: String) : Flow<FbResponse<Boolean>>
}