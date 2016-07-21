package com.example;

public class Database {

	public String getBoletimOcorrenciaFromDatabasePorId(Integer idBO) {
		System.out.println("Consultado BO na base");
		return "BO_"+idBO;
	}

}
