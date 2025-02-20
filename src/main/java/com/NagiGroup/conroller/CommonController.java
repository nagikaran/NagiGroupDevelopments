package com.NagiGroup.conroller;

import org.springframework.web.multipart.MultipartFile;

public class CommonController {
	
	public static String getFileNameWithoutExtension(MultipartFile file) {
        if (file == null || file.getOriginalFilename() == null) {
            return null;
        }
        
        String originalFileName = file.getOriginalFilename();
        int lastDotIndex = originalFileName.lastIndexOf(".");
        
        if (lastDotIndex == -1) {
            return originalFileName; // No extension found, return as is
        }
        
        return originalFileName.substring(0, lastDotIndex);
    }

}
