package com.NagiGroup.repository;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Repository;

import com.NagiGroup.connection.web.DbContextService;
import com.NagiGroup.conroller.CommonController;
import com.NagiGroup.dto.driverDocument.DriverDocumentManagementDto;
import com.NagiGroup.dto.subFolder.SubFolderDto;
import com.NagiGroup.helperClass.DocumentDetails;
import com.NagiGroup.model.DriverDocumentManagementModel;
import com.NagiGroup.query.QueryMaster;
import com.NagiGroup.service.DriverDocumentManagementService;
import com.NagiGroup.utility.ApiResponse;
import com.NagiGroup.utility.PropertiesReader;


@Repository
public class DriverDocumentManagementRepository implements DriverDocumentManagementService {
	
	private DbContextService dbContextserviceBms;
	public DriverDocumentManagementRepository(DbContextService dbContextserviceBms){
		this.dbContextserviceBms=dbContextserviceBms;
	}
	public static String rootFolder = PropertiesReader.getProperty("constant", "BASEURL_FOR_YEAR");
	  private static final List<String> SUBFOLDERS = Arrays.asList(
	            "ANNUAL DOT INSPECTION",
	            "DISPATCH RECORD",
	            "DRIVER + EQUIPMENT INFORMATION",
	            "FUEL RECEIPT", 
	            "IFTA QUARTERLY",
	            "P.O.D + LUMPER RECEIPT + SCALE",
	            "TRUCK AND TRAILER REPAIR",
	            "TRUCK TRAILER SERVICES"
	    );

