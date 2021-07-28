package br.inatel.icc.goMusic.config.cloudinary;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CloudinaryService {

	private Cloudinary cloudinary;

	@Value("${goMusic.cloudinary.default}")
	private String cloudinaryDefault;

	@Autowired
	public CloudinaryService(@Value("${goMusic.cloudinary.cloud_name}") String cloudName,
			@Value("${goMusic.cloudinary.api_key}") String apiKey,
			@Value("${goMusic.cloudinary.api_secret}") String apiSecret) {

		Map<String, String> valuesMap = new HashMap<>();

		valuesMap.put("cloud_name", cloudName);
		valuesMap.put("api_key", apiKey);
		valuesMap.put("api_secret", apiSecret);

		cloudinary = new Cloudinary(valuesMap);
	}

	@SuppressWarnings("rawtypes")
	public Map upload(MultipartFile multipartFile, String folder) throws IOException {
		File file = convertToFile(multipartFile);

		Map params = ObjectUtils.asMap("folder", cloudinaryDefault + "/" + folder, "unique_filename", true, "overwrite",
				true, "resource_type", "image");

		Map result = cloudinary.uploader().upload(file, params);
		file.delete();
		
		log.info("Uploading an image to the Cloudinary");
		return result;
	}

	@SuppressWarnings("rawtypes")
	public Map delete(String id) throws IOException {
		Map result = cloudinary.uploader().destroy(id, ObjectUtils.emptyMap());
		
		log.info("Removing an Image from Cloudinary");
		return result;
	}

	private File convertToFile(MultipartFile multipartFile) throws IOException {
		File file = new File(multipartFile.getOriginalFilename());
		FileOutputStream fo = new FileOutputStream(file);
		fo.write(multipartFile.getBytes());
		fo.close();

		return file;
	}

	public String getCloudinaryDefault() {
		return cloudinaryDefault;
	}

}
