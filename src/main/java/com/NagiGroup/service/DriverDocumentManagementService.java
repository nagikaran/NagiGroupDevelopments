package com.NagiGroup.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.NagiGroup.dto.driverDocument.DriverDocumentManagementDto;
import com.NagiGroup.dto.subFolder.SubFolderDto;
import com.NagiGroup.model.DriverDocumentManagementModel;
import com.NagiGroup.utility.ApiResponse;

@Service
public interface DriverDocumentManagementService {

	ApiResponse<Integer> driverDocumentManagemeInsert(DriverDocumentManagementModel driverDocumentManagementModel,
			HttpServletRequest request);

	ApiResponse<List<DriverDocumentManagementDto>> getDriverDocuments();

	ApiResponse<List<SubFolderDto>> getsubFolderMaster();

}