	@Override
	public ApiResponse<Integer> driverDocumentManagemeInsert(
			DriverDocumentManagementModel driverDocumentManagementModel, HttpServletRequest request) {

		int document_id = 0;
		String document_name = "";
		String original_document_name = "";
		List<DocumentDetails> documents = new ArrayList<>();
		
		System.out.println("driverDocumentManagementModel Name: "+driverDocumentManagementModel.getAnnual_dot_inspection().getName());

		// Check each field and add to the list
		if (driverDocumentManagementModel.getRoc() != null) {
			
		    documents.add(new DocumentDetails(CommonController.getFileNameWithoutExtension(driverDocumentManagementModel.getRoc()),
		                                      driverDocumentManagementModel.getRoc().getOriginalFilename()));
		}
		if (driverDocumentManagementModel.getAnnual_dot_inspection() != null) {
		    documents.add(new DocumentDetails(CommonController.getFileNameWithoutExtension(driverDocumentManagementModel.getAnnual_dot_inspection()),
		                                      driverDocumentManagementModel.getAnnual_dot_inspection().getOriginalFilename()));
		}
		if (driverDocumentManagementModel.getDriver_equipment_information() != null) {
		    documents.add(new DocumentDetails(CommonController.getFileNameWithoutExtension(driverDocumentManagementModel.getDriver_equipment_information()),
		                                      driverDocumentManagementModel.getDriver_equipment_information().getOriginalFilename()));
		}
		if (driverDocumentManagementModel.getFuel_reciept() != null) {
		    documents.add(new DocumentDetails(CommonController.getFileNameWithoutExtension(driverDocumentManagementModel.getFuel_reciept()),
		                                      driverDocumentManagementModel.getFuel_reciept().getOriginalFilename()));
		}
		if (driverDocumentManagementModel.getIfta_quaterly() != null) {
		    documents.add(new DocumentDetails(CommonController.getFileNameWithoutExtension(driverDocumentManagementModel.getIfta_quaterly()),
		                                      driverDocumentManagementModel.getIfta_quaterly().getOriginalFilename()));
		}
		if (driverDocumentManagementModel.getPod() != null) {
		    documents.add(new DocumentDetails(CommonController.getFileNameWithoutExtension(driverDocumentManagementModel.getPod()),
		                                      driverDocumentManagementModel.getPod().getOriginalFilename()));
		}
		if (driverDocumentManagementModel.getTruck_and_trailer_repair() != null) {
		    documents.add(new DocumentDetails(CommonController.getFileNameWithoutExtension(driverDocumentManagementModel.getTruck_and_trailer_repair()),
		                                      driverDocumentManagementModel.getTruck_and_trailer_repair().getOriginalFilename()));
		}
		if (driverDocumentManagementModel.getTruck_trailer_serivices() != null) {
		    documents.add(new DocumentDetails(CommonController.getFileNameWithoutExtension(driverDocumentManagementModel.getTruck_trailer_serivices()),
		                                      driverDocumentManagementModel.getTruck_trailer_serivices().getOriginalFilename()));
		}
		String originalFilename ="";
        LocalDate currentDate = LocalDate.now();
        int monthValue = currentDate.getMonthValue(); // Numeric month (1-12)
        Month month = currentDate.getMonth();
        
        String yearFolder = rootFolder +"_"+ Year.now().getValue();
         
        String driverFolder = yearFolder + "/"+month+"/" + driverDocumentManagementModel.getDriver_name();
        String targetFolder = driverFolder + "/" + driverDocumentManagementModel.getSub_folder_name();
       
		// Now insert each document into the database
		for (DocumentDetails doc : documents) {
			System.out.println("DocumentDetails: "+doc.getDocumentName());
			System.out.println("DocumentDetails: "+doc.getOriginalDocumentName());
			Object[] param = {
					doc.getDocumentName(),
					doc.getOriginalDocumentName(),
					targetFolder,
					driverDocumentManagementModel.getSub_folder_id(),
					driverDocumentManagementModel.getDriver_id()
					
	};
			    
			    
			    try {
			        // Insert user details into the database and get the new user ID
			         document_id = dbContextserviceBms.QueryToFirstWithInt(QueryMaster.insert_driver_document, param);
			        System.out.println("document_id: " + document_id);
	        
			    } catch (Exception e) {
			    	e.printStackTrace();
			        System.err.println("Error during user creation: " + e.getMessage());
		    }
		
			
		}

		
	
		try {
			
            
            System.out.println("targetFolder: "+targetFolder);
            File folder = new File(targetFolder);
            if (!folder.exists()) {
                folder.mkdirs();
            }
            
            if(driverDocumentManagementModel.getRoc()!=null) {
            	originalFilename = driverDocumentManagementModel.getRoc().getOriginalFilename();
            	Path filePath = Paths.get(targetFolder, originalFilename);
            	Files.write(filePath, driverDocumentManagementModel.getRoc().getBytes());
            	
            }
            return new ApiResponse<Integer>(true,
						PropertiesReader.getProperty("message", "document_saved_successfully"), true, 1, 1);
        
		} catch (IOException e) {
        	e.printStackTrace();
        	return new ApiResponse<Integer>(false,
						PropertiesReader.getProperty("message", "document_upload_failed"), false, 0, 1);
        }
			
	}
	
	
	public List<SubFolderDto> getSubFolder() {
		List<SubFolderDto> subFolderDtos = null;
		return subFolderDtos = dbContextserviceBms.QueryToList(QueryMaster.get_all_sub_folders, SubFolderDto.class);
		
	}


	@Override
	public ApiResponse<List<DriverDocumentManagementDto>> getDriverDocuments() {
		
		List<DriverDocumentManagementDto> driverDocumentManagementDtos = null;
		try {
			driverDocumentManagementDtos = dbContextserviceBms.QueryToList(QueryMaster.get_all_driver_documents, DriverDocumentManagementDto.class);
			
			return new ApiResponse<List<DriverDocumentManagementDto>>(true, "Total Record " + driverDocumentManagementDtos.size() + " ", true,
					driverDocumentManagementDtos, driverDocumentManagementDtos.size());
		} catch (Exception e) {
			
			return new ApiResponse<List<DriverDocumentManagementDto>>(false, e.getMessage(), false, null, 0);
		}
	}


	@Override
	public ApiResponse<List<SubFolderDto>> getsubFolderMaster() {
		
		List<SubFolderDto> subFolderDtos = null;
		try {
			subFolderDtos = dbContextserviceBms.QueryToList(QueryMaster.get_all_sub_folders, SubFolderDto.class);
			
			return new ApiResponse<List<SubFolderDto>>(true, "Total Record " + subFolderDtos.size() + " ", true,
					subFolderDtos, subFolderDtos.size());
		} catch (Exception e) {
			
			return new ApiResponse<List<SubFolderDto>>(false, e.getMessage(), false, null, 0);
		}
	}
	
	
	

}
