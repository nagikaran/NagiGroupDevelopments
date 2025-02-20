package com.NagiGroup.dto.driverDocument;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DriverDocumentManagementDto {
	
	private String document_name;
	private String original_document_name;
	private String document_path;
	private int sub_folder_id;	
	private String sub_folder_name;
	private int driver_id;
	private String driver_name;
	public String getDocument_name() {
		return document_name;
	}
	public void setDocument_name(String document_name) {
		this.document_name = document_name;
	}
	public String getOriginal_document_name() {
		return original_document_name;
	}
	public void setOriginal_document_name(String original_document_name) {
		this.original_document_name = original_document_name;
	}
	public String getDocument_path() {
		return document_path;
	}
	public void setDocument_path(String document_path) {
		this.document_path = document_path;
	}
	public int getSub_folder_id() {
		return sub_folder_id;
	}
	public void setSub_folder_id(int sub_folder_id) {
		this.sub_folder_id = sub_folder_id;
	}
	public String getSub_folder_name() {
		return sub_folder_name;
	}
	public void setSub_folder_name(String sub_folder_name) {
		this.sub_folder_name = sub_folder_name;
	}
	public int getDriver_id() {
		return driver_id;
	}
	public void setDriver_id(int driver_id) {
		this.driver_id = driver_id;
	}
	public String getDriver_name() {
		return driver_name;
	}
	public void setDriver_name(String driver_name) {
		this.driver_name = driver_name;
	}
	
	
	

}
