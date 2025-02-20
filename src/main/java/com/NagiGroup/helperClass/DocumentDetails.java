package com.NagiGroup.helperClass;

public class DocumentDetails {
	String documentName;
    String originalDocumentName;

    public DocumentDetails(String documentName, String originalDocumentName) {
        this.documentName = documentName;
        this.originalDocumentName = originalDocumentName;
    }

	public String getDocumentName() {
		return documentName;
	}

	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}

	public String getOriginalDocumentName() {
		return originalDocumentName;
	}

	public void setOriginalDocumentName(String originalDocumentName) {
		this.originalDocumentName = originalDocumentName;
	}
    
    
    
}
