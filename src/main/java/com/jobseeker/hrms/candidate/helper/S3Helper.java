package com.jobseeker.hrms.candidate.helper;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import software.amazon.awssdk.services.s3.model.GetUrlRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.ByteArrayOutputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

@Component
@RequiredArgsConstructor
public class S3Helper {
    @Value("${spring.cloud.aws.s3.bucket-name}")
    private String S3Bucket;

    private final S3Client s3Client = S3Client.builder()
            .region(Region.AP_SOUTHEAST_3)
            .build();

    public InputStreamReader readStreamFileFromS3(String key) {
        GetObjectRequest request = GetObjectRequest.builder()
                .bucket(S3Bucket)
                .key(key)
                .build();

        ResponseInputStream<GetObjectResponse> s3InputStream = s3Client.getObject(request);
        return new InputStreamReader(s3InputStream, StandardCharsets.UTF_8);
    }

    public ResponseInputStream<GetObjectResponse> fetchFileFromS3(String key) {
        GetObjectRequest request = GetObjectRequest.builder()
                .bucket(S3Bucket)
                .key(key)
                .build();

        return s3Client.getObject(request);
    }

    public String uploadFileToS3(String key, ByteArrayOutputStream baos) {
        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(S3Bucket)
                .key(key)
                .contentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
                .build();

        s3Client.putObject(putObjectRequest,
                RequestBody.fromBytes(baos.toByteArray()));

        // Generate and return S3 URL
        return s3Client.utilities().getUrl(GetUrlRequest.builder()
                        .bucket(S3Bucket)
                        .key(key)
                        .build())
                .toString();
    }
}
