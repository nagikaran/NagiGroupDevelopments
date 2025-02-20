package com.NagiGroup.query;

public final class QueryMaster {
	public static String user_login = "select * from user_login(?,?)";
	public static String insert_user = "SELECT insert_user(?,?,?,?,?,?,?,?,?,?,?)";
	public static String get_all_users = "SELECT * FROM public.get_all_users();";
	public static String get_all_sub_folders = "select * from get_all_sub_folders()";
	public static String insert_driver_document = "select * from insert_driver_document(?,?,?,?,?)";
	public static String get_all_driver_documents = "select * from get_all_driver_documents()";
}
